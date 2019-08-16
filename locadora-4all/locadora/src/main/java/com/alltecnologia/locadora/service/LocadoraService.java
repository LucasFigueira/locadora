package com.alltecnologia.locadora.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alltecnologia.locadora.config.MsgConfiguration;
import com.alltecnologia.locadora.dto.AluguelResponseDto;
import com.alltecnologia.locadora.dto.DevolucaoRequestDto;
import com.alltecnologia.locadora.dto.LocacaoDto;
import com.alltecnologia.locadora.dto.LocadoraResponse;
import com.alltecnologia.locadora.exception.LocadoraBadRequestException;
import com.alltecnologia.locadora.model.Filme;
import com.alltecnologia.locadora.model.Locacao;
import com.alltecnologia.locadora.model.User;
import com.alltecnologia.locadora.repository.FilmeRepository;
import com.alltecnologia.locadora.repository.LocacaoRepository;
import com.alltecnologia.locadora.repository.UserRepository;
 
 
@Service
@Transactional(rollbackFor={Exception.class})
public class LocadoraService {
	
	@Autowired
	FilmeRepository filmeRp;
	
	@Autowired
	LocacaoRepository locacaoRp;
	
	@Autowired
	UserRepository userRp;
	
	@Autowired
	MsgConfiguration messages;
 
	
	public LocadoraResponse addMovie(@RequestBody Filme filme) {
		try {
			
			Filme findFilme = filmeRp.findByTituloIgnoreCase(filme.getTitulo());
			
			if(findFilme != null) {
				findFilme.setQuantidade(filme.getQuantidade()+1);
			}else {
				if(filme.getDisponivel() > filme.getQuantidade()) {
					return createResponse(HttpStatus.OK.value(), "A quantidade de filmes disponíveis não pode ser maior que a quantidade total.");
				}
				filmeRp.save(filme);
			} 
			
		}catch(Exception e) {
			String msg = messages.get("locadora.validation.addMovie");
			throw new LocadoraBadRequestException(msg);
		} 
		
		return createResponse(HttpStatus.CREATED.value(), "Filme adicionado");
	}
	
	
	
	public List<Filme> listarFilmesDisponiveis() {
		List<Filme> findFilme = null;
		
		try {
			findFilme = filmeRp.findFilmesDisponiveis();
 
		}catch(Exception e) {
			String msg = messages.get("locadora.validation.findFilmesByTitulo");
			throw new LocadoraBadRequestException(msg);
			
		} 
		
		return findFilme;
	}
	
	
	public AluguelResponseDto alugarFilme(LocacaoDto locacaoDto) {
	Locacao locacao = null;
	
	try { 
		 Filme filme = filmeRp.findByTituloIgnoreCase(locacaoDto.getFilme().getTitulo());
		 User user = userRp.findByIdUser(locacaoDto.getUser().getIdUser());
		
		 if(user == null) {
			String msg = messages.get("locadora.validation.findByIdUser");
			throw new LocadoraBadRequestException(msg);			
		 } 
		 
		 if(filme.getDisponivel() == 0) {
			 return createResponse(HttpStatus.OK.value(), "Nenhuma unidade disponível para esse título.", null);
		 }else {				 
			 filmeRp.updateUnidadeDisponivel(filme.getIdFilme(), filme.getDisponivel() - 1);
			 locacao = createLocacao(filme.getIdFilme(), user.getIdUser(), user.getName());
			 locacaoRp.save(locacao);
		 }
 
	}catch(Exception e) {
		String msg = messages.get("locadora.validation.alugarFilme");
		throw new LocadoraBadRequestException(msg);					
	} 
		
		return createResponse(HttpStatus.OK.value(), "Filme alugado com sucesso", locacao.getDataDevolucao());
	}
	
	
	public LocadoraResponse devolverFilme(DevolucaoRequestDto devolucaoRequest) {
		Date dataDevolucao = null;
		try {  
		    Locacao locacao = locacaoRp.findByTituloIgnoreCase(devolucaoRequest.getTitulo() ); 
		 
			if(locacao.getDataDevolucao() != null) {
				return createResponse(HttpStatus.OK.value(), "O filme já foi devolvido.");
			}
			Calendar cal = Calendar.getInstance(); 
			dataDevolucao = cal.getTime(); 
			
			locacao.setDataDevolucao(dataDevolucao);
			
			locacaoRp.dataDevolucao(dataDevolucao, locacao.getCodigoLocacao());
			filmeRp.updateUnidadeDisponivel(locacao.getFilme().getIdFilme(), locacao.getFilme().getDisponivel() + 1);
			
		}catch(Exception e) {
			String msg = messages.get("locadora.validation.devolverFilme");
			throw new LocadoraBadRequestException(msg);	
		}
		return createResponse(HttpStatus.OK.value(),"Filme devolvido com sucesso"); 
	}
	
	
	public AluguelResponseDto createResponse(Integer codigoHttp, String message, Date prazoDevolucao) {
		AluguelResponseDto aluguelResponseDto = new AluguelResponseDto();
		
		aluguelResponseDto.setCodigoHttp(codigoHttp);
		aluguelResponseDto.setMensagem(message);
		aluguelResponseDto.setPrazoDevolucao(prazoDevolucao);
		
		return aluguelResponseDto;
	}
	
	public LocadoraResponse createResponse(Integer codigoHttp, String mensagem) {
		LocadoraResponse locadoraResponse = new LocadoraResponse();
		
		locadoraResponse.setCodigoHttp(codigoHttp);
		locadoraResponse.setMensagem(mensagem);
		
		return locadoraResponse;
	}
	
	public Locacao createLocacao(Integer idFilme, Integer idUser, String userName) {
		Locacao locacao = new Locacao();
	    Filme filme = new Filme();
	    User user = new User();
		Calendar cal = Calendar.getInstance(); 
		Date startingDate = cal.getTime(); 
		locacao.setDataEmprestimo(startingDate);
		
		//Adiciono 3 dias na data de hoje para devolução do filme
		cal.add(Calendar.DATE, +3);		
		locacao.setPrazo_devolucao(cal.getTime());
		filme.setIdFilme(idFilme);
		locacao.setFilme(filme);
		
		user.setIdUser(idUser);
		user.setName(userName); 
		locacao.setUser(user);
		
		return locacao;
	}
	
}

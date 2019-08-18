package com.alltecnologia.locadora.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alltecnologia.locadora.dto.DevolucaoRequestDto;
import com.alltecnologia.locadora.dto.LocacaoDto;
import com.alltecnologia.locadora.dto.LocadoraResponse;
import com.alltecnologia.locadora.model.Filme;
import com.alltecnologia.locadora.service.LocadoraService;
 
 

@RestController
@RequestMapping(value = "/locadora", consumes = MediaType.APPLICATION_JSON_VALUE)
public class LocadoraController {
	
	@Autowired
	LocadoraService locadoraService;
	
	
	/**
	 * Adiciona novo filme na locadora
	 * 
	 * @param filme: Novo Filme
	 * @return response 201 (CREATED) - Conseguiu adicionar um filme
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/adicionar", consumes = MediaType.APPLICATION_JSON_VALUE, produces=  MediaType.APPLICATION_JSON_VALUE)    
	public LocadoraResponse addMovie(@RequestBody @Valid  Filme filme) { 
		
		return locadoraService.addMovie(filme);
	}
	
	/**
	 * Lista os filmes disponíveis para locação
	 * 
	 * @return Lista de filmes para locação
	 */
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/listar")  
	public List<Filme> listarFilmesDisponiveis() { 
		
		return locadoraService.listarFilmesDisponiveis();
	}
	
	/**
	 * Permite alugar um filme
	 * 
	 * @param  LocacaoDto: contém o titulo do filme
	 * @return response 200 (OK) - Conseguiu Alugar um filme
	 */
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping(value= "/alugar", consumes = MediaType.APPLICATION_JSON_VALUE, produces=  MediaType.APPLICATION_JSON_VALUE)  
	public LocadoraResponse alugarFilme(@RequestBody @Valid LocacaoDto locacaoRequest) { 
		 
		return locadoraService.alugarFilme(locacaoRequest);
	}
	
	
	/**
	 * Permite devolver um filme
	 * 
	 * @param codigo da locação
	 * @return response 200 (OK) - Conseguiu devolver o filme
	 */
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PutMapping(value ="/devolver", consumes = MediaType.APPLICATION_JSON_VALUE, produces=  MediaType.APPLICATION_JSON_VALUE)    
	public LocadoraResponse devolverFilme(@RequestBody  @Valid DevolucaoRequestDto devolucaoRequest) {
		return locadoraService.devolverFilme(devolucaoRequest);
	}

}

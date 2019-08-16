package com.alltecnologia.locadora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alltecnologia.locadora.config.MsgConfiguration;
import com.alltecnologia.locadora.dto.LocadoraResponse;
import com.alltecnologia.locadora.exception.LocadoraBadRequestException;
import com.alltecnologia.locadora.model.User;
import com.alltecnologia.locadora.repository.UserRepository;

@Service
public class AdminService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	MsgConfiguration messages;
 
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	public LocadoraResponse addUserByAdmin(@RequestBody User user) {
		try {
			String pwd = user.getPassword();
			String encryptPwd = passwordEncoder.encode(pwd);
			user.setPassword(encryptPwd); 		
			
			User usuarioCadastrado = userRepository.findByEmailIgnoreCase(user.getEmail());
			
			if(usuarioCadastrado != null) { 
				return createResponse(HttpStatus.OK.value(), "Usuário já cadastrado no sistema");
			}
			
			userRepository.save(user);
				
		}catch(Exception e) {
			String msg = messages.get("locadora.validation.addUserByAdmin");
			throw new LocadoraBadRequestException(msg);
		} 
		return createResponse(HttpStatus.CREATED.value(), "Usuário adicionado com sucesso");
	}
 	
	
	public LocadoraResponse createResponse(Integer codigoHttp, String message) {
		LocadoraResponse locadoraResponse = new LocadoraResponse();
		
		locadoraResponse.setCodigoHttp(codigoHttp);
		locadoraResponse.setMensagem(message);
		
		return locadoraResponse;
	}
  

}

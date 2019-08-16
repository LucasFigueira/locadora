package com.alltecnologia.locadora.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alltecnologia.locadora.dto.LocadoraResponse;
import com.alltecnologia.locadora.model.User;
import com.alltecnologia.locadora.service.AdminService;
 
@RestController
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	AdminService autenticacaoService;
 
	
	/**
	 * Permite adicionar um usuário ao sistema.(Somente o administrador pode adicionar um novo usuário)
	 * 
	 * @param user: novo usuário do sistema
	 * @return response 201 (CREATED) - Conseguiu criar um usuário
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/adicionar") 
	public LocadoraResponse addUserByAdmin(@RequestBody User user) {
	 
		return autenticacaoService.addUserByAdmin(user);
	} 
 
}
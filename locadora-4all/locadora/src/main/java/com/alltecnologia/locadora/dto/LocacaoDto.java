package com.alltecnologia.locadora.dto;

import com.alltecnologia.locadora.model.Filme;
import com.alltecnologia.locadora.model.User;

public class LocacaoDto {
	private Filme filme;
	private User user;
 

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	
	
}

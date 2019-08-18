package com.alltecnologia.locadora.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LocacaoDto {
	
	@NotBlank	
	@Size(max = 100)
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
 

	
}
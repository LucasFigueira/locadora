package com.alltecnologia.locadora.dto;

import javax.validation.constraints.NotBlank;

public class DevolucaoRequestDto {
	
	@NotBlank
	private String  titulo;
 
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
 
}
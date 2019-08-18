package com.alltecnologia.locadora.dto;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Range;

public class DevolucaoRequestDto {
	
	@Digits(fraction = 0, integer = 6)
	@Range(min=0,max=100000)
	private Integer  idLocacao;

	public Integer getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(Integer idLocacao) {
		this.idLocacao = idLocacao;
	}
	
	
 
}
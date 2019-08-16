package com.alltecnologia.locadora.dto;

import java.util.Date;

public class AluguelResponseDto extends LocadoraResponse{
	private Date prazoDevolucao;

	public Date getPrazoDevolucao() {
		return prazoDevolucao;
	}

	public void setPrazoDevolucao(Date prazoDevolucao) {
		this.prazoDevolucao = prazoDevolucao;
	}
	
	
	
}

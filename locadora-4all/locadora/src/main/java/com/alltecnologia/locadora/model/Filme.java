package com.alltecnologia.locadora.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
 
 
@Entity
@Table(name ="filme")
public class Filme implements Serializable {
  
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
	private Integer idFilme;
	
	@NotBlank	
	@Size(max = 100)
	private String  titulo;
	@NotBlank	
	@Size(max = 50)
	private String  diretor;
	@Digits(fraction = 0, integer = 5)
	@Range(min=0,max=1000)
	private Integer quantidade;
	@Digits(fraction = 0, integer = 5)
	@Range(min=0,max=1000)
	private Integer disponivel; 
 
	public Integer getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Integer disponivel) {
		this.disponivel = disponivel;
	}
	public Integer getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
}

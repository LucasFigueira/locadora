package com.alltecnologia.locadora.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "LOCACAO_SEQ", sequenceName = "LOCACAO_SEQ", initialValue = 1, allocationSize = 1)
public class Locacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "LOCACAO_SEQ")
	private Integer codigoLocacao;
	
	@OneToOne(cascade= CascadeType.REFRESH) 
	@JoinColumns(foreignKey = @ForeignKey(name = "FK_USER"), value = {
			@JoinColumn(referencedColumnName = "user_id", name = "user_id"),
			@JoinColumn(referencedColumnName = "name", name = "name") })
	private User user;
	
	@OneToOne
	@JoinColumn(name="id_filme")
	private  Filme filme; 
	
	@Column(name="titulo")
	private  String titulo; 
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_emprestimo")
	private Date dataEmprestimo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_devolucao")
	private Date dataDevolucao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_devolucao")
	private Date prazo_devolucao;
	

	public Integer getCodigoLocacao() {
		return codigoLocacao;
	}

	public void setCodigoLocacao(Integer codigoLocacao) {
		this.codigoLocacao = codigoLocacao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public Date getPrazo_devolucao() {
		return prazo_devolucao;
	}

	public void setPrazo_devolucao(Date prazo_devolucao) {
		this.prazo_devolucao = prazo_devolucao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
}

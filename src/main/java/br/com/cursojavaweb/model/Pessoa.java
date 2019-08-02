package br.com.cursojavaweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.cursojavaweb.enuns.EnumSituacao;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id") 
	private Long id;
	
	@Column(name="nome", length = 100)
	private String nome;
	
	@Column(name="cpfCnpj", length = 18)
	private String cpfCnpj;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", length = 10)
	private EnumSituacao situacao;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public EnumSituacao getSituacao() {
		return situacao;
	}
	public void setSituacao(EnumSituacao situacao) {
		this.situacao = situacao;
	}
}

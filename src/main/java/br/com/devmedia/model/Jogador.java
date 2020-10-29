package br.com.devmedia.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jogador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	String nome;
	Role role;
	
	public Jogador() {
		if (this.role == null) this.role = Role.NENHUM;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	} 
	
	
}

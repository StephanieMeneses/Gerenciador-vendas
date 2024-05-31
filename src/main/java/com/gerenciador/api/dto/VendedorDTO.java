package com.gerenciador.api.dto;

import com.gerenciador.api.model.Vendedor;

public class VendedorDTO {
	
	private Long id;	
	private String nome;
	private String email; 
	
	public VendedorDTO () {
		
	}
	
	public VendedorDTO(Long id, String nome, String cliente, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public VendedorDTO (Vendedor entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();		
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

package com.gerenciador.api.dto;

import com.gerenciador.api.model.Cliente;

public class ClienteDTO {
	
	private Long id;	
	private String nome;
	private String cpf;
	private String endereco;	
	private String email;
	private String telefone;
	
	public ClienteDTO () {
			
	}
	
	public ClienteDTO(Long id, String nome, String cpf, 
			String endereco, String email, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
	}

	public ClienteDTO (Cliente entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf = entity.getCpf();
		this.endereco = entity.getEndereco();
		this.email = entity.getEmail();
		this.telefone = entity.getTelefone();		
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

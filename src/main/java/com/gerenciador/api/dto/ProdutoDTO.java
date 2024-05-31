package com.gerenciador.api.dto;

import com.gerenciador.api.model.Produto;

public class ProdutoDTO {

	private Long id;
	private String nome;
	private String marca;
	private double valor;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.marca = entity.getMarca();
		this.valor = entity.getValor();

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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}

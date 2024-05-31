package com.gerenciador.api.model;

import java.io.Serializable;

import com.gerenciador.api.dto.ProdutoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "pro_produto")
	public class Produto implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(nullable = false)
		private String nome;
		
		@Column(nullable = false)
		private double valor;
		
		@Column(nullable = false)
		private String marca;
		
		
		public Produto(ProdutoDTO dto) {
			this.id = dto.getId();
			this.nome = dto.getNome();
			this.marca = dto.getMarca();
			this.valor = dto.getValor();
	
		}

		public Produto(Long id, String nome, double valor, String marca) {
			super();
			this.id = id;
			this.nome = nome;
			this.valor = valor;
			this.marca = marca;
		}
		
		public Produto() {
			
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

		public double getValor() {
			return valor;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}
		
		

}

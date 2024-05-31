package com.gerenciador.api.model;

import java.io.Serializable;
import java.time.Instant;

import com.gerenciador.api.dto.PedidoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "ped_pedido")
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private double valor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Instant data;
	
	@Column(nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Pedido() {
	}
	
	public Pedido(PedidoDTO dto) {
		this.id = dto.getId();
		this.valor = dto.getValor();
		this.data = dto.getData();
		this.status = dto.getStatus();
	}

	public Pedido(double valor, Instant data, String status, Vendedor vendedor) {
		this.valor = valor;
		this.data = data;
		this.status = status;
		this.vendedor = vendedor;
	}
	
	@PrePersist
	protected void onCreate() {
		this.data = Instant.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
}

package com.gerenciador.api.dto;

import java.time.Instant;

import com.gerenciador.api.model.Pedido;

public class PedidoDTO {
	
	private Long id;	
	private double valor;
	private Instant data ;
	private String status;
	private VendedorDTO vendedor;
	
	public PedidoDTO() {
	}

	public PedidoDTO (Pedido entity) {
		this.id = entity.getId();
		this.valor = entity.getValor();
		this.data = entity.getData();
		this.status = entity.getStatus();
		this.vendedor = new VendedorDTO(entity.getVendedor());
			
	}
	
	public PedidoDTO(double valor, Instant data, String status) {
		this.valor = valor;
		this.data = data;
		this.status = status;

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

	public VendedorDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
	}
	
	
	
	

}

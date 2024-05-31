package com.gerenciador.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciador.api.dto.PedidoDTO;
import com.gerenciador.api.model.Pedido;
import com.gerenciador.api.repository.PedidoRepository;
import com.gerenciador.api.repository.VendedorRepository;
import com.gerenciador.api.services.exceptions.DataBaseException;
import com.gerenciador.api.services.exceptions.EntidadeNaoEncontradaException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> buscarPedidos() {
		
		List<Pedido> pedidos = pedidoRepository.findAll();
				
		return pedidos.stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());	
		
	}
	
	@Transactional(readOnly = true)
	public PedidoDTO buscarPedidoId(Long id) {
		
		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Pedido com id " + id + " não encontrado"));
		return new PedidoDTO(pedido);
	}
	
	@Transactional	
	public PedidoDTO criarPedido(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		copiarDtoParaEntidade(dto, pedido);
		pedidoRepository.save(pedido);
		return new PedidoDTO(pedido);
	}
	
	public void deletarPedido(Long id) {
		if (!pedidoRepository.existsById(id))
			throw new EntidadeNaoEncontradaException("Não é possível deletar, id informado não existe.");
		try {
			pedidoRepository.deleteById(id);

		}

		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade referencial violada");
		}
		
		
	}	
	
	@Transactional
	public PedidoDTO editarPedido(Long id, PedidoDTO dto) {
		try {
			
			Pedido pedido = pedidoRepository.getReferenceById(id);
			copiarDtoParaEntidade(dto, pedido);
			pedido = pedidoRepository.save(pedido);
			
			return new PedidoDTO(pedido);
			
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException("Não é possivel atualizar, id informado não existe");
		}
	}

	private void copiarDtoParaEntidade(PedidoDTO dto, Pedido pedido) {
		
		pedido.setValor(dto.getValor());
		pedido.setData(dto.getData());
		pedido.setStatus(dto.getStatus());
		pedido.setVendedor(vendedorRepository.getOne(dto.getVendedor().getId())); 
	
	}
}

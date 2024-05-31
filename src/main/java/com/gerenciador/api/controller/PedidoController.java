package com.gerenciador.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gerenciador.api.dto.PedidoDTO;
import com.gerenciador.api.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> buscarPedidos() {
		
		List<PedidoDTO> dto = pedidoService.buscarPedidos();
		
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> buscarPedidoId(@PathVariable Long id) {
		
		PedidoDTO dto = pedidoService.buscarPedidoId(id);
		
		return ResponseEntity.ok().body(dto);

	}
	@PostMapping
	public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO dto) {
		
		dto = pedidoService.criarPedido(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);

	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarPedido(@PathVariable Long Id) {
		
		pedidoService.deletarPedido(Id);
		
		return ResponseEntity.noContent().build();

	}
	
	@PutMapping(value ="/{id}s")
	public ResponseEntity<PedidoDTO> editarPedido(@RequestBody PedidoDTO dto, @PathVariable Long id) {
		
		dto = pedidoService.editarPedido(id, dto); 
		
		return ResponseEntity.ok().body(dto);	}

	}
	
	


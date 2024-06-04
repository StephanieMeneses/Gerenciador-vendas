package com.gerenciador.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gerenciador.api.dto.ClienteDTO;
import com.gerenciador.api.services.ClienteService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;


	@GetMapping
	public ResponseEntity<List<ClienteDTO>> buscarClientes() {

		List<ClienteDTO> dto = clienteService.buscarClientes();

		return ResponseEntity.ok().body(dto);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> buscarClienteId(@PathVariable Long id) {

		ClienteDTO dto = clienteService.buscarClienteId(id);

		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO dto) {

		dto = clienteService.criarCliente(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
		clienteService.deletarCliente(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> editarCliente(@RequestBody ClienteDTO dto, @PathVariable Long id) {

		dto = clienteService.editarCliente(id, dto);
		
		return ResponseEntity.ok().body(dto);	}
}

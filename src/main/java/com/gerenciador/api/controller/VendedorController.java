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

import com.gerenciador.api.dto.VendedorDTO;
import com.gerenciador.api.services.VendedorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	
	@Autowired
	private VendedorService vendedorService;


	@GetMapping
	public ResponseEntity<List<VendedorDTO>> buscarVendedores() {

		List<VendedorDTO> dto = vendedorService.buscarVendedores();

		return ResponseEntity.ok().body(dto);

	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VendedorDTO> buscarVendedorId(@PathVariable Long id) {

		VendedorDTO dto = vendedorService.buscarVendedorId(id);

		return ResponseEntity.ok().body(dto);
	}


	@PostMapping
	public ResponseEntity<VendedorDTO> criarVendedor(@RequestBody VendedorDTO dto) {

		dto = vendedorService.criarVendedor(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarVendedor(@PathVariable Long id) {
		vendedorService.deletarVendedor(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VendedorDTO> editarVendedor(@RequestBody VendedorDTO dto, @PathVariable Long id) {

		dto = vendedorService.editarVendedor(id, dto);
		
		return ResponseEntity.ok().body(dto);	}


}

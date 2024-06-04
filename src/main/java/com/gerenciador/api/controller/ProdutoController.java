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

import com.gerenciador.api.dto.ProdutoDTO;
import com.gerenciador.api.services.ProdutoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;


	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> buscarProdutos() {

		List<ProdutoDTO> dto = produtoService.buscarProdutos();

		return ResponseEntity.ok().body(dto);

	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> buscarProdutoId(@PathVariable Long id) {

		ProdutoDTO dto = produtoService.buscarProdutoId(id);

		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO dto) {

		dto = produtoService.criarProduto(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
		produtoService.deletarProduto(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> editarProduto(@RequestBody ProdutoDTO dto, @PathVariable Long id) {

		dto = produtoService.editarProduto(id, dto);
		
		return ResponseEntity.ok().body(dto);	}
}

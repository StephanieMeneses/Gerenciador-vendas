package com.gerenciador.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciador.api.dto.ProdutoDTO;
import com.gerenciador.api.model.Produto;
import com.gerenciador.api.repository.ProdutoRepository;
import com.gerenciador.api.services.exceptions.DataBaseException;
import com.gerenciador.api.services.exceptions.EntidadeNaoEncontradaException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional(readOnly = true)
	public List<ProdutoDTO> buscarProdutos() {
		
		List<Produto> produtos = produtoRepository.findAll();
		return produtos.stream().map(produto -> new ProdutoDTO(produto)).collect(Collectors.toList());	
	}
	
	@Transactional(readOnly = true)
	public ProdutoDTO buscarProdutoId(Long id) {
		
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com id " + id + " não encontrado"));
		return new ProdutoDTO(produto);
	}

	@Transactional	
	public ProdutoDTO criarProduto(ProdutoDTO dto) {
		Produto produto = new Produto(dto);
		produtoRepository.save(produto);
		return new ProdutoDTO(produto);
	}
	

	public void deletarProduto(Long id) {
		if (!produtoRepository.existsById(id))
			throw new EntidadeNaoEncontradaException("Não é possível deletar, id informado não existe.");
		try {
			produtoRepository.deleteById(id);

		}

		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade referencial violada");
		}
		
	}
	@Transactional
	public ProdutoDTO editarProduto(Long id, ProdutoDTO dto) {
		try {
			
			Produto produto = produtoRepository.getReferenceById(id);
			copiarDtoParaEntidade(dto, produto);
			produto = produtoRepository.save(produto);
			
			return new ProdutoDTO(produto);
			
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException("Não é possivel atualizar, id informado não existe");
		}
	}

	private void copiarDtoParaEntidade(ProdutoDTO dto, Produto produto) {
		
		produto.setNome(dto.getNome());
		produto.setMarca(dto.getMarca());
		produto.setValor(dto.getValor());
		
	}

}

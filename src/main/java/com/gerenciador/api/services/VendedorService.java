package com.gerenciador.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciador.api.dto.VendedorDTO;
import com.gerenciador.api.model.Vendedor;
import com.gerenciador.api.repository.VendedorRepository;
import com.gerenciador.api.services.exceptions.DataBaseException;
import com.gerenciador.api.services.exceptions.EntidadeNaoEncontradaException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;

	@Transactional(readOnly = true)
	public VendedorDTO buscarVendedorId(Long id) {

		Vendedor vendedor = vendedorRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Vendedor com id " + id + " não encontrado"));

		return new VendedorDTO(vendedor);

	}
	
	@Transactional(readOnly = true)
	public List<VendedorDTO> buscarVendedores() {

		List<Vendedor> vendedores = vendedorRepository.findAll();

		return vendedores.stream().map(vendedor -> new VendedorDTO(vendedor)).collect(Collectors.toList());

	}
	
	@Transactional
	public VendedorDTO criarVendedor(VendedorDTO dto) {

		Vendedor vendedor = new Vendedor(dto);
		vendedorRepository.save(vendedor);
		return new VendedorDTO(vendedor);
	}
	
	public void deletarVendedor(Long id) {
		if (!vendedorRepository.existsById(id))
			throw new EntidadeNaoEncontradaException("Não é possível deletar, id informado não existe.");
		try {
			vendedorRepository.deleteById(id);

		}

		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade referencial violada");
		}

	}

	@Transactional
	public VendedorDTO editarVendedor(Long id, VendedorDTO dto) {
		
		try {
			
			Vendedor vendedor = vendedorRepository.getReferenceById(id);
			copiarDtoParaEntidade(dto, vendedor);
			vendedor = vendedorRepository.save(vendedor);
			
			return new VendedorDTO(vendedor);
			
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException("Não é possivel atualizar, id informado não existe");
		}
	}

	private void copiarDtoParaEntidade(VendedorDTO dto, Vendedor vendedor) {
		
		vendedor.setNome(dto.getNome());
		vendedor.setCliente(dto.getEmail());
	}
	
	
}

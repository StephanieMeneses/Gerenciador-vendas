package com.gerenciador.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gerenciador.api.dto.ClienteDTO;
import com.gerenciador.api.model.Cliente;
import com.gerenciador.api.repository.ClienteRepository;
import com.gerenciador.api.services.exceptions.DataBaseException;
import com.gerenciador.api.services.exceptions.EntidadeNaoEncontradaException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public ClienteDTO buscarClienteId(Long id) {

		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com id " + id + " não encontrado"));

		return new ClienteDTO(cliente);

	}

	@Transactional(readOnly = true)
	public List<ClienteDTO> buscarClientes() {

		List<Cliente> clientes = clienteRepository.findAll();

		return clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());

	}

	@Transactional
	public ClienteDTO criarCliente(ClienteDTO dto) {

		Cliente cliente = new Cliente(dto);
		clienteRepository.save(cliente);
		return new ClienteDTO(cliente);
	}

	public void deletarCliente(Long id) {
		if (!clienteRepository.existsById(id))
			throw new EntidadeNaoEncontradaException("Não é possível deletar, id informado não existe.");
		try {
			clienteRepository.deleteById(id);

		}

		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade referencial violada");
		}

	}
	
	@Transactional
	public ClienteDTO editarCliente(Long id, ClienteDTO dto) {
		
		try {
			
			Cliente cliente = clienteRepository.getReferenceById(id);
			copiarDtoParaEntidade(dto, cliente);
			cliente = clienteRepository.save(cliente);
			
			return new ClienteDTO(cliente);
			
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException("Não é possivel atualizar, id informado não existe");
		}
	}

	
		private void copiarDtoParaEntidade(ClienteDTO dto, Cliente cliente) {
			
			cliente.setNome(dto.getNome());
			cliente.setCpf(dto.getCpf());
			cliente.setEmail(dto.getEmail());
			cliente.setEndereco(dto.getEndereco());
			cliente.setTelefone(dto.getTelefone());
			
		}
}

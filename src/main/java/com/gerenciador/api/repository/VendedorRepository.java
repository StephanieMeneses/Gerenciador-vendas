package com.gerenciador.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador.api.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

	List<Vendedor> findAll();
	

}

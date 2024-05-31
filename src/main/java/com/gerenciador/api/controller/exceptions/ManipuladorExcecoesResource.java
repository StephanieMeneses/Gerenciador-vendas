package com.gerenciador.api.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gerenciador.api.services.exceptions.DataBaseException;
import com.gerenciador.api.services.exceptions.EntidadeNaoEncontradaException;
import com.gerenciador.api.services.exceptions.ViolacaoRegraDeNegocioException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ManipuladorExcecoesResource {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<ErroPadrao> entidadeNaoEncontrada(EntidadeNaoEncontradaException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		
		ErroPadrao err = new ErroPadrao();
		
		
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<ErroPadrao> database(EntidadeNaoEncontradaException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao err = new ErroPadrao();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(ViolacaoRegraDeNegocioException.class)
	public ResponseEntity<ErroPadrao> database(ViolacaoRegraDeNegocioException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.CONFLICT;
		ErroPadrao err = new ErroPadrao();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Violação regra de negócio");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
		
	}

}

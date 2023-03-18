package com.empresa.colaboradores.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.colaboradores.dto.ColaboradorRequest;
import com.empresa.colaboradores.entity.Colaborador;
import com.empresa.colaboradores.service.ColaboradorService;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
    private MessageSource messageSource;
	
	@PostMapping
	public ResponseEntity<Colaborador> cadastrar(@RequestBody @Valid ColaboradorRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.cadastrar(request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletar(@PathVariable Integer id) {
		colaboradorService.deletar(id);
		return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("removido.com.sucesso", null, null));
	}
	
	@GetMapping
	public ResponseEntity<List<Colaborador>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.consultar());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Colaborador> atualizar(@RequestBody @Valid ColaboradorRequest request, @PathVariable Integer id) {
		 return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.atualizar(request, id));
	}
	
}

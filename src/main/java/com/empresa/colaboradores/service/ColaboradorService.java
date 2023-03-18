package com.empresa.colaboradores.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.colaboradores.dto.ColaboradorRequest;
import com.empresa.colaboradores.entity.Colaborador;
import com.empresa.colaboradores.repository.ColaboradorRepository;
import com.empresa.colaboradores.util.CriptografiaUtil;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public Colaborador cadastrar(ColaboradorRequest request) {
		Colaborador lider = colaboradorRepository.findById(request.getIdLider()).orElseThrow(() -> new EntityNotFoundException("Não foi encontrado lider com ID" + request.getIdLider()));
		Colaborador colaborador = Colaborador.builder()
				.nome(request.getNome())
				.senha(CriptografiaUtil.criptografar(request.getSenha()))
				.percentual(1)
				.build();
		lider.getSubordinados().add(colaborador);
		colaboradorRepository.save(lider);
		return colaborador;
	}

	public void deletar(Integer codigo) {
		colaboradorRepository.deleteById(codigo);
	}
	
	public List<Colaborador> consultar() {
		return colaboradorRepository.findAll();
	}
	
	public Colaborador atualizar(ColaboradorRequest request, Integer id) {
		Colaborador colaborador = colaboradorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi encontrado colaborador com ID" + id));
		colaborador.setNome(request.getNome());
		colaborador.setSenha(CriptografiaUtil.criptografar(request.getSenha()));
		return colaboradorRepository.save(colaborador);					
	}
	
}

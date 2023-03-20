package com.empresa.colaboradores.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.colaboradores.dto.ColaboradorRequest;
import com.empresa.colaboradores.entity.Colaborador;
import com.empresa.colaboradores.repository.ColaboradorRepository;
import com.empresa.colaboradores.util.CriptografiaUtil;
import com.empresa.colaboradores.util.GeradorScore;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public Colaborador cadastrar(ColaboradorRequest request) {
		Colaborador colaborador = Colaborador.builder()
				.nome(request.getNome())
				.senha(CriptografiaUtil.criptografar(request.getSenha()))
				.score(GeradorScore.gerar(request.getSenha()))
				.build();
		Colaborador cadastrado = colaboradorRepository.save(colaborador);
		this.vincularComLider(request.getIdLider(), cadastrado);
		return cadastrado;
	}
	
	private void vincularComLider(Integer idLider, Colaborador colaborador) {
		if (idLider != null) {
        	Optional<Colaborador> lider = colaboradorRepository.findById(idLider);
        	lider.ifPresent(s -> {
        		s.getSubordinados().add(colaborador);
        		colaboradorRepository.save(s);
        	});
        }
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
		colaborador.setScore(GeradorScore.gerar(request.getSenha()));
		colaborador.setSenha(CriptografiaUtil.criptografar(request.getSenha()));
		return colaboradorRepository.save(colaborador);					
	}
	
}

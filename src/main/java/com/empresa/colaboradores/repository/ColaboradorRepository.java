package com.empresa.colaboradores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.colaboradores.entity.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer>{

	public List<Colaborador> findAllBySubordinadosIsNotNull();
	
}

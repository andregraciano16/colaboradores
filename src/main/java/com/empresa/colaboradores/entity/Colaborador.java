package com.empresa.colaboradores.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {

	@Id
	@GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@JsonIgnore
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "percentual", length = 3, nullable = false)
	private Integer percentual;
	
	@Getter(AccessLevel.NONE)
	@OneToMany(cascade = CascadeType.ALL)
	private List<Colaborador> subordinados;
	
	public List<Colaborador> getSubordinados() {
		if (this.subordinados == null) {
			this.subordinados = new ArrayList<>();
		}
		return this.subordinados;
	}
	
}
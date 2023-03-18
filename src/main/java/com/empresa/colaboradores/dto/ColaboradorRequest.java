package com.empresa.colaboradores.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColaboradorRequest {

	@NotEmpty(message = "{mensagem.validacao.nome.obrigatorio}")
	@Size(max = 100, message = "{mensagem.validacao.nome.tamanho}")
	private String nome;
	
	@NotEmpty(message = "{mensagem.validacao.senha.obrigatorio}")
	private String senha;

	private Integer idLider;
}

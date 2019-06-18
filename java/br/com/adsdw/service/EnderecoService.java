package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Endereco;
import br.com.adsdw.repository.Enderecos;
import br.com.adsdw.util.jpa.Transactional;

public class EnderecoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Enderecos enderecos;
	
	@Transactional
	public void salvar(Endereco endereco) {
		enderecos.salvar(endereco);
	}
	
	@Transactional
	public void excluir(Endereco endereco) {
		enderecos.excluir(endereco);
	}
}

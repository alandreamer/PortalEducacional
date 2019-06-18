package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.PlanoAula;
import br.com.adsdw.repository.PlanoAulas;
import br.com.adsdw.util.jpa.Transactional;

public class PlanoAulaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlanoAulas planoAulas;
	
	@Transactional
	public void salvar(PlanoAula planoAula) {
		planoAulas.salvar(planoAula);
	}
	
	@Transactional
	public void excluir(PlanoAula planoAula) {
		planoAulas.excluir(planoAula);
	}
}

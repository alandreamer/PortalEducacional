package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Disciplina;
import br.com.adsdw.repository.Disciplinas;
import br.com.adsdw.util.jpa.Transactional;

public class DisciplinaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Disciplinas disciplinas;
	
	@Transactional
	public void salvar(Disciplina disciplina) {
		disciplinas.salvar(disciplina);
	}
	
	@Transactional
	public void excluir(Disciplina disciplina) {
		disciplinas.excluir(disciplina);
	}
}

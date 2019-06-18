package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Professor;
import br.com.adsdw.repository.Professores;
import br.com.adsdw.util.jpa.Transactional;

public class ProfessorService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Professores professores;
	
	@Transactional
	public void salvar(Professor professor) {
		professores.salvar(professor);
	}
	
	@Transactional
	public void excluir(Professor professor) {
		professores.excluir(professor);
	}
}

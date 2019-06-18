package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Turma;
import br.com.adsdw.repository.Turmas;
import br.com.adsdw.util.jpa.Transactional;

public class TurmaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Turmas turmas;
	
	@Transactional
	public void salvar(Turma turma) {
		turmas.salvar(turma);
	}
	
	@Transactional
	public void excluir(Turma turma) {
		turmas.excluir(turma);
	}
}

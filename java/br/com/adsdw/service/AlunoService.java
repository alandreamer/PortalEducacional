package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Aluno;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.util.jpa.Transactional;

public class AlunoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Alunos alunos;
	
	@Transactional
	public void salvar(Aluno aluno) {
		alunos.salvar(aluno);
	}
	
	@Transactional
	public void excluir(Aluno aluno) {
		alunos.excluir(aluno);
	}
}

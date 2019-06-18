package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Curso;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.util.jpa.Transactional;

public class CursoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cursos cursos;
	
	@Transactional
	public void salvar(Curso curso) {
		cursos.salvar(curso);
	}
	
	@Transactional
	public void excluir(Curso curso) {
		cursos.excluir(curso);
	}
}

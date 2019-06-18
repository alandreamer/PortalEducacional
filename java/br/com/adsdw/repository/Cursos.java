package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Curso;

public class Cursos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Curso porId(Long id) {
		return manager.find(Curso.class, id);
	}
	
	public List<Curso> todos() {
		return manager.createQuery("from Curso", Curso.class).getResultList();
	}
	
	public Curso salvar(Curso curso) {
		return manager.merge(curso);
	}
	
	public void excluir(Curso curso) {
		curso = porId(curso.getId());
		manager.remove(curso);
	}
}
package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Professor;


public class Professores implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Professor porId(Long id) {
		return manager.find(Professor.class, id);
	}
	
	public List<Professor> todos() {
		return manager.createQuery("from Professor", Professor.class).getResultList();
	}
	
	public Professor salvar(Professor professor) {
		return manager.merge(professor);
	}
	
	public void excluir(Professor professor) {
		professor = porId(professor.getId());
		manager.remove(professor);
	}
}
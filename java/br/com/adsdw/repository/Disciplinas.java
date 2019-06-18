package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Disciplina;



public class Disciplinas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Disciplina porId(Long id) {
		return manager.find(Disciplina.class, id);
	}
	
	public List<Disciplina> todos() {
		return manager.createQuery("from Disciplina", Disciplina.class).getResultList();
	}
	
	public Disciplina salvar(Disciplina disciplina) {
		return manager.merge(disciplina);
	}
	
	public void excluir(Disciplina disciplina) {
		disciplina = porId(disciplina.getId());
		manager.remove(disciplina);
	}
}
package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Turma;


public class Turmas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Turma porId(Long id) {
		return manager.find(Turma.class, id);
	}
	
	public List<Turma> todos() {
		return manager.createQuery("from Turma", Turma.class).getResultList();
	}
	
	public Turma salvar(Turma turma) {
		return manager.merge(turma);
	}
	
	public void excluir(Turma turma) {
		turma = porId(turma.getId());
		manager.remove(turma);
	}
}
package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Aluno;

public class Alunos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Aluno porId(Long id) {
		return manager.find(Aluno.class, id);
	}
	
	public List<Aluno> todos() {
		return manager.createQuery("from Aluno", Aluno.class).getResultList();
	}
	
	public Aluno salvar(Aluno aluno) {
		return manager.merge(aluno);
	}
	
	public void excluir(Aluno aluno) {
		aluno = porId(aluno.getId());
		manager.remove(aluno);
	}
}
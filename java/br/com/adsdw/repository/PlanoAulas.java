package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.PlanoAula;

public class PlanoAulas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public PlanoAula porId(Long id) {
		return manager.find(PlanoAula.class, id);
	}
	
	public List<PlanoAula> todos() {
		return manager.createQuery("from PlanoAula", PlanoAula.class).getResultList();
	}
	
	public PlanoAula salvar(PlanoAula planoAula) {
		return manager.merge(planoAula);
	}
	
	public void excluir(PlanoAula planoAula) {
		planoAula = porId(planoAula.getId());
		manager.remove(planoAula);
	}
}
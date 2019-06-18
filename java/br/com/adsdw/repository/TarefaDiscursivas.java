package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.ItemTarefaDiscursiva;
import br.com.adsdw.model.TarefaDiscursiva;

public class TarefaDiscursivas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public TarefaDiscursiva porId(Long id) {
		return manager.find(TarefaDiscursiva.class, id);
	}
	
	public List<TarefaDiscursiva> todos() {
		return manager.createQuery("from TarefaDiscursiva", TarefaDiscursiva.class).getResultList();
	}
	
	public List<ItemTarefaDiscursiva> todosItem() {
		return manager.createQuery("from ItemTarefaDiscursiva", ItemTarefaDiscursiva.class).getResultList();
	}
	
	
	public TarefaDiscursiva salvar(TarefaDiscursiva tarefaDiscursiva) {
		return manager.merge(tarefaDiscursiva);
	}
	
	public void excluir(TarefaDiscursiva tarefaDiscursiva) {
		tarefaDiscursiva = porId(tarefaDiscursiva.getId());
		manager.remove(tarefaDiscursiva);
	}
}
package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.ItemTarefaObjetiva;
import br.com.adsdw.model.Tarefa;

public class Tarefas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Tarefa porId(Long id) {
		return manager.find(Tarefa.class, id);
	}
	
	public List<Tarefa> todos() {
		return manager.createQuery("from Tarefa", Tarefa.class).getResultList();
	}
	
	public List<ItemTarefaObjetiva> todosItem() {
		return manager.createQuery("from ItemTarefaObjetiva", ItemTarefaObjetiva.class).getResultList();
	}
	
	
	public Tarefa salvar(Tarefa tarefa) {
		return manager.merge(tarefa);
	}
	
	public void excluir(Tarefa tarefa) {
		tarefa = porId(tarefa.getId());
		manager.remove(tarefa);
	}
}
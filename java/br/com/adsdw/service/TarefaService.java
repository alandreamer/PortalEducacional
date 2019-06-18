package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Tarefa;
import br.com.adsdw.repository.Tarefas;
import br.com.adsdw.util.jpa.Transactional;

public class TarefaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tarefas tarefas;
	
	@Transactional
	public void salvar(Tarefa tarefa) {
		tarefas.salvar(tarefa);
	}
	
	@Transactional
	public void excluir(Tarefa tarefa) {
		tarefas.excluir(tarefa);
	}
}

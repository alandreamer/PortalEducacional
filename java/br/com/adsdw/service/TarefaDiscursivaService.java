package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.TarefaDiscursiva;
import br.com.adsdw.repository.TarefaDiscursivas;
import br.com.adsdw.util.jpa.Transactional;

public class TarefaDiscursivaService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TarefaDiscursivas tarefaDiscursivas;
	
	@Transactional
	public void salvar(TarefaDiscursiva tarefaDiscursiva) {
		tarefaDiscursivas.salvar(tarefaDiscursiva);
	}
	
	@Transactional
	public void excluir(TarefaDiscursiva tarefaDiscursiva) {
		tarefaDiscursivas.excluir(tarefaDiscursiva);
	}
}

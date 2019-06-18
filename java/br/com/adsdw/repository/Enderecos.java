package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Endereco;

public class Enderecos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}
	
	public List<Endereco> todos() {
		return manager.createQuery("from Endereco", Endereco.class).getResultList();
	}
	
	public Endereco salvar(Endereco endereco) {
		return manager.merge(endereco);
	}
	
	public void excluir(Endereco endereco) {
		endereco = porId(endereco.getId());
		manager.remove(endereco);
	}
}
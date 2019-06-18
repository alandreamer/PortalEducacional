package br.com.adsdw.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.adsdw.model.Cache;



public class Caches implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cache porId(Long id) {
		return manager.find(Cache.class, id);
	}
	
	public Cache todo() {
		Cache cache = null;
		try {
			cache = manager.createQuery("from Cache", Cache.class).getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		}
		return cache;
	}
	
	public Cache salvar(Cache cache) {
		return manager.merge(cache);
	}
	
	public void excluir(Cache cache) {
		cache = porId(cache.getId());
		manager.remove(cache);
	}
}
package br.com.adsdw.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.adsdw.model.Cache;
import br.com.adsdw.repository.Caches;
import br.com.adsdw.util.jpa.Transactional;

public class CacheService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Caches caches;
	
	@Transactional
	public void salvar(Cache cache) {
		caches.salvar(cache);
	}
	
	@Transactional
	public void excluir(Cache cache) {
		caches.excluir(cache);
	}
}

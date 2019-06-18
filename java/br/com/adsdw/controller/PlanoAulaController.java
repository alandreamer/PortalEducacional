package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adsdw.model.Aluno;
import br.com.adsdw.model.Cache;
import br.com.adsdw.model.Curso;
import br.com.adsdw.model.Endereco;
import br.com.adsdw.model.PlanoAula;
import br.com.adsdw.model.Turma;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.repository.Caches;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.repository.PlanoAulas;
import br.com.adsdw.repository.Turmas;
import br.com.adsdw.service.AlunoService;
import br.com.adsdw.service.CacheService;
import br.com.adsdw.service.CepWebService;
import br.com.adsdw.service.EnderecoService;
import br.com.adsdw.service.PlanoAulaService;
import br.com.adsdw.service.TurmaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PlanoAulaController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PlanoAulaService planoAulaService;
	
	@Inject
	private PlanoAulas planoAulas;
	
	@Inject
	private Caches caches;
	
	@Inject
	private Turmas turmas;
	
	private PlanoAula planoAula;
	private PlanoAula planoAulaSelecionado;
	private Turma turma = new Turma();
	private Cache cache = new Cache();
	private List<PlanoAula> listaPlano = new ArrayList<>();
	
	public PlanoAulaController() {
		planoAula = new PlanoAula();
	} 
	
	public void salvar() {
		cache.setIdExterno(caches.todo().getIdExterno());
        turma = turmas.porId(cache.getIdExterno());
        planoAula.setTurma(turma);
		planoAulaService.salvar(planoAula);
		planoAula = new PlanoAula();
		listarTodos();
		FacesUtil.addInfoMessage("Plano de Aula salvo com sucesso!");
	}
	
	public void editar() {
		planoAulaService.salvar(planoAula);
		planoAula = new PlanoAula();
		listarTodos();
		FacesUtil.addInfoMessage("Plano de aula editado com sucesso!");
	}
	
	@PostConstruct
    private void listarTodos() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaPlano = planoAulas.todos();
        cache.setIdExterno(caches.todo().getIdExterno());
        turma = turmas.porId(cache.getIdExterno());
        for(int w = 0; w < listaPlano.size(); w++) {
        	if(listaPlano.get(w).getTurma().getId() != turma.getId()) {
        		listaPlano.remove(w);
        	}
        }
    }
	
	public void deletar() {
		planoAulaService.excluir(planoAulaSelecionado);
		planoAulaSelecionado = null;
		listarTodos();
		FacesUtil.addInfoMessage("Plano de aula excluído com sucesso!");
	}

	//JSF requer get e set no MB para manipular os componentes da tela
	public List<PlanoAula> getListaPlano() {
		return listaPlano;
	}

	public void setListaPlano(List<PlanoAula> listaPlano) {
		this.listaPlano = listaPlano;
	}

	public PlanoAula getPlanoAula() {
		return planoAula;
	}

	public void setPlanoAula(PlanoAula planoAula) {
		this.planoAula = planoAula;
	}

	public PlanoAula getPlanoAulaSelecionado() {
		return planoAulaSelecionado;
	}

	public void setPlanoAulaSelecionado(PlanoAula planoAulaSelecionado) {
		this.planoAulaSelecionado = planoAulaSelecionado;
	}
	
}
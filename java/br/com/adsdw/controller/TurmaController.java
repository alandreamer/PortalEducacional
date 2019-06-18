package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
import br.com.adsdw.model.Disciplina;
import br.com.adsdw.model.Endereco;
import br.com.adsdw.model.Turma;
import br.com.adsdw.repository.Caches;
import br.com.adsdw.repository.Disciplinas;
import br.com.adsdw.repository.TarefaDiscursivas;
import br.com.adsdw.repository.Tarefas;
import br.com.adsdw.repository.Turmas;
import br.com.adsdw.service.CacheService;
import br.com.adsdw.service.TarefaDiscursivaService;
import br.com.adsdw.service.TarefaService;
import br.com.adsdw.service.TurmaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TurmaController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private TurmaService turmaService;
	
	@Inject
	private TarefaDiscursivaService tarefaDiscursivaService;
	
	@Inject
	private TarefaService tarefaService;
	
	@Inject
	private CacheService cacheService;
	
	@Inject
	private Caches caches;
	
	@Inject
	private Turmas turmas;
	
	@Inject
	private Tarefas tarefas;
	
	@Inject
	private Disciplinas disciplinas;
	
	@Inject
	private TarefaDiscursivas tarefaDiscursivas;
	
	private List <Turma> listaTurmas = new ArrayList<Turma>();
	private Turma turmaSelecionada;
	private Turma turmaEdicao = new Turma();
	private Turma turma = new Turma();
	private List <SelectItem> disciplinaSelect;
	private Disciplina disciplina = new Disciplina();
	private List <Disciplina> disciplinaLista;
	private Cache cache = new Cache();
	
	public void salvar() {
		turma.setDisciplina(disciplina);
		turmaService.salvar(turma);
		disciplina = new Disciplina();
		turma = new Turma();
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de turma salvo com sucesso!");
	}
	
	public void salvarCache(ActionEvent event) {
		try{
			cache.setId(caches.todo().getId());
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(cache.getId() != null) {
				cacheService.excluir(cache);
			}
			cache.setIdExterno(turmaSelecionada.getId());
			cacheService.salvar(cache);
			cache = new Cache();
		}
	}
	
	public void editar() {
		turmaService.salvar(turma);
		turma = new Turma();
		disciplina = new Disciplina();
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de turma editado com sucesso!");
	}
	
	@PostConstruct
    private void listarTodos() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaTurmas = turmas.todos();
    }
	
	public void deletar() {
		turmaService.excluir(turmaSelecionada);
		turmaSelecionada = null;
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de turma excluído com sucesso!");
	}

	public List<Turma> getListaTurmas() {
		return listaTurmas;
	}

	public void setListaTurmas(List<Turma> listaTurmas) {
		this.listaTurmas = listaTurmas;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}

	public Turma getTurmaEdicao() {
		return turmaEdicao;
	}

	public void setTurmaEdicao(Turma turmaEdicao) {
		this.turmaEdicao = turmaEdicao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinaLista() {
		return disciplinaLista;
	}

	public void setDisciplinaLista(List<Disciplina> disciplinaLista) {
		this.disciplinaLista = disciplinaLista;
	}

public List<SelectItem> getDisciplinaSelect() {
		
		disciplinaSelect = new ArrayList<SelectItem>();
		disciplinaLista = disciplinas.todos();
		
		for (Disciplina disciplina : disciplinaLista) {
			disciplinaSelect.add(new SelectItem(disciplina, disciplina.getNomeDisciplina()));
		}	
		return disciplinaSelect;
	}
	
	
}
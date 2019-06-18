package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adsdw.model.Aluno;
import br.com.adsdw.model.Cache;
import br.com.adsdw.model.Curso;
import br.com.adsdw.model.Endereco;
import br.com.adsdw.model.ItemTarefaDiscursiva;
import br.com.adsdw.model.ItemTarefaObjetiva;
import br.com.adsdw.model.Tarefa;
import br.com.adsdw.model.Turma;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.repository.Caches;
import br.com.adsdw.repository.Tarefas;
import br.com.adsdw.repository.Turmas;
import br.com.adsdw.service.AlunoService;
import br.com.adsdw.service.CacheService;
import br.com.adsdw.service.EnderecoService;
import br.com.adsdw.service.TarefaService;
import br.com.adsdw.service.TurmaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TarefaObjetivaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	@Inject TarefaService tarefaService;
	
	@Inject
	private Alunos alunos;
	
	@Inject
	private Tarefas tarefas;
	
	@Inject
	private EnderecoService enderecoService;
	
	@Inject
	private CacheService cacheService;
	
	@Inject
	private Caches caches;
	
	@Inject
	private Turmas turmas;
	
	@Inject
	private TurmaService turmaService;
	
	private Aluno aluno;
	private Endereco endereco;
	private List <SelectItem> cursoSelect;
	private Curso curso;
	private List <Curso> cursoLista;
	private List <Tarefa> listaTarefas = new ArrayList<Tarefa>();
	private Tarefa tarefaSelecionada;
	private Aluno alunoEdicao = new Aluno();
	private Tarefa tarefa = new Tarefa();
	private ItemTarefaDiscursiva itemTarefa;
	private ItemTarefaObjetiva itemTarefaObjetiva;
	private List<ItemTarefaDiscursiva> itemTarefaTemp = new ArrayList<ItemTarefaDiscursiva>();
	private List<ItemTarefaObjetiva> itemTarefaObjetivaTemp = new ArrayList<ItemTarefaObjetiva>();
	private List<Tarefa> tarefaTemp = new ArrayList <Tarefa>();
	private short num = 1;
	private Turma turma = new Turma();
	private Cache cache = new Cache();
	
	public TarefaObjetivaController() {
		novoItem();
		itemTarefaObjetiva.setNumQuestao(num);
	} 
	
	public void novoItem() {
		itemTarefaObjetiva = new ItemTarefaObjetiva();
	}
	
	public void addQuestao() {
		itemTarefaObjetiva.setNumQuestao(num++);
		itemTarefaObjetivaTemp.add(itemTarefaObjetiva);
		itemTarefaObjetiva = new ItemTarefaObjetiva();
		itemTarefaObjetiva.setNumQuestao(num);
		FacesUtil.addInfoMessage("Questão adicionada!");	
	}
	
	public void incluirTarefa() {
		tarefa.getItemTarefaObjetiva().addAll(itemTarefaObjetivaTemp);
		for(int i = 0; i < itemTarefaObjetivaTemp.size(); i++) {
			itemTarefaObjetivaTemp.get(i).setTarefa(tarefa);
		}
		cache.setIdExterno(caches.todo().getIdExterno());
        turma = turmas.porId(cache.getIdExterno());
        tarefa.setTurma(turma);
		tarefaService.salvar(tarefa);	
		itemTarefaObjetivaTemp = new ArrayList<ItemTarefaObjetiva>();
		num = 1;
		tarefa = new Tarefa();
		turma = new Turma();
		cache = new Cache();
		itemTarefaObjetiva.setNumQuestao(num);
		FacesUtil.addInfoMessage("Tarefa salva com sucesso!");
	}
	
	
	public ItemTarefaObjetiva getItemTarefaObjetiva() {
		return itemTarefaObjetiva;
	}

	public void setItemTarefaObjetiva(ItemTarefaObjetiva itemTarefaObjetiva) {
		this.itemTarefaObjetiva = itemTarefaObjetiva;
	}

	public ItemTarefaDiscursiva getItemTarefa() {
		return itemTarefa;
	}

	public void setItemTarefa(ItemTarefaDiscursiva itemTarefa) {
		this.itemTarefa = itemTarefa;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
	
	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

	public Aluno getAlunoEdicao() {
		return alunoEdicao;
	}

	public void setAlunoEdicao(Aluno alunoEdicao) {
		this.alunoEdicao = alunoEdicao;
	}
	
	public List<Curso> getCursoLista() {
		return cursoLista;
	}

	public void setCursoLista(List<Curso> cursoLista) {
		this.cursoLista = cursoLista;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}

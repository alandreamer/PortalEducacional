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
import br.com.adsdw.model.TarefaDiscursiva;
import br.com.adsdw.model.Turma;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.repository.Caches;
import br.com.adsdw.repository.TarefaDiscursivas;
import br.com.adsdw.repository.Tarefas;
import br.com.adsdw.repository.Turmas;
import br.com.adsdw.service.AlunoService;
import br.com.adsdw.service.CacheService;
import br.com.adsdw.service.EnderecoService;
import br.com.adsdw.service.TarefaDiscursivaService;
import br.com.adsdw.service.TarefaService;
import br.com.adsdw.service.TurmaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TarefaDiscursivaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	@Inject TarefaDiscursivaService tarefaDiscursivaService;
	
	@Inject
	private Alunos alunos;
	
	@Inject
	private TarefaDiscursivas tarefaDiscursivas;
	
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
	private List <TarefaDiscursiva> listaTarefas = new ArrayList<TarefaDiscursiva>();
	private TarefaDiscursiva tarefaDiscursivaSelecionada;
	private Aluno alunoEdicao = new Aluno();
	private TarefaDiscursiva tarefaDiscursiva = new TarefaDiscursiva();
	private ItemTarefaDiscursiva itemTarefaDiscursiva;
	private List<ItemTarefaDiscursiva> itemTarefaTemp = new ArrayList<ItemTarefaDiscursiva>();
	private List<ItemTarefaDiscursiva> itemTarefaDiscursivaTemp = new ArrayList<ItemTarefaDiscursiva>();
	private List<TarefaDiscursiva> tarefaDiscursivaTemp = new ArrayList <TarefaDiscursiva>();
	private short num = 1;
	private Turma turma = new Turma();
	private Cache cache = new Cache();
	
	public TarefaDiscursivaController() {
		novoItem();
		itemTarefaDiscursiva.setNumQuestao(num);
	} 
	
	public void novoItem() {
		itemTarefaDiscursiva = new ItemTarefaDiscursiva();
	}
	
	
	public void addQuestao() {
		itemTarefaDiscursiva.setNumQuestao(num++);
		itemTarefaDiscursivaTemp.add(itemTarefaDiscursiva);
		itemTarefaDiscursiva = new ItemTarefaDiscursiva();
		itemTarefaDiscursiva.setNumQuestao(num);
		FacesUtil.addInfoMessage("Questão adicionada!");
	}
	
	public void incluirTarefa() {
		tarefaDiscursiva.getItemTarefaDiscursiva().addAll(itemTarefaDiscursivaTemp);
		for(int i = 0; i < itemTarefaDiscursivaTemp.size(); i++) {
			itemTarefaDiscursivaTemp.get(i).setTarefaDiscursiva(tarefaDiscursiva);
		}
		cache.setIdExterno(caches.todo().getIdExterno());
        turma = turmas.porId(cache.getIdExterno());
        tarefaDiscursiva.setTurma(turma);
		tarefaDiscursivaService.salvar(tarefaDiscursiva);	
		itemTarefaDiscursivaTemp = new ArrayList<ItemTarefaDiscursiva>();
		num = 1;
		tarefaDiscursiva = new TarefaDiscursiva();
		itemTarefaDiscursiva.setNumQuestao(num);
		FacesUtil.addInfoMessage("Tarefa salva com sucesso!");
	}

	public ItemTarefaDiscursiva getItemTarefaDiscursiva() {
		return itemTarefaDiscursiva;
	}

	public void setItemTarefaDiscursiva(ItemTarefaDiscursiva itemTarefaDiscursiva) {
		this.itemTarefaDiscursiva = itemTarefaDiscursiva;
	}

	public TarefaDiscursiva getTarefaDiscursiva() {
		return tarefaDiscursiva;
	}

	public void setTarefaDiscursiva(TarefaDiscursiva tarefaDiscursiva) {
		this.tarefaDiscursiva = tarefaDiscursiva;
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
	
	public List<TarefaDiscursiva> getListaTarefas() {
		return listaTarefas;
	}

	public void setListaTarefas(List<TarefaDiscursiva> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
	
	public TarefaDiscursiva getTarefaDiscursivaSelecionada() {
		return tarefaDiscursivaSelecionada;
	}

	public void setTarefaDiscursivaSelecionada(TarefaDiscursiva tarefaDiscursivaSelecionada) {
		this.tarefaDiscursivaSelecionada = tarefaDiscursivaSelecionada;
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

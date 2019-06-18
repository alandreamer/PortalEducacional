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
import br.com.adsdw.model.ItemTarefaDiscursiva;
import br.com.adsdw.model.Tarefa;
import br.com.adsdw.model.TarefaDiscursiva;
import br.com.adsdw.model.Turma;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.repository.Caches;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.repository.TarefaDiscursivas;
import br.com.adsdw.repository.Tarefas;
import br.com.adsdw.repository.Turmas;
import br.com.adsdw.service.AlunoService;
import br.com.adsdw.service.CacheService;
import br.com.adsdw.service.CepWebService;
import br.com.adsdw.service.EnderecoService;
import br.com.adsdw.service.TarefaDiscursivaService;
import br.com.adsdw.service.TarefaService;
import br.com.adsdw.service.TurmaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TarefaController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	@Inject 
	private TarefaService tarefaService;
	
	@Inject
	private TarefaDiscursivaService tarefaDiscursivaService;
	
	@Inject
	private Alunos alunos;
	
	@Inject
	private Tarefas tarefas;
	
	@Inject
	private CacheService cacheService;
	
	@Inject
	private Caches caches;
	
	@Inject
	private Turmas turmas;
	
	@Inject
	private TurmaService turmaService;
	
	@Inject
	private TarefaDiscursivas tarefaDiscursivas;
	
	@Inject
	private EnderecoService enderecoService;
	
	private Aluno aluno;
	private Endereco endereco;
	private List <SelectItem> cursoSelect;
	private Curso curso;
	private List <Curso> cursoLista;
	private List <Tarefa> listaTarefas = new ArrayList<Tarefa>();
	private List <TarefaDiscursiva> listaTarefasDiscursivas = new ArrayList<TarefaDiscursiva>();
	private Tarefa tarefaSelecionada;
	private TarefaDiscursiva tarefaDiscursivaSelecionada;
	private Aluno alunoEdicao = new Aluno();
	private Tarefa tarefa = new Tarefa();
	private TarefaDiscursiva tarefaDiscursiva = new TarefaDiscursiva();
	private ItemTarefaDiscursiva itemTarefa = new ItemTarefaDiscursiva();
	private List<ItemTarefaDiscursiva> itemTarefaTemp = new ArrayList<ItemTarefaDiscursiva>();
	private Turma turma = new Turma();
	private Cache cache = new Cache();
	short num = 0;
	
	public TarefaController() {
		curso = new Curso();
		aluno = new Aluno();
		endereco = new Endereco();
		cursoLista = new ArrayList<>();
	} 
	
	public void editar() {
		tarefaDiscursivaService.salvar(tarefaDiscursiva);
		tarefaDiscursiva = new TarefaDiscursiva();
		listarTarefas();
		FacesUtil.addInfoMessage("Tarefa pontuada com sucesso!");
	}
	
	
	@PostConstruct
    private void listarTarefas() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaTarefas = tarefas.todos();
        listaTarefasDiscursivas = tarefaDiscursivas.todos();
        cache.setIdExterno(caches.todo().getIdExterno());
        turma = turmas.porId(cache.getIdExterno());
        for(int w = 0; w < listaTarefas.size(); w++) {
        	if(listaTarefas.get(w).getTurma().getId() != turma.getId()) {
        		listaTarefas.remove(w);
        	}
        }
        for(int w = 0; w < listaTarefasDiscursivas.size(); w++) {
        	if(listaTarefasDiscursivas.get(w).getTurma().getId() != turma.getId()) {
        		listaTarefasDiscursivas.remove(w);
        	}
        }
    }
	
	public void deletarTarefaDiscursiva() {
		tarefaDiscursivaService.excluir(tarefaDiscursivaSelecionada);
		tarefaDiscursivaSelecionada = null;
		listarTarefas();
		FacesUtil.addInfoMessage("Tarefa excluída com sucesso!");
	}
	
	public void deletarTarefa() {
		tarefaService.excluir(tarefaSelecionada);
		tarefaSelecionada = null;
		listarTarefas();
		FacesUtil.addInfoMessage("Tarefa excluída com sucesso!");
	}
	
	public ItemTarefaDiscursiva getItemTarefa() {
		return itemTarefa;
	}

	public void setItemTarefa(ItemTarefaDiscursiva itemTarefa) {
		this.itemTarefa = itemTarefa;
	}

	public List<TarefaDiscursiva> getListaTarefasDiscursivas() {
		return listaTarefasDiscursivas;
	}


	public void setListaTarefasDiscursivas(List<TarefaDiscursiva> listaTarefasDiscursivas) {
		this.listaTarefasDiscursivas = listaTarefasDiscursivas;
	}


	public TarefaDiscursiva getTarefaDiscursivaSelecionada() {
		return tarefaDiscursivaSelecionada;
	}


	public void setTarefaDiscursivaSelecionada(TarefaDiscursiva tarefaDiscursivaSelecionada) {
		this.tarefaDiscursivaSelecionada = tarefaDiscursivaSelecionada;
	}


	public TarefaDiscursiva getTarefaDiscursiva() {
		return tarefaDiscursiva;
	}


	public void setTarefaDiscursiva(TarefaDiscursiva tarefaDiscursiva) {
		this.tarefaDiscursiva = tarefaDiscursiva;
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
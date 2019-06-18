package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
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
import br.com.adsdw.model.Curso;
import br.com.adsdw.model.Endereco;
import br.com.adsdw.model.ItemTarefaDiscursiva;
import br.com.adsdw.model.ItemTarefaObjetiva;
import br.com.adsdw.model.Tarefa;
import br.com.adsdw.model.TarefaDiscursiva;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.repository.TarefaDiscursivas;
import br.com.adsdw.repository.Tarefas;
import br.com.adsdw.service.AlunoService;
import br.com.adsdw.service.EnderecoService;
import br.com.adsdw.service.TarefaDiscursivaService;
import br.com.adsdw.service.TarefaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@SessionScoped
public class TarefaAlunoController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private TarefaService tarefaService;
	
	@Inject
	private TarefaDiscursivaService tarefaDiscursivaService;
	
	@Inject
	private Alunos alunos;
	
	@Inject
	private Tarefas tarefas;
	
	@Inject
	private TarefaDiscursivas tarefaDiscursivas;
	
	private Aluno aluno;
	private Curso curso;
	private List <Curso> cursoLista;
	private List <Tarefa> listaTarefas = new ArrayList<Tarefa>();
	private List <TarefaDiscursiva> listaTarefasDiscursivas = new ArrayList<TarefaDiscursiva>();
	private List <ItemTarefaObjetiva> listaItemTarefas = new ArrayList<ItemTarefaObjetiva>();
	private List <ItemTarefaDiscursiva> listaItemTarefasDiscursivas = new ArrayList<ItemTarefaDiscursiva>();
	private Tarefa tarefaSelecionada;
	private TarefaDiscursiva tarefaDiscursivaSelecionada;
	private ItemTarefaObjetiva itemTarefaObjetivaSelecionada;
	private ItemTarefaDiscursiva itemTarefaDiscursivaSelecionada;
	private Aluno alunoEdicao = new Aluno();
	private Tarefa tarefa = new Tarefa();
	private TarefaDiscursiva tarefaDiscursiva = new TarefaDiscursiva();
	private ItemTarefaDiscursiva itemTarefa;
	private ItemTarefaObjetiva itemTarefaObjetiva = new ItemTarefaObjetiva();
	private ItemTarefaDiscursiva itemTarefaDiscursiva = new ItemTarefaDiscursiva();
	short h = 0;
	int k = 0;
	double ponto [] = new double[3];
	short m = 0;
	int n = 0;
	double pontoD [] = new double[3];
	
	public TarefaAlunoController() {
		novoItem();
	} 
	
	public void novoItem() {
		itemTarefaObjetiva = new ItemTarefaObjetiva();
		itemTarefaDiscursiva = new ItemTarefaDiscursiva();
	}
	
	public void prepararItemTarefaDiscursiva() {
		//observar variável k com if
		tarefaDiscursiva.setPontuacao(0.0);
		itemTarefaDiscursiva.setId(tarefaDiscursiva.getItemTarefaDiscursiva().get(n).getId());
		itemTarefaDiscursiva.setValorDiscursiva(tarefaDiscursiva.getItemTarefaDiscursiva().get(n).getValorDiscursiva());
		itemTarefaDiscursiva.setNumQuestao(tarefaDiscursiva.getItemTarefaDiscursiva().get(n).getNumQuestao());
		itemTarefaDiscursiva.setDescricaoQuestao(tarefaDiscursiva.getItemTarefaDiscursiva().get(n).getDescricaoQuestao());
		itemTarefaDiscursiva.setRespostaDiscursiva(tarefaDiscursiva.getItemTarefaDiscursiva().get(n).getRespostaDiscursiva());
	}
	
	public void prepararItemTarefa() {
		//observar variável k com if
		tarefa.setPontuacao(0.0);
		itemTarefaObjetiva.setAlunoA(false);
		itemTarefaObjetiva.setAlunoB(false);
		itemTarefaObjetiva.setAlunoC(false);
		itemTarefaObjetiva.setAlunoD(false);
		itemTarefaObjetiva.setId(tarefa.getItemTarefaObjetiva().get(k).getId());
		itemTarefaObjetiva.setValorObjetiva(tarefa.getItemTarefaObjetiva().get(k).getValorObjetiva());
		itemTarefaObjetiva.setNumQuestao(tarefa.getItemTarefaObjetiva().get(k).getNumQuestao());
		itemTarefaObjetiva.setQuestao(tarefa.getItemTarefaObjetiva().get(k).getQuestao());
		itemTarefaObjetiva.setObjetivaA(tarefa.getItemTarefaObjetiva().get(k).getObjetivaA());
		itemTarefaObjetiva.setObjetivaB(tarefa.getItemTarefaObjetiva().get(k).getObjetivaB());
		itemTarefaObjetiva.setObjetivaC(tarefa.getItemTarefaObjetiva().get(k).getObjetivaC());
		itemTarefaObjetiva.setObjetivaD(tarefa.getItemTarefaObjetiva().get(k).getObjetivaD());
		itemTarefaObjetiva.setLetraA(tarefa.getItemTarefaObjetiva().get(k).isLetraA());
		itemTarefaObjetiva.setLetraB(tarefa.getItemTarefaObjetiva().get(k).isLetraB());
		itemTarefaObjetiva.setLetraC(tarefa.getItemTarefaObjetiva().get(k).isLetraC());
		itemTarefaObjetiva.setLetraD(tarefa.getItemTarefaObjetiva().get(k).isLetraD());
	}
	
	@PostConstruct
    public void listarTarefas() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaTarefas = tarefas.todos();
        listaTarefasDiscursivas = tarefaDiscursivas.todos();
        listaItemTarefas = tarefas.todosItem();
        listaItemTarefasDiscursivas = tarefaDiscursivas.todosItem();
    }
	
	public void guardaTarefa() {
		tarefa.getItemTarefaObjetiva().get(k).setId(itemTarefaObjetiva.getId());
		tarefa.getItemTarefaObjetiva().get(k).setNumQuestao(itemTarefaObjetiva.getNumQuestao());
		tarefa.getItemTarefaObjetiva().get(k).setQuestao(itemTarefaObjetiva.getQuestao());
		tarefa.getItemTarefaObjetiva().get(k).setAlunoA(itemTarefaObjetiva.isAlunoA());
		tarefa.getItemTarefaObjetiva().get(k).setAlunoB(itemTarefaObjetiva.isAlunoB());
		tarefa.getItemTarefaObjetiva().get(k).setAlunoC(itemTarefaObjetiva.isAlunoC());
		tarefa.getItemTarefaObjetiva().get(k).setAlunoD(itemTarefaObjetiva.isAlunoD());
	}
	
	public void guardaTarefaDiscursiva() {
		tarefaDiscursiva.getItemTarefaDiscursiva().get(n).setId(itemTarefaDiscursiva.getId());
		tarefaDiscursiva.getItemTarefaDiscursiva().get(n).setNumQuestao(itemTarefaDiscursiva.getNumQuestao());
		tarefaDiscursiva.getItemTarefaDiscursiva().get(n).setDescricaoQuestao(itemTarefaDiscursiva.getDescricaoQuestao());
		tarefaDiscursiva.getItemTarefaDiscursiva().get(n).setRespostaDiscursiva(itemTarefaDiscursiva.getRespostaDiscursiva());
	}
	
	public void nextQuestao() {
		if(k < tarefa.getItemTarefaObjetiva().size() -1) {
			calculaQuestao();
			guardaTarefa();
			k++;
			prepararItemTarefa();
		}
		else {
			FacesUtil.addInfoMessage("Não há mais questões!");
		}	
	}
	
	public void nextQuestaoDiscursiva() {
		if(n < tarefaDiscursiva.getItemTarefaDiscursiva().size() -1) {
			guardaTarefaDiscursiva();
			n++;
			prepararItemTarefaDiscursiva();
		}
		else {
			FacesUtil.addInfoMessage("Não há mais questões!");
		}	
	}
	
	public void previousQuestao () {
		if(k == 0) {
			FacesUtil.addInfoMessage("Já está na questão 1!");
		}
		else {
			calculaQuestao();
			guardaTarefa();
			k--;
			prepararItemTarefa();
		}
	}
	
	public void previousQuestaoDiscursiva () {
		if(n == 0) {
			FacesUtil.addInfoMessage("Já está na questão 1!");
		}
		else {
			guardaTarefaDiscursiva();
			n--;
			prepararItemTarefaDiscursiva();
		}
	}
	
	public void calculaQuestao() {
		ponto [k] = itemTarefaObjetiva.corrigirQuestao();
		System.out.println("Teste: " + itemTarefaObjetiva.getValorObjetiva());
	}
	
	public void calculaTudo() {
		for(int i=0; i<ponto.length; i++) {
			tarefa.setPontuacao(ponto[i]);
		}
	}
	
	public void incluirTarefa() {
		calculaQuestao();
		calculaTudo();
		guardaTarefa();
		tarefaService.salvar(tarefa);	
		tarefa = new Tarefa();
		h = 0;
		k = 0;
		ponto = new double [3];
		itemTarefaObjetiva = new ItemTarefaObjetiva();
		FacesUtil.addInfoMessage("Tarefa salva com sucesso!");
	}
	
	public void incluirTarefaDiscursiva() {
		guardaTarefaDiscursiva();
		tarefaDiscursivaService.salvar(tarefaDiscursiva);	
		tarefaDiscursiva = new TarefaDiscursiva();
		n = 0;
		itemTarefaDiscursiva = new ItemTarefaDiscursiva();
		FacesUtil.addInfoMessage("Tarefa salva com sucesso!");
	}
	
	

	public List<TarefaDiscursiva> getListaTarefasDiscursivas() {
		return listaTarefasDiscursivas;
	}

	public void setListaTarefasDiscursivas(List<TarefaDiscursiva> listaTarefasDiscursivas) {
		this.listaTarefasDiscursivas = listaTarefasDiscursivas;
	}

	public List<ItemTarefaDiscursiva> getListaItemTarefasDiscursivas() {
		return listaItemTarefasDiscursivas;
	}

	public void setListaItemTarefasDiscursivas(List<ItemTarefaDiscursiva> listaItemTarefasDiscursivas) {
		this.listaItemTarefasDiscursivas = listaItemTarefasDiscursivas;
	}

	public TarefaDiscursiva getTarefaDiscursivaSelecionada() {
		return tarefaDiscursivaSelecionada;
	}

	public void setTarefaDiscursivaSelecionada(TarefaDiscursiva tarefaDiscursivaSelecionada) {
		this.tarefaDiscursivaSelecionada = tarefaDiscursivaSelecionada;
	}

	public ItemTarefaDiscursiva getItemTarefaDiscursivaSelecionada() {
		return itemTarefaDiscursivaSelecionada;
	}

	public void setItemTarefaDiscursivaSelecionada(ItemTarefaDiscursiva itemTarefaDiscursivaSelecionada) {
		this.itemTarefaDiscursivaSelecionada = itemTarefaDiscursivaSelecionada;
	}

	public TarefaDiscursiva getTarefaDiscursiva() {
		return tarefaDiscursiva;
	}

	public void setTarefaDiscursiva(TarefaDiscursiva tarefaDiscursiva) {
		this.tarefaDiscursiva = tarefaDiscursiva;
	}

	public ItemTarefaDiscursiva getItemTarefaDiscursiva() {
		return itemTarefaDiscursiva;
	}

	public void setItemTarefaDiscursiva(ItemTarefaDiscursiva itemTarefaDiscursiva) {
		this.itemTarefaDiscursiva = itemTarefaDiscursiva;
	}

	public ItemTarefaObjetiva getItemTarefaObjetivaSelecionada() {
		return itemTarefaObjetivaSelecionada;
	}

	public void setItemTarefaObjetivaSelecionada(ItemTarefaObjetiva itemTarefaObjetivaSelecionada) {
		this.itemTarefaObjetivaSelecionada = itemTarefaObjetivaSelecionada;
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
	
	public List<ItemTarefaObjetiva> getListaItemTarefas() {
		return listaItemTarefas;
	}

	public void setListaItemTarefas(List<ItemTarefaObjetiva> listaItemTarefas) {
		this.listaItemTarefas = listaItemTarefas;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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

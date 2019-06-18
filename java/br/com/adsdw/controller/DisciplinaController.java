package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adsdw.model.Curso;
import br.com.adsdw.model.Disciplina;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.repository.Disciplinas;
import br.com.adsdw.service.CursoService;
import br.com.adsdw.service.DisciplinaService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class DisciplinaController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private DisciplinaService disciplinaService;
	
	@Inject
	private Disciplinas disciplinas;
	
	private List <Curso> listaCursos = new ArrayList<Curso>();
	private Curso cursoSelecionado;
	private Curso cursoEdicao = new Curso();
	private Curso curso;
	private List <Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
	private Disciplina disciplinaSelecionada;
	private Disciplina disciplinaEdicao = new Disciplina();
	private Disciplina disciplina;
	

	public DisciplinaController() {
		disciplina = new Disciplina();
	} 
	
	public void salvar() {
		disciplinaService.salvar(disciplina);
		disciplina = new Disciplina();
		listarTodos();
		FacesUtil.addInfoMessage("Disciplina salva com sucesso!");
	}
	
	public void editar() {
		disciplinaService.salvar(disciplina);
		disciplina = new Disciplina();
		listarTodos();
		FacesUtil.addInfoMessage("Disciplina editada com sucesso!");
	}
	
	@PostConstruct
    private void listarTodos() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaDisciplinas = disciplinas.todos();
    }
	
	public void deletar() {
		disciplinaService.excluir(disciplinaSelecionada);
		disciplinaSelecionada = null;
		listarTodos();
		FacesUtil.addInfoMessage("Disciplina excluída com sucesso!");
	}
	
	//JSF requer get e set no MB para manipular os componentes da tela
	public List<Disciplina> getListaDisciplinas() {
		return listaDisciplinas;
	}

	public void setListaDisciplinas(List<Disciplina> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public Disciplina getDisciplinaEdicao() {
		return disciplinaEdicao;
	}

	public void setDisciplinaEdicao(Disciplina disciplinaEdicao) {
		this.disciplinaEdicao = disciplinaEdicao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public Curso getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public Curso getCursoEdicao() {
		return cursoEdicao;
	}

	public void setCursoEdicao(Curso cursoEdicao) {
		this.cursoEdicao = cursoEdicao;
	}
}
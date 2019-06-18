package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adsdw.model.Curso;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.service.CursoService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CursoController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CursoService cursoService;
	
	@Inject
	private Cursos cursos;
	
	private List <Curso> listaCursos = new ArrayList<Curso>();
	private Curso cursoSelecionado;
	private Curso cursoEdicao = new Curso();
	private Curso curso;
	

	public CursoController() {
		curso = new Curso();
	} 

	public void salvar() {
		cursoService.salvar(curso);
		curso = new Curso();
		listarTodos();
		FacesUtil.addInfoMessage("Curso salvo com sucesso!");
	}
	
	public void editar() {
		cursoService.salvar(curso);
		curso = new Curso();
		listarTodos();
		FacesUtil.addInfoMessage("Curso editado com sucesso!");
	}
	
	@PostConstruct
    private void listarTodos() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaCursos = cursos.todos();
    }
	
	public void consultar(ActionEvent actionEvent) {
		curso = new Curso();
		listarTodos();
	}
	
	public void deletar() {
		cursoService.excluir(cursoSelecionado);
		cursoSelecionado = null;
		listarTodos();
		FacesUtil.addInfoMessage("Curso excluído com sucesso!");
	}
	
	
	//JSF requer get e set no MB para manipular os componentes da tela
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
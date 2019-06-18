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

import br.com.adsdw.model.Professor;
import br.com.adsdw.repository.Professores;
import br.com.adsdw.service.ProfessorService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProfessorController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ProfessorService professorService;
	
	@Inject
	private Professores professores;
	
	private List <Professor> listaProfessores = new ArrayList<Professor>();
	private Professor professorSelecionado;
	private Professor professorEdicao = new Professor();
	private Professor professor = new Professor();
	
	public ProfessorController() {
		professor = new Professor();
	} 
	
	/* public void novoItem() {
		endereco = new Endereco();
	} */
	
	public void salvar() {
		professorService.salvar(professor);
		professor = new Professor();
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de professor salvo com sucesso!");
	}
	
	public void editar() {
		professorService.salvar(professor);
		professor = new Professor();
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de professor editado com sucesso!");
	}
	
	@PostConstruct
    private void listarTodos() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaProfessores = professores.todos();
    }
	
	public void deletar() {
		professorService.excluir(professorSelecionado);
		professorSelecionado = null;
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de professor excluído com sucesso!");
	}

	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public Professor getProfessorEdicao() {
		return professorEdicao;
	}

	public void setProfessorEdicao(Professor professorEdicao) {
		this.professorEdicao = professorEdicao;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
}
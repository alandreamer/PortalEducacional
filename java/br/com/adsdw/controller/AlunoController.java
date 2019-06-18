package br.com.adsdw.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.adsdw.model.Aluno;
import br.com.adsdw.model.Curso;
import br.com.adsdw.model.Endereco;
import br.com.adsdw.repository.Alunos;
import br.com.adsdw.repository.Cursos;
import br.com.adsdw.service.AlunoService;
import br.com.adsdw.service.CepWebService;
import br.com.adsdw.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AlunoController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private AlunoService alunoService;
	
	@Inject
	private Alunos alunos;
	
	@Inject 
	private Cursos cursos;
	
	private Aluno aluno;
	private Endereco endereco;
	private List <SelectItem> cursoSelect;
	private Curso curso;
	private List <Curso> cursoLista;
	private List <Aluno> listaAlunos = new ArrayList<Aluno>();
	private Aluno alunoSelecionado;
	private Aluno alunoEdicao = new Aluno();
	
	public AlunoController() {
		curso = new Curso();
		aluno = new Aluno();
		endereco = new Endereco();
		cursoLista = new ArrayList<>();
	} 

	public void salvar() {
		List <Curso> cursoSalva = new ArrayList<>();
		cursoSalva.add(curso);
		aluno.setCurso(cursoSalva);
		aluno.setEndereco(endereco);
		alunoService.salvar(aluno);
		aluno = new Aluno();
		endereco = new Endereco();
		curso = new Curso();
		cursoSalva = new ArrayList<>();
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de aluno salvo com sucesso!");
	}
	
	public void editar() {
		alunoService.salvar(aluno);
		aluno = new Aluno();
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de aluno editado com sucesso!");
	}
	
	@PostConstruct
    private void listarTodos() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        listaAlunos = alunos.todos();
    }
	
	public void deletar() {
		alunoService.excluir(alunoSelecionado);
		alunoSelecionado = null;
		listarTodos();
		FacesUtil.addInfoMessage("Cadastro de aluno excluído com sucesso!");
	}
	
	public void encontraCep(AjaxBehaviorEvent event) {
        CepWebService cepWebService = new CepWebService(endereco.getCep());
 
        if (cepWebService.getResultado() == 1) {
            endereco.setLogradouro(cepWebService.getLogradouro());
            endereco.setUf(cepWebService.getEstado());
            endereco.setCidade(cepWebService.getCidade());
            endereco.setBairro(cepWebService.getBairro());
        } else {
        	FacesUtil.addErrorMessage("Servidor dos Correios não está respondendo.");
        }
    }
	//JSF requer get e set no MB para manipular os componentes da tela
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
	
	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
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
	
	public List<SelectItem> getCursoSelect() {
		
		cursoSelect = new ArrayList<SelectItem>();
		cursoLista = cursos.todos();
		
		for (Curso curso : cursoLista) {
			cursoSelect.add(new SelectItem(curso, curso.getNomeCurso()));
		}	
		return cursoSelect;
	}
}
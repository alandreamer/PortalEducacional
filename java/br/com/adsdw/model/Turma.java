package br.com.adsdw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Turma implements Serializable, SampleEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Disciplina disciplina;
	
	@OneToMany (mappedBy = "turma", cascade = CascadeType.ALL)
	private List<Tarefa> tarefa = new ArrayList<>();
	
	@OneToMany (mappedBy = "turma", cascade = CascadeType.ALL)
	private List<TarefaDiscursiva> tarefaDiscursiva = new ArrayList<>();
	
	@OneToMany (mappedBy = "turma", cascade = CascadeType.ALL)
	private List<PlanoAula> planoAula = new ArrayList<>();
	
	private String semestre;
	
	private String ano;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<PlanoAula> getPlanoAula() {
		return planoAula;
	}

	public void setPlanoAula(List<PlanoAula> planoAula) {
		this.planoAula = planoAula;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}

	public List<TarefaDiscursiva> getTarefaDiscursiva() {
		return tarefaDiscursiva;
	}

	public void setTarefaDiscursiva(List<TarefaDiscursiva> tarefaDiscursiva) {
		this.tarefaDiscursiva = tarefaDiscursiva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
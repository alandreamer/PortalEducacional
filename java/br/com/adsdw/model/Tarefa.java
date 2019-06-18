package br.com.adsdw.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	private Date dataEntrega;
	
	private String dataEntregaFormat;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_postagem")
	private Date dataPostagem;
	
	@ManyToOne
	private Turma turma;
	
	private double pontuacao;
	
	@OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List <ItemTarefaObjetiva> itemTarefaObjetiva = new ArrayList<ItemTarefaObjetiva>();
	

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<ItemTarefaObjetiva> getItemTarefaObjetiva() {
		return itemTarefaObjetiva;
	}

	public void setItemTarefaObjetiva(List<ItemTarefaObjetiva> itemTarefaObjetiva) {
		this.itemTarefaObjetiva = itemTarefaObjetiva;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao += pontuacao;
	}
	
	public String getDataEntregaFormat() {
		SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");

		String dataFormatada = null;

		dataFormatada = formatoDesejado.format(dataEntrega);
		
		return dataFormatada;
	}
	
	public Date getDataEntrega() {
		
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}	

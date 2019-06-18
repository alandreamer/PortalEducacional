package br.com.adsdw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ItemTarefaDiscursiva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private double valorDiscursiva;
	
	private short numQuestao;
	
	private String descricaoQuestao;
	
	private String respostaDiscursiva;
	
	@ManyToOne 
	private TarefaDiscursiva tarefaDiscursiva;
	

	public double getValorDiscursiva() {
		return valorDiscursiva;
	}

	public void setValorDiscursiva(double valorDiscursiva) {
		this.valorDiscursiva = valorDiscursiva;
	}

	public String getRespostaDiscursiva() {
		return respostaDiscursiva;
	}

	public void setRespostaDiscursiva(String respostaDiscursiva) {
		this.respostaDiscursiva = respostaDiscursiva;
	}

	public TarefaDiscursiva getTarefaDiscursiva() {
		return tarefaDiscursiva;
	}

	public void setTarefaDiscursiva(TarefaDiscursiva tarefaDiscursiva) {
		this.tarefaDiscursiva = tarefaDiscursiva;
	}

	public short getNumQuestao() {
		return numQuestao;
	}

	public void setNumQuestao(short numQuestao) {
		this.numQuestao = numQuestao;
	}

	public String getDescricaoQuestao() {
		return descricaoQuestao;
	}

	public void setDescricaoQuestao(String descricaoQuestao) {
		this.descricaoQuestao = descricaoQuestao;
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
		ItemTarefaDiscursiva other = (ItemTarefaDiscursiva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}	

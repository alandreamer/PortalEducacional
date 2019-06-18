package br.com.adsdw.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class ItemTarefaObjetiva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private double valorObjetiva;
	
	private short numQuestao;
	private String questao;
	
	private boolean letraA;
	private boolean letraB;
	private boolean letraC;
	private boolean letraD;
	
	private String objetivaA;
	private String objetivaB;
	private String objetivaC;
	private String objetivaD;
	
	private boolean alunoA;
	private boolean alunoB;
	private boolean alunoC;
	private boolean alunoD;
	
	@ManyToOne 
	private Tarefa tarefa;

	
	public double corrigirQuestao() {
		if(alunoA && alunoA == letraA) {
			return valorObjetiva;
		} else if(alunoB && alunoB == letraB) {
			return valorObjetiva;
		} else if(alunoC && alunoC == letraC) {
			return valorObjetiva;
		} else if(alunoD && alunoD == letraD) {
			return valorObjetiva;
		} else {
			return 0.0;
		}
	}
	
	
	public boolean isAlunoA() {
		return alunoA;
	}


	public void setAlunoA(boolean alunoA) {
		this.alunoA = alunoA;
	}


	public boolean isAlunoB() {
		return alunoB;
	}


	public void setAlunoB(boolean alunoB) {
		this.alunoB = alunoB;
	}


	public boolean isAlunoC() {
		return alunoC;
	}


	public void setAlunoC(boolean alunoC) {
		this.alunoC = alunoC;
	}


	public boolean isAlunoD() {
		return alunoD;
	}


	public void setAlunoD(boolean alunoD) {
		this.alunoD = alunoD;
	}

	public short getNumQuestao() {
		return numQuestao;
	}


	public void setNumQuestao(short numQuestao) {
		this.numQuestao = numQuestao;
	}


	public String getQuestao() {
		return questao;
	}


	public void setQuestao(String questao) {
		this.questao = questao;
	}


	public boolean isLetraA() {
		return letraA;
	}


	public void setLetraA(boolean letraA) {
		this.letraA = letraA;
	}


	public boolean isLetraB() {
		return letraB;
	}


	public void setLetraB(boolean letraB) {
		this.letraB = letraB;
	}


	public boolean isLetraC() {
		return letraC;
	}


	public void setLetraC(boolean letraC) {
		this.letraC = letraC;
	}


	public boolean isLetraD() {
		return letraD;
	}


	public void setLetraD(boolean letraD) {
		this.letraD = letraD;
	}


	public String getObjetivaA() {
		return objetivaA;
	}


	public void setObjetivaA(String objetivaA) {
		this.objetivaA = objetivaA;
	}


	public String getObjetivaB() {
		return objetivaB;
	}


	public void setObjetivaB(String objetivaB) {
		this.objetivaB = objetivaB;
	}


	public String getObjetivaC() {
		return objetivaC;
	}


	public void setObjetivaC(String objetivaC) {
		this.objetivaC = objetivaC;
	}


	public String getObjetivaD() {
		return objetivaD;
	}


	public void setObjetivaD(String objetivaD) {
		this.objetivaD = objetivaD;
	}


	public double getValorObjetiva() {
		return valorObjetiva;
	}


	public void setValorObjetiva(double valorObjetiva) {
		this.valorObjetiva = valorObjetiva;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}


	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
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
		ItemTarefaObjetiva other = (ItemTarefaObjetiva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}	

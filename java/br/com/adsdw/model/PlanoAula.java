package br.com.adsdw.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PlanoAula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String objetivoAula;
	
	@NotEmpty
	private String metodologia;
	
	@NotEmpty
	private String leitura;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_aula")
	private Date dataAula;

	@ManyToOne
	private Turma turma;
	
	private String dataEntregaFormat;
	
	public String getDataEntregaFormat() {
		SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");

		String dataFormatada = null;

		dataFormatada = formatoDesejado.format(dataAula);
		
		return dataFormatada;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getObjetivoAula() {
		return objetivoAula;
	}

	public void setObjetivoAula(String objetivoAula) {
		this.objetivoAula = objetivoAula;
	}

	public String getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}

	public String getLeitura() {
		return leitura;
	}

	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}

	public Date getDataAula() {
		return dataAula;
	}

	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
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
		PlanoAula other = (PlanoAula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}	

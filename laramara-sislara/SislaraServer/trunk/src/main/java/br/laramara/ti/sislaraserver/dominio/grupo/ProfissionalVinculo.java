package br.laramara.ti.sislaraserver.dominio.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;

@Entity
@Table(name = "profissional_vinculo")
public class ProfissionalVinculo implements Identificavel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_profissional", nullable = false)
	private Profissional profissional;
	
	@Column(name = "participante")
	private boolean participante;

	public ProfissionalVinculo(){
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public boolean isParticipante() {
		return participante;
	}

	public void setParticipante(boolean participante) {
		this.participante = participante;
	}

	@Override
	public String toString() {
		return "ProfissionalVinculo [id=" + id + ", profissional="
				+ profissional + ", participante=" + participante + "]";
	}
}

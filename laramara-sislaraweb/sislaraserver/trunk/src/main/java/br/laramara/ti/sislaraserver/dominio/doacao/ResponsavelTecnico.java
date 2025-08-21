package br.laramara.ti.sislaraserver.dominio.doacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@Table(name = "responsavel_tecnico")
public class ResponsavelTecnico implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_profissional", nullable = false)
	private Profissional profissional;

	@Column(name = "principal")
	private boolean principal;

	public ResponsavelTecnico(){
	}
	
	public ResponsavelTecnico(Long id, Profissional profissional, boolean principal) {
		super();
		this.id = id;
		this.profissional = profissional;
		this.principal = principal;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public boolean isPrincipal() {
		return principal;
	}

	@Override
	public String toString() {
		return "ResponsavelTecnico [id=" + id + ", profissional=" + profissional + ", principal=" + principal + "]";
	}
}

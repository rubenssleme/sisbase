package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

@Entity
@Table(name = "recurso_relacionamento")
public class RecursoRelacionamento extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_recurso", nullable = false)
	private Recurso recurso;

	@Enumerated(EnumType.STRING)
	@Column(name = "possui_necessita", length = TamanhoMaximoGenerico.POSSUI_NECESSITA, nullable = false)
	private PossuiNecessita possuiNecessita;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public PossuiNecessita getPossuiNecessita() {
		return possuiNecessita;
	}

	public void setPossuiNecessita(PossuiNecessita possuiNecessita) {
		this.possuiNecessita = possuiNecessita;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (recurso == null) {
			adicionarErro("Insira um Recurso.");
		}
		if (possuiNecessita == null) {
			adicionarErro("Insira uma opção possui/necessita para o recurso.");
		}
	}

	@Override
	public String toString() {
		return "RecursoRelacionamento [id=" + id + ", recurso=" + recurso + ", possuiNecessita=" + possuiNecessita + "]";
	}
}

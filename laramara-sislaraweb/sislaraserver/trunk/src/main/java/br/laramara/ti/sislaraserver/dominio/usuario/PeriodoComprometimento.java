package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

@Entity
@Table(name = "periodo_comprometimento")
public class PeriodoComprometimento extends Historico implements ValidavelObrigatoriedadeETamanhoMaximo {

	@ManyToOne
	@JoinColumn(name = "id_comprometimento")
	private Comprometimento comprometimento;

	@Enumerated(EnumType.STRING)
	@Column(name = "epoca_incidencia", length = TamanhoMaximoUsuario.EPOCA_INCIDENCIA)
	private EpocaIncidencia epocaIncidencia;

	public Comprometimento getComprometimento() {
		return comprometimento;
	}

	public void setComprometimento(Comprometimento comprometimento) {
		this.comprometimento = comprometimento;
	}

	public EpocaIncidencia getEpocaIncidencia() {
		return epocaIncidencia;
	}

	public void setEpocaIncidencia(EpocaIncidencia epocaIncidencia) {
		this.epocaIncidencia = epocaIncidencia;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (comprometimento == null){
			adicionarErro("Insira o comprometimento.");
		}
		super.validarObrigatoriedadeEObrigatoriedadeDeDados();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeriodoComprometimento other = (PeriodoComprometimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeriodoComprometimento [comprometimento=" + comprometimento + ", epocaIncidencia=" + epocaIncidencia
				+ ", dataInicial=" + DataHoraUtils.formatarDataHora(dataInicialVigencia) + ", dataFinal="
				+ DataHoraUtils.formatarDataHora(dataFinalVigencia) + "]";
	}
}

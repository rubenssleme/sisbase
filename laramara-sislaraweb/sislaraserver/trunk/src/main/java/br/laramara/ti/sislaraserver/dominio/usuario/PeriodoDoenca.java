package br.laramara.ti.sislaraserver.dominio.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

@Entity
@Table(name = "periodo_doenca")
public class PeriodoDoenca extends Historico implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_doenca")
	private Doenca doenca;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String obs;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Obs contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	private void validarObrigatoriedade() {
		if (doenca == null) {
			adicionarErro("Insira uma Doenca.");
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
		PeriodoDoenca other = (PeriodoDoenca) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeriodoDoenca [id=" + id + ", doença=" + doenca
				+ ", dataInicial="
				+ DataHoraUtils.formatarDataHora(dataInicialVigencia)
				+ ", dataFinal="
				+ DataHoraUtils.formatarDataHora(dataFinalVigencia) + ", obs="
				+ obs + "]";
	}
}

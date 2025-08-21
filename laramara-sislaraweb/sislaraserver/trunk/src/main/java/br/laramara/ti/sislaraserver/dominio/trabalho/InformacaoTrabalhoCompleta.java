package br.laramara.ti.sislaraserver.dominio.trabalho;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

@Entity
@Table(name = "informacao_trabalho_completa")
public class InformacaoTrabalhoCompleta extends Historico implements ValidavelObrigatoriedadeETamanhoMaximo {

	@OneToOne(optional = false)
	@JoinColumn(name = "id_local_trabalho")
	private LocalTrabalho localTrabalho;

	@OneToOne(optional = false)
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	@Enumerated(EnumType.STRING)
	@Column(name = "voluntario", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao voluntario;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estagiario", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao estagiario;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "encaminhado_por_laramara", length = TamanhoMaximoGenerico.SIM_NAO)
	private SimNao encaminhadoPorLaramara;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String obs;

	public InformacaoTrabalhoCompleta() {
	}

	public LocalTrabalho getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(LocalTrabalho localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public SimNao getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(SimNao voluntario) {
		this.voluntario = voluntario;
	}
	
	public SimNao getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(SimNao estagiario) {
		this.estagiario = estagiario;
	}

	public SimNao getEncaminhadoPorLaramara() {
		return encaminhadoPorLaramara;
	}

	public void setEncaminhadoPorLaramara(SimNao encaminhadoPorLaramara) {
		this.encaminhadoPorLaramara = encaminhadoPorLaramara;
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Obs contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (localTrabalho == null) {
			adicionarErro("Insira um Local de Trabalho.");
		}
		if (cargo == null) {
			adicionarErro("Insira um Cargo.");
		}
		if (encaminhadoPorLaramara == null) {
			adicionarErro("Insira um Encaminhamento pela Laramara.");
		}
		super.validarObrigatoriedadeEObrigatoriedadeDeDados();
		validarTamanhoMaximoDeDados();
	}

	@Override
	public String toString() {
		return "InformacaoTrabalhoCompleta [id=" + id
				+ ", dataInicialVigencia = " + DataHoraUtils.formatarData(dataInicialVigencia)
				+ ", dataFinalVigencia = " + DataHoraUtils.formatarData(dataFinalVigencia)
				+ ", localTrabalho=" + localTrabalho + ", cargo=" + cargo
				+ ", voluntario=" + voluntario + ", estagiario=" + estagiario + ", encaminhadoPorLaramara="
				+ encaminhadoPorLaramara + ", obs=" + obs + "]";
	}
}

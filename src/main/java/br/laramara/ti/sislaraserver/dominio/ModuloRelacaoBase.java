package br.laramara.ti.sislaraserver.dominio;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;

@MappedSuperclass
public abstract class ModuloRelacaoBase extends Validavel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	protected Calendar dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ocorrencia")
	protected Calendar dataOcorrencia;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = TamanhoMaximoGenerico.STATUS, nullable = false)
	protected StatusRelacaoComModulo status;
	
	@Column(name = "aprovado")
	protected boolean aprovado;
	
	@Column(name = "obs", length = TamanhoMaximoGenerico.OBS)
	protected String obs;

	public ModuloRelacaoBase(){
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataInicio() {
		return DataHoraUtils.formatarData(dataInicio);
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataInicio);
	}

	public String getDataOcorrencia() {
		return DataHoraUtils.formatarData(dataOcorrencia);
	}
	
	public Calendar getDataOcorrenciaCalendario() {
		return dataOcorrencia;
	}
	
	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = DataHoraUtils
				.obterDataValidaInvalidaOuNulo(dataOcorrencia);
	}

	public StatusRelacaoComModulo getStatus() {
		return status;
	}

	public void setStatus(StatusRelacaoComModulo status) {
		if (status != null) {
			this.status = status;
		}
	}
	
	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getObs() {
		return obs != null ? obs : "";
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public void adicionarObs(String obs) {
		if (this.obs == null) {
			this.obs = "";
		} else {
			this.obs += "\n";
		}
		this.obs += obs;
	}
	
	public boolean estaIntegradoOuRemovidoOuDesligado() {
		return estaIntegrado() || estaRemovido() || estaDesligado();
	}
	
	public boolean estaEventual() {
		return status.equals(StatusRelacaoComModulo.EVENTUAL);
	}
	
	private boolean estaRemovido() {
		return status.equals(StatusRelacaoComModulo.REMOVIDO);
	}

	public boolean estaIntegrado() {
		return status.equals(StatusRelacaoComModulo.INTEGRADO);
	}
	
	public boolean estaDesligado() {
		return status.equals(StatusRelacaoComModulo.DESLIGADO);
	}

	public boolean estaProvisorioOuAcesso() {
		return estaAcesso() || estaProvisorio();
	}
	
	public boolean estaIntegradoOuProvisorioOuAcesso() {
		return estaIntegrado() || estaAcesso() || estaProvisorio();
	}

	public boolean estaProvisorio() {
		return status.equals(StatusRelacaoComModulo.PROVISORIO);
	}
	
	public boolean estaAcesso() {
		return status.equals(StatusRelacaoComModulo.ACESSO);
	}
	
	protected void validarObrigatoriedade(){
		if (dataInicio == null || dataInicio != null
				&& DataHoraUtils.dataInvalida(dataInicio)) {
			adicionarErro("Insira a Data de Início válida.");
		}
		if (status != null
				&& !(status.equals(StatusRelacaoComModulo.INTEGRADO) || status.equals(StatusRelacaoComModulo.PROVISORIO)
						|| status.equals(StatusRelacaoComModulo.ACESSO)
						|| status.equals(StatusRelacaoComModulo.EVENTUAL))
				&& (dataOcorrencia == null || DataHoraUtils.dataInvalida(dataOcorrencia))) {
			adicionarErro("Insira o Status e a Data de Ocorrência válida.");
		} else if (dataOcorrencia != null && DataHoraUtils.dataInvalida(dataOcorrencia)) {
			adicionarErro("Insira uma Data de Ocorrência válida.");
		}
	}
	
	protected void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(obs, TamanhoMaximoGenerico.OBS)) {
			adicionarErro("Insira uma Observação contendo até "
					+ TamanhoMaximoGenerico.OBS + " caracteres.");
		}
	}
}

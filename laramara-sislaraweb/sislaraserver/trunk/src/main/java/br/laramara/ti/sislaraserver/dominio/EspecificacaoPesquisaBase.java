package br.laramara.ti.sislaraserver.dominio;

import java.util.Calendar;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;

public class EspecificacaoPesquisaBase extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	protected DescricaoTipoAtendimento descricaoTipoAtendimento;
	protected Modulo modulo;
	protected Long prontuario;
	protected String rg;
	protected Calendar dataInicio;
	protected Calendar dataTermino;

	public DescricaoTipoAtendimento getDescricaoTipoAtendimento() {
		return descricaoTipoAtendimento;
	}

	public void setDescricaoTipoAtendimento(
			DescricaoTipoAtendimento descricaoTipoAtendimento) {
		this.descricaoTipoAtendimento = descricaoTipoAtendimento;
	}
	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Long getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = NumeroUtils.retornaLongoOuInvalido(prontuario);
	}

	public boolean possuiProntuario() {
		return prontuario != null;
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = TextoUtils.anularVazio(rg);
	}

	public boolean possuiRg() {
		return rg != null;
	}

	public boolean possuiModulo() {
		return modulo != null;
	}
	
	public boolean possuiDescricaoTipoAtendimento() {
		return descricaoTipoAtendimento != null;
	}
	
	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = DataHoraUtils
				.obterDataValidaInvalidaOuNulo(dataInicio);
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = DataHoraUtils
				.obterDataValidaInvalidaOuNulo(dataTermino);
	}

	public boolean possuiDataInicioETermino() {
		return dataInicio != null && dataTermino != null
				&& !DataHoraUtils.dataInvalida(dataInicio)
				&& !DataHoraUtils.dataInvalida(dataTermino);
	}

	public boolean possuiApenasDataInicio() {
		return dataInicio != null && dataTermino == null
				&& !DataHoraUtils.dataInvalida(dataInicio);
	}

	protected boolean possuiTodosItensBaseNulos() {
		return descricaoTipoAtendimento == null && modulo == null
				&& prontuario == null && rg == null
				&& dataInicio == null && dataTermino == null;
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (DataHoraUtils.dataInvalida(dataInicio)) {
			adicionarErro("Insira uma Data de Início válida.");
		}
		if (DataHoraUtils.dataInvalida(dataTermino)) {
			adicionarErro("Insira uma Data de Término válida.");
		}
		if (DataHoraUtils
				.dataTerminoAnteriorDataInicio(dataInicio, dataTermino)) {
			adicionarErro("Insira uma Data de Témino posterior a Data de Início.");
		}
	}

	@Override
	public String toString() {
		return "EspecificacaoPesquisaBase [descricaoTipoAtendimento="
				+ descricaoTipoAtendimento + ", modulo=" + modulo
				+ ", prontuario=" + prontuario + ", rg="
				+ rg + ", dataInicio="
				+ DataHoraUtils.formatarData(dataInicio) + ", dataTermino="
				+ DataHoraUtils.formatarData(dataTermino) + "]";
	}
}

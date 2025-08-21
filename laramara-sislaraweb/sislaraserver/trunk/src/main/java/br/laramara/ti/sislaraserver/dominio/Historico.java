package br.laramara.ti.sislaraserver.dominio;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@MappedSuperclass
public abstract class Historico extends Validavel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicial_vigencia", nullable = false)
	protected Calendar dataInicialVigencia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_final_vigencia")
	protected Calendar dataFinalVigencia;
	
	public Historico() {
		dataInicialVigencia = obterDataHoraAtual();
	}
	
	protected Calendar obterDataHoraAtual(){
		return MaquinaTempo.obterInstancia().obterCalendarioAtual();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public boolean possuiId() {
		return id != null;
	}
	
	public int getAnosTranscorridos() {
		return DataHoraUtils.obterAnosTranscorridos(dataInicialVigencia);
	}
	
	public Calendar getDataInicialVigencia() {
		return dataInicialVigencia;
	}
	
	public Calendar getDataFinalVigencia(){
		return dataFinalVigencia;
	}
	
	public String getDataInicial() {
		return DataHoraUtils.formatarData(dataInicialVigencia);
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicialVigencia = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataInicial);
	}

	public String getDataFinal() {
		return DataHoraUtils.formatarData(dataFinalVigencia);
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinalVigencia = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataFinal);
	}
	
	public Boolean vigenciaEncerrada() {
		return dataFinalVigencia != null;
	}
	
	public void encerrarVigencia() {
		if (dataFinalVigencia == null){
			dataFinalVigencia = obterDataHoraAtual();
		}
	}
	
	public static Historico obterHistoricoAtual(List<? extends Historico> historicos) {
		Historico retorno = null;
		if (historicos != null) {
			for (Historico historico : historicos) {
				if (!historico.vigenciaEncerrada()) {
					retorno = historico;
				}
			}
		}
		return retorno;
	}
	
	public static String getHistoricoOperacoes(List<? extends HistoricoOperacao> historicos) {
		String resultado = "";
		for (HistoricoOperacao historico : historicos) {
			resultado += historico.getStringDoStatus()
					+ " por ";
			if (historico.getContaAcesso()!=null){
				resultado+= historico.getContaAcesso().getLogin();
			}else{
				resultado+="NENHUM";
			}
			resultado+= " de "
					+ DataHoraUtils.formatarDataHora(historico
							.getDataInicialVigencia()) + " até ";
			if (historico.vigenciaEncerrada()) {
				resultado += DataHoraUtils.formatarDataHora(historico
						.getDataFinalVigencia());
			} else {
				resultado += "HOJE";
			}
			resultado += ". ";
		}
		return resultado;
	}
	
	public void validarObrigatoriedadeEObrigatoriedadeDeDados() {
		if (dataInicialVigencia == null
				|| DataHoraUtils.dataInvalida(dataInicialVigencia)) {
			adicionarErro("Insira uma Data Inicial válida.");
		}
		if (DataHoraUtils.dataInvalida(dataFinalVigencia)) {
			adicionarErro("Insira uma Data Final válida.");
		}
		if (DataHoraUtils.dataTerminoAnteriorDataInicio(dataInicialVigencia,
				dataFinalVigencia)) {
			adicionarErro("Insira uma Data Final posterior à Data Inicial.");
		}
	}
}

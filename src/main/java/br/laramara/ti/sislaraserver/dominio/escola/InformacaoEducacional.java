package br.laramara.ti.sislaraserver.dominio.escola;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.Historico;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;

@Entity
@Table(name = "informacao_educacional")
public class InformacaoEducacional extends Historico implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@OneToOne(optional = true)
	@JoinColumn(name = "id_instituicao")
	private Instituicao instituicao;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "id_escolaridade")
	private Escolaridade escolaridade;

	@OneToOne(optional = true)
	@JoinColumn(name = "id_serie")
	private Serie serie;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao", length = TamanhoMaximoInformacaoEscolar.SITUACAO, nullable = true)
	private SituacaoEducacional situacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "periodo", length = TamanhoMaximoInformacaoEscolar.PERIODO, nullable = true)
	private Periodo periodo;
	
	@Column(name = "nome_professor", length = TamanhoMaximoGenerico.NOME)
	private String nomeProfessor;
	
	@OneToOne
	@JoinColumn(name = "id_area_formacao")
	private AreaFormacao areaFormacao;
	
	@Column(name= "excluido")
	private boolean excluido;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_referencia", nullable = false)
	protected Calendar dataReferencia;
	
	public InformacaoEducacional() {
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void removerIdentificadorEGeraDataInicialVigencia(){
		this.id = null;
		this.dataInicialVigencia = obterDataHoraAtual();
	}

	public Long getId() {
		return id;
	}
	
	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public SituacaoEducacional getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEducacional situacao) {
		this.situacao = situacao;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	
	public AreaFormacao getAreaFormacao() {
		return areaFormacao;
	}

	public void setAreaFormacao(AreaFormacao areaFormacao) {
		this.areaFormacao = areaFormacao;
	}

	public boolean excluido() {
		return excluido;
	}


	public String getDataReferencia() {
		return DataHoraUtils.formatarData(dataReferencia);
	}

	public void setDataReferencia(String dataInformacao) {
		this.dataReferencia = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataInformacao);
	}
	
	public void marcarExcluido() {
		if (!vigenciaEncerrada()) {
			encerrarVigencia();
			this.excluido = true;
		}
	}
	
	public static List<InformacaoEducacional> obterInformacoesEducacionais(List<InformacaoEducacional> informacoesEducacionais) {
		List<InformacaoEducacional> informacoesEscolaresVigentes = new ArrayList<>();
		for (InformacaoEducacional informacaoEducacional : informacoesEducacionais) {
			if (!informacaoEducacional.vigenciaEncerrada()
					&& !informacaoEducacional.excluido()) {
				informacoesEscolaresVigentes.add(informacaoEducacional);
			}
		}
		return informacoesEscolaresVigentes;
	}
	
	public static void adicionarInformacoesEducacionais(List<InformacaoEducacional> informacoesEducacionaisExistentes, 
			List<InformacaoEducacional> informacoesEducacionaisParaAdicionar) {
		marcarExcluidoInformacoesEducacionais(informacoesEducacionaisExistentes, informacoesEducacionaisParaAdicionar);
		for (InformacaoEducacional informacaoEducacional : informacoesEducacionaisParaAdicionar) {
			InformacaoEducacional informacaoEducacionalExistente = obterInformacaoEducacionalPorId(
					informacaoEducacional, informacoesEducacionaisExistentes);
			if (informacaoEducacionalExistente != null
					&& !informacaoEducacionalExistente.equals(informacaoEducacional)) {
				informacaoEducacionalExistente.encerrarVigencia();
				informacaoEducacional.removerIdentificadorEGeraDataInicialVigencia();
				informacoesEducacionaisExistentes.add(informacaoEducacional);
			} else if (informacaoEducacionalExistente == null) {
				informacoesEducacionaisExistentes.add(informacaoEducacional);
			}
		}
	}
	
	private static void marcarExcluidoInformacoesEducacionais(
			List<InformacaoEducacional> informacoesEducacionaisExistentes,
			List<InformacaoEducacional> informacoesEducacionaisATratar) {
		for (InformacaoEducacional informacaoEducacional : informacoesEducacionaisExistentes) {
			if (obterInformacaoEducacionalPorId(informacaoEducacional,
					informacoesEducacionaisATratar) == null) {
				informacaoEducacional.marcarExcluido();
			}
		}
	}
	
	private static InformacaoEducacional obterInformacaoEducacionalPorId(
			InformacaoEducacional informacaoEducacionalAPesquisar,
			List<InformacaoEducacional> listaInformacaoEducacional) {
		InformacaoEducacional retorno = null;
		for (InformacaoEducacional informacaoEducacional : listaInformacaoEducacional) {
			if (informacaoEducacional.getId() != null
					&& informacaoEducacional.getId().equals(
							informacaoEducacionalAPesquisar.getId())) {
				retorno = informacaoEducacional;
			}
		}
		return retorno;
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}
	
	private void validarObrigatoriedade(){
		if (instituicao == null && escolaridade == null && situacao == null && serie == null && periodo == null) {
			adicionarErro("Insira alguma informação educacional.");
		}
		
		if (dataReferencia == null || DataHoraUtils.dataInvalida(dataReferencia)) {
			adicionarErro("Insira uma Data de Referência válida.");
		}
	}
	
	private void validarTamanhoMaximoDeDados() {
		if (nomeProfessor != null
				&& nomeProfessor.length() > TamanhoMaximoGenerico.NOME) {
			adicionarErro("Insira o Nome do Professor contendo até "
					+ TamanhoMaximoGenerico.NOME + " caracteres.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacaoEducacional other = (InformacaoEducacional) obj;
		if (dataReferencia == null) {
			if (other.dataReferencia != null)
				return false;
		} else if (!dataReferencia.equals(other.dataReferencia))
			return false;
		if (escolaridade == null) {
			if (other.escolaridade != null)
				return false;
		} else if (!escolaridade.equals(other.escolaridade))
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (areaFormacao == null) {
			if (other.areaFormacao != null)
				return false;
		} else if (!areaFormacao.equals(other.areaFormacao))
			return false;
		if (nomeProfessor == null) {
			if (other.nomeProfessor != null)
				return false;
		} else if (!nomeProfessor.equals(other.nomeProfessor))
			return false;
		if (periodo != other.periodo)
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InformacaoEducacional [id=" + id + ", dataInicialVigencia="
				+ DataHoraUtils.formatarDataHora(dataInicialVigencia)
				+ ", dataFinalVigencia="
				+ DataHoraUtils.formatarDataHora(dataFinalVigencia)
				+ ", dataReferencia="
				+ DataHoraUtils.formatarDataHora(dataReferencia)
				+ ", instituicao=" + instituicao + ", escolaridade="
				+ escolaridade + ", serie=" + serie + ", situacao=" + situacao
				+ ", periodo=" + periodo + ", areaFormacao = " + areaFormacao
				+ ", nomeProfessor=" + nomeProfessor + "]";
	}
}

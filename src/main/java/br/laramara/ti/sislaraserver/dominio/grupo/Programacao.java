package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "programacao")
public class Programacao extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "aula", nullable = false)
	private Integer aula;

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;

	@Column(name = "tema_conteudo", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String temaConteudo;

	@Column(name = "recado_familia", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String recadoFamilia;

	@Column(name = "estrategias", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String estrategias;

	@OneToOne
	@JoinColumn(name = "id_local_atendimento")
	private LocalAtendimento localAtendimento;

	public Programacao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAula() {
		return NumeroUtils.obterTexto(aula);
	}

	public void setAula(String aula) {
		this.aula = NumeroUtils.retornaInteiroOuInvalido(aula);
	}

	public String getData() {
		return DataHoraUtils.formatarData(data);
	}

	public void setData(String data) {
		this.data = DataHoraUtils.obterDataValidaInvalidaOuNulo(data);
	}

	public String getTemaConteudo() {
		return temaConteudo;
	}

	public void setTemaConteudo(String temaConteudo) {
		this.temaConteudo = temaConteudo;
	}

	public String getRecadoFamilia() {
		return recadoFamilia;
	}

	public void setRecadoFamilia(String recadoFamilia) {
		this.recadoFamilia = recadoFamilia;
	}

	public String getEstrategias() {
		return estrategias;
	}

	public void setEstrategias(String estrategias) {
		this.estrategias = estrategias;
	}

	public LocalAtendimento getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(LocalAtendimento localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(aula, TamanhoMaximoProgramacao.AULA)) {
			adicionarErro("Insira a Aula contendo até "
					+ TamanhoMaximoProgramacao.AULA + " caracteres.");
		}
		if (tamanhoMaximoViolado(temaConteudo,
				TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira o Tema/Conteúdo contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	private void validarObrigatoriedade() {
		if (aula == null || NumeroUtils.numeroInteiroInvalido(aula)) {
			adicionarErro("Insitra a Aula válida.");
		}
		if (data == null || DataHoraUtils.dataInvalida(data)) {
			adicionarErro("Insira a Data.");
		}
		if (temaConteudo == null
				|| (temaConteudo != null && temaConteudo.isEmpty())) {
			adicionarErro("Insira o Tema/Conteúdo.");
		}
	}

	@Override
	public String toString() {
		return "Programacao [id=" + id + ", aula=" + aula + ", data="
				+ DataHoraUtils.formatarData(data) + ", temaConteudo="
				+ temaConteudo + ", recadofamilia=" + recadoFamilia
				+ ", estrategias=" + estrategias + ", localAtendimento="
				+ localAtendimento + "]";
	}
}

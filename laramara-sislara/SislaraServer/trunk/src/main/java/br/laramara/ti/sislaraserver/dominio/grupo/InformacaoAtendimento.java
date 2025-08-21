package br.laramara.ti.sislaraserver.dominio.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "informacao_atendimento")
public class InformacaoAtendimento extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "frequencia", length = TamanhoMaximoGenerico.FREQUENCIA, nullable = false)
	private Frequencia frequencia;

	@Column(name = "justificativa", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String justificativa;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "participacao", length = TamanhoMaximoGenerico.DESCRICAO)
	private Participacao participacao;

	public InformacaoAtendimento() {
		this.frequencia = Frequencia.AT;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	public Participacao getParticipacao() {
		return participacao;
	}

	public void setParticipacao(Participacao participacao) {
		this.participacao = participacao;
	}

	public boolean estaComQualquerFrequencia(){
		return frequencia != null;
	}
	
	public boolean estaComFrenquenciaAT(){
		return frequencia.equals(Frequencia.AT);
	}
	
	public boolean estaComFrenquenciaFR() {
		return frequencia.equals(Frequencia.FR);
	}
	
	public boolean estaComFrenquenciaFP() {
		return frequencia != null && frequencia.equals(Frequencia.FP);
	}
	
	public boolean estaComFrenquenciaATouFRouFPouOAouRC(){
		return estaComFrequenciaATouFP() || estaComFrenquenciaFR() || estaComFrenquenciaOA() || estaComFrequenciaRC();
	}	
	
	private boolean estaComFrequenciaRC() {
		return frequencia.equals(Frequencia.RC);
	}

	private boolean estaComFrenquenciaOA() {
		return frequencia.equals(Frequencia.OA);
	}

	public boolean estaComFrequenciaATouFP(){
		return estaComFrenquenciaAT() || estaComFrenquenciaFP();
	}
	
	public boolean estaComFrenquenciaFUouFJ() {
		return frequencia.equals(Frequencia.FU)||frequencia.equals(Frequencia.FJ);
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	private void validarObrigatoriedade() {
		if (frequencia == null) {
			adicionarErro("Insira uma Frequência. ");
		}
	}

	private void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(descricao, TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Descrição contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
		if (tamanhoMaximoViolado(justificativa,
				TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Justificativa contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}

	@Override
	public String toString() {
		return "InformaçãoAtendimento [id=" + id + ", descricao=" + descricao
				+ ", frequencia=" + frequencia + ", justificativa="
				+ justificativa + ", participacao=" + participacao + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;
import br.laramara.ti.sislaraserver.dominio.atendimento.ParticipacaoDetalhada;
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
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "informacao_atendimento_participacao_detalhada", joinColumns = { @JoinColumn(name = "id_informacao_atendimento", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_participacao_detalhada", referencedColumnName = "id") })
	private List<ParticipacaoDetalhada> participacaoDetalhada;

	public InformacaoAtendimento() {
		this.frequencia = Frequencia.AT;
		participacaoDetalhada = new ArrayList<>();
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
	
	public List<ParticipacaoDetalhada> getParticipacaoDetalhada() {
		return participacaoDetalhada;
	}

	public void setParticipacaoDetalhada(List<ParticipacaoDetalhada> participacaoDetalhada) {
		this.participacaoDetalhada = participacaoDetalhada;
	}

	public boolean estaComQualquerFrequencia(){
		return frequencia != null;
	}
	
	public boolean estaComFrenquenciaAT(){
		return estaComQualquerFrequencia() && frequencia.equals(Frequencia.AT);
	}
	
	public boolean estaComFrenquenciaFR() {
		return estaComQualquerFrequencia() && frequencia.equals(Frequencia.FR);
	}
	
	public boolean estaComFrenquenciaFP() {
		return estaComQualquerFrequencia() && frequencia.equals(Frequencia.FP);
	}
	
	public boolean estaComFrenquenciaATouFRouFPouOAouRC(){
		return estaComFrequenciaATouFP() || estaComFrenquenciaFR() || estaComFrenquenciaOA() || estaComFrequenciaRC();
	}	
	
	private boolean estaComFrequenciaRC() {
		return estaComQualquerFrequencia() && frequencia.equals(Frequencia.RC);
	}

	private boolean estaComFrenquenciaOA() {
		return estaComQualquerFrequencia() && frequencia.equals(Frequencia.OA);
	}

	public boolean estaComFrequenciaATouFP(){
		return estaComFrenquenciaAT() || estaComFrenquenciaFP();
	}
	
	public boolean estaComFrenquenciaFUouFJ() {
		return estaComFrequenciaFU() || estaComFrequenciaFJ();
	}
	
	public boolean estaComFrenquenciaFUouFJouFP() {
		return frequencia != null && (estaComFrequenciaFU() || estaComFrequenciaFJ() || estaComFrenquenciaFP());
	}

	private boolean estaComFrequenciaFU() {
		return frequencia.equals(Frequencia.FU);
	}

	private boolean estaComFrequenciaFJ() {
		return frequencia.equals(Frequencia.FJ);
	}
	
	public void limparIndentificacaoDaParticipacaoDetalhada() {
		participacaoDetalhada.stream().forEach(participacaoDetalhada -> participacaoDetalhada.setId(null));
	}
	
	public void limparParticipacaoDetalhada() {
		if (participacaoDetalhada != null) {
			participacaoDetalhada = Arrays.asList();
		}
	}
	
	public void limparParticipacaoDetalhadaComFrequenciaFPFUouFJ() {
		if (estaComFrenquenciaFUouFJouFP()){
			limparParticipacaoDetalhada();
		}
	}
	
	public void marcarFrequenciaFPERemoverParticipacao() {
		setFrequencia(Frequencia.FP);
		limparParticipacaoDetalhada();
	}
	
	public void inicializarParticipacaoDetalhada(Grupo grupo, Modulo modulo) {
		if (grupo.eDescricaoInformaticaEOutrasTecnologiasAssistivas() || grupo.eDescricaoAEArtes()
				|| (grupo.eDescricaoAEMundoDoTrabalho() && !modulo.eCursoDeAutonomiaEIndependencia())
				|| grupo.eDescricaoAEAtividadesDeVidaAutonoma()
				|| grupo.eDescricaoAtividadesSocioPoliticas()
				|| grupo.eDescricaoPsicossocialDeJovensEAdultos()
				|| grupo.eDescricaoAtividadeDeCulturaELazer()
				|| grupo.eDescricaoOrientacaoSocioeducativa()) {
			adicionarParticipacaoDetalhadaPadrao(Participacao.APENAS_USUARIO);
		} else if (grupo.eDescricaoAEMundoDoTrabalho() && modulo.eCursoDeAutonomiaEIndependencia()) {
			adicionarParticipacaoDetalhadaPadrao(Participacao.COM_FAMILIA);
		}
	}

	private void adicionarParticipacaoDetalhadaPadrao(Participacao participacao) {
		ParticipacaoDetalhada participacaoDetalhada = new ParticipacaoDetalhada();
		participacaoDetalhada.setParticipacao(participacao);
		participacaoDetalhada.setQuantidadePadrao();
		this.participacaoDetalhada.add(participacaoDetalhada);
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
		if (participacaoDetalhada.stream()
				.anyMatch(participacaoDetalhada -> Participacao.obterParticipacoesUnicas()
						.contains(participacaoDetalhada.getParticipacao()))
				&& participacaoDetalhada.size() > 1 && !possuiSomenteParticipacaoApenasFamiliaEApenasAcompanhante()) {
			adicionarErro("As participações APENAS_FAMILIA, APENAS_ACOMPANHANTE e APENAS_USUARIO devem ser exclusivas.");
		}
	}

	private boolean possuiSomenteParticipacaoApenasFamiliaEApenasAcompanhante() {
		return participacaoDetalhada.stream().allMatch(participacaoDetalhada -> Participacao
				.obterParticipacoesUnicasLiberadas().contains(participacaoDetalhada.getParticipacao()))
				&& participacaoDetalhada.size() == 2;
	}
	
	public void validarExistenciaParticipacaDetalhada() {
		if (participacaoDetalhada.isEmpty() && estaComFrenquenciaAT()) {
			adicionarErro("Insira uma Participação no atendimento com frequencia AT.");
		}
	}

	@Override
	public String toString() {
		return "InformaçãoAtendimento [id=" + id + ", descricao=" + descricao
				+ ", frequencia=" + frequencia + ", justificativa="
				+ justificativa + ", participacaoDetalhada=" + participacaoDetalhada + "]";
	}
}

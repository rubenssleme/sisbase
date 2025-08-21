package br.laramara.ti.sislaraserver.dominio.atendimento;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;

@MappedSuperclass
public abstract class AtendimentoAuxiliarBase extends Validavel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
	@JoinColumn(name = "id_informacao_atendimento", nullable = false)
	protected InformacaoAtendimento informacaoAtendimento;

	public AtendimentoAuxiliarBase() {
		informacaoAtendimento = new InformacaoAtendimento();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InformacaoAtendimento getInformacaoAtendimento() {
		return informacaoAtendimento;
	}

	public void setInformacaoAtendimento(
			InformacaoAtendimento informacaoAtendimento) {
		this.informacaoAtendimento = informacaoAtendimento;
	}

	protected void validarExistenciaParticipacaoDetalhada(){
		if (informacaoAtendimento != null) {
			informacaoAtendimento
					.validarExistenciaParticipacaDetalhada();
			adicionarErros();
		}
	}
	
	protected void validarInformacaoAtendimento() {
		if (informacaoAtendimento != null) {
			informacaoAtendimento
					.validarObrigatoriedadeETamanhoMaximoDeDados();
			adicionarErros();
		}
	}

	private void adicionarErros() {
		if (!informacaoAtendimento.validado()) {
			adicionarErro(informacaoAtendimento.obterErros());
		}
	}	
	
	public boolean possuiFrequenciaFP(){
		return informacaoAtendimento.estaComFrenquenciaFP();
	}

	public boolean possuiFrequenciaFUouFJ(){
		return informacaoAtendimento.estaComFrenquenciaFUouFJ();
	}
	
	public boolean possuiFrequenciaAT(){
		return informacaoAtendimento.estaComFrenquenciaAT();
	}
}

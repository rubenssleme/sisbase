package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@Table(name = "atendimento_profissional")
public class AtendimentoProfissional extends AtendimentoAuxiliarBase implements
		ValidavelObrigatoriedadeETamanhoMaximo{

	@ManyToOne
	@JoinColumn(name = "id_profissional", nullable = false)
	private Profissional profissional;
	
	@Transient
	private boolean apenasParticipante;
	
	public AtendimentoProfissional() {
		informacaoAtendimento = new InformacaoAtendimento();
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public boolean isApenasParticipante() {
		return apenasParticipante;
	}

	public void setApenasParticipante(boolean apenasParticipante) {
		this.apenasParticipante = apenasParticipante;
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();	
		validarInformacaoAtendimento();
	}

	private void validarObrigatoriedade() {
		if (profissional == null) {
			adicionarErro("Insira o Profissional. ");
		}
	}

	public static final Comparator<AtendimentoProfissional> obterComparador() {
		return new Comparator<AtendimentoProfissional>() {
			public int compare(AtendimentoProfissional o1,
					AtendimentoProfissional o2) {
				return o1.getProfissional().getNome()
						.compareTo(o2.getProfissional().getNome());
			}
		};
	}
	
	@Override
	public String toString() {
		return "AtendimentoProfissional [id=" + id + ", profissional="
				+ profissional + ", informacaoAtendimento="
				+ informacaoAtendimento + "]";
	}
}

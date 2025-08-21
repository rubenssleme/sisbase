package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.ModuloRelacaoBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "modulo_usuario")
public class ModuloUsuario extends ModuloRelacaoBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name = "ignorar_regra_reuniao_de_integracao")
	private boolean ignorarRegraDeReuniaoDeIntegracao;
	
	public ModuloUsuario() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void setIgnorarRegraDeReuniaoDeIntegracao(boolean ignorarRegraDeReuniaoDeIntegracao) {
		this.ignorarRegraDeReuniaoDeIntegracao = ignorarRegraDeReuniaoDeIntegracao;
	}

	public boolean isIgnorarRegraDeReuniaoDeIntegracao() {
		return this.ignorarRegraDeReuniaoDeIntegracao;
	}

	public boolean possuiId() {
		return id != null;
	}
	
	public boolean possuiUsuario() {
		return usuario != null;
	}

	public boolean possuiStatusOuDataOcorrenciaDiferente(ModuloUsuario moduloUsuarioOriginal) {
		if (!status.equals(moduloUsuarioOriginal.getStatus()) || (dataOcorrencia != null
				&& !DataHoraUtils.dataIgual(dataOcorrencia, moduloUsuarioOriginal.getDataOcorrenciaCalendario()))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void propagar(ModuloPeriodo moduloPeriodoReponsavelPelaPropagacao, ModuloUsuario moduloUsuarioReferenciaParaPropagacao){
		adicionarObs("\n-----------------------------\n" + "Alteração do status do usuário realizada automaticamento no dia "
				+ DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual()) + ". Date de ocorrência e status anteriores: "
				+ DataHoraUtils.formatarData(dataOcorrencia) + ", " + getStatus().toString() + ". " 
				+ "Acionado por mudança de status do usuário no módulo " + moduloPeriodoReponsavelPelaPropagacao.getModulo().getNome() + " deste grupo.");
		setStatus(moduloUsuarioReferenciaParaPropagacao.getStatus());
		setDataOcorrencia(moduloUsuarioReferenciaParaPropagacao.getDataOcorrencia());
	}
	
	public void desligar(Grupo grupoOrigemDesligamento, ModuloPeriodo moduloPeriodoOrigemDesligamento,
			String dataOcorrenciaOrigem, String obs) {
		adicionarObs("\n-----------------------------\n" + "Desligamento efetuado automaticamente pelo sistema no dia "
				+ DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual()) + ". Date de ocorrência e status anteriores: "
				+ DataHoraUtils.formatarData(dataOcorrencia) + ", " + getStatus().toString() + ". " 
				+ "Acionado pelo " + grupoOrigemDesligamento.obterNomeGrupoETurma() + ", módulo "
				+ moduloPeriodoOrigemDesligamento.getModulo().getNome() + "\nOBS: " + obs);
		setStatus(StatusRelacaoComModulo.DESLIGADO);
		dataOcorrencia = DataHoraUtils.criar(dataOcorrenciaOrigem);
	}

	public void atualizarStatusApartirDeStatusIntegradoOuProvisorioOuAcesso(
			ModuloUsuario moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso) {
		if (moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.estaIntegrado()) {
			setStatus(StatusRelacaoComModulo.INTEGRADO);
		} else if (moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.estaProvisorio()) {
			setStatus(StatusRelacaoComModulo.PROVISORIO);
		} else if (moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.estaAcesso()) {
			setStatus(StatusRelacaoComModulo.INTEGRADO);
		}
		setDataInicio(moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getDataInicio());
		setDataOcorrencia(moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getDataOcorrencia());
		
		String obs = "Status alterado automaticamente pelo sistema. \nObs do modulo original: \n"
				+ moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getObs();
		if (!moduloUsuarioComStatusIntegradoOuProvisorioOuAcesso.getObs().isEmpty()
				&& (this.obs != null && !this.obs.contains(obs) || this.obs == null)) {
			adicionarObs(obs);
		}
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}

	protected void validarObrigatoriedade() {
		if (usuario == null) {
			adicionarErro("Insira o Usuário.");
		}
		if (status == null){
			adicionarErro("Insira um Status.");
		}
		super.validarObrigatoriedade();
	}

	public static final Comparator<ModuloUsuario> obterComparador() {
		return new Comparator<ModuloUsuario>() {
			public int compare(ModuloUsuario o1, ModuloUsuario o2) {
				return o1
						.getUsuario()
						.getInformacaoEssencial()
						.getNome()
						.compareTo(
								o2.getUsuario().getInformacaoEssencial()
										.getNome());
			}
		};
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModuloUsuario other = (ModuloUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ModuloUsuario [id=" + id + ", usuario=" + usuario
				+ ", dataInicio=" + DataHoraUtils.formatarData(dataInicio)
				+ ", dataOcorrencia="
				+ DataHoraUtils.formatarData(dataOcorrencia) + ", status="
				+ status + ", aprovado=" + aprovado + ", obs=" + obs + ", regraDeReuniaoDeIntegracaoJaProcessada = " + ignorarRegraDeReuniaoDeIntegracao + "]";
	}
}
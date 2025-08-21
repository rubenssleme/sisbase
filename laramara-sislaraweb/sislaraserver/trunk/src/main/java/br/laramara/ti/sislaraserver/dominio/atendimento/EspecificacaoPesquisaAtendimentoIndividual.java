package br.laramara.ti.sislaraserver.dominio.atendimento;

import br.laramara.ti.sislaraserver.dominio.EspecificacaoPesquisaBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

public class EspecificacaoPesquisaAtendimentoIndividual extends
		EspecificacaoPesquisaBase implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	private Profissional profissional;
	private PreCadastro preCadastro;
	private boolean possuiIntegracao;
	
	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}
	
	public PreCadastro getPreCadastro(){
		return preCadastro;
	}
	
	public boolean possuiPreCadastro(){
		return preCadastro != null;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public boolean possuiProfissional() {
		return profissional != null;
	}
	
	public boolean possuiIntegracao() {
		return possuiIntegracao;
	}

	public void setPossuiIntegracao(boolean possuiIntegracao) {
		this.possuiIntegracao = possuiIntegracao;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (possuiTodosItensBaseNulos() && profissional == null && preCadastro == null) {
			adicionarErro("Insira um parâmetro para pesquisa.");
		}
		super.validarObrigatoriedadeETamanhoMaximoDeDados();
	}

	@Override
	public String toString() {
		return "EspecificacaoPesquisaEsperaIndividual [profissional="
				+ profissional + ", " + super.toString() + ", possuiIntegracao=" + possuiIntegracao + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.agenda;

import br.laramara.ti.sislaraserver.dominio.EspecificacaoPesquisaBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public class EspecificacaoPesquisaAgendamento extends EspecificacaoPesquisaBase
		implements ValidavelObrigatoriedadeETamanhoMaximo {

	private Profissional profissional;
	private StatusAgendamento statusAgendamento;
	private boolean dataFutura;

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public boolean possuiProfissional() {
		return profissional != null;
	}

	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	public boolean possuiStatusAgendamento() {
		return statusAgendamento != null;
	}
	

	public void setDataFutura(boolean dataFutura){
		this.dataFutura = dataFutura;
	}
	
	public boolean somenteDataFutura() {
		return dataFutura;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (possuiTodosItensBaseNulos() && profissional == null
				&& statusAgendamento == null) {
			adicionarErro("Insira um parâmetro para pesquisa.");
		}
		super.validarObrigatoriedadeETamanhoMaximoDeDados();
	}

	@Override
	public String toString() {
		return "EspecificacaoPesquisaAgendamento [profissional=" + profissional
				+ ", dataFutura=" + dataFutura + ", statusAgendamento=" + statusAgendamento + ", "
				+ super.toString() + "]";
	}
}

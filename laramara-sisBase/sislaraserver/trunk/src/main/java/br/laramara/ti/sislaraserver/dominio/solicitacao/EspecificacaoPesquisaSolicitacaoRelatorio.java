package br.laramara.ti.sislaraserver.dominio.solicitacao;

import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraserver.dominio.EspecificacaoPesquisaBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;

public class EspecificacaoPesquisaSolicitacaoRelatorio extends
		EspecificacaoPesquisaBase implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	protected StatusSolicitacaoRelatorio status;
	protected Long protocolo;

	public Long getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = NumeroUtils.retornaLongoOuInvalido(protocolo);
	}

	public boolean possuiProtocolo() {
		return protocolo != null;
	}

	public boolean possuiStatus() {
		return status != null;
	}

	public StatusSolicitacaoRelatorio getStatus() {
		return status;
	}

	public void setStatus(StatusSolicitacaoRelatorio status) {
		this.status = status;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (protocolo == null && prontuario == null && status == null) {
			adicionarErro("Insira um dados para pesquisa.");
		}
	}
}

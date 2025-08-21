package br.laramara.ti.sislaracommons.dtos.solicitacao;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;

public class EspecificacaoPesquisaSolicitacaoRelatorioDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 4153187596713188405L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.PRONTUARIO);
		adicionar(ChavePesquisaDTO.PROTOCOLO);
		adicionar(ChavePesquisaDTO.STATUS_SOLICITACAO_RELATORIO);
	}

	public String getProntuario() {
		return (String) obterParametro(ChavePesquisaDTO.PRONTUARIO);
	}

	public void setProntuario(String prontuario) {
		adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario);
	}

	public String getProtocolo() {
		return (String) obterParametro(ChavePesquisaDTO.PROTOCOLO);
	}

	public void setProtocolo(String protocolo) {
		adicionarParametro(ChavePesquisaDTO.PROTOCOLO, protocolo);
	}

	public StatusSolicitacaoRelatorioDTO getStatusSolicitacaoRelatorio() {
		return (StatusSolicitacaoRelatorioDTO) obterParametro(ChavePesquisaDTO.STATUS_SOLICITACAO_RELATORIO);
	}

	public void setStatusSolicitacaoRelatorio(
			StatusSolicitacaoRelatorioDTO statusSolicitacaoRelatorio) {
		adicionarParametro(ChavePesquisaDTO.STATUS_SOLICITACAO_RELATORIO,
				statusSolicitacaoRelatorio);
	}
}

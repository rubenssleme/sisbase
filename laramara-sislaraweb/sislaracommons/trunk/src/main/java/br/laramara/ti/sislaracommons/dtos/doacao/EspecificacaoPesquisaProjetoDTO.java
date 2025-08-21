package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;



public class EspecificacaoPesquisaProjetoDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = -4945223322250908652L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.PROJETO);
	}
	
	public String getProjeto() {
		return (String) obterParametro(ChavePesquisaDTO.PROJETO);
	}

	public void setProjeto(String projeto) {
		adicionarParametro(ChavePesquisaDTO.PROJETO, projeto);
	}
}
	



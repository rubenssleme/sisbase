package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;

public class ResultadoEdicaoContaAcessoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(ContaAcessoDTO objetoDtoEditado) {
		efetuadoComSucesso(
				DADOS_ARMAZENADOS_COM_SUCESSO ,
				objetoDtoEditado);
	}
}

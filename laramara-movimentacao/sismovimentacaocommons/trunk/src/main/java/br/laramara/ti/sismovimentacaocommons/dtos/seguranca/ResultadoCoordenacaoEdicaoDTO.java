package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;

public class ResultadoCoordenacaoEdicaoDTO extends ResultadoDTO {

	private static final long serialVersionUID = -5665870994550068802L;

	public void efetuadoComSucesso(ContaAcessoDTO contaAcessoDTO) {
		efetuadoComSucesso("O item está sendo editado pelo(a): "
				+ contaAcessoDTO.getLogin(), contaAcessoDTO);
	}
}

package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoAcaoCondutaDTO extends ResultadoDTO {

	private static final long serialVersionUID = 4438233700108387388L;

	public void efetuadoComSucesso(AcaoCondutaDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}

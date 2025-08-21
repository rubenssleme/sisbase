package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoParticipacaoDetalhadaDTO extends ResultadoDTO {

	private static final long serialVersionUID = 8504607579375038571L;

	public void efetuadoComSucesso(ParticipacaoDetalhadaDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}

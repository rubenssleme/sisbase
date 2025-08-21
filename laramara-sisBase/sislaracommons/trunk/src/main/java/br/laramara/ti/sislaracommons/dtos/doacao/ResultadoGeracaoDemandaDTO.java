package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;

public class ResultadoGeracaoDemandaDTO extends
		ResultadoListagemDTO<DemandaDTO> {

	private static final long serialVersionUID = 4184413985508218936L;

	private static final String SUCESSO = "Geração de demanda realizada com sucesso.";

	public void efetuadoComSucesso(List<DemandaDTO> objetosDtoGerados) {
		efetuadoComSucesso(SUCESSO, objetosDtoGerados);
	}
}

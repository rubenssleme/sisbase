package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;

public class ResultadoGeracaoAgendamentoDTO extends
		ResultadoListagemDTO<AgendamentoDTO> {

	private static final long serialVersionUID = 4184413985508218936L;

	private static final String SUCESSO = "Geração de agendamento realizada com sucesso.";

	public void efetuadoComSucesso(List<AgendamentoDTO> objetosDtoGerados) {
		efetuadoComSucesso(SUCESSO, objetosDtoGerados);
	}

	public void efetuadoComSucesso(AgendamentoDTO objetoDtoGerado) {
		List<AgendamentoDTO> agendamentosDto = new ArrayList<>();
		agendamentosDto.add(objetoDtoGerado);
		efetuadoComSucesso(SUCESSO, agendamentosDto);
	}
}

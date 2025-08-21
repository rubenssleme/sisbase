package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.RecursoRelacionamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PossuiNecessitaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.RecursoRelacionamento;
import br.laramara.ti.sislaraserver.dominio.usuario.PossuiNecessita;

public class ContextoRecursoRelacionamento {

	public static RecursoRelacionamentoDTO construirRecursoRelacionamentoDTO() {
		RecursoRelacionamentoDTO recursoRelacionamentoDTO = new RecursoRelacionamentoDTO();
		recursoRelacionamentoDTO.setRecursoDto(ContextoRecurso.construirRecursoDTO());
		recursoRelacionamentoDTO.setPossuiNecessitaDto(new PossuiNecessitaDTO(PossuiNecessita.NECESSITA.toString()));
		return recursoRelacionamentoDTO;
	}

	public static RecursoRelacionamento construirRecursoRelacionamento() {
		RecursoRelacionamento recursoRelacionamento = new RecursoRelacionamento();
		recursoRelacionamento.setRecurso(ContextoRecurso.fabricarRecursoAlternativoComTodosOsDados());
		recursoRelacionamento.setPossuiNecessita(PossuiNecessita.NECESSITA);
		return recursoRelacionamento;
	}
}

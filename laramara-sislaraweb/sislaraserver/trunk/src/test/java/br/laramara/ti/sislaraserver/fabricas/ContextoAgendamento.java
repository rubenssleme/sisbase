package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.StatusAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;

public class ContextoAgendamento {
	public static Agendamento fabricarAgendamentoComTodosOsDados() {
		Agendamento agendamento = new Agendamento();
		agendamento.setId(new Long(12222));
		agendamento.setData("31/12/2012");
		agendamento.setHorario(new Horario("09:00", "10:00"));
		agendamento.setProfissional(ContextoProfissional
				.fabricarComTodosOsDados());
		agendamento.setSetor(Setor.CTO);
		agendamento.setReservaPara(Status.CASO_NOVO);
		agendamento.setLocalAtendimento(ContextoLocalAtendimento
				.fabricarComTodosOsDados());
		agendamento
				.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento
						.fabricarComTodosOsDados());
		agendamento.setModulo(ContextoModulo.fabricarComTodosOsDados());
		agendamento.setObs("Grande texto de observação");
		agendamento.inicializarStatus(ContextoContaAcesso.fabricarComTodosOsDados());
		return agendamento;
	}

	public static AgendamentoDTO construirAgendamentoDTO() {
		AgendamentoDTO agendamentoDto = new AgendamentoDTO();
		agendamentoDto.setData("31/12/2012");
		agendamentoDto.setHorarioDto(new HorarioDTO("09:00", "12:00"));
		agendamentoDto.setProfissionalDto(ContextoProfissional
				.construirProfissionalDTO());
		agendamentoDto.setStatusDto(new StatusAgendamentoDTO("AGENDADO"));
		agendamentoDto.setSetorDto(new SetorDTO("CTO"));
		agendamentoDto.setReservaParaDto(new StatusDTO("CASO_NOVO"));
		agendamentoDto.setLocalAtendimentoDto(ContextoLocalAtendimento
				.construirLocalAtendimentoDTO());
		agendamentoDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento
						.construirDescricaoTipoAtendimentoDTO());
		agendamentoDto.setModuloDto(ContextoModulo.construirModuloDTO());
		agendamentoDto.setObs("Grande texto de observação");
		return agendamentoDto;
	}
}

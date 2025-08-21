package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoAgendamento;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;

public class ContextoEspecificacaoGeracaoAgendamento {

	public static EspecificacaoGeracaoAgendamento fabricarComTodosOsDados() {
		EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento = new EspecificacaoGeracaoAgendamento();
		especificacaoGeracaoAgendamento.setUsuario(ContextoUsuario
				.fabricarUsuarioComTodosOsDados());
		List<Profissional> profissionais = new ArrayList<>();
		profissionais.add(ContextoProfissional.fabricarComTodosOsDados());
		especificacaoGeracaoAgendamento.setProfissionais(profissionais);
		especificacaoGeracaoAgendamento
				.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento
						.fabricarComTodosOsDados());
		especificacaoGeracaoAgendamento.setSetor(Setor.CTO);
		especificacaoGeracaoAgendamento
				.setLocalAtendimento(ContextoLocalAtendimento
						.fabricarComTodosOsDados());
		especificacaoGeracaoAgendamento.setDataInicio("31/01/2012");
		especificacaoGeracaoAgendamento.setDataTermino("01/07/2012");
		especificacaoGeracaoAgendamento.setDiaSemana(DiaSemana.SEGUNDA);
		especificacaoGeracaoAgendamento.setHorario(new Horario("09:00", "11:00"));
		especificacaoGeracaoAgendamento.setReservaStatus(Status.CASO_NOVO);
		return especificacaoGeracaoAgendamento;
	}

	public static EspecificacaoGeracaoAgendamentoDTO fabricarAgendamentoUnicoComTodosOsDados() {
		UsuarioDTO usuarioDto = ContextoUsuario.construirUsuarioDTO();
		usuarioDto.setId(new Long(12222));
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = new EspecificacaoGeracaoAgendamentoDTO();
		especificacaoGeracaoAgendamentoDto.setUsuarioDto(usuarioDto);
		List<ProfissionalDTO> profissionalDTOs = new ArrayList<>();
		profissionalDTOs.add(ContextoProfissional.construirProfissionalDTO());
		especificacaoGeracaoAgendamentoDto
				.setProfissionaisDto(profissionalDTOs);
		especificacaoGeracaoAgendamentoDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento
						.construirDescricaoTipoAtendimentoDTO());
		especificacaoGeracaoAgendamentoDto.setModuloDto(ContextoModulo.construirModuloDTO());
		especificacaoGeracaoAgendamentoDto.setSetorDto(new SetorDTO("CTO"));
		especificacaoGeracaoAgendamentoDto
				.setLocalAtendimentoDto(ContextoLocalAtendimento
						.construirLocalAtendimentoDTO());
		especificacaoGeracaoAgendamentoDto.setDataInicio("31/01/2112");
		especificacaoGeracaoAgendamentoDto.setHorarioDto(new HorarioDTO("09:00", "11:00"));
		especificacaoGeracaoAgendamentoDto.setReservaStatusDto(new StatusDTO(
				"RETORNO"));
		return especificacaoGeracaoAgendamentoDto;
	}
	
	public static EspecificacaoGeracaoAgendamentoDTO fabricarAgendamentoMultiploComTodosOsDados() {
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDto.getProfissionaisDto().add(
				ContextoProfissional.construirProfissionalDTOAlternativo());
		especificacaoGeracaoAgendamentoDto.setDataTermino("29/02/2012");
		especificacaoGeracaoAgendamentoDto.setDiaSemanaDto(new DiaSemanaDTO(
				DiaSemana.SEGUNDA.toString()));
		return especificacaoGeracaoAgendamentoDto;
	}
	
	public static EspecificacaoGeracaoAgendamentoDTO fabricarAgendamentoMultiploComConflito() {
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDto.setDiaSemanaDto(new DiaSemanaDTO(
				DiaSemana.TERÇA.toString()));

		especificacaoGeracaoAgendamentoDto.setDataInicio("01/01/2112");
		especificacaoGeracaoAgendamentoDto.setDataTermino("31/01/2112");
		
		return especificacaoGeracaoAgendamentoDto;
	}
	
	public static EspecificacaoGeracaoAgendamentoDTO fabricarAgendamentoMultiplo() {
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDto.setDiaSemanaDto(new DiaSemanaDTO(
				DiaSemana.TERÇA.toString()));

		especificacaoGeracaoAgendamentoDto.setDataInicio("01/01/2100");
		especificacaoGeracaoAgendamentoDto.setDataTermino("31/01/2100");
		
		return especificacaoGeracaoAgendamentoDto;
	}
}

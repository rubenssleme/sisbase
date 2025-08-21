package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;

public class ContextoAtendimentoProfissional {

	public static AtendimentoProfissional fabricarAtendimentoProfissionalComTodosOsDados() {
		AtendimentoProfissional atendimentoProfissional = new AtendimentoProfissional();
		atendimentoProfissional.setProfissional(ContextoProfissional
				.fabricarComTodosOsDados());
		atendimentoProfissional
				.setInformacaoAtendimento(ContextoInformacaoAtendimento
						.fabricarInformacaoAtendimentoComTodosOsDados());
		return atendimentoProfissional;
	}
	
	public static AtendimentoProfissional fabricarAtendimentoProfissionalComATComTodosOsDados() {
		AtendimentoProfissional atendimentoProfissional = fabricarAtendimentoProfissionalComTodosOsDados();
		atendimentoProfissional.getInformacaoAtendimento().setFrequencia(Frequencia.AT);
		return atendimentoProfissional;
	}

	public static AtendimentoProfissionalDTO construirAtendimentoProfissionalDTO() {
		AtendimentoProfissionalDTO atendimentoProfissionalDTO = new AtendimentoProfissionalDTO();
		atendimentoProfissionalDTO.setHorarioDto(new HorarioDTO("09:00", "10:00"));
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional
				.construirProfissionalDTO());
		atendimentoProfissionalDTO
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento
						.construirInformacaoAtendimentoDTO());
		return atendimentoProfissionalDTO;
	}
	
	public static AtendimentoProfissionalDTO construirAtendimentoProfissionalDTOComAT() {
		AtendimentoProfissionalDTO atendimentoProfissionalDTO = new AtendimentoProfissionalDTO();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional
				.construirProfissionalDTOComId3000());
		atendimentoProfissionalDTO
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento
						.construirInformacaoAtendimentoComATDTO());
		return atendimentoProfissionalDTO;
	}
	
	public static AtendimentoProfissionalDTO construirAtendimentoProfissional1000DTOComAT() {
		AtendimentoProfissionalDTO atendimentoProfissionalDTO = new AtendimentoProfissionalDTO();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional
				.construirProfissionalDTO());
		atendimentoProfissionalDTO
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento
						.construirInformacaoAtendimentoComATDTO());
		return atendimentoProfissionalDTO;
	}
}

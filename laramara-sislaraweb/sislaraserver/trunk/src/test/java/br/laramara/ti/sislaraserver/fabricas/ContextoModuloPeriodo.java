package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPreCadastro;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.grupo.ProfissionalVinculo;
import br.laramara.ti.sislaraserver.dominio.grupo.Programacao;

public class ContextoModuloPeriodo {

	public static ModuloPeriodo fabricarComTodosOsDadosInformatica() {
		ModuloPeriodo moduloPeriodo = gerarModuloPeriodo();
		moduloPeriodo.setId(new Long(11111));
		moduloPeriodo.setModulo(new Modulo(new Long(1), "Informatica"));
		moduloPeriodo.setDiasSemanaEHorariosDaAtividade(ContextoDiasSemanaEHorarios.fabricarComTodosOsDados());
		return moduloPeriodo;
	}
	
	public static ModuloPeriodoDTO fabricarModuloPeriodoDTOComTodosOsDadosBraille() {
		ModuloPeriodoDTO moduloPeriodoBrailleSemAtendimentoGrupoDto = fabricarModuloPeriodoDTO();
		
		moduloPeriodoBrailleSemAtendimentoGrupoDto.getAtendimentosGrupoDto().clear();
		
		moduloPeriodoBrailleSemAtendimentoGrupoDto.setModuloDto(new ModuloDTO(new Long(64), "Braille"));
		moduloPeriodoBrailleSemAtendimentoGrupoDto.setDiasSemanaEHorariosDaAtividadeDto(
				Arrays.asList(ContextoDiasSemanaEHorarios.construirDiaSemanaEHorarioDTO()));

		return moduloPeriodoBrailleSemAtendimentoGrupoDto;
	}
	
	public static ModuloPeriodo fabricarModuloPeriodoComTodosOsDadosBraille() {
		ModuloPeriodo moduloPeriodoBrailleSemAtendimentoGrupo = gerarModuloPeriodo();

		moduloPeriodoBrailleSemAtendimentoGrupo.setModulo(new Modulo(new Long(64), "Braille"));
		moduloPeriodoBrailleSemAtendimentoGrupo
				.setDiasSemanaEHorariosDaAtividade(ContextoDiasSemanaEHorarios.fabricarComTodosOsDados());

		return moduloPeriodoBrailleSemAtendimentoGrupo;
	}

	public static ModuloPeriodo fabricarComTodosOsDadosIngles() {
		ModuloPeriodo moduloPeriodo = gerarModuloPeriodo();
		moduloPeriodo.setId(new Long(22222));
		moduloPeriodo.setModulo(new Modulo(new Long(2), "Ingles"));
		moduloPeriodo.setDiasSemanaEHorariosDaAtividade(ContextoDiasSemanaEHorarios.fabricarComTodosOsDados());
		
		List<AtendimentoGrupo> atendimentos = new ArrayList<>();
		atendimentos.add(ContextoAtendimentoGrupo.fabricarAtendimentoComTodosOsDados());
		moduloPeriodo.setAtendimentosGrupo(atendimentos);
		return moduloPeriodo;
	}

	private static ModuloPeriodo gerarModuloPeriodo() {
		ModuloPeriodo moduloPeriodo = new ModuloPeriodo();
		List<Profissional> profissionais = new ArrayList<>();
		profissionais.add(ContextoProfissional.fabricarComTodosOsDados());
		List<ProfissionalVinculo> profissionalVinculo = new ArrayList<>();
		profissionalVinculo.add(ContextoProfissionalVinculo.fabricarComTodosOsDados());
		moduloPeriodo.setProfissionaisVinculo(profissionalVinculo);
		moduloPeriodo.setLocalAtendimento(ContextoLocalAtendimento
				.fabricarComTodosOsDados());
		moduloPeriodo.setVagas("30");
		moduloPeriodo.setCargaHoraria("244:00");
		moduloPeriodo.setCargaHorariaMinima("050:00");
		List<Programacao> programacoes = new ArrayList<>();
		programacoes.add(ContextoProgramacao
				.fabricarProgramacaoComTodosOsDados());
		moduloPeriodo.setProgramacoes(programacoes);
		
		
		List<ModuloUsuario> modulosUsuarios = new ArrayList<>();
		modulosUsuarios.add(ContextoModuloUsuario.fabricarComTodosOsDados());
		ModuloUsuario moduloUsuario = ContextoModuloUsuario
		.fabricarComTodosOsDados();
		moduloUsuario.setUsuario(ContextoUsuario
				.fabricaUsuarioComTodosOsDadosEProntuarioAlternativo());
		modulosUsuarios.add(moduloUsuario);
		
		List<ModuloPreCadastro> modulosPreCadastros = new ArrayList<>();
		modulosPreCadastros.add(ContextoModuloPreCadastro.fabricarComTodosOsDados());
				
		moduloPeriodo.setModulosUsuarios(modulosUsuarios);
		moduloPeriodo.setModulosPreCadastro(modulosPreCadastros);
		return moduloPeriodo;
	}

	public static ModuloPeriodoDTO fabricarModuloPeriodoDTO() {
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto
				.setModuloDto(new ModuloDTO(new Long(1), "Informatica"));
		List<DiaSemanaEHorarioDTO> diasSemanaEHorariosDTOs = new ArrayList<>();
		diasSemanaEHorariosDTOs.add(ContextoDiasSemanaEHorarios.construirDiaSemanaEHorarioDTO());
		moduloPeriodoDto.setDiasSemanaEHorariosDaAtividadeDto(diasSemanaEHorariosDTOs);
		List<ProfissionalVinculoDTO> profissionaisVinculoDto = new ArrayList<>();
		profissionaisVinculoDto.add(ContextoProfissionalVinculo.construirVinculoProfissionalDTO());
		moduloPeriodoDto.setProfissionaisVinculoDto(profissionaisVinculoDto);
		moduloPeriodoDto.setLocalAtendimentoDTO(ContextoLocalAtendimento
				.construirLocalAtendimentoDTO());
		moduloPeriodoDto.setVagas("30");
		moduloPeriodoDto.setCargaHoraria("244:00");
		List<ProgramacaoDTO> programacoesDto = new ArrayList<>();
		programacoesDto.add(ContextoProgramacao.construirProgramacaoDTO());
		moduloPeriodoDto.setProgramacaoDto(programacoesDto);

		List<AtendimentoGrupoDTO> atendimentosDto = new ArrayList<>();
		atendimentosDto.add(ContextoAtendimentoGrupo.construirAtendimentoDTO());
		moduloPeriodoDto.setAtendimentosGrupoDto(atendimentosDto);
		
		return moduloPeriodoDto;
	}
}

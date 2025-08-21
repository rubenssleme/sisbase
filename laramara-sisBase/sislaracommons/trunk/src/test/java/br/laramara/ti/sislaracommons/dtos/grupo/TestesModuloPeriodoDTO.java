package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesModuloPeriodoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiododto_foi_construida_com_sucesso() {

		Long id = new Long(12222);
		ModuloDTO moduloDto = new ModuloDTO(new Long(1), "Informática");
		DiaSemanaDTO diaSemanaDto = new DiaSemanaDTO("SEGUNDA");
		DiaSemanaEHorarioDTO diaSemanaEHorarioDTO = new DiaSemanaEHorarioDTO();
		diaSemanaEHorarioDTO.setDiaSemanaDto(diaSemanaDto);
		String horaInicio = "09:00";
		String horaTermino = "14:00";
		diaSemanaEHorarioDTO.setHorarioDto(new HorarioDTO(horaInicio, horaTermino));
		List<DiaSemanaEHorarioDTO> diasSemanaEHorariosDTO = new ArrayList<>();
		diasSemanaEHorariosDTO.add(diaSemanaEHorarioDTO);
		
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(1000), "Paulo",
				"12233");
		List<ProfissionalDTO> profissionaisDto = new ArrayList<>();
		profissionaisDto.add(profissionalDTO);
		List<ProfissionalVinculoDTO> profissionaisVinculoDto = new ArrayList<>();
		ProfissionalVinculoDTO profissionalVinculoDTO = new ProfissionalVinculoDTO();
		profissionalVinculoDTO.setProfissionalDto(profissionalDTO);
		profissionalVinculoDTO.setParticipante(false);
		profissionaisVinculoDto.add(profissionalVinculoDTO);
		LocalAtendimentoDTO localAtendimentoDto = new LocalAtendimentoDTO(
				new Long(1), "SALA 1");
		String vagas = "33";
		String cargaHoraria = "1";
		String cargaHorariaMinima = "1";
		List<ProgramacaoDTO> programacoesDto = new ArrayList<>();
		programacoesDto.add(new ProgramacaoDTO());
		List<AtendimentoGrupoDTO> atendimentosDto = new ArrayList<>();
		atendimentosDto.add(new AtendimentoGrupoDTO());
		List<ModuloUsuarioDTO> modulosUsuariosDto = new ArrayList<>();
		modulosUsuariosDto.add(new ModuloUsuarioDTO());
		List<ModuloPreCadastroDTO> modulosPreCadastroDto = new ArrayList<>();
		modulosPreCadastroDto.add(new ModuloPreCadastroDTO());

		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(id);
		moduloPeriodoDto.setModuloDto(moduloDto);
		moduloPeriodoDto.setDiasSemanaEHorariosDaAtividadeDto(diasSemanaEHorariosDTO);
		moduloPeriodoDto.setProfissionaisDto(profissionaisDto);
		moduloPeriodoDto.setProfissionaisVinculoDto(profissionaisVinculoDto);
		moduloPeriodoDto.setLocalAtendimentoDTO(localAtendimentoDto);
		moduloPeriodoDto.setVagas(vagas);
		moduloPeriodoDto.setCargaHoraria(cargaHoraria);
		moduloPeriodoDto.setCargaHorariaMinima(cargaHorariaMinima);
		moduloPeriodoDto.setProgramacaoDto(programacoesDto);
		moduloPeriodoDto.setAtendimentosGrupoDto(atendimentosDto);
		moduloPeriodoDto.setModulosUsuariosDto(modulosUsuariosDto);
		moduloPeriodoDto.setModulosPreCadastroDto(modulosPreCadastroDto);

		Assert.assertEquals(moduloPeriodoDto.getId(), id);
		Assert.assertEquals(moduloPeriodoDto.getModuloDto().getId(),
				moduloDto.getId());
		Assert.assertEquals(moduloPeriodoDto.getDiasSemanaEHorariosDaAtividadeDto().size(), 1);
		Assert.assertEquals(moduloPeriodoDto.getProfissionaisVinculoDto().size(), 1);
		Assert.assertEquals(moduloPeriodoDto.getLocalAtendimentoDTO(),
				localAtendimentoDto);
		Assert.assertEquals(moduloPeriodoDto.getVagas(), vagas);
		Assert.assertEquals(moduloPeriodoDto.getCargaHoraria().toString(),
				cargaHoraria);
		Assert.assertEquals(moduloPeriodoDto.getCargaHorariaMinima().toString(),
				cargaHorariaMinima);
		Assert.assertEquals(moduloPeriodoDto.getProgramacaoDto().size(),
				programacoesDto.size());
		Assert.assertEquals(moduloPeriodoDto.getAtendimentosGrupoDto().size(),
				atendimentosDto.size());
		Assert.assertEquals(moduloPeriodoDto.getModulosUsuariosDto().size(), 1);
		Assert.assertEquals(moduloPeriodoDto.getModulosPreCadastroDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void moduloperiododto_obtem_atendimento() {
		AtendimentoGrupoDTO atendimentoDTO1 = new AtendimentoGrupoDTO();
		atendimentoDTO1.setId(new Long(1222));
		AtendimentoGrupoDTO atendimentoDTO2 = new AtendimentoGrupoDTO();
		atendimentoDTO2.setId(new Long(1333));
		List<AtendimentoGrupoDTO> atendimentosDtos = new ArrayList<>();
		atendimentosDtos.add(atendimentoDTO1);
		atendimentosDtos.add(atendimentoDTO2);

		ModuloPeriodoDTO moduloPeriodoDTO = new ModuloPeriodoDTO();
		moduloPeriodoDTO.setAtendimentosGrupoDto(atendimentosDtos);

		AtendimentoGrupoDTO atendimentoAObterDTO = new AtendimentoGrupoDTO();
		atendimentoAObterDTO.setId(new Long(1222));

		Assert.assertNotNull(moduloPeriodoDTO
				.obterAtendimentoGrupo(atendimentoAObterDTO));
	}
}

package br.laramara.ti.sislaracommons.dtos.agenda;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoGeracaoAgendamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaogeracaoagendamento_com_um_parametro_construida_com_sucesso() {
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(1));
		PreCadastroDTO preCadastroDTO = new PreCadastroDTO();
		preCadastroDTO.setId(new Long(1));
		List<GrupoDTO> gruposDto = new ArrayList<>();
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(3));
		gruposDto.add(grupoDto);
		List<ProfissionalDTO> profissionaisDto = new ArrayList<>();
		ProfissionalDTO profissionalDto = new ProfissionalDTO(new Long(1),
				"Paulo", "1234");
		profissionaisDto.add(profissionalDto);
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(new Long(23));
		ModuloDTO moduloDto = new ModuloDTO(new Long(122), "Informcatica");
		SetorDTO setorDto = new SetorDTO("CTO");
		LocalAtendimentoDTO localAtendimentoDTO = new LocalAtendimentoDTO(
				new Long(12), "SALA 1");
		DiaSemanaDTO diaSemanaDto = new DiaSemanaDTO("SEGUNDA");
		String dataInicio = "31/12/2012";
		String dataTermino = "12/12/2012";
		String horaInicio = "09:00";
		String horaTermino = "12:00";
		StatusDTO statusAReservarDTO = new StatusDTO("RETORNO");
		String obs = "Grande texto de observacao";

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = new EspecificacaoGeracaoAgendamentoDTO();
		especificacaoGeracaoAgendamentoDto.setUsuarioDto(usuarioDto);
		especificacaoGeracaoAgendamentoDto.setPreCadastroDTO(preCadastroDTO);
		especificacaoGeracaoAgendamentoDto.setGruposDTO(gruposDto);
		especificacaoGeracaoAgendamentoDto
				.setProfissionaisDto(profissionaisDto);
		especificacaoGeracaoAgendamentoDto
				.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDto);
		especificacaoGeracaoAgendamentoDto.setModuloDto(moduloDto);
		especificacaoGeracaoAgendamentoDto.setSetorDto(setorDto);
		especificacaoGeracaoAgendamentoDto
				.setLocalAtendimentoDto(localAtendimentoDTO);
		especificacaoGeracaoAgendamentoDto.setDataInicio(dataInicio);
		especificacaoGeracaoAgendamentoDto.setDataTermino(dataTermino);
		especificacaoGeracaoAgendamentoDto.setDiaSemanaDto(diaSemanaDto);
		especificacaoGeracaoAgendamentoDto.setHorarioDto(new HorarioDTO(horaInicio, horaTermino));
		especificacaoGeracaoAgendamentoDto
				.setReservaStatusDto(statusAReservarDTO);
		especificacaoGeracaoAgendamentoDto.setObs(obs);

		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getUsuarioDto()
				.getId(), usuarioDto.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getPreCadastroDTO().getId(), preCadastroDTO.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getGruposDTO()
				.get(0).getId(), grupoDto.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getDescricaoTipoAtendimentoDto().getId(),
				descricaoTipoAtendimentoDto.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getModuloDto().getId(),
				moduloDto.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getSetorDto()
				.toString(), setorDto.toString());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getLocalAtendimentoDto().getId(), localAtendimentoDTO.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getProfissionaisDto().get(0).getId(), profissionalDto.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getDiaSemanaDto().toString(), diaSemanaDto.toString());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getDataInicio(),
				dataInicio);
		Assert.assertEquals(
				especificacaoGeracaoAgendamentoDto.getDataTermino(),
				dataTermino);
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getHorarioDto().getHoraInicio(),
				horaInicio);
		Assert.assertEquals(
				especificacaoGeracaoAgendamentoDto.getHorarioDto().getHoraTermino(),
				horaTermino);
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto
				.getReservaStatusDto().toString(), statusAReservarDTO
				.toString());
		Assert.assertEquals(especificacaoGeracaoAgendamentoDto.getObs(), obs);
	}
}

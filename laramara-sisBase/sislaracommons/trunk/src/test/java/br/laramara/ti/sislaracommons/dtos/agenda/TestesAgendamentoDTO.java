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

public class TestesAgendamentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void agendamentodto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		UsuarioDTO usuarioDto = new UsuarioDTO(id);
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(id);
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(id);
		List<GrupoDTO> gruposDto = new ArrayList<>();
		gruposDto.add(grupoDto);
		ProfissionalDTO profissionalDto = new ProfissionalDTO(id, "Paulo",
				"1234");
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(id);
		ModuloDTO moduloDto = new ModuloDTO(id, "Informatica");
		String data = "31/12/2012";
		String horaInicio = "09:00";
		String horaTermino = "10:00";
		DiaSemanaDTO diaSemanaDto = new DiaSemanaDTO("SEGUNDA");
		StatusAgendamentoDTO status = new StatusAgendamentoDTO("AGENDADO");
		SetorDTO setor = new SetorDTO("CTO");
		StatusDTO reservaParaDto = new StatusDTO("CASO_NOVO");
		LocalAtendimentoDTO localAtendimentoDto = new LocalAtendimentoDTO(
				new Long(1), "Sala 1");
		String obs = "Grande observação";
		MotivoCancelamentoDTO motivoCancelamentoDto = new MotivoCancelamentoDTO(id, "Sem justificativa");
		String justificativaCancelamento = "Justificativa do cancelamento";
		boolean estaDisponivel = true;
		boolean estaCancelado = false;
		boolean estaAgendado = true;
		boolean estaReagendado = true;
		String reponsaveisOperacoes = "Texto das operações";
		String idade = "33";
		HorarioDTO horarioDto = new HorarioDTO(horaInicio, horaTermino);

		AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
		agendamentoDTO.setId(id);
		agendamentoDTO.setUsuarioDto(usuarioDto);
		agendamentoDTO.setPreCadastroDto(preCadastroDto);
		agendamentoDTO.setGruposDto(gruposDto);
		agendamentoDTO.setProfissionalDto(profissionalDto);
		agendamentoDTO
				.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		agendamentoDTO.setModuloDto(moduloDto);
		agendamentoDTO.setData(data);
		agendamentoDTO.setDiaSemanaDto(diaSemanaDto);
		agendamentoDTO.setHorarioDto(horarioDto);
		agendamentoDTO.setStatusDto(status);
		agendamentoDTO.setSetorDto(setor);
		agendamentoDTO.setReservaParaDto(reservaParaDto);
		agendamentoDTO.setLocalAtendimentoDto(localAtendimentoDto);
		agendamentoDTO.setObs(obs);
		agendamentoDTO.setMotivoCancelamentoDTO(motivoCancelamentoDto);
		agendamentoDTO.setJustificativaCancelamento(justificativaCancelamento);
		agendamentoDTO.setEstaDisponivel(estaDisponivel);
		agendamentoDTO.setEstaCancelado(estaCancelado);
		agendamentoDTO.setEstaAgendado(estaAgendado);
		agendamentoDTO.setEstaReagendado(estaReagendado);
		agendamentoDTO.setUsuarioCriadoPeloPreCadastro(usuarioDto);
		agendamentoDTO.setResponsaveisOperacoes(reponsaveisOperacoes);
		agendamentoDTO.setIdadeDoUsuarioOuPreCadastro(idade);

		Assert.assertEquals(agendamentoDTO.getId(), id);
		Assert.assertEquals(agendamentoDTO.getUsuarioDto().getId(), id);
		Assert.assertEquals(agendamentoDTO.getPreCadastroDto().getId(), id);
		Assert.assertEquals(agendamentoDTO.getGruposDto().get(0).getId(), id);
		Assert.assertEquals(agendamentoDTO.getProfissionalDto().getId(), id);
		Assert.assertEquals(agendamentoDTO.getDescricaoTipoAtendimentoDto()
				.getId(), id);
		Assert.assertEquals(agendamentoDTO.getModuloDto().getId(), id);
		Assert.assertEquals(agendamentoDTO.getData(), data);
		Assert.assertEquals(agendamentoDTO.getDiaSemanaDto().toString(),
				diaSemanaDto.toString());
		Assert.assertEquals(agendamentoDTO.getSetorDto().toString(),
				setor.toString());
		Assert.assertEquals(agendamentoDTO.getReservaParaDto().toString(),
				reservaParaDto.toString());
		Assert.assertEquals(agendamentoDTO.getLocalAtendimentoDto().getId(),
				localAtendimentoDto.getId());
		Assert.assertEquals(agendamentoDTO.getObs(), obs);
		Assert.assertEquals(agendamentoDTO.getStatusDto().toString(),
				status.toString());
		Assert.assertEquals(agendamentoDTO.getMotivoCancelamentoDTO()
				.toString(), motivoCancelamentoDto.toString());
		Assert.assertEquals(agendamentoDTO.getJustificativaCancelamento(),
				justificativaCancelamento);
		Assert.assertEquals(agendamentoDTO.isEstaDisponivel(), estaDisponivel);
		Assert.assertEquals(agendamentoDTO.isEstaCancelado(), estaCancelado);
		Assert.assertEquals(agendamentoDTO.isEstaAgendado(), estaAgendado);
		Assert.assertEquals(agendamentoDTO.isEstaReagendado(), estaReagendado);
		Assert.assertEquals(agendamentoDTO.getResponsaveisOperacoes(),
				reponsaveisOperacoes);
		Assert.assertEquals(agendamentoDTO.getHorarioDto().getHoraInicio(), horaInicio);
		Assert.assertEquals(agendamentoDTO.getHorarioDto().getHoraTermino(), horaTermino);
		Assert.assertEquals(agendamentoDTO.getUsuarioCriadoPeloPreCadastro().getId(), usuarioDto.getId());
		Assert.assertEquals(agendamentoDTO.getIdadeDoUsuarioOuPreCadastro(), idade);
	}
}

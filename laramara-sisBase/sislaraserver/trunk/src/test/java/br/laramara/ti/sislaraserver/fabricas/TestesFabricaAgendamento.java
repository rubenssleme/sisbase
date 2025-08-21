package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.agenda.MotivoCancelamento;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public class TestesFabricaAgendamento {
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB})
	public void fabrica_agendamento_converte_objeto_de_dominio_para_dto() {
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		List<Grupo> grupos = new ArrayList<>();
		Grupo grupo = ContextoGrupo.fabricarGrupoComTodosOsDados();
		grupos.add(grupo);
		
		Agendamento agendamento = ContextoAgendamento
				.fabricarAgendamentoComTodosOsDados();
		agendamento.setUsuario(usuario);
		agendamento.setPreCadastro(preCadastro);
		agendamento.setGrupos(grupos);
		agendamento.setMotivoCancelamento(new MotivoCancelamento(new Long(12),
				"cancelado sem justificativa"));
		agendamento
				.setJustificativaCancelamento("Grande texto de justificativa");

		AgendamentoDTO agendamentoDtoCovertido = new FabricaAgendamento().converterParaDTO(agendamento);

		Assert.assertEquals(agendamentoDtoCovertido.getId(),
				agendamento.getId());
		Assert.assertEquals(agendamentoDtoCovertido.getUsuarioDto()
				.getInformacaoEssencialDto().getNome(), usuario
				.getInformacaoEssencial().getNome());
		Assert.assertEquals(agendamentoDtoCovertido.getPreCadastroDto()
				.getInformacaoEssencialDto().getNome(), agendamento
				.getPreCadastro().getInformacaoEssencial().getNome());
		Assert.assertEquals(agendamentoDtoCovertido.getGruposDto().get(0)
				.getDescricao(), agendamento.getGrupos().get(0).getDescricao());
		Assert.assertEquals(agendamentoDtoCovertido.getProfissionalDto()
				.getId(), agendamento.getProfissional().getId());
		Assert.assertEquals(agendamentoDtoCovertido
				.getDescricaoTipoAtendimentoDto().getId(), agendamento
				.getDescricaoTipoAtendimento().getId());
		Assert.assertEquals(agendamentoDtoCovertido.getData(),
				agendamento.getData());
		Assert.assertEquals(agendamentoDtoCovertido.getHorarioDto().getHoraInicio(),
				agendamento.getHorario().getHoraInicio());
		Assert.assertEquals(agendamentoDtoCovertido.getHorarioDto().getHoraTermino(),
				agendamento.getHorario().getHoraTermino());
		Assert.assertEquals(agendamentoDtoCovertido.getStatusDto().toString(),
				agendamento.getStatusAtual().toString());
		Assert.assertEquals(agendamentoDtoCovertido.getSetorDto().toString(),
				agendamento.getSetor().toString());
		Assert.assertEquals(agendamentoDtoCovertido.getReservaParaDto()
				.toString(), agendamento.getReservaPara().toString());
		Assert.assertEquals(agendamentoDtoCovertido.getLocalAtendimentoDto()
				.getId(), agendamento.getLocalAtendimento().getId());
		Assert.assertEquals(agendamentoDtoCovertido.getObs(),
				agendamento.getObs());
		Assert.assertEquals(agendamentoDtoCovertido.getStatusDto()
				.toString(), agendamento.getStatusAtual().toString());
		Assert.assertEquals(agendamentoDtoCovertido.getMotivoCancelamentoDTO()
				.getId(), agendamento.getMotivoCancelamento().getId());
		Assert.assertEquals(
				agendamentoDtoCovertido.getJustificativaCancelamento(),
				agendamento.getJustificativaCancelamento());
		Assert.assertEquals(
				agendamentoDtoCovertido.isEstaDisponivel(),
				agendamento.estaDisponivel());
		Assert.assertEquals(
				agendamentoDtoCovertido.isEstaCancelado(),
				agendamento.estaCancelado());
		Assert.assertEquals(agendamentoDtoCovertido.isEstaAgendado(),
				agendamento.estaAgendado());
		Assert.assertEquals(agendamentoDtoCovertido.getIdadeDoUsuarioOuPreCadastro(),
				agendamento.obterIdadeDoUsuarioOuPreCadastro());
		Assert.assertNotNull(agendamentoDtoCovertido.getResponsaveisOperacoes());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_agendamento_converte_objeto_dto_para_dominio() {
		UsuarioDTO usuarioDto = ContextoUsuario.construirUsuarioDTO();
		PreCadastroDTO preCadastroDto = ContextoPreCadastro
				.construirPreCadastroDTOsemIdentificacao();
		GrupoDTO grupoDto = ContextoGrupo.construirGrupoDTO();
		List<GrupoDTO> gruposDto = new ArrayList<>();
		gruposDto.add(grupoDto);
		
		AgendamentoDTO agendamentoDto = ContextoAgendamento
				.construirAgendamentoDTO();
		agendamentoDto.setUsuarioDto(usuarioDto);
		agendamentoDto.setPreCadastroDto(preCadastroDto);
		agendamentoDto.setGruposDto(gruposDto);
		agendamentoDto.setMotivoCancelamentoDTO(new MotivoCancelamentoDTO(
				new Long(23), "Motivo justificado"));
		agendamentoDto.setJustificativaCancelamento("Grande justificativa");

		Agendamento agendamentoCovertido = new FabricaAgendamento()
				.converterParaDominio(agendamentoDto);

		Assert.assertEquals(agendamentoCovertido.getId(),
				agendamentoDto.getId());
		Assert.assertEquals(agendamentoCovertido.getUsuario()
				.getInformacaoEssencial().getNome(), agendamentoDto
				.getUsuarioDto().getInformacaoEssencialDto().getNome());
		Assert.assertEquals(agendamentoCovertido.getPreCadastro()
				.getInformacaoEssencial().getNome(), agendamentoDto
				.getPreCadastroDto().getInformacaoEssencialDto().getNome());
		Assert.assertEquals(agendamentoCovertido.getGrupos().get(0).getDescricao(),
				agendamentoDto.getGruposDto().get(0).getDescricao());
		Assert.assertEquals(agendamentoCovertido.getProfissional().getId(),
				agendamentoDto.getProfissionalDto().getId());
		Assert.assertEquals(agendamentoCovertido.getDescricaoTipoAtendimento()
				.getId(), agendamentoDto.getDescricaoTipoAtendimentoDto()
				.getId());
		Assert.assertEquals(agendamentoCovertido.getData(),
				agendamentoDto.getData());
		Assert.assertEquals(agendamentoCovertido.getHorario().getHoraInicio(),
				agendamentoDto.getHorarioDto().getHoraInicio());
		Assert.assertEquals(agendamentoCovertido.getHorario().getHoraTermino(),
				agendamentoDto.getHorarioDto().getHoraTermino());
		Assert.assertEquals(agendamentoCovertido.getSetor().toString(),
				agendamentoDto.getSetorDto().toString());
		Assert.assertEquals(agendamentoCovertido.getReservaPara().toString(),
				agendamentoDto.getReservaParaDto().toString());
		Assert.assertEquals(agendamentoCovertido.getLocalAtendimento().getId(),
				agendamentoDto.getLocalAtendimentoDto().getId());
		Assert.assertEquals(agendamentoCovertido.getObs(),
				agendamentoDto.getObs());
		Assert.assertEquals(agendamentoCovertido.getMotivoCancelamento()
				.getId(), agendamentoDto.getMotivoCancelamentoDTO().getId());
		Assert.assertEquals(
				agendamentoCovertido.getJustificativaCancelamento(),
				agendamentoDto.getJustificativaCancelamento());
	}
}

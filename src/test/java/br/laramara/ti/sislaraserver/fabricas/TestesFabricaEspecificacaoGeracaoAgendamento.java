package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.agenda.EspecificacaoGeracaoAgendamento;

public class TestesFabricaEspecificacaoGeracaoAgendamento {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_especificacao_geracao_agendamento_converte_objeto_de_dto_para_dominio() {
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoMultiploComTodosOsDados();
		especificacaoGeracaoAgendamentoDto.getUsuarioDto().setId(null);
		especificacaoGeracaoAgendamentoDto
				.setPreCadastroDTO(ContextoPreCadastro
						.construirPreCadastroDTOsemIdentificacao());
		List<GrupoDTO> gruposDto = new ArrayList<>();
		gruposDto.add(ContextoGrupo.construirGrupoDTO());
		especificacaoGeracaoAgendamentoDto.setGruposDTO(gruposDto);

		EspecificacaoGeracaoAgendamento especificacaoGeracaoAgendamento = new FabricaEspecificacaoGeracaoAgendamento()
				.converterParaDominio(especificacaoGeracaoAgendamentoDto);

		Assert.assertEquals(especificacaoGeracaoAgendamento.getUsuario()
				.getInformacaoEssencial().getNome(),
				especificacaoGeracaoAgendamentoDto.getUsuarioDto()
						.getInformacaoEssencialDto().getNome());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getPreCadastro()
				.getInformacaoEssencial().getNome(),
				especificacaoGeracaoAgendamentoDto.getPreCadastroDTO()
						.getInformacaoEssencialDto().getNome());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getGrupos().get(0)
				.getDescricao(), especificacaoGeracaoAgendamentoDto
				.getGruposDTO().get(0).getDescricao());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getProfissionais()
				.get(0).getId(), especificacaoGeracaoAgendamentoDto
				.getProfissionaisDto().get(0).getId());
		Assert.assertEquals(especificacaoGeracaoAgendamento
				.getDescricaoTipoAtendimento().getId(),
				especificacaoGeracaoAgendamentoDto
						.getDescricaoTipoAtendimentoDto().getId());
		Assert.assertEquals(
				especificacaoGeracaoAgendamento.getModulo().getId(),
				especificacaoGeracaoAgendamentoDto.getModuloDto().getId());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getSetor()
				.toString(), especificacaoGeracaoAgendamentoDto.getSetorDto()
				.toString());
		Assert.assertEquals(especificacaoGeracaoAgendamento
				.getLocalAtendimento().getId(),
				especificacaoGeracaoAgendamentoDto.getLocalAtendimentoDto()
						.getId());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getDataInicio(),
				especificacaoGeracaoAgendamentoDto.getDataInicio());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getDataTermino(),
				especificacaoGeracaoAgendamentoDto.getDataTermino());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getHorario().getHoraInicio(),
				especificacaoGeracaoAgendamentoDto.getHorarioDto().getHoraInicio());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getHorario().getHoraTermino(),
				especificacaoGeracaoAgendamentoDto.getHorarioDto().getHoraTermino());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getReservaStatus()
				.toString(), especificacaoGeracaoAgendamentoDto
				.getReservaStatusDto().toString());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getDiaSemana()
				.toString(), especificacaoGeracaoAgendamentoDto
				.getDiaSemanaDto().toString());
		Assert.assertEquals(especificacaoGeracaoAgendamento.getObs(),
				especificacaoGeracaoAgendamentoDto.getObs());
	}
}

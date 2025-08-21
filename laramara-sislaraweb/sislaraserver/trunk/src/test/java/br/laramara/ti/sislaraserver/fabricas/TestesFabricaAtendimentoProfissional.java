package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;

public class TestesFabricaAtendimentoProfissional {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_profissional_converte_objeto_de_dominio_para_dto() {
		AtendimentoProfissional atendimentoProfissional = ContextoAtendimentoProfissional
				.fabricarAtendimentoProfissionalComTodosOsDados();

		AtendimentoProfissionalDTO atendimentoProfissionalDtoCovertido = new FabricaAtendimentoProfissional()
				.converterParaDTO(atendimentoProfissional);

		Assert.assertEquals(atendimentoProfissionalDtoCovertido.getId(),
				atendimentoProfissional.getId());
		Assert.assertEquals(atendimentoProfissionalDtoCovertido
				.getProfissionalDto().getId(), atendimentoProfissional
				.getProfissional().getId());
		Assert.assertEquals(atendimentoProfissionalDtoCovertido
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				atendimentoProfissional.getInformacaoAtendimento()
						.getFrequencia().toString());
		Assert.assertEquals(atendimentoProfissionalDtoCovertido
				.getInformacaoAtendimentoDto().getDescricao(),
				atendimentoProfissional.getInformacaoAtendimento()
						.getDescricao());
		Assert.assertEquals(atendimentoProfissionalDtoCovertido
				.getInformacaoAtendimentoDto().getJustificativa(),
				atendimentoProfissional.getInformacaoAtendimento()
						.getJustificativa());
		Assert.assertEquals(
				atendimentoProfissionalDtoCovertido.isApenasParticipante(),
				atendimentoProfissional.isApenasParticipante());
		Assert.assertEquals(atendimentoProfissionalDtoCovertido.getHorarioDto().getHoraInicio(),
				atendimentoProfissional.getHorario().getHoraInicio());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_profissional_converte_objeto_de_dto_para_dominio() {
		AtendimentoProfissionalDTO atendimentoProfissionalDto = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTO();

		AtendimentoProfissional atendimentoProfissionalCovertido = new FabricaAtendimentoProfissional()
				.converterParaDominio(atendimentoProfissionalDto);

		Assert.assertEquals(atendimentoProfissionalCovertido.getId(),
				atendimentoProfissionalDto.getId());
		Assert.assertEquals(atendimentoProfissionalCovertido.getProfissional()
				.getId(), atendimentoProfissionalDto.getProfissionalDto()
				.getId());
		Assert.assertEquals(atendimentoProfissionalCovertido
				.getInformacaoAtendimento().getFrequencia().toString(),
				atendimentoProfissionalDto.getInformacaoAtendimentoDto()
						.getFrequenciaDto().toString());
		Assert.assertEquals(atendimentoProfissionalCovertido
				.getInformacaoAtendimento().getDescricao(),
				atendimentoProfissionalDto.getInformacaoAtendimentoDto()
						.getDescricao());
		Assert.assertEquals(atendimentoProfissionalCovertido
				.getInformacaoAtendimento().getJustificativa(),
				atendimentoProfissionalDto.getInformacaoAtendimentoDto()
						.getJustificativa());
		Assert.assertEquals(atendimentoProfissionalCovertido.getHorario().getHoraInicio(),
				atendimentoProfissionalDto.getHorarioDto().getHoraInicio());
	}
}

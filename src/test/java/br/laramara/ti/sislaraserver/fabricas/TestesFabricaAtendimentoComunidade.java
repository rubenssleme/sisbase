package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoComunidade;

public class TestesFabricaAtendimentoComunidade {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_comunidade_converte_objeto_de_dominio_para_dto() {
		AtendimentoComunidade atendimentoComunidade = ContextoAtendimentoComunidade
				.fabricarComTodosOsDados();

		AtendimentoComunidadeDTO atendimentoComunidadeDtoCovertido = new FabricaAtendimentoComunidade()
				.converterParaDTO(atendimentoComunidade);

		Assert.assertEquals(atendimentoComunidadeDtoCovertido.getId(),
				atendimentoComunidade.getId());
		Assert.assertEquals(atendimentoComunidadeDtoCovertido
				.getPreCadastroDto().getId(), atendimentoComunidade
				.getPreCadastro().getId());
		Assert.assertEquals(atendimentoComunidadeDtoCovertido
				.getTipoVinculoDto().getId(), atendimentoComunidade
				.getTipoVinculo().getId());
		Assert.assertEquals(atendimentoComunidadeDtoCovertido
				.getInformacaoAtendimentoDto().getFrequenciaDto().toString(),
				atendimentoComunidade.getInformacaoAtendimento()
						.getFrequencia().toString());
		Assert.assertEquals(atendimentoComunidadeDtoCovertido
				.getInformacaoAtendimentoDto().getDescricao(),
				atendimentoComunidade.getInformacaoAtendimento().getDescricao());
		Assert.assertEquals(atendimentoComunidadeDtoCovertido
				.getInformacaoAtendimentoDto().getJustificativa(),
				atendimentoComunidade.getInformacaoAtendimento()
						.getJustificativa());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_atendimento_profissional_converte_objeto_de_dto_para_dominio() {
		AtendimentoComunidadeDTO atendimentoComunidadeDto = ContextoAtendimentoComunidade
				.construirDTO();

		AtendimentoComunidade atendimentoComunidadeCovertido = new FabricaAtendimentoComunidade()
				.converterParaDominio(atendimentoComunidadeDto);

		Assert.assertEquals(atendimentoComunidadeCovertido.getId(),
				atendimentoComunidadeDto.getId());
		Assert.assertEquals(atendimentoComunidadeCovertido.getPreCadastro()
				.getId(), atendimentoComunidadeDto.getPreCadastroDto().getId());
		Assert.assertEquals(atendimentoComunidadeCovertido.getTipoVinculo()
				.getId(), atendimentoComunidadeDto.getTipoVinculoDto().getId());
		Assert.assertEquals(atendimentoComunidadeCovertido
				.getInformacaoAtendimento().getFrequencia().toString(),
				atendimentoComunidadeDto.getInformacaoAtendimentoDto()
						.getFrequenciaDto().toString());
		Assert.assertEquals(atendimentoComunidadeCovertido
				.getInformacaoAtendimento().getDescricao(),
				atendimentoComunidadeDto.getInformacaoAtendimentoDto()
						.getDescricao());
		Assert.assertEquals(atendimentoComunidadeCovertido
				.getInformacaoAtendimento().getJustificativa(),
				atendimentoComunidadeDto.getInformacaoAtendimentoDto()
						.getJustificativa());
	}
}

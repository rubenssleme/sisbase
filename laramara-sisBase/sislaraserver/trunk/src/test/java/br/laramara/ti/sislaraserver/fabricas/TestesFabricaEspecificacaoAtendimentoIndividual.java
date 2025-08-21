package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;

public class TestesFabricaEspecificacaoAtendimentoIndividual {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_especificacao_pesquisa_atendimento_individual_converte_objeto_de_dto_para_dominio() {
		EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDto = ContextoEspecificacaoPesquisaAtendimentoIndividual
				.fabricarDtoComTodosOsDados();

		EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new FabricaEspecificacaoPesquisaAtendimentoIndividual()
				.converterParaDominio(especificacaoPesquisaAtendimentoIndividualDto);

		Assert.assertEquals(especificacaoPesquisaAtendimentoIndividual
				.getProfissional().getId(),
				especificacaoPesquisaAtendimentoIndividualDto
						.obterProfissionalDto().getId());
		Assert.assertEquals(especificacaoPesquisaAtendimentoIndividual
				.getDescricaoTipoAtendimento().getId(),
				especificacaoPesquisaAtendimentoIndividualDto
						.obterDescricaoTipoAtendimentoDto().getId());
		Assert.assertEquals(especificacaoPesquisaAtendimentoIndividual
				.getModulo().getId(),
				especificacaoPesquisaAtendimentoIndividualDto.obterModuloDto()
						.getId());
		Assert.assertEquals(DataHoraUtils
				.formatarData(especificacaoPesquisaAtendimentoIndividual
						.getDataInicio()),
				especificacaoPesquisaAtendimentoIndividualDto.getDataInicio());
		Assert.assertEquals(DataHoraUtils
				.formatarData(especificacaoPesquisaAtendimentoIndividual
						.getDataTermino()),
				especificacaoPesquisaAtendimentoIndividualDto.getDataTermino());
		Assert.assertEquals(especificacaoPesquisaAtendimentoIndividual.getProntuario()
				.toString(), especificacaoPesquisaAtendimentoIndividualDto.getProntuario()
				.toString());
	}
}

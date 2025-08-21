package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.TipificacaoServicoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;

public class TestesFabricaTipificacaoServico {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_tipificacao_servico_converte_objeto_de_dto_para_dominio() {
		TipificacaoServicoDTO tipificacaoServicoDto = ContextoTipificacaoServico
				.construirTipificacaoServicoDTO();

		TipificacaoServico tipificacaoServico = new FabricaTipificacaoServico()
				.converterParaDominio(tipificacaoServicoDto);

		Assert.assertEquals(tipificacaoServico.getId(),
				tipificacaoServicoDto.getId());
		Assert.assertEquals(tipificacaoServico.getNome(),
				tipificacaoServicoDto.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_tipificacao_servico_converte_objeto_de_dominio_para_dto() {
		TipificacaoServico tipificacaoServico = ContextoTipificacaoServico
				.fabricarComTodosOsDados();

		TipificacaoServicoDTO tipificacaoServicoDto = new FabricaTipificacaoServico()
				.converterParaDTO(tipificacaoServico);

		Assert.assertEquals(tipificacaoServicoDto.getId(),
				tipificacaoServico.getId());
		Assert.assertEquals(tipificacaoServicoDto.toString(),
				tipificacaoServico.getNome());
	}
}

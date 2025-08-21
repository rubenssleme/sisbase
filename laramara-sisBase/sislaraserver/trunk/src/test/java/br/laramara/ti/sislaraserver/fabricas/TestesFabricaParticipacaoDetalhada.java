package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.ParticipacaoDetalhada;

public class TestesFabricaParticipacaoDetalhada {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_participacao_detalahda_converte_objeto_de_dominio_para_dto() {
		ParticipacaoDetalhada participacaoDetalhada = ContextoParticipacaoDetalhada.contruirParticipacaoDetalhada();

		ParticipacaoDetalhadaDTO participacaoDetalhadaDTO = new FabricaParticipacaoDetalhada()
				.converterParaDTO(participacaoDetalhada);

		Assert.assertEquals(participacaoDetalhadaDTO.getId(), participacaoDetalhada.getId());
		Assert.assertEquals(participacaoDetalhadaDTO.getParticipacaoDto().toString(),
				participacaoDetalhada.getParticipacao().toString());
		Assert.assertEquals(participacaoDetalhadaDTO.getQuantidade(), participacaoDetalhada.getQuantidade());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_participacao_converte_objeto_de_dto_para_dominio() {
		ParticipacaoDetalhadaDTO participacaoDto = ContextoParticipacaoDetalhada.contruirParticipacaoDetalhadaDto();

		ParticipacaoDetalhada participacaoObtido = new FabricaParticipacaoDetalhada()
				.converterParaDominio(participacaoDto);

		Assert.assertEquals(participacaoObtido.getId(), participacaoDto.getId());
		Assert.assertEquals(participacaoObtido.getParticipacao().toString(),
				participacaoDto.getParticipacaoDto().toString());
		Assert.assertEquals(participacaoObtido.getQuantidade(), participacaoDto.getQuantidade());
	}
}

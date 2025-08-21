package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;

public class TestesFabricaParticipacao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_participacao_converte_objeto_de_dominio_para_dto() {
		Participacao participacao = Participacao.APENAS_FAMILIA;

		ParticipacaoDTO participacaoDTO = new FabricaParticipacao()
				.converterParaDTO(participacao);

		Assert.assertEquals(participacaoDTO.toString(), participacao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_participacao_converte_objeto_de_dto_para_dominio() {
		Participacao participacaoEsperado = Participacao.COM_FAMILIA;
		ParticipacaoDTO participacaoDto = new ParticipacaoDTO(
				participacaoEsperado.toString());

		Participacao participacaoObtido = new FabricaParticipacao()
				.converterParaDominio(participacaoDto);

		Assert.assertEquals(participacaoObtido, participacaoEsperado);
	}
}

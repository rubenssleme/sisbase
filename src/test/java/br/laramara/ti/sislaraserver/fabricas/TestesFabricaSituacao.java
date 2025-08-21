package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.escola.SituacaoEducacional;

public class TestesFabricaSituacao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_situacao_converte_objeto_de_dominio_para_dto() {
		SituacaoEducacional situacao = SituacaoEducacional.CURSANDO;

		SituacaoEducacionalDTO situacaoDTO = new FabricaSituacao()
				.converterParaDTO(situacao);

		Assert.assertEquals(situacaoDTO.toString(), situacao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_situacao_converte_objeto_de_dto_para_dominio() {
		SituacaoEducacional situacao = SituacaoEducacional.INCOMPLETO;
		SituacaoEducacionalDTO situacaoDto = new SituacaoEducacionalDTO(situacao.toString());

		SituacaoEducacional situacaoObtido = new FabricaSituacao()
				.converterParaDominio(situacaoDto);

		Assert.assertEquals(situacaoObtido, situacao);
	}
}

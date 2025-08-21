package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.atendimento.TipoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoAcaoConduta;

public class TestesFabricaTipoAcaoConduta {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_acao_conduta_converte_objeto_de_dominio_para_dto() {
		TipoAcaoConduta tipoAcaoConduta = TipoAcaoConduta.INTEGRAR;

		TipoAcaoCondutaDTO tipoAcaoCondutaDTO = new FabricaTipoAcaoConduta()
				.converterParaDTO(tipoAcaoConduta);

		Assert.assertEquals(tipoAcaoCondutaDTO.toString(), tipoAcaoConduta.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_acao_conduta_converte_objeto_de_dto_para_dominio() {
		TipoAcaoConduta tipoAcaoApoioEsperado = TipoAcaoConduta.INTEGRAR;
		TipoAcaoCondutaDTO tipoAcaoApoioApoioDto = new TipoAcaoCondutaDTO(
				tipoAcaoApoioEsperado.toString());

		TipoAcaoConduta tipoAcaoApoioObtido = new FabricaTipoAcaoConduta()
				.converterParaDominio(tipoAcaoApoioApoioDto);

		Assert.assertEquals(tipoAcaoApoioObtido, tipoAcaoApoioEsperado);
	}
}

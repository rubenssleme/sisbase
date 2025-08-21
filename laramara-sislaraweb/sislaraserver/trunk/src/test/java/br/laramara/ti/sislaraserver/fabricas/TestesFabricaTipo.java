package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.TipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.instituicao.TipoInstituicao;

public class TestesFabricaTipo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_converte_objeto_de_dominio_para_dto() {
		TipoInstituicao tipo = TipoInstituicao.CIEJA;

		TipoInstituicaoDTO tipoDTO = new FabricaTipoInstituicao()
				.converterParaDTO(tipo);

		Assert.assertEquals(tipoDTO.toString(), tipo.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipoinstituicao_converte_objeto_de_dto_para_dominio() {
		TipoInstituicao tipoInstituicaoEsperado = TipoInstituicao.CRECHE;
		TipoInstituicaoDTO tipoInstituicaoDto = new TipoInstituicaoDTO(
				tipoInstituicaoEsperado.toString());

		TipoInstituicao tipoInstituicaoObtido = new FabricaTipoInstituicao()
				.converterParaDominio(tipoInstituicaoDto);

		Assert.assertEquals(tipoInstituicaoObtido, tipoInstituicaoEsperado);
	}
}

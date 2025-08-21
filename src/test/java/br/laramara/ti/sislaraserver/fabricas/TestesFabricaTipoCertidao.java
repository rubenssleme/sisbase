package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.TipoCertidaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoCertidao;

public class TestesFabricaTipoCertidao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_certidao_converte_objeto_de_dominio_para_dto() {
		TipoCertidao tipoCertidao = TipoCertidao.CASAMENTO;

		TipoCertidaoDTO tipoCertidaoDTO = new FabricaTipoCertidao()
				.converterParaDTO(tipoCertidao);

		Assert.assertEquals(tipoCertidaoDTO.toString(), tipoCertidao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipo_certidao_converte_objeto_de_dto_para_dominio() {
		TipoCertidao tipoCertidao = TipoCertidao.NASCIMENTO;
		TipoCertidaoDTO tipoCertidaoDto = new TipoCertidaoDTO(
				tipoCertidao.toString());

		TipoCertidao tipoCertidaoObtido = new FabricaTipoCertidao()
				.converterParaDominio(tipoCertidaoDto);

		Assert.assertEquals(tipoCertidaoObtido, tipoCertidao);
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ClassificacaoSocialDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.ClassificacaoSocial;

public class TestesFabricaClassificacaoSocial {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_classificacaosocial_converte_objeto_de_dominio_para_dto() {
		ClassificacaoSocial ip = ClassificacaoSocial.I;

		ClassificacaoSocialDTO ipDTO = new FabricaClassificacaoSocial()
				.converterParaDTO(ip);

		Assert.assertEquals(ipDTO.toString(), ip.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_classificacaosocial_converte_objeto_de_dto_para_dominio() {
		ClassificacaoSocial classificacaoEsperada = ClassificacaoSocial.V5;
		ClassificacaoSocialDTO classificacaoSocialDto = new ClassificacaoSocialDTO(
				classificacaoEsperada.toString());

		ClassificacaoSocial classificacaoObtido = new FabricaClassificacaoSocial()
				.converterParaDominio(classificacaoSocialDto);

		Assert.assertEquals(classificacaoObtido, classificacaoEsperada);
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.SituacaoGuardaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.SituacaoGuarda;

public class TestesFabricaSituacaoGuarda {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_situacao_guarda_converte_objeto_de_dominio_para_dto() {
		SituacaoGuarda situacaoGuarda = new SituacaoGuarda(new Long(12222),
				"TUTELA");

		SituacaoGuardaDTO situacaoGuardaDTO = new FabricaSituacaoGuarda()
				.converterParaDTO(situacaoGuarda);

		Assert.assertEquals(situacaoGuardaDTO.getId(), situacaoGuarda.getId());
		Assert.assertEquals(situacaoGuardaDTO.toString(),
				situacaoGuarda.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_situacao_guarda_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(122222);
		String descricao = "TUTELA";

		SituacaoGuardaDTO situacaoGuardaDto = new SituacaoGuardaDTO(id,
				descricao);

		SituacaoGuarda situacaoGuardaObtido = new FabricaSituacaoGuarda()
				.converterParaDominio(situacaoGuardaDto);

		Assert.assertEquals(situacaoGuardaObtido.getDescricao(), descricao);
		Assert.assertEquals(situacaoGuardaObtido.getId(), id);
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ComprometimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Comprometimento;

public class TestesFabricaComprometimento {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_comprometimento_converte_objeto_de_dominio_para_dto() {
		Comprometimento comprometimento = new Comprometimento(new Long(12222), "TESTE");

		ComprometimentoDTO comprometimentoDTO = new FabricaComprometimento().converterParaDTO(comprometimento);

		Assert.assertEquals(comprometimentoDTO.getId(), comprometimento.getId());
		Assert.assertEquals(comprometimentoDTO.toString(), comprometimento.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_comprometimento_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(122222);
		String descricao = "TESTE";

		ComprometimentoDTO comprometimentoDto = new ComprometimentoDTO(id, descricao);

		Comprometimento comprometimentoObtido = new FabricaComprometimento().converterParaDominio(comprometimentoDto);

		Assert.assertEquals(comprometimentoObtido.getDescricao(), descricao);
		Assert.assertEquals(comprometimentoObtido.getId(), id);
	}
}

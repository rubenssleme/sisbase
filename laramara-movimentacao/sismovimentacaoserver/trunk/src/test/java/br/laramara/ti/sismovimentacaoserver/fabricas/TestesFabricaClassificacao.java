package br.laramara.ti.sismovimentacaoserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ClassificacaoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Classificacao;

public class TestesFabricaClassificacao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_classificacao_converte_objeto_de_dominio_para_dto() {
		Classificacao classificacao = Classificacao.AMARELO;

		ClassificacaoDTO classificacaoDto = new FabricaClassificacao()
				.converterParaDTO(classificacao);

		Assert.assertEquals(classificacaoDto.toString(),
				classificacao.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_classificacao_converte_objeto_de_dto_para_dominio() {
		Classificacao classificacao = Classificacao.BRANCO;
		ClassificacaoDTO classificacaoDto = new ClassificacaoDTO(
				classificacao.toString());

		Classificacao statusObtido = new FabricaClassificacao()
				.converterParaDominio(classificacaoDto);

		Assert.assertEquals(statusObtido, classificacao);
	}
}

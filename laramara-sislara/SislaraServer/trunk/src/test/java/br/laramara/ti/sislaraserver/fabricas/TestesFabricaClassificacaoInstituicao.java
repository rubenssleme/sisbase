package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.instituicao.ClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.instituicao.ClassificacaoInstituicao;

public class TestesFabricaClassificacaoInstituicao {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_classificacaoinstituicao_converte_objeto_de_dominio_para_dto() {
		ClassificacaoInstituicao federal = ClassificacaoInstituicao.FEDERAL;

		ClassificacaoInstituicaoDTO federalDTO = new FabricaClassificacaoInstituicao()
				.converterParaDTO(federal);

		Assert.assertEquals(federalDTO.toString(), federal.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_classificacaoinstituicao_converte_objeto_de_dto_para_dominio() {
		ClassificacaoInstituicao classificacaoInstituicao = ClassificacaoInstituicao.MUNICIPAL;
		ClassificacaoInstituicaoDTO classificacaoInstituicaoDto = new ClassificacaoInstituicaoDTO(
				classificacaoInstituicao.toString());

		ClassificacaoInstituicao classificacaoObtido = new FabricaClassificacaoInstituicao()
				.converterParaDominio(classificacaoInstituicaoDto);

		Assert.assertEquals(classificacaoObtido, classificacaoInstituicao);
	}
}

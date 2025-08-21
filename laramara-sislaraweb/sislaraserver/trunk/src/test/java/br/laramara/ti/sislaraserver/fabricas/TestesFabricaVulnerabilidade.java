package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.VulnerabilidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Vulnerabilidade;

public class TestesFabricaVulnerabilidade {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_vulnerabilidade_converte_objeto_de_dominio_para_dto() {
		Vulnerabilidade vulnerabilidade = new Vulnerabilidade(new Long(1), "Vulnerabilidade");

		VulnerabilidadeDTO vulnerabilidadeDto = new FabricaVulnerabilidade()
				.converterParaDTO(vulnerabilidade);

		Assert.assertEquals(vulnerabilidadeDto.getId(), vulnerabilidade.getId());
		Assert.assertEquals(vulnerabilidadeDto.toString(),
				vulnerabilidade.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_vulnerabilidade_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(1);
		String descricao = "Vulnerabilidade";

		VulnerabilidadeDTO vulnerabilidadeDto = new VulnerabilidadeDTO(id, descricao);

		Vulnerabilidade vulnerabilidadeObtido = new FabricaVulnerabilidade()
				.converterParaDominio(vulnerabilidadeDto);

		Assert.assertEquals(vulnerabilidadeObtido.getDescricao(), descricao);
		Assert.assertEquals(vulnerabilidadeObtido.getId(), id);
	}
}

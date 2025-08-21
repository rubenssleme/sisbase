package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPatrocinadorDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void patrocinadordto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String cpf = "71894810287";
		String nome = "Texto de observação";

		PatrocinadorDTO patrocinadorDto = new PatrocinadorDTO(id, cpf, nome);

		Assert.assertEquals(patrocinadorDto.getId(), id);
		Assert.assertEquals(patrocinadorDto.getCpfCnpj(), cpf);
		Assert.assertEquals(patrocinadorDto.getNome(), nome);
	}
}

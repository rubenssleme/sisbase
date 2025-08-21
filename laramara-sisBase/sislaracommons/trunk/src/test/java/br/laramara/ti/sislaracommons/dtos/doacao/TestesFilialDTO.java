package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesFilialDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void filialdto_foi_construida_com_sucesso() {
		Long id = new Long(122222);
		String endereco = "Rua Blablabla";
		String cnpj = "71894810287";
		String cep = "01151000";

		FilialDTO filialDTO = new FilialDTO();
		filialDTO.setId(id);
		filialDTO.setCnpj(cnpj);
		filialDTO.setEndereco(endereco);
		filialDTO.setCep(cep);

		Assert.assertEquals(filialDTO.getId(), id);
		Assert.assertEquals(filialDTO.getCnpj(), cnpj);
		Assert.assertEquals(filialDTO.getEndereco(), endereco);
		Assert.assertEquals(filialDTO.getCep(), cep);
	}
}

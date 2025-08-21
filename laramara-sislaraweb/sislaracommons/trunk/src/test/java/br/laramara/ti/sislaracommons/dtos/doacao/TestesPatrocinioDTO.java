package br.laramara.ti.sislaracommons.dtos.doacao;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPatrocinioDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void patrociniodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String valor = "123,00";

		PatrocinioDTO projetoDto = new PatrocinioDTO();
		projetoDto.setId(id);
		projetoDto.setEmpresaDto(new EmpresaDTO(id, ""));
		projetoDto.setValor(valor);

		Assert.assertEquals(projetoDto.getId(), id);
		Assert.assertEquals(projetoDto.getEmpresaDto().getId(), projetoDto.getId());
		Assert.assertEquals(projetoDto.getValor(), valor);
	}
}

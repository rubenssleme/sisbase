package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDeficienciaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void deficienciaDto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		String descricao = "AUDITIVA";
		boolean etiologia = true;
		
		DeficienciaDTO deficienciaDto = new DeficienciaDTO(id, descricao, etiologia);
		
		Assert.assertEquals(deficienciaDto.getId(), id);
		Assert.assertEquals(deficienciaDto.toString(), descricao);
		Assert.assertEquals(deficienciaDto.isEtiologiaObrigatorio(), true);
	}
}

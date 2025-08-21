package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesRecursoDisponivelDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void recurso_disponiveldto_foi_construido_com_sucesso() {
		Long id = new Long(123);
		String quantidade = "15";
		String valor = "2500,00";
		RecursoDTO recursoDTO = new RecursoDTO(id, "Bengala", false, true, "150,00", Arrays.asList());
		RecursoDisponivelDTO recursoDisponivelDto = new RecursoDisponivelDTO();
		recursoDisponivelDto.setId(id);
		recursoDisponivelDto.setRecursoDto(recursoDTO);
		recursoDisponivelDto.setQuantidade(quantidade);
		recursoDisponivelDto.setValorUnitario(valor);
		recursoDisponivelDto.setValorTotal(valor);

		Assert.assertEquals(recursoDisponivelDto.getId(), id);
		Assert.assertEquals(recursoDisponivelDto.getRecursoDto().getId(), recursoDTO.getId());
		Assert.assertEquals(recursoDisponivelDto.getQuantidade(), quantidade);
		Assert.assertEquals(recursoDisponivelDto.getValorUnitario(), valor);
		Assert.assertEquals(recursoDisponivelDto.getValorTotal(), valor);
	}
}

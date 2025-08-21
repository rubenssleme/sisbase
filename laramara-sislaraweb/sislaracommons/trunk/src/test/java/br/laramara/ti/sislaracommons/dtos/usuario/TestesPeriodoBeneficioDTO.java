package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPeriodoBeneficioDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_beneficiosdto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		BeneficioDTO beneficioDto = new BeneficioDTO(id, "Aposentadoria Por Invalidez");
		String dataInicial = "01/11/2011";
		String dataFinal = "30/12/2015";
		StatusBeneficioDTO status = new StatusBeneficioDTO("SIM");
		PeriodoBeneficioDTO periodoBeneficioDto = new PeriodoBeneficioDTO();
		periodoBeneficioDto.setId(id);
		periodoBeneficioDto.setBeneficioDto(beneficioDto);
		periodoBeneficioDto.setDataInicial(dataInicial);
		periodoBeneficioDto.setDataFinal(dataFinal);
		periodoBeneficioDto.setStatusDto(status);

		Assert.assertEquals(periodoBeneficioDto.getId(), id);
		Assert.assertEquals(periodoBeneficioDto.getBeneficioDto().getId(), id);
		Assert.assertEquals(periodoBeneficioDto.getDataInicial(), dataInicial);
		Assert.assertEquals(periodoBeneficioDto.getDataFinal(), dataFinal);
		Assert.assertEquals(periodoBeneficioDto.getStatusDto(), status);
	}

}

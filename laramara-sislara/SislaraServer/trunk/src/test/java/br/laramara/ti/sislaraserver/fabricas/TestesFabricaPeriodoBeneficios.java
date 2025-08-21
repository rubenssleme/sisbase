package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoBeneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.StatusBeneficio;

public class TestesFabricaPeriodoBeneficios {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_beneficio_converte_objeto_de_dominio_para_dto() {
		Long id = new Long(1);
		String textoBeneficio = "Aposentadoria por Invalidez";
		String dataInicial = "01/01/2001";
		String dataFinal = "31/12/2010";
		StatusBeneficio status = StatusBeneficio.NAO_SE_APLICA;

		Beneficio beneficio = new Beneficio(id, textoBeneficio);
		PeriodoBeneficio periodoBeneficio = new PeriodoBeneficio();
		periodoBeneficio.setId(id);
		periodoBeneficio.setBeneficio(beneficio);
		periodoBeneficio.setDataInicial(dataInicial);
		periodoBeneficio.setDataFinal(dataFinal);
		periodoBeneficio.setStatus(status);

		PeriodoBeneficioDTO periodoBeneficioDTO = new FabricaPeriodoBeneficio()
				.converterParaDTO(periodoBeneficio);

		Assert.assertEquals(periodoBeneficioDTO.getId(), id);
		Assert.assertEquals(periodoBeneficioDTO.getBeneficioDto().getId(), id);
		Assert.assertEquals(periodoBeneficioDTO.getDataInicial(), dataInicial);
		Assert.assertEquals(periodoBeneficioDTO.getDataFinal(), dataFinal);
		Assert.assertEquals(periodoBeneficioDTO.getStatusDto().toString(),
				status.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_beneficio_converte_objeto_de_dto_para_dominio() {
		PeriodoBeneficioDTO periodoBeneficioDTO = ContextoPeriodoBeneficio
				.construirPeriodoBeneficioDTO();

		PeriodoBeneficio periodoBeneficio = new FabricaPeriodoBeneficio()
				.converterParaDominio(periodoBeneficioDTO);

		Assert.assertEquals(periodoBeneficio.getId(),
				periodoBeneficioDTO.getId());
		Assert.assertEquals(periodoBeneficio.getBeneficio().getId(),
				periodoBeneficioDTO.getBeneficioDto().getId());
		Assert.assertEquals(periodoBeneficio.getDataInicial(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(periodoBeneficioDTO
						.getDataInicial())));
		Assert.assertEquals(periodoBeneficio.getDataFinal(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(periodoBeneficioDTO
						.getDataFinal())));
		Assert.assertEquals(periodoBeneficio.getStatus().toString(),
				periodoBeneficioDTO.getStatusDto().toString());
	}
}

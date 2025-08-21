package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.BeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusBeneficioDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Beneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoBeneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.StatusBeneficio;

public class ContextoPeriodoBeneficio {
	
	public static PeriodoBeneficioDTO construirPeriodoBeneficioDTO() {
		Long id = new Long(1);
		String textoBeneficio = "Aposentadoria por Invalidez";
		String dataInicial = "01/01/2001";
		String dataFinal = "31/12/2010";
		BeneficioDTO beneficioDTO = new BeneficioDTO(id, textoBeneficio);

		PeriodoBeneficioDTO periodoBeneficioDTO = new PeriodoBeneficioDTO();
		periodoBeneficioDTO.setId(id);
		periodoBeneficioDTO.setBeneficioDto(beneficioDTO);
		periodoBeneficioDTO.setDataInicial(dataInicial);
		periodoBeneficioDTO.setDataFinal(dataFinal);
		periodoBeneficioDTO.setStatusDto(new StatusBeneficioDTO("SIM"));
		return periodoBeneficioDTO;
	}
	
	public static PeriodoBeneficio construirComTodosOsDados(){
		PeriodoBeneficio periodoBeneficio = new PeriodoBeneficio();
		periodoBeneficio.setBeneficio(new Beneficio(new Long(1), "Aposentadoria por Invalidez"));
		periodoBeneficio.setDataInicial("01/01/2011");
		periodoBeneficio.setDataFinal("31/12/2011");
		periodoBeneficio.setStatus(StatusBeneficio.SIM);
		
		return periodoBeneficio;
	}
}

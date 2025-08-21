package br.laramara.ti.sislaracommons.dtos.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesPeriodoDeficienciaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void periodo_deficienciadto_foi_construido_com_sucesso() {
		Long id = new Long(12222);
		DeficienciaDTO deficienciaDto = new DeficienciaDTO(id, "AUDITIVA", true);
		String dataInicial = "01/11/2011";
		String dataFinal = "30/12/2015";
		CidDTO cidDto = new CidDTO("A123", "Teste");
		List<EtiologiaDTO> etiologiasDto = new ArrayList<>();
		etiologiasDto.add(new EtiologiaDTO());

		PeriodoDeficienciaDTO periodoDeficienciaDto = new PeriodoDeficienciaDTO();
		periodoDeficienciaDto.setId(id);
		periodoDeficienciaDto.setDeficienciaDto(deficienciaDto);
		periodoDeficienciaDto.setCidDto(cidDto);
		periodoDeficienciaDto.setDataInicial(dataInicial);
		periodoDeficienciaDto.setDataFinal(dataFinal);
		periodoDeficienciaDto.setEtiologiasDto(etiologiasDto);

		Assert.assertEquals(periodoDeficienciaDto.getId(), id);
		Assert.assertEquals(periodoDeficienciaDto.getDeficienciaDto().getId(),
				id);
		Assert.assertEquals(periodoDeficienciaDto.getCidDto().getId(),
				cidDto.getId());
		Assert.assertEquals(periodoDeficienciaDto.getDataInicial(), dataInicial);
		Assert.assertEquals(periodoDeficienciaDto.getDataFinal(), dataFinal);
		Assert.assertEquals(periodoDeficienciaDto.getEtiologiasDto().size(),
				etiologiasDto.size());
	}
}

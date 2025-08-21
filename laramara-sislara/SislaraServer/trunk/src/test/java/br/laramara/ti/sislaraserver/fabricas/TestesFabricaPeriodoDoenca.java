package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Doenca;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDoenca;

public class TestesFabricaPeriodoDoenca  {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_doenca_converte_objeto_de_dominio_para_dto() {
		Long id = new Long(1);
		String textoDoenca = "HIP";
		String dataInicial = "01/01/2001";
		String dataFinal = "31/12/2010";
		String obs = "OBS";
		
		Doenca doenca = new Doenca(id, textoDoenca);
		PeriodoDoenca periodoDoenca = new PeriodoDoenca();
		periodoDoenca.setId(id);
		periodoDoenca.setDoenca(doenca);
		periodoDoenca.setDataInicial(dataInicial);
		periodoDoenca.setDataFinal(dataFinal);
		periodoDoenca.setObs(obs);
		
		PeriodoDoencaDTO periodoDoencaDTO = new FabricaPeriodoDoenca()
				.converterParaDTO(periodoDoenca);

		Assert.assertEquals(periodoDoencaDTO.getId(), id);
		Assert.assertEquals(periodoDoencaDTO.getDoencaDto().getId(),
				id);
		Assert.assertEquals(periodoDoencaDTO.getObs()
				.toString(), obs);
		Assert.assertEquals(periodoDoencaDTO.getDataInicial(), dataInicial);
		Assert.assertEquals(periodoDoencaDTO.getDataFinal(), dataFinal);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_doenca_converte_objeto_de_dto_para_dominio() {
		PeriodoDoencaDTO periodoDoencaDTO = ContextoPeriodoDoenca
				.construirPeriodoDoencaDTO();

		PeriodoDoenca periodoDoenca = new FabricaPeriodoDoenca()
				.converterParaDominio(periodoDoencaDTO);

		Assert.assertEquals(periodoDoenca.getId(),
				periodoDoencaDTO.getId());
		Assert.assertEquals(periodoDoenca.getDoenca().getId(), periodoDoencaDTO
				.getDoencaDto().getId());
		Assert.assertEquals(periodoDoenca.getObs().toString(),
				periodoDoencaDTO.getObs().toString());
		Assert.assertEquals(periodoDoenca.getDataInicial(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(periodoDoencaDTO
						.getDataInicial())));
		Assert.assertEquals(periodoDoenca.getDataFinal(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(periodoDoencaDTO
						.getDataFinal())));
	}
}

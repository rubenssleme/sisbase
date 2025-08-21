package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Comprometimento;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoComprometimento;

public class TestesFabricaPeriodoComprometimento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_comprometimento_converte_objeto_de_dominio_para_dto() {
		Long id = new Long(1);
		String textoComprometimento = "Aposentadoria por Invalidez";
		String dataInicial = "01/01/2001";
		String dataFinal = "31/12/2010";
		EpocaIncidencia epocaIncidencia = EpocaIncidencia.CONGENITA;

		Comprometimento comprometimento = new Comprometimento(id, textoComprometimento);
		PeriodoComprometimento periodoComprometimento = new PeriodoComprometimento();
		periodoComprometimento.setId(id);
		periodoComprometimento.setComprometimento(comprometimento);
		periodoComprometimento.setDataInicial(dataInicial);
		periodoComprometimento.setDataFinal(dataFinal);
		periodoComprometimento.setEpocaIncidencia(epocaIncidencia);

		PeriodoComprometimentoDTO periodoComprometimentoDTO = new FabricaPeriodoComprometimento()
				.converterParaDTO(periodoComprometimento);

		Assert.assertEquals(periodoComprometimentoDTO.getId(), id);
		Assert.assertEquals(periodoComprometimentoDTO.getComprometimentoDto().getId(), id);
		Assert.assertEquals(periodoComprometimentoDTO.getDataInicial(), dataInicial);
		Assert.assertEquals(periodoComprometimentoDTO.getDataFinal(), dataFinal);
		Assert.assertEquals(periodoComprometimentoDTO.getEpocaIncidenciaDto().toString(), epocaIncidencia.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_comprometimento_converte_objeto_de_dto_para_dominio() {
		PeriodoComprometimentoDTO periodoComprometimentoDTO = ContextoPeriodoComprometimento
				.construirPeriodoComprometimentoDTO();

		PeriodoComprometimento periodoComprometimento = new FabricaPeriodoComprometimento()
				.converterParaDominio(periodoComprometimentoDTO);

		Assert.assertEquals(periodoComprometimento.getId(), periodoComprometimentoDTO.getId());
		Assert.assertEquals(periodoComprometimento.getComprometimento().getId(),
				periodoComprometimentoDTO.getComprometimentoDto().getId());
		Assert.assertEquals(periodoComprometimento.getDataInicial(),
				DataHoraUtils.formatarData(DataHoraUtils.criar(periodoComprometimentoDTO.getDataInicial())));
		Assert.assertEquals(periodoComprometimento.getDataFinal(),
				DataHoraUtils.formatarData(DataHoraUtils.criar(periodoComprometimentoDTO.getDataFinal())));
		Assert.assertEquals(periodoComprometimento.getEpocaIncidencia().toString(),
				periodoComprometimentoDTO.getEpocaIncidenciaDto().toString());
	}
}

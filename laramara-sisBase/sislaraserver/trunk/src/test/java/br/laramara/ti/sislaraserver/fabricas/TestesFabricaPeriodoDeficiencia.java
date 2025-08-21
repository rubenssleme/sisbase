package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;
import br.laramara.ti.sislaraserver.dominio.usuario.Deficiencia;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;
import br.laramara.ti.sislaraserver.dominio.usuario.Etiologia;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDeficiencia;

public class TestesFabricaPeriodoDeficiencia {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_deficiencia_converte_objeto_de_dominio_para_dto() {
		Long id = new Long(1);
		String textoDeficiencia = "AUDITIVA";
		String dataInicial = "01/01/2001";
		String dataFinal = "31/12/2010";
		Cid cid = new Cid("A12", "Teste");
		EpocaIncidencia epocaIncidencia = EpocaIncidencia.ADQUIRIDA;
		List<Etiologia> etiologias = new ArrayList<>();
		etiologias.add(new Etiologia());

		Deficiencia deficiencia = new Deficiencia(id, textoDeficiencia, true);
		PeriodoDeficiencia periodoDeficiencia = new PeriodoDeficiencia();
		periodoDeficiencia.setId(id);
		periodoDeficiencia.setDeficiencia(deficiencia);
		periodoDeficiencia.setCid(cid);
		periodoDeficiencia.setEpocaIncidencia(epocaIncidencia);
		periodoDeficiencia.setDataInicial(dataInicial);
		periodoDeficiencia.setDataFinal(dataFinal);
		periodoDeficiencia.setEtiologias(etiologias);

		PeriodoDeficienciaDTO periodoDeficienciaDTO = new FabricaPeriodoDeficiencia()
				.converterParaDTO(periodoDeficiencia);

		Assert.assertEquals(periodoDeficienciaDTO.getId(), id);
		Assert.assertEquals(periodoDeficienciaDTO.getDeficienciaDto().getId(),
				id);
		Assert.assertEquals(periodoDeficienciaDTO.getCidDto().getId(),
				cid.getId());
		Assert.assertEquals(periodoDeficienciaDTO.getEpocaIncidenciaDto()
				.toString(), epocaIncidencia.toString());
		Assert.assertEquals(periodoDeficienciaDTO.getDataInicial(), dataInicial);
		Assert.assertEquals(periodoDeficienciaDTO.getDataFinal(), dataFinal);
		Assert.assertEquals(periodoDeficienciaDTO.getEtiologiasDto().size(),
				etiologias.size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_periodo_deficiencia_converte_objeto_de_dto_para_dominio() {
		PeriodoDeficienciaDTO periodoDeficienciaDTO = ContextoPeriodoDeficiencia
				.construirPeriodoDeficienciaDTO();

		PeriodoDeficiencia periodoDeficiencia = new FabricaPeriodoDeficiencia()
				.converterParaDominio(periodoDeficienciaDTO);

		Assert.assertEquals(periodoDeficiencia.getId(),
				periodoDeficienciaDTO.getId());
		Assert.assertEquals(periodoDeficiencia.getDeficiencia().getId(),
				periodoDeficienciaDTO.getDeficienciaDto().getId());
		Assert.assertEquals(periodoDeficiencia.getCid().getId(),
				periodoDeficienciaDTO.getCidDto().getId());
		Assert.assertEquals(periodoDeficiencia.getEpocaIncidencia().toString(),
				periodoDeficienciaDTO.getEpocaIncidenciaDto().toString());
		Assert.assertEquals(periodoDeficiencia.getDataInicial(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(periodoDeficienciaDTO
						.getDataInicial())));
		Assert.assertEquals(periodoDeficiencia.getDataFinal(), DataHoraUtils
				.formatarData(DataHoraUtils.criar(periodoDeficienciaDTO
						.getDataFinal())));
		Assert.assertEquals(periodoDeficiencia.getEtiologias().size(),
				periodoDeficienciaDTO.getEtiologiasDto().size());
	}
}

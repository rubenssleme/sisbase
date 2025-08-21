package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.usuario.CidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.DeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EtiologiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;
import br.laramara.ti.sislaraserver.dominio.usuario.Deficiencia;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;
import br.laramara.ti.sislaraserver.dominio.usuario.Etiologia;
import br.laramara.ti.sislaraserver.dominio.usuario.PeriodoDeficiencia;

public class ContextoPeriodoDeficiencia {
	
	public static PeriodoDeficienciaDTO construirPeriodoDeficienciaDTO() {
		Long id = new Long(1);
		String textoDeficiencia = "AUDITIVA";
		String dataInicial = "01/01/2001";
		String dataFinal = "31/12/2010";
		CidDTO cidDto = new CidDTO("AA1", "TESTE");
		DeficienciaDTO deficienciaDTO = new DeficienciaDTO(id, textoDeficiencia, true);
		
		EtiologiaDTO etiologiaDTO = new EtiologiaDTO();
		etiologiaDTO.setId(new Long(1));
		etiologiaDTO.setCidDto(new CidDTO("A001", "A"));

		List<EtiologiaDTO> etiologiasDto = new ArrayList<>();
		etiologiasDto.add(etiologiaDTO);
		
		PeriodoDeficienciaDTO periodoDeficienciaDTO = new PeriodoDeficienciaDTO();
		periodoDeficienciaDTO.setId(id);
		periodoDeficienciaDTO.setDeficienciaDto(deficienciaDTO);
		periodoDeficienciaDTO.setCidDto(cidDto);
		periodoDeficienciaDTO.setEpocaIncidenciaDto(new EpocaIncidenciaDTO("ADQUIRIDA"));
		periodoDeficienciaDTO.setDataInicial(dataInicial);
		periodoDeficienciaDTO.setDataFinal(dataFinal);
		periodoDeficienciaDTO.setEtiologiasDto(etiologiasDto);
		return periodoDeficienciaDTO;
	}
	
	public static PeriodoDeficiencia construirComTodosOsDados(){
		Etiologia etiologia = new Etiologia();
		etiologia.setId(new Long(1));
		etiologia.setCid(new Cid("A001", "A"));

		List<Etiologia> etiologias = new ArrayList<>();
		etiologias.add(etiologia);
		
		PeriodoDeficiencia periodoDeficiencia = new PeriodoDeficiencia();
		periodoDeficiencia.setDeficiencia(new Deficiencia(new Long(1), "AUDITIVA", true));
		periodoDeficiencia.setEpocaIncidencia(EpocaIncidencia.ADQUIRIDA);
		periodoDeficiencia.setDataInicial("01/01/2011");
		periodoDeficiencia.setDataFinal("31/12/2011");
		periodoDeficiencia.setEtiologias(etiologias);
		return periodoDeficiencia;
	}
}

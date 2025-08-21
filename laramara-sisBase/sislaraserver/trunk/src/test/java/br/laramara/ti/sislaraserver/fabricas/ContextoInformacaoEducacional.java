package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.escola.AreaFormacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.escola.PeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SerieDTO;
import br.laramara.ti.sislaracommons.dtos.escola.SituacaoEducacionalDTO;
import br.laramara.ti.sislaraserver.dominio.escola.AreaFormacao;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.escola.Periodo;
import br.laramara.ti.sislaraserver.dominio.escola.Serie;
import br.laramara.ti.sislaraserver.dominio.escola.SituacaoEducacional;

public class ContextoInformacaoEducacional {

	public static InformacaoEducacional fabricarInformacaoEscolarComTodosOsDados() {
		InformacaoEducacional informacaoEducacional = new InformacaoEducacional();
		informacaoEducacional.setInstituicao(ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados());
		informacaoEducacional.setEscolaridade(new Escolaridade(new Long(1),
				"SUPERIOR COMPLETO"));
		informacaoEducacional.setPeriodo(Periodo.MANHA);
		informacaoEducacional.setSituacao(SituacaoEducacional.CURSANDO);
		informacaoEducacional.setSerie(new Serie(new Long(1), "1º Série"));
		informacaoEducacional.setNomeProfessor("Josep Meaza");
		informacaoEducacional.setAreaFormacao(new AreaFormacao(new Long(1), "Direito"));
		informacaoEducacional.setDataReferencia("31/12/2012");
		return informacaoEducacional;
	}

	public static InformacaoEducacionalDTO construirInformacaoEducacionalDTO() {
		InformacaoEducacionalDTO informacaoEducacionalDto = new InformacaoEducacionalDTO();
		informacaoEducacionalDto.setInstituicaoDto(ContextoInstituicao
				.construirInstitucaoDTO());
		informacaoEducacionalDto.setEscolaridadeDto(ContextoEscolaridade.construirEscolaridadeDto());
		informacaoEducacionalDto.setPeriodoDto(new PeriodoDTO("MANHA"));
		informacaoEducacionalDto.setSituacaoEducacionalDto(new SituacaoEducacionalDTO("COMPLETO"));
		informacaoEducacionalDto.setSerieDto(new SerieDTO(new Long(1), "1º Série"));
		informacaoEducacionalDto.setNomeProfessor("Josep Meaza");
		informacaoEducacionalDto.setAreaFormacaoDto(new AreaFormacaoDTO(new Long(1), "Direito"));
		informacaoEducacionalDto.setDataReferencia("31/12/2012");
		return informacaoEducacionalDto;
	}
}

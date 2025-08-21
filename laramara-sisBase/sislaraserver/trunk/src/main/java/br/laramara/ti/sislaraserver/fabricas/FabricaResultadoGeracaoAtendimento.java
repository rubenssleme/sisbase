package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoGeracaoAtendimento;

public class FabricaResultadoGeracaoAtendimento extends FabricaBase<ResultadoGeracaoAtendimentoDTO, ResultadoGeracaoAtendimento> {

	public final ResultadoGeracaoAtendimentoDTO converterParaDTO(ResultadoGeracaoAtendimento resultadoGeracaoAtendimento) {
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = new ResultadoGeracaoAtendimentoDTO();
		resultadoGeracaoAtendimentoDTO.efetuadoComSucesso(
				new FabricaAtendimentoGrupo().converterParaDTO(resultadoGeracaoAtendimento.getAtendimentoGrupo()));
		resultadoGeracaoAtendimentoDTO.setModuloPeriodoDTO(
				new FabricaModuloPeriodo().converterParaDTO(resultadoGeracaoAtendimento.getModuloPeriodo()));
		resultadoGeracaoAtendimentoDTO
				.setGrupoDto(new FabricaGrupo().converterParaDTO(resultadoGeracaoAtendimento.getGrupo()));
		return resultadoGeracaoAtendimentoDTO;
	}

	public final ResultadoGeracaoAtendimento converterParaDominio(
			ResultadoGeracaoAtendimentoDTO recursoMoradiaDto) {
		return null;
	}
}

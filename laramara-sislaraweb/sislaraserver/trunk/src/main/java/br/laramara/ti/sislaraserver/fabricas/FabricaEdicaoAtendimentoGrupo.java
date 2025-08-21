package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoAtendimentoGrupoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoEdicaoAtendimentoGrupo;

public class FabricaEdicaoAtendimentoGrupo extends FabricaBase<ResultadoEdicaoAtendimentoGrupoDTO, ResultadoEdicaoAtendimentoGrupo> {

	@Override
	public ResultadoEdicaoAtendimentoGrupo converterParaDominio(ResultadoEdicaoAtendimentoGrupoDTO objetoDto) {
		return null;
	}

	@Override
	public ResultadoEdicaoAtendimentoGrupoDTO converterParaDTO(
			ResultadoEdicaoAtendimentoGrupo resultadoEdicaoAtendimentoGrupoDominio) {
		ResultadoEdicaoAtendimentoGrupoDTO resultadoEdicaoAtendimentoGrupoDto = new ResultadoEdicaoAtendimentoGrupoDTO();
		resultadoEdicaoAtendimentoGrupoDto.setSucesso(resultadoEdicaoAtendimentoGrupoDominio.sucesso());
		resultadoEdicaoAtendimentoGrupoDto.setMensagem(resultadoEdicaoAtendimentoGrupoDominio.getMensagem());
		resultadoEdicaoAtendimentoGrupoDto.setObjetoDtoResultado(new FabricaAtendimentoGrupo()
				.converterParaDTO(resultadoEdicaoAtendimentoGrupoDominio.getAtendimentoGrupo()));
		return resultadoEdicaoAtendimentoGrupoDto;
	}

}

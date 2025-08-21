package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoModuloPeriodoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoEdicaoModuloPeriodo;

public class FabricaEdicaoModuloPeriodo
		extends FabricaBase<ResultadoEdicaoModuloPeriodoDTO, ResultadoEdicaoModuloPeriodo> {

	@Override
	public ResultadoEdicaoModuloPeriodo converterParaDominio(ResultadoEdicaoModuloPeriodoDTO objetoDto) {
		return null;
	}

	@Override
	public ResultadoEdicaoModuloPeriodoDTO converterParaDTO(ResultadoEdicaoModuloPeriodo moduloPeriodoDominio) {
		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDto = new ResultadoEdicaoModuloPeriodoDTO();
		resultadoEdicaoModuloPeriodoDto.setSucesso(moduloPeriodoDominio.sucesso());
		resultadoEdicaoModuloPeriodoDto.setMensagem(moduloPeriodoDominio.getMensagem());
		resultadoEdicaoModuloPeriodoDto.setObjetoDtoResultado(
				new FabricaModuloPeriodo().converterParaDTO(moduloPeriodoDominio.getModuloPeriodo()));
		return resultadoEdicaoModuloPeriodoDto;
	}
}

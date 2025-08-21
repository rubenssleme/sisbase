package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.EspecificacaoGeracaoAtendimento;

public class FabricaEspecificacaoGeracaoAtendimento
		extends FabricaBase<EspecificacaoGeracaoAtendimentoDTO, EspecificacaoGeracaoAtendimento> {

	@Override
	public EspecificacaoGeracaoAtendimentoDTO converterParaDTO(
			EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento) {
		return null;
	}

	@Override
	public EspecificacaoGeracaoAtendimento converterParaDominio(
			EspecificacaoGeracaoAtendimentoDTO especificacaoGeracaoAtendimentoDto) {
		return especificacaoGeracaoAtendimentoDto != null
				? new EspecificacaoGeracaoAtendimento(especificacaoGeracaoAtendimentoDto.getIdModuloPeriodoDto(),
						especificacaoGeracaoAtendimentoDto.getData(),
						new FabricaHorario().converterParaDominio(especificacaoGeracaoAtendimentoDto.getHorarioDto()))
				: null;
	}
}

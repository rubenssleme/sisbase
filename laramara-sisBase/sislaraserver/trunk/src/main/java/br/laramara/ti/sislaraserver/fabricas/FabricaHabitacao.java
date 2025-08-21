package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.HabitacaoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Habitacao;

public class FabricaHabitacao extends FabricaBase<HabitacaoDTO, Habitacao> {

	public final HabitacaoDTO converterParaDTO(Habitacao habitacao) {
		return habitacao != null ? new HabitacaoDTO(habitacao.getId(), habitacao.getDescricao()) : null;
	}

	public final Habitacao converterParaDominio(HabitacaoDTO habitacaoDto) {
		return habitacaoDto != null ? new Habitacao(habitacaoDto.getId(), habitacaoDto.toString()) : null;
	}
}

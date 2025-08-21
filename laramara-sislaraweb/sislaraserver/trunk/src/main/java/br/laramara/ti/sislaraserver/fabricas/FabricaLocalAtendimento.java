package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.grupo.LocalAtendimentoDTO;
import br.laramara.ti.sislaraserver.dominio.grupo.LocalAtendimento;

public class FabricaLocalAtendimento extends
		FabricaBase<LocalAtendimentoDTO, LocalAtendimento> {

	public final LocalAtendimentoDTO converterParaDTO(
			LocalAtendimento localAtendimento) {
		return localAtendimento != null ? new LocalAtendimentoDTO(
				localAtendimento.getId(), localAtendimento.getNome()) : null;
	}

	public final LocalAtendimento converterParaDominio(
			LocalAtendimentoDTO localAtendimentoDto) {
		return localAtendimentoDto != null ? new LocalAtendimento(
				localAtendimentoDto.getId(), localAtendimentoDto.toString())
				: null;
	}
}
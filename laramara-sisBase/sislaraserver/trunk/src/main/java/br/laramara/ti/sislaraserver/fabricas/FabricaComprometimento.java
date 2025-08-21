package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ComprometimentoDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Comprometimento;

public class FabricaComprometimento extends FabricaBase<ComprometimentoDTO, Comprometimento> {

	public final ComprometimentoDTO converterParaDTO(Comprometimento comprometimento) {
		return comprometimento != null ? new ComprometimentoDTO(comprometimento.getId(), comprometimento.getDescricao())
				: null;
	}

	public final Comprometimento converterParaDominio(ComprometimentoDTO comprometimentoDto) {
		return comprometimentoDto != null
				? new Comprometimento(comprometimentoDto.getId(), comprometimentoDto.toString()) : null;
	}
}

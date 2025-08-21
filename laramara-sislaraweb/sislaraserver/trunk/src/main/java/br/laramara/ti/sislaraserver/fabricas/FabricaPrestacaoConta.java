package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.PrestacaoContaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.PrestacaoConta;

public class FabricaPrestacaoConta extends FabricaBase<PrestacaoContaDTO, PrestacaoConta> {
	public final PrestacaoContaDTO converterParaDTO(PrestacaoConta permissao) {
		return permissao != null ? new PrestacaoContaDTO(permissao.toString()) : null;
	}

	public final PrestacaoConta converterParaDominio(PrestacaoContaDTO permissaoDto) {
		return permissaoDto != null ? PrestacaoConta.valueOf(permissaoDto.toString()) : null;
	}
}

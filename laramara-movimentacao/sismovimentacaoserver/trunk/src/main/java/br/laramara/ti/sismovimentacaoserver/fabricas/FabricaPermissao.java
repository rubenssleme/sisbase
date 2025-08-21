package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;

public class FabricaPermissao extends FabricaBase<PermissaoDTO, Permissao> {
	public final PermissaoDTO converterParaDTO(Permissao permissao) {
		return permissao != null ? new PermissaoDTO(permissao.toString())
				: null;
	}

	public final Permissao converterParaDominio(PermissaoDTO permissaoDto) {
		return permissaoDto != null ? Permissao
				.valueOf(permissaoDto.toString()) : null;
	}
}

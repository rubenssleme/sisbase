package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;

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

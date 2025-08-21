package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;

public class FabricaPerfil extends FabricaBase<PerfilDTO, Perfil> {
	public final PerfilDTO converterParaDTO(Perfil perfil) {
		return perfil != null ? new PerfilDTO(perfil.getId(),
				perfil.getDescricao(),
				new FabricaPermissao().converterParaDTO(perfil.getPermissoes()))
				: null;
	}

	public final Perfil converterParaDominio(PerfilDTO perfilDto) {
		return perfilDto != null ? new Perfil(perfilDto.getId(),
				perfilDto.toString(),
				new FabricaPermissao().converterParaDominio(perfilDto
						.getPermissoesDto())) : null;
	}
}

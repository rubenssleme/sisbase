package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;

public class NivelFabrica extends FabricaBase<NivelDTO, Nivel> {
	public final NivelDTO converterParaDTO(Nivel nivel) {
		NivelDTO nivelDto = new NivelDTO();
		if (nivel != null) {
			nivelDto.setId(nivel.getId());
			nivelDto.setDescricao(nivel.getDescricao());
			nivelDto.setPermissoesDto(new PermissaoFabrica().converterParaDTO(nivel.getPermissoes()));
		}
		return nivelDto;
	}

	public final Nivel converterParaDominio(NivelDTO nivelDto) {
		Nivel nivel = new Nivel();
		if (nivelDto != null) {
			nivel.setId(nivelDto.getId());
			nivel.setDescricao(nivelDto.getDescricao());
			nivel.setPermissoes(new PermissaoFabrica().converterParaDominio(nivelDto.getPermissoesDto()));
		}
		return nivel;
	}
}

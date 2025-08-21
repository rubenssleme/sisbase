package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.externa.CredencialExterna;

public class FabricaCredencialExterna extends FabricaBase<CredencialExternaDTO, CredencialExterna> {

	public CredencialExterna converterParaDominio(CredencialExternaDTO credencialExternaDto) {
		return new CredencialExterna(credencialExternaDto.getEmail(), credencialExternaDto.getSenha());
	}

	@Override
	public CredencialExternaDTO converterParaDTO(CredencialExterna objetoDominio) {
		return null;
	}
}

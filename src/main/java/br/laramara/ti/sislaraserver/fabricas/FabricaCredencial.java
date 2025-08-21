package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.Credencial;

public class FabricaCredencial extends FabricaBase<CredencialDTO, Credencial> {
	public Credencial converterParaDominio(CredencialDTO credencialDto) {
		Credencial credencial = new Credencial(credencialDto.getUsuario(),
				credencialDto.getSenha());
		return credencial;
	}

	@Override
	public CredencialDTO converterParaDTO(Credencial objetoDominio) {
		return null;
	}
}

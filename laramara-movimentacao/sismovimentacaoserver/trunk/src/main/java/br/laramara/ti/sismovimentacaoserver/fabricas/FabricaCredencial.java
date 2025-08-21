package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Credencial;

public class FabricaCredencial extends FabricaBase<CredencialDTO, Credencial> {
	public Credencial converterParaDominio(CredencialDTO credencialDto) {
		Credencial credencial = new Credencial(credencialDto.getUsuario(),
				credencialDto.getSenha());
		return credencial;
	}

	@Override
	public CredencialDTO converterParaDTO(Credencial objetoDominio) {
		// TODO Auto-generated method stub
		return null;
	}
}

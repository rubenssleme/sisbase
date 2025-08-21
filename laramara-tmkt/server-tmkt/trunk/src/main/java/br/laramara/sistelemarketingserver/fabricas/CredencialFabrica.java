package br.laramara.sistelemarketingserver.fabricas;

import br.laramara.sistelemarketingcommons.dtos.seguranca.CredencialDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.Credencial;

public class CredencialFabrica  extends FabricaBase<CredencialDTO, Credencial> {
	public Credencial converterParaDominio(CredencialDTO credencialDto) {
		Credencial credencial = new Credencial(credencialDto.getUsuario(), credencialDto.getSenha(),
				new RamalFabrica().converterParaDominio(credencialDto.getRamalDto()));
		return credencial;
	}

	@Override
	public CredencialDTO converterParaDTO(Credencial objetoDominio) {
		return null;
	}
}

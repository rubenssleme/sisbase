package br.laramara.sistelemarketingserver.dominio.seguranca;

import br.laramara.sistelemarketingcommons.dtos.BairroDTO;
import br.laramara.sistelemarketingserver.dominio.Bairro;

public class ContextoBairro {

	public static Bairro fabricarBairro() {
		Bairro bairro = new Bairro();
		bairro.setNome("Barra Funda");
		return bairro;
	}

	public static BairroDTO fabricarBairroDto() {
		BairroDTO bairro = new BairroDTO();
		bairro.setNome("Barra Funda");
		return bairro;
	}
}

package br.laramara.sistelemarketingserver.dominio.seguranca;

import br.laramara.sistelemarketingcommons.dtos.seguranca.CredencialDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;

public class ContextoCredencial {

	public static CredencialDTO construirPauloBandeira() {
		return new CredencialDTO("paulo.bandeira", "1234", new RamalDTO("6489"));
	}

	public static CredencialDTO construirPriscilaBandeira() {
		return new CredencialDTO("priscila.bandeira", "1234", new RamalDTO("6489"));
	}

	public static CredencialDTO construirSenhaErrada() {
		return new CredencialDTO("paulo.bandeira", "adsakjd", new RamalDTO("6489"));
	}
}

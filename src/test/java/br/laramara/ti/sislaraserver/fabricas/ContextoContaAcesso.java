package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;

public class ContextoContaAcesso {

	public static ContaAcesso fabricarComTodosOsDados() {
		return new ContaAcesso(new Long(1), "paulo", "1234", new Perfil(
				new Long(1), "Administraodor"));
	}
}

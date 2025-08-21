package br.laramara.ti.sismovimentacaoserver.fabricas;

import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;

public class ContextoContaAcesso {

	public static ContaAcesso fabricarComTodosOsDados() {
		return new ContaAcesso(new Long(1), "paulo", "1234", new Perfil(
				new Long(1), "Administraodor"));
	}
}

package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;

public interface RepositorioCEP {
	public EnderecoCEP obterPorCEP(String cep);
}

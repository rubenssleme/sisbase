package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;

public interface RepositorioPais {
	public List<Pais> obterTodos();

	public EnderecoCEP carregarPais(EnderecoCEP enderecoCEP);
}

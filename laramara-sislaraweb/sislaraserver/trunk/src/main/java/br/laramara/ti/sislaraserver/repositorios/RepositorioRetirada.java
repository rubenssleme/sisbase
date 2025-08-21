package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.retirada.Retirada;
import br.laramara.ti.sislaraserver.dominio.retirada.StatusRetirada;

public interface RepositorioRetirada {
	public List<Retirada> obterRetiradas(Long prontuario, StatusRetirada statusRetirada);

	public Retirada salvar(Retirada retirada);
}

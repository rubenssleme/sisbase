package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.RedeEncaminhamento;

public interface RepositorioRedeEncaminhamento {
	public List<RedeEncaminhamento> obterTodos();
}

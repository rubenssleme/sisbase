package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.TipoConstrucao;

public interface RepositorioTipoConstrucao {
	public List<TipoConstrucao> obterTodos();
}

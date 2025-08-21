package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;

public interface RepositorioTipificacaoServico {
	public List<TipificacaoServico> obterTodos();
}

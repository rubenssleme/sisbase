package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Habitacao;

public interface RepositorioHabitacao {
	public List<Habitacao> obterTodos();
}

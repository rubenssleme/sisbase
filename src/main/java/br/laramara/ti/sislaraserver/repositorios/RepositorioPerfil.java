package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;

public interface RepositorioPerfil {
	public List<Perfil> obterTodos();

	public Perfil salvar(Perfil perfil);
}

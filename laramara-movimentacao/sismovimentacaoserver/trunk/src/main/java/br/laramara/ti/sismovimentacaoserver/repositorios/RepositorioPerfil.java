package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;

public interface RepositorioPerfil {
	public List<Perfil> obterTodos();

	public Perfil salvar(Perfil perfil);
}

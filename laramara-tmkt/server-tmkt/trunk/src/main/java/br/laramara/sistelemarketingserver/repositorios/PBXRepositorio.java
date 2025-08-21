package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;

public interface PBXRepositorio {
	public List<EventoTelefonia> obterEventosAposId(Long id);
}

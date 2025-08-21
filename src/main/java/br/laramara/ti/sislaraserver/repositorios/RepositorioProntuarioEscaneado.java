package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.ProntuarioEscaneado;

public interface RepositorioProntuarioEscaneado {
	public List<ProntuarioEscaneado> obterPorId(Long prontuario);

	public ProntuarioEscaneado obterProntuarioEscaneado(Long prontuario,
			ProntuarioEscaneado prontuarioEscaneado);
}

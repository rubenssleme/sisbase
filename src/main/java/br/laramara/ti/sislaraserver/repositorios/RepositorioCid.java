package br.laramara.ti.sislaraserver.repositorios;

import br.laramara.ti.sislaraserver.dominio.usuario.Cid;

public interface RepositorioCid {
	public Cid obterPorCid(String cid);
}

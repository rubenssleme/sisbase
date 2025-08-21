package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.atendimento.AcaoConduta;

public interface RepositorioAcaoConduta {
	public List<AcaoConduta> obterAcaoCondutasNaoProcessadas();
	public AcaoConduta salvar(AcaoConduta acaoConduta);
}

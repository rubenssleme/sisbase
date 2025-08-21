package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;

public interface RepositorioEspera {
	public Espera obterPorId(Long id);
	public String obterObsHistoricas(InformacaoEssencial informacaoEssencial);
	public List<Espera> obterPor(EspecificacaoPesquisaEspera especificacaoPesquisaEspera);
	public List<Espera> obterTodos();
	public Espera salvar(Espera espera);
}

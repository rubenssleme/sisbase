package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;

public interface RepositorioDemanda {

	public Demanda obterPorId(Long id);

	public List<Demanda> obterPor(
			EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda);

	public List<Demanda> salvar(List<Demanda> demanda);
	
	public Demanda salvar(Demanda demanda);
}

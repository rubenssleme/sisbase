package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;

public interface RepositorioTipoAtendimento {
	public List<TipoAtendimento> obterTodos();

	public DescricaoTipoAtendimento obterDescricaoTipoAtendimentoServicoSocial();
	
	public DescricaoTipoAtendimento obterDescricaoTipoAvaliacaoFuncional();
	
	public Modulo obterModuloExcessoDeFalta();

	public DescricaoTipoAtendimento obterDescricaoAvaliacaoDeServicoEmOftalmologia();

	public Modulo obterModuloAtendimentoEspecificoEspecialidado();

	public Modulo obterModuloTriagemOftalmologica();

	public Modulo obterAvaliacaoETriagem();
}

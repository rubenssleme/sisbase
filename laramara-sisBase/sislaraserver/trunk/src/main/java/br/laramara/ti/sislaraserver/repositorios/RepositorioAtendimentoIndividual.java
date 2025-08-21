package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;

public interface RepositorioAtendimentoIndividual {
	public List<AtendimentoIndividual> obterPor(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual);
	
	public List<AtendimentoIndividual> obterPor(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual,
			int quantidade);

	public AtendimentoIndividual salvar(AtendimentoIndividual atendimento);

	public AtendimentoIndividual obterPorId(Long id);

	public boolean existeAtendimentoIndividualComUsuarioPrimeiraVez(AtendimentoIndividual atendimentoIndividual);

	public String obterResumoIntegracao(String prontuario);

	public boolean existeAtendimentoAvaliacaoFuncionalComFrequenciaATUltimos6Meses(AtendimentoIndividual atendimentoIndividual);
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.ArquivoDisponivel;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;

public interface RepositorioArquivo {
	public void salvar(AtendimentoIndividual atendimentoIndividual);
	
	public void salvar(AtendimentoGrupo atendimentoGrupo);
	
	public void salvar(Demanda demanda);
	
	public Arquivo obterArquivoAtendimentoIndividual(AtendimentoIndividual atendimentoIndividual, Arquivo arquivo);

	public Arquivo obterArquivoAtendimentoGrupo(AtendimentoGrupo atendimentoGrupo, Arquivo arquivo);
	
	public Arquivo obterArquivoAtendimentoUsuario(AtendimentoUsuario atendimentoUsuario, Arquivo arquivo);
	
	public Arquivo obterArquivoDocumentoSolicitacaoDoacao(Demanda demanda, Arquivo arquivo);
	
	public List<ArquivoDisponivel> obterArquivosDisponiveis(String dadosPesquisa, boolean somenteGrupos);
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;
import br.laramara.ti.sislaraserver.dominio.solicitacao.Elaborador;
import br.laramara.ti.sislaraserver.dominio.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoPesquisaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesRepositorioSolicitacaoRelatorio extends
		TestesIntegracaoAbstrato {

	public TestesRepositorioSolicitacaoRelatorio() {
		super("DadosTestesRepositorioSolicitacaoRelatorio.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_solicitacao_relatorio_salva_alterada_e_obtida_com_sucesso() {
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio.setNomeSolicitante("TESTE");
		solicitacaoRelatorio.setRgSolicitante("1234");
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoSolicitacao(new ContaAcesso(
						new Long(1), "a", "a", new Perfil()));
		solicitacaoRelatorio.setElaborador(Elaborador.OFTALMOLOGIA);
		solicitacaoRelatorio.setUsuario(ContextoUsuario
				.fabricaUsuarioComTodosOsDadosEProntuario());
		

		RepositorioSolicitacaoRelatorio repositorio = Registro
				.obterRepositorioSolicitacaoRelatorio();

		SolicitacaoRelatorio solicitacaoRelatorioSalva = repositorio
				.salvar(solicitacaoRelatorio);
		
		solicitacaoRelatorioSalva.setNomeSolicitante("TESTE ALTERADO");

		SolicitacaoRelatorio solicitacaoRelatorioAlterada = repositorio
				.salvar(solicitacaoRelatorioSalva);

		SolicitacaoRelatorio solicitacaoObtida = repositorio
				.obterPorId(solicitacaoRelatorioAlterada.getId());

		Assert.assertNotNull(solicitacaoObtida);
		Assert.assertEquals(solicitacaoRelatorioAlterada.getNomeSolicitante(),
				"TESTE ALTERADO");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_solicitacao_relatorio_pesquisa_com_especificacao() {
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(new Long(12222));

		EspecificacaoPesquisaSolicitacaoRelatorio especificacao = ContextoEspecificacaoPesquisaSolicitacaoRelatorio
				.fabricarDominioComTodosOsDados();

		RepositorioSolicitacaoRelatorio repositorio = Registro
				.obterRepositorioSolicitacaoRelatorio();

		List<SolicitacaoRelatorio> solicitacoesRelatoriosObtidos = repositorio
				.obterPor(especificacao);

		Assert.assertEquals(solicitacoesRelatoriosObtidos.size(), 1);
	}
}

package br.laramara.ti.sislaraserver.dominio.solicitacao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesSolicitacaoRelatorio {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_validacao_sem_erro_de_obrigatoriedade() {
		List<FinalidadeRelatorio> tiposRelatorios = new ArrayList<>();
		tiposRelatorios.add(FinalidadeRelatorio.INSS);
		
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio.setUsuario(ContextoUsuario
				.fabricarUsuarioComTodosOsDados());
		solicitacaoRelatorio.setNomeSolicitante("JOSEP");
		solicitacaoRelatorio.setRgSolicitante("1234");
		solicitacaoRelatorio.setObs("OBS");
		solicitacaoRelatorio.setQuantidadeRelatoriosEmitidos("12");
		solicitacaoRelatorio.setQuantidadeRelatoriosEntregues("124");
		solicitacaoRelatorio.setStatusDaEntrega(StatusSolicitacaoRelatorio.ENTREGUE_PARA_FAMILIA);
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoEntrega(new ContaAcesso());
		solicitacaoRelatorio.setFinalidadesRelatorios(tiposRelatorios);
		solicitacaoRelatorio.setElaborador(Elaborador.ORTOPTICA);

		solicitacaoRelatorio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(solicitacaoRelatorio.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_validacao_com_erro_de_obrigatoriedade() {
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio.setContaAcessoResponsavelPorOperacaoEmissaoPorProfissional(new ContaAcesso());

		solicitacaoRelatorio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(solicitacaoRelatorio.validado());
		Assert.assertEquals(solicitacaoRelatorio.obterNumeroErros(), 6);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_validacao_com_erro_de_entrega_duplicada_recepcao() {
		List<FinalidadeRelatorio> tiposRelatorios = new ArrayList<>();
		tiposRelatorios.add(FinalidadeRelatorio.INSS);
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio.setStatusDaEntrega(StatusSolicitacaoRelatorio.ENTREGUE_PARA_RECEPCAO);
		solicitacaoRelatorio.setContaAcessoResponsavelPorOperacaoEntrega(new ContaAcesso());
		solicitacaoRelatorio.setStatusDaEntrega(StatusSolicitacaoRelatorio.ENTREGUE_PARA_RECEPCAO);
		solicitacaoRelatorio.setContaAcessoResponsavelPorOperacaoEntrega(new ContaAcesso());
		solicitacaoRelatorio.setFinalidadesRelatorios(tiposRelatorios);
		solicitacaoRelatorio.setElaborador(Elaborador.ORTOPTICA);


		solicitacaoRelatorio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(solicitacaoRelatorio.validado());
		Assert.assertEquals(solicitacaoRelatorio.obterNumeroErros(), 4);
	}


	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_validacao_com_erro_de_tamanho_maximo() {
		List<FinalidadeRelatorio> tiposRelatorios = new ArrayList<>();
		tiposRelatorios.add(FinalidadeRelatorio.INSS);
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio.setUsuario(ContextoUsuario
				.fabricarUsuarioComTodosOsDados());
		solicitacaoRelatorio.setNomeSolicitante(ContextoGenerico.obterGrande());
		solicitacaoRelatorio.setRgSolicitante(ContextoGenerico.obterGrande());
		solicitacaoRelatorio.setObs(ContextoGenerico.obterGrande());
		solicitacaoRelatorio.setFinalidadesRelatorios(tiposRelatorios);
		solicitacaoRelatorio.setStatusDaEntrega(StatusSolicitacaoRelatorio.ENTREGUE_PARA_CORREIOS);
		solicitacaoRelatorio.setQuantidadeRelatoriosEntregues("32");
		solicitacaoRelatorio.setContaAcessoResponsavelPorOperacaoEntrega(new ContaAcesso());
		solicitacaoRelatorio.setElaborador(Elaborador.ORTOPTICA);


		solicitacaoRelatorio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(solicitacaoRelatorio.validado());
		Assert.assertEquals(solicitacaoRelatorio.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_no_status_solicitado() {
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoSolicitacao(new ContaAcesso());

		Assert.assertTrue(solicitacaoRelatorio.estaSolicitado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_no_status_encaminhado_pela_recepcao() {
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoEncaminhamentoRecepcao(new ContaAcesso());

		Assert.assertTrue(solicitacaoRelatorio.estaEncaminhadoPelaRecepcao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_no_status_emitido_pelo_profissional() {
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoEmissaoPorProfissional(new ContaAcesso());

		Assert.assertTrue(solicitacaoRelatorio.estaEmitidoPeloProfissional());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_no_status_entregue() {
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio
				.setStatusDaEntrega(StatusSolicitacaoRelatorio.ENTREGUE_PARA_CORREIOS);
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoEntrega(new ContaAcesso());

		Assert.assertTrue(solicitacaoRelatorio.estaEntregue());
	}
}

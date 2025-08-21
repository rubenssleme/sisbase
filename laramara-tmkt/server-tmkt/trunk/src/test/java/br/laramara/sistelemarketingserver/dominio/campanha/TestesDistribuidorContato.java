package br.laramara.sistelemarketingserver.dominio.campanha;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.TestesIntegracaoAbstrato;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuidorContato;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;
import br.laramara.sistelemarketingserver.utilitarios.Registro;

public class TestesDistribuidorContato extends TestesIntegracaoAbstrato {

	protected TestesDistribuidorContato() {
		super("DadosTestesDistribuidorContato.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void distribuidor_contato_distribui_com_sucesso() {
		DistribuidorContato distribuidorContato = Registro.obterDistribuidorContato();
		distribuidorContato.limparDistribuicoesSelecionadas();
		distribuidorContato.distribuirContatos();

		// Está vinculado à campanha, porém não é operador
		List<DistribuicaoContato> distribuicaoContatosCarlos = distribuidorContato
				.obterDistribuicoes(ContextoContaAcesso.fabricarContaAcessoCarlos());

		List<DistribuicaoContato> distribuicaoContatosPaulo = distribuidorContato
				.obterDistribuicoes(ContextoContaAcesso.fabricarContaAcessoPaulo());

		// Está vinculado à campanha, porém o usuário está inativo
		List<DistribuicaoContato> distribuicaoContatosPriscila = distribuidorContato
				.obterDistribuicoes(ContextoContaAcesso.fabricarContaAcessoPriscila());

		// Não está vinculado a alguma campanha
		List<DistribuicaoContato> distribuicaoContatosTereza = distribuidorContato
				.obterDistribuicoes(ContextoContaAcesso.fabricarContaAcessoTereza());

		Assert.assertEquals(distribuicaoContatosCarlos.size(), 0);
		Assert.assertEquals(distribuicaoContatosPaulo.size(), 4);
		Assert.assertEquals(distribuicaoContatosPriscila.size(), 0);
		Assert.assertEquals(distribuicaoContatosTereza.size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void distribuidor_contato_obtem_contato_com_sucesso() {
		DistribuidorContato distribuidorContato = Registro.obterDistribuidorContato();
		distribuidorContato.limparDistribuicoesSelecionadas();
		distribuidorContato.distribuirContatos();

		DistribuicaoContato distribuicaoContatoIndisponivelParaCarlos = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoCarlos());
		DistribuicaoContato distribuicaoContatoParaPauloPrimeiraVez = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoPaulo());
		DistribuicaoContato distribuicaoContatoParaPauloEmAtuacao = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoPaulo());
		DistribuicaoContato distribuicaoContatoParaRubens = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoRubens());
		DistribuicaoContato distribuicaoContatoIndisponivelParaMarcos = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoMarcos());

		Assert.assertNotNull(distribuicaoContatoParaPauloPrimeiraVez);
		Assert.assertNotNull(distribuicaoContatoParaPauloEmAtuacao);
		Assert.assertEquals(distribuicaoContatoParaPauloPrimeiraVez, distribuicaoContatoParaPauloEmAtuacao);
		Assert.assertNotNull(distribuicaoContatoParaRubens);
		Assert.assertNull(distribuicaoContatoIndisponivelParaCarlos);
		Assert.assertNull(distribuicaoContatoIndisponivelParaMarcos);
		Assert.assertNotEquals(distribuicaoContatoParaPauloPrimeiraVez.getContato(), distribuicaoContatoParaRubens.getContato());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void distribuidor_contato_obtem_contato_liberado_apos_selecao_por_outro_operador_com_sucesso() {
		DistribuidorContato distribuidorContato = Registro.obterDistribuidorContato();
		distribuidorContato.limparDistribuicoesSelecionadas();
		distribuidorContato.distribuirContatos();

		DistribuicaoContato distribuicaoContatoParaRubensLiberado = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoRubens());
		DistribuicaoContato distribuicaoContatoParaRubensSelecionado = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoRubens());
		DistribuicaoContato distribuicaoContatoParaPauloSelecionado = distribuidorContato
				.obterContato(ContextoContaAcesso.fabricarContaAcessoPaulo());

		Assert.assertNotNull(distribuicaoContatoParaRubensLiberado);
		Assert.assertNotNull(distribuicaoContatoParaRubensSelecionado);
		Assert.assertNotNull(distribuicaoContatoParaPauloSelecionado);
	}
}

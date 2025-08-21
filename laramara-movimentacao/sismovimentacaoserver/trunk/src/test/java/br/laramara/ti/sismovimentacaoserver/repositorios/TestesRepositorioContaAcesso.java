package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sismovimentacaoserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Credencial;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ExtensaoArquivo;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Perfil;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public class TestesRepositorioContaAcesso extends TestesIntegracaoAbstrato {

	public TestesRepositorioContaAcesso() {
		super("DadosTestesRepositorioContaAcesso.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_conta_acesso_localiza_uma_conta_acesso_existente_a_partir_de_uma_credencial_valida()
			throws Exception {
		ContaAcesso contaAcessoEsperado = new ContaAcesso(new Long(3), "Paulo", "82159876", new Perfil(new Long(1), "Administrador"));
		RepositorioContaAcesso repositorioContaAcesso = Registro
				.obterRepositorioContaAcesso();
		Credencial credencial = new Credencial("Paulo", "ca99498816e8e429036944e00aaf6594");

		ContaAcesso contaAcessoObtido = repositorioContaAcesso.obterContaAcesso(credencial);

		Assert.assertEquals(contaAcessoEsperado, contaAcessoObtido);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_de_conta_acesso_obtem_todas_contas_acesso()
			throws Exception {
		RepositorioContaAcesso repositorioContaAcesso = Registro
				.obterRepositorioContaAcesso();

		List<ContaAcesso> contaAcessoObtido = repositorioContaAcesso.obterTodos();

		Assert.assertEquals(contaAcessoObtido.size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_conta_acesso_retorna_uma_conta_acesso_com_permissao_exigida(){
		Permissao permisaoExigida = Permissao.CONTA_ACESSO_EDICAO;
		RepositorioContaAcesso repositorioContaAcesso = Registro
		.obterRepositorioContaAcesso();
		
		Credencial credencial = new Credencial("Paulo", "ca99498816e8e429036944e00aaf6594");

		ContaAcesso contaAcessoObtido = repositorioContaAcesso.obterContaAcesso(credencial);
		
		Assert.assertTrue(contaAcessoObtido.possuiPermissao(permisaoExigida));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_conta_acesso_retorna_uma_conta_acesso_sem_permissao_exigida(){
		Permissao permisaoExigida = Permissao.DESBLOQUEIO_TELA;
		RepositorioContaAcesso repositorioContaAcesso = Registro
		.obterRepositorioContaAcesso();
		
		Credencial credencial = new Credencial("Paulo", "ca99498816e8e429036944e00aaf6594");

		ContaAcesso contaAcessoObtido = repositorioContaAcesso.obterContaAcesso(credencial);
		
		Assert.assertFalse(contaAcessoObtido.possuiPermissao(permisaoExigida));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void repositorio_conta_acesso_altera_extensao_padrao_de_relatorio() {
		RepositorioContaAcesso repositorioContaAcesso = Registro
				.obterRepositorioContaAcesso();

		Credencial credencial = new Credencial("Paulo",
				"ca99498816e8e429036944e00aaf6594");

		ContaAcesso contaAcessoAntesAlteracao = repositorioContaAcesso
				.obterContaAcesso(credencial);
		contaAcessoAntesAlteracao.marcarExtensaoDeRelatorioXls();

		repositorioContaAcesso.salvar(contaAcessoAntesAlteracao);

		ContaAcesso contaAcessoAposAlteracao = repositorioContaAcesso
				.obterContaAcesso(credencial);

		Assert.assertEquals(contaAcessoAposAlteracao.getExtensaoRelatorios(),
				ExtensaoArquivo.xls);
	}
}

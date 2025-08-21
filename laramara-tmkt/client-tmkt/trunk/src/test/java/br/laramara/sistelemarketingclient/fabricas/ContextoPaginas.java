package br.laramara.sistelemarketingclient.fabricas;

import br.laramara.sistelemarketingclient.paginas.PaginaCampanhaEdicao;
import br.laramara.sistelemarketingclient.paginas.PaginaCampanhaGerenciamento;
import br.laramara.sistelemarketingclient.paginas.PaginaContaAcessoEdicao;
import br.laramara.sistelemarketingclient.paginas.PaginaContaAcessoGerenciamento;
import br.laramara.sistelemarketingclient.paginas.PaginaListagem;
import br.laramara.sistelemarketingclient.paginas.PaginaLogin;
import br.laramara.sistelemarketingclient.paginas.PaginaNivelPermissaoEdicao;
import br.laramara.sistelemarketingclient.paginas.PaginaNivelPermissaoGerenciamento;
import br.laramara.sistelemarketingclient.paginas.PaginaPrincipal;

public class ContextoPaginas {
		
	public static PaginaLogin obterPaginaLogin() {
		return new PaginaLogin();
	}

	public static PaginaPrincipal obterPaginaPrincipal() {
		return new PaginaPrincipal();
	}

	public static PaginaNivelPermissaoGerenciamento obterPaginaNivelPermissaoGerenciamento() {
		return new PaginaNivelPermissaoGerenciamento(false);
	}

	public static PaginaNivelPermissaoEdicao obterPaginaNivelPermissaoEdicao() {
		return new PaginaNivelPermissaoEdicao(PaginaNivelPermissaoGerenciamento.URL_EDICAO_NIVEL_PERMISSAO_GERENTE, false);
	}
	
	public static PaginaNivelPermissaoEdicao obterPaginaNivelPermissaoEdicaoIncluirNova() {
		return new PaginaNivelPermissaoEdicao(PaginaNivelPermissaoEdicao.URL_EDICAO_NIVEL_PERMISSAO_NOVA, false);
	}
	
	public static PaginaNivelPermissaoGerenciamento obterPaginaNivelPermissaoGerenciamentoForcado() {
		return new PaginaNivelPermissaoGerenciamento(true);
	}

	public static PaginaNivelPermissaoEdicao obterPaginaNivelPermissaoEdicaoForcado() {
		return new PaginaNivelPermissaoEdicao(PaginaNivelPermissaoGerenciamento.URL_EDICAO_NIVEL_PERMISSAO_GERENTE, true);
	}

	public static PaginaContaAcessoGerenciamento obterPaginaContaAcessoGerenciamento() {
		return new PaginaContaAcessoGerenciamento(false);
	}

	public static PaginaContaAcessoEdicao obterPaginaContaAcessoEdicaoIncluirNovo() {
		return new PaginaContaAcessoEdicao(PaginaContaAcessoEdicao.URL_EDICAO_CONTA_ACESSO_NOVO, false);
	}
	
	public static PaginaContaAcessoEdicao obterPaginaContaAcessoEdicao() {
		return new PaginaContaAcessoEdicao(PaginaContaAcessoGerenciamento.URL_EDICAO_CONTA_ACESSO_CARLOS, false);
	}
	
	public static PaginaContaAcessoEdicao obterPaginaContaAcessoEdicaoForcado() {
		return new PaginaContaAcessoEdicao(PaginaContaAcessoGerenciamento.URL_EDICAO_CONTA_ACESSO_CARLOS, true);
	}

	public static PaginaContaAcessoGerenciamento obterPaginaContaAcessoGerenciamentoForcado() {
		return new PaginaContaAcessoGerenciamento(true);
	}

	public static PaginaCampanhaEdicao obterPaginaCampanhaEdicao() {
		return new PaginaCampanhaEdicao(PaginaCampanhaGerenciamento.URL_EDICAO_CAMPANHA_A, false);
	}
	
	public static PaginaCampanhaEdicao obterPaginaCampanhaEdicaoIncluirNova() {
		return new PaginaCampanhaEdicao(PaginaCampanhaEdicao.URL_EDICAO_NOVA, false);
	}
	
	public static PaginaCampanhaEdicao obterPaginaCampanhaEdicaoForcado() {
		return new PaginaCampanhaEdicao(PaginaCampanhaEdicao.URL_EDICAO_NOVA, true);
	}

	public static PaginaCampanhaGerenciamento obterPaginaCampanhaGerenciamento() {
		return new PaginaCampanhaGerenciamento(false);
	}

	public static PaginaCampanhaGerenciamento obterPaginaCampanhaGerenciamentoForcado() {
		return new PaginaCampanhaGerenciamento(true);
	}

	public static PaginaListagem obterPaginaListagemForcado() {
		return new PaginaListagem(true);
	}
}

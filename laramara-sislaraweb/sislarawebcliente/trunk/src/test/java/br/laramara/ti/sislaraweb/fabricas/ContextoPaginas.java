package br.laramara.ti.sislaraweb.fabricas;

import br.laramara.ti.sislaraweb.paginas.PaginaCadastroUsuarioExterno;
import br.laramara.ti.sislaraweb.paginas.PaginaDetalheCurso;
import br.laramara.ti.sislaraweb.paginas.PaginaInicial;
import br.laramara.ti.sislaraweb.paginas.PaginaInscricao;
import br.laramara.ti.sislaraweb.paginas.PaginaLogin;
import br.laramara.ti.sislaraweb.paginas.PaginaPerfil;
import br.laramara.ti.sislaraweb.paginas.PaginaSolicitacaoRecuperacaoSenha;

public class ContextoPaginas {
	public final static String SENHA_PADRAO = "1234";
	public final static String SENHA_INVALIDA = "4321";

	public static PaginaLogin obterPaginaLogin(String email, String senha) {
		return obterPaginaLogin().logarComoUsuarioInvalido(email, senha);
	}

	public static PaginaInicial obterPaginaInicial(String email) {
		return obterPaginaLogin().logarComoUsuarioExterno(email, SENHA_PADRAO);
	}

	public static PaginaSolicitacaoRecuperacaoSenha obterPaginaSolicitacaoRecuperacaoSenha() {
		return obterPaginaLogin().abrirPaginaSolicitacaoRecuperacaoSenha();
	}

	public static PaginaCadastroUsuarioExterno obterPaginaCadastroUsuarioExterno() {
		return obterPaginaLogin().abrirPaginaCadastroUsuarioExterno();
	}

	private static PaginaLogin obterPaginaLogin() {
		return new PaginaLogin();
	}

	public static PaginaDetalheCurso obterPaginaDetalheCurso() {
		return new PaginaDetalheCurso();
	}
	
	public static PaginaPerfil obterPaginaPerfil(String email) {
		obterPaginaLogin().logarComoUsuarioExterno(email, SENHA_PADRAO);

		PaginaDetalheCurso paginaDetalheCurso = obterPaginaDetalheCurso();
		paginaDetalheCurso.clicarEmSaibaMaisNoCursoEnsinoEAplicacaoSistemaBrailleemNivelBasico();

		return paginaDetalheCurso.clicarEmInscreverSeSemPerfilPreenchido();
	}
	
	public static PaginaInscricao obterPaginaInscricaoDoCursoEnsinoEAplicacaoSistemaBrailleEmNivelBasico(String email) {
		obterPaginaLogin().logarComoUsuarioExterno(email, SENHA_PADRAO);

		PaginaDetalheCurso paginaDetalheCurso = obterPaginaDetalheCurso();
		paginaDetalheCurso.clicarEmSaibaMaisNoCursoEnsinoEAplicacaoSistemaBrailleemNivelBasico();

		return paginaDetalheCurso.clicarEmInscreverSeComPerfilPreenchido();
	}
	
	public static PaginaInscricao obterPaginaInscricaoDoCursoAvaliacaoFuncionalEVisao(String email) {
		obterPaginaLogin().logarComoUsuarioExterno(email, SENHA_PADRAO);

		PaginaDetalheCurso paginaDetalheCurso = obterPaginaDetalheCurso();
		paginaDetalheCurso.clicarEmSaibaMaisNoCursoAvaliacaoFuncionalEVisao();

		return paginaDetalheCurso.clicarEmInscreverSeComPerfilPreenchido();
	}
}

package br.laramara.ti.sislaraweb.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.laramara.ti.sislaraweb.controladores.Controlador;
import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public class PaginaDetalheCurso extends PaginaBase {
	private static final String TITULO_NOME_CURSO = "Ensino e Aplicação do Sistema Braille em Nível Básico";
	
	@FindBy(id = "outrosCursos")
	private WebElement outrosCursos;
	@FindBy(id = "nomeCurso")
	private WebElement nomeCurso;
	@FindBy(id = "outrosCursos:0:botaoSaibaMais")
	private WebElement botaoSaibaMaisEnsinoEAplicacaoSistemaBrailleemNivelBasico;
	@FindBy(id = "outrosCursos:1:botaoSaibaMais")
	private WebElement botaoSaibaMaisAvaliacaoFuncionalEVisao;
	@FindBy(id = "botaoInscrever")
	private WebElement botaoInscreverSe;
	@FindBy(id = "mensagem")
	private WebElement mensagem;
	
	public PaginaDetalheCurso() {
		DriverUtils.ir(Controlador.PAGINA_PRINCIPAL + Controlador.PAGINA_DETALHE_CURSO);
	}
	
	public boolean paginaValida() {
		return paginaValida(TITULO_NOME_CURSO);
	}

	public void clicarEmSaibaMaisNoCursoEnsinoEAplicacaoSistemaBrailleemNivelBasico() {
		DriverUtils.clicar(botaoSaibaMaisEnsinoEAplicacaoSistemaBrailleemNivelBasico);
	}
	
	public void clicarEmSaibaMaisNoCursoAvaliacaoFuncionalEVisao() {
		DriverUtils.clicar(botaoSaibaMaisAvaliacaoFuncionalEVisao);
	}

	public void clicarEmInscrever() {
		DriverUtils.clicar(botaoInscreverSe);
	}
	
	public boolean invalido() {
		return sucesso(mensagem, Controlador.MENSAGEM_ERRO_SELECIONE_UM_CURSO);
	}

	public PaginaInscricao clicarEmInscreverSeComPerfilPreenchido() {
		return abrir(botaoInscreverSe, PaginaInscricao.class);
	}
	
	public PaginaPerfil clicarEmInscreverSeSemPerfilPreenchido() {
		return abrir(botaoInscreverSe, PaginaPerfil.class);
	}

}
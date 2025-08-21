package br.laramara.ti.sislaraweb.paginas;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.laramara.ti.sislaraweb.controladores.Controlador;
import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public class PaginaInscricao extends PaginaBase {
	private  static final String MENSAGEM_ERRO_INSCRICAO_REALIZADA_SEM_SUCESSO = "Erro: o campo Nome para Crachá é obrigatório.\n"+
			"Erro: o campo Área de Formação é obrigatório.\n"+
			"Erro: o campo Local de Trabalho é obrigatório.\n"+
			"Erro: o campo Cargo / Função é obrigatório.\n"+
			"Erro: o campo CEP é obrigatório.\n"+
			"Erro: o campo Endereço é obrigatório.\n"+
			"Erro: o campo Número é obrigatório.\n"+
			"Erro: o campo Bairro é obrigatório.\n"+
			"Erro: o campo UF é obrigatório.\n"+
			"Erro: o campo Município é obrigatório.";
	
	private static final String MENSAGEM_SUCESSO_INSCRICAO_REALIZADA_COM_SUCESSO = "Inscrição realizada com sucesso.";
	
	@FindBy(id = "cep")
	private WebElement cep;
	@FindBy(id = "endereco")
	private WebElement endereco;
	@FindBy(id = "numero")
	private WebElement numero;
	@FindBy(id = "complemento")
	private WebElement complemento;
	@FindBy(id = "bairro")
	private WebElement bairro;
	@FindBy(id = "uf")
	private WebElement uf;
	@FindBy(id = "municipio")
	private WebElement municipio;
	@FindBy(id = "botaoConfirmar")
	private WebElement botaoConfirmar;
	@FindBy(id = "mensagem")
	private WebElement mensagem;
	@FindBy(xpath = "//*[@id=\"opcaoEnderecoResidencialOuOutro:1\"]")
	private WebElement opcaoUsarOutroEndereco;
	@FindBy(xpath = "//*[@id=\"opcaoEnderecoResidencialOuOutro:0\"]")
	private WebElement opcaoUsarEnderecoJaExistente;
	@FindBy(id = "dados-sobre-deficiencia")
	private List<WebElement> dadosSobreDeficiencia;
	@FindBy(id = "nomeParaCracha")
	private WebElement nomeParaCracha;
	@FindBy(id = "observacoes")
	private WebElement observacoes;
	@FindBy(id = "areaFormacao")
	private WebElement areaFormacao;
	@FindBy(id = "localTrabalho")
	private WebElement localTrabalho;
	@FindBy(id = "cargoOuFuncao")
	private WebElement cargoOuFuncao;
	@FindBy(id = "usuarioExternoPossuiCadeiraDeRodas")
	private WebElement usuarioExternoPossuiCadeiraDeRodas;
	@FindBy(id = "usuarioExternoPossuiCaoGuia")
	private WebElement usuarioExternoPossuiCaoGuia;
	@FindBy(id = "valorTotalAlmocoIncluso")
	private WebElement valorTotalAlmocoIncluso;

	public PaginaInscricao() {
		DriverUtils.ir(Controlador.PAGINA_PRINCIPAL + Controlador.PAGINA_INSCRICOES);
	}

	public void preencherEndereco(String cep, String endereco, String numero, String complemento, String bairro,
			String uf, String municipio) {
		DriverUtils.adicionarTexto(this.cep, cep);
		DriverUtils.adicionarTexto(this.endereco, endereco);
		DriverUtils.adicionarTexto(this.numero, numero);
		DriverUtils.adicionarTexto(this.complemento, complemento);
		DriverUtils.adicionarTexto(this.bairro, bairro);
		DriverUtils.selecionarOpcao(this.uf, uf);
		DriverUtils.selecionarOpcao(this.municipio, municipio);
	}

	public void confirmar() {
		DriverUtils.clicarEEsperar(botaoConfirmar);
	}

	public boolean sucesso() {
		return sucesso(mensagem, MENSAGEM_SUCESSO_INSCRICAO_REALIZADA_COM_SUCESSO);
	}

	public boolean mensagemInvalida() {
		return sucesso(mensagem, MENSAGEM_ERRO_INSCRICAO_REALIZADA_SEM_SUCESSO);
	}

	public boolean paginaValida() {
		return super.paginaValida("SisLaraWeb - Inscrições");
	}

	public void clicarEmUsarOutroEndereco() {
		DriverUtils.clicar(opcaoUsarOutroEndereco);
		DriverUtils.esperarElementoSerMarcado(opcaoUsarOutroEndereco);
	}

	public void clicarEmUsarEnderecoJaExistente() {
		DriverUtils.clicar(opcaoUsarEnderecoJaExistente);
		DriverUtils.esperarElementoSerMarcado(opcaoUsarEnderecoJaExistente);
	}

	public void preencherNomeParaCracha(String nomeParaCracha) {
		DriverUtils.adicionarTexto(this.nomeParaCracha, nomeParaCracha);
	}
	
	public void preencherObservacoes(String observacoes) {
		DriverUtils.adicionarTexto(this.observacoes, observacoes);
	}
	
	public void preencherAreaFormacao(String areaFormacao) {
		DriverUtils.adicionarTexto(this.areaFormacao, areaFormacao);
	}
	
	public void preencherLocalTrabalho(String localTrabalho) {
		DriverUtils.adicionarTexto(this.localTrabalho, localTrabalho);
	}
	
	public void preencherCargoOuFuncao(String cargoOuFuncao) {
		DriverUtils.adicionarTexto(this.cargoOuFuncao, cargoOuFuncao);
	}
	
	public boolean possuiDadosSobreDeficiencia() {
		return dadosSobreDeficiencia.size() != 0;
	}

	public void marcarUsuarioExternoPossuiCadeiraDeRodas(boolean usuarioExternoPossuiCadeiraDeRodas) {
		DriverUtils.marcar(this.usuarioExternoPossuiCadeiraDeRodas, usuarioExternoPossuiCadeiraDeRodas);
	}

	public void marcarUsuarioExternoPossuiCaoGuia(boolean usuarioExternoPossuiCaoGuia) {
		DriverUtils.marcar(this.usuarioExternoPossuiCaoGuia, usuarioExternoPossuiCaoGuia);
	}

	public void marcarValorTotalAlmocoIncluso(boolean valorTotalAlmocoIncluso) {
		DriverUtils.marcar(this.valorTotalAlmocoIncluso, valorTotalAlmocoIncluso);
	}
	
	public boolean valorTotalAlmocoVisivel() {
		return DriverUtils.elementoEstaVisivel(valorTotalAlmocoIncluso);
	}
}

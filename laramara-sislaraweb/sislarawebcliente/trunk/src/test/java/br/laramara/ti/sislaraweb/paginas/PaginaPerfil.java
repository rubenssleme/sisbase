package br.laramara.ti.sislaraweb.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.laramara.ti.sislaraweb.controladores.Controlador;
import br.laramara.ti.sislaraweb.utilitarios.DriverUtils;

public class PaginaPerfil extends PaginaBase {
	private static final String MENSAGEM_ERRO_EDICAO_USUARIO_EXTERNO_INVALIDO = "Erro: o campo Nome Completo é obrigatório.\n" + 
			"Erro: o campo Data de Nascimento é obrigatório.\n" + 
			"Erro: o campo CPF é obrigatório.\n" + 
			"Erro: o campo RG / RNE é obrigatório.\n" + 
			"Erro: o campo Telefone Celular é obrigatório.\n" + 
			"Erro: o campo CEP é obrigatório.\n" + 
			"Erro: o campo Endereço é obrigatório.\n" + 
			"Erro: o campo Número é obrigatório.\n" + 
			"Erro: o campo Bairro é obrigatório.\n" + 
			"Erro: o campo UF é obrigatório.\n" + 
			"Erro: o campo Município é obrigatório.";

	@FindBy(id = "nomeCompleto")
	private WebElement nomeCompleto;
	@FindBy(id = "dataNascimento")
	private WebElement dataNascimento;
	@FindBy(id = "cpf")
	private WebElement cpf;
	@FindBy(id = "rgRne")
	private WebElement rgRne;
	@FindBy(id = "telefoneCelular")
	private WebElement telefoneCelular;
	@FindBy(id = "outroTelefone")
	private WebElement outroTelefone;
	@FindBy(id = "autorizoRecebimentoInformativo")
	private WebElement autorizoRecebimentoInformativo;
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
	@FindBy(id = "botaoSalvar")
	private WebElement botaoSalvar;
	@FindBy(id = "mensagem")
	private WebElement mensagem;

	public PaginaPerfil() {
		DriverUtils.ir(Controlador.PAGINA_PRINCIPAL + Controlador.PAGINA_PERFIL);
	}

	public boolean paginaValida() {
		return super.paginaValida("SisLaraWeb - Perfil");
	}

	public boolean estaNaPaginaInscricoes() {
		return super.paginaValida("SisLaraWeb - Inscrições");
	}

	public void preencherInformacoesPessoais(String nomeCompleto, String dataNascimento, String cpf, String rgRne) {
		DriverUtils.adicionarTexto(this.nomeCompleto, nomeCompleto);
		DriverUtils.adicionarTexto(this.dataNascimento, dataNascimento);
		DriverUtils.adicionarTexto(this.cpf, cpf);
		DriverUtils.adicionarTexto(this.rgRne, rgRne);
	}

	public void preencherDadosParaContato(String telefoneCelular, String outroTelefone,
			boolean autorizoRecebimentoInformativo) {
		DriverUtils.adicionarTexto(this.telefoneCelular, telefoneCelular);
		DriverUtils.adicionarTexto(this.outroTelefone, outroTelefone);
		DriverUtils.marcar(this.autorizoRecebimentoInformativo, autorizoRecebimentoInformativo);
	}

	public void preencherEnderecoResidencial(String cep, String endereco, String numero, String complemento,
			String bairro, String uf, String municipio) {
		DriverUtils.adicionarTexto(this.cep, cep);
		DriverUtils.adicionarTexto(this.endereco, endereco);
		DriverUtils.adicionarTexto(this.numero, numero);
		DriverUtils.adicionarTexto(this.complemento, complemento);
		DriverUtils.adicionarTexto(this.bairro, bairro);
		DriverUtils.selecionarOpcao(this.uf, uf);
		DriverUtils.selecionarOpcao(this.municipio, municipio);
	}

	public void salvar() {
		DriverUtils.clicarEEsperar(botaoSalvar);
	}

	public boolean sucesso() {
		return super.paginaValida("SisLaraWeb - Página Inicial");
	}

	public boolean mensagemInvalida() {
		return sucesso(mensagem, MENSAGEM_ERRO_EDICAO_USUARIO_EXTERNO_INVALIDO);
	}
}
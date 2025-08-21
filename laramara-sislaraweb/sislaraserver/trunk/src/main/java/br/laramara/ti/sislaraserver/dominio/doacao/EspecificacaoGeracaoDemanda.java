package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.ValidavelCpf;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public class EspecificacaoGeracaoDemanda extends Validavel implements
		ValidavelObrigatoriedadeETamanhoMaximo {

	private Projeto projeto;
	private List<RecursoEDescricaoRecurso> recursosEDescricaoRecurso;
	private Grupo grupo;
	private Usuario usuario;
	private PreCadastro preCadastro;
	private boolean cartelaDeSelos;
	private List<DocumentoSolicitacaoDoacao> documentosSolicitacaoDoacao;
	private transient ContaAcesso contaAcessoResponsavelPorOperacao;
	private Calendar dataExpectativa;
	
	public EspecificacaoGeracaoDemanda() {
		documentosSolicitacaoDoacao = new ArrayList<>();
		recursosEDescricaoRecurso = new ArrayList<>();
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<RecursoEDescricaoRecurso> getRecursosEDescricaoRecurso() {
		return recursosEDescricaoRecurso;
	}

	public void setRecursosEDescricaoRecurso(List<RecursoEDescricaoRecurso> recursosEDescricaoRecurso) {
		this.recursosEDescricaoRecurso = recursosEDescricaoRecurso;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}
	
	public List<DocumentoSolicitacaoDoacao> getDocumentosSolicitacaoDoacao() {
		return documentosSolicitacaoDoacao;
	}

	public void setDocumentosSolicitacaoDoacao(List<DocumentoSolicitacaoDoacao> documentosSolicitacaoDoacao) {
		this.documentosSolicitacaoDoacao = documentosSolicitacaoDoacao;
	}

	public boolean isCartelaDeSelos() {
		return cartelaDeSelos;
	}

	public void setCartelaDeSelos(boolean cartelaDeSelos) {
		this.cartelaDeSelos = cartelaDeSelos;
	}

	public void atribuirContaAcessoResponsavel(ContaAcesso contaAcesso) {
		this.contaAcessoResponsavelPorOperacao = contaAcesso;
	}
	
	public void setDataExpectativa(String dataExpectativa) {
		this.dataExpectativa = DataHoraUtils.obterDataValidaInvalidaOuNulo(dataExpectativa);
	}
	
	public String getDataExpectativa() {
		return DataHoraUtils.formatarData(dataExpectativa);
	}

	private void validarDocumentoSolicitacaoDoacao() {
		documentosSolicitacaoDoacao.forEach(
				documentoSolicitacaoDoacao -> documentoSolicitacaoDoacao.validarObrigatoriedadeETamanhoMaximoDeDados());
		if (documentosSolicitacaoDoacao.stream().anyMatch(documento -> !documento.validado())) {
			adicionarErro("Existe um documento inv�lido.");
		}
	}

	private void validarObrigatoriedadeCpf() {
		if (possuiPreCadastroOuUsuario()) {
			ValidavelCpf validalCpf = (ValidavelCpf) obterInformacaoEssencial();
			if (validalCpf != null) {
				validalCpf.validarObrigatoriedadeDeCpf();
				if (!validalCpf.validado()) {
					adicionarErro(validalCpf.obterDescricaoErros());
				}
			}
		}
	}

	public boolean possuiUsuario() {
		return usuario != null;
	}
	
	public boolean possuiPreCadastro() {
		return preCadastro != null;
	}
	
	private boolean possuiPreCadastroOuUsuario() {
		return possuiUsuario() || possuiPreCadastro();
	}

	public InformacaoEssencial obterInformacaoEssencial() {
		return usuario != null ? usuario.getInformacaoEssencial() : preCadastro.getInformacaoEssencial();
	}

	private boolean possuiMaisDeUmRecurso() {
		return recursosEDescricaoRecurso.size() > 1;
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (recursosEDescricaoRecurso.isEmpty()) {
			adicionarErro("Insira um Recurso.");
		}
		if ((usuario == null && preCadastro == null) || (usuario != null && preCadastro != null)) {
			adicionarErro("Insira um Usu�rio ou Pr� Cadastro.");
		}
		if (dataExpectativa != null && DataHoraUtils.dataInvalida(dataExpectativa)){
			adicionarErro("Insira uma data de expectativa v�lida.");
		}
		validarRecursoEDescricaoRecurso();
		validarObrigatoriedadeCpf();
		validarDocumentoSolicitacaoDoacao();
		validarObrigatoriedadeDeCartelaDeSelos();
		validarRestricaoDeGeracaoPorContaAcesso();
		validarObrigatoriedadeDeDadosDoUsuarioInterno();
	}

	private void validarObrigatoriedadeDeDadosDoUsuarioInterno() {
		if (possuiUsuario()){
			if (!usuario.possuiDataNascimento()){
				adicionarErro("Insira a data de nascimento do usu�rio.");
			}
			if (!usuario.possuiCpf()){
				adicionarErro("Insira o CPF do usu�rio.");
			}
			if (usuario.naoPossuiRg()){
				adicionarErro("Insira o RG do usu�rio.");
			}
			if (!usuario.possuiEmail()){
				adicionarErro("Insira o email do usu�rio.");
			}
			if (!usuario.possuiEndereco()){
				adicionarErro("Insira o endere�o do usu�rio.");
			}
			if (usuario.eMenorIdadeENaoPossuiFamiliarPrincipalResponsavelComCPFRGeEmail()){
				adicionarErro("Insira um familiar que � principal respons�vel com CPF, RG e Email.");
			}
		}
	}

	private void validarRecursoEDescricaoRecurso() {
		for (RecursoEDescricaoRecurso recursoEDescricaoRecurso : recursosEDescricaoRecurso) {
			recursoEDescricaoRecurso.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (!recursoEDescricaoRecurso.validado()) {
				adicionarErro(recursoEDescricaoRecurso.obterDescricaoErros());
			}
		}
	}

	private void validarRestricaoDeGeracaoPorContaAcesso() {
		if (!cartelaDeSelos && contaAcessoResponsavelPorOperacao != null && !Profissional.obterProfissionaisReponsaveisPeloServicoSocial()
				.contains(contaAcessoResponsavelPorOperacao.getProfissional())) {
			adicionarErro("Somente profissionais do Servi�o Social podem realizar essa opera��o.");
		}
	}

	private List<NomeDocumento> obterDocumentosExistentes() {
		return documentosSolicitacaoDoacao.stream().map(documento -> documento.getNomeDocumento())
				.collect(Collectors.toList());
	}
	
	private void validarObrigatoriedadeDeCartelaDeSelos() {
		if (cartelaDeSelos) {
			if (!(obterDocumentosExistentes().contains(NomeDocumento.AUTODECLARACAO_COMO_USUARIO_DO_SISTEMA_BRAILLE)
					|| obterDocumentosExistentes()
							.contains(NomeDocumento.INDICACAO_PARA_USO_PREFERENCIAL_DO_SISTEMA_BRAILLE))) {
				adicionarErro("Insira uma autodeclara��o ou indica��o de usu�rio de sistema braille.");
			}
			if (possuiPreCadastroOuUsuario() && !obterInformacaoEssencial().possuiDataNascimento()) {
				adicionarErro("Solicitante n�o possui data de nascimento.");
			}
			if (possuiPreCadastroOuUsuario() && !obterInformacaoEssencial().possuiIdadeIgualOuSuperior5Anos()) {
				adicionarErro("Solicitante n�o possui idade m�nima de 5 anos.");
			}
			if (!(obterDocumentosExistentes().contains(NomeDocumento.DECLARACAO_DE_MATRICULA))){
				adicionarErro("Insira uma c�pia da declara��o de matr�cula.");
			}
			if (!(obterDocumentosExistentes().contains(NomeDocumento.FOTO_RECENTE))){
				adicionarErro("Insira uma foto recente.");
			}
			if (!(obterDocumentosExistentes().contains(NomeDocumento.RG_CPF))){
				adicionarErro("Insira uma c�pia do CPF e RG.");
			}
			if (!(obterDocumentosExistentes().contains(NomeDocumento.COMPROVANTE_DE_ENDERECO))){
				adicionarErro("Insira uma c�pia do comprovante de endere�o.");
			}
			if (!(obterDocumentosExistentes().contains(NomeDocumento.HISTORICO_DO_SOLICITANTE))){
				adicionarErro("Insira uma c�pia do hist�rico do solicitante.");
			}
			if (possuiMaisDeUmRecurso()){
				adicionarErro("N�o � poss�vel incluir mais de um recurso na cartela de selos.");
			}
		}
	}

	@Override
	public String toString() {
		return "EspecificacaoGeracaoDemanda [projeto=" + projeto + ", recursos=" + recursosEDescricaoRecurso + ", grupo="
				+ (grupo != null ? grupo.obterNomeGrupoETurma() : "") + ", usuario=" + usuario + ", preCadastro="
				+ preCadastro + ", cartelaDeSelos=" + cartelaDeSelos + ", documentosSolicitacaoDoacao="
				+ documentosSolicitacaoDoacao + "]";
	}
}

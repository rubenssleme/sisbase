package br.laramara.ti.sislaraweb.controladores;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.laramara.ti.sislaracommons.dtos.MecanismoTransferenciaDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.NumeroUtils;
import br.laramara.ti.sislaraweb.servicos.rest.ServicoSisLaraClient;
import br.laramara.ti.sislaraweb.utilitarios.Configuracao;
import br.laramara.ti.sislaraweb.utilitarios.Mensagem;

public abstract class Controlador {
	public static final String MENSAGEM_ERRO_SELECIONE_UM_CURSO = "Erro: Selecione um curso.";
	protected static final String MENSAGEM_ERRO_INFORME_UM_LOGIN_VALIDO = "Erro: Informe um e-mail válido.";
	protected static final String MENSAGEM_ERRO_DURANTE_O_CADASTRO_AS_SENHAS_NAO_ESTAO_IGUAIS = "Erro durante cadastro de nova senha. As senhas não estão iguais.";
	
	protected static final String TIPO_ENDERECO_COMERCIAL = "COMERCIAL";
	protected static final String TIPO_ENDERECO_RESIDENCIAL = "RESIDENCIAL";
	
	private static final String CHAVE_TOKEN = "token";
	private static final String CHAVE_USUARIO_EXTERNO = "usuario_externo";
	private static final String CHAVE_IDGRUPOSELECIONADO = "id_grupo_selecionado";
	private static final String CHAVE_DETALHE_CURSO = "detalhe_curso";
	private static final String CHAVE_ENDERECO = "endereco";

	protected static final String OPCAO_PADRAO = "Selecione";

	public static String PAGINA_PRINCIPAL = "http://localhost:"
			+ Configuracao.obterConfiguracao(Configuracao.PORTA_APLICACAO) + "/sislaraweb/";

	private String PAGINA_LOGIN = "/login.xhtml";
	private String PAGINA_INICIAL = "/paginaInicial.xhtml";
	public static String PAGINA_INSCRICOES = "/inscricoes.xhtml";
	public static String PAGINA_PERFIL = "/perfil.xhtml";
	public static String PAGINA_DETALHE_CURSO = "/detalheCurso.xhtml";

	@Inject
	protected ServicoSisLaraClient servicoSisLaraClient;
	
	protected void sair() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		redirecionarParaPaginaInicialSeNaoEstiverLogado();
	}

	protected void exibirResultado(MecanismoTransferenciaDTO resultado) {
		if (resultado.sucesso()) {
			Mensagem.exibirMensagem(resultado.getMensagem());
		} else {
			Mensagem.exibirMensagemErro(resultado.getMensagem());
		}
	}

	protected boolean estaComItemSelecionado(String campo) {
		return campo != null && !campo.isEmpty() && !campo.equals(OPCAO_PADRAO);
	}

	private void redirecionar(String pagina) {
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.getRequestContextPath() + pagina);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void redirecionarParaPaginaInicialSeNaoEstiverLogado() {
		if (!estaLogado()) {
			redirecionarParaPaginaLogin();
		} else if (perfilPreenchidoEpossuiCursoSelecionado()) {
			redirecionarParaPaginaInscricoes();
		} else if (perfilPreenchidoENaoPossuiCursoSelecionado()) {
			redirecionarParaPaginaInicial();
		} else if (perfilNaoPreenchido()) {
			redirecionarParaPaginaPerfil();
		}
	}

	private void redirecionarParaPaginaLogin() {
		redirecionar(PAGINA_LOGIN);
	}

	private void redirecionarParaPaginaPerfil() {
		redirecionar(PAGINA_PERFIL);
	}

	protected void redirecionarParaPaginaInicial() {
		redirecionar(PAGINA_INICIAL);
	}

	protected void redirecionarParaPaginaInscricoes() {
		redirecionar(PAGINA_INSCRICOES);
	}

	private boolean perfilNaoPreenchido() {
		return estaLogado() && !perfilPreenchido() && !estaNaPagina(PAGINA_PERFIL);
	}

	private boolean perfilPreenchidoENaoPossuiCursoSelecionado() {
		return estaLogado() && perfilPreenchido() && !possuiCursoSelecionado() && !estaNaPagina(PAGINA_INICIAL);
	}

	private boolean perfilPreenchidoEpossuiCursoSelecionado() {
		return estaLogado() && perfilPreenchido() && possuiCursoSelecionado() && !estaNaPagina(PAGINA_INSCRICOES);
	}

	private boolean estaNaPagina(String url) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().equals(url);
	}

	private boolean perfilPreenchido() {
		return servicoSisLaraClient.consultarPerfilPreenchido(new TokenDTO(getToken())).isPerfilPreenchido();
	}

	public boolean estaLogado() {
		return getToken() != null && !getToken().isEmpty();
	}

	protected void setToken(String token) {
		obterSessao().put(CHAVE_TOKEN, token);
	}

	protected String getToken() {
		return obterSessao().get(CHAVE_TOKEN) != null ? (String) obterSessao().get(CHAVE_TOKEN) : "";
	}

	public UsuarioExternoDTO getUsuarioExternoDto() {
		return (UsuarioExternoDTO) obterSessao().get(CHAVE_USUARIO_EXTERNO);
	}

	public DetalheCursoDTO getDetalheCursoDto() {
		DetalheCursoDTO detalheCursoDto = (DetalheCursoDTO) obterSessao().get(CHAVE_DETALHE_CURSO);

		if (detalheCursoDto == null) {
			detalheCursoDto = new DetalheCursoDTO();
			obterSessao().put(CHAVE_DETALHE_CURSO, detalheCursoDto);
		}
		return detalheCursoDto;
	}

	public void setDetalheCursoDto(DetalheCursoDTO detalheCursoDto) {
		obterSessao().put(CHAVE_DETALHE_CURSO, detalheCursoDto);
	}

	public EnderecoDTO getEnderecoDto() {
		return (EnderecoDTO) obterSessao().get(CHAVE_ENDERECO);
	}

	public void setEnderecoDto(EnderecoDTO enderecoDto) {
		obterSessao().put(CHAVE_ENDERECO, enderecoDto);
	}
	
	public Long getIdGrupoSelecionado() {
		return (Long) obterSessao().get(CHAVE_IDGRUPOSELECIONADO);
	}

	public void setIdGrupoSelecionado(Long idGrupoSelecionado) {
		obterSessao().put(CHAVE_IDGRUPOSELECIONADO, idGrupoSelecionado);
	}

	public boolean possuiCursoSelecionado() {
		return getIdGrupoSelecionado() != null && !NumeroUtils.numeroLongoInvalido(getIdGrupoSelecionado());
	}

	public void setUsuarioExternoDto(UsuarioExternoDTO usuarioExternoDto) {
		obterSessao().put(CHAVE_USUARIO_EXTERNO, usuarioExternoDto);
	}

	private Map<String, Object> obterSessao() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

}

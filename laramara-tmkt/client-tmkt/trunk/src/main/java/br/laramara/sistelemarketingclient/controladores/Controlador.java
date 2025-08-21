package br.laramara.sistelemarketingclient.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import br.laramara.sistelemarketingclient.servicos.rest.ServicoSistelemarketingClient;
import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComDescricao;
import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComIdEDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.IdentificacaoPermissao;
import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoResultadoDTO;

public abstract class Controlador {
	
	@Inject
	protected ServicoSistelemarketingClient servicoSisLaraClient;
	
	private static final String CHAVE_TOKEN = "token";
	private static final String CHAVE_CONTA_ACESSO = "conta_acesso";
	
	public static final String URL_INICIO_PARAMETRO = "?";
	public static final String URL_FIM_PARAMETRO = "=";
	
	public static final String PARAMETRO_ID = "id";
	public static final String PARAMETRO_MENSAGEM_INFORMACAO = "mensagem_informacao";
	
	public static final String TITULO_MENSAGEM_INFORMACAO = "Informações: ";
	public static final String TITULO_MENSAGEM_ERRO = "Erro: ";
	
	public static final String PAGINA_LOGIN = "login.xhtml";
	public static final String PAGINA_PRINCIPAL = "principal.xhtml";
	public static final String PAGINA_SEM_AUTORIZACAO = "semAutorizacao.xhtml";
	public static final String PAGINA_NIVEL_GERENCIAMENTO = "nivelGerenciamento.xhtml";
	public static final String PAGINA_NIVEL_EDICAO = "nivelEdicao.xhtml";
	public static final String PAGINA_CONTA_ACESSO_GERENCIAMENTO = "contaAcessoGerenciamento.xhtml";
	public static final String PAGINA_CONTA_ACESSO_EDICAO = "contaAcessoEdicao.xhtml";
	public static final String PAGINA_CAMPANHA_GERENCIAMENTO = "campanhaGerenciamento.xhtml";
	public static final String PAGINA_CAMPANHA_EDICAO = "campanhaEdicao.xhtml";

	private static final String ID_AREA_MENSAGEM = "mensagem";
	private static final String MENSAGEM_SUCESSO = "Salvo com sucesso.";
	protected static final String OPCAO_PADRAO = "Selecione uma opção";

	protected String obterParametro(String chave) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.containsKey(chave) ? params.get(chave) : "";
	}
	
	protected String obterParametroId() {
		return obterParametro(PARAMETRO_ID);
	}
	
	protected boolean possuiParametroId() {
		return !obterParametroId().isEmpty();
	}
	
	protected void exibirMensagemInformacao() {
		exibirMensagem(FacesMessage.SEVERITY_INFO, obterMensagemInformacao(), null);
	}
	
	public void exibirMensagemInformacaoForcandoAtualizacao(String mensagem) {
		exibirMensagemInformacao(mensagem);
		atualizarMensagem();
	}	
		
	public void exibirMensagemInformacao(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem, null);
	}
	
	protected void exibirMensagemErro(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, TITULO_MENSAGEM_ERRO + mensagem, null);
	}
	
	private void atualizarMensagem() {
		PrimeFaces.current().ajax().update(ID_AREA_MENSAGEM);
	}
	
	private void exibirMensagem(Severity severity, String titulo, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, mensagem));
	}
	
	protected void redirecionarNivelGerenciamento() {
		redirecionar(PAGINA_NIVEL_GERENCIAMENTO, MENSAGEM_SUCESSO);
	}
	
	protected void redirecionarLogin() {
		redirecionar(PAGINA_LOGIN);
	}
	
	protected void redirecionarPrincipal() {
		redirecionar(PAGINA_PRINCIPAL);
	}

	protected void redirecionarContaAcessoGerenciamento() {
		redirecionar(PAGINA_CONTA_ACESSO_GERENCIAMENTO, MENSAGEM_SUCESSO);
	}
	
	protected void redirecionarCampanhaGerenciamento() {
		redirecionar(PAGINA_CAMPANHA_GERENCIAMENTO, MENSAGEM_SUCESSO);
	}
	
	protected void redirecionarNivelEdicao() {
		redirecionar(PAGINA_NIVEL_EDICAO);
	}	
	
	protected void redirecionarSemAutorizacao() {
		redirecionar(PAGINA_SEM_AUTORIZACAO);
	}	

	protected void redirecionarContaAcessoEdicao() {
		redirecionar(PAGINA_CONTA_ACESSO_EDICAO);
	}
	
	protected void redirecionarCampanhaEdicao() {
		redirecionar(PAGINA_CAMPANHA_EDICAO);
	}
	
	protected String obterMensagemInformacao() {
		return obterParametro(PARAMETRO_MENSAGEM_INFORMACAO);
	}
	
	protected boolean possuiMensagemInformacao() {
		return !obterParametro(PARAMETRO_MENSAGEM_INFORMACAO).isEmpty();
	}
	
	protected void redirecionar(String pagina, String mensagem) {
		redirecionar(pagina + URL_INICIO_PARAMETRO + PARAMETRO_MENSAGEM_INFORMACAO + URL_FIM_PARAMETRO + mensagem);
	}

	protected boolean possuiPermissaoVisualizarContaAcesso() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_CONTA_ACESSO_VISUALIZAR);
	}
	
	protected boolean possuiPermissaoVisualizarCampanha() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_CAMPANHA_VISUALIZAR);
	}

	protected boolean possuiPermissaoVisualizarNivel() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_NIVEL_VISUALIZAR);
	}
	
	protected boolean possuiPermissaoEditarCampanha() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_CAMPANHA_EDITAR);
	}
		
	protected boolean possuiPermissaoVisualizarListagem() {
		return possuiAutorizacao(IdentificacaoPermissao.ID_PERMISSAO_LISTAGEM_VISUALIZAR);
	}

	protected void redirecionar(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (Exception e) {
		}
	}

	protected void gravarContaAcessoEToken(ContaAcessoDTO contaAcessoDto, String token) {
		obterSessao().put(CHAVE_CONTA_ACESSO, contaAcessoDto);
		obterSessao().put(CHAVE_TOKEN, token);
	}

	private Map<String, Object> obterSessao() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	protected void sair() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	protected ContaAcessoDTO obterContaAcesso() {
		return (ContaAcessoDTO) obterSessao()
				.get(CHAVE_CONTA_ACESSO);
	}

	protected String obterToken() {
		return (String) obterSessao().get(CHAVE_TOKEN);
	}

	protected boolean possuiAutorizacao(Long idPermissao) {
		try {
			PermissaoResultadoDTO resultadoListagemDtos = servicoSisLaraClient.permissaoObter(obterToken());
			List<PermissaoDTO> permissoesDto = resultadoListagemDtos.getPermissoesDto();
			return permissoesDto.contains(new PermissaoDTO(idPermissao));
		} catch (Exception e) {
			return false;
		}
	}
	
	protected List<SelectItem> converterDTOParaSelectItemComIdEDescricao(
			List<? extends ItemComboboxComIdEDescricao> modelosDto) {
		return converterDTOParaSelectItemComIdEDescricao(modelosDto,
				(item -> new SelectItem(item.getId(), item.toString())));
	}
	
	protected List<SelectItem> converterDTOParaSelectItemComIdEDescricao(
			List<? extends ItemComboboxComIdEDescricao> modelosDto,
			Function<ItemComboboxComIdEDescricao, SelectItem> funcao) {
		List<SelectItem> listas = new ArrayList<>();
		for (ItemComboboxComIdEDescricao modeloDTO : modelosDto) {
			listas.add((SelectItem) funcao.apply(modeloDTO));
		}
		return listas;
	}
	
	protected List<SelectItem> converterDTOParaSelectItemComDescricao(List<? extends ItemComboboxComDescricao> modelosDto) {
		List<SelectItem> listas = new ArrayList<>();
		for (ItemComboboxComDescricao modeloDTO : modelosDto) {
			listas.add(new SelectItem(modeloDTO.toString()));
		}
		return listas;
	}
	
	protected ModeloDTO obterDTOApartirDeDescricao(List<? extends ItemComboboxComDescricao> modeloDtosComDescricao,
			String descricao) {
		return (ModeloDTO) modeloDtosComDescricao.stream()
				.filter(modeloDtoComDescricao -> modeloDtoComDescricao.toString().equals(descricao)).findFirst()
				.orElse(null);
	}
	
	protected ModeloDTO obterDTOApartirDeId(List<? extends ItemComboboxComIdEDescricao> modeloDtosComIdEDescricao,
			String id) {
		return (ModeloDTO) modeloDtosComIdEDescricao.stream()
				.filter(modeloComIdEDescricao -> modeloComIdEDescricao.getId().equals(Long.valueOf(id))).findFirst()
				.orElse(null);
	}
	
	protected boolean estaComItemSelecionado(String campo) {
		return !campo.equals(OPCAO_PADRAO);
	}
}

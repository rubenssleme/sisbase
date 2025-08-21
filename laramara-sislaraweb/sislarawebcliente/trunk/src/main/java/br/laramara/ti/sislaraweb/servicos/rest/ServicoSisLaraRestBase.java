package br.laramara.ti.sislaraweb.servicos.rest;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.laramara.ti.sislaracommons.dtos.MecanismoTransferenciaDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.servicos.rest.ContratoRest;
import br.laramara.ti.sislaraweb.utilitarios.Configuracao;

public abstract class ServicoSisLaraRestBase {
	protected static final String ERRO_SERVICO_INDISPONIVEL_TENTE_NOVAMENTE_MAIS_TARDE = "Serviço indisponível. Tente novamente mais tarde.";

	private Logger logger = Logger.getLogger(getClass().getName());
	
	protected static Client client;
	protected Client obterCliente() {
		if (client == null) {
			client = new ResteasyClientBuilder().build();
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			
			client.register(mapper);
		}

		return client;
	}

	private <T> Entity<T> criarEntidade(T objeto) {
		return Entity.json(objeto);
	}
	protected <T> ModeloDTO executarRequisicaoGet(String url, MecanismoTransferenciaDTO resultado) {
		try {
			Builder request = obterCliente().target(url).request(MediaType.APPLICATION_JSON);
			Response response = request.get();
			resultado = response.readEntity(resultado.getClass());
		} catch (Exception e) {
			logger.severe("Falha ao executar requisição GET na url " + url + ".\nDetalhes: " + e.getMessage());
			resultado.adicionarErro(ERRO_SERVICO_INDISPONIVEL_TENTE_NOVAMENTE_MAIS_TARDE);
		}
		return resultado;
	}
	protected <T> ModeloDTO executarRequisicaoPost(String url, T solicitacao, MecanismoTransferenciaDTO resultado) {
		try {
			Builder request = obterCliente().target(url).request(MediaType.APPLICATION_JSON);
			Response response = request.post(criarEntidade(solicitacao));
			resultado = response.readEntity(resultado.getClass());
		} catch (Exception e) {
			logger.severe("Falha ao executar requisição POST na url " + url + ".\nDetalhes: " + e.getMessage());
			resultado.adicionarErro(ERRO_SERVICO_INDISPONIVEL_TENTE_NOVAMENTE_MAIS_TARDE);
		}
		return resultado;
	}

	protected String obterUrlDetalheCursoPorIdGrupo(Long idGrupo) {
		return obterUrlListagemDetalhesCursos() + idGrupo;
	}
	protected String obterUrlListagemDetalhesCursos() {
		return obterUrlBase() + ContratoRest.URL_DETALHE_CURSO_LISTAGEM;
	}
	protected String obterUrlCadastroUsuarioExterno() {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_CADASTRO;
	}
	
	protected String obterUrlEdicaoUsuarioExterno() {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_EDICAO;
	}
	
	protected String obterUrlConsultaPerfilPreenchidoUsuarioExterno() {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_CONSULTA_PERFIL_PREENCHIDO;
	}
	
	protected String obterUrlListagemUf() {
		return obterUrlBase() + ContratoRest.URL_UF_LISTAGEM;
	}
	
	protected String obterUrlCadastroInscricao() {
		return obterUrlBase() + ContratoRest.URL_INSCRICAO_CADASTRO;
	}
	
	protected String obterUrlListagemMunicipioPorUf(String uf) {
		return obterUrlBase() + ContratoRest.URL_MUNICIPIO_LISTAGEM_POR_UF
				.replace(ContratoRest.URL_PARAMETRO_UF, uf);
	}
	
	protected String obterUrlConsultaEnderecoPorCep(String cep) {
		return obterUrlBase() + ContratoRest.URL_ENDERECO_CONSULTA_POR_CEP
				.replace(ContratoRest.URL_PARAMETRO_CEP, cep);
	}
	
	protected String obterUrlConsultaUsuarioExternoPorToken(String token) {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_CONSULTA_POR_TOKEN
				.replace(ContratoRest.URL_PARAMETRO_TOKEN, token);
	}
	
	protected String obterUrlSolicitacaoRecuperacaoSenha() {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_SOLICITACAO_RECUPERACAO_SENHA;
	}

	protected String obterUrlAutenticacaoUsuarioExterno() {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_AUTENTICACAO;
	}

	protected String obterUrlAlteracaoSenha() {
		return obterUrlBase() + ContratoRest.URL_USUARIO_EXTERNO_CADASTRO_NOVA_SENHA;
	}

	private String obterUrlBase() {
		return Configuracao.obterConfiguracao(Configuracao.ENDERECO_SERVICO_EXTERNO);
	}
}

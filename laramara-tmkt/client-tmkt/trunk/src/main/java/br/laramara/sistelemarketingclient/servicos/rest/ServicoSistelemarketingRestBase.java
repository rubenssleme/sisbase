package br.laramara.sistelemarketingclient.servicos.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import br.laramara.sistelemarketingclient.utilitarios.Configuracao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.MecanismoTransferenciaDTO;
import br.laramara.sistelemarketingcommons.servicos.rest.ContratoRest;


public abstract class ServicoSistelemarketingRestBase {
	
	protected static final String ERRO_SERVICO_INDISPONIVEL = "Serviço indisponível. Contate o administrador do sistema.";

	protected static Client client;

	protected Client obterCliente() {
		if (client == null) {
			client = new ResteasyClientBuilder().build();
		}

		return client;
	}

	private <T> Entity<T> criarEntidade(T objeto) {
		return Entity.json(objeto);
	}

	protected String executarRequisicaoGet(String url) {
		Builder request = obterClienteRest(url);
		Response response = request.get();
		return response.readEntity(String.class);
	}
	
	protected <T> ModeloDTO executarRequisicaoGet(String url, MecanismoTransferenciaDTO resultado) {
		try {
			Builder request = obterClienteRest(url);
			Response response = request.get();
			resultado = response.readEntity(resultado.getClass());
		} catch (Exception e) {
			resultado.adicionarErro(ERRO_SERVICO_INDISPONIVEL);
		}
		return resultado;
	}

	private Builder obterClienteRest(String url) {
		return obterCliente().target(url).request(MediaType.APPLICATION_JSON);
	}

	protected <T> ModeloDTO executarRequisicaoPost(String url, T solicitacao, MecanismoTransferenciaDTO resultado) {
		try {
			Builder request = obterClienteRest(url);
			Response response = request.post(criarEntidade(solicitacao));
			resultado = response.readEntity(resultado.getClass());
		} catch (Exception e) {
			resultado.adicionarErro(ERRO_SERVICO_INDISPONIVEL);
		}
		return resultado;
	}

	private String obterUrlBase() {
		return Configuracao.obterConfiguracao(Configuracao.ENDERECO_SERVICO_EXTERNO);
	}

	protected String obterUrlContaAcessoEditar() {
		return obterUrlBase() + ContratoRest.URL_CONTA_ACESSO_EDITAR;
	}
	
	protected String obterUrlContaAcessoListar() {
		return obterUrlBase() + ContratoRest.URL_CONTA_ACESSO_LISTAR;
	}
	
	protected String obterUrlContaAcessoListarTodosOperadoresAtivos() {
		return obterUrlBase() + ContratoRest.URL_CONTA_ACESSO_LISTAR_TODOS_OPERADORES_ATIVOS;
	}
	
	protected String obterUrlContaAcessoObter() {
		return obterUrlBase() + ContratoRest.URL_CONTA_ACESSO_OBTER;
	}

	protected String obterUrlContaAcessoAutenticar() {
		return obterUrlBase() + ContratoRest.URL_CONTA_ACESSO_AUTENTICAR;
	}
	
	protected String obterUrlNivelListar() {
		return obterUrlBase() + ContratoRest.URL_NIVEL_LISTAR;
	}
	
	protected String obterUrlNivelObter() {
		return obterUrlBase() + ContratoRest.URL_NIVEL_OBTER;
	}
		
	protected String obterUrlNivelEditar() {
		return obterUrlBase() + ContratoRest.URL_NIVEL_EDITAR;
	}
	
	protected String obterUrlPermissaoListar() {
		return obterUrlBase() + ContratoRest.URL_PERMISSAO_LISTAR;
	}
	
	protected String obterUrlPermissaoObter() {
		 return obterUrlBase() + ContratoRest.URL_PERMISSAO_OBTER;
	}
	
	protected String obterUrlTurnoListar() {
		return obterUrlBase() + ContratoRest.URL_TURNO_LISTAR;
	}
	
	protected String obterUrlEstadoListar() {
		return obterUrlBase() + ContratoRest.URL_ESTADO_LISTAR;
	}
	
	protected String obterUrlMunicipioListar() {
		return obterUrlBase() + ContratoRest.URL_MUNICIPIO_LISTAR;
	}
	
	protected String obterUrlBairroListar() {
		return obterUrlBase() + ContratoRest.URL_BAIRRO_LISTAR;
	}
	
	protected String obterUrlCampanhaEditar() {
		return obterUrlBase() + ContratoRest.URL_CAMPANHA_EDITAR;
	}
	
	protected String obterUrlCampanhaObter() {
		return obterUrlBase() + ContratoRest.URL_CAMPANHA_OBTER;
	}
	
	protected String obterUrlCampanhaListar() {
		return obterUrlBase() + ContratoRest.URL_CAMPANHA_LISTAR;
	}
	
	protected String obterUrlCriterioValidar() {
		return obterUrlBase() + ContratoRest.URL_CRITERIO_VALIDAR;
	}
	
	protected String obterUrlAlocacaoOperadorValidar() {
		return obterUrlBase() + ContratoRest.URL_ALOCACAO_OPERADOR_VALIDAR;
	}
	
	protected String obterUrlStatusRamalObter() {
		return obterUrlBase() + ContratoRest.URL_STATUS_RAMAL_OBTER;
	}
	
	protected String obterUrlLigar() {
		return obterUrlBase() + ContratoRest.URL_LIGAR;
	}
	
	protected String obterUrlDistribuicaoContatoObter() {
		return obterUrlBase() + ContratoRest.URL_DISTRIBUICAO_CONTATO_OBTER;
	}
	
	protected String obterUrlRamalListar() {
		return obterUrlBase() + ContratoRest.URL_RAMAL_LISTAR;
	}
	
	protected String obterUrlDoacaoEditar() {
		return obterUrlBase() + ContratoRest.URL_DOACAO_EDITAR;
	}
	
	protected String obterUrlMetodoListar() {
		return obterUrlBase() + ContratoRest.URL_METODO_LISTAR;
	}
	
	protected String obterUrlValorDetalhadoValidar() {
		return obterUrlBase() + ContratoRest.URL_VALOR_DETALHADO_VALIDAR;
	}
}

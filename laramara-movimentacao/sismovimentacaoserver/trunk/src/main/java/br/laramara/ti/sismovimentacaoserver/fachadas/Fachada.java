package br.laramara.ti.sismovimentacaoserver.fachadas;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.Permissao;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaBase;
import br.laramara.ti.sismovimentacaoserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioContaAcesso;

public abstract class Fachada {

	protected final Logger logger;
	@Inject
	protected RepositorioContaAcesso repositorioContaAcesso;

	protected static final String MENSAGEM_STATUS_FINAL = "Status final alcançado.";
	protected static final String MENSAGEM_INSIRA_TIPO_DADO_PESQUISA = "Insira o tipo e dados para pesquisa.";
	protected static final String MENSAGEM_NAO_POSSUI_AUTORIZACAO = "Você não possui autorização para realizar a operação.";
	protected static final String MENSAGEM_DATA_INVALIDA = "Data inválida.";
	protected static final String MENSAGEM_OBRIGATORIEDADE_AGENDADO = "O agendamento não está no status Agendado.";
	protected static final String MENSAGEM_ESPERA_JA_FOI_AGENDADA = "A Espera já foi agendada.";
	public static final String GRUPO_ATIVO_JA_EXISTENTE = "O Grupo já está cadastrado e ativo. Não efetue cadastros duplicados.";
	public static final String MENSAGEM_DADOS_DESATUALIZADOS = "Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.";
	protected static final String MENSAGEM_SENHA_DIFERENTE = "A nova senha não foi confirmada.";
	protected static final String MENSAGEM_PERIODO_INVALIDO = "Insira um período válido.";
	
	protected Fachada(){
		this.logger = Logger.getLogger(getClass());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void gravarResultadoSeNaoForVazio(List<?> objetosDominio,
			FabricaBase fabrica, ResultadoListagemDTO resultado) {
		if (!objetosDominio.isEmpty()) {
			resultado.efetuadoComSucesso(fabrica
					.converterParaDTO(objetosDominio));
		} else {
			resultado.nenhumRegistroEncontrado();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ResultadoListagemDTO obterListagem(List<?> objetosDominio,
			FabricaBase fabrica, String descricaoDaListagem,
			ResultadoListagemDTO objetoResultado) {
		try {
			List<? extends ModeloDTO> objetosDto = fabrica
					.converterParaDTO(objetosDominio);
			objetoResultado.efetuadoComSucesso(objetosDto);
		} catch (Exception e) {
			String erro = "Ocorreu um erro durante a obtenção da lista de "
					+ descricaoDaListagem + ".";
			objetoResultado.adicionarErro(erro);
			logger.error(erro + " \nDetalhes: " + e);
		}
		return objetoResultado;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ResultadoDTO efetuarValidacao(ModeloDTO objetoDto,
			FabricaBase fabrica, String descricaoDaListagem,
			ResultadoDTO objetoResultado) {

		ResultadoDTO resultado = objetoResultado;
		try {
			ValidavelObrigatoriedadeETamanhoMaximo objetoDominioValidavel = (ValidavelObrigatoriedadeETamanhoMaximo) fabrica
					.converterParaDominio(objetoDto);
			objetoDominioValidavel
					.validarObrigatoriedadeETamanhoMaximoDeDados();

			if (objetoDominioValidavel.validado()) {
				resultado.efetuadoComSucesso((ModeloDTO) fabrica
						.converterParaDTO(objetoDominioValidavel));
			} else {
				resultado.adicionarErro(objetoDominioValidavel
						.obterDescricaoErros());
			}
		} catch (Exception e) {
			String erro = "Ocorreu um erro durante a validação do "
					+ descricaoDaListagem + ".";
			resultado.adicionarErro(erro);
			logger.error(erro + " \nDetalhes: " + e);
		}
		return resultado;
	}
	
	protected ContaAcesso obterContaAcesso(TokenDTO tokenDto) {
		return repositorioContaAcesso.obterPorToken(tokenDto.getToken());
	}
	
	protected ResultadoDTO verificarPermissaoEProcessar(
			OperacaoFachada operacao, Permissao permissao,
			ResultadoDTO resultadoDto, TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(permissao)) {
			resultadoDto = operacao.processar(contaAcesso, resultadoDto);
		} else {
			resultadoDto.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoDto;
	}
}

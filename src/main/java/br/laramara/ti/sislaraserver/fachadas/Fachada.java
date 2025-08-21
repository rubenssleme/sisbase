package br.laramara.ti.sislaraserver.fachadas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaBase;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.relatorios.GeradorRelatorio;
import br.laramara.ti.sislaraserver.relatorios.ModeloRelatorio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;

public abstract class Fachada {

	protected final Logger logger;
	@Inject
	protected RepositorioContaAcesso repositorioContaAcesso;
	@Inject
	private GeradorRelatorio geradorRelatorio;

	protected static final String MENSAGEM_INSIRA_TIPO_DADO_PESQUISA = "Insira o tipo e dados para pesquisa.";
	protected static final String MENSAGEM_NAO_POSSUI_AUTORIZACAO = "Voc� n�o possui autoriza��o para realizar a opera��o.";
	protected static final String MENSAGEM_NAO_E_POSSIVEL_REALIZAR_MUDANCA_DE_STATUS = "N�o � poss�vel realizar a mudan�a de status.";
	protected static final String MENSAGEM_OBRIGATORIEDADE_AGENDADO = "O agendamento n�o est� no status Agendado ou n�o possui motivo de cancelamento.";
	protected static final String MENSAGEM_OPERACAO_NAO_POSSIVEL_DEVIDO_BLOQUEIO_GRUPO = "N�o � poss�vel realizar essa opera��o devido ao bloqueio de grupo.";
	protected static final String MENSAGEM_MODULO_BLOQUEADO = "M�dulo / Atividade est� bloqueado.";
	protected static final String MENSAGEM_ESPERA_JA_FOI_AGENDADA_OU_MODULO_BLOQUEADO = "A Espera j� foi agendada ou o "
			+ MENSAGEM_MODULO_BLOQUEADO;
	public static final String GRUPO_ATIVO_JA_EXISTENTE = "O Grupo j� est� cadastrado e ativo. N�o efetue cadastros duplicados.";
	public static final String MENSAGEM_DADOS_DESATUALIZADOS = "Os dados foram alterados por outro usu�rio do sistema. Por favor, tente novamente.";
	protected static final String MENSAGEM_SENHA_DIFERENTE = "A nova senha n�o foi confirmada.";
	protected static final String MENSAGEM_PERIODO_INVALIDO = "Insira um per�odo v�lido.";
	public static final String MENSAGEM_BLOQUEIO_POR_FALTAS_EXCESSIVAS = "O Usu�rio est� bloqueado por excesso de faltas n�o justificadas.";
	public static final String MENSAGEM_BLOQUEIO_POR_EXISTENCIA_SIMULTANEA_TRIAGEM_E_AVALIACAO_OFTALMOLOGICA = "O Usu�rio n�o pode estar na espera para Triagem e Avalia��o Oftalmol�gica simultaneamente.";
		
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
			String erro = "Ocorreu um erro durante a obten��o da lista de "
					+ descricaoDaListagem + ".";
			objetoResultado.adicionarErro(erro);
			logger.error(erro + " \nDetalhes: " + e);
		}
		return objetoResultado;
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
	
	protected ArquivoDTO gerarRelatorio(Permissao permissao,
			ModeloRelatorio modeloRelatorio, Map<String, Object> parametros,
			TokenDTO tokenDto) {
		ArquivoDTO resultado = new ArquivoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(permissao)) {
			resultado = gerar(contaAcesso, parametros, modeloRelatorio);
		} else {
			resultado.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultado;
	}
	
	private ArquivoDTO gerar(ContaAcesso contaAcesso,
			Map<String, Object> parametros, ModeloRelatorio modeloRelatorio) {
		return new FabricaArquivo().converterParaDTO(geradorRelatorio
				.gerarRelatorio(contaAcesso, parametros, modeloRelatorio));
	}
		
	protected Map<String, Object> gerarParametros(Long numeroRecibo, String valorExtenso) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_id_recibo", numeroRecibo);
		parametros.put("param_valor_extenso", valorExtenso);
		return parametros;
	}
}

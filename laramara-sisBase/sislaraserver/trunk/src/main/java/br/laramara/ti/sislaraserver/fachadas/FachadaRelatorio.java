package br.laramara.ti.sislaraserver.fachadas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoVinculo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoVinculo;
import br.laramara.ti.sislaraserver.relatorios.GeradorRelatorio;
import br.laramara.ti.sislaraserver.relatorios.ModeloRelatorio;

@Component
public class FachadaRelatorio extends Fachada {

	private static String NOT = "not";
	
	@Inject
	private GeradorRelatorio geradorRelatorio;

	public ArquivoDTO gerarRelatorioFolhaDeRosto(Long prontuario,
			TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_FOLHA_DE_ROSTO,
				ModeloRelatorio.FOLHA_DE_ROSTO, gerarParametroProntuario(prontuario),
				tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioGeracaoAtendimentosIndividuasEGrupo(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_GERACAO_ATENDIMENTOS_INDIVIDUAIS_E_GRUPO,
				ModeloRelatorio.GERACAO_ATENDIMENTOS_INDIVIDUAIS_E_GRUPO,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioVagasEmGruposAtivos(TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_VAGAS_EM_GRUPOS_ATIVOS, ModeloRelatorio.VAGAS_EM_GRUPOS_ATIVOS, null,
				tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
			String nomeGrupo, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_ACOMPANHAMENTO_DE_PROGRAMACAO,
				ModeloRelatorio.ACOMPANHAMENTO_DE_PROGRAMACAO,
				gerarParametroNomeGrupo(nomeGrupo), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(
			GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_ATENDIMENTO_USUARIO_NO_GRUPO_SIMPLES,
				ModeloRelatorio.ATENDIMENTOS_DE_USUARIO_NO_GRUPO_SIMPLES,
				gerarParametros(grupoDto, moduloPeriodoDto), tokenDto);
	}

	public ArquivoDTO gerarRelatorioAtendimentoDeUsuarioNoGrupoDetalhado(
			GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			UsuarioDTO usuarioDto, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_ATENDIMENTO_USUARIO_NO_GRUPO_DETALHADO,
				ModeloRelatorio.ATENDIMENTOS_DE_USUARIO_NO_GRUPO_DETALHADO,
				gerarParametros(grupoDto, moduloPeriodoDto, usuarioDto),
				tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_GRUPO,
				ModeloRelatorio.TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_GRUPO,
				gerarParametroProntuario(prontuario), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO,
				ModeloRelatorio.TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO,
				gerarParametroProntuario(prontuario), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosIndividuaisDoUsuarioPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_TODOS_ATENDIMENTOS_INDIVIDUAIS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO,
				ModeloRelatorio.TODOS_ATENDIMENTOS_INDIVIDUAIS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO,
				gerarParametroProntuario(prontuario), tokenDto);
	}

	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO_LEGADO,
				ModeloRelatorio.TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO_LEGADO,
				gerarParametroProntuario(prontuario), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioQuantidadeUsuarioOrdenadoPorIdade(
			TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_QUANTIDADE_USUARIO_POR_IDADE,
				ModeloRelatorio.QUANTIDADE_USUARIO_ORDENADO_POR_IDADE, null,
				tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(
			TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_PESSOAS_NA_LISTA_DE_ESPERA_POR_DATA_EXPECTATIVA,
				ModeloRelatorio.QUANTIDADE_PESSOAS_NA_LISTA_DE_ESPERA_POR_DATA_EXPECTATIVA,
				null, tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(
			Long idadeInicio, Long idadeTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_PESSOAS_NA_LISTA_DE_ESPERA_POR_FAIXA_IDADE,
				ModeloRelatorio.QUANTIDADE_PESSOAS_NA_LISTA_DE_ESPERA_POR_FAIXA_IDADE,
				gerarParametros(idadeInicio, idadeTermino), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioRetiradaProntuarioNoAgendamento(
			String data, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_RETIRADA_PRONTUARIOS_NO_AGENDAMENTO,
				ModeloRelatorio.RETIRADA_PRONTUARIOS_NO_AGENDAMENTO,
				gerarParametroData(data), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendimentosIndividuais(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_ATENDIMENTOS_INDIVIDUAIS,
				ModeloRelatorio.QUANTIDADE_ATENDIMENTOS_INDIVIDUAIS,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(
			TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_CONFERENCIA_AGENDAMENTOS_E_ATENDIMENTOS_INDIVIDUAIS,
				ModeloRelatorio.CONFERENCIA_AGENDAMENTO_ATENDIMENTOS_INDIVIDUAIS,
				null, tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioContatosIntegrantesGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_CONTATOS_INTEGRANTES_GRUPO,
				ModeloRelatorio.CONTATOS_INTEGRANTES_GRUPO,
				gerarParametros(grupoDto, moduloPeriodoDto), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorTiposVinculos(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_ATENDIDOS_POR_TIPOS_VINCULOS,
				ModeloRelatorio.QUANTIDADE_ATENDIDOS_POR_TIPOS_VINCULOS,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_ATENDIDOS_POR_TIPOS_VINCULOS_POR_GRUPO,
				ModeloRelatorio.QUANTIDADE_ATENDIDOS_POR_TIPOS_VINCULOS_POR_GRUPO,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}
	

	public ArquivoDTO gerarRelatorioQuantidadeAtendimentoParaPrefeitura(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_ATENDIMENTOS_PARA_PREFEITURA,
				ModeloRelatorio.QUANTIDADE_ATENDIMENTOS_PARA_PEFEITURA,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioTodasRetiradasPendentes(TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_RETIRADAS_PENDENTES,
				ModeloRelatorio.RETIRADAS_PENDENTES, null, tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodasFrequenciasOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_TODAS_FREQUENCIAS_ORDENADO_POR_DATA_LANCAMENTO,
				ModeloRelatorio.TODAS_FREQUENCIAS_ORDENADO_POR_DATA_LANCAMENTO,
				gerarParametroProntuario(prontuario), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodosUsuarioComStatusDeGrupoProvisorioOuAcesso(TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_TODOS_USUARIOS_COM_STATUS_DE_GRUPO_PROVISORIO_OU_ACESSO,
				ModeloRelatorio.TODOS_USUARIOS_COM_STATUS_DE_GRUPO_PROVISORIO_OU_ACESSO, null, tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodasRetiradasPorProntuario(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_RETIRADAS_POR_PRONTUARIO,
				ModeloRelatorio.RETIRADAS_POR_PRONTUARIO,
				gerarParametroProntuario(prontuario), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioParticipacaoUsuarioEmGrupos(
			Long prontuario, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_PARTICIPACAO_USUARIO_EM_GRUPOS,
				ModeloRelatorio.PARTICIPACAO_USUARIO_EM_GRUPOS,
				gerarParametroProntuario(prontuario), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioSolicitacaoRelatorio(Long protocolo,
			TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_SOLICITACAO_RELATORIO,
				ModeloRelatorio.SOLICITACAO_RELATORIO,
				gerarParametroProtocolo(protocolo), tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioCargaHorariaDeUsuarioPorGrupo(
			String nomeGrupo, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_CARGA_HORARIA_DE_USUARIO_POR_GRUPO,
				ModeloRelatorio.CARGA_HORARIA_DE_USUARIO_POR_GRUPO,
				gerarParametroNomeGrupo(nomeGrupo), tokenDto);
	}

	public ArquivoDTO gerarRelatorioFrequenciaPorGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, boolean paisagem,
			TokenDTO tokenDto) {
		ArquivoDTO resultado = new ArquivoDTO();
		if (paisagem) {
			resultado = gerarRelatorio(
					Permissao.RELATORIO_LISTA_FREQUENCIA_POR_GRUPO,
					ModeloRelatorio.LISTA_DE_FREQUENCIA_TABULAR_POR_GRUPO,
					gerarParametros(grupoDto, moduloPeriodoDto), tokenDto);
		} else {
			resultado = gerarRelatorio(
					Permissao.RELATORIO_LISTA_FREQUENCIA_POR_GRUPO,
					ModeloRelatorio.LISTA_DE_FREQUENCIA_VERTICAL_POR_GRUPO,
					gerarParametros(grupoDto, moduloPeriodoDto), tokenDto);
		}
		return resultado;
	}	

	public ArquivoDTO gerarRelatorioPorcentagensFrequenciaPorGrupo(GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_PORCENTAGENS_FREQUENCIA_POR_GRUPO,
				ModeloRelatorio.PORCENTAGENS_DE_FREQUENCIA_POR_GRUPO, gerarParametros(grupoDto, moduloPeriodoDto),
				tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioProgramacaoDoGrupoParaFamilia(
			String dataInicio, String dataTermino, GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto) {
		ArquivoDTO resultado = new ArquivoDTO();
		if (DataHoraUtils.dataValida(dataInicio)
				&& DataHoraUtils.dataValida(dataTermino)) {
			resultado = gerarRelatorio(
					Permissao.RELATORIO_PROGRAMACAO_DO_GRUPO_PARA_FAMILIA,
					ModeloRelatorio.PROGRAMACAO_DO_GRUPO_PARA_FAMILIA,
					gerarParametros(dataInicio, dataTermino, grupoDto,
							moduloPeriodoDto), tokenDto);
		} else {
			resultado.adicionarErro(MENSAGEM_PERIODO_INVALIDO);
		}
		return resultado;
	}
	
	private Map<String, Object> gerarParametros(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, UsuarioDTO usuarioDto) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_id_grupo", grupoDto.getId());
		parametros.put("param_id_modulo_periodo", moduloPeriodoDto.getId());
		parametros.put("param_id_usuario", usuarioDto.getId());
		return parametros;
	}
	
	private Map<String, Object> gerarParametros(String dataInicio,
			String dataTermino, GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto) {
		return gerarParametros(gerarParametros(grupoDto, moduloPeriodoDto),
				dataInicio, dataTermino);
	}
	
	private Map<String, Object> gerarParametros(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_id_grupo", grupoDto.getId());
		parametros.put("param_id_modulo_periodo", moduloPeriodoDto.getId());
		return parametros;
	}
	
	private Map<String, Object> gerarParametros(Long idadeInicio,
			Long idadeTermino) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_idade_inicio", idadeInicio);
		parametros.put("param_idade_termino", idadeTermino);
		return parametros;
	}
	
	private Map<String, Object> gerarParametroProtocolo(Long protocolo) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_id_protocolo", protocolo);
		return parametros;
	}
	
	private Map<String, Object> gerarParametroProntuario(Long prontuario) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_id_usuario", prontuario);
		return parametros;
	}
	
	private Map<String, Object> gerarParametroData(String data) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_data", DataHoraUtils.criar(data).getTime());
		return parametros;
	}
	
	private Map<String, Object> gerarParametroNomeGrupo(String data) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_nome_grupo", data);
		return parametros;
	}

	private Map<String, Object> gerarParametros(Map<String, Object> parametros,
			String dataInicio, String dataTermino) {
		parametros.put("param_data_inicio", DataHoraUtils
				.criar(dataInicio).getTime());
		parametros.put("param_data_termino", DataHoraUtils
				.criar(dataTermino).getTime());
		return parametros;
	}

	private Map<String, Object> gerarParametros(String dataInicio,
			String dataTermino) {
		Map<String, Object> parametros = new HashMap<>();
		return gerarParametros(parametros, dataInicio, dataTermino);
	}
	
	private Map<String, Object> gerarParametros(String dataInicio,
			String dataTermino, String condicionalCasoNovoOuRetorno) {
		Map<String, Object> parametros = gerarParametros(dataInicio, dataTermino);
		parametros.put("param_condicional_cn_ou_ret", condicionalCasoNovoOuRetorno);
		return parametros;
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorTipoAtendimento(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_TIPO_ATENDIMENTO,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_TIPO_ATENDIMENTO,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosPorIdadeEGenero(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_POR_IDADE_E_GENERO,
				ModeloRelatorio.COMAS_ATENDIDOS_POR_IDADE_E_GENERO,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorPorUF(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_UF,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_UF,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorRenda(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_RENDA,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_RENDA,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_REGIAO_SP,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_REGIAO_SP,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorMunicipioSP(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_MUNICIPIO_SP,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_MUNICIPIO_SP,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorEscolaridade(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_ESCOLARIDADE,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_ESCOLARIDADE,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorDeficiencia(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_DEFICIENCIA,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_DEFICIENCIA,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorClassificacaoInstituicao(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_CLASSIFICACAO_DE_INSTITUICAO,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_CLASSIFICACAO_INSTITUICAO,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorBeneficio(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_COMAS_ATENDIDOS_ATENDIMENTOS_POR_BENEFICIO,
				ModeloRelatorio.COMAS_ATENDIDOS_ATENDIMENTOS_POR_BENEFICIO,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioAtendidosVisitaMonitorada(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_QUANTIDADE_ATENDIDOS_POR_COMUNIDADE_VISITA_MONITORADA,
				ModeloRelatorio.ATENDIDOS_POR_COMUNIDADE_VISITA_MONITORADA,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioAtendidosSemInformacaoDeficiencia(
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(
				Permissao.RELATORIO_ATENDIDOS_SEM_INFORMACAO_DEFICIENCIA,
				ModeloRelatorio.ATENDIDOS_SEM_INFORMACAO_DEFICIENCIA,
				gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioQuantidadesAvaliacoesFuncionais(String dataInicio, String dataTermino,
			TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_QUANTIDADES_AVALIACOES_FUNCIONAIS,
				ModeloRelatorio.QUANTIDADES_AVALIACOES_FUNCIONAIS, gerarParametros(dataInicio, dataTermino), tokenDto);
	}

	public ArquivoDTO gerarRelatorioFluxoAtendimentoCasosNovosAvaliacaoFuncional(String dataInicio,
			String dataTermino, TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_FLUXO_ATENDIMENTO_AVALIACAO_FUNCIONAL,
				ModeloRelatorio.FLUXO_ATENDIMENTO_AVALIACAO_FUNCIONAL, gerarParametros(dataInicio, dataTermino, NOT),
				tokenDto);
	}

	public ArquivoDTO gerarRelatorioFluxoAtendimentoRetornosAvaliacaoFuncional(String dataInicio, String dataTermino,
			TokenDTO tokenDto) {
		return gerarRelatorio(Permissao.RELATORIO_FLUXO_ATENDIMENTO_AVALIACAO_FUNCIONAL,
				ModeloRelatorio.FLUXO_ATENDIMENTO_AVALIACAO_FUNCIONAL, gerarParametros(dataInicio, dataTermino, ""),
				tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioMailingVisitaMonitorada(String dataInicio, String dataTermino,
			List<TipoVinculoDTO> tiposVinculosDto, TokenDTO tokenDto) {
		List<TipoVinculo> tiposVinculos = new FabricaTipoVinculo().converterParaDominio(tiposVinculosDto);
		ContaAcesso contaAcesso = forcarFormatoRelatorioXls(tokenDto);
		ArquivoDTO arquivoDto = gerarRelatorio(Permissao.RELATORIO_MAILING_VISITA_MONITORADA,
				ModeloRelatorio.MAILING_VISITA_MONITORADA, gerarParametros(dataInicio, dataTermino, tiposVinculos),
				tokenDto);
		restaurarFormatoRelatorioOriginal(contaAcesso);
		return arquivoDto;
	}

	private void restaurarFormatoRelatorioOriginal(ContaAcesso contaAcesso) {
		contaAcesso.restaurarExtensaoDeRelatorioOriginal();
		repositorioContaAcesso.salvar(contaAcesso);
	}

	private ContaAcesso forcarFormatoRelatorioXls(TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		contaAcesso.marcarExtensaoDeRelatorioTemporariamente(ExtensaoArquivo.xls);
		repositorioContaAcesso.salvar(contaAcesso);
		return contaAcesso;
	}

	private Map<String, Object> gerarParametros(String dataInicio, String dataTermino, List<TipoVinculo> tiposVinculos) {
		Map<String, Object> parametros = gerarParametros(dataInicio, dataTermino);
		List<String> textoTiposVinculos = tiposVinculos.stream().map(tipoVinculo -> tipoVinculo.getId().toString())
				.collect(Collectors.toList());
		parametros.put("param_ids_categorias", TextoUtils.substituirCaracteres(textoTiposVinculos.toString()));
		return parametros;
	}

	public ArquivoDTO gerarRelatorioFrequenciasDeAtendimentosGlobaisPorUsuarioNoPeriodo(String prontuarios,
			String dataInicio, String dataTermino, TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		ArquivoDTO arquivoCsvDto = new ArquivoDTO();

		if (contaAcesso.possuiPermissao(Permissao.RELATORIO_FREQUENCIA_ATENDIMENTO_GLOBAL_POR_USUARIOS_NO_PERIODO)) {
			arquivoCsvDto = geradorRelatorio.gerarRelatorioFrequenciasDeAtendimentosGlobaisPorUsuarioNoPeriodo(
					prontuarios, dataInicio, dataTermino, contaAcesso);
		} else {
			arquivoCsvDto.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return arquivoCsvDto;
	}
}

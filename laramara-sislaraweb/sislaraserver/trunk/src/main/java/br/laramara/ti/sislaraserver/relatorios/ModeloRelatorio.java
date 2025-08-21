package br.laramara.ti.sislaraserver.relatorios;

import javax.sql.DataSource;

import br.laramara.ti.sislaraserver.utilitarios.Registro;

public enum ModeloRelatorio {
	ACOMPANHAMENTO_DE_PROGRAMACAO(
			"relatorio_de_acompanhamento_de_programacao.jasper", Registro
					.obterDataSourceSisLara()), ATENDIMENTOS_DE_USUARIO_NO_GRUPO_SIMPLES(
			"relatorio_atendimento_usuario_no_grupo_simples.jasper", Registro
					.obterDataSourceSisLara()), ATENDIMENTOS_DE_USUARIO_NO_GRUPO_DETALHADO(
			"relatorio_atendimento_usuario_no_grupo_detalhado.jasper", Registro
					.obterDataSourceSisLara()), TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_GRUPO(
			"relatorio_todos_atendimentos_usuario_ordenado_por_grupo.jasper",
			Registro.obterDataSourceSisLara()), TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO(
			"relatorio_todos_atendimentos_usuario_ordenado_por_data_lancamento.jasper",
			Registro.obterDataSourceSisLara()), TODOS_ATENDIMENTOS_INDIVIDUAIS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO(
			"relatorio_todos_atendimentos_individuais_usuario_ordernado_por_data_lancamento.jasper",
			Registro.obterDataSourceSisLara()), TODOS_ATENDIMENTOS_DO_USUARIO_ORDENADO_POR_DATA_LANCAMENTO_LEGADO(
			"relatorio_todos_atendimentos_usuario_ordenado_por_data_lancamento_legado.jasper",
			Registro.obterDataSourceSisLaraLegado()), QUANTIDADE_USUARIO_ORDENADO_POR_IDADE(
			"relatorio_quantidade_usuario_ordernado_por_idade.jasper", Registro
					.obterDataSourceSisLara()), QUANTIDADE_PESSOAS_NA_LISTA_DE_ESPERA_POR_DATA_EXPECTATIVA(
			"relatorio_quantidade_pessoas_aguardando_na_lista_de_espera_por_expectativa.jasper",
			Registro.obterDataSourceSisLara()), RETIRADA_PRONTUARIOS_NO_AGENDAMENTO(
			"relatorio_retirada_prontuarios_no_agendamento.jasper", Registro
					.obterDataSourceSisLara()), CONFERENCIA_AGENDAMENTO_ATENDIMENTOS_INDIVIDUAIS(
			"relatorio_conferencia_agendamentos_e_atendimentos_individuais.jasper",
			Registro.obterDataSourceSisLara()), CONTATOS_INTEGRANTES_GRUPO(
			"relatorio_contatos_de_integrantes_do_grupo.jasper", Registro
					.obterDataSourceSisLara()), FOLHA_DE_ROSTO(
			"relatorio_folha_de_rosto_usuario.jasper", Registro
					.obterDataSourceSisLara()), GERACAO_ATENDIMENTOS_INDIVIDUAIS_E_GRUPO(
			"relatorio_geracao_atendimentos_individuais_e_grupo.jasper",
			Registro.obterDataSourceSisLara()), QUANTIDADE_ATENDIMENTOS_INDIVIDUAIS(
			"relatorio_quantidade_atendimentos_atendidos_individual.jasper",
			Registro.obterDataSourceSisLara()), QUANTIDADE_ATENDIMENTOS_PARA_PEFEITURA(
					"relatorio_quantidade_atendimentos_usuario_por_grupo_para_prefeitura.jasper", 
			Registro.obterDataSourceSisLara()), QUANTIDADE_ATENDIDOS_POR_TIPOS_VINCULOS(
					"relatorio_quantidade_atendidos_por_tipos_de_vinculos.jasper", 
			Registro.obterDataSourceSisLara()), QUANTIDADE_ATENDIDOS_POR_TIPOS_VINCULOS_POR_GRUPO(
					"relatorio_quantidade_atendidos_por_tipos_de_vinculos_por_grupo.jasper",
			Registro.obterDataSourceSisLara()), RETIRADAS_PENDENTES(
			"relatorio_todas_retiradas_pendentes.jasper", Registro
					.obterDataSourceSisLara()), RETIRADAS_POR_PRONTUARIO(
			"relatorio_todas_retiradas_por_prontuario.jasper", Registro
					.obterDataSourceSisLara()), PROGRAMACAO_DO_GRUPO_PARA_FAMILIA(
			"relatorio_de_programacao_do_grupo_para_familia.jasper", Registro
					.obterDataSourceSisLara()), PARTICIPACAO_USUARIO_EM_GRUPOS(
			"relatorio_participacao_usuarios_nos_grupos.jasper", Registro
					.obterDataSourceSisLara()), QUANTIDADE_PESSOAS_NA_LISTA_DE_ESPERA_POR_FAIXA_IDADE(
			"relatorio_quantidade_pessoas_aguardando_na_lista_de_espera_por_faixa_idade.jasper", Registro
					.obterDataSourceSisLara()), LISTA_DE_FREQUENCIA_TABULAR_POR_GRUPO(
			"relatorio_lista_frequencia_tabular_por_grupo.jasper", Registro
					.obterDataSourceSisLara()), PORCENTAGENS_DE_FREQUENCIA_POR_GRUPO(
			"relatorio_porcentagem_frequencias_por_grupo.jasper", Registro
					.obterDataSourceSisLara()), LISTA_DE_FREQUENCIA_VERTICAL_POR_GRUPO(
			"relatorio_lista_frequencia_vertical_por_grupo.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_TIPO_ATENDIMENTO(
			"relatorio_comas_todos_atendidos_e_atendimentos_por_tipo_atendimento.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_POR_IDADE_E_GENERO(
			"relatorio_comas_atendidos_por_idade_e_genero.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_UF(
			"relatorio_comas_atendidos_e_atendimentos_por_uf.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_RENDA(
			"relatorio_comas_atendidos_e_atendimentos_por_renda.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_REGIAO_SP(
			"relatorio_comas_atendidos_e_atendimentos_por_regiao_sp.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_MUNICIPIO_SP(
			"relatorio_comas_atendidos_e_atendimentos_por_municipio_sp.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_ESCOLARIDADE(
			"relatorio_comas_atendidos_e_atendimentos_por_escolaridade.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_DEFICIENCIA(
			"relatorio_comas_atendidos_e_atendimentos_por_deficiencia.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_CLASSIFICACAO_INSTITUICAO(
			"relatorio_comas_atendidos_e_atendimentos_por_classificacao_instituicao.jasper", Registro
					.obterDataSourceSisLara()), COMAS_ATENDIDOS_ATENDIMENTOS_POR_BENEFICIO(
			"relatorio_comas_atendidos_e_atendimentos_por_beneficio.jasper", Registro
					.obterDataSourceSisLara()), CARGA_HORARIA_DE_USUARIO_POR_GRUPO(
			"relatorio_carga_horaria_de_usuarios_por_grupo.jasper", Registro
					.obterDataSourceSisLara()), ATENDIDOS_POR_COMUNIDADE_VISITA_MONITORADA(
			"relatorio_quantidade_atendidos_por_comunidade_visita_monitorada.jasper", Registro
					.obterDataSourceSisLara()), SOLICITACAO_RELATORIO(
			"relatorio_solicitacao_relatorio.jasper", Registro
					.obterDataSourceSisLara()), ATENDIDOS_SEM_INFORMACAO_DEFICIENCIA(
			"relatorio_atendidos_sem_informacao_deficiencia.jasper", Registro
					.obterDataSourceSisLara()), TODAS_FREQUENCIAS_ORDENADO_POR_DATA_LANCAMENTO(
			"relatorio_todas_frequencias_ordernado_por_data_lancamento.jasper", Registro
					.obterDataSourceSisLara()), TODOS_USUARIOS_COM_STATUS_DE_GRUPO_PROVISORIO_OU_ACESSO(
			"relatorio_todos_usuarios_com_status_de_grupo_provisorio_ou_acesso.jasper",  Registro
					.obterDataSourceSisLara()), QUANTIDADES_AVALIACOES_FUNCIONAIS(
			"relatorio_quantidades_de_avaliacoes_funcionais.jasper", Registro
					.obterDataSourceSisLara()), VAGAS_EM_GRUPOS_ATIVOS(
			"relatorio_de_vagas_em_grupos_ativos.jasper", Registro
					.obterDataSourceSisLara()), FLUXO_ATENDIMENTO_AVALIACAO_FUNCIONAL(
			"relatorio_fluxo_atendimento_avaliacao_funcional.jasper", Registro
					.obterDataSourceSisLara()), RECIBO(
			"relatorio_recibo.jasper", Registro
					.obterDataSourceSisLara()), MAILING_VISITA_MONITORADA(
			"relatorio_mailing_visita_monitorada.jasper", Registro
					.obterDataSourceSisLara()), AVALIACAO_PSICOSSOCIAL(
			"relatorio_ficha_do_servico_social.jasper", Registro
					.obterDataSourceSisLara());
	private final String nomeArquivo;
	private final DataSource fonteDeDados;

	private ModeloRelatorio(String nomeArquivo, DataSource fonteDeDados) {
		this.nomeArquivo = nomeArquivo;
		this.fonteDeDados = fonteDeDados;
	}

	public String obterNomeArquivo() {
		return nomeArquivo;
	}

	public DataSource obterFonteDeDados() {
		return fonteDeDados;
	}
}

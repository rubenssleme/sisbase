package br.laramara.ti.sismovimentacaocommons.dtos;

import java.io.Serializable;

public enum ChavePesquisaDTO implements Serializable {
	NOME("Nome do Usu�rio"), 
	FAMILIAR("Nome do Familiar"),
	PRONTUARIO("Prontu�rio"), 
	NOME_INSTITUICAO("Nome da Institui��o"),
	PRE_CADASTRO("Nome do Usu�rio"),
	RG_PRE_CADASTRO("RG"),
	GRUPOS_INATIVOS("Grupos Inativos"),
	GRUPOS_ATIVOS("Grupos Ativos"),
	TODOS_PERFIS("Todos os Perfis"),
	TODOS_CONTAS_ACESSO("Todas as Contas Acesso"), 
	PROFISSIONAL("Profissional"), 
	DESCRICAO_TIPO_ATENDIMENTO("Descricao de Tipo de Atendimento"), 
	DATA_INICIO("Data de Inicio"), 
	DATA_TERMINO("Data de Termino"), 
	STATUS_AGENDAMENTO("Status de Atendimento"), 
	MODULO("M�dulo"), 
	TIPO_ESPERA("Tipo de Espera"), 
	STATUS_ESPERA("Status da Espera"), 
	SETOR("Setor"),
	RECURSO("Recurso"),
	PROJETO("Nome do Projeto"), 
	DATA_FUTURA("Data Futura"), 
	QUANTIDADE("Quantidade de pesquisa");
	private String descricao;

	private ChavePesquisaDTO(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
}
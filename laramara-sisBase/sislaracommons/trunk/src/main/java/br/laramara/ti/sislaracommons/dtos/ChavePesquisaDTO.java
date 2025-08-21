package br.laramara.ti.sislaracommons.dtos;

import java.io.Serializable;

public enum ChavePesquisaDTO implements Serializable {
	NOME("Nome do Usu�rio"), 
	FAMILIAR("Nome do Familiar"),
	PRONTUARIO("Prontu�rio"), 
	NOME_INSTITUICAO("Nome da Institui��o"),
	PRE_CADASTRO("Nome do Usu�rio"),
	RG("RG"),
	CPF("CPF"),
	CPF_CNPJ("CPF ou CNPJ"),
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
	PROTOCOLO("Protocolo"),
	DATA_FUTURA("Data Futura"), 
	STATUS_SOLICITACAO_RELATORIO("Status de Solicita��o de Relat�rio"),
	NOME_CONTRIBUINTE("Nome do Contribuinte/Empresa"), 
	TODOS_CONTRIBUINTES("Todos os contribuintes"), 
	TODOS_RECIBOS("Todos os recibos"), 
	INTERESSE("Interesse"), 
	LMLIGOU("Ligou"), 
	PENDENCIAS("Pendencias"), 
	CARTELA_DE_SELOS("Cartela de Selos"), 
	STATUS_DEMANDA("Status da Demanda"), 
	FILIAL("Filial");
	private String descricao;

	private ChavePesquisaDTO(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
}
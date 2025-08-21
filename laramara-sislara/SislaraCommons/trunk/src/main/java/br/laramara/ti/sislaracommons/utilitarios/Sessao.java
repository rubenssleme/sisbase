package br.laramara.ti.sislaracommons.utilitarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;

public class Sessao {

	private static final String CHAVE_TOKEN = "Token";
	public static final String CHAVE_USUARIO = "Usuário";
	public static final String CHAVE_USUARIO_SELECIONADO = "Usuário Selecionado";
	public static final String CHAVE_PRE_CADASTRO = "Pré-Cadastro";
	public static final String CHAVE_PRE_CADASTRO_SELECIONADO = "Pré-Cadastro Selecionado";
	public static final String CHAVE_AGENDAMENTO = "Agendamento";
	public static final String CHAVE_ESPERA = "Espera";
	public static final String CHAVE_DEMANDA = "Demanda";
	public static final String CHAVE_RECIBO = "Recibo";
	public static final String CHAVE_INFORMACAO_ESSENCIAL = "Informação Essencial";
	public static final String CHAVE_INSTITUICAO = "Instituição";
	public static final String CHAVE_FAMILIAR = "Familiar";
	public static final String CHAVE_INFORMACAO_EDUCACIONAL = "Informação Educacional";
	public static final String CHAVE_PERIODO_BENEFICIO = "Beneficio";
	public static final String CHAVE_PERIODO_DEFICIENCIA = "Decifiência";
	public static final String CHAVE_PERIODO_DOENCA= "Doença";
	public static final String CHAVE_GRUPO = "Grupo";
	public static final String CHAVE_CONTA_ACESSO = "Conta Acesso";
	public static final String CHAVE_PERFIL = "Perfil";
	public static final String CHAVE_SOLICITACAO_RELATORIO = "Solicitação de Relatório";
	public static final String CHAVE_ATENDIMENTO_USUARIO = "Atendimento de Usuário ou Pré-Cadastro em Grupo";
	public static final String CHAVE_ATENDIMENTO_GRUPO = "Atendimento de Grupo";
	public static final String CHAVE_ATENDIMENTO_PROFISSIONAL = "Atendimento de Profissional ao Grupo";
	public static final String CHAVE_ATENDIMENTO_INDIVIDUAL = "Atendimento Individual";
	public static final String CHAVE_ATENDIMENTO_COMUNIDADE = "Atendimento de Comunidade";
	public static final String CHAVE_LISTAGEM_AGENDAMENTOS = "Agendamentos Efetuados";
	public static final String CHAVE_LISTAGEM_DEMANDAS = "Demandas Efetuadas";
	public static final String CHAVE_MODULO_CARGA_HORARIA = "Carga Horária de Módulo";
	public static final String CHAVE_MODULO_PERIODO = "Módulo/Atividade no Período";
	public static final String CHAVE_MODULO_RELACAO_BASE = "Relação base de Usuário ou Pré-Cadastro ao Módulo";
	public static final String CHAVE_PROGRAMACAO = "Programação dos Grupos";
	public static final String CHAVE_PROJETO = "Projetos";
	public static final String CHAVE_CERTIDAO = "Certidao";
	public static final String CHAVE_INFORMACAO_TRABALHO_COMPLETA = "Informação de Trabalho Completa";
	public static final String CHAVE_CONTRIBUINTE = "Contribuinte";
	
	private Map<String, ModeloDTO> dtosArmazenados;
	private Map<String, List<? extends ModeloDTO>> dtosListasArmazenados;
	private static final Sessao instancia = new Sessao();

	private Sessao() {
		dtosArmazenados = new HashMap<>();
		dtosListasArmazenados = new HashMap<>();
	}

	public static Sessao obterInstancia() {
		return instancia;
	}

	public boolean possuiDto(String chave) {
		return dtosArmazenados.containsKey(chave);
	}
	
	public boolean possuiDtos(String chave) {
		return dtosListasArmazenados.containsKey(chave);
	}

	public TokenDTO obterToken(){
		return (TokenDTO) dtosArmazenados.get(CHAVE_TOKEN);
	}
	
	public void armazenarTokenDTO(ModeloDTO objetoDto) {
		dtosArmazenados.put(CHAVE_TOKEN, objetoDto);
	}

	public void armazenarDTOs(String chave, List<? extends ModeloDTO> objetoDto) {
		dtosListasArmazenados.put(chave, objetoDto);
	}
	
	public void armazenarDTO(String chave, ModeloDTO objetoDto) {
		dtosArmazenados.put(chave, objetoDto);
	}

	public final Object obterDto(String chave) {
		Object retorno = dtosArmazenados.get(chave);
		removerDto(chave);
		return retorno;
	}
	
	public final List<? extends ModeloDTO> obterDtos(String chave) {
		List<? extends ModeloDTO> retorno = dtosListasArmazenados.get(chave);
		removerDtos(chave);
		return retorno;
	}

	public void removerDto(String chave) {
		dtosArmazenados.remove(chave);
	}
	
	public void removerDtos(String chave) {
		dtosListasArmazenados.remove(chave);
	}
}

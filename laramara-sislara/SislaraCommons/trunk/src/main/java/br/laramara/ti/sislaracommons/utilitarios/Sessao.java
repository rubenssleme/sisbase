package br.laramara.ti.sislaracommons.utilitarios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;

public class Sessao {

	private static final String CHAVE_TOKEN = "Token";
	public static final String CHAVE_USUARIO = "Usu�rio";
	public static final String CHAVE_USUARIO_SELECIONADO = "Usu�rio Selecionado";
	public static final String CHAVE_PRE_CADASTRO = "Pr�-Cadastro";
	public static final String CHAVE_PRE_CADASTRO_SELECIONADO = "Pr�-Cadastro Selecionado";
	public static final String CHAVE_AGENDAMENTO = "Agendamento";
	public static final String CHAVE_ESPERA = "Espera";
	public static final String CHAVE_DEMANDA = "Demanda";
	public static final String CHAVE_RECIBO = "Recibo";
	public static final String CHAVE_INFORMACAO_ESSENCIAL = "Informa��o Essencial";
	public static final String CHAVE_INSTITUICAO = "Institui��o";
	public static final String CHAVE_FAMILIAR = "Familiar";
	public static final String CHAVE_INFORMACAO_EDUCACIONAL = "Informa��o Educacional";
	public static final String CHAVE_PERIODO_BENEFICIO = "Beneficio";
	public static final String CHAVE_PERIODO_DEFICIENCIA = "Decifi�ncia";
	public static final String CHAVE_PERIODO_DOENCA= "Doen�a";
	public static final String CHAVE_GRUPO = "Grupo";
	public static final String CHAVE_CONTA_ACESSO = "Conta Acesso";
	public static final String CHAVE_PERFIL = "Perfil";
	public static final String CHAVE_SOLICITACAO_RELATORIO = "Solicita��o de Relat�rio";
	public static final String CHAVE_ATENDIMENTO_USUARIO = "Atendimento de Usu�rio ou Pr�-Cadastro em Grupo";
	public static final String CHAVE_ATENDIMENTO_GRUPO = "Atendimento de Grupo";
	public static final String CHAVE_ATENDIMENTO_PROFISSIONAL = "Atendimento de Profissional ao Grupo";
	public static final String CHAVE_ATENDIMENTO_INDIVIDUAL = "Atendimento Individual";
	public static final String CHAVE_ATENDIMENTO_COMUNIDADE = "Atendimento de Comunidade";
	public static final String CHAVE_LISTAGEM_AGENDAMENTOS = "Agendamentos Efetuados";
	public static final String CHAVE_LISTAGEM_DEMANDAS = "Demandas Efetuadas";
	public static final String CHAVE_MODULO_CARGA_HORARIA = "Carga Hor�ria de M�dulo";
	public static final String CHAVE_MODULO_PERIODO = "M�dulo/Atividade no Per�odo";
	public static final String CHAVE_MODULO_RELACAO_BASE = "Rela��o base de Usu�rio ou Pr�-Cadastro ao M�dulo";
	public static final String CHAVE_PROGRAMACAO = "Programa��o dos Grupos";
	public static final String CHAVE_PROJETO = "Projetos";
	public static final String CHAVE_CERTIDAO = "Certidao";
	public static final String CHAVE_INFORMACAO_TRABALHO_COMPLETA = "Informa��o de Trabalho Completa";
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

package br.laramara.ti.sislaracommons.servicos.rest;

public class ContratoRest {

	public static final String URL_PARAMETRO_IDGRUPO = "{idGrupo}";
	public static final String URL_PARAMETRO_TOKEN = "{token}";
	public static final String URL_PARAMETRO_CEP = "{cep}";
	public static final String URL_PARAMETRO_UF = "{uf}";
	
	private static final String URL_INSCRICAO = "/inscricao";
	
	public static final String URL_USUARIO_EXTERNO_AUTENTICACAO = URL_INSCRICAO + "/usuario-externo/autenticacao/";
	public static final String URL_USUARIO_EXTERNO_SOLICITACAO_RECUPERACAO_SENHA = URL_INSCRICAO + "/usuario-externo/solicitacao-recuperacao-senha/";
	public static final String URL_USUARIO_EXTERNO_CADASTRO_NOVA_SENHA = URL_INSCRICAO + "/usuario-externo/cadastro-nova-senha/";
	public static final String URL_USUARIO_EXTERNO_CADASTRO = URL_INSCRICAO + "/usuario-externo/cadastro/";
	public static final String URL_USUARIO_EXTERNO_CONSULTA_PERFIL_PREENCHIDO = URL_INSCRICAO + "/usuario-externo/consulta-perfil-preenchido";
	public static final String URL_USUARIO_EXTERNO_EDICAO = URL_INSCRICAO + "/usuario-externo/edicao";
	public static final String URL_USUARIO_EXTERNO_ALTERACAO = URL_INSCRICAO + "/usuario-externo/alteracao";
	public static final String URL_USUARIO_EXTERNO_CONSULTA_POR_TOKEN = URL_INSCRICAO + "/usuario-externo/" + URL_PARAMETRO_TOKEN;
	
	public static final String URL_DETALHE_CURSO_LISTAGEM = URL_INSCRICAO + "/detalhes-cursos/";
	public static final String URL_DETALHE_CURSO_CONSULTA_POR_IDGRUPO = URL_DETALHE_CURSO_LISTAGEM + URL_PARAMETRO_IDGRUPO;
	
	public static final String URL_ENDERECO_CONSULTA_POR_CEP = URL_INSCRICAO + "/endereco/" + URL_PARAMETRO_CEP;
	
	public static final String URL_MUNICIPIO_LISTAGEM_POR_UF = URL_INSCRICAO + "/municipio/" + URL_PARAMETRO_UF;
	
	public static final String URL_UF_LISTAGEM = URL_INSCRICAO + "/ufs/";
	
	public static final String URL_INSCRICAO_CADASTRO = URL_INSCRICAO + "/inscricao/cadastro/";
	
}
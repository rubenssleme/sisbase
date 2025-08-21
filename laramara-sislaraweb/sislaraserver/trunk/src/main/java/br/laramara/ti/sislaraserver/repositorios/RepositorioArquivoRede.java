package br.laramara.ti.sislaraserver.repositorios;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.ArquivoDisponivel;
import br.laramara.ti.sislaraserver.dominio.TipoArquivoDisponivel;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;
import br.laramara.ti.sislaraserver.dominio.doacao.Demanda;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;

@Repository
public class RepositorioArquivoRede implements RepositorioArquivo{

	private Logger logger = Logger.getLogger(RepositorioArquivoRede.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String localArmazenamento;
	
	public RepositorioArquivoRede(){
		localArmazenamento = new Configuracao()
				.obterConfiguracao(Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_INDIVIDUAL);
	}
	
	private void salvar(List<Arquivo> arquivos, Long id, String configuracao) {
		List<Arquivo> arquivosComConteudo = arquivos.stream().filter(arquivo -> arquivo.possuiConteudo())
				.collect(Collectors.toList());
		for (Arquivo arquivoComConteudo : arquivosComConteudo) {
			try {
				FileUtils.writeByteArrayToFile(criarRepresentacaoArquivo(configuracao, arquivoComConteudo, id),
						arquivoComConteudo.getConteudo());
				logger.info(arquivoComConteudo + " armazenado com sucesso.");
			} catch (IOException e) {
				logger.error("Erro durante armazenamento do " + arquivoComConteudo + " \nDetalhes: " + e);
			}
		}
	}
	
	public List<ArquivoDisponivel> obterArquivosDisponiveis(String dadosPesquisa, boolean somenteGrupos){
		String condicionalGrupos = "nom.nome ||'-'|| gru.turma ||'-'|| to_char(gru.data_inicio, 'DD/MM/YYYY') like ?";
		String condicionalProntuario  = "ateusu.id_usuario = ?";
		String comando = "select aux.* from " +	
				"(select nom.nome ||'-'|| gru.turma ||'-'|| to_char(gru.data_inicio, 'DD/MM/YYYY') as nome_grupo, " + (somenteGrupos ? "ategru.id" : "ateusu.id") + " as id_atendimento, ategru.data as data_atendimento, ategru.hora_inicio, ategru.hora_termino, arq.nome as nome_arquivo, " + (somenteGrupos ? "'GRUPO'" : "'USUARIO_NO_GRUPO'") + " as tipo "
				+ 	(somenteGrupos ? 
						"from atendimento_grupo_arquivo ategruarq " +
						"inner join atendimento_grupo ategru " +
							"on ategru.id = ategruarq.id_atendimento_grupo " + 
						"inner join arquivo arq " + 
							"on arq.id = ategruarq.id_arquivo " :
						"from atendimento_usuario_arquivo ateusuarq " +
						"inner join atendimento_usuario ateusu " +
							"on ateusu.id = ateusuarq.id_atendimento_usuario " +
						"inner join atendimento_grupo_atendimento_usuario ategruateusu " +
							"on ategruateusu.id_atendimento_usuario = ateusu.id " +
						"inner join atendimento_grupo ategru " +
							"on ategru.id = ategruateusu.id_atendimento_grupo " +
						"inner join arquivo arq " + 
							"on arq.id = ateusuarq.id_arquivo "
					) + "inner join modulo_periodo_atendimento_grupo modperategru  " +
							"on modperategru.id_atendimento_grupo = ategru.id  " +
						"inner join modulo_periodo modper  " +
							"on modper.id = modperategru.id_modulo_periodo  " +
						"inner join grupo_modulo_periodo grumodper  " +
							"on grumodper.id_modulo_periodo = modper.id  " +
						"inner join grupo gru  " +
							"on gru.id = grumodper.id_grupo  " +
						"inner join nome_grupo nom  " +
							"on nom.id = gru.id_nome_grupo  " +
					"where " + (somenteGrupos ? condicionalGrupos : condicionalProntuario) + " and ategru.status = 'NORMAL' " +
					(!somenteGrupos ? 
					"union all " +
					"select null as nome_grupo, ateind.id as id_atendimento, ateind.data as data_atendimento, ateind.hora_inicio, ateind.hora_termino, arq.nome as nome_arquivo, 'INDIVIDUAL' as tipo from atendimento_individual_arquivo ateindarq " +
						"inner join arquivo arq " +
							"on ateindarq.id_arquivo = arq.id " +
						"inner join atendimento_individual ateind " +
							"on ateind.id = ateindarq.id_atendimento_individual " +
					"where ateind.id_usuario = ? and ateind.status = 'NORMAL' " : "")
					
					+ ")aux " +
				"order by aux.data_atendimento desc, aux.hora_inicio; ";
		
		return executarComando(dadosPesquisa, somenteGrupos, comando);
	}

	private List<ArquivoDisponivel> executarComando(String dadosPesquisa, boolean somenteGrupos, String comando) {
		return jdbcTemplate.query(comando, obterParametros(dadosPesquisa, somenteGrupos),
				new RowMapper<ArquivoDisponivel>() {
					public ArquivoDisponivel mapRow(ResultSet rs, int rowNum) throws SQLException {
						ArquivoDisponivel arquivoDisponivel = new ArquivoDisponivel();
						arquivoDisponivel.setNomeGrupo(rs.getString("nome_grupo"));
						arquivoDisponivel.setIdAtendimento(rs.getLong("id_atendimento"));
						arquivoDisponivel.setDataAtendimento(rs.getDate("data_atendimento"));
						arquivoDisponivel.setHoraInicio(rs.getString("hora_inicio"));
						arquivoDisponivel.setHoraTermino(rs.getString("hora_termino"));
						arquivoDisponivel.setNomeArquivo(rs.getString("nome_arquivo"));
						arquivoDisponivel.setTipo(TipoArquivoDisponivel.valueOf(rs.getString("tipo")).toString());
						return arquivoDisponivel;
					}
				});
	}

	private Object[] obterParametros(String dadosPesquisa, boolean somenteGrupos) {
		Object[] parametros;
		if (!somenteGrupos){
			parametros = new Object[] {Long.valueOf(dadosPesquisa), Long.valueOf(dadosPesquisa)};
		}
		else{
			parametros = new Object[] {dadosPesquisa};
		}
		return parametros;
	}
		
	private Arquivo obterArquivo(Long id, String configuracao, Arquivo arquivo) {
		if (id != null) {
			try {
				arquivo.setConteudo(FileUtils.readFileToByteArray(
						criarRepresentacaoArquivo(configuracao,
								arquivo, id)));
			} catch (Exception e) {
				logger.error("Erro durante leitura de arquivo de atendimento individual. " + e);
			}
		}
		return arquivo;
	}
	
	private File criarRepresentacaoArquivo(String configuracao, Arquivo arquivo, Long id) {
		return new File(new Configuracao().obterConfiguracao(configuracao) + id.toString() + "/"
				+ arquivo.getNome());
	}
	
	@Override
	public void salvar(AtendimentoIndividual atendimentoIndividual) {
		salvar(atendimentoIndividual.getArquivos(), atendimentoIndividual.getId(),
				Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_INDIVIDUAL);
	}

	@Override
	public void salvar(AtendimentoGrupo atendimentoGrupo) {
		salvar(atendimentoGrupo.getArquivos(), atendimentoGrupo.getId(),
				Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_GRUPO);
		for (AtendimentoUsuario atendimentoUsuario : atendimentoGrupo.getAtendimentosUsuarios()) {
			salvar(atendimentoUsuario.getArquivos(), atendimentoUsuario.getId(),
					Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_USUARIO);
		}
	}
		
	@Override
	public void salvar(Demanda demanda) {
		salvar(demanda.getArquivos(), demanda.getId(), Configuracao.DIRETORIO_ARQUIVOS_DEMANDA);
	}
	
	@Override
	public Arquivo obterArquivoAtendimentoIndividual(AtendimentoIndividual atendimentoIndividual, Arquivo arquivo) {
		return obterArquivo(atendimentoIndividual.getId(), Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_INDIVIDUAL,
				arquivo);
	}

	@Override
	public Arquivo obterArquivoAtendimentoGrupo(AtendimentoGrupo atendimentoGrupo, Arquivo arquivo) {
		return obterArquivo(atendimentoGrupo.getId(), Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_GRUPO, arquivo);
	}
	
	@Override
	public Arquivo obterArquivoAtendimentoUsuario(AtendimentoUsuario atendimentoUsuario, Arquivo arquivo) {
		return obterArquivo(atendimentoUsuario.getId(), Configuracao.DIRETORIO_ARQUIVOS_ATENDIMENTO_USUARIO, arquivo);
	}

	@Override
	public Arquivo obterArquivoDocumentoSolicitacaoDoacao(Demanda demanda, Arquivo arquivo) {
		return obterArquivo(demanda.getId(), Configuracao.DIRETORIO_ARQUIVOS_DEMANDA, arquivo);
	}

	@Override
	public Arquivo obterArquivoProjeto(Projeto projeto, Arquivo arquivo) {
		return obterArquivo(projeto.getId(), Configuracao.DIRETORIO_ARQUIVOS_PROJETO,
				arquivo);
	}

	@Override
	public void salvar(Projeto projetoASalvar) {
		salvar(projetoASalvar.getArquivos(), projetoASalvar.getId(), Configuracao.DIRETORIO_ARQUIVOS_PROJETO);
	}
}

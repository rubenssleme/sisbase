package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.agenda.Agendamento;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.pendencia.TipoPendencia;
import br.laramara.ti.sislaraserver.dominio.seguranca.Area;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

@Repository
public class RepositorioSislaraBD implements RepositorioSislara {
	
	private JdbcTemplate jdbcTemplate;
	private static final String CHAVE_NOSSO_NUMERO = "nosso_numero_seq";
	
	private static String SQL_JUNCAO_ATENDIMENTO_GRUPO = 
		"inner join grupo_modulo_periodo grumodper " + 
			"on grumodper.id_grupo = gru.id " +
		"inner join modulo_periodo modper " +
			"on grumodper.id_modulo_periodo = modper.id " +
		"inner join modulo_periodo_atendimento_grupo modperategru " +
			"on modperategru.id_modulo_periodo = modper.id " +
		"inner join atendimento_grupo ategru " +
			"on ategru.id = modperategru.id_atendimento_grupo " +
		"inner join atendimento_grupo_atendimento_usuario ategruateusu " +
			"on ategruateusu.id_atendimento_grupo = ategru.id " +
		"inner join atendimento_usuario ateusu " +
			"on ategruateusu.id_atendimento_usuario = ateusu.id " +
		"inner join informacao_atendimento infate " +
			"on ateusu.id_informacao_atendimento = infate.id " +
		"inner join descricao_tipo_atendimento destipate " +
			"on destipate.id = gru.id_descricao_tipo_atendimento " +
		"inner join modulo mod " +
			"on mod.id = modper.id_modulo " +
		"inner join tipo_atendimento tip " +
			"on tip.id = destipate.id_tipo_atendimento ";
	
	private static String SQL_CABECALHO_ATENDIMENTO_GRUPO = "select ategru.id, tip.id as id_tipo, tip.nome as tipo, gru.id_descricao_tipo_atendimento, destipate.nome as descricao, modper.id_modulo, mod.nome as modulo, ategru.data, infate.frequencia, ategru.categoria from grupo gru ";
	
	private static String SQL_ATENDIMENTO_INDIVIDUAL = "select ateind.id, tip.id as id_tipo, tip.nome as tipo, ateind.id_descricao_tipo_atendimento, destipate.nome as descricao, ateind.id_modulo, mod.nome as modulo, ateind.data, infate.frequencia, ateind.categoria from atendimento_individual ateind " + 
			"inner join informacao_atendimento infate " +
				"on ateind.id_informacao_atendimento = infate.id " +
			"inner join descricao_tipo_atendimento destipate " +
				"on destipate.id = ateind.id_descricao_tipo_atendimento " +
			"inner join modulo mod " +
				"on mod.id = ateind.id_modulo " +
			"inner join tipo_atendimento tip " +
				"on tip.id = destipate.id_tipo_atendimento " +
		"where ateind.status = 'NORMAL' ";

	
	@Resource(name = Registro.NOME_DATA_SOURCE_SISLARA)
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private String obterComandoSelecaoAtendimentoDeGrupoOuIndividual(Long idUsuario){
		return "select aux.* from( "+
				obterComandoSelecaoAtendimentoDeGrupo(idUsuario) +
				" union all " +
				SQL_ATENDIMENTO_INDIVIDUAL + 
					"and ateind.id_usuario = " + idUsuario + 
			")aux ";
	}
	
	private String obterComandoSelecaoAtendimentoDeGrupoOuIndividual(Espera espera) {
		return obterComandoSelecaoAtendimentoDeGrupoOuIndividual(espera.getUsuario().getId())
				+ "WHERE aux.frequencia = '" + Frequencia.AT.toString() + "' and aux.data between '"
				+ DataHoraUtils.formatarData(espera.getDataExpectativaCalendar()) + "' and '"
				+ DataHoraUtils.formatarData(DataHoraUtils.avancarDoisAnos(espera.getDataExpectativaCalendar())) + "'";
	}

	private String obterAtendimentosEmGrupoOuIndividual(Long idUsuario, Area area, boolean incluindo, int quantidade){
		return obterComandoSelecaoAtendimentoDeGrupoOuIndividual(idUsuario) +
		"where " + (incluindo == false ? "not" : "") + " exists ( " +
			"select * from bloqueio blo " + 
			"where blo.id_descricao_tipo_atendimento = aux.id_descricao_tipo_atendimento and blo.id_modulo = aux.id_modulo and blo.area = '" + area + "') " +
		"order by aux.data desc " +
		"limit " + quantidade + "; ";
	}
	
	private String obterComandoCondicionalAtendimentoGrupoStatusNormal(Long idUsuario){
		return "where ategru.status = 'NORMAL' and ateusu.id_usuario = " + idUsuario;
	}
	
	private String obterComandoSelecaoAtendimentoDeGrupo(Long idUsuario) {
		return SQL_CABECALHO_ATENDIMENTO_GRUPO + SQL_JUNCAO_ATENDIMENTO_GRUPO
				+ obterComandoCondicionalAtendimentoGrupoStatusNormal(idUsuario);
	}
	
	private String obterComandoSelecaoAtendimentoDeGrupoPorUsuarioDataDescricaoTipoAtendimentoEModulo(String data, Long idUsuario, Long idDescricaoTipoAtendimento, Long... idModulos) {
		return SQL_CABECALHO_ATENDIMENTO_GRUPO + SQL_JUNCAO_ATENDIMENTO_GRUPO
				+ obterComandoCondicionalAtendimentoGrupoStatusNormal(idUsuario) + " " 
				+ obterComandoCondicionalDescricaoTipoAtendimentoEModulos(idDescricaoTipoAtendimento, idModulos) +" and ategru.data = '" + data + "'";
	}
	
	private String obterComandoCodicionalModulos(Long... idModulos) {
		String modulos = "(";
		for (int i = 0; i < idModulos.length; i++) {
			modulos += idModulos[i].toString() + ",";
		}
		modulos = modulos.substring(0, modulos.length() - 1) + ") ";
		return modulos;
	}
	
	private String obterComandoCondicionalDescricaoTipoAtendimentoEModulos(Long idDescricaoTipoAtendimento, Long... idModulos){
		return "and destipate.id = " + idDescricaoTipoAtendimento + " and mod.id in " + obterComandoCodicionalModulos(idModulos);
	}
	
	private String obterComandoSelecaoAtendimentoDeGrupoAgrupadoPorDia(
			Long idUsuario, Long idDescricaoTipoAtendimento, Long... idModulos) {
		return 	"select ategru.data, gru.id_descricao_tipo_atendimento, count(ategru.id) from grupo gru " +
		SQL_JUNCAO_ATENDIMENTO_GRUPO + 
		obterComandoCondicionalAtendimentoGrupoStatusNormal(idUsuario) + " " + obterComandoCondicionalDescricaoTipoAtendimentoEModulos(idDescricaoTipoAtendimento, idModulos) + " " +
		"group by ategru.data, gru.id_descricao_tipo_atendimento " + 
		"having count(ategru.id) > 1";
	}

	@Override
	public List<Map<String, Object>> obterFrenquenciaPorProntuarioExcluindoArea(
			Long idUsuario, Area area) {
		return jdbcTemplate.queryForList(obterAtendimentosEmGrupoOuIndividual(idUsuario, area, false, 20));
	}
	
	@Override
	public boolean possuiAlgumAtendimentoNosUltimosDoisAnos(Espera espera) {
		return !jdbcTemplate.queryForList(obterComandoSelecaoAtendimentoDeGrupoOuIndividual(espera))
				.isEmpty();
	}

	public List<Map<String, Object>> obterFrenquenciaDeModulosNoMesmoDia(
			Long idusuario, Long idDescricaoTipoAtendimento, Long... idModulos) {
		List<Map<String, Object>> frenquenciaDeAtendimentosEmGrupoNoMesmoDia = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> datasQueContemAtendimentosNoMesmoDiaEmModulosDistintos = jdbcTemplate
				.queryForList(obterComandoSelecaoAtendimentoDeGrupoAgrupadoPorDia(
						idusuario, idDescricaoTipoAtendimento, idModulos));
		for (Map<String, Object> dataQueContemAtendimentosNoMesmoDiaEmModulosDistintos : datasQueContemAtendimentosNoMesmoDiaEmModulosDistintos) {
			String data = dataQueContemAtendimentosNoMesmoDiaEmModulosDistintos
					.get("data").toString();
			List<Map<String, Object>> atendimentos = jdbcTemplate
					.queryForList(obterComandoSelecaoAtendimentoDeGrupoPorUsuarioDataDescricaoTipoAtendimentoEModulo(
							data, idusuario, idDescricaoTipoAtendimento, idModulos));
			frenquenciaDeAtendimentosEmGrupoNoMesmoDia.addAll(atendimentos);
		}
		
		return frenquenciaDeAtendimentosEmGrupoNoMesmoDia;
	}

	@Override
	public List<Map<String, Object>> obterStatusDoUsuarioNosModulosDosGruposIniciadosNoAnoCorrenteComExcessaoDoReuniaoDeIntegracao(Usuario usuario) {
		String dataAtual = DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual());
		String sql = "select gru.id, nom.nome, gru.turma, gru.data_inicio, tip.nome as tipo, destipate.nome as descricao, mod.nome as modulo, modusu.status from grupo gru " +
			"inner join nome_grupo nom " +
				"on nom.id = gru.id_nome_grupo " +
			"inner join grupo_modulo_periodo grumodper " +   
				"on grumodper.id_grupo = gru.id " +  
			"inner join modulo_periodo modper " +  
				"on grumodper.id_modulo_periodo = modper.id " + 
			"inner join modulo_periodo_modulo_usuario modpermodusu " +
				"on modpermodusu.id_modulo_periodo = modper.id " +
			"inner join modulo_usuario modusu " +
				"on modusu.id = modpermodusu.id_modulo_usuario " +
			"inner join descricao_tipo_atendimento destipate " + 
				"on destipate.id = gru.id_descricao_tipo_atendimento " + 
			"inner join tipo_atendimento tip " + 
				"on tip.id = destipate.id_tipo_atendimento " +
			"inner join modulo mod " +  
				"on mod.id = modper.id_modulo " + 
			"where modusu.id_usuario = " + usuario.getId() + " and EXTRACT(YEAR FROM gru.data_inicio) = EXTRACT(YEAR FROM DATE '" + dataAtual + "') and gru.excluido = false and gru.ativo = true and mod.id not in ( " + Modulo.ID_MODULO_REUNIAO_INTEGRACAO.toString() + ")" +
		"order by gru.id;";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public Long obterProximoNossoNumero() {
		String sql = "select nextval('" + CHAVE_NOSSO_NUMERO + "') as " + CHAVE_NOSSO_NUMERO + ";";
		return (Long) jdbcTemplate.queryForMap(sql).get(CHAVE_NOSSO_NUMERO);
	}

	@Override
	public void aplicarSequence(Long sequence) {
		String sql = "ALTER SEQUENCE " + CHAVE_NOSSO_NUMERO + " RESTART WITH " + sequence.toString();
		jdbcTemplate.update(sql);
	}

	@Override
	public boolean nuncaParticipouReuniaoIntegracao(Usuario usuario) {
		String sql = "select * from atendimento_grupo ategru " +
		"inner join atendimento_grupo_atendimento_usuario ategruateusu " +
			"on ategruateusu.id_atendimento_grupo = ategru.id " +
		"inner join atendimento_usuario ateusu " +
			"on ategruateusu.id_atendimento_usuario = ateusu.id " +
		"inner join informacao_atendimento infate " +
			"on infate.id = ateusu.id_informacao_atendimento " +
		"inner join modulo_periodo_atendimento_grupo modperategru " +
			"on modperategru.id_atendimento_grupo = ategru.id " +
		"inner join modulo_periodo modper " +
			"on modper.id = modperategru.id_modulo_periodo " +
		"inner join grupo_modulo_periodo grumodper " +
			"on grumodper.id_modulo_periodo = modper.id " +
		"inner join grupo gru " +
			"on gru.id = grumodper.id_grupo " +
		"where ateusu.id_usuario = " + usuario.getId().toString() + 
		" and gru.id_descricao_tipo_atendimento = " + DescricaoTipoAtendimento.ID_SERVICO_SOCIAL.toString() +
		" and modper.id_modulo = " + Modulo.ID_MODULO_REUNIAO_INTEGRACAO.toString() + 
		" and infate.frequencia = '" + Frequencia.AT.toString() + "';";
		return jdbcTemplate.queryForList(sql).isEmpty();
	}

	@Override
	public void resolverPendencia(Grupo grupo) {
		String sql = "update pendencia set resolvida = true, obs = 'Pendencia resolvida automaticamente em consequencia a edição do grupo.' where id_grupo = "
				+ grupo.getId().toString() + " and tipo = '" + TipoPendencia.ATENDIMENTO_GRUPO.toString() + "' and resolvida = false";
		jdbcTemplate.update(sql);		
	}
	
	@Override
	public void resolverPendencia(Long idPendencia) {
		String sql = "update pendencia set resolvida = true where id = " + idPendencia;
		jdbcTemplate.update(sql);		
	}

	@Override
	public boolean cpfJaExiste(InformacaoEssencial informacaoEssencial) {
		String sql = "select id from informacao_essencial where cpf = '" + informacaoEssencial.getCpf() + "'";
		if (informacaoEssencial.possuiCpf()) {
			List<Map<String, Object>> idsExistentesDiferentesDoIdAtual = jdbcTemplate.queryForList(sql).stream()
					.filter(t -> t.containsKey("id") && !t.containsValue(informacaoEssencial.getId()))
					.collect(Collectors.toList());
			return !idsExistentesDiferentesDoIdAtual.isEmpty();
		} else {
			return false;
		}
	}

	@Override
	public boolean cpfCnpjJaExisteDoacaoMaquinaBrailleCartelaDeSelos(InformacaoEssencial informacaoEssencial) {
		String sql = "select * from doacoes_legado where cpf = '" + informacaoEssencial.getCpf() + "' and tipo='CARTELA_DE_SELOS_MAQUINA_BRAILLE'";
		return !jdbcTemplate.queryForList(sql).isEmpty();
	}

	@Override
	public boolean existeFeriadoOuPonteEmConflito(Agendamento agendamento) {
		String sql = "select * from feriado_ponte where data = '" + agendamento.getData() + "' and '"
				+ agendamento.getHorario().getHoraInicio() + "' between hora_inicio and hora_termino";
		return !jdbcTemplate.queryForList(sql).isEmpty();
	}

	@Override
	public List<Map<String, Object>> gerarRelatorioFrequenciasDeAtendimentosGlobaisPorUsuarioNoPeriodo(String prontuarios,
			String dataInicio, String dataTermino) {
		//Instala função de pivoteamento se necessário
		jdbcTemplate.execute("CREATE EXTENSION IF NOT EXISTS tablefunc;");
		
		String sql = "select * from crosstab("
				+ "'select distinct infess.nome::text as rowid, EXTRACT(DAY FROM aux.data)::int as attribute, case when EXTRACT(DAY FROM aux.data) <> 0 THEN ''X'' ELSE '''' END as value "
						+ "from usuario usu "
							+ "inner join informacao_essencial infess "
								+ "on infess.id = usu.id_informacao_essencial "
							+ "left join ( "
								+ "select ateusu.id_usuario, ategru.data from atendimento_usuario ateusu "
								+ "left join atendimento_grupo_atendimento_usuario ategruateusu "
									+ "on ateusu.id = ategruateusu.id_atendimento_usuario "
								+ "left join atendimento_grupo ategru "
									+ "ON ategru.id = ategruateusu.id_atendimento_grupo "
								+ "left join informacao_atendimento infate "
									+ "on infate.id = ateusu.id_informacao_atendimento "
								+ "left join atendimento_grupo_atendimento_profissional ategruatepro "
									+ "on ategruatepro.id_atendimento_grupo = ategru.id "
								+ "left join atendimento_profissional atepro "
									+ "on atepro.id = ategruatepro.id_atendimento_profissional "
								+ "left join informacao_atendimento infatepro "
									+ "on infatepro.id = atepro.id_informacao_atendimento "
								+ "where ategru.data between ''" + dataInicio + "'' and ''" + dataTermino + "'' and infate.frequencia = ''AT'' and infatepro.frequencia = ''AT'' "
							+ ")aux on aux.id_usuario = usu.id "
				+ "WHERE usu.id in ( "
				+ prontuarios
				+ ") "
				+ "order by 1, 2',"
				 + "'select m from generate_series(1,31) m'"
				+ ") as ct(row_name TEXT, \"1\" TEXT, \"2\" TEXT, \"3\" TEXT, \"4\" TEXT, \"5\" TEXT, \"6\" TEXT, \"7\" TEXT, \"8\" TEXT, \"9\" TEXT, \"10\" TEXT, \"11\" TEXT, \"12\" TEXT, \"13\" TEXT, \"14\" TEXT, \"15\" TEXT, \"16\" TEXT, \"17\" TEXT, \"18\" TEXT, \"19\" TEXT, \"20\" TEXT, \"21\" TEXT, \"22\" TEXT, \"23\" TEXT, \"24\" TEXT, \"25\" TEXT, \"26\" TEXT, \"27\" TEXT, \"28\" TEXT, \"29\" TEXT, \"30\" TEXT, \"31\" TEXT);";
		List<Map<String, Object>> resultado = jdbcTemplate.queryForList(sql);
		return resultado;
	}
}

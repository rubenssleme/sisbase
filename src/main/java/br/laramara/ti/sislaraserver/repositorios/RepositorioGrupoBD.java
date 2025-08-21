package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheGrupo;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

@Repository
public class RepositorioGrupoBD extends RepositorioDB<Grupo> implements
		RepositorioGrupo {

	private JdbcTemplate jdbcTemplate;

	@Inject
	private CacheGrupo cacheGrupo;

	@Resource(name = Registro.NOME_DATA_SOURCE_SISLARA)
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	public Grupo salvar(Grupo grupoASalvar) {
		String acao;
		Grupo grupoSalvo = null;
		try {
			if (grupoASalvar.getId() == null) {
				em.merge(grupoASalvar);
				grupoSalvo = (Grupo) obterPorId(obterValorIdentificador(Grupo.SEQUENCIA));
				acao = "Inclusão";
			} else {
				em.merge(grupoASalvar);
				grupoSalvo = (Grupo) obterPorId(grupoASalvar.getId());
				acao = "Alteração";
			}
			grupoSalvo.mudarVersao();
			cacheGrupo.atualizarCache(grupoSalvo);
			logger.info(acao + " do " + grupoSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ grupoSalvo + ". \nDetalhes: " + e);
		}
		return grupoSalvo;
	}

	@Override
	public List<Grupo> obterAtivos() {
		List<Grupo> grupos = new ArrayList<>();
	
		TypedQuery<Grupo> query = em.createQuery(
				"SELECT g FROM Grupo g WHERE g.ativo IS TRUE AND g.excluido is FALSE", Grupo.class);
		try {
			grupos = query.getResultList();
			Collections.sort(grupos, Grupo.obterComparador());
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Grupo ativos. \nDetalhe:"
					+ e);
		}
		return grupos;
	}
	
	public List<Grupo> obterInativosPorNome(String nomeGrupo) {
		List<Grupo> grupos = new ArrayList<>();
		if (!nomeGrupo.isEmpty()) {
			try {
				TypedQuery<Grupo> query = em
						.createQuery(
								"SELECT g FROM Grupo g WHERE LOWER(g.nomeGrupo.nome) LIKE :nomeGrupo AND g.ativo is FALSE AND g.excluido is FALSE",
								Grupo.class);
				query.setParameter("nomeGrupo", "%" + nomeGrupo.toLowerCase()
						+ "%");
				grupos = query.getResultList();
				Collections.sort(grupos, Grupo.obterComparador());
			} catch (Exception e) {
				logger.fatal("Erro durante pesquisa de Grupo por nome.\n Detalhes:"
						+ e);
			}
		}
		return grupos;
	}

	public boolean possuiGrupoAtivo(Grupo grupo) {
		return cacheGrupo.possuiGrupoAtivo(grupo);
	}

	@Override
	public List<String> obterNomesGrupos(String nomeGrupo) {
		List<String> nomesGrupos = new ArrayList<>();
		if (nomeGrupo.length() > 0) {
			List<Map<String, Object>> resultadoDoSQL = jdbcTemplate
					.queryForList("SELECT UPPER(CONCAT(nomgru.nome, '-', gru.turma, '-', to_char(gru.data_inicio, 'DD/MM/YYYY'))) as nome "
							+ "FROM grupo gru "
							+ "INNER JOIN nome_grupo nomgru "
							+ "ON nomgru.id = gru.id_nome_grupo "
							+ "WHERE gru.excluido is false AND UPPER(CONCAT(nomgru.nome, '-', gru.turma, '-', to_char(gru.data_inicio, 'DD/MM/YYYY'))) like '"
							+ nomeGrupo.toUpperCase()
							+ "%' "
							+ "ORDER BY nomgru.nome, gru.turma, gru.data_inicio");
	
			for (Map<String, Object> resultadoIndividual : resultadoDoSQL) {
				nomesGrupos.add((String) resultadoIndividual.get("nome"));
			}
		}
		return nomesGrupos;
	}

	@Override
	public Grupo obterGrupoPorId(Long id) {
		return cacheGrupo.obterGrupoPorId(id);
	}

	@Override
	public boolean confirmaVersaoAtualPorIdGrupo(Long id, String versao) {
		return cacheGrupo.confirmaVersaoAtualPorIdGrupo(id, versao);
	}

	@Override
	public boolean confirmaVersaoAtualPorIdModuloPeriodo(Long id, String versao) {
		return cacheGrupo.confirmaVersaoAtualPorIdModuloPeriodo(id, versao);
	}

	@Override
	public ModuloPeriodo obterModuloPeriodoPorId(Long id) {
		return cacheGrupo.obterModuloPeriodoPorId(id);
	}

	@Override
	public List<Grupo> obterAtivos(boolean todos, String nomeGrupoETurma,
			boolean exato) {
		return cacheGrupo.obterAtivos(todos, nomeGrupoETurma, exato);
	}

	@Override
	public List<Grupo> obterInativos(String nomeGrupo) {
		return cacheGrupo.obterInativos(nomeGrupo);
	}

	@Override
	public List<Grupo> obterGruposAtivosSemAtendimentosEIntegrantes(
			String parametro) {
		return cacheGrupo
				.obterGruposAtivosSemAtendimentosEIntegrantes(parametro);
	}

	@Override
	public Grupo obterGrupoSemAtendimentosEIntegrantesPorId(Long id) {
		return cacheGrupo.obterGrupoSemAtendimentosEIntegrantesPorId(id);
	}

	@Override
	public List<ModuloPeriodo> obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(Usuario usuario) {
		return cacheGrupo.obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(usuario);
	}
	
	@Override
	public List<Profissional> obterProfissionaisDeModulosPeriodosDeGruposAtivosComUsuarioIntegrado(Usuario usuario) {
		return cacheGrupo.obterProfissionaisDeModulosPeriodosDeGruposAtivosComUsuarioIntegrado(usuario);
	}

	@Override
	public List<ModuloPeriodo> obterModulosPeriodosIrmaos(ModuloPeriodo moduloPeriodo) {
		Grupo grupo = cacheGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId());
		List<ModuloPeriodo> modulosPeriodosIrmaos = new ArrayList<>();
		for (ModuloPeriodo moduloPeriodoIrmao : grupo.getModulosPeriodos()) {
			if (!moduloPeriodoIrmao.equals(moduloPeriodo)) {
				modulosPeriodosIrmaos.add(moduloPeriodoIrmao);
			}
		}
		return modulosPeriodosIrmaos;
	}

	@Override
	public Grupo obterGrupoPorIdModuloPeriodo(Long idModuloPeriodo) {
		return cacheGrupo.obterGrupoPorIdModuloPeriodo(idModuloPeriodo);
	}

	@Override
	public List<ModuloPeriodo> obterModuloPeriodoServicoSocialReuniaoIntegracaoAtivosComDataFuturaEPeriodoCompativel(
			ModuloPeriodo moduloPeriodo) {
		return cacheGrupo.obterModuloPeriodoServicoSocialReuniaoIntegracaoAtivosComPeriodoCompativel(moduloPeriodo);
	}

	@Override
	public List<Grupo> obterGruposDescricaoServicoSocialModuloReuniaoDeIntegracaoAtivos() {
		return cacheGrupo.obterGruposDescricaoServicoSocialModuloReuniaoDeIntegracaoAtivos();
	}

	@Override
	public Grupo obterGrupoPorIdAtendimentoGrupo(Long idAtendimentoGrupo) {
		return cacheGrupo.obterGrupoPorIdAtendimentoGrupo(idAtendimentoGrupo);
	}
}

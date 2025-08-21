package br.laramara.ti.sislaraserver.repositorios;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheGrupo;

@Repository
public class RepositorioAtendimentoGrupoBD extends
		RepositorioDB<AtendimentoGrupo> implements RepositorioAtendimentoGrupo {

	@Inject
	private CacheGrupo cacheGrupo;
	@Inject
	private RepositorioArquivo repositorioArquivo;

	@Transactional
	public AtendimentoGrupo salvar(AtendimentoGrupo atendimentoGrupoASalvar) {
		AtendimentoGrupo atendimentoGrupoSalvo = null;
		try {
			em.merge(atendimentoGrupoASalvar);
			atendimentoGrupoSalvo = obterPorId(atendimentoGrupoASalvar.getId());
			cacheGrupo.atualizarCache(atendimentoGrupoSalvo);
			repositorioArquivo.salvar(atendimentoGrupoASalvar);
			logger.info("Alteração do " + atendimentoGrupoSalvo
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ atendimentoGrupoASalvar + ". \nDetalhes: " + e);
		}
		return atendimentoGrupoSalvo;
	}
}

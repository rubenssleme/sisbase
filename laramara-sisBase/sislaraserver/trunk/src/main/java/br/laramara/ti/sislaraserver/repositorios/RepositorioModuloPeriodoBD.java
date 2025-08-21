package br.laramara.ti.sislaraserver.repositorios;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheGrupo;

@Repository
public class RepositorioModuloPeriodoBD extends RepositorioDB<ModuloPeriodo>
		implements RepositorioModuloPeriodo {

	@Inject
	private CacheGrupo cacheGrupo;
	
	@Transactional
	public ModuloPeriodo salvar(ModuloPeriodo moduloPeriodoASalvar) {
		ModuloPeriodo moduloPeriodoSalvo = null;
		try {
			em.merge(moduloPeriodoASalvar);
			moduloPeriodoSalvo = obterPorId(moduloPeriodoASalvar.getId());
			cacheGrupo.atualizarCache(moduloPeriodoSalvo);
			logger.info("Alteração do " + moduloPeriodoSalvo
					+ " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ moduloPeriodoASalvar + ". \nDetalhes: " + e);
		}

		return moduloPeriodoSalvo;
	}

	@Override
	public ModuloPeriodo obterPorAtendimentoGrupo(AtendimentoGrupo atendimentoGrupo) {
		return cacheGrupo.obterModuloPeriodoPorAtendimentoGrupo(atendimentoGrupo);
	}
}

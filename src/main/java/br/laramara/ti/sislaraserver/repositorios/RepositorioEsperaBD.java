package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheEspera;

@Repository
public class RepositorioEsperaBD extends RepositorioDB<Espera> implements
		RepositorioEspera {
	
	@Inject
	private CacheEspera cacheEspera;
	
	@Transactional
	public Espera salvar(Espera espera) {
		String acao;
		try {
			if (espera.getId() == null) {
				em.persist(espera);
				acao = "Inclusão";
			} else {
				em.merge(espera);
				acao = "Alteração";
			}
			cacheEspera.atualizarCache(espera);
			logger.info(acao + " do " + espera + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do "
					+ espera + ". \nDetalhes: " + e);
		}
		return espera;
	}

	public List<Espera> obterTodos() {
		List<Espera> esperas = new ArrayList<>();
		try {
			TypedQuery<Espera> query = em.createQuery("SELECT e FROM Espera e",
					Espera.class);
			esperas = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Espera.\n Detalhes:" + e);
		}
		return esperas;
	}
	
	@Override
	public List<Espera> obterPor(
			EspecificacaoPesquisaEspera especificacaoPesquisaEspera) {
		return cacheEspera.obterPor(especificacaoPesquisaEspera);
	}

	@Override
	public String obterObsHistoricas(InformacaoEssencial informacaoEssencial) {
		String retorno = "";
		List<Espera> esperas = new ArrayList<>();
		try {
			TypedQuery<Espera> query = em
					.createQuery(
							"SELECT e FROM Espera e "
									+ " LEFT JOIN e.usuario.informacaoEssencial iu "
									+ " LEFT JOIN e.preCadastro.informacaoEssencial ip "
									+ " WHERE iu = :informacaoEssencial OR ip = :informacaoEssencial"
									+ " ORDER BY e.data", Espera.class);
			query.setParameter("informacaoEssencial", informacaoEssencial);
			esperas = query.getResultList();
		} catch (Exception e) {
			logger.fatal("Erro durante pesquisa de Espera.\n Detalhes:" + e);
		}

		for (Espera espera : esperas) {
			if (!espera.estaCancelado()) {
				retorno += "Descrição: "
						+ espera.getDescricaoTipoAtendimento().getNome() + "\n";
				retorno += "Módulo: " + espera.getModulo().getNome() + "\n";
				retorno += "Expectativa: " + espera.getDataExpectativa() + "\n";
				retorno += espera.getObs() + "\n";
				retorno += "Solicitada por: "
						+ (espera.getProfissionalSolicitou() != null ? espera
								.getProfissionalSolicitou().getNome() : "")
						+ "\n";
				retorno += "----\n";
			}
		}
		return retorno;
	}
}

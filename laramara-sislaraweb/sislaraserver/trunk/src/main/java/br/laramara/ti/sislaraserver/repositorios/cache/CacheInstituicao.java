package br.laramara.ti.sislaraserver.repositorios.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rits.cloning.Cloner;

import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInstituicao;

@Component
public class CacheInstituicao {

	private final Logger logger;

	@Inject
	private RepositorioInstituicao repositorioInstituicao;
	private Map<Long, Instituicao> cacheInstituicao;

	public CacheInstituicao() {
		logger = Logger.getLogger(getClass());
		cacheInstituicao =  new ConcurrentHashMap<>();
	}

	public List<Instituicao> obterTodos() {
		inicializar();
		List<Instituicao> instituicoes = new ArrayList<>();
		for(Instituicao instituicao :  cacheInstituicao.values()){
			instituicoes.add(instituicao);
		}
		return new Cloner().deepClone(instituicoes);
	}
	
	public synchronized void inicializar() {
		try {
			if (cacheInstituicao.isEmpty()) {
				logger.info("Cache do Instituição inicializando...");
				List<Instituicao> instituicoes = repositorioInstituicao.obterTodosOrdenadasPorNome();
				for (Instituicao instituicao : instituicoes) {
					atualizarCache(instituicao);
				}
				logger.info("Cache do Instituição inicializado com sucesso.");
			}
		} catch (Exception e) {
			logger.fatal("Erro durante inicialização de Cache dos Grupos. Detalhes: "
					+ e);
		}
	}
	
	public void atualizarCache(Instituicao instituicaoASalvar) {
		cacheInstituicao.put(instituicaoASalvar.getId(), instituicaoASalvar);
	}

	public synchronized void atualizarCache() {
		cacheInstituicao = new HashMap<>();
		inicializar();
		logger.info("Cache de Instituicoes atualizado.");
	}

	public void limpar() {
		cacheInstituicao.clear();
	}
}

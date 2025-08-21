package br.laramara.ti.sislaraserver.repositorios.cache;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rits.cloning.Cloner;

import br.laramara.ti.sislaraserver.dominio.espera.EspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;

@Component
public class CacheEspera {

	private final Logger logger;

	@Inject
	private RepositorioEspera repositorioEspera;
	private static Map<Long, Espera> cacheEspera;

	public CacheEspera() {
		logger = Logger.getLogger(getClass());
		cacheEspera = new HashMap<>();
	}

	public synchronized void atualizarCache(){
		cacheEspera = new HashMap<>();
		inicializar();
		logger.info("Cache da Lista de Espera atualizado.");
	}
	
	public synchronized void inicializar() {
		if (cacheEspera.isEmpty()) {
			logger.info("Cache da Espera inicializando...");
			for (Espera espera : repositorioEspera.obterTodos()) {
				atualizarCache(espera);
			}
			logger.info("Cache da Espera inicializado com sucesso.");
		}
	}

	public synchronized void atualizarCache(Espera espera) {
		try {
			cacheEspera.put(espera.getId(), espera);
		} catch (Exception e) {
			logger.fatal("Erro durante salvamento de " + espera
					+ " no cache. \nDetalhes: " + e);
		}
	}
	
	private Stream<Espera> filtrar(Stream<Espera> esperas, boolean filtrar, Predicate<Espera> predicado) {
		if (filtrar) {
			return esperas.filter(predicado);
		} else {
			return esperas;
		}
	}

	public synchronized List<Espera> obterPor(EspecificacaoPesquisaEspera especificacaoPesquisaEspera) {
		inicializar();

		Stream<Espera> resultadoParcial = filtrarData(especificacaoPesquisaEspera.getDataInicio(),
				especificacaoPesquisaEspera.getDataTermino(), cacheEspera.values().parallelStream());

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getDescricaoTipoAtendimento() != null,
				(Espera espera) -> espera.getDescricaoTipoAtendimento()
						.equals(especificacaoPesquisaEspera.getDescricaoTipoAtendimento()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getModulo() != null,
				(Espera espera) -> espera.getModulo().equals(especificacaoPesquisaEspera.getModulo()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getNomeGrupo() != null,
				(Espera espera) -> espera.getNomeGrupo() != null
						&& espera.getNomeGrupo().equals(especificacaoPesquisaEspera.getNomeGrupo()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getTipoEspera() != null,
				(Espera espera) -> espera.getTipoEspera() != null
						&& espera.getTipoEspera().equals(especificacaoPesquisaEspera.getTipoEspera()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getStatusEspera() != null,
				(Espera espera) -> espera.obterStatus().equals(especificacaoPesquisaEspera.getStatusEspera()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getSetor() != null,
				(Espera espera) -> espera.getSetor().equals(especificacaoPesquisaEspera.getSetor()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getProntuario() != null,
				(Espera espera) -> espera.getUsuario() != null
						&& espera.getUsuario().getId().equals(especificacaoPesquisaEspera.getProntuario()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.getRg() != null,
				(Espera espera) -> espera.obterRg().equals(especificacaoPesquisaEspera.getRg()));

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.isInteresse(),
				(Espera espera) -> espera.isInteresse() == especificacaoPesquisaEspera.isInteresse());

		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.isLmLigou(),
				(Espera espera) -> espera.isLmLigou() == especificacaoPesquisaEspera.isLmLigou());
		
		resultadoParcial = filtrar(resultadoParcial, especificacaoPesquisaEspera.isPendencias(),
				(Espera espera) -> espera.isPendencias() == especificacaoPesquisaEspera.isPendencias());

		return new Cloner().deepClone(resultadoParcial.collect(Collectors.toList()));
	}

	private synchronized Stream<Espera> filtrarData(Calendar dataInicio, Calendar dataTermino, Stream<Espera> esperas) {
		return esperas.filter(espera -> espera.dentroDoPeriodo(dataInicio, dataTermino));
	}

	public void limpar() {
		cacheEspera.clear();
	}
}

package br.laramara.ti.sislaraserver.repositorios.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.rits.cloning.Cloner;

import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloUsuario;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;

@Component
public class CacheGrupo {

	private final Logger logger;
	
	@Inject
	private RepositorioGrupo repositorioGrupo;
	private Map<Long, Grupo> cacheGruposAtivos;

	public CacheGrupo() {
		logger = Logger.getLogger(getClass());
		cacheGruposAtivos = new ConcurrentHashMap<>();
	}

	public List<Grupo> obterAtivos() {
		return obterAtivos(true, "", false);
	}
	
	public synchronized List<Grupo> obterAtivos(boolean todos, String nomeGrupoETurma,
			boolean exato) {
		inicializar();
		List<Grupo> retorno = new ArrayList<>();
		for (Entry<Long, Grupo> grupoAtivo : cacheGruposAtivos.entrySet()) {
			Grupo grupo = grupoAtivo.getValue();
			if (todos
					|| (nomeGrupoETurma != null && !nomeGrupoETurma.isEmpty() && grupo
							.isAtivo() && (exato ? grupo.obterNomeGrupoETurma()
							.toLowerCase()
							.equals(nomeGrupoETurma.toLowerCase()) : grupo
							.obterNomeGrupoETurma().toLowerCase()
							.startsWith(nomeGrupoETurma.toLowerCase())))) {
				retorno.add(grupoAtivo.getValue());
			}
		}
		Collections.sort(retorno, Grupo.obterComparador());
		return retorno;
	}

	public synchronized void inicializar() {
		try {
			if (cacheGruposAtivos.isEmpty()) {
				logger.info("Cache do Grupo inicializando...");
				List<Grupo> gruposAtivos = repositorioGrupo.obterAtivos();
				for (Grupo grupoAtivo : gruposAtivos) {
					salvar(grupoAtivo);
				}
				logger.info("Cache do Grupo inicializado com sucesso.");
			}
		} catch (Exception e) {
			logger.fatal("Erro durante inicialização de Cache dos Grupos. Detalhes: "
					+ e);
		}
	}

	public void limpar(){
		cacheGruposAtivos.clear();
	}
	
	public boolean possuiGrupoAtivo(Grupo grupoAVerificar) {
		for (Grupo grupo : obterAtivos(false, grupoAVerificar.obterNomeGrupoETurma(), true)) {
			if (grupo.obterNomeGrupoETurma().equals(
					grupoAVerificar.obterNomeGrupoETurma())
					&& !grupo.getId().equals(grupoAVerificar.getId())) {
				return true;
			}
		}
		return false;
	}
	
	private synchronized void salvar(Grupo grupo) {
		cacheGruposAtivos.put(grupo.getId(), grupo);
	}
	
	public synchronized void salvar(List<Grupo> grupos) {
		for (Grupo grupo : grupos) {
			salvar(grupo);
		}
	}
	
	public synchronized void atualizarCache(Grupo grupo) {
		try {
			if (grupo != null) {
				salvar(grupo);
			}
		} catch (Exception e) {
			logger.fatal("Erro durante adição de item ao Cache dos Grupos. Detalhes: "
					+ e);
		}
	}

	public synchronized void atualizarCache(ModuloPeriodo moduloPeriodoAAtualizar) {
		Grupo grupo = obterGrupoPorIdModuloPeriodo(moduloPeriodoAAtualizar.getId());
		grupo.atualizarModuloPeriodo(moduloPeriodoAAtualizar);
		grupo.mudarVersao();
		atualizarCache(grupo);
	}

	public synchronized void atualizarCache(AtendimentoGrupo atendimentoGrupoAAtualizar) {
		Grupo grupo = obterGrupoPorIdAtendimentoGrupo(atendimentoGrupoAAtualizar.getId());
		grupo.atualizarAtendimendoGrupo(atendimentoGrupoAAtualizar);
		grupo.mudarVersao();
		atualizarCache(grupo);
	}
	
	public synchronized Grupo obterGrupoPorId(Long id) {
		inicializar();
		if (id != null && cacheGruposAtivos.containsKey(id)) {
			return new Cloner().deepClone(this.cacheGruposAtivos.get(id));
		} else {
			return null;
		}
	}
	
	public synchronized Grupo obterGrupoSemAtendimentosEIntegrantesPorId(Long id) {
		return removerAtendimentoEIntegrantes(new Cloner()
				.deepClone(this.cacheGruposAtivos.get(id)));
	}
	
	public synchronized Grupo obterGrupoPorIdModuloPeriodo(Long idModuloPeriodo) {
		List<Grupo> grupos = obterAtivos();
		for (Grupo grupo : grupos) {
			for (ModuloPeriodo moduloPeriodo : grupo.getModulosPeriodos()) {
				if (moduloPeriodo.getId().equals(idModuloPeriodo)) {
					return new Cloner().deepClone(grupo);
				}
			}
		}
		return null;
	}

	public synchronized Grupo obterGrupoPorIdAtendimentoGrupo(
			Long idAtendimentoGrupo) {
		List<Grupo> grupos = obterAtivos();
		for (Grupo grupo : grupos) {
			for (ModuloPeriodo moduloPeriodo : grupo.getModulosPeriodos()) {
				for (AtendimentoGrupo atendimentoGrupo : moduloPeriodo
						.getAtendimentosGrupo()) {
					if (atendimentoGrupo.getId().equals(idAtendimentoGrupo)) {
						return new Cloner().deepClone(grupo);
					}
				}
			}
		}
		return null;
	}

	public synchronized ModuloPeriodo obterModuloPeriodoPorId(Long idModuloPeriodoABuscar) {
		List<Grupo> grupos = obterAtivos();
		for (Grupo grupo : grupos) {
			for (ModuloPeriodo moduloPeriodo : grupo.getModulosPeriodos()) {
				if (moduloPeriodo.getId().equals(idModuloPeriodoABuscar)) {
					return new Cloner().deepClone(moduloPeriodo);
				}
			}
		}
		return null;
	}

	public synchronized List<Grupo> obterGruposAtivosSemAtendimentosEIntegrantes(String nomeGrupo) {
		List<Grupo> grupos = new Cloner().deepClone(obterAtivos(false, nomeGrupo, false));
		for (Grupo grupo : grupos) {
			removerAtendimentoEIntegrantes(grupo);
		}
		return grupos;
	}

	private synchronized Grupo removerAtendimentoEIntegrantes(Grupo grupo){
		for (ModuloPeriodo moduloPeriodo : grupo.getModulosPeriodos()) {
			moduloPeriodo.getAtendimentosGrupo().clear();
			moduloPeriodo.getModulosUsuarios().clear();
			moduloPeriodo.getModulosPreCadastro().clear();
		}
		return grupo;
	}
	
	public boolean confirmaVersaoAtualPorIdGrupo(Long idGrupo, String versao) {
		Grupo grupo = obterGrupoPorId(idGrupo);
		return grupo == null || grupo.getVersao().equals(versao);
	}

	public boolean confirmaVersaoAtualPorIdModuloPeriodo(Long idModuloPerido,
			String versao) {
		Grupo grupo = obterGrupoPorIdModuloPeriodo(idModuloPerido);
		return grupo == null || grupo.getVersao().equals(versao);
	}

	public synchronized List<Grupo> obterInativos(String parametro) {
		List<Grupo> gruposInativos = repositorioGrupo.obterInativosPorNome(parametro);
		salvar(gruposInativos);
		return new Cloner().deepClone(gruposInativos);
	}

	public synchronized List<ModuloPeriodo> obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(
			Usuario usuario) {
		List<ModuloPeriodo> moduloPeriodosDeGruposAtivosComUsuarioNaoDesligado = new ArrayList<>();
		for (Grupo grupo : obterAtivos(true, "", false)) {
			for (ModuloPeriodo moduloPeriodo : grupo.getModulosPeriodos()) {
				for (ModuloUsuario moduloUsuario : moduloPeriodo
						.getModulosUsuarios()) {
					if (moduloUsuario.getUsuario() != null
							&& moduloUsuario.getUsuario().equals(usuario)
							&& !moduloUsuario.estaDesligado())
						moduloPeriodosDeGruposAtivosComUsuarioNaoDesligado.add(new Cloner()
								.deepClone(moduloPeriodo));
				}
			}
		}
		return moduloPeriodosDeGruposAtivosComUsuarioNaoDesligado;
	}

	public List<Profissional> obterProfissionaisDeModulosPeriodosDeGruposAtivosComUsuarioIntegrado(Usuario usuario) {
		List<ModuloPeriodo> modulosPeriodosDeGruposAtivosComUsuarioIntegrado = (List<ModuloPeriodo>) obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(
				usuario).stream()
						.filter(moduloPeriodo -> moduloPeriodo.existeUsuarioIntegrado(usuario)
								&& !moduloPeriodo.ePossuiModuloDeReuniaoIntegracao()
								&& !obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId())
										.eDescricaoAtividadeDeCulturaELazer()
								&& !moduloPeriodo.ePossuiModuloPsicossocialParaAsFamilias()
						&& !obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId()).eDescricaoOrientacaoSocioeducativa())
						.collect(Collectors.toList());
		
		List<Profissional> profissionaisDosGruposAtivosComUsuarioIntegrado = modulosPeriodosDeGruposAtivosComUsuarioIntegrado
				.stream().flatMap(moduloPeriodoComUsuarioIntegrado -> moduloPeriodoComUsuarioIntegrado
						.obterSomenteProfissionais().stream())
				.distinct().collect(Collectors.toList());

		return new Cloner().deepClone(profissionaisDosGruposAtivosComUsuarioIntegrado);
	}

	public List<ModuloPeriodo> obterModuloPeriodoServicoSocialReuniaoIntegracaoAtivosComPeriodoCompativel(
			ModuloPeriodo moduloPeriodo) {
		List<ModuloPeriodo> moduloPeriodoDeGrupoDeServicoSocialComModuloReuniaoDeIntegracao = obterAtivos().stream()
				.filter(grupo -> grupo.isAtivo() && grupo
						.eDescricaoServicoSocialModuloReuniaoDeIntegracaoComDataFuturaEPeriodoCompativel(moduloPeriodo))
				.sorted((grupoA, grupoB) -> grupoA.obterDataInicioDaReuniaoDeIntegracao()
						.compareTo(grupoB.obterDataInicioDaReuniaoDeIntegracao()))
				.map(Grupo::obterModuloPeriodoDeReuniaoDeIntegracao).collect(Collectors.toList());
		return new Cloner().deepClone(moduloPeriodoDeGrupoDeServicoSocialComModuloReuniaoDeIntegracao);
	}

	public synchronized void atualizarCache() {
		cacheGruposAtivos = new HashMap<>();
		inicializar();
		logger.info("Cache de Grupo atualizado.");
	}

	public List<Grupo> obterGruposDescricaoServicoSocialModuloReuniaoDeIntegracaoAtivos() {
		List<Grupo> gruposDescricaoServicoSocialComModuloReuniaoDeIntegracaoAtivos = obterAtivos().stream()
				.filter(grupo -> grupo.isAtivo() && grupo.eDescricaoServicoSocialComModuloReuniaoDeIntegracao())
				.collect(Collectors.toList());
		return new Cloner().deepClone(gruposDescricaoServicoSocialComModuloReuniaoDeIntegracaoAtivos);
	}
}

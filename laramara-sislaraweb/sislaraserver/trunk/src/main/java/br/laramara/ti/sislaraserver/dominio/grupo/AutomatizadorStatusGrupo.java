package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioModuloPeriodo;

@Component
public class AutomatizadorStatusGrupo {

	private final Logger logger = Logger
			.getLogger(AutomatizadorStatusGrupo.class);

	@Inject
	private RepositorioGrupo repositorioGrupo;
	@Inject
	private RepositorioModuloPeriodo repositorioModuloPeriodo;

	public void efetuarDesligamentosEmTodosOsGruposAtivosDosUsuariosDesligados(
			ModuloPeriodo moduloPeriodo) {
		for (ModuloUsuario moduloUsuarioDesligado : moduloPeriodo
				.getModulosUsuariosDesligados()) {
			Usuario usuarioADesligar = moduloUsuarioDesligado.getUsuario();
			Grupo grupoComModuloUsuarioDesligado = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId());
			List<ModuloPeriodo> modulosPeriodosDeGruposAtivosComUsuarioNaoDesligado = repositorioGrupo
					.obterModulosPeriodosDeGruposAtivosComUsuarioNaoDesligado(usuarioADesligar);
			for (ModuloPeriodo moduloPeriodoDeGrupoAtivoComUsuarioNaoDesligado : modulosPeriodosDeGruposAtivosComUsuarioNaoDesligado) {
				moduloPeriodoDeGrupoAtivoComUsuarioNaoDesligado.desligarTodosOsModulosComUsuario(usuarioADesligar,
						grupoComModuloUsuarioDesligado, moduloPeriodo, moduloUsuarioDesligado.getDataOcorrencia(),
						moduloUsuarioDesligado.getObs());
				logger.info("Desligamento automático do " + usuarioADesligar
						+ " solicidado no "
						+ moduloPeriodoDeGrupoAtivoComUsuarioNaoDesligado);
				repositorioModuloPeriodo
						.salvar(moduloPeriodoDeGrupoAtivoComUsuarioNaoDesligado);
			}
		}
	}
}

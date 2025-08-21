package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioModuloPeriodo;

@Component
public class AutomatizadorModuloPeriodo {
	
	private final Logger logger = Logger
			.getLogger(AutomatizadorModuloPeriodo.class);

	@Inject
	private RepositorioModuloPeriodo repositorioModuloPeriodo;
	@Inject
	private RepositorioGrupo repositorioGrupo;

	public void aplicarRegraPropagacaoStatusNaoAcessoOuProvisorioAosOutrosModulosDoGrupo(ModuloPeriodo moduloPeriodo) {
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId());
		List<ModuloUsuario> modulosUsuariosComStatusDiferenteDeProvisorioEAcesso = moduloPeriodo
				.obterUsuariosComStatusDiferenteDeProvisorioEAcesso();
		for (ModuloUsuario moduloUsuarioComStatusDiferenteDeProvisorioEAcesso : modulosUsuariosComStatusDiferenteDeProvisorioEAcesso) {
			List<ModuloPeriodo> modulosPeriodosIrmaosComUsuario = grupo.getModulosPeriodos().stream()
					.filter(moduloPeriodoAVerificarExistenciaUsuarioComStatusDiferente -> !moduloPeriodoAVerificarExistenciaUsuarioComStatusDiferente
							.equals(moduloPeriodo)
							&& moduloPeriodoAVerificarExistenciaUsuarioComStatusDiferente
									.existeUsuarioComStatusRelacaoDiferente(
											moduloUsuarioComStatusDiferenteDeProvisorioEAcesso))
					.collect(Collectors.toList());
			modulosPeriodosIrmaosComUsuario.forEach(moduloPeriodoIrmaoComComUsuario -> moduloPeriodoIrmaoComComUsuario
					.propagarStatus(moduloPeriodo, moduloUsuarioComStatusDiferenteDeProvisorioEAcesso));
			modulosPeriodosIrmaosComUsuario.forEach(
					moduloPeriodoIrmaoComUsuario -> repositorioModuloPeriodo.salvar(moduloPeriodoIrmaoComUsuario));
		}
	}
	
	public void aplicarRegraDeIntegradoOuProvisorioOuAcessoAosModulosPeriodosDePssicossocialNosGruposDeAtendimentoEspecializadoGlobalOuTranstornoGlobalDoDesenvolvimento(
			ModuloPeriodo moduloPeriodoAEE) {
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodoAEE.getId());
		if ((grupo.eAtendimentoEspecializadoGlobal() || grupo.eTranstornoGlobalDoDesenvolvimento())
				&& moduloPeriodoAEE.eAtendimentoEspecificoEspecializado()) {
			List<ModuloPeriodo> modulosPeriodosIrmaos = repositorioGrupo.obterModulosPeriodosIrmaos(moduloPeriodoAEE);
			ModuloPeriodo moduloPeriodoPsicossocialParaAsFamilias = obterModuloPsicossocialParaAsFamilias(
					modulosPeriodosIrmaos);
			if (moduloPeriodoPsicossocialParaAsFamilias != null) {
				List<ModuloUsuario> moduloUsuariosComStatusIntegradorOuProvisorioOuAcesso = moduloPeriodoAEE
						.obterModuloUsuariosComStatusIntegradoOuProvisorioOuAcesso();
				moduloPeriodoPsicossocialParaAsFamilias
						.incluirOuAlterarStatusModuloUsuario(moduloUsuariosComStatusIntegradorOuProvisorioOuAcesso);
				logger.info(
						"Aplicação de regra de atualização de modulo perído com Psicossocial de um AEE Integrado, Provisório ou Acesso solicidada no "
								+ moduloPeriodoPsicossocialParaAsFamilias);
				repositorioModuloPeriodo.salvar(moduloPeriodoPsicossocialParaAsFamilias);
			}
		}
	}

	private ModuloPeriodo obterModuloPsicossocialParaAsFamilias(List<ModuloPeriodo> modulosPeriodos) {
		for (ModuloPeriodo moduloPeriodo : modulosPeriodos) {
			if (moduloPeriodo.getModulo().ePsicossocialParaAsFamilias()) {
				return moduloPeriodo;
			}
		}
		return null;
	}
}

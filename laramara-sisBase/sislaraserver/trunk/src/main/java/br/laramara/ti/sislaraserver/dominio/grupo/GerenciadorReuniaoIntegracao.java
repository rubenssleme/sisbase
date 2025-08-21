package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.PendenciaInclusaoReuniaoDeIntegracao;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;

@Component
public class GerenciadorReuniaoIntegracao {

	private final Logger logger = Logger.getLogger(GerenciadorReuniaoIntegracao.class);
	
	@Inject
	private RepositorioSislara repositorioSislara;
	@Inject
	private RepositorioGrupo repositorioGrupo;
	@Inject
	private RepositorioPendencia repositorioPendencia;

	public ModuloPeriodo obterModuloPeriodoReuniaoIntegracaoComDataFuturaEPeriodoCompativel(
			ModuloPeriodo moduloPeriodo) {
		return repositorioGrupo
				.obterModuloPeriodoServicoSocialReuniaoIntegracaoAtivosComDataFuturaEPeriodoCompativel(moduloPeriodo)
				.stream().findFirst().orElse(null);
	}

	public void incluirEmReuniaoDeIntegracaoSeNecessario(ModuloPeriodo moduloPeriodoSolicitante) {
		Grupo grupoSolicitante = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodoSolicitante.getId());
		if (grupoSolicitante.podeIncluirUsuariosEmReuniaoDeIntegracao()
				&& !moduloPeriodoSolicitante.ePossuiModuloDeReuniaoComInstituicaoDeEnsino()) {
			List<Usuario> usuariosSujeitosARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticiparamDaReuniaoIntegracao = obterUsuariosComStatusIntegradoOuProvisorioOuAcessoSujeitosARegraDaReuniaoIntegracao(
					moduloPeriodoSolicitante);
			
			List<Usuario> usuariosJaIntegradosEmOutrosGruposAtivos = obterUsuariosIntegradosEmGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao();
			
			usuariosSujeitosARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticiparamDaReuniaoIntegracao.removeAll(usuariosJaIntegradosEmOutrosGruposAtivos);
			if (!usuariosSujeitosARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticiparamDaReuniaoIntegracao
							.isEmpty()) {
				for (Usuario usuarioSujeitoARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticipouDaReuniaoIntegracao : usuariosSujeitosARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticiparamDaReuniaoIntegracao) {
					if (!repositorioPendencia.possuiPendenciaDeReuniaoDeIntegracaoNaoResolvidaPorUsuario(
							usuarioSujeitoARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticipouDaReuniaoIntegracao)
							&& !repositorioPendencia.jaExistiuPendenciaDeReunicaoDeIntegracao(moduloPeriodoSolicitante,
									usuarioSujeitoARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticipouDaReuniaoIntegracao)) {
						Pendencia pendencia = new PendenciaInclusaoReuniaoDeIntegracao(
								usuarioSujeitoARegraDeReuniaoDeIntegracaoComStatusIntegradoOuProvisorioOuAcessoQueNuncaParticipouDaReuniaoIntegracao
										.getId(),
								moduloPeriodoSolicitante,
								Profissional.obterProfissionaisReponsaveisPeloServicoSocial());
						repositorioPendencia.salvar(pendencia);
						logger.warn("Pendência de inclusão de prontuário em reunião de integração inseriada com sucesso.");
					}
				}
			}
		}
	}
	
	private List<Usuario> obterUsuariosIntegradosEmGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao() {
		List<Grupo> gruposDescricaoServicoSocialComModuloDeReuniaoIntegracaoAtivos = repositorioGrupo
				.obterGruposDescricaoServicoSocialModuloReuniaoDeIntegracaoAtivos();

		return gruposDescricaoServicoSocialComModuloDeReuniaoIntegracaoAtivos.stream()
				.flatMap(grupo -> grupo.obterTodosUsuariosIntegrados().stream()).collect(Collectors.toList());
	}

	private List<Usuario> obterUsuariosComStatusIntegradoOuProvisorioOuAcessoSujeitosARegraDaReuniaoIntegracao(
			ModuloPeriodo moduloPeriodo) {
		return moduloPeriodo.obterUsuariosComStatusIntegradoOuProvisorioOuAcessoSujeitosARegraDaReuniaoDeIntegracao().stream()
				.filter(usuarioComStatusProvisorioOuAcesso -> repositorioSislara
						.nuncaParticipouReuniaoIntegracao(usuarioComStatusProvisorioOuAcesso))
				.collect(Collectors.toList());
	}

	public List<Usuario> obterUsuariosIntegradosEmGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao(
			ModuloPeriodo moduloPeriodoSolicitante) {
		List<Usuario> usuariosIntegradosComGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao = new ArrayList<>();
		if (moduloPeriodoSolicitante.ePossuiModuloDeReuniaoIntegracao()) {
		
			List<Usuario> usuariosIntegrados = moduloPeriodoSolicitante.obterUsuariosIntegradosPorModuloPeriodo();

			for (Usuario usuarioIntegrado : usuariosIntegrados) {
				if (existeUsuarioIntegradoEmGrupoAtivoDeReunicaoDeIntegracaoDistintoDoSolicitante(moduloPeriodoSolicitante,
						usuarioIntegrado)) {
					usuariosIntegradosComGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao
							.add(usuarioIntegrado);
				}
			}
		}
		return usuariosIntegradosComGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao;
	}

	public boolean existeUsuarioIntegradoEmGrupoAtivoDeReunicaoDeIntegracaoDistintoDoSolicitante(
			ModuloPeriodo moduloPeriodoSolicitante, Usuario usuarioIntegrado) {
		return repositorioGrupo.obterGruposDescricaoServicoSocialModuloReuniaoDeIntegracaoAtivos().stream()
				.anyMatch(
						grupoDescricaoServicoSocialComModuloReuniaoIntegracaoAtivo -> grupoDescricaoServicoSocialComModuloReuniaoIntegracaoAtivo
								.existeUsuarioIntegradoEmModuloDeReunicaoDeIntegracaoEmModuloPeriodoDistinto(usuarioIntegrado,
										moduloPeriodoSolicitante));
	}
}

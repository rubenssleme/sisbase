package br.laramara.ti.sislaraserver.fachadas;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.pendencia.ResultadoListagemPendenciaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaraserver.dominio.pendencia.AutomatizadorPendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;

@Component
public class FachadaPendencia extends Fachada {

	@Inject
	private RepositorioPendencia repositorioPendencia;
	@Inject
	private AutomatizadorPendencia automatizadorPendencia;
	
	public ResultadoListagemPendenciaDTO obterListagemPendencia(TokenDTO tokenDto) {
		ContaAcesso contaAcessoSolicitante = obterContaAcesso(tokenDto);
		automatizadorPendencia.atualizarPendenciasAtendimentoGrupo(contaAcessoSolicitante.getProfissional());
		automatizadorPendencia.atualizarPendenciasAtendimentoIndividual(contaAcessoSolicitante.getProfissional());
		List<Pendencia> todasAsPendenciasComDataPassadaOuNula = repositorioPendencia.obterPendenciasComDataPassadaOuNulaPor(contaAcessoSolicitante.getProfissional());
		List<Pendencia> pendenciasDeExcessoDeFaltasOuAtendimentoIndividualOuGrupoPassados = todasAsPendenciasComDataPassadaOuNula
				.stream()
				.filter(pendencia -> pendencia.eExcessoDeFaltas() || pendencia.eReuniaoDeIntegracao()
						|| ((pendencia.eAtendimentoIndividualUsuario() || pendencia.eAtendimentoIndividualPreCadastro()
								|| pendencia.eAtendimentoDeGrupo()) && pendencia.posterior()))
				.collect(Collectors.toList());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = new ResultadoListagemPendenciaDTO();
		resultadoListagemPendenciaDTO.efetuadoComSucesso(
				new FabricaPendencia().converterParaDTO(pendenciasDeExcessoDeFaltasOuAtendimentoIndividualOuGrupoPassados));
		return resultadoListagemPendenciaDTO;
	}
}

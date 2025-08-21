package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.espera.ResultadoInclusaoListaEsperaAvaliacaoFuncional;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;
import br.laramara.ti.sislaraserver.dominio.grupo.GerenciadorGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.ResultadoEdicaoModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAcaoConduta;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;

@Component
public class AutomatizadorAcaoConduta {

	private final Logger logger = Logger.getLogger(AutomatizadorAcaoConduta.class);

	@Inject
	private RepositorioAcaoConduta repositorioAcaoConduta;
	@Inject
	private RepositorioGrupo repositorioGrupo;
	@Inject
	private AutomatizadorEspera automatizadorEspera;
	@Inject 
	private GerenciadorGrupo gerenciadorGrupo;

	public void processarAcoesCondutas() {
		processarCondutas(TipoAcaoConduta.INTEGRAR, integrarAcaoConduta());
		processarCondutas(TipoAcaoConduta.RETORNAR, retornarAcaoConduta());
		processarCondutas(TipoAcaoConduta.NAO_E_CASO_PARA_LM, marcarAcaoCondutaComoNaoECasoLM());
	}

	private Consumer<AcaoConduta> integrarAcaoConduta() {
		return (acaoCondutaAProcessar) -> {
			Grupo grupo = repositorioGrupo.obterPorId(acaoCondutaAProcessar.getGrupo().getId());
			ModuloPeriodo moduloPeriodoAEE = grupo.obterModuloPeriodoAEE();
			moduloPeriodoAEE.provisionarUsuario(acaoCondutaAProcessar.getUsuario(),
					"Inserido automaticamente pela conduta de integração.", false);
			ResultadoEdicaoModuloPeriodo resultadoEdicaoModuloPeriodo = gerenciadorGrupo
					.alterarModuloPeriodo(ContaAcesso.obterAcessoRoot(), moduloPeriodoAEE);
			if (resultadoEdicaoModuloPeriodo.sucesso()) {
				acaoCondutaAProcessar.marcarComoProcessado(resultadoEdicaoModuloPeriodo.getMensagem());
			}
		};
	}

	private Consumer<AcaoConduta> marcarAcaoCondutaComoNaoECasoLM() {
		return (acaoCondutaAProcessar) -> {
			acaoCondutaAProcessar.marcarComoProcessado("Processado automaticamente pelo sistema.");
		};
	}

	private Consumer<AcaoConduta> retornarAcaoConduta() {
		return (acaoCondutaAProcessar) -> {
			Espera espera = new Espera();
			espera.setDataExpectativa(acaoCondutaAProcessar.getDataExpectativa());
			ResultadoInclusaoListaEsperaAvaliacaoFuncional resultadoInclusaoListaEsperaAvaliacaoFuncional = automatizadorEspera
					.inserirNaListaDeEsperaDeAvaliacaoFuncional(espera, acaoCondutaAProcessar.getUsuario(), 
							acaoCondutaAProcessar.getObs() + " Incluído automaticamente pela conduta do retorno. ",
							acaoCondutaAProcessar.getSetor(), TipoEspera.RET);
			acaoCondutaAProcessar.marcarComoProcessado(resultadoInclusaoListaEsperaAvaliacaoFuncional.getMensagem());
		};
	}

	private void processarCondutas(TipoAcaoConduta tipoAcaoConduta,
			Consumer<AcaoConduta> operacaoProcessamentoDeAcaoConduta) {
		logger.info("Processamento de condutas de " + tipoAcaoConduta.toString() + " iniciado.");
		List<AcaoConduta> acoesCondutasAProcessar = repositorioAcaoConduta.obterAcaoCondutasNaoProcessadas().stream()
				.filter(acaoConduta -> acaoConduta.getTipoAcaoConduta().equals(tipoAcaoConduta)
						&& !acaoConduta.cancelada())
				.collect(Collectors.toList());
		for (AcaoConduta acaoCondutaAProcessar : acoesCondutasAProcessar) {
			operacaoProcessamentoDeAcaoConduta.accept(acaoCondutaAProcessar);
			repositorioAcaoConduta.salvar(acaoCondutaAProcessar);
		}
		logger.info("Processamento de condutas de " + tipoAcaoConduta.toString() + " finalizado.");
	}
}

package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDemanda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProjeto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecurso;

@Component
public class AutomatizadorDoacao {

	private final Logger logger = Logger.getLogger(AutomatizadorDoacao.class);

	@Inject
	private RepositorioDemanda repositorioDemanda;
	@Inject
	private RepositorioProjeto repositorioProjeto;
	@Inject
	private RepositorioRecurso repositorioRecurso;
	
	public void atualizarDoacoes() {
		//reservarCartelaDeSelos();
		//reservarBengalaParaUsuarioInternoNaoCartelaDeSelos();
	}

	private void reservarCartelaDeSelos() {
		logger.info("Atualização de cartela de selos iniciada.");
		List<Demanda> demandasCarteDeSelosAguardandoOuProrrogada = obterDemandasDeCartelaDeSelosAguardandoOuProrrogada();
		for (Demanda demandaCarteDeSelosAguardandoOuProrrogada : demandasCarteDeSelosAguardandoOuProrrogada) {
			if (demandaCarteDeSelosAguardandoOuProrrogada.saldoZerado()) {
				logger.info("Reversando recurso da cartela de selos " + demandaCarteDeSelosAguardandoOuProrrogada);
				demandaCarteDeSelosAguardandoOuProrrogada.marcarComStatusReservado();
				demandaCarteDeSelosAguardandoOuProrrogada.setContaAcessoResponsavelPorOperacao(ContaAcesso.obterAcessoRoot());
				repositorioDemanda.salvar(demandaCarteDeSelosAguardandoOuProrrogada);
				logger.info("Reversa de cartela de selos efetuada com sucesso.");
			}
		}
		logger.info("Atualização de cartela de selos finalizada.");
	}

	private List<Demanda> obterDemandasDeCartelaDeSelosAguardandoOuProrrogada() {
		EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
		especificacaoPesquisaDemanda.setStatusDemanda(StatusDemanda.AGUARDANDO);
		List<Demanda> demandasCarteDeSelosAguardandoOuProrrogada = repositorioDemanda.obterPor(especificacaoPesquisaDemanda);
		especificacaoPesquisaDemanda.setStatusDemanda(StatusDemanda.PRORROGADA);
		demandasCarteDeSelosAguardandoOuProrrogada.addAll(repositorioDemanda.obterPor(especificacaoPesquisaDemanda));
		return demandasCarteDeSelosAguardandoOuProrrogada.stream().filter(demanda -> demanda.isCarteloDeSelos())
				.collect(Collectors.toList());
	}

	//TODO: REMOVER
	
	private void reservarBengalaParaUsuarioInternoNaoCartelaDeSelos() {
		logger.info("Reserva de demanda não cartela de selos iniciada.");
		List<Projeto> projetosAtivos = repositorioProjeto.obterAtivos();
		for (Projeto projetoAtivo : projetosAtivos) {
			for (Recurso recurso : projetoAtivo.obterRecursosDisponiveisParaDoacao()) {
				EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
				especificacaoPesquisaDemanda.setStatusDemanda(StatusDemanda.AGUARDANDO);
				especificacaoPesquisaDemanda.setRecurso(recurso);
				List<Demanda> demandas = repositorioDemanda
						.obterPor(especificacaoPesquisaDemanda).stream()
						.filter(demanda -> !demanda.isCarteloDeSelos() && demanda.estaAguardando()
								&& demanda.possuiUsuairo() && demanda.possuiUsuarioComIdadeIgualOuSuperiorA4Anos()
								&& demanda.eBengala())
						.collect(Collectors.toList());
				
				List<Demanda> demandasPrioritarias = demandas.stream()
						.filter(demanda -> demanda.possuiUsuarioComIdadeIgualOuSuperiorA16Anos())
						.collect(Collectors.toList());
				Collections.sort(demandasPrioritarias, Demanda.obterComparadorId());
				reservarValorParaRecurso(demandasPrioritarias, projetoAtivo);
			
				Collections.sort(demandas, Demanda.obterComparadorId());
				reservarValorParaRecurso(demandas, projetoAtivo);
			}
		}
		logger.info("Reserva de demanda não cartela de selos finalizada.");
	}
	
	private void reservarValorParaRecurso(List<Demanda> demandasNaoCarteDeSelosAguardandoPrioritarias, Projeto projeto) {
		for (Demanda demandaNaoCartelaDeSelosAguardando : demandasNaoCarteDeSelosAguardandoPrioritarias) {
			Projeto projetoAVerificar = repositorioProjeto.obterPorId(projeto.getId());
			Demanda demandaAVerificar = repositorioDemanda.obterPorId(demandaNaoCartelaDeSelosAguardando.getId());
			
			if (projetoAVerificar.possuiRecursoDisponivel(demandaAVerificar) && demandaAVerificar.estaAguardando()) {
				logger.info("Reversando recurso da " + demandaAVerificar + " no "
						+ projetoAVerificar);
				projetoAVerificar.reservarParaDemanda(demandaAVerificar);
				repositorioProjeto.salvar(projetoAVerificar);
				demandaAVerificar.marcarComStatusReservadoEAtualizarValor();
				demandaAVerificar
						.setContaAcessoResponsavelPorOperacao(ContaAcesso.obterAcessoRoot());
				repositorioDemanda.salvar(demandaAVerificar);
				logger.info("Reversa efetuada com sucesso.");
			}
			
			/*
			Recurso recursoAReservar = repositorioRecurso.obterPorId(recurso.getId());
			Projeto projetoAVerificar = repositorioProjeto.obterPorId(projetoAtivo.getId());
			
			if (DinheiroUtils.primeiroValorMaiorOuIgualQueSegundoValor(
					projetoAVerificar.saldoDisponiveParaRecurso(recurso), recursoAReservar.getValor())) {
				reservarValorParaRecurso(demandaNaoCartelaDeSelosAguardando, projetoAVerificar);
			}
			*/
		}
	}
}

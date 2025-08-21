package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.utilitarios.DinheiroUtils;
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
		reservarCartelaDeSelos();
		reservarNaoCartelaDeSelos();
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

	private void reservarNaoCartelaDeSelos() {
		logger.info("Reserva de demanda não cartela de selos iniciada.");
		List<Projeto> projetosAtivos = repositorioProjeto.obterAtivos();
		for (Projeto projetoAtivoComSaldo : projetosAtivos) {
			for (Recurso recurso : projetoAtivoComSaldo.getRecursosComSaldo()) {
				EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
				especificacaoPesquisaDemanda.setStatusDemanda(StatusDemanda.AGUARDANDO);
				especificacaoPesquisaDemanda.setRecurso(recurso);
				List<Demanda> demandasNaoCarteDeSelosAguardando = repositorioDemanda
						.obterPor(especificacaoPesquisaDemanda).stream().filter(demanda -> !demanda.isCarteloDeSelos())
						.collect(Collectors.toList());
				Collections.sort(demandasNaoCarteDeSelosAguardando, Demanda.obterComparador());
				for (Demanda demandaNaoCartelaDeSelosAguardando : demandasNaoCarteDeSelosAguardando) {
					Recurso recursoAReservar = repositorioRecurso.obterPorId(recurso.getId());
					Projeto projetoAVerificar = repositorioProjeto.obterPorId(projetoAtivoComSaldo.getId());
					if (DinheiroUtils.primeiroValorMaiorOuIgualQueSegundoValor(
							projetoAVerificar.saldoDisponiveParaRecurso(recurso), recursoAReservar.getValor())) {
						reservarValorParaRecurso(demandaNaoCartelaDeSelosAguardando, projetoAVerificar);
					}
				}
			}
		}
		logger.info("Reserva de demanda não cartela de selos finalizada.");
	}

	private void reservarValorParaRecurso(Demanda demandaNaoCartelaDeSelosAguardando, Projeto projetoAVerificar) {
		logger.info("Reversando recurso da " + demandaNaoCartelaDeSelosAguardando + " no "
				+ projetoAVerificar);
		projetoAVerificar.reservarParaDemanda(demandaNaoCartelaDeSelosAguardando);
		repositorioProjeto.salvar(projetoAVerificar);
		demandaNaoCartelaDeSelosAguardando.marcarComStatusReservadoEAtualizarValor();
		demandaNaoCartelaDeSelosAguardando
				.setContaAcessoResponsavelPorOperacao(ContaAcesso.obterAcessoRoot());
		repositorioDemanda.salvar(demandaNaoCartelaDeSelosAguardando);
		logger.info("Reversa efetuada com sucesso.");
	}
}

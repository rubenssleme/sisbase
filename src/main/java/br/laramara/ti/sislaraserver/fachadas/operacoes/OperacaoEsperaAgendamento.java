package br.laramara.ti.sislaraserver.fachadas.operacoes;

import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;

public class OperacaoEsperaAgendamento extends OperacaoEspera {

	public OperacaoEsperaAgendamento(FabricaEspera fabricaEspera,
			RepositorioEspera repositorioEspera, EsperaDTO esperaDto, boolean obsAutomaticaDeAssociacao) {
		super(fabricaEspera, repositorioEspera, esperaDto, obsAutomaticaDeAssociacao);
	}

	@Override
	protected void executar(Espera espera, ContaAcesso contaAcesso) {
		espera.agendado(contaAcesso);
	}

	@Override
	protected String descricao() {
		return "Agendamento";
	}
}

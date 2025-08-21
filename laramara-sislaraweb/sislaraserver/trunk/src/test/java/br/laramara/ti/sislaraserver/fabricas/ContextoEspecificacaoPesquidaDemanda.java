package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;
import br.laramara.ti.sislaraserver.dominio.doacao.StatusDemanda;

public class ContextoEspecificacaoPesquidaDemanda {

	public static EspecificacaoPesquisaDemanda fabricarDominioComTodosOsDados() {
		EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
		especificacaoPesquisaDemanda.setPreCadastro(ContextoPreCadastro.fabricarPreCadastroComTodosOsDados());
		especificacaoPesquisaDemanda.setRecurso(ContextoRecurso.fabricarRecursoAlternativoComTodosOsDados());
		especificacaoPesquisaDemanda.setStatusDemanda(StatusDemanda.AGUARDANDO);
		return especificacaoPesquisaDemanda;
	}

	public static EspecificacaoPesquisaDemandaDTO fabricarDtoComTodosOsDados() {
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		especificacaoPesquisaDemandaDTO.setProntuario("12222");
		especificacaoPesquisaDemandaDTO.setCpf("71894810287");
		especificacaoPesquisaDemandaDTO.setRecursoDto(ContextoRecurso.construirRecursoDTO());
		especificacaoPesquisaDemandaDTO.setStatusDemandaDto(new StatusDemandaDTO("AGUARDANDO"));
		return especificacaoPesquisaDemandaDTO;
	}
}

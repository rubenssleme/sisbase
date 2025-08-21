package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.EspecificacaoPesquisaDemanda;

public class FabricaEspecificacaoPesquisaDemanda
		extends
		FabricaRecursiva<EspecificacaoPesquisaDemandaDTO, EspecificacaoPesquisaDemanda> {

	@Override
	public EspecificacaoPesquisaDemandaDTO converterParaDTO(
			EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda) {
		return null;
	}

	@Override
	public EspecificacaoPesquisaDemanda converterParaDominio(
			EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDto,
			EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda) {
		if (especificacaoPesquisaDemandaDto != null) {
			if (especificacaoPesquisaDemanda == null) {
				especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
			}
			especificacaoPesquisaDemanda.setRecurso(new FabricaRecurso()
					.converterParaDominio(especificacaoPesquisaDemandaDto
							.getRecurso()));
			especificacaoPesquisaDemanda
					.setProntuario(especificacaoPesquisaDemandaDto
							.getProntuario());
			especificacaoPesquisaDemanda.setStatusDemanda(new FabricaStatusDemanda()
					.converterParaDominio(especificacaoPesquisaDemandaDto
							.getStatusDemandaDTO()));
			especificacaoPesquisaDemanda
					.setPreCadastro(new FabricaPreCadastro()
							.converterParaDominio(especificacaoPesquisaDemandaDto
									.getPreCadastro()));
			especificacaoPesquisaDemanda.setCpf(especificacaoPesquisaDemandaDto.getCpf());
		}
		return especificacaoPesquisaDemanda;
	}

	@Override
	public EspecificacaoPesquisaDemanda obterNovo() {
		return new EspecificacaoPesquisaDemanda();
	}
}

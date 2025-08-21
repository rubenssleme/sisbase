package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;

public class FabricaEspecificacaoPesquisaAtendimentoIndividual
		extends
		FabricaBase<EspecificacaoPesquisaAtendimentoIndividualDTO, EspecificacaoPesquisaAtendimentoIndividual> {

	@Override
	public EspecificacaoPesquisaAtendimentoIndividualDTO converterParaDTO(
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual) {
		return null;
	}

	@Override
	public EspecificacaoPesquisaAtendimentoIndividual converterParaDominio(
			EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDto) {
		EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisa = new EspecificacaoPesquisaAtendimentoIndividual();
		especificacaoPesquisa
				.setProfissional(new FabricaProfissional()
						.converterParaDominio(especificacaoPesquisaAtendimentoIndividualDto
								.obterProfissionalDto()));
		especificacaoPesquisa
				.setDescricaoTipoAtendimento(new FabricaDescricaoTipoAtendimento()
						.converterParaDominio(especificacaoPesquisaAtendimentoIndividualDto
								.obterDescricaoTipoAtendimentoDto()));
		especificacaoPesquisa
				.setModulo(new FabricaModulo()
						.converterParaDominio(especificacaoPesquisaAtendimentoIndividualDto
								.obterModuloDto()));
		especificacaoPesquisa
				.setDataInicio(especificacaoPesquisaAtendimentoIndividualDto
						.getDataInicio());
		especificacaoPesquisa
				.setDataTermino(especificacaoPesquisaAtendimentoIndividualDto
						.getDataTermino());
		especificacaoPesquisa.setProntuario(especificacaoPesquisaAtendimentoIndividualDto.getProntuario());
		return especificacaoPesquisa;
	}
}

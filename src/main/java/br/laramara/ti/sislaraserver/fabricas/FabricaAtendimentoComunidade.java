package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoComunidade;

public class FabricaAtendimentoComunidade extends
		FabricaBase<AtendimentoComunidadeDTO, AtendimentoComunidade> {

	public FabricaAtendimentoComunidade() {
	}

	@Override
	public AtendimentoComunidadeDTO converterParaDTO(
			AtendimentoComunidade atendimentoComunidade) {

		AtendimentoComunidadeDTO atendimentoComunidadeDto = null;
		if (atendimentoComunidade != null) {
			atendimentoComunidadeDto = new AtendimentoComunidadeDTO();
			atendimentoComunidadeDto.setId(atendimentoComunidade.getId());
			atendimentoComunidadeDto.setPreCadastroDto(new FabricaPreCadastro()
					.converterParaDTO(atendimentoComunidade.getPreCadastro()));
			atendimentoComunidadeDto.setTipoVinculoDto(new FabricaTipoVinculo()
					.converterParaDTO(atendimentoComunidade.getTipoVinculo()));
			atendimentoComunidadeDto
					.setInformacaoAtendimentoDto(new FabricaInformacaoAtendimento()
							.converterParaDTO(atendimentoComunidade
									.getInformacaoAtendimento()));
		}
		return atendimentoComunidadeDto;
	}

	@Override
	public AtendimentoComunidade converterParaDominio(
			AtendimentoComunidadeDTO atendimentoComunidadeDto) {

		AtendimentoComunidade atendimentoComunidade = null;
		if (atendimentoComunidadeDto != null) {
			atendimentoComunidade = new AtendimentoComunidade();
			atendimentoComunidade.setId(atendimentoComunidadeDto.getId());
			atendimentoComunidade.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(atendimentoComunidadeDto
							.getPreCadastroDto()));
			atendimentoComunidade.setTipoVinculo(new FabricaTipoVinculo()
					.converterParaDominio(atendimentoComunidadeDto
							.getTipoVinculoDto()));
			atendimentoComunidade
					.setInformacaoAtendimento(new FabricaInformacaoAtendimento()
							.converterParaDominio(atendimentoComunidadeDto
									.getInformacaoAtendimentoDto()));
		}
		return atendimentoComunidade;
	}
}

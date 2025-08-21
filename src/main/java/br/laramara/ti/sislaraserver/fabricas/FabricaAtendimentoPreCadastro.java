package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoPreCadastro;

public class FabricaAtendimentoPreCadastro extends
		FabricaRecursiva<AtendimentoPreCadastroDTO, AtendimentoPreCadastro> {

	@Override
	public AtendimentoPreCadastroDTO converterParaDTO(
			AtendimentoPreCadastro atendimentoPreCadastro) {

		AtendimentoPreCadastroDTO atendimentoPreCadastroDto = null;
		if (atendimentoPreCadastro != null) {
			atendimentoPreCadastroDto = new AtendimentoPreCadastroDTO();
			atendimentoPreCadastroDto.setId(atendimentoPreCadastro.getId());
			atendimentoPreCadastroDto
					.setPreCadastroDto(new FabricaPreCadastro()
							.converterParaDTO(atendimentoPreCadastro
									.getPreCadastro()));
			atendimentoPreCadastroDto
					.setInformacaoAtendimentoDto(new FabricaInformacaoAtendimento()
							.converterParaDTO(atendimentoPreCadastro
									.getInformacaoAtendimento()));
			atendimentoPreCadastroDto
					.setInstituicaoDto(new FabricaInstituicao()
							.converterParaDTO(atendimentoPreCadastro
									.getInstituicao()));
			atendimentoPreCadastroDto.setDreCefaiDto(new FabricaDreCefai()
					.converterParaDTO(atendimentoPreCadastro.getDreCefai()));
			atendimentoPreCadastroDto
					.setDiretoriaEnsinoDto(new FabricaDiretoriaEnsino()
							.converterParaDTO(atendimentoPreCadastro
									.getDiretoriaEnsino()));
			atendimentoPreCadastroDto.setNomeOrigem(atendimentoPreCadastro
					.getNomeOrigem());
			atendimentoPreCadastroDto
					.setTipoVinculoDto(new FabricaTipoVinculo()
							.converterParaDTO(atendimentoPreCadastro
									.getTipoVinculo()));
		}
		return atendimentoPreCadastroDto;
	}

	@Override
	public AtendimentoPreCadastro converterParaDominio(
			AtendimentoPreCadastroDTO atendimentoPreCadastroDto,
			AtendimentoPreCadastro atendimentoPreCadastro) {
		if (atendimentoPreCadastroDto != null) {
			if (atendimentoPreCadastro == null) {
				atendimentoPreCadastro = new AtendimentoPreCadastro();
			}
			atendimentoPreCadastro.setId(atendimentoPreCadastroDto.getId());
			atendimentoPreCadastro.setPreCadastro(new FabricaPreCadastro()
					.converterParaDominio(
							atendimentoPreCadastroDto.getPreCadastroDto(),
							atendimentoPreCadastro.getPreCadastro()));
			atendimentoPreCadastro
					.setInformacaoAtendimento(new FabricaInformacaoAtendimento()
							.converterParaDominio(atendimentoPreCadastroDto
									.getInformacaoAtendimentoDto()));
		}
		return atendimentoPreCadastro;
	}

	@Override
	public AtendimentoPreCadastro obterNovo() {
		return new AtendimentoPreCadastro();
	}
}

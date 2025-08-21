package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

public class FabricaPreCadastro extends FabricaRecursiva<PreCadastroDTO, PreCadastro> {

	@Override
	public PreCadastro converterParaDominio(PreCadastroDTO preCadastroDto,
			PreCadastro preCadastro) {
		if (preCadastroDto != null) {
			if (preCadastro == null) {
				preCadastro = new PreCadastro();
			}
			preCadastro.setId(preCadastroDto.getId());
			preCadastro.setInformacaoEssencial(new FabricaInformacaoEssencial()
					.converterParaDominio(
							preCadastroDto.getInformacaoEssencialDto(),
							preCadastro.getInformacaoEssencial()));
			preCadastro.setObs(preCadastroDto.getObs());
		}
		return preCadastro;
	}

	public final PreCadastroDTO converterParaDTO(
			PreCadastro preCadastro) {
		PreCadastroDTO preCadastroDto = null;
		if (preCadastro != null) {
			preCadastroDto = new PreCadastroDTO();
			if (preCadastro.getId() != null) {
				preCadastroDto.setId(preCadastro.getId());
			}
			preCadastroDto.setDataCadastro(preCadastro.getDataCadastro());
			preCadastroDto.setInformacaoEssencialDto(new FabricaInformacaoEssencial()
					.converterParaDTO(preCadastro.getInformacaoEssencial()));
			preCadastroDto.setObs(preCadastro.getObs());
		}
		return preCadastroDto;
	}

	@Override
	public PreCadastro obterNovo() {
		return new PreCadastro();
	}
}

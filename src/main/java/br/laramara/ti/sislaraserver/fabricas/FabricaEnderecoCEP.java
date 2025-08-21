package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoCEPDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;

public class FabricaEnderecoCEP extends
		FabricaBase<EnderecoCEPDTO, EnderecoCEP> {

	@Override
	public EnderecoCEP converterParaDominio(EnderecoCEPDTO objetoDto) {
		return null;
	}

	@Override
	public EnderecoCEPDTO converterParaDTO(EnderecoCEP objetoDominio) {
		EnderecoCEPDTO enderecoCepDto = new EnderecoCEPDTO();
		enderecoCepDto.setEndereco(objetoDominio.getEndereco());
		enderecoCepDto.setBairro(objetoDominio.getBairro());
		enderecoCepDto.setMunicipioDto(new FabricaMunicipio()
				.converterParaDTO(objetoDominio.getMunicipio()));
		enderecoCepDto.setUfDto(new FabricaUf().converterParaDTO(objetoDominio
				.getUf()));
		enderecoCepDto.setPaisDto(new FabricaPais().converterParaDTO(objetoDominio
				.getPais()));
		return enderecoCepDto;
	}
}

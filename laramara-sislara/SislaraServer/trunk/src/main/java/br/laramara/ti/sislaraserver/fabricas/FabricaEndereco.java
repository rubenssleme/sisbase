package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;

public class FabricaEndereco extends FabricaBase<EnderecoDTO, Endereco> {

	public final Endereco converterParaDominio(EnderecoDTO enderecoDto) {

		Endereco endereco = null;
		if (enderecoDto != null) {
			endereco = new Endereco();
			if (enderecoDto.getId() != null) {
				endereco.setId(new Long(enderecoDto.getId()));
			}
			endereco.setCep(enderecoDto.getCep());
			endereco.setEndereco(enderecoDto.getEndereco());
			endereco.setNumero(enderecoDto.getNumero());
			endereco.setComplemento(enderecoDto.getComplemento());
			endereco.setZona(enderecoDto.getZonaDto() != null ? new FabricaZona()
					.converterParaDominio(enderecoDto.getZonaDto()) : null);
			endereco.setBairro(enderecoDto.getBairro());
			endereco.setMunicipio(new FabricaMunicipio()
					.converterParaDominio(enderecoDto.getMunicipioDto()));
			endereco.setUf(new FabricaUf().converterParaDominio(enderecoDto
					.getUfDto()));
			endereco.setPais(new FabricaPais().converterParaDominio(enderecoDto
					.getPaisDto()));
		}
		return endereco;
	}

	public final EnderecoDTO converterParaDTO(Endereco endereco) {
		EnderecoDTO enderecoDto = new EnderecoDTO();
		if (endereco.getId() != null) {
			enderecoDto.setId(endereco.getId());
		}
		enderecoDto.setCep(endereco.getCep());
		enderecoDto.setEndereco(endereco.getEndereco());
		enderecoDto.setNumero(endereco.getNumero());
		enderecoDto.setComplemento(endereco.getComplemento());
		enderecoDto.setZonaDto(new FabricaZona().converterParaDTO(endereco
				.getZona()));
		enderecoDto.setBairro(endereco.getBairro());
		enderecoDto.setMunicipioDto(new FabricaMunicipio()
				.converterParaDTO(endereco.getMunicipio()));
		enderecoDto
				.setUfDto(new FabricaUf().converterParaDTO(endereco.getUf()));
		enderecoDto.setPaisDto(new FabricaPais().converterParaDTO(endereco
				.getPais()));

		return enderecoDto;
	}
}

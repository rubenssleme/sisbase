package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEnderecoCEPDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void enderecocepdto_foi_construida_com_sucesso() {
		String endereco = "Rua barra Funda";
		String bairro = "Barra Funda";
		MunicipioDTO municipio = new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO("SP"));
		UfDTO uf = new UfDTO("SP");
		PaisDTO pais = new PaisDTO(new Long(1), "Brasil");

		EnderecoCEPDTO enderecoDto = new EnderecoCEPDTO();
		enderecoDto.setEndereco(endereco);
		enderecoDto.setBairro(bairro);
		enderecoDto.setMunicipioDto(municipio);
		enderecoDto.setUfDto(uf);
		enderecoDto.setPaisDto(pais);

		Assert.assertEquals(enderecoDto.getEndereco(), endereco);
		Assert.assertEquals(enderecoDto.getBairro(), bairro);
		Assert.assertEquals(enderecoDto.getMunicipioDto(), municipio);
		Assert.assertEquals(enderecoDto.getUfDto().toString(), uf.toString());
		Assert.assertEquals(enderecoDto.getPaisDto().toString(),
				pais.toString());
	}
}

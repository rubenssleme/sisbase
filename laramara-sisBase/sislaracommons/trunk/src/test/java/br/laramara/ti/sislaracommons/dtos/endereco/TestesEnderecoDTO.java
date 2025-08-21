package br.laramara.ti.sislaracommons.dtos.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEnderecoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void enderecodto_foi_construida_com_sucesso() {
		Long id = new Long(23);
		String cep = "01151000";
		String endereco = "Rua barra Funda";
		String numero = "4343";
		String complemento = "AP43";
		ZonaDTO zona = new ZonaDTO("NORTE");
		String bairro = "Barra Funda";
		MunicipioDTO municipio = new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO("SP"));
		UfDTO uf = new UfDTO("SP");
		PaisDTO pais = new PaisDTO(new Long(1), "Brasil");

		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setId(id);
		enderecoDto.setCep(cep);
		enderecoDto.setEndereco(endereco);
		enderecoDto.setNumero(numero);
		enderecoDto.setComplemento(complemento);
		enderecoDto.setZonaDto(zona);
		enderecoDto.setBairro(bairro);
		enderecoDto.setMunicipioDto(municipio);
		enderecoDto.setUfDto(uf);
		enderecoDto.setPaisDto(pais);

		Assert.assertEquals(enderecoDto.getId(), id);
		Assert.assertEquals(enderecoDto.getCep(), cep);
		Assert.assertEquals(enderecoDto.getEndereco(), endereco);
		Assert.assertEquals(enderecoDto.getNumero(), numero);
		Assert.assertEquals(enderecoDto.getComplemento(), complemento);
		Assert.assertEquals(enderecoDto.getZonaDto().toString(),
				zona.toString());
		Assert.assertEquals(enderecoDto.getBairro(), bairro);
		Assert.assertEquals(enderecoDto.getMunicipioDto(), municipio);
		Assert.assertEquals(enderecoDto.getUfDto().toString(), uf.toString());
		Assert.assertEquals(enderecoDto.getPaisDto().toString(),
				pais.toString());
	}
}

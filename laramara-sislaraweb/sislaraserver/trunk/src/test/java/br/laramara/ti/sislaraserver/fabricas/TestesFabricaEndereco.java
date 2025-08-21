package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;

public class TestesFabricaEndereco {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_endereco_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(34324);
		EnderecoDTO enderecoDto = ContextoEndereco.construirEnderecoDTO();
		enderecoDto.setId(id);
		Endereco enderecoConvertido = new FabricaEndereco()
				.converterParaDominio(enderecoDto);

		Assert.assertEquals(enderecoConvertido.getId().toString(), enderecoDto
				.getId().toString());
		Assert.assertEquals(enderecoConvertido.getCep(), enderecoDto.getCep());
		Assert.assertEquals(enderecoConvertido.getEndereco(),
				enderecoDto.getEndereco());
		Assert.assertEquals(enderecoConvertido.getNumero(),
				enderecoDto.getNumero());
		Assert.assertEquals(enderecoConvertido.getComplemento(),
				enderecoDto.getComplemento());
		Assert.assertEquals(enderecoConvertido.getZona().toString(),
				enderecoDto.getZonaDto().toString());
		Assert.assertEquals(enderecoConvertido.getBairro(),
				enderecoDto.getBairro());
		Assert.assertEquals(enderecoConvertido.getMunicipio().getNome(),
				enderecoDto.getMunicipioDto().toString());
		Assert.assertEquals(enderecoConvertido.getUf().toString(), enderecoDto
				.getUfDto().toString());
		Assert.assertEquals(enderecoConvertido.getPais().getNome(), enderecoDto
				.getPaisDto().toString());
		Assert.assertEquals(enderecoConvertido.getTipoEndereco().toString(),
				enderecoDto.getTipoEnderecoDto().toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_endereco_converte_objeto_de_dominio_para_dto() {
		Long id = new Long("34324");
		Endereco enderecoDominio = ContextoEndereco
				.fabricarEnderecoComTodosOsDados();
		enderecoDominio.setId(id);
		EnderecoDTO enderecoConvertido = new FabricaEndereco()
				.converterParaDTO(enderecoDominio);

		Assert.assertEquals(enderecoConvertido.getId(), enderecoDominio.getId());
		Assert.assertEquals(enderecoConvertido.getCep(),
				enderecoDominio.getCep());
		Assert.assertEquals(enderecoConvertido.getEndereco(),
				enderecoDominio.getEndereco());
		Assert.assertEquals(enderecoConvertido.getNumero(),
				enderecoDominio.getNumero());
		Assert.assertEquals(enderecoConvertido.getComplemento(),
				enderecoDominio.getComplemento());
		Assert.assertEquals(enderecoConvertido.getZonaDto().toString(),
				enderecoDominio.getZona().toString());
		Assert.assertEquals(enderecoConvertido.getBairro(),
				enderecoDominio.getBairro());
		Assert.assertEquals(enderecoConvertido.getMunicipioDto().toString(),
				enderecoDominio.getMunicipio().getNome());
		Assert.assertEquals(enderecoConvertido.getUfDto().toString(),
				enderecoDominio.getUf().toString());
		Assert.assertEquals(enderecoConvertido.getPaisDto().toString(),
				enderecoDominio.getPais().getNome());
		Assert.assertEquals(enderecoConvertido.getTipoEnderecoDto().toString(),
				enderecoDominio.getTipoEndereco().toString());
	}
}

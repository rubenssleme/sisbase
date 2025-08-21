package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.TipoEnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.endereco.Municipio;
import br.laramara.ti.sislaraserver.dominio.endereco.Pais;
import br.laramara.ti.sislaraserver.dominio.endereco.TipoEndereco;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;

public class ContextoEndereco {
	public static Endereco fabricarEnderecoComTodosOsDados() {
		Endereco endereco = new Endereco();
		endereco.setCep("01151000");
		endereco.setEndereco("Rua Brigadeiro Galvão");
		endereco.setNumero("348");
		endereco.setComplemento("AP384");
		endereco.setZona(Zona.OESTE);
		endereco.setBairro("Barra Funda");
		endereco.setMunicipio(new Municipio(new Long(4850), "São Paulo", UF.SP));
		endereco.setUf(UF.SP);
		endereco.setPais(new Pais(new Long(1), "Brasil"));
		endereco.setTipoEndereco(TipoEndereco.COMERCIAL);
		return endereco;
	}

	public static EnderecoDTO construirEnderecoDTO() {
		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setCep("01151000");
		enderecoDto.setEndereco("Rua Brigadeiro Galvão");
		enderecoDto.setNumero("348");
		enderecoDto.setComplemento("AP384");
		enderecoDto.setZonaDto(new ZonaDTO(Zona.OESTE.toString()));
		enderecoDto.setBairro("Barra Funda");
		enderecoDto.setMunicipioDto(new MunicipioDTO(new Long(4850), "São Paulo",
				new UfDTO(UF.SP.toString())));
		enderecoDto.setUfDto(new UfDTO(UF.AL.toString()));
		enderecoDto.setPaisDto(new PaisDTO(new Long(1), "Brasil"));
		enderecoDto.setTipoEnderecoDto(new TipoEnderecoDTO(TipoEndereco.COMERCIAL.toString()));
		return enderecoDto;
	}
	
	public static Endereco construirEnderecoSemPaisEZona() {
		Endereco endereco = new Endereco();
		endereco.setCep("01151000");
		endereco.setEndereco("Rua Brigadeiro Galvão");
		endereco.setNumero("348");
		endereco.setComplemento("AP384");
		endereco.setBairro("Barra Funda");
		endereco.setMunicipio(new Municipio(new Long(4850), "São Paulo", UF.SP));
		endereco.setUf(UF.SP);
		return endereco;
	}
	
	public static EnderecoDTO construirEnderecoDTOSemPaisEZona() {
		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setCep("01151000");
		enderecoDto.setEndereco("Rua Brigadeiro Galvão");
		enderecoDto.setNumero("348");
		enderecoDto.setComplemento("AP384");
		enderecoDto.setBairro("Barra Funda");
		enderecoDto.setMunicipioDto(new MunicipioDTO(new Long(4850), "São Paulo", new UfDTO("SP")));
		enderecoDto.setUfDto(new UfDTO("SP"));
		return enderecoDto;
	}
}

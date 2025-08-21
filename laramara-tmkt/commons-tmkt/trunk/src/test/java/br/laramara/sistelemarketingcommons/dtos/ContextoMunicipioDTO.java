package br.laramara.sistelemarketingcommons.dtos;

public class ContextoMunicipioDTO {

	public static MunicipioDTO construir() {
		MunicipioDTO municipioDTO = new MunicipioDTO();
		municipioDTO.setId(new Long(8966));
		municipioDTO.setDescricao("São Paulo");
		return municipioDTO;
	}

}

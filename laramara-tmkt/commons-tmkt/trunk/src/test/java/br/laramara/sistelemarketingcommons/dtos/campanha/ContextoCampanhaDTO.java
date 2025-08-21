package br.laramara.sistelemarketingcommons.dtos.campanha;

public class ContextoCampanhaDTO {

	public static CampanhaDTO construir() {
		CampanhaDTO campanhaDTO = new CampanhaDTO();
		campanhaDTO.setId(new Long(1));
		campanhaDTO.setNome("Nome da campanha");
		return campanhaDTO;
	}

}

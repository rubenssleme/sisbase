package br.laramara.sistelemarketingcommons.dtos.campanha;

public class ContextoCriterioDTO {

	public static CriterioDTO criarDto() {
		CriterioDTO criterioDTO = new CriterioDTO();
		criterioDTO.setId(new Long(1));
		criterioDTO.setNome("Criterio Bairro");
		return criterioDTO;
	}
}

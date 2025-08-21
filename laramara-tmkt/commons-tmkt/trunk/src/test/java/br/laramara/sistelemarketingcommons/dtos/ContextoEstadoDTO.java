package br.laramara.sistelemarketingcommons.dtos;

public class ContextoEstadoDTO {

	public static EstadoDTO construir() {
		EstadoDTO estadoDTO = new EstadoDTO();
		estadoDTO.setId(new Long(26));
		estadoDTO.setDescricao("São Paulo");
		return estadoDTO;
	}

}

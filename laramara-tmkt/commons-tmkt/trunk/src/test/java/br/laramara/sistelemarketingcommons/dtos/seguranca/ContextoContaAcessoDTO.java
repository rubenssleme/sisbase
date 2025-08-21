package br.laramara.sistelemarketingcommons.dtos.seguranca;

public class ContextoContaAcessoDTO {

	public static ContaAcessoDTO criar() {
		ContaAcessoDTO contaAcessoDTO = new ContaAcessoDTO();
		contaAcessoDTO.setId(new Long(1));
		return contaAcessoDTO;
	}

}

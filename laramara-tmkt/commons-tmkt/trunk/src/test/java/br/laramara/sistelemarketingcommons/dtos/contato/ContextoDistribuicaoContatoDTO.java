package br.laramara.sistelemarketingcommons.dtos.contato;

public class ContextoDistribuicaoContatoDTO {

	public static DistribuicaoContatoDTO criar() {
		DistribuicaoContatoDTO distribuicaoContatoDTO = new DistribuicaoContatoDTO();
		distribuicaoContatoDTO.setContatoDto(ContextoContatoDTO.criar());
		return distribuicaoContatoDTO;
	}
}

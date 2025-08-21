package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;

public class ContextoInformacaoEssencial {

	public static InformacaoEssencial fabricarInformacaoEssencialComTodosOsDados() {
		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.setNome("Paulo Augusto");
		informacaoEssencial.setDataNascimento("31/12/2004");
		informacaoEssencial.setContato(ContextoContato
				.fabricarContatoComTodosOsDados());
		informacaoEssencial.setCpf("93380738743");
		informacaoEssencial.adicionarRg("12345");
		return informacaoEssencial;
	}

	public static InformacaoEssencialDTO construirInformacaoEssencialDTO() {
		InformacaoEssencialDTO informacaoEssencialDTO = new InformacaoEssencialDTO();
		informacaoEssencialDTO.setNome("Josep Meaza");
		informacaoEssencialDTO.setDataNascimento("31/12/2013");
		informacaoEssencialDTO.setContatoDto(ContextoContato
				.construirContatoDTO());
		informacaoEssencialDTO.setCpf("93380738743");
		informacaoEssencialDTO.setRg("12345");
		return informacaoEssencialDTO;
	}
}

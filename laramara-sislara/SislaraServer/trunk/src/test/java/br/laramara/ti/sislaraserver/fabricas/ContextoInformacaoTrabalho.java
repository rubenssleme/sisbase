package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO;
import br.laramara.ti.sislaraserver.dominio.familiar.InformacaoTrabalho;

public class ContextoInformacaoTrabalho {

	public static InformacaoTrabalho fabricarInformacaoTrabalhoComTodosOsDados() {
		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.setFuncao("Analista de Sistemas");
		informacaoTrabalho.setFuncao("Accenture");
		
		return informacaoTrabalho;
	}
	
	public static InformacaoTrabalhoDTO construirInformacaoTrabalhoDTO() {
		br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO informacaoTrabalhoDto = new InformacaoTrabalhoDTO();
		informacaoTrabalhoDto.setFuncao("Analista de Sistemas");
		informacaoTrabalhoDto.setEmpresa("Coca Cola");
		return informacaoTrabalhoDto;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaraserver.dominio.familiar.InformacaoTrabalho;
import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;

public class ContextoInformacaoTrabalho {

	public static InformacaoTrabalho fabricarInformacaoTrabalhoComTodosOsDados() {
		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.setFuncao("Analista de Sistemas");
		informacaoTrabalho.setFuncao("Accenture");
		informacaoTrabalho.setCargo(new Cargo(new Long(1), "Analista de Sistemas"));
		return informacaoTrabalho;
	}
	
	public static InformacaoTrabalhoDTO construirInformacaoTrabalhoDTO() {
		br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO informacaoTrabalhoDto = new InformacaoTrabalhoDTO();
		informacaoTrabalhoDto.setFuncao("Analista de Sistemas");
		informacaoTrabalhoDto.setEmpresa("Coca Cola");
		informacaoTrabalhoDto.setCargoDto(new CargoDTO(new Long(1), "Analista de Sistemas"));
		return informacaoTrabalhoDto;
	}
}

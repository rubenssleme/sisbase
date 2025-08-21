package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.LocalTrabalhoDTO;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.trabalho.Cargo;
import br.laramara.ti.sislaraserver.dominio.trabalho.InformacaoTrabalhoCompleta;
import br.laramara.ti.sislaraserver.dominio.trabalho.LocalTrabalho;

public class ContextoInformacaoTrabalhoCompleta {

	public static InformacaoTrabalhoCompleta fabricarInformacaoTrabalhoCompletaComTodosOsDados() {
		InformacaoTrabalhoCompleta informacaoTrabalhoCompleta = new InformacaoTrabalhoCompleta();
		informacaoTrabalhoCompleta.setDataInicial("31/12/2012");
		informacaoTrabalhoCompleta.setLocalTrabalho(new LocalTrabalho(new Long(1), "CTIS"));
		informacaoTrabalhoCompleta.setCargo(new Cargo(new Long(1), "Administrador"));
		informacaoTrabalhoCompleta.setDataFinal("31/01/2013");
		informacaoTrabalhoCompleta.setEncaminhadoPorLaramara(SimNao.SIM);
		informacaoTrabalhoCompleta.setVoluntario(SimNao.SIM);
		informacaoTrabalhoCompleta.setEstagiario(SimNao.SIM);
		return informacaoTrabalhoCompleta;
	}

	public static InformacaoTrabalhoCompletaDTO fabricarInformacaoTrabalhoDTOCompletaComTodosOsDados() {
		InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto = new InformacaoTrabalhoCompletaDTO();
		informacaoTrabalhoCompletaDto.setDataInicialVigencia("31/12/2012");
		informacaoTrabalhoCompletaDto.setLocalTrabalhoDto(new LocalTrabalhoDTO(new Long(1), "CTIS"));
		informacaoTrabalhoCompletaDto.setCargoDto(new CargoDTO(new Long(1), "Administrador"));
		informacaoTrabalhoCompletaDto.setDataFinalVigencia("31/01/2013");
		informacaoTrabalhoCompletaDto.setEncaminhadoPorLaramaraDto(new SimNaoDTO("SIM"));
		informacaoTrabalhoCompletaDto.setVoluntarioDto(new SimNaoDTO("SIM"));
		informacaoTrabalhoCompletaDto.setEstagiarioDto(new SimNaoDTO("SIM"));
		return informacaoTrabalhoCompletaDto;
	}
}

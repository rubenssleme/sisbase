package br.laramara.ti.sislaracommons.dtos.trabalho;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInformacaoTrabalhoCompletoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalhocompletadto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		CargoDTO cargoDto = new CargoDTO(id, "Administrador");
		LocalTrabalhoDTO localTrabalhoDto = new LocalTrabalhoDTO(id, "CTIS");
		SimNaoDTO simNaoDto = new SimNaoDTO("SIM");
		String data = "31/12/2012";
		String obs = "Texto";
		
		InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto = new InformacaoTrabalhoCompletaDTO();
		informacaoTrabalhoCompletaDto.setId(id);
		informacaoTrabalhoCompletaDto.setDataFinalVigencia(data);
		informacaoTrabalhoCompletaDto.setDataInicialVigencia(data);
		informacaoTrabalhoCompletaDto.setCargoDto(cargoDto);
		informacaoTrabalhoCompletaDto.setLocalTrabalhoDto(localTrabalhoDto);
		informacaoTrabalhoCompletaDto.setVoluntarioDto(simNaoDto);
		informacaoTrabalhoCompletaDto.setEncaminhadoPorLaramaraDto(simNaoDto);
		informacaoTrabalhoCompletaDto.setEstagiarioDto(simNaoDto);
		informacaoTrabalhoCompletaDto.setObs(obs);
		
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getId(), id);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getDataFinalVigencia(), data);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getDataInicialVigencia(), data);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getCargoDto().getId(), id);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getLocalTrabalhoDto().getId(), id);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getVoluntarioDto(), simNaoDto);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getEncaminhadoPorLaramaraDto(), simNaoDto);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getEstagiarioDto(), simNaoDto);
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getObs(), obs);
	}
}

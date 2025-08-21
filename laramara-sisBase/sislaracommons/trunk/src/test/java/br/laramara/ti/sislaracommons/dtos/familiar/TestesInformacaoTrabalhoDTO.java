package br.laramara.ti.sislaracommons.dtos.familiar;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInformacaoTrabalhoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaotrabalhodto_foi_construida_com_sucesso() {
		Long id = new Long(83444);
		String empresa = "CTIS";
		String funcao = "Analista de Sistemas";
		CargoDTO cargoDto = new CargoDTO(new Long(1), "Analista de Sistemas");
		
		InformacaoTrabalhoDTO informacaoTrabalhoDTO = new InformacaoTrabalhoDTO();
		informacaoTrabalhoDTO.setId(id);
		informacaoTrabalhoDTO.setEmpresa(empresa);
		informacaoTrabalhoDTO.setFuncao(funcao);
		informacaoTrabalhoDTO.setCargoDto(cargoDto);
		
		Assert.assertEquals(informacaoTrabalhoDTO.getId(), id);
		Assert.assertEquals(informacaoTrabalhoDTO.getEmpresa(), empresa);
		Assert.assertEquals(informacaoTrabalhoDTO.getFuncao(), funcao);
		Assert.assertEquals(informacaoTrabalhoDTO.getCargoDto().getId(), cargoDto.getId());
	}
}

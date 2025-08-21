package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesOrigemEncaminhamentoDetalhadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void origemencaminhamentodetalhadodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String profissionalLiberal = "Joao Siqueira";
		OrigemEncaminhamentoDTO origemEncaminhamentoDto = new OrigemEncaminhamentoDTO(id, "Hospital");

		OrigemEncaminhamentoDetalhadoDTO origemEncaminhamentoDTO = new OrigemEncaminhamentoDetalhadoDTO();
		origemEncaminhamentoDTO.setId(id);
		origemEncaminhamentoDTO.setProfissionalLiberal(profissionalLiberal);
		origemEncaminhamentoDTO.setOrigemEncaminhamentoDto(origemEncaminhamentoDto);

		Assert.assertEquals(origemEncaminhamentoDTO.getId(), id);
		Assert.assertEquals(origemEncaminhamentoDTO.getProfissionalLiberal(), profissionalLiberal);
		Assert.assertEquals(origemEncaminhamentoDTO.getOrigemEncaminhamentoDto().getId(), origemEncaminhamentoDto.getId());
	}
}

package br.laramara.ti.sislaracommons.dtos.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEncaminhamentoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void encaminhamentodto_foi_construida_com_sucesso() {
		Long id = new Long(1);
		String profissionalLiberal = "Joao Siqueira";
		OrigemEncaminhamentoDTO origemEncaminhamentoDto = new OrigemEncaminhamentoDTO(id, "Hospital");
		RedeEncaminhamentoDTO redeEncaminhamentoDto = new RedeEncaminhamentoDTO(id, "SUAS");

		EncaminhamentoDTO encaminhamentoDTO = new EncaminhamentoDTO();
		encaminhamentoDTO.setId(id);
		encaminhamentoDTO.setProfissionalLiberal(profissionalLiberal);
		encaminhamentoDTO.setOrigemEncaminhamentoDto(origemEncaminhamentoDto);
		encaminhamentoDTO.setRedeEncaminhamentoDto(redeEncaminhamentoDto);

		Assert.assertEquals(encaminhamentoDTO.getId(), id);
		Assert.assertEquals(encaminhamentoDTO.getProfissionalLiberal(), profissionalLiberal);
		Assert.assertEquals(encaminhamentoDTO.getOrigemEncaminhamentoDto().getId(), origemEncaminhamentoDto.getId());
		Assert.assertEquals(encaminhamentoDTO.getRedeEncaminhamentoDto().getId(), redeEncaminhamentoDto.getId());
	}
}

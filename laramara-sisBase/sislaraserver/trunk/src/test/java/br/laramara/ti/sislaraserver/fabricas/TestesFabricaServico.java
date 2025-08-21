package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.ServicoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Servico;

public class TestesFabricaServico {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_servico_converte_objeto_de_dominio_para_dto() {
		Servico servico = new Servico(new Long(11111), "Fisioterapia");

		ServicoDTO servicoDTO = new FabricaServico()
				.converterParaDTO(servico);

		Assert.assertEquals(servicoDTO.getId(), servico.getId());
		Assert.assertEquals(servicoDTO.toString(), servico.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_servico_converte_objeto_de_dto_para_dominio() {
		ServicoDTO servicoDto = new ServicoDTO(new Long(11111),
				"Fisioteparia");

		Servico servicoObtido = new FabricaServico()
				.converterParaDominio(servicoDto);

		Assert.assertEquals(servicoObtido.getId(), servicoDto.getId());
		Assert.assertEquals(servicoObtido.getDescricao(), servicoDto.toString());
	}
}

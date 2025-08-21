package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.retirada.Retirada;

public class TestesFabricaRetirada {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_retirada_converte_objeto_dto_para_dominio() {
		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProfissionalDto(ContextoProfissional
				.construirProfissionalDTO());
		retiradaDto.setVoluntarioDto(ContextoProfissional
				.construirProfissionalDTOAlternativo());
		retiradaDto.setProntuario(new Long(2323));

		Retirada retiradaCovertido = new FabricaRetirada()
				.converterParaDominio(retiradaDto);

		Assert.assertEquals(retiradaCovertido.getProfissional().getId(),
				retiradaDto.getProfissionalDto().getId());
		Assert.assertEquals(retiradaCovertido.getVoluntario().getId(),
				retiradaDto.getVoluntarioDto().getId());
		Assert.assertEquals(retiradaCovertido.getProntuario(),
				retiradaDto.getProntuario());
	}
}

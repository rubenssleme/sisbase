package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.HabitacaoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Habitacao;

public class TestesFabricaHabitacao {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_habitacao_converte_objeto_de_dominio_para_dto() {
		Habitacao habitacao = new Habitacao(new Long(1000), "TESTE");

		HabitacaoDTO habitacaoDTO = new FabricaHabitacao()
				.converterParaDTO(habitacao);

		Assert.assertEquals(habitacaoDTO.getId(), habitacao.getId());
		Assert.assertEquals(habitacaoDTO.toString(), habitacao.getDescricao());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_habitacao_converte_objeto_de_dto_para_dominio() {
		HabitacaoDTO habitacaoDto = new HabitacaoDTO(new Long(1000),
				"TESTE");

		Habitacao habitacaoObtido = new FabricaHabitacao()
				.converterParaDominio(habitacaoDto);

		Assert.assertEquals(habitacaoObtido.getId(), habitacaoDto.getId());
		Assert.assertEquals(habitacaoObtido.getDescricao(), habitacaoDto.toString());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;

public class TestesFabricaHorario {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_horario_converte_objeto_de_dto_para_dominio() {
		HorarioDTO horarioDTO = new HorarioDTO("09:00", "10:00");

		Horario contatoConvertido = new FabricaHorario()
				.converterParaDominio(horarioDTO);

		Assert.assertEquals(contatoConvertido.getHoraInicio(),
				horarioDTO.getHoraInicio());
		Assert.assertEquals(contatoConvertido.getHoraTermino(),
				horarioDTO.getHoraTermino());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_horario_converte_objeto_de_dominio_para_dto() {
		Horario horario = new Horario("09:00", "10:00");

		HorarioDTO contatoConvertido = new FabricaHorario()
				.converterParaDTO(horario);

		Assert.assertEquals(contatoConvertido.getHoraInicio(),
				horario.getHoraInicio());
		Assert.assertEquals(contatoConvertido.getHoraTermino(),
				horario.getHoraTermino());
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemanaEHorario;

public class TestesFabricaDiaSemanaEHorario {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_diasemanaehorario_converte_objeto_de_dto_para_dominio() {
		DiaSemanaEHorarioDTO diaSemanaEHorarioDto = ContextoDiasSemanaEHorarios.construirDiaSemanaEHorarioDTO();

		DiaSemanaEHorario diaSemanaEHorarioConvertido = new FabricaDiaSemanaEHorario()
				.converterParaDominio(diaSemanaEHorarioDto);

		Assert.assertEquals(diaSemanaEHorarioConvertido.getId(), diaSemanaEHorarioDto.getId());
		Assert.assertEquals(diaSemanaEHorarioConvertido.getDiaSemana().toString(),
				diaSemanaEHorarioConvertido.getDiaSemana().toString());
		Assert.assertEquals(diaSemanaEHorarioConvertido.getHorario().getHoraInicio(),
				diaSemanaEHorarioDto.getHorarioDto().getHoraInicio());
		Assert.assertEquals(diaSemanaEHorarioConvertido.getHorario().getHoraTermino(),
				diaSemanaEHorarioDto.getHorarioDto().getHoraTermino());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_diasemanaehorario_converte_objeto_de_dominio_para_dto() {
		DiaSemanaEHorario diaSemanaEHorario = ContextoDiasSemanaEHorarios.gerarDiaSemanaEHorario();

		DiaSemanaEHorarioDTO diaSemanaEHorarioDtoConvertido = new FabricaDiaSemanaEHorario()
				.converterParaDTO(diaSemanaEHorario);

		Assert.assertEquals(diaSemanaEHorarioDtoConvertido.getId(), diaSemanaEHorario.getId());
		Assert.assertEquals(diaSemanaEHorarioDtoConvertido.getDiaSemanaDto().toString(),
				diaSemanaEHorario.getDiaSemana().toString());
		Assert.assertEquals(diaSemanaEHorarioDtoConvertido.getHorarioDto().getHoraInicio(),
				diaSemanaEHorario.getHorario().getHoraInicio());
		Assert.assertEquals(diaSemanaEHorarioDtoConvertido.getHorarioDto().getHoraTermino(),
				diaSemanaEHorario.getHorario().getHoraTermino());
	}
}

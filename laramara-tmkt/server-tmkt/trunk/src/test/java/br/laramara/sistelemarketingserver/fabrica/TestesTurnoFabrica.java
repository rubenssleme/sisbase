package br.laramara.sistelemarketingserver.fabrica;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoDTO;
import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoTurno;
import br.laramara.sistelemarketingserver.dominio.seguranca.Turno;
import br.laramara.sistelemarketingserver.fabricas.TurnoFabrica;

public class TestesTurnoFabrica {
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_turno_converte_objeto_de_dominio_para_dto() {

		Turno turno = ContextoTurno.fabricarTurno();

		TurnoDTO turnoDtoCovertido = new TurnoFabrica().converterParaDTO(turno);

		Assert.assertEquals(turnoDtoCovertido.toString(), turno.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_turno_converte_objeto_dto_para_dominio() {
		TurnoDTO turnoDto = ContextoTurno.fabricarDto();

		Turno turnoCovertido = new TurnoFabrica().converterParaDominio(turnoDto);

		Assert.assertEquals(turnoCovertido.toString(), turnoDto.getTurno().toString());
	}
}

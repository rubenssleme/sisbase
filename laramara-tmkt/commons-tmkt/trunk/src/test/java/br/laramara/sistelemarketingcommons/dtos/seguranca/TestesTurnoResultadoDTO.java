package br.laramara.sistelemarketingcommons.dtos.seguranca;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;

public class TestesTurnoResultadoDTO {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void turno_resultado_listagem_dto_foi_construida_com_sucesso() {

		List<TurnoDTO> turnosDTO = Arrays.asList(ContextoTurnoDTO.construir());

		TurnoResultadoDTO permissoesResultadoDTO = new TurnoResultadoDTO();
		permissoesResultadoDTO.efetuadoComSucesso(turnosDTO);

		Assert.assertEquals(permissoesResultadoDTO.getTurnosDto().size(), permissoesResultadoDTO.getTurnosDto().size());
	}
}

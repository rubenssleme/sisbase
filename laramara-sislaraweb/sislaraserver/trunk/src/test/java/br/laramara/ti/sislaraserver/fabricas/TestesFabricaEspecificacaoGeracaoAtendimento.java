package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.EspecificacaoGeracaoAtendimento;

public class TestesFabricaEspecificacaoGeracaoAtendimento {
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_especificacao_geracao_atendimento_converte_objeto_de_dto_para_dominio() {

		Long idModuloPeriodo = new Long(1000);
		String data = "01/01/2000";
		HorarioDTO horarioDto = new HorarioDTO("09:00", "10:00");
		
		EspecificacaoGeracaoAtendimentoDTO especificacaoGeracaoAtendimentoDto = new EspecificacaoGeracaoAtendimentoDTO(idModuloPeriodo, data, horarioDto);
		
		EspecificacaoGeracaoAtendimento especificacaoGeracaoAtendimento = new FabricaEspecificacaoGeracaoAtendimento()
				.converterParaDominio(especificacaoGeracaoAtendimentoDto);
		
		Assert.assertEquals(especificacaoGeracaoAtendimento.getIdModuloPeriodo(),
				especificacaoGeracaoAtendimentoDto.getIdModuloPeriodoDto());
		Assert.assertEquals(especificacaoGeracaoAtendimento.getData(),
				especificacaoGeracaoAtendimentoDto.getData());
		Assert.assertEquals(especificacaoGeracaoAtendimento.getHorario().getHoraInicio(),
				especificacaoGeracaoAtendimentoDto.getHorarioDto().getHoraInicio());
		Assert.assertEquals(especificacaoGeracaoAtendimento.getHorario().getHoraTermino(),
				especificacaoGeracaoAtendimentoDto.getHorarioDto().getHoraTermino());
	}
}

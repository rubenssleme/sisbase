package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemTipoAtendimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoatendimentodto_com_tipoatendimentos_sem_erro_foi_construido() {
		List<TipoAtendimentoDTO> tipoAtendimentos = new ArrayList<>();
		tipoAtendimentos.add(new TipoAtendimentoDTO());
		tipoAtendimentos.add(new TipoAtendimentoDTO());

		ResultadoListagemTipoAtendimentoDTO resultado = new ResultadoListagemTipoAtendimentoDTO();
		resultado.efetuadoComSucesso(tipoAtendimentos);

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemtipoatendimentodto_com_um_tipoatendimento_sem_erro_foi_construido() {
		ResultadoListagemTipoAtendimentoDTO resultado = new ResultadoListagemTipoAtendimentoDTO();
		resultado.efetuadoComSucesso(new TipoAtendimentoDTO());

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisatipoatendimentodto_vazio_foi_construido() {

		ResultadoListagemTipoAtendimentoDTO resultado = new ResultadoListagemTipoAtendimentoDTO();
		resultado.nenhumRegistroEncontrado();

		Assert.assertFalse(resultado.sucesso());
		Assert.assertNotNull(resultado.obterMensagens());
	}
}

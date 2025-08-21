package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemDescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemDescricaoTipoAtendimentoDTO {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdescricaotipoatendimentodto_com_descricaotipoatendimentos_sem_erro_foi_construido() {
		List<DescricaoTipoAtendimentoDTO> descricaoTipoAtendimentos = new ArrayList<>();
		descricaoTipoAtendimentos.add(new DescricaoTipoAtendimentoDTO());
		descricaoTipoAtendimentos.add(new DescricaoTipoAtendimentoDTO());

		ResultadoListagemDescricaoTipoAtendimentoDTO resultado = new ResultadoListagemDescricaoTipoAtendimentoDTO();
		resultado.efetuadoComSucesso(descricaoTipoAtendimentos);

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemdescricaotipoatendimentodto_com_um_descricaotipoatendimento_sem_erro_foi_construido() {
		ResultadoListagemDescricaoTipoAtendimentoDTO resultado = new ResultadoListagemDescricaoTipoAtendimentoDTO();
		resultado.efetuadoComSucesso(new DescricaoTipoAtendimentoDTO());

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadopesquisatipoatendimentodto_vazio_foi_construido() {

		ResultadoListagemDescricaoTipoAtendimentoDTO resultado = new ResultadoListagemDescricaoTipoAtendimentoDTO();
		resultado.nenhumRegistroEncontrado();

		Assert.assertFalse(resultado.sucesso());
		Assert.assertNotNull(resultado.obterMensagens());
	}
}

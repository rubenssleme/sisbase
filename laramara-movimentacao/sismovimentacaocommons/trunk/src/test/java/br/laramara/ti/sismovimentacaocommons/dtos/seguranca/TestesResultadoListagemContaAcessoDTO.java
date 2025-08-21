package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TiposDeTeste;

public class TestesResultadoListagemContaAcessoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void resultadolistagemcontaacessodto_com_contaacesso_sem_erro_foi_construido() {
		List<ContaAcessoDTO> contaAcessos = new ArrayList<>();
		contaAcessos.add(new ContaAcessoDTO());
		contaAcessos.add(new ContaAcessoDTO());

		ResultadoListagemContaAcessoDTO resultado = new ResultadoListagemContaAcessoDTO();
		resultado.efetuadoComSucesso(contaAcessos);

		Assert.assertFalse(resultado.getObjetosDto().isEmpty());
		Assert.assertEquals(resultado.getObjetosDto().size(),
				contaAcessos.size());
	}
}

package br.laramara.ti.sislaraserver.dominio.evento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoEvento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesDescricaoEvento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void descricaoevento_validacao_com_erro_obrigatoriedade() {
		DescricaoEvento descricaoEvento = new DescricaoEvento();

		descricaoEvento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(descricaoEvento.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void descricaoevento_validacao_com_erro_de_tamanho_maximo() {
		DescricaoEvento descricaoEvento = ContextoDescricaoEvento.fabricarDescricaoEmentaComTodosOsDados();

		descricaoEvento.setConteudo(ContextoGenerico.obterGrande());
		
		descricaoEvento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(descricaoEvento.obterNumeroErros(), 1);
	}
}

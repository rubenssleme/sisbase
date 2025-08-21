package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoOrigemEncaminhamentoDetalhado;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesOrigemEncaminhamentoDetalhado {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void origem_encaminhamento_detalhado_validacao_com_erro_tamanho_maximo() {
		OrigemEncaminhamentoDetalhado encaminhamento = ContextoOrigemEncaminhamentoDetalhado.fabricarOrigemEncaminhamentoDetalhado();
		encaminhamento.setProfissionalLiberal(ContextoGenerico.obterGrande());
		encaminhamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(encaminhamento.validado());
		Assert.assertTrue(encaminhamento.obterDescricaoErros().contains("Insira o Profissional liberal contendo até 100 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void origem_encaminhamento_detalhado_validacao_sem_erro() {
		OrigemEncaminhamentoDetalhado encaminhamento = ContextoOrigemEncaminhamentoDetalhado.fabricarOrigemEncaminhamentoDetalhado();
		encaminhamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(encaminhamento.validado());
	}
}

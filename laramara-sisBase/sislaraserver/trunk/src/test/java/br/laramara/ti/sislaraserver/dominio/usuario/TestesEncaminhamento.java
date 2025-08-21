package br.laramara.ti.sislaraserver.dominio.usuario;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoEncaminhamento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesEncaminhamento {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void encaminhamento_validacao_com_erro_tamanho_maximo() {
		Encaminhamento encaminhamento = ContextoEncaminhamento.fabricarEncaminhamento();
		encaminhamento.setProfissionalLiberal(ContextoGenerico.obterGrande());
		encaminhamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(encaminhamento.validado());
		Assert.assertTrue(encaminhamento.obterDescricaoErros().contains("Insira o Profissional liberal contendo até 100 caracteres."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void encaminhamento_validacao_sem_erro() {
		Encaminhamento encaminhamento = ContextoEncaminhamento.fabricarEncaminhamento();
		encaminhamento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(encaminhamento.validado());
	}
}

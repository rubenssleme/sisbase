package br.laramara.ti.sislaraserver.dominio;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesTelefone {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_construido_com_sucesso() {
		String dddTelefone = "1122223333";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone);

		Assert.assertEquals(telefone.getDDDTelefone(), dddTelefone);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_alternativo__construido_com_sucesso() {
		String dddTelefone = "11922223333";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone);

		Assert.assertEquals(telefone.getDDDTelefone(), dddTelefone);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_validacao_com_ddd_e_telefone_invalido() {
		String dddTelefone = "1";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone);
		telefone.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(telefone.validado());
		Assert.assertEquals(telefone.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_alternativo_validacao_com_ddd_e_telefone_invalido() {
		String dddTelefone = "111234567890";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone);
		telefone.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(telefone.validado());
		Assert.assertEquals(telefone.obterNumeroErros(), 1);
	}
}

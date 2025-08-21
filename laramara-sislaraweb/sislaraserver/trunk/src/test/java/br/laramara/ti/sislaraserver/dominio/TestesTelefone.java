package br.laramara.ti.sislaraserver.dominio;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesTelefone {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_construido_com_sucesso() {
		String dddTelefone = "1122223333";
		String ramal = "1234";
		String nomeContato = "Paulo";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone, ramal, nomeContato);

		Assert.assertEquals(telefone.getDDDTelefone(), dddTelefone);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_alternativo__construido_com_sucesso() {
		String dddTelefone = "11922223333";
		String ramal = "1234";
		String nomeContato = "Paulo";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone, ramal, nomeContato);

		Assert.assertEquals(telefone.getDDDTelefone(), dddTelefone);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_validacao_com_ddd_e_telefone_invalido() {
		String dddTelefone = ContextoGenerico.obterGrande();
		String ramal = ContextoGenerico.obterGrande();
		String nomeContato =  ContextoGenerico.obterGrande();
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone, ramal, nomeContato);
		telefone.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(telefone.validado());
		Assert.assertEquals(telefone.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void telefone_alternativo_validacao_com_ddd_e_telefone_invalido() {
		String dddTelefone = "111234567890";
		String ramal = "1234";
		String nomeContato = "Paulo";
		Telefone telefone = new Telefone(TipoTelefone.RESIDENCIAL, dddTelefone, ramal, nomeContato);
		telefone.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(telefone.validado());
		Assert.assertEquals(telefone.obterNumeroErros(), 1);
	}
}

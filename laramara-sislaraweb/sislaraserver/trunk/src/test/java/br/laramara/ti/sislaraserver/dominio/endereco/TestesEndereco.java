package br.laramara.ti.sislaraserver.dominio.endereco;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoEndereco;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesEndereco {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void endereco_novo_com_validacao_sem_erro() {
		Endereco usuarioExterno = ContextoEndereco.fabricarEnderecoComTodosOsDados();

		usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuarioExterno.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void endereco_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Endereco endereco = new Endereco();
		
		endereco.setCep("1234");
		endereco.setEndereco("");
		endereco.setNumero("");
		endereco.setBairro("");
		endereco.setMunicipio(null);
		endereco.setUf(null);
		endereco.setPais(null);
		endereco.setZona(null);
		
		endereco.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(endereco.validado());
		Assert.assertNotNull(endereco.obterDescricaoErros());
		Assert.assertEquals(endereco.obterNumeroErros(), 8, "Deveria haver 8 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void endereco_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();
		
		Endereco endereco = ContextoEndereco.fabricarEnderecoComTodosOsDados();

		endereco.setEndereco(textoGrande);
		endereco.setNumero(textoGrande);
		endereco.setComplemento(textoGrande);
		endereco.setBairro(textoGrande);

		endereco.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(endereco.validado());
		Assert.assertNotNull(endereco.obterDescricaoErros());
		Assert.assertEquals(endereco.obterNumeroErros(), 4,
				"Deveriam haver 4 infrações de largura máxima de dados.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void endereco_anula_campo_em_branco() {
		Endereco endereco = new Endereco();

		endereco.setCep("");
		endereco.setComplemento("");

		Assert.assertNull(endereco.getCep());
		Assert.assertNull(endereco.getComplemento());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void endereco_obtem_endereco_com_numero_e_complemento_com_sucesso() {
		String enderecoComNumeroEComplemento = "Rua Brigadeiro Galvão, 348 AP384";
		
		Endereco usuarioExterno = ContextoEndereco.fabricarEnderecoComTodosOsDados();
		
		Assert.assertEquals(usuarioExterno.obterEnderecoComNumeroEComplemento(), enderecoComNumeroEComplemento);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void endereco_obtem_endereco_com_numero_e_complemento_sem_sucesso() {
		String enderecoSemNumeroEComplemento = "Rua Brigadeiro Galvão";
		Endereco usuarioExterno = new Endereco();
		
		usuarioExterno.setEndereco(enderecoSemNumeroEComplemento);
		
		Assert.assertEquals(usuarioExterno.obterEnderecoComNumeroEComplemento(), enderecoSemNumeroEComplemento);
	}
}

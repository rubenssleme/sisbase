package br.laramara.ti.sislaraserver.dominio.usuario.externo;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoUsuarioExterno;

public class TestesUsuarioExterno {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_novo_com_validacao_sem_erro() {

		UsuarioExterno usuarioExterno = ContextoUsuarioExterno.fabricarUsuarioExternoComTodosOsDados();

		usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuarioExterno.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_validacao_com_erro_de_obrigatoriedade_de_dados() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();

		usuarioExterno.setEmail("");
		usuarioExterno.setNomeCompleto("");
		usuarioExterno.setDddETelefone("");
		usuarioExterno.setCpf("");
		usuarioExterno.setDataNascimento("");
		usuarioExterno.setRgRne("");
		
		Endereco enderecoResidencial = new Endereco();
		
		enderecoResidencial.setEndereco("");
		enderecoResidencial.setNumero("");
		enderecoResidencial.setBairro("");
		enderecoResidencial.setMunicipio(null);
		enderecoResidencial.setUf(null);
		
		usuarioExterno.setEnderecoResidencial(enderecoResidencial);

		usuarioExterno.validarPerfilPreenchido();

		Assert.assertFalse(usuarioExterno.validado());
		Assert.assertNotNull(usuarioExterno.obterDescricaoErros());
		Assert.assertEquals(usuarioExterno.obterNumeroErros(), 11, "Deveria haver 11 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_validacao_com_erro_de_tamanho_de_dados() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();

		usuarioExterno.setEmail(ContextoGenerico.obterGrande() + "@gmail.com");
		usuarioExterno.setNomeCompleto(ContextoGenerico.obterGrande());
		usuarioExterno.setDddETelefone("11234567");
		usuarioExterno.setOutroTelefone("11234567");
		usuarioExterno.setCpf(ContextoGenerico.obterGrande());
		usuarioExterno.setRgRne(ContextoGenerico.obterGrande());

		usuarioExterno.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuarioExterno.validado());
		Assert.assertNotNull(usuarioExterno.obterDescricaoErros());
		Assert.assertEquals(usuarioExterno.obterNumeroErros(), 6,
				"Deveriam haver 6 infrações de largura máxima de dados.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_anula_campo_em_branco() {
		UsuarioExterno usuarioExterno = new UsuarioExterno();

		usuarioExterno.setEmail("");
		usuarioExterno.setNomeCompleto("");
		usuarioExterno.setDddETelefone("");
		usuarioExterno.setOutroTelefone("");
		usuarioExterno.setCpf("");

		Assert.assertNull(usuarioExterno.getEmail());
		Assert.assertNull(usuarioExterno.getNomeCompleto());
		Assert.assertNull(usuarioExterno.getDdd());
		Assert.assertNull(usuarioExterno.getTelefoneCelular());
		Assert.assertNull(usuarioExterno.getOutroTelefone());
		Assert.assertNull(usuarioExterno.getCpf());
	}
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_novo_com_senha_criptografada() {
		String senha = "81dc9bdb52d04dc20036dbd8313ed055";
		UsuarioExterno usuarioExterno = new UsuarioExterno();
		usuarioExterno.setSenha("1234");

		Assert.assertEquals(usuarioExterno.getSenha(), senha);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_externo_valida_perfilpreenchido_com_datanascimento_invalida_sem_sucesso() {
		UsuarioExterno usuarioExterno = ContextoUsuarioExterno.fabricarUsuarioExternoComTodosOsDados();
		
		usuarioExterno.setDataNascimento("48372974");
		
		usuarioExterno.validarPerfilPreenchido();
		
		Assert.assertFalse(usuarioExterno.validado());
		Assert.assertNotNull(usuarioExterno.obterDescricaoErros());
		Assert.assertEquals(usuarioExterno.obterNumeroErros(), 1, "Deveria haver 1 infração de obrigatoriedade.");
	}
}

package br.laramara.ti.sislaraserver.dominio;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoContato;

public class TestesContato {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contato_validacao_sem_erro_de_obrigatoriedade_de_dados() {
		Contato contato = ContextoContato.fabricarContatoComTodosOsDados();
		contato.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(contato.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contato_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Contato contato = new Contato();
		contato.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(contato.validado());
		Assert.assertNotNull(contato.obterDescricaoErros());
		Assert.assertEquals(contato.obterNumeroErros(), 1,
				"Deveria haver 1 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contato_validacao_com_erro_de_obrigatoriedade_de_dados_do_telefone() {
		Contato contato = new Contato();
		List<Telefone> telefones = new ArrayList<>();
		telefones.add(new Telefone(null, null, null, null));
		contato.setTelefones(telefones);
		
		contato.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertFalse(contato.validado());
		Assert.assertNotNull(contato.obterDescricaoErros());
		Assert.assertEquals(contato.obterNumeroErros(), 2,
				"Deveria haver 2 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contato_validacao_com_erro_de_tamanho_exato_de_dados() {
		String telefoneInexistenteA = "112222";
		String telefoneInexistenteB = "113333";
		String telefoneInexistenteC = "114444";
		String ramal = "1234";
		String nomeContato = "Paulo";
		
		List<Telefone> telefones = new ArrayList<>();
		telefones.add(new Telefone(TipoTelefone.RESIDENCIAL,
				telefoneInexistenteA, ramal, nomeContato));
		telefones.add(new Telefone(TipoTelefone.CELULAR, telefoneInexistenteB, ramal, nomeContato));
		telefones
				.add(new Telefone(TipoTelefone.COMERCIAL, telefoneInexistenteC, ramal, nomeContato));

		Contato contato = new Contato();
		contato.setTelefones(telefones);

		contato.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(contato.validado());
		Assert.assertNotNull(contato.obterDescricaoErros());
		Assert.assertEquals(contato.obterNumeroErros(), 3,
				"Deveria haver 3 infrações de tamanho exato.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void contato_remove_caracteres_invalidos() {
		String telefoneComCaracteresInvalidos = "(11)22223333";
		String ramal = "1234";
		String nomeContato = "Paulo";
		List<Telefone> telefones = new ArrayList<>();
		telefones.add(new Telefone(TipoTelefone.RESIDENCIAL,
				telefoneComCaracteresInvalidos, ramal, nomeContato));

		Contato contato = new Contato();
		contato.setTelefones(telefones);

		Assert.assertEquals(contato.getTelefones().get(0).getDDDTelefone(),
				"1122223333");
	}
}

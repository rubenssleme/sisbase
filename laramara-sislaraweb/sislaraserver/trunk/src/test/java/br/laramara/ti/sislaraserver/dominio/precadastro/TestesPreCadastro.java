package br.laramara.ti.sislaraserver.dominio.precadastro;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.Telefone;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;

public class TestesPreCadastro {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void precadastro_validacao_sem_erro() {
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		preCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(preCadastro.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void precadastro_validacao_com_erro_de_obrigatoriedade_de_dados() {
		PreCadastro preCadastro = new PreCadastro();
		preCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(preCadastro.obterNumeroErros(), 3);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void precadastro_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();
		String ramal = "1234";
		String nomeContato = "Paulo";
		
		List<Telefone> telefones = new ArrayList<>();
		telefones
				.add(new Telefone(TipoTelefone.RESIDENCIAL, textoGrande + "A", ramal, nomeContato));
		telefones.add(new Telefone(TipoTelefone.COMERCIAL, textoGrande + "B", ramal, nomeContato));
		telefones.add(new Telefone(TipoTelefone.CELULAR, textoGrande + "C", ramal, nomeContato));
				
		Contato contato = new Contato();
		contato.setNomeContato(textoGrande);
		contato.setTelefones(telefones);
		contato.setRamal(textoGrande);
		contato.setEmail(textoGrande);
		
		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.setNome(textoGrande);
		informacaoEssencial.adicionarRg(textoGrande);
		informacaoEssencial.setContato(contato);
				
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		preCadastro.setInformacaoEssencial(informacaoEssencial);
		preCadastro.setObs(textoGrande);
		preCadastro.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(preCadastro.obterNumeroErros(), 9);
	}
}

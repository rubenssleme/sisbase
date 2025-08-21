package br.laramara.ti.sislaraserver.dominio.familiar;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.Telefone;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;
import br.laramara.ti.sislaraserver.fabricas.ContextoFamiliar;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoEssencial;

public class TestesFamiliar {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_sem_erro_nao_responsavel_com_dados() {
		Familiar familiar = ContextoFamiliar
				.fabricarFamiliarNaoResponsavelApenasComDadosObrigatorios();
		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(familiar.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_com_erro_nao_responsavel_sem_dados() {
		Familiar familiar = new Familiar();
		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(familiar.validado());
		Assert.assertEquals(familiar.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_com_erro_data_nascimento_invalida() {
		Familiar familiar = ContextoFamiliar
				.fabricarFamiliarResponsavelApenasComDadosObrigatorios();
		familiar.setDataNascimento("49/27/1982");
		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(familiar.validado());
		Assert.assertEquals(familiar.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_com_erro_renda_mensal_invalida() {
		Familiar familiar = ContextoFamiliar
				.fabricarFamiliarResponsavelApenasComDadosObrigatorios();
		familiar.setRenda("289128");
		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(familiar.validado());
		Assert.assertEquals(familiar.obterNumeroErros(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();

		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial.fabricarInformacaoEssencialComTodosOsDados();
		informacaoEssencial.adicionarRg(textoGrande);
		informacaoEssencial.setNome(textoGrande);
		
		Familiar familiar = ContextoFamiliar.fabricarFamiliarComTodosOsDados();
		familiar.setCpf(textoGrande);
		familiar.setInformacaoEssencial(informacaoEssencial);
		
		InformacaoTrabalho informacaoTrabalho = familiar
				.getInformacaoTrabalho();
		informacaoTrabalho.setEmpresa(textoGrande);
		informacaoTrabalho.setFuncao(textoGrande);

		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(familiar.obterNumeroErros(), 5);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_sem_erro_responsavel_com_dados() {
		Familiar familiar = ContextoFamiliar
				.fabricarFamiliarResponsavelApenasComDadosObrigatorios();

		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(familiar.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_com_erro_responsavel_sem_dados() {
		Familiar familiar = new Familiar();
		familiar.setPrincipalResponsavel(true);

		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(familiar.validado());
		Assert.assertEquals(familiar.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_nao_reponsavel_com_erro_de_tamanho_de_dados_de_contato() {
		String textoGrande = ContextoGenerico.obterGrande();
		String ramal = "1234";
		String nomeContato = "Paulo";
		List<Telefone> telefones = new ArrayList<>();
		telefones
				.add(new Telefone(TipoTelefone.RESIDENCIAL, textoGrande + "A", ramal, nomeContato));
		telefones.add(new Telefone(TipoTelefone.COMERCIAL, textoGrande + "B", ramal, nomeContato));
		telefones.add(new Telefone(TipoTelefone.CELULAR, textoGrande + "C", ramal, nomeContato));
		 
		Familiar familiar = ContextoFamiliar.fabricarFamiliarComTodosOsDados();
		familiar.setPrincipalResponsavel(false);
		Contato contato = familiar.getInformacaoEssencial().getContato();
		contato.setTelefones(telefones);
		contato.setRamal(textoGrande);
		contato.setNomeContato(textoGrande);
		contato.setEmail(textoGrande);
		
		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(familiar.obterNumeroErros(), 6);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_reponsavel_com_erro_de_tamanho_de_dados_de_informacao_trabalho() {
		String textoGrande = ContextoGenerico.obterGrande();

		Familiar familiar = ContextoFamiliar.fabricarFamiliarComTodosOsDados();
		familiar.setPrincipalResponsavel(false);
		familiar.setObs(textoGrande);

		InformacaoTrabalho informacaoTrabalho = familiar.getInformacaoTrabalho();
		informacaoTrabalho.setEmpresa(textoGrande);
		informacaoTrabalho.setFuncao(textoGrande);
		
		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(familiar.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void familiar_validacao_com_erro_de_renda_invalida() {
		Familiar familiar = ContextoFamiliar.fabricarFamiliarComTodosOsDados();
		familiar.setRenda("A12");

		familiar.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(familiar.validado());
		Assert.assertEquals(familiar.obterNumeroErros(), 1);
	}
}

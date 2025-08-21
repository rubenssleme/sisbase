package br.laramara.ti.sislaraserver.dominio.instituicao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Telefone;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoInstituicao;

public class TestesInstituicao {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void instituicao_obtem_endereco_atual_vazio() {
		Instituicao instituicao = new Instituicao();
		
		Endereco endereco = instituicao.getEndereco();
		
		Assert.assertNotNull(endereco);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void instituicao_validacao_sem_erro() {

		Instituicao instituicao = ContextoInstituicao.fabricarInstituicaoComTodosOsDados();
		instituicao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(instituicao.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void instituicao_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Instituicao instituicao = new Instituicao();

		instituicao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(instituicao.validado());
		Assert.assertNotNull(instituicao.obterDescricaoErros());
		Assert.assertEquals(instituicao.obterNumeroErros(), 12,
				"Deveria haver 12 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void instituicao_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();
		
		List<Telefone> telefones = new ArrayList<>();
		telefones
				.add(new Telefone(TipoTelefone.RESIDENCIAL, textoGrande + "A"));
		telefones
				.add(new Telefone(TipoTelefone.RESIDENCIAL, textoGrande + "B"));
		telefones
				.add(new Telefone(TipoTelefone.RESIDENCIAL, textoGrande + "C"));
				
		Instituicao instituicao = ContextoInstituicao
				.fabricarInstituicaoComTodosOsDados();
		instituicao.setNome(textoGrande);
		instituicao.getContato().setEmail(textoGrande);
		instituicao.getContato().setNomeContato(textoGrande);
		instituicao.setNomeCoordenadorResponsavel(textoGrande);
		instituicao.setObs(textoGrande);
		instituicao.getContato().setRamal(textoGrande);
		instituicao.getContato().setTelefones(telefones);

		Endereco endereco = instituicao.getEndereco();
		endereco.setEndereco(textoGrande);
		endereco.setNumero(textoGrande);
		endereco.setComplemento(textoGrande);
		endereco.setBairro(textoGrande);

		instituicao.setEndereco(endereco);

		instituicao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(instituicao.validado());
		Assert.assertNotNull(instituicao.obterDescricaoErros());
		Assert.assertEquals(instituicao.obterNumeroErros(), 13,
				"Deveriam haver 13 infrações de largura máxima de dados.");
	}
}

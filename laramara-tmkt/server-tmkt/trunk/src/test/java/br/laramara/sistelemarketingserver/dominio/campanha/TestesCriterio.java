package br.laramara.sistelemarketingserver.dominio.campanha;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.ContextoGenerico;
import br.laramara.sistelemarketingserver.ContextoMunicipio;
import br.laramara.sistelemarketingserver.dominio.Municipio;

public class TestesCriterio {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criterio_construido_com_sucesso() {
		Long id = new Long(12);
		String nome = "Nome do criterio";
		String bairro = "Higienopolis";
		Municipio municipio = ContextoMunicipio.fabricarMunicipio();
		Integer quantidadeAAdistribuir = new Integer(10000);

		Criterio criterio = new Criterio();
		criterio.setId(id);
		criterio.setNome(nome);
		criterio.setMunicipio(municipio);
		criterio.setBairro(bairro);
		criterio.setQuantidadeDistribuir(quantidadeAAdistribuir);
		criterio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(criterio.validado());
		Assert.assertEquals(criterio.getNome(), nome);
		Assert.assertEquals(criterio.getMunicipio(), municipio);
		Assert.assertEquals(criterio.getBairro(), bairro);
		Assert.assertEquals(criterio.getQuantidadeDistribuir(), quantidadeAAdistribuir);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criterio_com_erro_de_obrigatoriedade() {
		Criterio criterio = new Criterio();
		criterio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(criterio.validado());
		Assert.assertTrue(criterio.obterDescricaoErros().contains("Insira um nome válido."));
		Assert.assertTrue(criterio.obterDescricaoErros().contains("Insira um município."));
		Assert.assertTrue(criterio.obterDescricaoErros().contains("Insira um bairro."));
		Assert.assertTrue(criterio.obterDescricaoErros().contains("Insira uma quantidade a distribuir."));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void criterio_com_erro_de_tamanho_maximo_e_inconsistencia() {
		Criterio criterio = ContextoCriterio.fabricar();
		criterio.setNome(ContextoGenerico.obterGrande());
		criterio.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(criterio.validado());
		Assert.assertTrue(criterio.obterDescricaoErros().contains("Insira um nome contendo até 100 caracteres."));
	}
}

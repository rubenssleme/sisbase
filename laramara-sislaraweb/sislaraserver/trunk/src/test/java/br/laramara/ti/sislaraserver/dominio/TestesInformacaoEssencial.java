package br.laramara.ti.sislaraserver.dominio;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoEssencial;

public class TestesInformacaoEssencial {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoessencial_obtem_idade_com_sucesso() {
		String idade = "32";
		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial
				.fabricarInformacaoEssencialComTodosOsDados();
		LocalDate dataNascimento = LocalDate.now().minusYears(Integer.parseInt(idade));
		informacaoEssencial.setDataNascimento(DataHoraUtils.formatarData(dataNascimento));

		Assert.assertEquals(informacaoEssencial.getIdadeComoString(), idade);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoessencial_validacao_sem_erro_de_obrigatoriedade_de_dados() {
		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial
				.fabricarInformacaoEssencialComTodosOsDados();
		informacaoEssencial.validarObrigatoriedadeDeNomeETamanhoMaximoDeDados();

		Assert.assertTrue(informacaoEssencial.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoessencial_sem_rg_validacao_com_erro_de_validade_e_obrigatoriedade_de_dados() {
		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.setDataNascimento("48372974");
		informacaoEssencial
				.validarObrigatoriedadeDeNomeRgContatoETamanhoMaximo();

		Assert.assertFalse(informacaoEssencial.validado());
		Assert.assertNotNull(informacaoEssencial.obterDescricaoErros());
		Assert.assertEquals(informacaoEssencial.obterNumeroErros(), 4,
				"Deveria haver 3 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoessencial_adiciona_rg_ao_historico() {
		String rgAntigo = "12345";
		String rgNovo = "99999";

		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.adicionarRg(rgAntigo);
		informacaoEssencial.adicionarRg(rgAntigo);
		informacaoEssencial.adicionarRg(rgNovo);

		Assert.assertEquals(informacaoEssencial.getRg(), rgNovo);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void informacaoessencial_adiciona_rg_vazio_ao_historico_com_erro_de_obrigatoriedade_de_dados() {
		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.adicionarRg(null);
		informacaoEssencial
				.validarObrigatoriedadeDeNomeRgContatoETamanhoMaximo();
		Assert.assertFalse(informacaoEssencial.validado());
	}
}

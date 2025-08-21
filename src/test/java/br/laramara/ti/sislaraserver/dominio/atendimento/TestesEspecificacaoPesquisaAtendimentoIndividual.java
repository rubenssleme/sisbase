package br.laramara.ti.sislaraserver.dominio.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public class TestesEspecificacaoPesquisaAtendimentoIndividual {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_atendimento_individual_construido_com_sucesso() {
		Long id = new Long(12);
		String data = "31/12/2012";

		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(id);
		Modulo modulo = new Modulo(new Long(12), "Informatica");
		Profissional profissional = new Profissional(new Long(12), "JOSEP",
				"1234");
		String prontuario = "1234";
		String rg = "948349";

		EspecificacaoPesquisaAtendimentoIndividual especificacao = new EspecificacaoPesquisaAtendimentoIndividual();
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setModulo(modulo);
		especificacao.setDataInicio(data);
		especificacao.setDataTermino(data);
		especificacao.setProntuario(prontuario);
		especificacao.setRg(rg);
		especificacao.setProfissional(profissional);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(especificacao.validado());
		Assert.assertEquals(
				especificacao.getDescricaoTipoAtendimento().getId(), id);
		Assert.assertEquals(especificacao.getProntuario().toString(),
				prontuario);
		Assert.assertEquals(especificacao.getRg(), rg);
		Assert.assertEquals(especificacao.getModulo().getId(), modulo.getId());
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacao.getDataInicio()), data);
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacao.getDataTermino()),
				data);
		Assert.assertEquals(especificacao.getProfissional().getId(),
				profissional.getId());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_atendimento_individual_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoPesquisaAtendimentoIndividual especificacao = new EspecificacaoPesquisaAtendimentoIndividual();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
}

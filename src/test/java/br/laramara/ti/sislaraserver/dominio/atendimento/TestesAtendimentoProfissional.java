package br.laramara.ti.sislaraserver.dominio.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesAtendimentoProfissional {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentoprofissional_validacao_com_erro_obrigatoriedade_e_tamanho_maximo() {
		AtendimentoProfissional atendimento = new AtendimentoProfissional();
		InformacaoAtendimento informacoesAtendimento = new InformacaoAtendimento();
		informacoesAtendimento.setFrequencia(null);
		informacoesAtendimento.setDescricao(ContextoGenerico.obterGrande());
		informacoesAtendimento.setJustificativa(ContextoGenerico.obterGrande());
		atendimento.setInformacaoAtendimento(informacoesAtendimento);

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 4);
	}
}

package br.laramara.ti.sislaraserver.dominio.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoTipoVinculo;

public class TestesAtendimentoComunidade {
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentocomunidade_validacao_com_erro_obrigatoriedade_e_tamanho_maximo() {
		AtendimentoComunidade atendimento = new AtendimentoComunidade();
		InformacaoAtendimento informacoesAtendimento = new InformacaoAtendimento();
		informacoesAtendimento.setFrequencia(null);
		informacoesAtendimento.setDescricao(ContextoGenerico.obterGrande());
		informacoesAtendimento.setJustificativa(ContextoGenerico.obterGrande());
		atendimento.setInformacaoAtendimento(informacoesAtendimento);
		atendimento.setPreCadastro(ContextoPreCadastro.fabricarPreCadastroComTodosOsDados());
		atendimento.setTipoVinculo(ContextoTipoVinculo.fabricarComTodosOsDados());

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 3);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentocomunidade_validacao_com_erro_obrigatoriedade() {
		AtendimentoComunidade atendimento = new AtendimentoComunidade();

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 2);
	}
}

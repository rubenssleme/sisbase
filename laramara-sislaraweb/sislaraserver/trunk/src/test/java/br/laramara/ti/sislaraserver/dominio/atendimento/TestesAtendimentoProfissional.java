package br.laramara.ti.sislaraserver.dominio.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;

public class TestesAtendimentoProfissional {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentoprofissional_validacao_com_erro_obrigatoriedade_e_tamanho_maximo_e_data_invalida() {
		AtendimentoProfissional atendimento = new AtendimentoProfissional();
		InformacaoAtendimento informacoesAtendimento = new InformacaoAtendimento();
		informacoesAtendimento.setFrequencia(null);
		informacoesAtendimento.setDescricao(ContextoGenerico.obterGrande());
		informacoesAtendimento.setJustificativa(ContextoGenerico.obterGrande());
		atendimento.setInformacaoAtendimento(informacoesAtendimento);
		atendimento.setHorario(new Horario("09:00", "34:00"));

		atendimento.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(atendimento.validado());
		Assert.assertEquals(atendimento.obterNumeroErros(), 5);
		Assert.assertTrue(atendimento.obterDescricaoErros().contains("Insira o Profissional."));
		Assert.assertTrue(atendimento.obterDescricaoErros().contains("Insira uma Hora de Término válida."));
		Assert.assertTrue(atendimento.obterDescricaoErros().contains("Insira uma Frequência."));
		Assert.assertTrue(atendimento.obterDescricaoErros().contains("Insira uma Descrição contendo até 20000 caracteres."));
		Assert.assertTrue(atendimento.obterDescricaoErros().contains("Insira uma Justificativa contendo até 20000 caracteres."));
				
		
	}
}

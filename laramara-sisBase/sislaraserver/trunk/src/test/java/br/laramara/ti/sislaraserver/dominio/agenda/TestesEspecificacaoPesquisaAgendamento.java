package br.laramara.ti.sislaraserver.dominio.agenda;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

public class TestesEspecificacaoPesquisaAgendamento {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_agendamento_construido_com_sucesso() {
		Long id = new Long(12);
		String data = "31/12/2012";
		Profissional profissional = new Profissional(id, "Josep", "1234");
		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(id);
		StatusAgendamento statusAgendamento = StatusAgendamento.AGENDADO;
		String prontuario = "1234";
		String rg = "433221A";

		EspecificacaoPesquisaAgendamento especificacao = new EspecificacaoPesquisaAgendamento();
		especificacao.setProfissional(profissional);
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setDataInicio(data);
		especificacao.setDataTermino(data);
		especificacao.setStatusAgendamento(statusAgendamento);
		especificacao.setProntuario(prontuario);
		especificacao.setRg(rg);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertTrue(especificacao.validado());
		Assert.assertEquals(especificacao.getProfissional().getId(), id);
		Assert.assertEquals(
				especificacao.getDescricaoTipoAtendimento().getId(), id);
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacao.getDataInicio()), data);
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacao.getDataTermino()),
				data);
		Assert.assertEquals(especificacao.getStatusAgendamento(),
				statusAgendamento);
		Assert.assertEquals(especificacao.getProntuario().toString(), prontuario);
		Assert.assertEquals(especificacao.getRg(), rg);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_agendamento_validacao_com_erro_de_obrigatoriedade_de_dados_de_data_invalida() {
		EspecificacaoPesquisaAgendamento especificacao = new EspecificacaoPesquisaAgendamento();
		especificacao.setDataInicio("82984");
		especificacao.setDataTermino("82984");
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_agendamento_validacao_com_erro_de_data_inicio_posterior_data_termino() {
		EspecificacaoPesquisaAgendamento especificacao = new EspecificacaoPesquisaAgendamento();
		especificacao.setDataInicio("27/07/2012");
		especificacao.setDataTermino("26/07/2012");
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_agendamento_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoPesquisaAgendamento especificacao = new EspecificacaoPesquisaAgendamento();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
}

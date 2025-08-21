package br.laramara.ti.sislaraserver.dominio.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class TestesEspecificacaoPesquisaEspera {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_espera_construido_com_sucesso() {
		Long id = new Long(12);
		String data = "31/12/2012";

		DescricaoTipoAtendimento descricaoTipoAtendimento = new DescricaoTipoAtendimento();
		descricaoTipoAtendimento.setId(id);
		Modulo modulo = new Modulo(new Long(12), "Informatica");
		Setor setor = Setor.PROCEJA;
		TipoEspera tipoEspera = TipoEspera.RET;
		StatusEspera statusEspera = StatusEspera.AGUARDANDO;
		String prontuario = "1234";
		String rg = "948349";

		EspecificacaoPesquisaEspera especificacao = new EspecificacaoPesquisaEspera();
		especificacao.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		especificacao.setModulo(modulo);
		especificacao.setDataInicio(data);
		especificacao.setDataTermino(data);
		especificacao.setSetor(setor);
		especificacao.setTipoEspera(tipoEspera);
		especificacao.setStatusEspera(statusEspera);
		especificacao.setProntuario(prontuario);
		especificacao.setRg(rg);
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(especificacao.validado());
		Assert.assertEquals(
				especificacao.getDescricaoTipoAtendimento().getId(), id);
		Assert.assertEquals(especificacao.getProntuario().toString(), prontuario);
		Assert.assertEquals(especificacao.getRg(), rg);
		Assert.assertEquals(especificacao.getModulo().getId(), modulo.getId());
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacao.getDataInicio()), data);
		Assert.assertEquals(
				DataHoraUtils.formatarData(especificacao.getDataTermino()),
				data);
		Assert.assertEquals(especificacao.getSetor().toString(),
				setor.toString());
		Assert.assertEquals(especificacao.getTipoEspera().toString(),
				tipoEspera.toString());
		Assert.assertEquals(especificacao.getStatusEspera().toString(),
				statusEspera.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_espera_validacao_com_erro_de_obrigatoriedade_de_dados_de_data_invalida() {
		EspecificacaoPesquisaEspera especificacao = new EspecificacaoPesquisaEspera();
		especificacao.setDataInicio("82984");
		especificacao.setDataTermino("82984");
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 2);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_agendamento_validacao_com_erro_de_data_inicio_posterior_data_termino() {
		EspecificacaoPesquisaEspera especificacao = new EspecificacaoPesquisaEspera();
		especificacao.setDataInicio("27/07/2012");
		especificacao.setDataTermino("26/07/2012");
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacao_pesquisa_espera_validacao_com_erro_de_obrigatoriedade_de_dados() {
		EspecificacaoPesquisaEspera especificacao = new EspecificacaoPesquisaEspera();
		especificacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(especificacao.validado());
		Assert.assertEquals(especificacao.obterNumeroErros(), 1);
	}
}

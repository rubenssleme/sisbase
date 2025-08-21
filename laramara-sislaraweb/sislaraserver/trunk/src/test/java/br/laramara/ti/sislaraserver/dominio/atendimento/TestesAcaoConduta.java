package br.laramara.ti.sislaraserver.dominio.atendimento;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class TestesAcaoConduta {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_validacao_com_erro_obrigatoriedade() {
		AcaoConduta acaoConduta = new AcaoConduta();

		acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(acaoConduta.obterNumeroErros(), 1);
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Adicione um tipo de conduta."));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_validacao_com_erro_obrigatoriedade_integracao_sem_grupo_com_data_expectativa() {
		AcaoConduta acaoConduta = new AcaoConduta();
		acaoConduta.setTipoAcaoConduta(TipoAcaoConduta.INTEGRAR);
		acaoConduta.setDataExpectativa("01/01/2017");
		
		acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(acaoConduta.obterNumeroErros(), 2);
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Selecione um grupo para conduta."));
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Não é possível selecionar uma data de expectativa."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_validacao_com_erro_obrigatoriedade_retornar_sem_data_expectativa_e_setor_com_grupo() {
		AcaoConduta acaoConduta = new AcaoConduta();
		acaoConduta.setTipoAcaoConduta(TipoAcaoConduta.RETORNAR);
		acaoConduta.setGrupo(new Grupo());
		
		acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(acaoConduta.obterNumeroErros(), 3);
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Selecione uma data de expectativa."));
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Selecione um setor."));
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Não é possível selecionar um grupo."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_validacao_com_erro_obrigatoriedade_retornar_com_data_expectativa_invalida() {
		AcaoConduta acaoConduta = new AcaoConduta();
		acaoConduta.setTipoAcaoConduta(TipoAcaoConduta.RETORNAR);
		acaoConduta.setSetor(Setor.CTO);
		acaoConduta.setDataExpectativa("21/21/2017");
		
		acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(acaoConduta.obterNumeroErros(), 1);
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Insira uma data válida."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_validacao_com_erro_obrigatoriedade_nao_e_caso_lm_sem_obs() {
		AcaoConduta acaoConduta = new AcaoConduta();
		acaoConduta.setTipoAcaoConduta(TipoAcaoConduta.NAO_E_CASO_PARA_LM);
		acaoConduta.setObs("");
		
		acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(acaoConduta.obterNumeroErros(), 1);
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Insira uma observação."));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void acao_conduta_validacao_com_erro_obrigatoriedade_nao_e_caso_lm_com_data_expectativa() {
		AcaoConduta acaoConduta = new AcaoConduta();
		acaoConduta.setTipoAcaoConduta(TipoAcaoConduta.NAO_E_CASO_PARA_LM);
		acaoConduta.setDataExpectativa("31/12/2017");
		
		acaoConduta.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertEquals(acaoConduta.obterNumeroErros(), 2);
		Assert.assertTrue(acaoConduta.obterDescricaoErros().contains("Não é possível selecionar um grupo, data de expectativa."));
	}
}

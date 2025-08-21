package br.laramara.ti.sislaraserver.dominio.espera;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.fabricas.ContextoContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspera;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoModulo;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;

public class TestesEspera {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_construido_com_sucesso() {
		Long id = new Long(12222);
		PreCadastro preCadastro = ContextoPreCadastro
				.fabricarPreCadastroComTodosOsDados();
		String data = "31/12/2012";
		DescricaoTipoAtendimento descricaoTipoAtendimento = ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados();
		Modulo modulo = ContextoModulo.fabricarComTodosOsDados();
		Setor setor = Setor.PROCEJA;
		NomeGrupo nomeGrupo = new NomeGrupo(new Long(1), "G02");
		TipoEspera tipoEspera = TipoEspera.RET;
		String obs = "Grande texto de observação";
		boolean interesse = true;

		Espera espera = new Espera();
		espera.setId(id);
		espera.setPreCadastro(preCadastro);
		espera.setDataExpectativa(data);
		espera.setDescricaoTipoAtendimento(descricaoTipoAtendimento);
		espera.setModulo(modulo);
		espera.setSetor(setor);
		espera.setNomeGrupo(nomeGrupo);
		espera.setTipoEspera(tipoEspera);
		espera.setObs(obs);
		espera.setContaAcessoOperacao(ContextoContaAcesso
				.fabricarComTodosOsDados());
		espera.setInteresse(interesse);
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(espera.validado());
		Assert.assertEquals(espera.getId(), id);
		Assert.assertEquals(espera.getPreCadastro().getId(),
				preCadastro.getId());
		Assert.assertEquals(espera.getDataExpectativa(), data);
		Assert.assertEquals(espera.getDescricaoTipoAtendimento().getId(),
				descricaoTipoAtendimento.getId());
		Assert.assertEquals(espera.getModulo().getId(), modulo.getId());
		Assert.assertEquals(espera.getSetor().toString(), setor.toString());
		Assert.assertEquals(espera.getNomeGrupo().getId(), nomeGrupo.getId());
		Assert.assertEquals(espera.getTipoEspera().toString(),
				tipoEspera.toString());
		Assert.assertEquals(espera.getObs(), obs);
		Assert.assertEquals(espera.isInteresse(), interesse);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Espera espera = new Espera();
		espera.setObs(ContextoGenerico.obterGrande());
		espera.setJustificativaCancelamento(ContextoGenerico.obterGrande());
		espera.setDataExpectativa("asdasd");
		espera.agendado(ContextoContaAcesso.fabricarComTodosOsDados());
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(espera.validado());
		Assert.assertNotNull(espera.obterDescricaoErros());
		Assert.assertEquals(espera.obterNumeroErros(), 7,
				"Deveria haver 7 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_validacao_com_erro_de_status_difirente_triando_e_possui_pendencias() {
		Espera espera = ContextoEspera.fabricarEsperaComTodosOsDados();
		espera.setPendencias(true);

		espera.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(espera.validado());
		Assert.assertTrue(espera.obterDescricaoErros()
				.contains("Não é possível existirem pendencias em espera com status diferente de TRIANDO."));
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_construido_com_status_aguardando() {
		Espera espera = new Espera();
		espera.setContaAcessoOperacao(ContextoContaAcesso
				.fabricarComTodosOsDados());

		Assert.assertEquals(espera.obterStatus(), StatusEspera.AGUARDANDO);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_com_ligacao_interesse_usuario() {
		Espera espera = new Espera();
		espera.setInteresse(true);

		Assert.assertEquals(espera.getDataUltimaLigacaoInteresse(),
				DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual()));
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_sem_ligacao_interesse_usuario() {
		Espera espera = new Espera();
		espera.setInteresse(true);
		espera.setInteresse(false);

		Assert.assertEquals(espera.getDataUltimaLigacaoInteresse(), "");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_construido_com_status_agendado() {
		Espera espera = new Espera();
		espera.agendado(ContextoContaAcesso.fabricarComTodosOsDados());

		Assert.assertEquals(espera.obterStatus(), StatusEspera.AGENDADO);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_construido_com_status_cancelado() {
		Espera espera = new Espera();
		espera.setContaAcessoOperacao(ContextoContaAcesso
				.fabricarComTodosOsDados());
		espera.cancelar(ContextoContaAcesso.fabricarComTodosOsDados());

		Assert.assertEquals(espera.obterStatus(), StatusEspera.CANCELADO);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_cancelada_com_erro_obrigatoriedade_dados() {
		Espera espera = ContextoEspera.fabricarEsperaComTodosOsDados();
		espera.setJustificativaCancelamento("");
		espera.cancelar(ContextoContaAcesso.fabricarComTodosOsDados());
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertEquals(espera.obterNumeroErros(), 1,
				"Deveria haver 1 infrações de obrigatoriedade.");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void espera_agendamento_com_erro_obrigatoriedade_dados() {
		Espera espera = ContextoEspera.fabricarEsperaComTodosOsDados();
		espera.setObs("");
		espera.agendado(ContextoContaAcesso.fabricarComTodosOsDados());
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		Assert.assertEquals(espera.obterNumeroErros(), 1,
				"Deveria haver 1 infrações de obrigatoriedade.");
	}
}

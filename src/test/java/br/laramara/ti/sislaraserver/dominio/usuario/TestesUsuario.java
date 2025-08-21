package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.Telefone;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;
import br.laramara.ti.sislaraserver.dominio.endereco.Endereco;
import br.laramara.ti.sislaraserver.dominio.escola.Escolaridade;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;
import br.laramara.ti.sislaraserver.fabricas.ContextoEndereco;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoEducacional;
import br.laramara.ti.sislaraserver.fabricas.ContextoPeriodoBeneficio;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;

public class TestesUsuario {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_obtem_renda_total_familiar() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();

		Assert.assertEquals(usuario.obterRendaTotalFamiliar(), "33,50");
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_sem_erro() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuario.validado());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_cep_e_data_expedicao_rg_invalido() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.getInformacaoEssencial().obterEnderecoAtual().setCep("283");
		usuario.setDataExpedicaoRg("23/00/00");
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertEquals(usuario.obterNumeroErros(), 2);
		Assert.assertFalse(usuario.obterDescricaoErros().isEmpty());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_de_obrigatoriedade_de_dados() {
		Usuario usuario = new Usuario();
		usuario.adicionarStatus(null);

		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertNotNull(usuario.obterDescricaoErros());
		Assert.assertEquals(usuario.obterNumeroErros(), 20,
				"Deveria haver 20 infrações de obrigatoriedade.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_de_tamanho_de_dados() {
		String textoGrande = ContextoGenerico.obterGrande();

		List<Telefone> telefones = new ArrayList<>();
		telefones
				.add(new Telefone(TipoTelefone.RESIDENCIAL, textoGrande + "A"));
		telefones.add(new Telefone(TipoTelefone.COMERCIAL, textoGrande + "B"));
		telefones.add(new Telefone(TipoTelefone.CELULAR, textoGrande + "C"));
				
		Contato contato = new Contato();
		contato.setNomeContato(textoGrande);
		contato.setTelefones(telefones);
		contato.setRamal(textoGrande);
		contato.setEmail(textoGrande);

		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.setNome(textoGrande);
		informacaoEssencial.adicionarRg(textoGrande);
		informacaoEssencial.setContato(contato);
		informacaoEssencial.adicionarEndereco(ContextoEndereco.fabricarEnderecoComTodosOsDados());

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.setInformacaoEssencial(informacaoEssencial);
		usuario.setNaturalidade(textoGrande);
		usuario.setNacionalidade(textoGrande);
		usuario.setOrgaoEmissorRg(textoGrande);
		usuario.getInformacaoEssencial().getContato()
				.setNomeContato(textoGrande);
		usuario.getInformacaoEssencial().getContato().setEmail(textoGrande);
		usuario.setObs(textoGrande);
		usuario.setOutrosApoiosServicos(textoGrande);
		usuario.setTamanhoFonte(textoGrande);
		usuario.setRenda("289128");
		usuario.setCirurgias(ContextoGenerico.obterGrande());
		usuario.setMedicamentos(ContextoGenerico.obterGrande());
		usuario.setConsanguinidade(ContextoGenerico.obterGrande());
				
		Endereco endereco = usuario.getInformacaoEssencial().obterEnderecoAtual();
		endereco.setEndereco(textoGrande);
		endereco.setNumero(textoGrande);
		endereco.setComplemento(textoGrande);
		endereco.setBairro(textoGrande);

		usuario.getInformacaoEssencial().adicionarEndereco(endereco);

		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertNotNull(usuario.obterDescricaoErros());
		Assert.assertEquals(usuario.obterNumeroErros(), 23,
				"Deveriam haver 23 infrações de largura máxima de dados.");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_anula_campo_em_branco() {
		Usuario usuario = new Usuario();
		usuario.getInformacaoEssencial().getContato().setNomeContato("");

		Endereco endereco = new Endereco();
		endereco.setComplemento("");

		usuario.getInformacaoEssencial().adicionarEndereco(endereco);

		Assert.assertNull(usuario.getInformacaoEssencial().getComplemento());
		Assert.assertNull(usuario.getInformacaoEssencial().getContato()
				.getNomeContato());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_nao_adiciona_endereco_igual_ao_atual() {
		Long id = new Long(1);

		Endereco primeiroEndereco = ContextoEndereco
				.fabricarEnderecoComTodosOsDados();
		primeiroEndereco.setId(id);

		Endereco segundoEndereco = ContextoEndereco
				.fabricarEnderecoComTodosOsDados();
		segundoEndereco.setId(id + 1);

		Usuario usuario = new Usuario();
		usuario.getInformacaoEssencial().adicionarEndereco(primeiroEndereco);
		usuario.getInformacaoEssencial().adicionarEndereco(segundoEndereco);

		Assert.assertEquals(usuario.getInformacaoEssencial().obterEnderecoAtual().getId(), id);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_finaliza_vigencia_endereco_atual_apos_atualizacao_de_endereco() {
		Long id = new Long(1);

		Endereco primeiroEndereco = ContextoEndereco
				.fabricarEnderecoComTodosOsDados();
		primeiroEndereco.setId(id);

		Endereco segundoEndereco = ContextoEndereco
				.fabricarEnderecoComTodosOsDados();
		segundoEndereco.setEndereco("NOVO ENDERECO");
		segundoEndereco.setId(id + 1);

		Usuario usuario = new Usuario();
		usuario.getInformacaoEssencial().adicionarEndereco(primeiroEndereco);
		usuario.getInformacaoEssencial().adicionarEndereco(segundoEndereco);

		Assert.assertTrue(primeiroEndereco.vigenciaEncerrada());
		Assert.assertFalse(segundoEndereco.vigenciaEncerrada());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_familiar_responsavel_inexitente() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.getFamiliares().get(0).setPrincipalResponsavel(false);
		usuario.getFamiliares().get(1).setPrincipalResponsavel(false);

		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertEquals(usuario.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_renda_invalida() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.setRenda("XAS");

		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertEquals(usuario.obterNumeroErros(), 1);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_duplicacao_beneficio() {

		List<PeriodoBeneficio> periodosBeneficios = new ArrayList<>();
		PeriodoBeneficio periodoBeneficio = ContextoPeriodoBeneficio
				.construirComTodosOsDados();
		periodoBeneficio.setDataFinal(null);
		periodoBeneficio.setId(new Long(1));
		periodosBeneficios.add(periodoBeneficio);
		PeriodoBeneficio periodoBeneficio2 = ContextoPeriodoBeneficio
				.construirComTodosOsDados();
		periodoBeneficio2.setDataFinal("");
		periodosBeneficios.add(periodoBeneficio2);
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.setPeriodoBeneficios(periodosBeneficios);

		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertEquals(usuario.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_de_mais_de_um_familiar_responsavel() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.getFamiliares().get(0).setPrincipalResponsavel(false);

		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
		Assert.assertEquals(usuario.obterNumeroErros(), 1);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_adiciona_historico_status_com_sucesso() {
		Status status = Status.CASO_NOVO;

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.adicionarStatus(status);

		Assert.assertEquals(usuario.obterHistoricoStatusAtual().getStatus(),
				status);
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_adiciona_e_altera_sem_excluir_informacao_educacional() {
		Long id = new Long(12222);
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		InformacaoEducacional informacaoEscolarAlterior = usuario
				.obterInformacoesEducacionais().get(0);
		informacaoEscolarAlterior.setId(id);

		InformacaoEducacional informacaoEscolarParaAlterar = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		informacaoEscolarParaAlterar.setId(id);
		informacaoEscolarParaAlterar.setEscolaridade(new Escolaridade(new Long(
				12), "TESTE"));

		InformacaoEducacional informacaoEscolarParaAdicionar = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		informacaoEscolarParaAdicionar.removerIdentificadorEGeraDataInicialVigencia();

		List<InformacaoEducacional> informacoesEscolares = new ArrayList<>();
		informacoesEscolares.add(informacaoEscolarParaAlterar);
		informacoesEscolares.add(informacaoEscolarParaAdicionar);

		usuario.adicionarInformacoesEducacionais(informacoesEscolares);

		Assert.assertEquals(usuario.obterInformacoesEducacionais().size(), 2);
		Assert.assertFalse(usuario.obterInformacoesEducacionais().get(0).excluido());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_exclui_informacao_educacional() {
		Long id = new Long(12222);
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		InformacaoEducacional informacaoEscolarAlterior = usuario
				.obterInformacoesEducacionais().get(0);
		informacaoEscolarAlterior.setId(id);

		List<InformacaoEducacional> informacoesEscolares = new ArrayList<>();

		usuario.adicionarInformacoesEducacionais(informacoesEscolares);

		Assert.assertEquals(usuario.obterInformacoesEducacionais().size(), 0);
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_nao_associado_ao_setor_proceja() {
		Usuario usuario = new Usuario();

		usuario.associarAoSetorProceja(true);
		usuario.associarAoSetorProceja(false);

		Assert.assertFalse(usuario.possuiVigenciaAtivaProceja());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_associado_ao_setor_proceja() {
		Usuario usuario = new Usuario();

		usuario.associarAoSetorProceja(true);

		Assert.assertTrue(usuario.possuiVigenciaAtivaProceja());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_nao_associado_ao_setor_cto() {
		Usuario usuario = new Usuario();

		usuario.associarAoSetorCTO(true);
		usuario.associarAoSetorCTO(false);

		Assert.assertFalse(usuario.possuiVigenciaAtivaCTO());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_associado_ao_setor_cto() {
		Usuario usuario = new Usuario();

		usuario.associarAoSetorCTO(true);

		Assert.assertTrue(usuario.possuiVigenciaAtivaCTO());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_nao_associado_ao_setor_proceja_e_cto() {
		Usuario usuario = new Usuario();

		usuario.associarAoSetorCTO(true);
		usuario.associarAoSetorProceja(true);
		usuario.associarAoSetorCTO(true);
		usuario.associarAoSetorProceja(true);
		
		usuario.associarAoSetorCTO(false);
		usuario.associarAoSetorProceja(false);
		
		Assert.assertFalse(usuario.possuiVigenciaAtivaCTO());
		Assert.assertFalse(usuario.possuiVigenciaAtivaProceja());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_classificacao_social_apos_inclusao_de_nula() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.adicionarClassificacaoSocial(null);
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertFalse(usuario.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_sem_erro_classificacao_social_apos_primeira_inclusao() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioSemClassificacaoSocial();

		usuario.adicionarClassificacaoSocial(ClassificacaoSocial.I);
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuario.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_sem_erro_classificacao_social_nula_e_nao_nula() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioSemClassificacaoSocial();

		usuario.adicionarClassificacaoSocial(null);
		usuario.adicionarClassificacaoSocial(ClassificacaoSocial.V5);
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuario.validado());
	}
	
	
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_sem_erro_classificacao_social_iserido_em_sequencia() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioSemClassificacaoSocial();

		usuario.adicionarClassificacaoSocial(ClassificacaoSocial.X1);
		usuario.adicionarClassificacaoSocial(ClassificacaoSocial.V5);
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuario.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_reponsavel_por_si_mesmo_sem_erro_obrigatoriedade_familiar_reponsavel() {

		List<Familiar> familiares = new ArrayList<>();
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.setFamiliares(familiares);
		usuario.setResponsavelPorSiMesmo(true);
		
		usuario.validarObrigatoriedadeETamanhoMaximoDeDados();

		Assert.assertTrue(usuario.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_sem_erro_de_multipla_deficiencia_e_cadeira_de_rodas() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.setCadeiraDeRodas(true);
		usuario.setMultiplaDeficiencia(true);
		
		Assert.assertTrue(usuario.validado());
	}
	
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_validacao_com_erro_de_multipla_deficiencia_e_cadeira_de_rodas() {

		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.setCadeiraDeRodas(false);
		usuario.setMultiplaDeficiencia(false);
		
		Assert.assertFalse(usuario.isCadeiraDeRodas());
	}
	
	//Teste expira daqui a 20 anos. Pode atualizar quando necessário.
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void usuario_atualiza_status_de_retorno_cto_com_sucesso() {
		Usuario usuario = ContextoUsuario.fabricarUsuarioComTodosOsDados();
		usuario.getInformacaoEssencial().setDataNascimento("26/07/2015");
		usuario.adicionarStatusUsuario(StatusRelacaoComModulo.DESISTENTE);

		usuario.atualizarStatusUsuarioParaDesistenteOuRetorno();

		Assert.assertEquals(usuario.getStatusUsuarioAtual(),
				StatusRelacaoComModulo.RETORNO);
	}
}

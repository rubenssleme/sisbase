package br.laramara.ti.sislaraserver.servicos.rest;

import java.rmi.RemoteException;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoConsultaDetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoListagemDetalhesCursoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaPerfilPreenchidoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoSolicitacaoRecuperacaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.externo.UsuarioExternoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoCredencialExterna;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoSolicitacaoCadastroInscricao;
import br.laramara.ti.sislaraserver.fabricas.externa.ContextoUsuarioExterno;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class TestesServicoSisLaraServerRest extends TestesIntegracaoAbstrato {
	
	public TestesServicoSisLaraServerRest() {
		super("DadosTestesServicoSisLaraServerRest.xml");
		Registro.inicializarContexto();
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_autentica_usuario_externo_com_sucesso() throws RemoteException {
		CredencialExternaDTO credencialExternaDTO = ContextoCredencialExterna.construirCredencialExternaComPerfilPreenchidoDTO();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(obterUrlUsuarioExternoAutenticacao(),
				credencialExternaDTO, ResultadoAutenticacaoDTO.class);

		Assert.assertTrue(resultadoAutenticacaoDTO.sucesso());
		Assert.assertFalse(resultadoAutenticacaoDTO.getToken().getToken().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_autentica_usuario_externo_sem_sucesso() throws RemoteException {
		String erro = "Usuário ou senha inválido. Tente novamente.";

		CredencialExternaDTO credencialExternaDTO = ContextoCredencialExterna
				.construirCredencialExternaDTONaoExistente();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(obterUrlUsuarioExternoAutenticacao(),
				credencialExternaDTO, ResultadoAutenticacaoDTO.class);

		Assert.assertFalse(resultadoAutenticacaoDTO.sucesso());
		Assert.assertNull(resultadoAutenticacaoDTO.getToken());
		Assert.assertTrue(resultadoAutenticacaoDTO.getMensagem().contains(erro));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_solicita_recuperacao_senha_do_usuario_externo_com_sucesso() throws RemoteException {
		String emailSolicitante = "usuario.externo@gmail.com";
		String mensagem = "Link de alteração de senha enviado para o email com sucesso.";

		ResultadoSolicitacaoRecuperacaoSenhaDTO resultadoSolicitacaoRecuperacaoSenhaDTO = executarPost(
				obterUrlUsuarioExternoSolicitacaoRecuperacaoSenha(), emailSolicitante,
				ResultadoSolicitacaoRecuperacaoSenhaDTO.class);

		Assert.assertTrue(resultadoSolicitacaoRecuperacaoSenhaDTO.sucesso());
		Assert.assertTrue(resultadoSolicitacaoRecuperacaoSenhaDTO.getMensagem().contains(mensagem));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_solicita_recuperacao_senha_do_usuario_externo_com_email_invalido_sem_sucesso()
			throws RemoteException {
		String emailInvalido = "carloskaf@";
		String erro = "E-mail ou CPF não encontrado ou inválido.";

		ResultadoSolicitacaoRecuperacaoSenhaDTO resultadoSolicitacaoRecuperacaoSenhaDTO = executarPost(
				obterUrlUsuarioExternoSolicitacaoRecuperacaoSenha(), emailInvalido,
				ResultadoSolicitacaoRecuperacaoSenhaDTO.class);

		Assert.assertFalse(resultadoSolicitacaoRecuperacaoSenhaDTO.sucesso());
		Assert.assertTrue(resultadoSolicitacaoRecuperacaoSenhaDTO.getMensagem().contains(erro));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_cadastra_novo_usuario_externo_com_sucesso() throws RemoteException {
		String mensagem = "Cadastro realizado com sucesso. Link de confirmação enviado para o seu e-mail.";

		SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDto = new SolicitacaoCadastroUsuarioExternoDTO(
				"Usuário não existente", "11111111111", "usuario.nao.existente@hotmail.com", true);

		ResultadoCadastroUsuarioExternoDTO resultadoCadastroUsuarioExternoDTO = executarPost(
				obterUrlUsuarioExternoCadastro(), solicitacaoCadastroUsuarioExternoDto,
				ResultadoCadastroUsuarioExternoDTO.class);

		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.sucesso());
		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.getMensagem().contains(mensagem));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_cadastra_usuario_externo_email_ja_sendo_utilizado_sem_sucesso() throws RemoteException {
		String email = "usuario.externo@gmail.com";
		String erro = "O email " + email + " já está sendo utilizado.";

		SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDto = new SolicitacaoCadastroUsuarioExternoDTO(
				"Carlos Henrique da Silva Kafka", "11981847597", email, true);

		ResultadoCadastroUsuarioExternoDTO resultadoCadastroInformacoesBasicasInscricaoDTO = executarPost(
				obterUrlUsuarioExternoCadastro(), solicitacaoCadastroUsuarioExternoDto,
				ResultadoCadastroUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoCadastroInformacoesBasicasInscricaoDTO.sucesso());
		Assert.assertTrue(resultadoCadastroInformacoesBasicasInscricaoDTO.getMensagem().contains(erro));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_cadastra_usuario_externo_sem_sucesso() throws RemoteException {
		String erro = "Informe um e-mail válido.\n" + "Informe um nome completo válido.\n"
				+ "Informe um telefone válido.";

		ResultadoCadastroUsuarioExternoDTO resultadoCadastroUsuarioExternoDTO = executarPost(
				obterUrlUsuarioExternoCadastro(), new SolicitacaoCadastroUsuarioExternoDTO(),
				ResultadoCadastroUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoCadastroUsuarioExternoDTO.sucesso());
		Assert.assertTrue(resultadoCadastroUsuarioExternoDTO.getMensagem().contains(erro));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_detalhescurso_com_sucesso() throws RemoteException {
		ResultadoListagemDetalhesCursoDTO resultadoListagemDetalhesCursoDto = executarGet(obterUrlDetalheCursoListagem(),
				ResultadoListagemDetalhesCursoDTO.class);

		Assert.assertTrue(resultadoListagemDetalhesCursoDto.sucesso());
		Assert.assertEquals(resultadoListagemDetalhesCursoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_detalhecurso_por_idgrupo_com_sucesso() throws RemoteException {
		Long idGrupo = 16666L;

		ResultadoConsultaDetalheCursoDTO resultadoConsultaDetalheCursoDTO = executarGet(
				obterUrlDetalheCursoConsultaPorIdGrupo(idGrupo), ResultadoConsultaDetalheCursoDTO.class);

		DetalheCursoDTO detalheCursoDto = resultadoConsultaDetalheCursoDTO.getDetalheCursoDto();

		Assert.assertTrue(resultadoConsultaDetalheCursoDTO.sucesso());
		Assert.assertEquals(detalheCursoDto.getIdGrupo(), idGrupo);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_detalhecurso_por_idgrupo_sem_sucesso() throws RemoteException {
		Long idGrupo = 0L;

		ResultadoConsultaDetalheCursoDTO resultadoConsultaDetalheCursoDTO = executarGet(
				obterUrlDetalheCursoConsultaPorIdGrupo(idGrupo), ResultadoConsultaDetalheCursoDTO.class);

		DetalheCursoDTO detalheCursoDto = resultadoConsultaDetalheCursoDTO.getDetalheCursoDto();

		Assert.assertFalse(resultadoConsultaDetalheCursoDTO.sucesso());
		Assert.assertNull(detalheCursoDto);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_consultaperfilpreenchido_usuarioexterno_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaComPerfilPreenchidoDTO(), ResultadoAutenticacaoDTO.class);

		ResultadoConsultaPerfilPreenchidoDTO resultadoConsultaPerfilPreenchidoDto = executarPost(
				obterUrlUsuarioExternoConsultaPerfilPreenchido(), resultadoAutenticacaoDTO.getToken(),
				ResultadoConsultaPerfilPreenchidoDTO.class);

		Assert.assertTrue(resultadoConsultaPerfilPreenchidoDto.sucesso());
		Assert.assertTrue(resultadoConsultaPerfilPreenchidoDto.isPerfilPreenchido());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_consultaperfilpreenchido_usuarioexterno_sem_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDTO = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirSegundaCredencialExternaSemPerfilPreenchidoDTO(), ResultadoAutenticacaoDTO.class);
		
		ResultadoConsultaPerfilPreenchidoDTO resultadoConsultaPerfilPreenchidoDto = executarPost(
				obterUrlUsuarioExternoConsultaPerfilPreenchido(), resultadoAutenticacaoDTO.getToken(),
				ResultadoConsultaPerfilPreenchidoDTO.class);

		Assert.assertFalse(resultadoConsultaPerfilPreenchidoDto.sucesso());
		Assert.assertFalse(resultadoConsultaPerfilPreenchidoDto.isPerfilPreenchido());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_nao_e_pessoa_com_deficiencia_e_marcou_baixavisao_cegueira_e_outradeficiencia_sem_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setePessoaComDeficiencia(false);
		usuarioExternoDto.setPossuiBaixaVisao(true);
		usuarioExternoDto.setPossuiCegueira(true);
		usuarioExternoDto.setOutraDeficiencia("Outra Deficiencia");
		
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);

		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_e_pessoa_com_deficiencia_e_nao_marcou_nenhuma_deficiencia_sem_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setePessoaComDeficiencia(true);
		usuarioExternoDto.setPossuiBaixaVisao(false);
		usuarioExternoDto.setPossuiCegueira(false);
		usuarioExternoDto.setOutraDeficiencia("");
		
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);

		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_nao_e_pessoa_com_deficiencia_e_nao_marcou_nenhuma_deficiencia_com_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setePessoaComDeficiencia(false);
		usuarioExternoDto.setPossuiBaixaVisao(false);
		usuarioExternoDto.setPossuiCegueira(false);
		usuarioExternoDto.setOutraDeficiencia("");
		
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);

		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertTrue(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_e_pessoa_com_deficiencia_e_marcou_baixa_visao_com_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setePessoaComDeficiencia(true);
		usuarioExternoDto.setPossuiBaixaVisao(true);
		
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);

		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertTrue(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_com_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);

		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertTrue(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_com_cpf_ja_existente_sem_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoDTOComTodosOsDados();

		usuarioExternoDto.setCpf("86890477006");
		
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);
		
		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_editar_usuarioexterno_sem_sucesso() throws RemoteException {
		UsuarioExternoDTO usuarioExternoDto = ContextoUsuarioExterno.fabricarUsuarioExternoInvalidoDTO();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto = new SolicitacaoEdicaoUsuarioExternoDTO();
		
		solicitacaoEdicaoUsuarioExternoDto.setToken(resultadoAutenticacaoDto.getToken().getToken());
		solicitacaoEdicaoUsuarioExternoDto.setUsuarioExternoDto(usuarioExternoDto);
		
		ResultadoEdicaoUsuarioExternoDTO resultadoEdicaoUsuarioExternoDto = executarPost(obterUrlUsuarioExternoEdicao(),
				solicitacaoEdicaoUsuarioExternoDto, ResultadoEdicaoUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoEdicaoUsuarioExternoDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_usuarioexterno_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);
		
		String token = resultadoAutenticacaoDto.getToken().getToken();

		ResultadoConsultaUsuarioExternoDTO resultadoConsultaUsuarioExternoDTO = executarGet(
				obterUrlUsuarioExternoConsultaPorToken(token), ResultadoConsultaUsuarioExternoDTO.class);

		Assert.assertTrue(resultadoConsultaUsuarioExternoDTO.sucesso());
		Assert.assertNotNull(resultadoConsultaUsuarioExternoDTO.getUsuarioExternoDto());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_usuarioexterno_sem_sucesso() throws RemoteException {
		ResultadoConsultaUsuarioExternoDTO resultadoConsultaUsuarioExternoDto = executarGet(
				obterUrlUsuarioExternoConsultaPorToken(new TokenDTO(UUID.randomUUID().toString()).getToken()),
				ResultadoConsultaUsuarioExternoDTO.class);

		Assert.assertFalse(resultadoConsultaUsuarioExternoDto.sucesso());
		Assert.assertNull(resultadoConsultaUsuarioExternoDto.getUsuarioExternoDto());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_consulta_endereco_por_cep_com_sucesso() throws RemoteException {
		String cep = "05205220";
		
		ResultadoConsultaCEP resultadoConsultaCep = executarGet(
				obterUrlEnderecoConsultaPorCep(cep),
				ResultadoConsultaCEP.class);

		Assert.assertTrue(resultadoConsultaCep.sucesso());
		Assert.assertNotNull(resultadoConsultaCep.getEnderecoCEPDto());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_consulta_endereco_por_cep_sem_sucesso() throws RemoteException {
		String cep = "1234";
		
		ResultadoConsultaCEP resultadoConsultaCep = executarGet(
				obterUrlEnderecoConsultaPorCep(cep),
				ResultadoConsultaCEP.class);

		Assert.assertFalse(resultadoConsultaCep.sucesso());
		Assert.assertNull(resultadoConsultaCep.getEnderecoCEPDto());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_listagem_uf_com_sucesso() throws RemoteException {
		int quantidadeUFs = 28;

		ResultadoListagemUfDTO resultadoListagemUfDto = executarGet(obterUrlUfListagem(),
				ResultadoListagemUfDTO.class);

		Assert.assertTrue(resultadoListagemUfDto.sucesso());
		Assert.assertEquals(resultadoListagemUfDto.getObjetosDto().size(), quantidadeUFs);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_municipio_por_uf_com_sucesso() throws RemoteException {
		String uf = "SP";
		int quantidadeMunicipios = 1;

		ResultadoListagemMunicipioDTO resultadoListagemMunicipioDto = executarGet(obterUrlMunicipioListagemPorUf(uf),
				ResultadoListagemMunicipioDTO.class);

		Assert.assertTrue(resultadoListagemMunicipioDto.sucesso());
		Assert.assertEquals(resultadoListagemMunicipioDto.getObjetosDto().size(), quantidadeMunicipios);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_obtem_municipio_por_uf_sem_sucesso() throws RemoteException {
		String ufInvalida = "INVÁLIDO";

		ResultadoListagemMunicipioDTO resultadoListagemMunicipioDto = executarGet(obterUrlMunicipioListagemPorUf(ufInvalida),
				ResultadoListagemMunicipioDTO.class);

		Assert.assertTrue(resultadoListagemMunicipioDto.sucesso());
		Assert.assertEquals(resultadoListagemMunicipioDto.getObjetosDto().size(), 0);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_cadastra_inscricao_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirCredencialExternaValidaDTO(), ResultadoAutenticacaoDTO.class);

		SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto = ContextoSolicitacaoCadastroInscricao
				.construirSolicitacaoCadastroInscricaoDTO(
						resultadoAutenticacaoDto.getToken().getToken());

		ResultadoCadastroInscricaoDTO resultadoCadastroInscricaoDTO = executarPost(obterUrlInscricaoCadastro(),
				solicitacaoCadastroInscricaoDto, ResultadoCadastroInscricaoDTO.class);

		Assert.assertTrue(resultadoCadastroInscricaoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_rest_cadastra_inscricao_sem_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = executarPost(obterUrlUsuarioExternoAutenticacao(),
				ContextoCredencialExterna.construirSegundaCredencialExternaSemPerfilPreenchidoDTO(),
				ResultadoAutenticacaoDTO.class);

		SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto = ContextoSolicitacaoCadastroInscricao
				.construirSolicitacaoCadastroInscricaoDTOComInscricaoInvalida(
						resultadoAutenticacaoDto.getToken().getToken());

		ResultadoCadastroInscricaoDTO resultadoCadastroInscricaoDTO = executarPost(obterUrlInscricaoCadastro(),
				solicitacaoCadastroInscricaoDto, ResultadoCadastroInscricaoDTO.class);

		Assert.assertFalse(resultadoCadastroInscricaoDTO.sucesso());
	}
}
package br.laramara.ti.sislaraserver.fachadas;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoAlteracaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.EspecificacaoPesquisaContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.EspecificacaoPesquisaPerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.GeralContaAcessoBloqueadoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PermissaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAlteracaoExtensaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoCoordenacaoEdicaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoEdicaoContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoEdicaoPerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemGeralBloqueadosDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemPerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoListagemPermissaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoOperacaoFiltroGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaraserver.dominio.seguranca.Autenticador;
import br.laramara.ti.sislaraserver.dominio.seguranca.Bloqueador;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcessoNaoAutenticadoException;
import br.laramara.ti.sislaraserver.dominio.seguranca.CoordenadorEdicaoGeral;
import br.laramara.ti.sislaraserver.dominio.seguranca.Credencial;
import br.laramara.ti.sislaraserver.dominio.seguranca.Perfil;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.fabricas.FabricaContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaCredencial;
import br.laramara.ti.sislaraserver.fabricas.FabricaPerfil;
import br.laramara.ti.sislaraserver.fabricas.FabricaPermissao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoContaAcessoEdicao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoPerfilEdicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPerfil;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;

@Component
public class FachadaSeguranca extends Fachada {

	@Inject 
	private Autenticador autenticador;
	@Inject
	private CoordenadorEdicaoGeral coordenadorEdicaoGeral;
	@Inject
	private RepositorioContaAcesso repositorioContaAcesso;
	@Inject
	private RepositorioPerfil repositorioPerfil;
	
	public ResultadoAutenticacaoDTO autenticarLogin(CredencialDTO credencialDto) {
		ResultadoAutenticacaoDTO resultado = new ResultadoAutenticacaoDTO();

		Credencial credencial = new FabricaCredencial()
				.converterParaDominio(credencialDto);
		try {
			String token = autenticador.autentica(credencial);
			resultado.efetuadoComSucesso(new TokenDTO(token));
		} catch (ContaAcessoNaoAutenticadoException e) {
			resultado.adicionarErro(e.getMessage());
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a solicitação de autenticação de login. \nDetalhes: "
					+ e);
		}
		return resultado;
	}

	public boolean possuiAutorizacao(TokenDTO tokenDto, String funcionalidade) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		Permissao permissao = Permissao
				.valueOf(Permissao.class, funcionalidade);

		return contaAcesso.possuiPermissao(permissao);
	}
	
	public void bloquearEdicao(String nome, TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (nome != null) {
			coordenadorEdicaoGeral.bloquear(nome, contaAcesso);
		}
	}

	public boolean estaBloqueadoParaEdicao(String nome, TokenDTO tokenDto) {
		return estaBloqueadoParaEdicao(nome, coordenadorEdicaoGeral, tokenDto);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean estaBloqueadoParaEdicao(String chave, Bloqueador bloqueador, TokenDTO tokenDto) {
		boolean retorno = true;
		ContaAcesso contaAcessoSolicitouEdicao = obterContaAcesso(tokenDto);
		if (chave != null) {
			ContaAcesso contaAcessoEditando = obterBloqueador(chave,
					bloqueador);
			if (contaAcessoEditando != null
					&& contaAcessoEditando.equals(contaAcessoSolicitouEdicao)) {
				retorno = false;
			} else {
				retorno = bloqueador.estaBloqueadoParaEdicao(chave);
			}
		}
		return retorno;
	}

	public void desbloquearEdicaoGeral(
			GeralContaAcessoBloqueadoDTO geralBloqueadoDto, TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		coordenadorEdicaoGeral.desbloquearChave(geralBloqueadoDto.getIdentificacao(), contaAcesso);
	}

	public void desbloquearEdicao(String nome, TokenDTO tokenDto) {
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		desbloquearEdicao(nome, contaAcesso);
	}
	
	private void desbloquearEdicao(String nome, ContaAcesso contaAcesso) {
		if (nome != null) {
			coordenadorEdicaoGeral.desbloquearChave(nome, contaAcesso);
		}
	}

	public ResultadoCoordenacaoEdicaoDTO obterContaAcessoEditando(String nome) {
		ResultadoCoordenacaoEdicaoDTO resultadoCoordenacaoEdicaoDto = new ResultadoCoordenacaoEdicaoDTO();

		ContaAcesso contaAcesso = obterBloqueador(nome,
				coordenadorEdicaoGeral);

		ContaAcessoDTO contaAcessoDto = new FabricaContaAcesso()
				.converterParaDTO(contaAcesso);

		resultadoCoordenacaoEdicaoDto.efetuadoComSucesso(contaAcessoDto);
		return resultadoCoordenacaoEdicaoDto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ContaAcesso obterBloqueador(String chaveBloqueio,
			Bloqueador bloqueador) {
		return (ContaAcesso) bloqueador.obterContaAcessoEditando(chaveBloqueio);
	}
	
	public ResultadoListagemGeralBloqueadosDTO obterListagemBloqueados() {
		ResultadoListagemGeralBloqueadosDTO resultadoListagemBloqueadosDTO = new ResultadoListagemGeralBloqueadosDTO();
		try {
			Map<String, ContaAcesso> geralContaAcessoBloqueados = coordenadorEdicaoGeral
					.obterObjetosBloqueados();

			List<GeralContaAcessoBloqueadoDTO> listagemGeralContaAcessoBloequadoDTO = new ArrayList<>();

			for (String chaveBloqueio : geralContaAcessoBloqueados.keySet()) {
				listagemGeralContaAcessoBloequadoDTO
						.add(new GeralContaAcessoBloqueadoDTO(chaveBloqueio
								.toString(), new FabricaContaAcesso()
								.converterParaDTO(geralContaAcessoBloqueados
										.get(chaveBloqueio))));
			}

			resultadoListagemBloqueadosDTO
					.efetuadoComSucesso(listagemGeralContaAcessoBloequadoDTO);
		} catch (Exception e) {
			logger.error("Erro durante listagem de bloqueados.");
		}
		return resultadoListagemBloqueadosDTO;
	}

	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaXLS(
			TokenDTO tokenDto) {
		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoExtensaoRelatorioDTO = new ResultadoAlteracaoExtensaoRelatorioDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		try {
			contaAcesso.marcarExtensaoDeRelatorioXls();
			repositorioContaAcesso.salvar(contaAcesso);
			resultadoAlteracaoExtensaoRelatorioDTO.efetuadoComSucesso();
		} catch (Exception e) {
			String erro = "Erro durante alteração de extensão de relatório padrão.";
			resultadoAlteracaoExtensaoRelatorioDTO.adicionarErro(erro);
			logger.error(erro + " \nDetalhes:" + e);
		}

		return resultadoAlteracaoExtensaoRelatorioDTO;
	}
	
	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaPDF(
			TokenDTO tokenDto) {
		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoExtensaoRelatorioDTO = new ResultadoAlteracaoExtensaoRelatorioDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		try {
			contaAcesso.marcarExtensaoDeRelatorioPdf();
			repositorioContaAcesso.salvar(contaAcesso);
			resultadoAlteracaoExtensaoRelatorioDTO.efetuadoComSucesso();
		} catch (Exception e) {
			String erro = "Erro durante alteração de extensão de relatório padrão.";
			resultadoAlteracaoExtensaoRelatorioDTO.adicionarErro(erro);
			logger.error(erro + " \nDetalhes:" + e);
		}

		return resultadoAlteracaoExtensaoRelatorioDTO;
	}

	public ResultadoAlteracaoSenhaDTO alterarSenha(CredencialDTO credencialDto,
			TokenDTO tokenDto) {
		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = new ResultadoAlteracaoSenhaDTO();
		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (credencialDto.novaSenhaValida()) {
			contaAcesso.setSenha(credencialDto.getSenha());
			repositorioContaAcesso.salvar(contaAcesso);
			resultadoAlteracaoSenhaDto.efetuadoComSucesso();
			logger.info(contaAcesso + " efetuou Alteração de Senha.");
		} else {
			resultadoAlteracaoSenhaDto.adicionarErro(MENSAGEM_SENHA_DIFERENTE);
		}
		return resultadoAlteracaoSenhaDto;
	}

	public ResultadoListagemContaAcessoDTO pesquisarContaAcessoPor(
			EspecificacaoPesquisaContaAcessoDTO especificacao) {
		ResultadoListagemContaAcessoDTO resultado = new ResultadoListagemContaAcessoDTO();
		try {
			List<ContaAcesso> contaAcesso = new ArrayList<>();
			if (especificacao.existeChave(ChavePesquisaDTO.TODOS_CONTAS_ACESSO)) {
				contaAcesso = repositorioContaAcesso.obterTodos();
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(contaAcesso, new FabricaContaAcesso(),
					resultado);
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Contas Acesso.";
			resultado.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultado;
	}

	public ResultadoListagemPerfilDTO obterListagemPerfil() {
		return (ResultadoListagemPerfilDTO) obterListagem(
				repositorioPerfil.obterTodos(), new FabricaPerfil(), "Perfil",
				new ResultadoListagemPerfilDTO());
	}

	public ResultadoEdicaoContaAcessoDTO editarContaAcesso(
			ContaAcessoDTO contaAcessoDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoContaAcessoEdicao(
				new FabricaContaAcesso(), repositorioContaAcesso, contaAcessoDto);
		return (ResultadoEdicaoContaAcessoDTO) verificarPermissaoEProcessar(
				operacao, Permissao.CONTA_ACESSO_EDICAO,
				new ResultadoEdicaoContaAcessoDTO(), tokenDto);
	}

	public ResultadoListagemPerfilDTO pesquisarPerfilPor(
			EspecificacaoPesquisaPerfilDTO especificacao) {
		ResultadoListagemPerfilDTO resultado = new ResultadoListagemPerfilDTO();
		try {
			List<Perfil> perfis = new ArrayList<>();
			if (especificacao.existeChave(ChavePesquisaDTO.TODOS_PERFIS)) {
				perfis = repositorioPerfil.obterTodos();
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(perfis, new FabricaPerfil(), resultado);
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por Perfis.";
			resultado.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultado;
	}

	public ResultadoListagemPermissaoDTO obterListagemPermissao() {
		return (ResultadoListagemPermissaoDTO) obterListagem(
				Arrays.asList(Permissao.values()), new FabricaPermissao(),
				"Permissão", new ResultadoListagemPermissaoDTO());
	}

	public ResultadoEdicaoPerfilDTO editarPerfil(PerfilDTO perfilDto,
			TokenDTO tokenDto) {
			OperacaoFachada operacao = new OperacaoPerfilEdicao(
					repositorioPerfil, perfilDto);
			return (ResultadoEdicaoPerfilDTO) verificarPermissaoEProcessar(
					operacao, Permissao.PERFIL_EDICAO,
					new ResultadoEdicaoPerfilDTO(), tokenDto);
	}

	public ResultadoOperacaoFiltroGrupoDTO ativarFiltroGrupo(TokenDTO tokenDto) {
		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDTO = new ResultadoOperacaoFiltroGrupoDTO();
		try {
			ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
			contaAcesso.ativarFiltroGrupo();
			resultadoOperacaoFiltroGrupoDTO = efetuarAlteracao(contaAcesso,
					"Ativação de filtro de Grupo realizada com sucesso.");
		} catch (Exception e) {
			logger.fatal("Erro durante operação de filtro de Grupo.. \nDetalhes: "
					+ e);
		}
		return resultadoOperacaoFiltroGrupoDTO;
	}
	
	public ResultadoOperacaoFiltroGrupoDTO desativarFiltroGrupo(
			TokenDTO tokenDto) {
		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDTO = new ResultadoOperacaoFiltroGrupoDTO();
		try {
			ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
			contaAcesso.desativarFiltroGrupo();
			resultadoOperacaoFiltroGrupoDTO = efetuarAlteracao(contaAcesso,
					"Desativação de filtro de Grupo realizada com sucesso.");
		} catch (Exception e) {
			logger.fatal("Erro durante operação de filtro de Grupo.. \nDetalhes: "
					+ e);
		}
		return resultadoOperacaoFiltroGrupoDTO;
	}
	
	private ResultadoOperacaoFiltroGrupoDTO efetuarAlteracao(ContaAcesso contaAcesso, String mensagem){
		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDto = new ResultadoOperacaoFiltroGrupoDTO();
		try {
			repositorioContaAcesso.salvar(contaAcesso);
			resultadoOperacaoFiltroGrupoDto.adicionarMensagem(mensagem);
			resultadoOperacaoFiltroGrupoDto.efetuadoComSucesso();
		} catch (Exception e) {
			String erro = "";
			resultadoOperacaoFiltroGrupoDto.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultadoOperacaoFiltroGrupoDto;
	}

	public boolean filtroEstaAtivado(TokenDTO tokenDto) {
		boolean retorno = false;
		try {
			ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
			retorno = contaAcesso.isFiltroGrupoAtivado();
		} catch (Exception e) {
			logger.error("Erro durante obtenão de Conta Acesso para verificar filtro do Grupo. \nDetalhes: "
					+ e);
		}
		return retorno;
	}

	public ResultadoListagemPermissaoDTO obterPermissoes(TokenDTO tokenDto) {
		ContaAcesso contaAcesso = repositorioContaAcesso.obterPorToken(tokenDto
				.getToken());
		List<PermissaoDTO> permissoes = new FabricaPermissao()
				.converterParaDTO(contaAcesso.getPerfil().getPermissoes());
		ResultadoListagemPermissaoDTO resultadoListagemPermissaoDTO = new ResultadoListagemPermissaoDTO();
		resultadoListagemPermissaoDTO.efetuadoComSucesso(permissoes);
		return resultadoListagemPermissaoDTO;
	}

	public void gravarTela(byte[] tela) {
		try {
			File imagem = new File(
					new Configuracao()
					.obterConfiguracao(Configuracao.DIRETORIO_TELAS_CAPTURADAS)
					+ DataHoraUtils.obterDataHoraMinutosMilesegundosAtual() + ".jpg");
			
			FileUtils.writeByteArrayToFile(imagem, tela);
		} catch (Exception ex) {
			logger.fatal("Erro durante armazenamento de imagem de tela. Detalhes: "
					+ ex);
		}
	}
}

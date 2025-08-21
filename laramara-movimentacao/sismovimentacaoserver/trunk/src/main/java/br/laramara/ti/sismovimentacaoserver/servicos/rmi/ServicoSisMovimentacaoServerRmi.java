package br.laramara.ti.sismovimentacaoserver.servicos.rmi;

import java.rmi.RemoteException;

import javax.inject.Inject;

import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoAlteracaoSenhaDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.EspecificacaoPesquisaMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoEdicaoMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemAbdbDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemFibraDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemMovimentacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemPapelDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemStatusDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.ResultadoListagemTextoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.StatusDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.EspecificacaoPesquisaContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.EspecificacaoPesquisaPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.GeralContaAcessoBloqueadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.PerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAlteracaoExtensaoRelatorioDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoCoordenacaoEdicaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoEdicaoContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoEdicaoPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemContaAcessoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemGeralBloqueadosDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPerfilDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoListagemPermissaoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.ResultadoOperacaoFiltroGrupoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sismovimentacaocommons.servicos.ServicoSisMovimentacaoServer;
import br.laramara.ti.sismovimentacaoserver.fachadas.FachadaGrupo;
import br.laramara.ti.sismovimentacaoserver.fachadas.FachadaMovimentacao;
import br.laramara.ti.sismovimentacaoserver.fachadas.FachadaSeguranca;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

public class ServicoSisMovimentacaoServerRmi implements ServicoSisMovimentacaoServer {
	
	@Inject
	private FachadaGrupo fachadaGrupo;
	
	@Inject
	private FachadaMovimentacao fachadaMovimentacao;
	
	@Inject
	private FachadaSeguranca fachadaSeguranca;
	
	public ServicoSisMovimentacaoServerRmi() {
	}

	@Override
	public ResultadoAutenticacaoDTO autenticarLogin(CredencialDTO credencialDto)
			throws RemoteException {
		return fachadaSeguranca.autenticarLogin(credencialDto);
	}

	@Override
	public ResultadoListagemContaAcessoDTO pesquisarContaAcessoPor(
			EspecificacaoPesquisaContaAcessoDTO especificacao)
			throws RemoteException {
		return fachadaSeguranca.pesquisarContaAcessoPor(especificacao);
	}
	
	@Override
	public ResultadoEdicaoContaAcessoDTO editarContaAcesso(
			ContaAcessoDTO contaAcessoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSeguranca.editarContaAcesso(contaAcessoDto, tokenDto);
	}
	
	@Override
	public ResultadoListagemPerfilDTO obterListagemPerfil()
			throws RemoteException {
		return fachadaSeguranca.obterListagemPerfil();
	}
	
	@Override
	public ResultadoListagemPermissaoDTO obterPermissoes(TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSeguranca.obterPermissoes(tokenDto);
	}

	@Override
	public boolean possuiAutorizacao(TokenDTO tokenDto, String funcionalidade)
			throws RemoteException {
		return fachadaSeguranca.possuiAutorizacao(tokenDto, funcionalidade);
	}

	
	@Override
	public void bloquearEdicao(String nome, TokenDTO tokenDto)
			throws RemoteException {
		fachadaSeguranca.bloquearEdicao(nome, tokenDto);
	}

	@Override
	public boolean estaBloqueadoParaEdicao(String nome, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSeguranca.estaBloqueadoParaEdicao(nome, tokenDto);
	}

	@Override
	public void desbloquearEdicaoGeral(GeralContaAcessoBloqueadoDTO geralBloqueadoDto, TokenDTO tokenDto)
			throws RemoteException {
		fachadaSeguranca.desbloquearEdicaoGeral(geralBloqueadoDto, tokenDto);
	}
	
	@Override
	public void desbloquearEdicao(String nome, TokenDTO tokenDto)
			throws RemoteException {
		fachadaSeguranca.desbloquearEdicao(nome, tokenDto);
	}

	@Override
	public ResultadoCoordenacaoEdicaoDTO obterContaAcessoEditando(
			String nome) throws RemoteException {
		return fachadaSeguranca.obterContaAcessoEditando(nome);
	}

	@Override
	public ResultadoListagemGeralBloqueadosDTO obterListagemBloqueados()
			throws RemoteException {
		return fachadaSeguranca.obterListagemBloqueados();
	}

	@Override
	public void finalizar() throws RemoteException {
		Registro.finalizarContexto();
	}
	
	@Override
	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaXLS(
			TokenDTO tokenDto) throws RemoteException {
		return fachadaSeguranca.alterarExtensaoRelatorioParaXLS(tokenDto);
	}
	
	@Override
	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaPDF(
			TokenDTO tokenDto) throws RemoteException {
		return fachadaSeguranca.alterarExtensaoRelatorioParaPDF(tokenDto);
	}

	@Override
	public ResultadoAlteracaoSenhaDTO alterarSenha(CredencialDTO credencialDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaSeguranca.alterarSenha(credencialDto, tokenDto);
	}

	@Override
	public ResultadoListagemProfissionalDTO obterListagemTodosProfissionais()
			throws RemoteException {
		return fachadaGrupo.obterListagemTodosProfissionais();
	}

	@Override
	public ResultadoListagemPerfilDTO pesquisarPerfilPor(
			EspecificacaoPesquisaPerfilDTO especificacao)
			throws RemoteException {
		return fachadaSeguranca.pesquisarPerfilPor(especificacao);
	}

	@Override
	public ResultadoListagemPermissaoDTO obterListagemPermissao()
			throws RemoteException {
		return fachadaSeguranca.obterListagemPermissao();
	}

	@Override
	public ResultadoEdicaoPerfilDTO editarPerfil(PerfilDTO perfilDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaSeguranca.editarPerfil(perfilDto, tokenDto);
	}

	@Override
	public ResultadoOperacaoFiltroGrupoDTO ativarFiltroGrupo(TokenDTO tokenDto) throws RemoteException {
		return fachadaSeguranca.ativarFiltroGrupo(tokenDto);
	}

	@Override
	public ResultadoOperacaoFiltroGrupoDTO desativarFiltroGrupo(TokenDTO tokenDto) throws RemoteException {
		return fachadaSeguranca.desativarFiltroGrupo(tokenDto);
	}
	
	public boolean filtroEstaAtivado(TokenDTO tokenDto) throws RemoteException{
		return fachadaSeguranca.filtroEstaAtivado(tokenDto);
	}

	@Override
	public void gravarTela(byte[] tela) throws RemoteException {
		fachadaSeguranca.gravarTela(tela);
	}

	@Override
	public ResultadoListagemMovimentacaoDTO pesquisarMovimentacaoPor(
			EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDTO)
			throws RemoteException {
		return fachadaMovimentacao.obterListagemMovimentacao(especificacaoPesquisaMovimentacaoDTO);
	}

	@Override
	public ResultadoListagemSimNaoDTO obterListagemSimNao()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemSimNao();
	}

	@Override
	public ResultadoListagemFibraDTO obterListagemFibra()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemFibra();
	}

	@Override
	public ResultadoListagemPapelDTO obterListagemPapel()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemPapel();
	}

	@Override
	public ResultadoListagemAbdbDTO obterListagemAbdb() throws RemoteException {
		return fachadaMovimentacao.obterListagemAbdb();
	}

	@Override
	public ResultadoEdicaoMovimentacaoDTO editarMovimentacao(
			MovimentacaoDTO movimentacaoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaMovimentacao.editarMovimentacao(movimentacaoDto, tokenDto);
	}

	@Override
	public ResultadoListagemTextoDTO obterListagemCliente()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemCliente();
	}
	
	@Override
	public ResultadoListagemTextoDTO obterListagemBobina()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemBobina();
	}

	@Override
	public ResultadoListagemTextoDTO obterListagemPlanaPapel() throws RemoteException {
		return fachadaMovimentacao.obterListagemPlanaPapel();
	}

	@Override
	public ResultadoListagemTextoDTO obterListagemDescricao()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemDescricao();
	}

	@Override
	public ResultadoListagemTextoDTO obterListagemCor() throws RemoteException {
		return fachadaMovimentacao.obterListagemCor();
	}

	@Override
	public ResultadoListagemTextoDTO obterListagemDescricaoProduto()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemDescricaoProduto();
	}

	@Override
	public ResultadoListagemStatusDTO obterListagemStatus()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemStatus();
	}

	@Override
	public ResultadoEdicaoMovimentacaoDTO editarStatusMovimentacao(
			MovimentacaoDTO movimentacaoDto, StatusDTO status, String data,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaMovimentacao.editarStatusMovimentacao(movimentacaoDto, status, data, tokenDto);
	}

	@Override
	public ResultadoEdicaoMovimentacaoDTO avancarStatusMovimentacao(
			MovimentacaoDTO movimentacaoDto, String dataHora, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaMovimentacao.avancarStatusMovimentacao(movimentacaoDto, dataHora, tokenDto);
	}

	@Override
	public String obterHistoricoLegado(Long id) throws RemoteException {
		return fachadaMovimentacao.obterHistoricoLegado(id);
	}

	@Override
	public ResultadoEdicaoMovimentacaoDTO incluirMovimentacao(String data,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaMovimentacao.inclurirMovimentacao(data, tokenDto);
	}

	@Override
	public ResultadoListagemTextoDTO obterListagemTipoProva()
			throws RemoteException {
		return fachadaMovimentacao.obterListagemTipoProva();
	}
}
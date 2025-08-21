package br.laramara.ti.sismovimentacaocommons.servicos;

import java.rmi.Remote;
import java.rmi.RemoteException;

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

public interface ServicoSisMovimentacaoServer extends Remote {

	public void gravarTela(byte[] tela) throws RemoteException;

	public ResultadoAutenticacaoDTO autenticarLogin(CredencialDTO credencial)
			throws RemoteException;

	public ResultadoListagemContaAcessoDTO pesquisarContaAcessoPor(
			EspecificacaoPesquisaContaAcessoDTO especificacao)
			throws RemoteException;

	public ResultadoEdicaoContaAcessoDTO editarContaAcesso(
			ContaAcessoDTO contaAcessoDto, TokenDTO tokenDto)
			throws RemoteException;

	public boolean filtroEstaAtivado(TokenDTO tokenDto) throws RemoteException;

	public ResultadoListagemPerfilDTO obterListagemPerfil()
			throws RemoteException;

	public ResultadoListagemPermissaoDTO obterPermissoes(TokenDTO tokenDto)
			throws RemoteException;

	public boolean possuiAutorizacao(TokenDTO tokenDto, String funcionalidade)
			throws RemoteException;

	public void bloquearEdicao(String nome, TokenDTO tokenDto)
			throws RemoteException;

	public boolean estaBloqueadoParaEdicao(String nome, TokenDTO tokenDto)
			throws RemoteException;

	public void desbloquearEdicaoGeral(
			GeralContaAcessoBloqueadoDTO geralBloqueadoDto, TokenDTO tokenDto)
			throws RemoteException;

	public void desbloquearEdicao(String objetoDto, TokenDTO tokenDto)
			throws RemoteException;

	public ResultadoOperacaoFiltroGrupoDTO ativarFiltroGrupo(TokenDTO tokenDto)
			throws RemoteException;

	public ResultadoOperacaoFiltroGrupoDTO desativarFiltroGrupo(
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoCoordenacaoEdicaoDTO obterContaAcessoEditando(String nome)
			throws RemoteException;

	public ResultadoListagemGeralBloqueadosDTO obterListagemBloqueados()
			throws RemoteException;

	public void finalizar() throws RemoteException;

	public ResultadoListagemProfissionalDTO obterListagemTodosProfissionais()
			throws RemoteException;

	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaXLS(
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaPDF(
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoAlteracaoSenhaDTO alterarSenha(CredencialDTO credencialDto,
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoListagemPerfilDTO pesquisarPerfilPor(
			EspecificacaoPesquisaPerfilDTO especificacao)
			throws RemoteException;

	public ResultadoListagemPermissaoDTO obterListagemPermissao()
			throws RemoteException;
	
	public ResultadoListagemStatusDTO obterListagemStatus()
			throws RemoteException;

	public ResultadoEdicaoPerfilDTO editarPerfil(PerfilDTO perfilDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoMovimentacaoDTO editarMovimentacao(MovimentacaoDTO movimentacaoDto,
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoEdicaoMovimentacaoDTO incluirMovimentacao(String data,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoMovimentacaoDTO editarStatusMovimentacao(
			MovimentacaoDTO movimentacaoDto, StatusDTO status, String data,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoMovimentacaoDTO avancarStatusMovimentacao(
			MovimentacaoDTO movimentacaoDto, String dataHora, TokenDTO tokenDto)
			throws RemoteException;
		
	public ResultadoListagemMovimentacaoDTO pesquisarMovimentacaoPor(
			EspecificacaoPesquisaMovimentacaoDTO especificacaoPesquisaMovimentacaoDTO)
			throws RemoteException;
	
	public ResultadoListagemSimNaoDTO obterListagemSimNao() throws RemoteException;
	
	public ResultadoListagemFibraDTO obterListagemFibra() throws RemoteException;
	
	public ResultadoListagemPapelDTO obterListagemPapel() throws RemoteException;
	
	public ResultadoListagemAbdbDTO obterListagemAbdb() throws RemoteException;
	
	public ResultadoListagemTextoDTO obterListagemCliente() throws RemoteException;

	public ResultadoListagemTextoDTO obterListagemDescricao() throws RemoteException;
	
	public ResultadoListagemTextoDTO obterListagemBobina() throws RemoteException;
	
	public ResultadoListagemTextoDTO obterListagemPlanaPapel() throws RemoteException;
	
	public ResultadoListagemTextoDTO obterListagemTipoProva() throws RemoteException;

	public ResultadoListagemTextoDTO obterListagemCor() throws RemoteException;
	
	public ResultadoListagemTextoDTO obterListagemDescricaoProduto() throws RemoteException;
	
	public String obterHistoricoLegado(Long id) throws RemoteException;
}
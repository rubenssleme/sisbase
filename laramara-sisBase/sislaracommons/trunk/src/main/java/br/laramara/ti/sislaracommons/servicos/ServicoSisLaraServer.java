package br.laramara.ti.sislaracommons.servicos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoAlteracaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemArquivoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoValidacaoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.AgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoAssociacaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoGeracaoCopiaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.EspecificacaoPesquisaAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.MotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoEdicaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoGeracaoAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemMotivoCancelamentoDTO;
import br.laramara.ti.sislaracommons.dtos.agenda.ResultadoListagemStatusAgendamentoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoEdicaoAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemOpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoValidacaoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoValidacaoParticipacaoDetalhadaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.TipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.EspecificacaoPesquisaContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoReciboDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemMotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemStatusContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoCaptacaoCartelaDeSelosDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoEdicaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoEdicaoProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemFilialDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemNomeDocumentoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoListagemStatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoProrrogacaoCartelaDeSelosDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ResultadoReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.StatusDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemAreaFormacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemDreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemEscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemSituacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemTipoApoioDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemTipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoValidacaoInformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EspecificacaoPesquisaEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoEdicaoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemStatusEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.ResultadoListagemTipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoListagemParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoValidacaoFamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoPesquisaGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoAtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoEdicaoModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoGeracaoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemDiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemFrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemLocalAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemNomeCompletoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemStatusRelacaoUsuarioModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemTipificacaoServicoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoListagemTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoAtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoDiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoLoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoModuloRelacaoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoProgramacaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.StatusRelacaoComModuloDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.EspecificacaoPesquisaInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoEdicaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemClassificacaoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.ResultadoListagemTipoInstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.pendencia.PendenciaDTO;
import br.laramara.ti.sislaracommons.dtos.pendencia.ResultadoListagemPendenciaDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.EspecificacaoPesquisaPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.ResultadoEdicaoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.ResultadoListagemPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.ResultadoEdicaoRetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.EspecificacaoPesquisaContaAcessoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.EspecificacaoPesquisaPerfilDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.GeralContaAcessoBloqueadoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.PerfilDTO;
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
import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemFinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemStatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemCargoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemLocalTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoValidacaoInformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoConsultaCidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoEdicaoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemClassificacaoSocialDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemCondicaoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemConvenioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemExpectativaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGeneroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGrupoEtnicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemHabitacaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemInfraestruturaBasicaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemItensCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemNecessidadeDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemOrigemEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemPaisDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemRecursoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemRedeEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemServicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSituacaoGuardaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSituacaoHabitacionalDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoCertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoConstrucaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoLeituraDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemZonaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoEncaminhamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public interface ServicoSisLaraServer extends Remote {
	
	public boolean exibirAvisoDeAtualizacao(TokenDTO tokenDto) throws RemoteException;
		
	public void confirmarLeituraDeAvisoDeAtualizacao(TokenDTO tokenDto) throws RemoteException;
	
	public boolean ativarDesativarAvisoDeAtualizacao() throws RemoteException;
	
	public void gravarTela(byte[] tela) throws RemoteException;
	
	public ResultadoAutenticacaoDTO autenticarUsuarioExterno(CredencialExternaDTO credencialExternaDto)
			throws RemoteException;
	
	public ResultadoAutenticacaoDTO autenticarLogin(CredencialDTO credencial)
			throws RemoteException;
	
	public ResultadoListagemContaAcessoDTO pesquisarContaAcessoPor(
			EspecificacaoPesquisaContaAcessoDTO especificacao)
			throws RemoteException;
	
	public ResultadoEdicaoContaAcessoDTO editarContaAcesso(ContaAcessoDTO contaAcessoDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public boolean filtroEstaAtivado(TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoListagemPerfilDTO obterListagemPerfil() throws RemoteException;
	
	public ResultadoListagemFilialDTO obterListagemFilial() throws RemoteException;
	
	public ResultadoListagemClassificacaoSocialDTO obterListagemClassificacaoSocial()
			throws RemoteException;

	public ResultadoListagemStatusDTO obterListagemStatus()
			throws RemoteException;
	
	public ResultadoListagemStatusDTO obterListagemStatusFamiliar()
			throws RemoteException;

	public ResultadoListagemTipoLeituraDTO obterListagemTipoLeitura()
			throws RemoteException;
	
	public ResultadoListagemGeneroDTO obterListagemGenero() throws RemoteException;
	
	public ResultadoListagemStatusBeneficioDTO obterListagemStatusBeneficio()
			throws RemoteException;
	
	public ResultadoListagemMotivoDesativacaoDTO obterListagemMotivoDesativacao() throws RemoteException;
	
	public ResultadoListagemEpocaIncidenciaDTO obterListagemEpocaIncidencia()
			throws RemoteException;

	public ResultadoListagemUfDTO obterListagemUf() throws RemoteException;

	public ResultadoListagemMunicipioDTO obterListagemMunicipio(UfDTO ufDto)
			throws RemoteException;

	public ResultadoListagemEstadoCivilDTO obterListagemEstadoCivil()
			throws RemoteException;

	public ResultadoListagemZonaDTO obterListagemZona() throws RemoteException;

	public ResultadoListagemAreaFormacaoDTO obterListagemAreaFormacao() throws RemoteException;
	
	public ResultadoListagemSetorDTO obterListagemSetor()
			throws RemoteException;

	public ResultadoListagemSimNaoDTO obterListagemSimNao()
			throws RemoteException;

	public ResultadoListagemCargoDTO obterListagemCargo()
			throws RemoteException;

	public ResultadoListagemLocalTrabalhoDTO obterListagemLocalTrabalho()
			throws RemoteException;
	
	public ResultadoListagemPaisDTO obterListagemPais() throws RemoteException;

	public ResultadoListagemInstituicaoDTO obterListagemInstituicao()
			throws RemoteException;

	public ResultadoListagemInstituicaoDTO obterListagemInstituicaoComSRMs()
			throws RemoteException;
	
	public ResultadoListagemInstituicaoDTO obterListagemInstituicaoComSalaRecurso()
			throws RemoteException;
	
	public ResultadoListagemInstituicaoDTO obterListagemInstituicaoComOutrosAEE()
			throws RemoteException;
	
	public ResultadoListagemEscolaridadeDTO obterListagemEscolaridade()
			throws RemoteException;

	public ResultadoListagemSituacaoDTO obterListagemSituacao()
			throws RemoteException;

	public ResultadoListagemServicoDTO obterListagemServico()
			throws RemoteException;
	
	public ResultadoListagemRecursoMoradiaDTO obterListagemRecursoProximoMoradia()
			throws RemoteException;
	
	public ResultadoListagemNecessidadeDTO obterListagemNecessidade()
			throws RemoteException;
	
	public ResultadoListagemExpectativaDTO obterListagemExpectativa()
			throws RemoteException;
	
	public ResultadoListagemCondicaoMoradiaDTO obterListagemCondicaoMoradia()
			throws RemoteException;
	
	public ResultadoListagemSituacaoHabitacionalDTO obterListagemSituacaoHabitacional()
			throws RemoteException;
	
	public ResultadoListagemHabitacaoDTO obterListagemHabitacao()
			throws RemoteException;
	
	public ResultadoListagemTipoConstrucaoDTO obterListagemTipoConstrucao()
			throws RemoteException;
	
	public ResultadoListagemTipoAcaoCondutaDTO obterListagemTipoAcaoConduta()
			throws RemoteException;
	
	public ResultadoListagemInfraestruturaBasicaDTO obterListagemInfraestruturaBasica()
			throws RemoteException;
	
	public ResultadoListagemPeriodoDTO obterListagemPeriodo()
			throws RemoteException;
	
	public ResultadoListagemTipoEspecialidadeDTO obterListagemTipoEspecialidade()
			throws RemoteException;
	
	public ResultadoListagemTipoApoioDTO obterListagemTipoApoio()
			throws RemoteException;
	
	public ResultadoListagemDreCefaiDTO obterListagemDreCefai()
		throws RemoteException;
	
	public ResultadoListagemDiretoriaEnsinoDTO obterListagemDiretoriaEnsino()
			throws RemoteException;

	public ResultadoListagemPermissaoDTO obterPermissoes(TokenDTO tokenDto)
			throws RemoteException;

	public boolean possuiAutorizacao(TokenDTO tokenDto, String funcionalidade)
			throws RemoteException;

	public ResultadoEdicaoUsuarioDTO editarUsuario(UsuarioDTO usuarioDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoSolicitacaoRelatorioDTO editarSolicitacaoRelatorio(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEncaminhamentoRecepcao(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEntrega(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarCancelamento(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEmissaoProfissional(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException;

	public ResultadoListagemUsuarioDTO pesquisarUsuarioPor(
			EspecificacaoPesquisaUsuarioDTO especificacao)
			throws RemoteException;

	public ResultadoListagemTipoInstituicaoDTO obterListagemTipoInstituicao()
			throws RemoteException;

	public ResultadoListagemClassificacaoInstituicaoDTO obterListagemClassificacaoInstituicao()
			throws RemoteException;

	public ResultadoEdicaoInstituicaoDTO editarInstituicao(
			InstituicaoDTO instituicaoDto, TokenDTO tokenDto)
			throws RemoteException;

	public ResultadoListagemInstituicaoDTO pesquisarInstituicaoPor(
			EspecificacaoPesquisaInstituicaoDTO especificacao)
			throws RemoteException;

	public ResultadoListagemPreCadastroDTO pesquisarPreCadastroPor(
			EspecificacaoPesquisaPreCadastroDTO especificacao)
			throws RemoteException;

	public ResultadoEdicaoPreCadastroDTO editarPreCadastro(
			PreCadastroDTO preCadastroDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemTipoAtendimentoDTO obterListagemTipoAtendimento()
			throws RemoteException;

	public ResultadoValidacaoFamiliarDTO validarFamiliar(FamiliarDTO familiarDto)
			throws RemoteException;
	
	public ResultadoValidacaoEncaminhamentoDTO validarEncaminhamento(EncaminhamentoDTO encaminhamentoDto)
			throws RemoteException;
	
	public ResultadoValidacaoCustoDTO validarCusto(CustoDTO custoDto)
			throws RemoteException;
	
	public ResultadoValidacaoAcaoCondutaDTO validarAcaoConduta(AcaoCondutaDTO acaoCondutaDto)
			throws RemoteException;
	
	public ResultadoValidacaoInformacaoEducacionalDTO validarInformacaoEducacional(
			InformacaoEducacionalDTO informacaoEducacionalDto) throws RemoteException;
	
	public ResultadoListagemParentescoDTO obterListagemParentesco()
			throws RemoteException;
	
	public ResultadoListagemSituacaoGuardaDTO obterListagemSituacaoGuarda()
			throws RemoteException;
	
	public ResultadoListagemBeneficioDTO obterListagemBeneficio()
			throws RemoteException;

	public ResultadoListagemConvenioDTO obterListagemConvenio()
			throws RemoteException;
	
	public ResultadoListagemTipoTelefoneDTO obterListagemTipoTelefone()
			throws RemoteException;
	
	public ResultadoValidacaoTelefoneDTO validarTelefone(TelefoneDTO telefoneDto)
			throws RemoteException;

	public ResultadoValidacaoCertidaoDTO validarCertidao(CertidaoDTO certidaoDto)
			throws RemoteException;
		
	public ResultadoValidacaoInformacaoTrabalhoCompletaDTO validarInformacaoTrabalhoCompleta(
			InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto)
			throws RemoteException;
		
	public ResultadoListagemGrupoEtnicoDTO obterListagemGrupoEtnico()
			throws RemoteException;
	
	public ResultadoValidacaoPeriodoBeneficioDTO validarPeriodoBeneficio(PeriodoBeneficioDTO periodoBeneficioDto)
		throws RemoteException;
	
	public ResultadoValidacaoDiaSemanaEHorarioDTO validarDiaSemanaEHorario(DiaSemanaEHorarioDTO diaSemanaEHorarioDTO)
			throws RemoteException;
	
	public ResultadoValidacaoPeriodoDeficienciaDTO validarPeriodoDeficiencia(PeriodoDeficienciaDTO periodoDeficienciaDto)
			throws RemoteException;
	
	public ResultadoValidacaoPeriodoComprometimentoDTO validarPeriodoComprometimento(PeriodoComprometimentoDTO periodoComprometimentoDto)
			throws RemoteException;
	
	public ResultadoValidacaoPeriodoDoencaDTO validarPeriodoDoenca(
			PeriodoDoencaDTO periodoDoencaDto) throws RemoteException;
	
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
		
	public ResultadoCoordenacaoEdicaoDTO obterContaAcessoEditando(
			String nome) throws RemoteException;
	
	public ResultadoListagemGeralBloqueadosDTO obterListagemBloqueados()
			throws RemoteException;
	
	public void finalizar() throws RemoteException;

	public ResultadoListagemLocalAtendimentoDTO obterListagemLocalAtendimento()
			throws RemoteException;
	
	public ResultadoListagemTipificacaoServicoDTO obterListagemTipificacaoServico()
			throws RemoteException;
	
	public ResultadoListagemRecursoDTO obterListagemRecurso()
			throws RemoteException;
	
	public ResultadoListagemProfissionalDTO obterListagemProfissionalAtivos()
			throws RemoteException;
	
	public ResultadoListagemProfissionalDTO obterListagemTodosProfissionais()
			throws RemoteException;
	
	public ResultadoListagemDiaSemanaDTO obterListagemDiaSemana()
			throws RemoteException;
	
	public ResultadoListagemStatusRelacaoUsuarioModuloDTO obterListagemStatusRelacaoUsuarioModulo(
			ModuloPeriodoDTO moduloPeriodoDto) throws RemoteException;

	public ResultadoListagemFrequenciaDTO obterListagemFrequenciaDoUsuario()
			throws RemoteException;
	
	public ResultadoListagemFrequenciaDTO obterListagemFrequenciaDoProfissional()
			throws RemoteException;
	
	public ResultadoListagemGrupoDTO pesquisarGrupoPor(
			EspecificacaoPesquisaGrupoDTO especificacao) throws RemoteException;
	
	public ResultadoListagemNomeCompletoGrupoDTO pesquisarNomeGrupoPor(String nomeGrupo)
			throws RemoteException;

	public ResultadoEdicaoGrupoDTO editarGrupo(GrupoDTO grupoDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoModuloPeriodoDTO editarModuloPeriodo(
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoAtendimentoGrupoDTO editarAtendimentoGrupo(ModuloPeriodoDTO moduloPeriodoDTO,
			AtendimentoGrupoDTO atendimentoGrupoDto, TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoValidacaoModuloPeriodoDTO validarModuloPeriodo(
			ModuloPeriodoDTO moduloPeriodoDto) throws RemoteException;
	
	public ResultadoValidacaoLoteRecursoDTO validarLoteRecurso(
			LoteRecursoDTO doacaoRecursoDto) throws RemoteException;
	
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(
			ModuloRelacaoBaseDTO moduloRelacaoBaseDto) throws RemoteException;
	
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(
			List<? extends ModuloRelacaoBaseDTO> modulosRelacaoBaseDto,
			ModuloRelacaoBaseDTO moduloRelacaoBaseDto) throws RemoteException;
	
	public ResultadoValidacaoProgramacaoDTO validarProgramacao(ProgramacaoDTO programacaoDto)
		throws RemoteException;
		
	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimentoAuxiliarBase(
			AtendimentoBaseDTO atendimentoBaseDTO) throws RemoteException;
	
	public ResultadoGeracaoAtendimentoDTO gerarAtendimentos(
			EspecificacaoGeracaoAtendimentoDTO especificacaoGeracaoAtendimentosDTO,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoGeracaoAtendimentoDTO gerarAtendimentos(PendenciaDTO pendenciaDTO,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoAtendimentoGrupoDTO cancelarAtendimentoGrupo(
			AtendimentoGrupoDTO atendimentoGropoDto, TokenDTO tokenDto)
			throws RemoteException;

	public ArquivoDTO gerarRelatorioFolhaDeRosto(Long prontuario,
			TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
			String nomeGrupo, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioFrequenciasDeAtendimentoGlobaisPorUsuarioNoPeriodo(String prontuarios, String dataInicio,
			String dataTermino, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioProgramacaoDoGrupoParaFamilia(
			String dataInicio, String dataTermino, GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto)
			throws RemoteException;

	public ArquivoDTO gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(
			GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioAtendimentoDeUsuarioNoGrupoDetalhado(
			GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			UsuarioDTO usuarioDto, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioCargaHorariaDeUsuarioPorGrupo(
			String nomeGrupo, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioSolicitacaoRelatorio(Long protocolo,
			TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioGeracaoAtendimentosIndividuaisEGrupo(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioVagasEmGruposAtivos(TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(
			Long prontuario, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorTipoAtendimento(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado(
			Long prontuario, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadeUsuarioOrdenadoPorIdade(
			TokenDTO tokenDto) throws RemoteException;	
	
	public ArquivoDTO gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(
			TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(
			Long idadeInicio, Long idadeTermino, TokenDTO tokenDto)
			throws RemoteException;
		
	public ArquivoDTO gerarRelatorioRetiradaProntuariosNoAgendamento(
			String data, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendimentosIndividuais(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorTiposVinculos(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendimentosParaPrefeitura(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodasRetiradasPendentes(TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodasFrequenciasOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodasRetiradasPorProntuario(
			Long prontuario, TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(
			TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioContatosIntegrantesGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO token) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioFrequenciaPorGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, boolean paisagem, TokenDTO token)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioPorcentagensFrequenciaPorGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO token)
			throws RemoteException;

	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaXLS(
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoAlteracaoExtensaoRelatorioDTO alterarExtensaoRelatorioParaPDF(
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoAlteracaoSenhaDTO alterarSenha(CredencialDTO credencialDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoListagemProntuarioEscaneadoDTO obterListagemProntuariosEscaneados(
			UsuarioDTO usuarioDto) throws RemoteException;
	
	public ProntuarioEscaneadoDTO obterProntuarioEscaneado(
			UsuarioDTO usuarioDto, ProntuarioEscaneadoDTO prontuarioEscaneadoDto) throws RemoteException;
	
	public ResultadoListagemDeficienciaDTO obterListagemDeficiencia()
			throws RemoteException;
	
	public ResultadoListagemComprometimentoDTO obterListagemComprometimento()
			throws RemoteException;
	
	public ResultadoListagemDoencaDTO obterListagemDoenca()
			throws RemoteException;
	
	public ResultadoGeracaoAgendamentoDTO gerarAgendamento(
			EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoAgendamentoDTO liberarAgendamentoCancelando(
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoAgendamentoDTO copiarAgendamento(
			EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO,
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemStatusDTO obterListagemStatusDisponiveisParaAgendamento()
			throws RemoteException;
	
	public ResultadoListagemStatusDTO obterListagemStatusDisponiveisParaAtendimentoIndividual() throws RemoteException;

	public ResultadoListagemStatusAgendamentoDTO obterListagemStatusAgendamento()
			throws RemoteException;
	
	public ResultadoListagemStatusContribuinteDTO obterListagemStatusContribuinte()
			throws RemoteException;

	public ResultadoListagemAgendamentoDTO obterListagemAgendamento(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto)
			throws RemoteException;
	
	public ResultadoListagemAgendamentoDTO obterListagemAgendamentoDisponiveis(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto)
			throws RemoteException;
	
	public ResultadoEdicaoAgendamentoDTO editarAgendamento(
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoEdicaoAgendamentoDTO cancelarAgendamento(
			AgendamentoDTO agendamentoDTO, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemMotivoCancelamentoDTO obterListagemMotivoCancelamentoAgendamento()
			throws RemoteException;
	
	public ResultadoListagemMotivoCancelamentoDTO obterListagemMotivoCancelamentoDemanda()
			throws RemoteException;
	
	public ResultadoListagemTipoCertidaoDTO obterListagemTipoCertidao()
			throws RemoteException;
	
	public ResultadoListagemTipoEsperaDTO obterListagemTipoEsperaTotal()
			throws RemoteException;
	
	public ResultadoListagemTipoEsperaDTO obterListagemTipoEsperaDisponiveisParaInclusao()
			throws RemoteException;
		
	public ResultadoListagemStatusEsperaDTO obterListagemStatusEspera()
			throws RemoteException;

	public ResultadoListagemEsperaDTO obterListagemEspera(
			EspecificacaoPesquisaEsperaDTO especificacao)
			throws RemoteException;
	
	public String obterObservacoesHistoricasEspera(
			InformacaoEssencialDTO informacaoEssencialDto)
			throws RemoteException;
	
	public ResultadoEdicaoEsperaDTO editarEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) throws RemoteException;

	public ResultadoEdicaoEsperaDTO editarEsperaEAssociarAgendamento(
			EspecificacaoAssociacaoAgendamentoDTO especificacaoAssociacaoAgendamentoDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoEsperaDTO cancelarEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoEsperaDTO concluirEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoAtendimentoIndividualDTO editarAtendimentoIndividual(
			AtendimentoIndividualDTO atendimentoIndividualDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemAtendimentoIndividualDTO obterListagemAtendimentoIndividual(
			EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDto)
			throws RemoteException;
	
	public ResultadoEdicaoAtendimentoIndividualDTO copiarAtendimentoIndividual(
			EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto,
			AtendimentoIndividualDTO atendimentoIndividualDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemTipoVinculoDTO obterListagemTipoVinculo()
			throws RemoteException;
	
	public ResultadoListagemOpcaoIntegracaoDTO obterListagemOpcaoIntegracao()
			throws RemoteException;
	
	public ResultadoListagemParticipacaoDTO obterListagemParticipacao()
			throws RemoteException;
	
	public ResultadoListagemPerfilDTO pesquisarPerfilPor(
			EspecificacaoPesquisaPerfilDTO especificacao)
			throws RemoteException;
	
	public ResultadoListagemPermissaoDTO obterListagemPermissao()
			throws RemoteException;
	
	public ResultadoEdicaoPerfilDTO editarPerfil(PerfilDTO perfilDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemDemandaDTO obterListagemDemanda(
			EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDto)
			throws RemoteException;

	public ResultadoListagemProjetoDTO obterListagemProjetoAtivos()
			throws RemoteException;
	
	public ResultadoEdicaoProjetoDTO editarProjeto(ProjetoDTO projetoDto, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemProjetoDTO pesquisarProjetoPor(
			EspecificacaoPesquisaProjetoDTO especificacao) throws RemoteException;

    public ResultadoGeracaoDemandaDTO gerarDemanda(
			EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO,
			TokenDTO tokenDto) throws RemoteException;
    
	public ResultadoListagemStatusDemandaDTO obterListagemStatusDemandaLimitada()
			throws RemoteException;
	
	public ResultadoListagemStatusDemandaDTO obterListagemStatusDemanda()
			throws RemoteException;
	
	public ResultadoEdicaoDemandaDTO mudarStatusDemanda(DemandaDTO demandaDto, StatusDemandaDTO statusDemandaDto,
			MotivoCancelamentoDTO motivoCancelamentoDTO, String obs, TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoConsultaCEP consultarEndereco(String cep)throws RemoteException;

	public ResultadoListagemGrupoDTO obterListagemGrupoAtivo(String nomeGrupo)
			throws RemoteException;
	
	public ResultadoListagemGrupoDTO obterListagemTodosGruposAtivos()
			throws RemoteException;
	
	public ResultadoEdicaoRetiradaDTO efetuarRetirada(RetiradaDTO retiradaDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoEdicaoRetiradaDTO baixarRetirada(RetiradaDTO retiradaDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoListagemProfissionalDTO obterListagemVoluntarioAtivos()
			throws RemoteException;

	public ArquivoDTO gerarRelatorioParticipacaoUsuarioEmGrupos(
			Long prontuario, TokenDTO token) throws RemoteException;

	public ResultadoConsultaCidDTO consultarCid(String codigo)
			throws RemoteException;

	public ArquivoDTO gerarRelatorioComasAtendidosPorIdadeEGenero(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorUF(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorRenda(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorMunicipioSP(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioTodosUsuarioComStatusDeGrupoProvisorioOuAcesso(TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorEscolaridade(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorDeficiencia(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorClassificacaoInstituicao(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorBeneficio(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorVisitaMonitorada(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;
	
	public ResultadoListagemSolicitacaoRelatorioDTO pesquisarSolicitacaoRelatorioPor(
			EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacao)
			throws RemoteException;

	public ResultadoListagemStatusSolicitacaoRelatorioDTO obterListagemStatusEntrega()
			throws RemoteException;

	public ResultadoListagemStatusSolicitacaoRelatorioDTO obterListagemStatusSolicitacaoRelatorio()
			throws RemoteException;
	
	public ResultadoEdicaoAgendamentoDTO reagendarAgendamento(
			AgendamentoDTO agendamentoOrigemDto,
			AgendamentoDTO agendamentoDestinoDto, TokenDTO tokenDto)
			throws RemoteException;

	public ResultadoListagemElaboradorDTO obterListagemElaborador()
			throws RemoteException;

	public ResultadoListagemFinalidadeRelatorioDTO obterListagemFinalidadeRelatorio()
			throws RemoteException;
	
	public ResultadoListagemItensCustoDTO obterListagemItensCustoDoenca()
			throws RemoteException;
		
	public ResultadoListagemItensCustoDTO obterListagemItensCustoDeficiencia()
			throws RemoteException;
	
	public boolean fechamentoAutomaticoClientAtivado() throws RemoteException;

	public ArquivoDTO gerarRelatorioAtendidosSemInformacaoDeficiencia(
			String dataInicio, String dataTermino, TokenDTO token)
			throws RemoteException;
	
	public ArquivoDTO gerarRelatorioMailingVisitaMonitorada(String dataInicio, String dataTermino,
			List<TipoVinculoDTO> tiposVinculosDtos, TokenDTO tokenDTO) throws RemoteException;

	public ResultadoListagemContribuinteDTO pesquisarContribuintePor(
			EspecificacaoPesquisaContribuinteDTO especificacao)
			throws RemoteException;
	
	public ResultadoListagemReciboDTO pesquisarReciboPor(
			EspecificacaoPesquisaReciboDTO especificacao)
			throws RemoteException;
	
	public ResultadoEdicaoContribuinteDTO editarContribuinte(
			ContribuinteDTO contribuinteDTO, TokenDTO tokenDTO)
			throws RemoteException;
	
	public ResultadoEdicaoReciboDTO editarRecibo(
			ReciboDTO reciboDTO, TokenDTO tokenDTO)
			throws RemoteException;
	
	public ResultadoListagemStatusRelacaoUsuarioModuloDTO obterListagemStatusRelacaoUsuarioModuloDisponiveisParaInclusao(GrupoDTO grupoDto)
			throws RemoteException;
	
	public boolean solicitarGeracaoArquivoCobranca(TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoListagemPendenciaDTO obterListagemPendencia(TokenDTO tokenDto) throws RemoteException;
	
	public ArquivoDTO gerarRelatorioQuantidadesAvaliacoesFuncionais(String dataInicio, String dataTermino,
			TokenDTO tokenDto) throws RemoteException;

	public ArquivoDTO gerarRelatorioFluxoAtendimentoCasosNovosDeAvaliacaoFuncional(String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException;

	public ArquivoDTO gerarRelatorioFluxoAtendimentoRetornosDeAvaliacaoFuncional(String dataInicio, String dataTermino,
			TokenDTO tokenDto) throws RemoteException;
	
	public StatusRelacaoComModuloDTO obterStatusRelacaoPadrao(GrupoDTO grupoDto) throws RemoteException;
	
	public ArquivoDTO obterArquivoAtendimentoIndividualDTO(AtendimentoIndividualDTO atendimentoIndividualDTO,
			ArquivoDTO arquivoDTO) throws RemoteException;

	public ArquivoDTO obterArquivoAtendimentoGrupoDTO(AtendimentoGrupoDTO atendimentoGrupoDTO,
			ArquivoDTO arquivoDTO) throws RemoteException;
	
	public ArquivoDTO obterArquivoAtendimentoUsuarioDTO(AtendimentoUsuarioDTO atendimentoUsurioDTO,
			ArquivoDTO arquivoDTO) throws RemoteException;
	
	public ArquivoDTO obterArquivoDocumentoSolicitacaoDoacaoDTO(DemandaDTO demandaDto, ArquivoDTO arquivoDTO)
			throws RemoteException;
	
	public ArquivoDTO obterRelatorioRecibo(Long numeroRecibo, TokenDTO tokenDTO)
			throws RemoteException;
	
	public ArquivoDTO obterArquivoDisponivelDTO(String id, String nome, String tipo) throws RemoteException;
	
	public ResultadoListagemArquivoDisponivelDTO obterListagemArquivoDisponivelDTO(String dadosPesquisa, boolean somenteGrupos) throws RemoteException;

	public ResultadoListagemNomeDocumentoDTO obterListagemNomeDocumento() throws RemoteException;
	
	public ResultadoProrrogacaoCartelaDeSelosDTO prorrogar(DemandaDTO demandaDto, TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoCaptacaoCartelaDeSelosDTO captar(EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDto,
			TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoReciboDTO obterReciboMaisRecentePorCpfCnpj(String cpfCnpj) throws RemoteException;
	
	public ReciboDTO obterReciboDto(String numeroRecibo) throws RemoteException;
	
	public String obterValorTotalPorDeRecurso(RecursoDTO recursoDto, String quantidade) throws RemoteException;
	
	public ResultadoEdicaoProjetoDTO calcularTotais(ProjetoDTO projetoDTO) throws RemoteException;
	
	public ResultadoEdicaoReciboDTO cancelarRecibo(Long numeroRecibo, String motivoDoCancelamento, TokenDTO tokenDto) throws RemoteException;
	
	public ResultadoValidacaoParticipacaoDetalhadaDTO validarParticipacaoDetalhada(
			ParticipacaoDetalhadaDTO participacaoDetalhadaDto) throws RemoteException;

	public String obterResumoIntegracao(String prontuario) throws RemoteException;
	
	public ResultadoListagemOrigemEncaminhamentoDTO obterListagemOrigemEncaminhamento()
			throws RemoteException;
	
	public ResultadoListagemRedeEncaminhamentoDTO obterListagemRedeEncaminhamento()
			throws RemoteException;
}
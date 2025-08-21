package br.laramara.ti.sislaraserver.servicos.rmi;

import java.rmi.RemoteException;
import java.util.List;

import javax.inject.Inject;

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
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoEdicaoAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoVinculoDTO;
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
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentosDTO;
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
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoConsultaCidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoEdicaoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemClassificacaoSocialDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemConvenioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEpocaIncidenciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGeneroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemGrupoEtnicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemItensCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemPaisDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemSituacaoGuardaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoCertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemTipoLeituraDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemZonaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;
import br.laramara.ti.sislaraserver.fachadas.FachadaAgendamento;
import br.laramara.ti.sislaraserver.fachadas.FachadaArquivoDisponivel;
import br.laramara.ti.sislaraserver.fachadas.FachadaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.fachadas.FachadaCEP;
import br.laramara.ti.sislaraserver.fachadas.FachadaContribuinte;
import br.laramara.ti.sislaraserver.fachadas.FachadaDemanda;
import br.laramara.ti.sislaraserver.fachadas.FachadaEspera;
import br.laramara.ti.sislaraserver.fachadas.FachadaGrupo;
import br.laramara.ti.sislaraserver.fachadas.FachadaInstituicao;
import br.laramara.ti.sislaraserver.fachadas.FachadaPendencia;
import br.laramara.ti.sislaraserver.fachadas.FachadaPreCadastro;
import br.laramara.ti.sislaraserver.fachadas.FachadaProjeto;
import br.laramara.ti.sislaraserver.fachadas.FachadaRecibo;
import br.laramara.ti.sislaraserver.fachadas.FachadaRelatorio;
import br.laramara.ti.sislaraserver.fachadas.FachadaRetirada;
import br.laramara.ti.sislaraserver.fachadas.FachadaSeguranca;
import br.laramara.ti.sislaraserver.fachadas.FachadaSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fachadas.FachadaUsuario;
import br.laramara.ti.sislaraserver.utilitarios.AvisoAtualizacao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;

public class ServicoSisLaraServerRmi implements ServicoSisLaraServer {
	
	@Inject
	private FachadaSeguranca fachadaSeguranca;
	@Inject
	private FachadaUsuario fachadaUsuario;
	@Inject
	private FachadaInstituicao fachadaInstituicao;
	@Inject
	private FachadaPreCadastro fachadaPreCadastro;
	@Inject 
	private FachadaGrupo fachadaGrupo;
	@Inject
	private FachadaDemanda fachadaDemanda;
	@Inject
	private FachadaAgendamento fachadaAgendamento;
	@Inject
	private FachadaEspera fachadaEspera;
	@Inject 
	private FachadaRelatorio fachadaRelatorio;
	@Inject
	private FachadaAtendimentoIndividual fachadaAtendimentoIndividual;
	@Inject
	private FachadaProjeto fachadaProjeto;
	@Inject
	private FachadaRetirada fachadaRetirada;
	@Inject 
	private FachadaCEP fachadaCEP;
	@Inject
	private FachadaSolicitacaoRelatorio fachadaSolicitacaoRelatorio;
	@Inject
	private FachadaContribuinte fachadaContribuinte;
	@Inject 
	private FachadaRecibo fachadaRecibo;
	@Inject
	private FachadaPendencia fachadaPendencia;
	@Inject
	private FachadaArquivoDisponivel fachadaArquivoDisponivel;
	@Inject
	private AvisoAtualizacao aviso;
	
	public ServicoSisLaraServerRmi() {
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
	public ResultadoListagemClassificacaoSocialDTO obterListagemClassificacaoSocial()
			throws RemoteException {
		return fachadaUsuario.obterListagemClassificacaoSocial();
	}
	
	@Override
	public ResultadoListagemGrupoEtnicoDTO obterListagemGrupoEtnico() throws RemoteException{
		return fachadaUsuario.obterListagemGrupoEtnico();
	}

	@Override
	public ResultadoListagemStatusDTO obterListagemStatus()
			throws RemoteException {
		return fachadaUsuario.obterListagemStatus();
	}
	
	@Override
	public ResultadoListagemStatusDTO obterListagemStatusFamiliar()
			throws RemoteException {
		return fachadaUsuario.obterListagemStatusFamiliar();
	}

	@Override
	public ResultadoListagemGeneroDTO obterListagemGenero() throws RemoteException {
		return fachadaUsuario.obterListagemGenero();
	}
	
	@Override
	public ResultadoListagemStatusBeneficioDTO obterListagemStatusBeneficio()
			throws RemoteException {
		return fachadaUsuario.obterListagemStatusBeneficio();
	}

	@Override
	public ResultadoListagemUfDTO obterListagemUf() throws RemoteException {
		return fachadaUsuario.obterListagemUf();
	}

	@Override
	public ResultadoListagemMunicipioDTO obterListagemMunicipio(UfDTO ufDto)
			throws RemoteException {
		return fachadaUsuario.obterListagemMunicipio(ufDto);
	}

	@Override
	public ResultadoListagemEstadoCivilDTO obterListagemEstadoCivil()
			throws RemoteException {
		return fachadaUsuario.obterListagemEstadoCivil();
	}

	@Override
	public ResultadoListagemZonaDTO obterListagemZona() throws RemoteException {
		return fachadaUsuario.obterListagemZona();
	}

	@Override
	public ResultadoListagemSimNaoDTO obterListagemSimNao()
			throws RemoteException {
		return fachadaUsuario.obterListagemSimNao();
	}
	
	@Override
	public ResultadoListagemSetorDTO obterListagemSetor()
			throws RemoteException {
		return fachadaUsuario.obterListagemSetor();
	}

	@Override
	public ResultadoListagemPaisDTO obterListagemPais() throws RemoteException {
		return fachadaUsuario.obterListagemPais();
	}

	@Override
	public ResultadoListagemEscolaridadeDTO obterListagemEscolaridade()
			throws RemoteException {
		return fachadaUsuario.obterListagemEscolaridade();
	}

	@Override
	public ResultadoListagemSituacaoDTO obterListagemSituacao()
			throws RemoteException {
		return fachadaUsuario.obterListagemSituacao();
	}

	@Override
	public ResultadoListagemPeriodoDTO obterListagemPeriodo()
			throws RemoteException {
		return fachadaUsuario.obterListagemPeriodo();
	}

	@Override
	public ResultadoEdicaoUsuarioDTO editarUsuario(UsuarioDTO usuarioDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaUsuario.editarUsuario(usuarioDto, tokenDto);
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
	public ResultadoListagemUsuarioDTO pesquisarUsuarioPor(
			EspecificacaoPesquisaUsuarioDTO especificacao)
			throws RemoteException {
		return fachadaUsuario.pesquisarUsuarioPor(especificacao);
	}

	@Override
	public ResultadoListagemTipoInstituicaoDTO obterListagemTipoInstituicao()
			throws RemoteException {
		return fachadaInstituicao.obterListagemTipoInstituicao();
	}

	@Override
	public ResultadoListagemClassificacaoInstituicaoDTO obterListagemClassificacaoInstituicao()
			throws RemoteException {
		return fachadaInstituicao.obterListagemClassificacaoInstituicao();
	}

	@Override
	public ResultadoEdicaoInstituicaoDTO editarInstituicao(
			InstituicaoDTO instituicaoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaInstituicao.editarInstituicao(instituicaoDto, tokenDto);
	}

	@Override
	public ResultadoListagemInstituicaoDTO pesquisarInstituicaoPor(
			EspecificacaoPesquisaInstituicaoDTO especificacao)
			throws RemoteException {
		return fachadaInstituicao.pesquisarInstituicaoPor(especificacao);
	}

	@Override
	public ResultadoListagemInstituicaoDTO obterListagemInstituicao()
			throws RemoteException {
		return fachadaInstituicao.obterListagemInstituicoes();
	}

	@Override
	public ResultadoListagemPreCadastroDTO pesquisarPreCadastroPor(
			EspecificacaoPesquisaPreCadastroDTO especificacao)
			throws RemoteException {
		return fachadaPreCadastro.pesquisarPreCadastroPor(especificacao);
	}

	@Override
	public ResultadoEdicaoPreCadastroDTO editarPreCadastro(
			PreCadastroDTO preCadastroDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaPreCadastro.editarPreCadastro(preCadastroDto, tokenDto);
	}
	
	@Override
	public ResultadoListagemTipoAtendimentoDTO obterListagemTipoAtendimento()
			throws RemoteException {
		return fachadaGrupo.obterListagemTipoAtendimento();
	}

	@Override
	public ResultadoValidacaoFamiliarDTO validarFamiliar(FamiliarDTO familiarDto)
			throws RemoteException {
		return fachadaUsuario.validarFamiliar(familiarDto);
	}

	@Override
	public ResultadoListagemParentescoDTO obterListagemParentesco()
			throws RemoteException {
		return fachadaUsuario.obterListagemParentesco();
	}
	@Override
	public ResultadoListagemSituacaoGuardaDTO obterListagemSituacaoGuarda()
			throws RemoteException {
		return fachadaUsuario.obterListagemSituacaoGuarda();
	}

	@Override
	public ResultadoListagemBeneficioDTO obterListagemBeneficio()
			throws RemoteException {
		return fachadaUsuario.obterListagemBeneficio();
	}

	@Override
	public ResultadoListagemConvenioDTO obterListagemConvenio()
			throws RemoteException {
		return fachadaUsuario.obterListagemConvenio();
	}

	@Override
	public ResultadoValidacaoInformacaoEducacionalDTO validarInformacaoEducacional(
			InformacaoEducacionalDTO informacaoEducacionalDto) throws RemoteException {
		return fachadaUsuario.validarInformacaoEducacional(informacaoEducacionalDto);
	}

	@Override
	public ResultadoListagemTipoTelefoneDTO obterListagemTipoTelefone()
			throws RemoteException {
		return fachadaUsuario.obterListagemTipoTelefone();
	}

	@Override
	public ResultadoValidacaoTelefoneDTO validarTelefone(TelefoneDTO telefoneDto)
			throws RemoteException {
		return fachadaUsuario.validarTelefone(telefoneDto);
	}
	
	@Override
	public ResultadoValidacaoCertidaoDTO validarCertidao(CertidaoDTO certidaoDto)
			throws RemoteException {
		return fachadaUsuario.validarCertidao(certidaoDto);
	}
	
	@Override
	public ResultadoValidacaoPeriodoBeneficioDTO validarPeriodoBeneficio(
			PeriodoBeneficioDTO periodoBeneficioDto) 
			throws RemoteException {
		return fachadaUsuario.validarPeriodoBeneficio(periodoBeneficioDto);
	}
	
	@Override
	public ResultadoValidacaoPeriodoDeficienciaDTO validarPeriodoDeficiencia(
			PeriodoDeficienciaDTO periodoDeficienciaDto) throws RemoteException {
		return fachadaUsuario.validarPeriodoDeficiencia(periodoDeficienciaDto);
	}
	
	@Override
	public ResultadoValidacaoPeriodoDoencaDTO validarPeriodoDoenca(
			PeriodoDoencaDTO periodoDoencaDto) throws RemoteException {
		return fachadaUsuario.validarPeriodoDoenca(periodoDoencaDto);
	}
	
	@Override
	public ResultadoListagemTipoCertidaoDTO obterListagemTipoCertidao()
			throws RemoteException {
		return fachadaUsuario.obterListagemTipoCertidao();
	}

	@Override
	public ResultadoListagemTipoEspecialidadeDTO obterListagemTipoEspecialidade()
			throws RemoteException {
		return fachadaInstituicao.obterListagemTipoEspecialidade();
	}
	
	@Override
	public ResultadoListagemTipoApoioDTO obterListagemTipoApoio()
			throws RemoteException {
		return fachadaInstituicao.obterListagemTipoApoio();
	}

	@Override
	public ResultadoListagemDreCefaiDTO obterListagemDreCefai()
			throws RemoteException {
		return fachadaInstituicao.obterListagemDreCefai();
	}

	@Override
	public ResultadoListagemDiretoriaEnsinoDTO obterListagemDiretoriaEnsino()
			throws RemoteException {
		return fachadaInstituicao.obterLitagemDiretoriaEnsino();
	}

	@Override
	public ResultadoListagemInstituicaoDTO obterListagemInstituicaoComSRMs()
			throws RemoteException {
		return fachadaInstituicao.obterListagemInstituicoesComSRMs();
	}
	
	@Override
	public ResultadoListagemInstituicaoDTO obterListagemInstituicaoComSalaRecurso()
			throws RemoteException {
		return fachadaInstituicao.obterListagemInstituicoesComSalaRecurso();
	}
	
	@Override
	public ResultadoListagemInstituicaoDTO obterListagemInstituicaoComOutrosAEE()
			throws RemoteException {
		return fachadaInstituicao.obterListagemInstituicoesComOutrosAEE();
	}
	
	@Override
	public ResultadoListagemTipoLeituraDTO obterListagemTipoLeitura()
			throws RemoteException {
		return fachadaUsuario.obterListagemTipoLeitura();
	}
		
	@Override
	public ResultadoListagemDeficienciaDTO obterListagemDeficiencia()
			throws RemoteException {
		return fachadaUsuario.obterListagemDeficiencia();
	}
	
	@Override
	public ResultadoListagemDoencaDTO obterListagemDoenca()
			throws RemoteException {
		return fachadaUsuario.obterListagemDoenca();
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
	public ResultadoListagemLocalAtendimentoDTO obterListagemLocalAtendimento()
			throws RemoteException {
		return fachadaGrupo.obterListagemLocalAtendimento();
	}
	
	@Override
	public ResultadoListagemTipificacaoServicoDTO obterListagemTipificacaoServico()
			throws RemoteException {
		return fachadaGrupo.obterListagemTipificacaoServico();
	}

	@Override
	public ResultadoEdicaoGrupoDTO editarGrupo(GrupoDTO grupoDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaGrupo.editarGrupo(grupoDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoModuloPeriodoDTO editarModuloPeriodo(ModuloPeriodoDTO moduloPeriodoDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaGrupo.editarModuloPeriodo(moduloPeriodoDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoAtendimentoGrupoDTO editarAtendimentoGrupo(
			AtendimentoGrupoDTO atendimentoGrupoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaGrupo.editarAtendimentoGrupo(atendimentoGrupoDto, tokenDto);
	}
		
	@Override
	public ResultadoListagemGrupoDTO obterListagemGrupoAtivo(String nomeGrupo)
			throws RemoteException {
		return fachadaGrupo.obterListagemGrupoAtivo(nomeGrupo);
	}
	
	@Override
	public ResultadoListagemProfissionalDTO obterListagemProfissionalAtivos()
			throws RemoteException {
		return fachadaGrupo.obterListagemProfissionaisAtivos();
	}
	
	@Override
	public ResultadoListagemProfissionalDTO obterListagemTodosProfissionais()
			throws RemoteException {
		return fachadaGrupo.obterListagemTodosProfissionais();
	}

	@Override
	public ResultadoListagemDiaSemanaDTO obterListagemDiaSemana()
			throws RemoteException {
		return fachadaGrupo.obterListagemDiaSemana();
	}

	@Override
	public ResultadoListagemStatusRelacaoUsuarioModuloDTO obterListagemStatusRelacaoUsuarioModulo(
			ModuloPeriodoDTO moduloPeriodoDto) throws RemoteException {
		return fachadaGrupo.obterListagemStatusRelacaoUsuarioModulo(moduloPeriodoDto);
	}
	
	@Override
	public ResultadoListagemStatusRelacaoUsuarioModuloDTO obterListagemStatusRelacaoUsuarioModuloDisponiveisParaInclusao(GrupoDTO grupoDto)
			throws RemoteException {
		return fachadaGrupo.obterListagemStatusRelacaoUsuarioModuloDisponiveisParaInclusao(grupoDto);
	}
	
	@Override
	public ResultadoListagemFrequenciaDTO obterListagemFrequenciaDoUsuario()
			throws RemoteException {
		return fachadaGrupo.obterListagemFrequenciaDoUsuario();
	}
	
	@Override
	public ResultadoListagemFrequenciaDTO obterListagemFrequenciaDoProfissional()
			throws RemoteException {
		return fachadaGrupo.obterListagemFrequenciaDoProfissional();
	}
	
	@Override
	public ResultadoValidacaoModuloPeriodoDTO validarModuloPeriodo(
			ModuloPeriodoDTO moduloPeriodoDto) throws RemoteException {
		return fachadaGrupo.validarModuloPeriodo(moduloPeriodoDto);
	}
	
	@Override
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(
			List<? extends ModuloRelacaoBaseDTO> modulosRelacaoBaseDto,
			ModuloRelacaoBaseDTO moduloRelacaoBaseDto) throws RemoteException {
		return fachadaGrupo.validarModuloRelacaoBase(modulosRelacaoBaseDto,
				moduloRelacaoBaseDto);
	}
	
	@Override
	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(
			ModuloRelacaoBaseDTO moduloRelacaoBaseDto) throws RemoteException {
		return fachadaGrupo.validarModuloRelacaoBase(moduloRelacaoBaseDto);
	}
	
	
	@Override
	public ResultadoValidacaoProgramacaoDTO validarProgramacao(
			ProgramacaoDTO programacaoDto) throws RemoteException {
		return fachadaGrupo.validarProgramacao(programacaoDto);
	}

	@Override
	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimentoAuxiliarBase(
			AtendimentoBaseDTO atendimentoBaseDTO) throws RemoteException {
		return fachadaGrupo.validarAtendimentoBase(atendimentoBaseDTO);
	}
	
	@Override
	public ResultadoListagemGrupoDTO pesquisarGrupoPor(
			EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDto)
			throws RemoteException {
		return fachadaGrupo.pesquisarGrupoPor(especificacaoPesquisaGrupoDto);
	}
	
	@Override
	public ResultadoListagemNomeCompletoGrupoDTO pesquisarNomeGrupoPor(
			String nomeGrupo) throws RemoteException {
		return fachadaGrupo.pesquisarNomeGrupoPor(nomeGrupo);
	}
	
	@Override
	public ResultadoGeracaoAtendimentoDTO gerarAtendimentos(
			EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaGrupo.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO,
				tokenDto);
	}
	
	@Override
	public ResultadoGeracaoAtendimentoDTO gerarAtendimentos(PendenciaDTO pendenciaDTO,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaGrupo.gerarAtendimentos(pendenciaDTO, tokenDto);
	}
	
	@Override
	public ResultadoEdicaoAtendimentoGrupoDTO cancelarAtendimentoGrupo(
			AtendimentoGrupoDTO atendimentoGrupoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaGrupo.cancelarAtendimentoGrupo(atendimentoGrupoDto,
				tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioFolhaDeRosto(Long prontuario,
			TokenDTO tokenDto)throws RemoteException {
		return fachadaRelatorio.gerarRelatorioFolhaDeRosto(prontuario, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
			String nomeGrupo, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
				nomeGrupo, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioProgramacaoDoGrupoParaFamilia(
			String dataInicio, String dataTermino, GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioProgramacaoDoGrupoParaFamilia(
				dataInicio, dataTermino, grupoDto, moduloPeriodoDto, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioCargaHorariaDeUsuarioPorGrupo(
			String nomeGrupo, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioCargaHorariaDeUsuarioPorGrupo(
				nomeGrupo, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(
			GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(grupoDto,
						moduloPeriodoDto, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioAtendimentoDeUsuarioNoGrupoDetalhado(
			GrupoDTO grupoDto, ModuloPeriodoDTO moduloPeriodoDto,
			UsuarioDTO usuarioDto, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioAtendimentoDeUsuarioNoGrupoDetalhado(
				grupoDto, moduloPeriodoDto, usuarioDto, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(
						prontuario, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(
						prontuario, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioTodosAtendimentosIndividuaisDoUsuarioPorDataLancamento(prontuario, tokenDto);
	}
	

	@Override
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorVisitaMonitorada(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioAtendidosVisitaMonitorada(dataInicio, dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorTipoAtendimento(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorTipoAtendimento(
						dataInicio, dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosPorIdadeEGenero(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosPorIdadeEGenero(
						dataInicio, dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorUF(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorPorUF(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorRenda(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorRenda(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorMunicipioSP(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorMunicipioSP(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorEscolaridade(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorEscolaridade(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorDeficiencia(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorDeficiencia(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorClassificacaoInstituicao(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorClassificacaoInstituicao(
						dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioComasAtendidosAtendimentosPorBeneficio(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioComasAtendidosAtendimentosPorBeneficio(
						dataInicio, dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamentoLegado(
						prontuario, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioGeracaoAtendimentosIndividuaisEGrupo(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioGeracaoAtendimentosIndividuasEGrupo(dataInicio,
						dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioVagasEmGruposAtivos(TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioVagasEmGruposAtivos(tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioQuantidadeUsuarioOrdenadoPorIdade(
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioQuantidadeUsuarioOrdenadoPorIdade(tokenDto);
	}
	

	@Override
	public ArquivoDTO gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(
			Long idadeInicio, Long idadeTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(
						idadeInicio, idadeTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioRetiradaProntuariosNoAgendamento(String data,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioRetiradaProntuarioNoAgendamento(data, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioQuantidadeAtendimentosIndividuais(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioQuantidadeAtendimentosIndividuais(dataInicio, dataTermino, tokenDto);
	}
		
	@Override
	public ArquivoDTO gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorTiposVinculos(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioQuantidadeAtendidosPorTiposVinculos(dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioQuantidadeAtendimentosParaPrefeitura(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioQuantidadeAtendimentoParaPrefeitura(dataInicio,
						dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(
			String dataInicio, String dataTermino, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(dataInicio, dataTermino, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioTodasRetiradasPendentes(TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioTodasRetiradasPendentes(tokenDto);
	}
	
	public ArquivoDTO gerarRelatorioTodasRetiradasPorProntuario(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioTodasRetiradasPorProntuario(prontuario, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioParticipacaoUsuarioEmGrupos(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioParticipacaoUsuarioEmGrupos(prontuario, tokenDto);
	}
	
	@Override
	public ArquivoDTO gerarRelatorioFrequenciaPorGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, boolean paisagem,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioFrequenciaPorGrupo(grupoDto,
				moduloPeriodoDto, paisagem, tokenDto);
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
	public ResultadoListagemProntuarioEscaneadoDTO obterListagemProntuariosEscaneados(
			UsuarioDTO usuarioDto) throws RemoteException {
		return fachadaUsuario.obterListagemProntuariosEscaneados(usuarioDto);
	}

	@Override
	public ProntuarioEscaneadoDTO obterProntuarioEscaneado(
			UsuarioDTO usuarioDto, ProntuarioEscaneadoDTO prontuarioEscaneadoDto)
			throws RemoteException {
		return fachadaUsuario.obterProntuarioEscaneado(usuarioDto, prontuarioEscaneadoDto);
	}

	@Override
	public ResultadoGeracaoAgendamentoDTO gerarAgendamento(
			EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaAgendamento.gerarAgendamento(
				especificacaoGeracaoAgendamentoDto, tokenDto);
	}
	
	@Override
	public ResultadoEdicaoAgendamentoDTO liberarAgendamentoCancelando(
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAgendamento.liberarAgendamentoColocandoStatusCancelando(agendamentoDto,
				tokenDto);
	}

	@Override
	public ResultadoEdicaoAgendamentoDTO copiarAgendamento(
			EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDto,
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAgendamento.copiarAgendamento(
				especificacaoGeracaoCopiaAgendamentoDto, agendamentoDto,
				tokenDto);
	}
	
	@Override
	public ResultadoListagemStatusDTO obterListagemStatusDisponiveisParaAgendamento()
			throws RemoteException {
		return fachadaAgendamento.obterListagemStatusDisponiveisParaAgendamento();
	}

	@Override
	public ResultadoListagemAgendamentoDTO obterListagemAgendamento(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto)
			throws RemoteException {
		return fachadaAgendamento.obterListagemAgendamento(especificacaoPesquisaAgendamentoDto);
	}
	

	@Override
	public ResultadoListagemAgendamentoDTO obterListagemAgendamentoDisponiveis(
			EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto)
			throws RemoteException {
		return fachadaAgendamento.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamentoDto);
	}

	@Override
	public ResultadoEdicaoAgendamentoDTO editarAgendamento(
			AgendamentoDTO agendamentoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAgendamento.editarAgendamento(agendamentoDto, tokenDto);
	}

	@Override
	public ResultadoListagemMotivoCancelamentoDTO obterListagemMotivoCancelamentoAgendamento()
			throws RemoteException {
		return fachadaAgendamento.obterListagemMotivoCancelamentoAgendamento();
	}
	
	@Override
	public ResultadoListagemMotivoCancelamentoDTO obterListagemMotivoCancelamentoDemanda()
			throws RemoteException {
		return fachadaDemanda.obterListagemMotivoCancelamentoDemanda();
	}

	@Override
	public ResultadoListagemStatusAgendamentoDTO obterListagemStatusAgendamento()
			throws RemoteException {
		return fachadaAgendamento.obterListagemStatusAgendamento();
	}

	@Override
	public ResultadoListagemTipoEsperaDTO obterListagemTipoEsperaTotal()
			throws RemoteException {
		return fachadaEspera.obterListagemTipoEsperaTotal();
	}
	
	@Override
	public ResultadoListagemTipoEsperaDTO obterListagemTipoEsperaDisponiveisParaInclusao()
			throws RemoteException {
		return fachadaEspera.obterListagemTipoEsperaDisponiveisParaInclusao();
	}

	@Override
	public ResultadoListagemStatusEsperaDTO obterListagemStatusEspera()
			throws RemoteException {
		return fachadaEspera.obterListagemStatusEspera();
	}

	@Override
	public ResultadoListagemEsperaDTO obterListagemEspera(
			EspecificacaoPesquisaEsperaDTO especificacao)
			throws RemoteException {
		return fachadaEspera.obterListagemEspera(especificacao);
	}
	
	@Override
	public String obterObservacoesHistoricasEspera(
			InformacaoEssencialDTO informacaoEssencialDto)
			throws RemoteException {
		return fachadaEspera.obterObservacoesHistoricasEspera(informacaoEssencialDto);
	}
	
	@Override
	public ResultadoEdicaoEsperaDTO editarEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaEspera.editarEspera(esperaDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoEsperaDTO cancelarEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaEspera.cancelarEspera(esperaDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoEsperaDTO editarEsperaEAssociarAgendamento(
			EspecificacaoAssociacaoAgendamentoDTO especificacaoAssociacaoAgendamentoDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaEspera.editarEsperaEAssociarAgendamento(
				especificacaoAssociacaoAgendamentoDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoEsperaDTO concluirEspera(EsperaDTO esperaDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaEspera.agendarEspera(esperaDto, false, tokenDto);
	}

	@Override
	public ResultadoEdicaoAtendimentoIndividualDTO editarAtendimentoIndividual(
			AtendimentoIndividualDTO atendimentoIndividualDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAtendimentoIndividual.editarAtendimentoIndividual(
				atendimentoIndividualDto, tokenDto);
	}

	@Override
	public ResultadoListagemAtendimentoIndividualDTO obterListagemAtendimentoIndividual(
			EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDto)
			throws RemoteException {
		return fachadaAtendimentoIndividual
				.obterListagemAtendimentoIndividual(especificacaoPesquisaAtendimentoIndividualDto);
	}
	
	@Override
	public ResultadoListagemTipoVinculoDTO obterListagemTipoVinculo()
			throws RemoteException {
		return fachadaAtendimentoIndividual.obterListagemTipoVinculo();
	}

	@Override
	public ResultadoListagemParticipacaoDTO obterListagemParticipacao()
			throws RemoteException {
		return fachadaAtendimentoIndividual.obterListagemParticipacao();
	}
	
	@Override
	public ResultadoEdicaoAtendimentoIndividualDTO copiarAtendimentoIndividual(
			EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto,
			AtendimentoIndividualDTO atendimentoIndividualDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAtendimentoIndividual.copiarAtendimento(
				especificacaoCopiaAtendimentoIndividualDto,
				atendimentoIndividualDto, tokenDto);
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
	public ResultadoListagemRecursoDTO obterListagemRecurso()
			throws RemoteException {
		return fachadaGrupo.obterListagemRecurso();
	}

	@Override
	public ResultadoValidacaoLoteRecursoDTO validarLoteRecurso(
			LoteRecursoDTO loteRecursoDto) 
					throws RemoteException {
		return fachadaGrupo.validarLoteRecurso(loteRecursoDto);
	}

	@Override
	public ResultadoListagemDemandaDTO obterListagemDemanda(
			EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDto)
			throws RemoteException {
		return fachadaDemanda.obterListagemDemanda(especificacaoPesquisaDemandaDto);
	}

	@Override
	public ResultadoListagemProjetoDTO obterListagemProjetoAtivos()
			throws RemoteException {
		return fachadaDemanda.obterListagemProjetoAtivo();
	}

	@Override
	public ResultadoEdicaoProjetoDTO editarProjeto(
			ProjetoDTO projetoDto, TokenDTO tokenDto)
					throws RemoteException {
		return fachadaProjeto.editarProjeto(projetoDto, tokenDto);
	}

	@Override
	public ResultadoListagemProjetoDTO pesquisarProjetoPor(
			EspecificacaoPesquisaProjetoDTO especificacaoPesquisaProjetoDto)
			throws RemoteException {
		return fachadaProjeto.pesquisarProjetoPor(especificacaoPesquisaProjetoDto);
	}
	
	@Override
	public ResultadoGeracaoDemandaDTO gerarDemanda(
			EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaDemanda.gerarDemanda(especificacaoGeracaoDemandaDTO, tokenDto);
	}

	@Override
	public ResultadoListagemStatusDemandaDTO obterListagemStatusDemandaLimitada()
			throws RemoteException {
		return fachadaDemanda.obterListagemStatusDemandaLimitada();
	}
	
	@Override
	public ResultadoListagemStatusDemandaDTO obterListagemStatusDemanda()
			throws RemoteException {
		return fachadaDemanda.obterListagemStatusDemanda();
	}

	@Override
	public ResultadoEdicaoDemandaDTO mudarStatusDemanda(DemandaDTO demandaDto, StatusDemandaDTO statusDemandaDTO, MotivoCancelamentoDTO motivoCancelamentoDTO,
			String obs, TokenDTO tokenDto) throws RemoteException {
		return fachadaDemanda.mudarStatusDemanda(demandaDto, statusDemandaDTO, motivoCancelamentoDTO, obs, tokenDto);
	}

	@Override
	public ResultadoConsultaCEP consultarEndereco(String cep) throws RemoteException {
		return fachadaCEP.consultarPorCEP(cep);
	}

	@Override
	public ArquivoDTO gerarRelatorioContatosIntegrantesGrupo(GrupoDTO grupoDto,
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto) {
		return fachadaRelatorio.gerarRelatorioContatosIntegrantesGrupo(grupoDto, moduloPeriodoDto, tokenDto);
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
	public ResultadoEdicaoRetiradaDTO efetuarRetirada(RetiradaDTO retiradaDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRetirada.efetuarRetirada(retiradaDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoRetiradaDTO baixarRetirada(RetiradaDTO retiradaDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRetirada.baixarRetirada(retiradaDto, tokenDto);
	}

	@Override
	public ResultadoListagemProfissionalDTO obterListagemVoluntarioAtivos()
			throws RemoteException {
		return fachadaRetirada.obterListagemVoluntarioAtivo();
	}

	@Override
	public ResultadoConsultaCidDTO consultarCid(String codigo) {
		return fachadaUsuario.consultarCid(codigo);
	}

	@Override
	public ResultadoListagemEpocaIncidenciaDTO obterListagemEpocaIncidencia()
			throws RemoteException {
		return fachadaUsuario.obterListagemEpocaIncidencia();
	}

	@Override
	public ResultadoListagemAreaFormacaoDTO obterListagemAreaFormacao()
			throws RemoteException {
		return fachadaUsuario.obterListagemAreaFormacao();
	}

	@Override
	public ResultadoListagemCargoDTO obterListagemCargo()
			throws RemoteException {
		return fachadaUsuario.obterListagemCargo();
	}

	@Override
	public ResultadoListagemLocalTrabalhoDTO obterListagemLocalTrabalho()
			throws RemoteException {
		return fachadaUsuario.obterListagemLocalTrabalho();
	}

	@Override
	public ResultadoValidacaoInformacaoTrabalhoCompletaDTO validarInformacaoTrabalhoCompleta(
			InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto)
			throws RemoteException {
		return fachadaUsuario.validarInformacaoTrabalhoCompleta(informacaoTrabalhoCompletaDto);
	}

	@Override
	public void gravarTela(byte[] tela) throws RemoteException {
		fachadaSeguranca.gravarTela(tela);
	}

	@Override
	public ResultadoEdicaoSolicitacaoRelatorioDTO editarSolicitacaoRelatorio(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.efetuarSolicitacao(
				solicitacaoRelatorioDto, tokenDto);
	}

	@Override
	public ResultadoListagemSolicitacaoRelatorioDTO pesquisarSolicitacaoRelatorioPor(
			EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacao)
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.pesquisarSolicitacaoRelatorioPor(especificacao);
	}

	@Override
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEncaminhamentoRecepcao(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.efetuarEncaminhamentoRecepcao(
				solicitacaoRelatorioDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEmissaoProfissional(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.efetuarEmissaoPorProfissional(
				solicitacaoRelatorioDto, tokenDto);
	}

	@Override
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarCancelamento(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.efetuarCancelamento(
				solicitacaoRelatorioDto, tokenDto);
	}
	
	@Override
	public ResultadoEdicaoSolicitacaoRelatorioDTO efetuarEntrega(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.efetuarEntrega(
				solicitacaoRelatorioDto, tokenDto);
	}

	@Override
	public ResultadoListagemStatusSolicitacaoRelatorioDTO obterListagemStatusEntrega()
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.obterListagemStatusEntrega();
	}
	
	@Override
	public ResultadoListagemStatusSolicitacaoRelatorioDTO obterListagemStatusSolicitacaoRelatorio()
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.obterListagemStatusSolicitacaoRelatorio();
	}

	@Override
	public ResultadoListagemFinalidadeRelatorioDTO obterListagemFinalidadeRelatorio()
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.obterListagemFinalidadeRelatorio();
	}

	@Override
	public ArquivoDTO gerarRelatorioSolicitacaoRelatorio(Long protocolo,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioSolicitacaoRelatorio(
				protocolo, tokenDto);
	}

	@Override
	public ResultadoListagemElaboradorDTO obterListagemElaborador()
			throws RemoteException {
		return fachadaSolicitacaoRelatorio.obterListagemElaborador();
	}

	@Override
	public ResultadoListagemItensCustoDTO obterListagemItensCustoDoenca()
			throws RemoteException {
		return fachadaUsuario.obterListagemItensCustoDoenca();
	}
	
	@Override
	public ResultadoListagemItensCustoDTO obterListagemItensCustoDeficiencia()
			throws RemoteException {
		return fachadaUsuario.obterListagemItensCustoDeficiencia();
	}

	@Override
	public ResultadoValidacaoCustoDTO validarCusto(CustoDTO custoDto)
			throws RemoteException {
		return fachadaUsuario.validarCusto(custoDto);
	}

	@Override
	public boolean exibirAvisoDeAtualizacao(TokenDTO tokenDto)
			throws RemoteException {
		return aviso.exibirAvisoDeAtualizacao(tokenDto);
	}

	@Override
	public void confirmarLeituraDeAvisoDeAtualizacao(TokenDTO tokenDto) throws RemoteException {
		aviso.confirmarLeituraDeAvisoDeAtualizacao(tokenDto);
	}

	@Override
	public boolean ativarDesativarAvisoDeAtualizacao() throws RemoteException {
		return aviso.ativarDesativarAvisoDeAtualizacao();
	}
	
	@Override
	public boolean fechamentoAutomaticoClientAtivado() throws RemoteException {
		return aviso.fechamentoAutomaticoClientAtivado();
	}

	@Override
	public ResultadoEdicaoAgendamentoDTO cancelarAgendamento(
			AgendamentoDTO agendamentoDTO, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAgendamento.cancelarAgendamento(agendamentoDTO, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioAtendidosSemInformacaoDeficiencia(
			String dataInicio, String dataTermino, TokenDTO token)
			throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioAtendidosSemInformacaoDeficiencia(dataInicio,
						dataTermino, token);
	}

	@Override
	public ResultadoEdicaoAgendamentoDTO reagendarAgendamento(
			AgendamentoDTO agendamentoOrigemDto,
			AgendamentoDTO agendamentoDestinoDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaAgendamento.reagendarAgendamento(agendamentoOrigemDto,
				agendamentoDestinoDto, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioTodasFrequenciasOrdenadoPorDataLancamento(
			Long prontuario, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio
				.gerarRelatorioTodasFrequenciasOrdenadoPorDataLancamento(
						prontuario, tokenDto);
	}

	@Override
	public ResultadoListagemContribuinteDTO pesquisarContribuintePor(
			EspecificacaoPesquisaContribuinteDTO especificacao) {
		return fachadaContribuinte.obterListagemContribuinte(especificacao);
	}

	@Override
	public ResultadoEdicaoContribuinteDTO editarContribuinte(
			ContribuinteDTO contribuinteDto, TokenDTO tokenDto)
			throws RemoteException {
		return fachadaContribuinte
				.editarContribuinte(contribuinteDto, tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioTodosUsuarioComStatusDeGrupoProvisorioOuAcesso(TokenDTO tokenDto)
			throws RemoteException {
		return fachadaRelatorio.gerarRelatorioTodosUsuarioComStatusDeGrupoProvisorioOuAcesso(tokenDto);
	}

	@Override
	public ResultadoListagemStatusContribuinteDTO obterListagemStatusContribuinte() throws RemoteException {
		return fachadaContribuinte.obterListagemStatusContribuinte();
	}

	@Override
	public boolean solicitarGeracaoArquivoCobranca(TokenDTO tokenDto) throws RemoteException {
		return fachadaContribuinte.solicitarGeracaoArquivoCobranca(tokenDto);
	}

	@Override
	public ResultadoListagemMotivoDesativacaoDTO obterListagemMotivoDesativacao() throws RemoteException {
		return fachadaContribuinte.obterListagemMotivoDesativacao();
	}

	@Override
	public ResultadoListagemPendenciaDTO obterListagemPendencia(TokenDTO tokenDto) throws RemoteException {
		return fachadaPendencia.obterListagemPendencia(tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioQuantidadesAvaliacoesFuncionais(String dataInicio, String dataTermino,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioQuantidadesAvaliacoesFuncionais(dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ResultadoValidacaoDiaSemanaEHorarioDTO validarDiaSemanaEHorario(DiaSemanaEHorarioDTO diaSemanaEHorarioDTO)
			throws RemoteException {
		return fachadaGrupo.validarDiaSemanaEHorario(diaSemanaEHorarioDTO);
	}

	@Override
	public ArquivoDTO gerarRelatorioFluxoAtendimentoCasosNovosDeAvaliacaoFuncional(String dataInicio,
			String dataTermino, TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioFluxoAtendimentoCasosNovosAvaliacaoFuncional(dataInicio, dataTermino,
				tokenDto);
	}

	@Override
	public ArquivoDTO gerarRelatorioFluxoAtendimentoRetornosDeAvaliacaoFuncional(String dataInicio, String dataTermino,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioFluxoAtendimentoRetornosAvaliacaoFuncional(dataInicio, dataTermino, tokenDto);
	}

	@Override
	public ResultadoListagemStatusDTO obterListagemStatusDisponiveisParaAtendimentoIndividual() throws RemoteException {
		return fachadaAtendimentoIndividual.obterListagemStatusDisponiveisParaAtendimentoIndividual();
	}

	@Override
	public StatusRelacaoComModuloDTO obterStatusRelacaoPadrao(GrupoDTO grupoDto) throws RemoteException {
		return fachadaGrupo.obterStatusRelacaoPadrao(grupoDto);
	}

	@Override
	public ArquivoDTO obterArquivoAtendimentoIndividualDTO(AtendimentoIndividualDTO atendimentoIndividualDTO,
			ArquivoDTO arquivoDTO) throws RemoteException {
		return fachadaAtendimentoIndividual.obterArquivoAtendimentoIndividual(atendimentoIndividualDTO, arquivoDTO);
	}

	@Override
	public ArquivoDTO obterArquivoAtendimentoGrupoDTO(AtendimentoGrupoDTO atendimentoGrupoDTO, ArquivoDTO arquivoDTO)
			throws RemoteException {
		return fachadaGrupo.obterArquivoAtendimentoGrupo(atendimentoGrupoDTO, arquivoDTO);
	}

	@Override
	public ArquivoDTO obterArquivoAtendimentoUsuarioDTO(AtendimentoUsuarioDTO atendimentoUsuarioDTO,
			ArquivoDTO arquivoDTO) throws RemoteException {
		return fachadaGrupo.obterArquivoAtendimentoUsuario(atendimentoUsuarioDTO, arquivoDTO);
	}
	
	@Override
	public ArquivoDTO obterArquivoDocumentoSolicitacaoDoacaoDTO(DemandaDTO demandaDto, ArquivoDTO arquivoDTO)
			throws RemoteException {
		return fachadaDemanda.obterArquivoDocumentoSolicitacaoDoacao(demandaDto, arquivoDTO);
	}

	@Override
	public ResultadoListagemArquivoDisponivelDTO obterListagemArquivoDisponivelDTO(String dadosPesquisa, boolean somenteGrupos)
			throws RemoteException {
		return fachadaArquivoDisponivel.obterListagemArquivosDisponiveis(dadosPesquisa, somenteGrupos);
	}

	@Override
	public ArquivoDTO obterArquivoDisponivelDTO(String id, String nome, String tipo)
			throws RemoteException {
		return fachadaArquivoDisponivel.obterArquivoDisponivel(id, nome, tipo);
	}

	@Override
	public ResultadoListagemNomeDocumentoDTO obterListagemNomeDocumento() throws RemoteException {
		return fachadaDemanda.obterListagemNomeDocumento();
	}

	@Override
	public ResultadoProrrogacaoCartelaDeSelosDTO prorrogar(DemandaDTO demandaDto, TokenDTO tokenDto) throws RemoteException {
		return fachadaDemanda.prorrogar(demandaDto, tokenDto);
	}

	@Override
	public ResultadoCaptacaoCartelaDeSelosDTO captar(EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDto,
			TokenDTO tokenDto) throws RemoteException {
		return fachadaDemanda.captar(especificacaoCaptacaoDemandaDto, tokenDto);
	}
	
	@Override
	public ResultadoReciboDTO obterReciboMaisRecentePorCpfCnpj(String cpfCnpj) throws RemoteException {
		return fachadaRecibo.obterReciboMaisRecentePorCpfCnpf(cpfCnpj);
	}

	@Override
	public ReciboDTO obterReciboDto(String numeroRecibo) throws RemoteException {
		return fachadaRecibo.obterValorTotalDoRecibo(numeroRecibo);
	}

	@Override
	public ArquivoDTO gerarRelatorioMailingVisitaMonitorada(String dataInicio, String dataTermino,
			List<TipoVinculoDTO> tiposVinculosDTO, TokenDTO tokenDTO) throws RemoteException {
		return fachadaRelatorio.gerarRelatorioMailingVisitaMonitorada(dataInicio, dataTermino, tiposVinculosDTO, tokenDTO);
	}

	@Override
	public String obterValorTotalPorDeRecurso(RecursoDTO recursoDto, String quantidade) throws RemoteException {
		return fachadaProjeto.obterValorTotalPorRecurso(recursoDto, quantidade);
	}

	@Override
	public ResultadoEdicaoProjetoDTO calcularTotais(ProjetoDTO projetoDTO) throws RemoteException {
		return fachadaProjeto.calcularTotais(projetoDTO);
	}

	@Override
	public ResultadoEdicaoReciboDTO editarRecibo(ReciboDTO reciboDTO, TokenDTO tokenDTO) throws RemoteException {
		return fachadaRecibo.editarRecibo(reciboDTO, tokenDTO);
	}

	@Override
	public ResultadoListagemFilialDTO obterListagemFilial() throws RemoteException {
		return fachadaRecibo.obterListagemFilial();
	}

	@Override
	public ResultadoListagemReciboDTO pesquisarReciboPor(EspecificacaoPesquisaReciboDTO especificacao)
			throws RemoteException {
		return fachadaRecibo.obterListagemRecibo(especificacao);
	}

	@Override
	public ArquivoDTO obterRelatorioRecibo(Long numeroRecibo, TokenDTO tokenDTO)
			throws RemoteException {
		return fachadaRecibo.obterRelatorioRecibo(numeroRecibo, tokenDTO);
	}

	@Override
	public ResultadoEdicaoReciboDTO cancelarRecibo(Long numeroRecibo, String motivoDoCancelamento, TokenDTO tokenDto) throws RemoteException {
		return fachadaRecibo.cancelar(numeroRecibo, motivoDoCancelamento, tokenDto);
	}
}
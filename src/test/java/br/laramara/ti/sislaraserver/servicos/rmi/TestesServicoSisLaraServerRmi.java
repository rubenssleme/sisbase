package br.laramara.ti.sislaraserver.servicos.rmi;

import java.io.File;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoAlteracaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemArquivoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoValidacaoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
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
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoEdicaoAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.EspecificacaoPesquisaContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoEdicaoReciboDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemMotivoDesativacaoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ResultadoListagemStatusContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.StatusContribuinteDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DocumentoSolicitacaoDoacaoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoCaptacaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoGeracaoDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaDemandaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.EspecificacaoPesquisaReciboDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.NomeDocumentoDTO;
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
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoCEPDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.PaisDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ZonaDTO;
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
import br.laramara.ti.sislaracommons.dtos.espera.StatusEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.espera.TipoEsperaDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoListagemParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoValidacaoFamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentosDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoPesquisaGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.FrequenciaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProgramacaoDTO;
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
import br.laramara.ti.sislaracommons.dtos.grupo.TipoAtendimentoDTO;
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
import br.laramara.ti.sislaracommons.dtos.solicitacao.ElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.EspecificacaoPesquisaSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.FinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoEdicaoSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemFinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.ResultadoListagemStatusSolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.StatusSolicitacaoRelatorioDTO;
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
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.TipoArquivoDisponivel;
import br.laramara.ti.sislaraserver.dominio.contribuicao.AutomatizadorContribuicao;
import br.laramara.ti.sislaraserver.dominio.doacao.NomeDocumento;
import br.laramara.ti.sislaraserver.dominio.doacao.StatusDemanda;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;
import br.laramara.ti.sislaraserver.dominio.espera.StatusEspera;
import br.laramara.ti.sislaraserver.dominio.espera.TipoEspera;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;
import br.laramara.ti.sislaraserver.dominio.solicitacao.StatusSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoIndividual;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoProfissional;
import br.laramara.ti.sislaraserver.fabricas.ContextoAtendimentoUsuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoCertidao;
import br.laramara.ti.sislaraserver.fabricas.ContextoContribuinte;
import br.laramara.ti.sislaraserver.fabricas.ContextoCusto;
import br.laramara.ti.sislaraserver.fabricas.ContextoDescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoDiasSemanaEHorarios;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoGeracaoAgendamento;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoGeracaoCopiaAgendamento;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoGeracaoDemanda;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoPesquidaDemanda;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspecificacaoPesquisaEspera;
import br.laramara.ti.sislaraserver.fabricas.ContextoEspera;
import br.laramara.ti.sislaraserver.fabricas.ContextoFamiliar;
import br.laramara.ti.sislaraserver.fabricas.ContextoGenerico;
import br.laramara.ti.sislaraserver.fabricas.ContextoGrupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoEducacional;
import br.laramara.ti.sislaraserver.fabricas.ContextoInformacaoTrabalhoCompleta;
import br.laramara.ti.sislaraserver.fabricas.ContextoInstituicao;
import br.laramara.ti.sislaraserver.fabricas.ContextoLocalAtendimento;
import br.laramara.ti.sislaraserver.fabricas.ContextoLoteRecurso;
import br.laramara.ti.sislaraserver.fabricas.ContextoModulo;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloPeriodo;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoModuloUsuario;
import br.laramara.ti.sislaraserver.fabricas.ContextoMotivoCancelamento;
import br.laramara.ti.sislaraserver.fabricas.ContextoPeriodoBeneficio;
import br.laramara.ti.sislaraserver.fabricas.ContextoPeriodoDeficiencia;
import br.laramara.ti.sislaraserver.fabricas.ContextoPeriodoDoenca;
import br.laramara.ti.sislaraserver.fabricas.ContextoPreCadastro;
import br.laramara.ti.sislaraserver.fabricas.ContextoProfissional;
import br.laramara.ti.sislaraserver.fabricas.ContextoProgramacao;
import br.laramara.ti.sislaraserver.fabricas.ContextoProjeto;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecibo;
import br.laramara.ti.sislaraserver.fabricas.ContextoRecurso;
import br.laramara.ti.sislaraserver.fabricas.ContextoSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.fabricas.ContextoTelefone;
import br.laramara.ti.sislaraserver.fabricas.ContextoTipoVinculo;
import br.laramara.ti.sislaraserver.fabricas.ContextoUsuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.utilitarios.Configuracao;
import br.laramara.ti.sislaraserver.utilitarios.Registro;
import br.laramara.ti.sislaraserver.utilitarios.tarefas.AutomatizadorTarefas;

public class TestesServicoSisLaraServerRmi extends TestesIntegracaoAbstrato {

	private ServicoSisLaraServer servicoSisLaraServerRmi;

	public TestesServicoSisLaraServerRmi() {
		super("DadosTestesServicoSisLaraServerRmi.xml");
		servicoSisLaraServerRmi = Registro.obterServicoSisLaraServer();
		Registro.obterAutomatizadorTarefas().desativarAtualizacaoPendencias();
	}

	private Identificavel obterDaRelacaoPorId(List<? extends Identificavel> objetosDto, Long id) {
		for (Identificavel identificavel : objetosDto) {
			if (identificavel.getId().equals(id)) {
				return identificavel;
			}
		}
		return null;
	}

	private Identificavel obterDaRelacaoPorId(List<ModuloUsuarioDTO> objetosDto, List<Long> ids) {
		for (Identificavel identificavel : objetosDto) {
			for (Long id : ids) {
				if (((ModuloUsuarioDTO) identificavel).getUsuarioDto().getId().equals(id)) {
					return identificavel;
				}
			}
		}
		return null;
	}

	private ModuloPeriodoDTO obterModuloPeriodoDto(List<GrupoDTO> gruposDto, Long idGrupo, Long idModuloPeriodo) {
		GrupoDTO grupoDTO = (GrupoDTO) obterDaRelacaoPorId(gruposDto, idGrupo);
		ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDTO.getModulosPeriodosDto(),
				idModuloPeriodo);
		return moduloPeriodoDTO;
	}

	private AtendimentoUsuarioDTO obterAtendimentoDoUsuarioPeloProntuario(
			List<AtendimentoUsuarioDTO> atendimentosUsuariosDto, Long prontuario) {
		for (AtendimentoUsuarioDTO atendimentoUsuarioDTO : atendimentosUsuariosDto) {
			if (atendimentoUsuarioDTO.getUsuarioDto().getId().equals(prontuario)) {
				return atendimentoUsuarioDTO;
			}
		}
		return null;
	}

	private ModuloPreCadastroDTO obterAtendimentoDoUsuarioPeloPreCadastro(
			List<ModuloPreCadastroDTO> modulosPreCadastroDto, Long idPreCadastro) {
		for (ModuloPreCadastroDTO moduloPreCadastroDto : modulosPreCadastroDto) {
			if (moduloPreCadastroDto.getPreCadastroDto().getId().equals(idPreCadastro)) {
				return moduloPreCadastroDto;
			}
		}
		return null;
	}

	private AtendimentoGrupoDTO obterAtendimentoGrupoDto(List<GrupoDTO> gruposDto, Long idGrupo, Long idModuloPeriodo,
			Long idAtendimentoGrupo) {
		ModuloPeriodoDTO moduloPeriodoDTO = obterModuloPeriodoDto(gruposDto, idGrupo, idModuloPeriodo);
		AtendimentoGrupoDTO atendimentoGrupoDTO = (AtendimentoGrupoDTO) obterDaRelacaoPorId(
				moduloPeriodoDTO.getAtendimentosGrupoDto(), idAtendimentoGrupo);
		return atendimentoGrupoDTO;
	}

	private ModuloUsuarioDTO obterPeloIdUsuario(ModuloPeriodoDTO moduloPeriodo, UsuarioDTO usuarioDto) {
		for (ModuloUsuarioDTO moduloUsuario : moduloPeriodo.getModulosUsuariosDto()) {
			if (moduloUsuario.getUsuarioDto().equals(usuarioDto)) {
				return moduloUsuario;
			}
		}
		return null;
	}

	private ResultadoEdicaoAtendimentoGrupoDTO gerar_atendimento_de_grupo_com_frequencia_fu(
			ResultadoAutenticacaoDTO resultadoDto, String nomeGrupo, Long idGrupo, Long idModuloPeriodo,
			String dataLancamento, Long prontuario) throws RemoteException {
		ResultadoListagemGrupoDTO resultadoListagemGrupoSS = servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupo);
		GrupoDTO grupoDTOSS = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoSS.getObjetosDto(), idGrupo);
		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDTOSS.getId(), obterDaRelacaoPorId(grupoDTOSS.getModulosPeriodosDto(), idModuloPeriodo).getId(),
				dataLancamento, "22:22", "23:00");
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());
		obterAtendimentoDoUsuarioPeloProntuario(
				resultadoGeracaoAtendimento.getAtendimentoGrupoGeradoDTO().getAtendimentosUsuariosDto(), prontuario)
						.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));
		return servicoSisLaraServerRmi.editarAtendimentoGrupo(
				resultadoGeracaoAtendimento.getAtendimentoGrupoGeradoDTO(), resultadoDto.getToken());
	}

	private void servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(
			ResultadoAutenticacaoDTO resultadoDto, Long prontuario) throws RemoteException {
		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes_adicionando_profissional(
				resultadoDto, prontuario, null);
	}

	private void servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes_adicionando_profissional(
			ResultadoAutenticacaoDTO resultadoDto, Long prontuario,
			AtendimentoProfissionalDTO atendimentoProfissionalDTO) throws RemoteException {
		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setId(null);
		atendimentoIndividualDto.getUsuarioDto().setId(prontuario);
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));
		if (atendimentoProfissionalDTO != null) {
			atendimentoIndividualDto.getAtendimentosProfissionalDto().add(atendimentoProfissionalDTO);
		}
		for (int i = 1; i <= 3; i++) {
			atendimentoIndividualDto.setData("0" + i + "/03/2015");
			servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());
		}
	}

	private void servico_gera_tres_atendimentos_de_grupo_com_datas_diferentes_e_altera_a_frenquencia_do_prontuario_para_fu(
			GrupoDTO grupoDto, Long idModuloPeriodo, ResultadoAutenticacaoDTO resultadoDto, Long prontuario)
			throws RemoteException {
		for (int i = 1; i <= 3; i++) {
			ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = servicoSisLaraServerRmi
					.gerarAtendimentos(new EspecificacaoGeracaoAtendimentosDTO(grupoDto.getId(),
							obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(idModuloPeriodo)).getId(),
							"0" + i + "/03/2015", "09:00", "19:00"), resultadoDto.getToken());
			ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) resultadoGeracaoAtendimentoDTO
					.obterObjetoDtoEditado();
			AtendimentoGrupoDTO atendimentoGrupoDTO = moduloPeriodoDTO.getAtendimentosGrupoDto()
					.get(moduloPeriodoDTO.getAtendimentosGrupoDto().size() - 1);
			obterAtendimentoDoUsuarioPeloProntuario(atendimentoGrupoDTO.getAtendimentosUsuariosDto(), prontuario)
					.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));
			servicoSisLaraServerRmi.editarAtendimentoGrupo(atendimentoGrupoDTO, resultadoDto.getToken());
		}
	}

	private void criar_espera_e_agendamento_e_efetuar_associacao_entre_os_dois(ResultadoAutenticacaoDTO resultadoDto)
			throws RemoteException {
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDTO.setUsuarioDto(null);
		especificacaoGeracaoAgendamentoDTO.setDataInicio(DataHoraUtils.formatarData(Calendar.getInstance()));

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());
		AgendamentoDTO agendamentoDto = resultadoGeracaoAgendamento.getObjetosDto().get(0);

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		EsperaDTO esperaDTOIncluida = (EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado();

		EspecificacaoAssociacaoAgendamentoDTO especificacaoAssociacaoAgendamentoDTO = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacaoAssociacaoAgendamentoDTO.setAgendamentoDto(agendamentoDto);
		especificacaoAssociacaoAgendamentoDTO.setEsperaDto(esperaDTOIncluida);
		servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacaoAssociacaoAgendamentoDTO,
				resultadoDto.getToken());
	}

	private ModuloUsuarioDTO servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
			StatusRelacaoComModulo statusUsuario, String nomeGrupoSolicitante, Long idGrupoSolicitante,
			Long idModuloPeriodoSolicitante, Long idModuloUsuarioSolicitante, String nomeGrupoDeReuniaoIntegracao,
			Long idGrupoComModuloReuniaoIntegracao, Long idModuloPeriodoReuniaoIntegracao, Long idUsuario)
			throws RemoteException {
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupoSolicitante), statusUsuario,
				idGrupoSolicitante, idModuloPeriodoSolicitante, idModuloUsuarioSolicitante);
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		GrupoDTO grupoComModuloReuniaoIntegracao = (GrupoDTO) obterDaRelacaoPorId(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupoDeReuniaoIntegracao).getObjetosDto(),
				idGrupoComModuloReuniaoIntegracao);

		ModuloPeriodoDTO moduloPeriodoReuniaoIntegracaoDTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoComModuloReuniaoIntegracao.getModulosPeriodosDto(), idModuloPeriodoReuniaoIntegracao);
		return (ModuloUsuarioDTO) obterPeloIdUsuario(moduloPeriodoReuniaoIntegracaoDTO, new UsuarioDTO(idUsuario));
	}

	private ResultadoEdicaoAtendimentoGrupoDTO servico_gera_atendimento_de_grupo_e_altera_frequencia_para_fu(
			EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO, Long idProntuario,
			ResultadoAutenticacaoDTO resultadoDto) throws RemoteException {
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());
		ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) resultadoGeracaoAtendimentoDTO.obterObjetoDtoEditado();
		AtendimentoGrupoDTO atendimentoGrupoDTO = moduloPeriodoDTO.getAtendimentosGrupoDto()
				.get(moduloPeriodoDTO.getAtendimentosGrupoDto().size() - 1);
		obterAtendimentoDoUsuarioPeloProntuario(atendimentoGrupoDTO.getAtendimentosUsuariosDto(), idProntuario)
				.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));
		return servicoSisLaraServerRmi.editarAtendimentoGrupo(atendimentoGrupoDTO, resultadoDto.getToken());
	}

	private UsuarioDTO servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(Long prontuario,
			SetorDTO setorDTO, FrequenciaDTO frequenciaDTO, DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO,
			ModuloDTO moduloDTO) throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());
		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.getUsuarioDto().setId(prontuario);
		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		atendimentoIndividualDto.setModuloDto(moduloDTO);
		atendimentoIndividualDto.setSetorDto(setorDTO);
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(frequenciaDTO);
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));

		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario.toString());
		ResultadoListagemUsuarioDTO resultadoListagemDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);
		return (UsuarioDTO) obterDaRelacaoPorId(resultadoListagemDto.getObjetosDto(), prontuario);

	}

	private CredencialDTO construirCredencialDtoValida() {
		return new CredencialDTO("pabsantos", "1234");
	}

	private CredencialDTO construirCredencialDtoValidaAdailza() {
		return new CredencialDTO("adailza", "1234");
	}

	private CredencialDTO construirCredencialDtoValidaVVitalino() {
		return new CredencialDTO("vvitalino", "1234");
	}

	private CredencialDTO construirCredencialDtoValidaShivas() {
		return new CredencialDTO("shivas", "1234");
	}

	private CredencialDTO construirCredencialDtoValidaVeraPereira() {
		return new CredencialDTO("vera.pereira", "1234");
	}

	private CredencialDTO construirCredencialAlternativaDtoValida() {
		return new CredencialDTO("rleme", "a", "a");
	}

	private CredencialDTO construirCredencialAlternativaDtoInvalida() {
		return new CredencialDTO("paulo", "a", "c");
	}

	private CredencialDTO construirCredencialDtoValidaSemSenha() {
		return new CredencialDTO("pabsantos", "", "");
	}

	private CredencialDTO construirCredencialDtoSemPermissao() {
		return new CredencialDTO("teste", "teste");
	}

	// Variacao existente em decorrência das datas nao deterministicas no rodape
	// do
	// relatorio
	private boolean variacaoMaximaDeBytes(byte[] bytesObitido, byte[] bytesEsperado) {
		int tamanhoByteObtido = bytesObitido.length;
		int tamanhoByteEsperado = bytesEsperado.length;
		return Math.abs(tamanhoByteObtido - tamanhoByteEsperado) <= 55;
	}

	private void servico_altera_vagas_do_grupo_pelo_modulo_periodo(String nomeGrupo, Long idModuloPeriodo, String vagas,
			TokenDTO tokenDTO) throws RemoteException {
		GrupoDTO grupoSS1Dto = servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupo).getObjetosDto().get(0);
		ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoSS1Dto.getModulosPeriodosDto(),
				idModuloPeriodo);
		moduloPeriodoDTO.setVagas(vagas);
		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDTO, tokenDTO);
	}

	private ResultadoEdicaoGrupoDTO servico_tenta_desativar_grupo_com_usuarios_no_status(
			StatusRelacaoComModulo statusRelacaoComModulo) throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisico = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoAEEPisico.getObjetosDto(),
				new Long(14444));

		ModuloPeriodoDTO moduloPeriodoAEEDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoAeePisicoDto.getModulosPeriodosDto(), new Long(77777));

		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoAEEDto.getModulosUsuariosDto(), new Long(77777));
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(statusRelacaoComModulo.toString()));
		moduloUsuarioDTO.setDataOcorrencia("01/01/2000");

		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoAEEDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisicoAposAtualizacao = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDtoAposAtualizacao = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAEEPisicoAposAtualizacao.getObjetosDto(), new Long(14444));
		grupoAeePisicoDtoAposAtualizacao.setAtivo(false);

		return servicoSisLaraServerRmi.editarGrupo(grupoAeePisicoDtoAposAtualizacao, resultadoDto.getToken());
	}

	private ModuloUsuarioDTO servico_inclui_usuario_com_status_de_relacao_no_aee_e_retorna_o_psicossocial_do_grupo(
			StatusRelacaoComModulo statusAtualizadoNoAEE) throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisico = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoAEEPisico.getObjetosDto(),
				new Long(14444));

		ModuloPeriodoDTO moduloPeriodoAEEDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoAeePisicoDto.getModulosPeriodosDto(), new Long(77777));

		ModuloUsuarioDTO moduloUsuarioDto = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		UsuarioDTO usuarioDto = ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoA();
		moduloUsuarioDto.setUsuarioDto(usuarioDto);
		moduloUsuarioDto.setStatusDto(new StatusRelacaoComModuloDTO(statusAtualizadoNoAEE.toString()));
		moduloUsuarioDto.setDataOcorrencia("01/01/2000");
		moduloUsuarioDto.setObs("A analisar.");

		moduloPeriodoAEEDto.getModulosUsuariosDto().add(moduloUsuarioDto);

		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoAEEDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisicoAposAtualizacao = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDtoAposAtualizacao = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAEEPisicoAposAtualizacao.getObjetosDto(), new Long(14444));

		ModuloPeriodoDTO moduloPeriodoPsicossocialDtoAposAtualizacao = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoAeePisicoDtoAposAtualizacao.getModulosPeriodosDto(), new Long(88888));

		return obterPeloIdUsuario(moduloPeriodoPsicossocialDtoAposAtualizacao, usuarioDto);
	}

	private ModuloPeriodoDTO alterar_status_relacao_usuario_no_modulo_periodo(
			ResultadoListagemGrupoDTO resultadoListagemGrupo, Long idGrupo, Long idModuloPeriodo, Long idModuloUsuario,
			String statusRelacaoUsuario) {
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(), idGrupo,
				idModuloPeriodo);
		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDto.getModulosUsuariosDto(), idModuloUsuario);
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(statusRelacaoUsuario));
		return moduloPeriodoDto;
	}

	private ModuloUsuarioDTO servico_atualiza_status_de_relacao_do_aee_e_retorna_o_psicossocial_do_grupo(
			StatusRelacaoComModulo statusAtualizadoNoAEE) throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisico = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoAEEPisico.getObjetosDto(),
				new Long(14444));

		ModuloPeriodoDTO moduloPeriodoAEEDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoAeePisicoDto.getModulosPeriodosDto(), new Long(77777));

		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoAEEDto.getModulosUsuariosDto(), new Long(77777));
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(statusAtualizadoNoAEE.toString()));
		moduloUsuarioDTO.setDataOcorrencia("01/01/2000");
		moduloUsuarioDTO.setObs("Texto de OBS.");

		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoAEEDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisicoAposAtualizacao = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDtoAposAtualizacao = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAEEPisicoAposAtualizacao.getObjetosDto(), new Long(14444));

		ModuloPeriodoDTO moduloPeriodoPsicossocialDtoAposAtualizacao = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoAeePisicoDtoAposAtualizacao.getModulosPeriodosDto(), new Long(88888));

		return (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoPsicossocialDtoAposAtualizacao.getModulosUsuariosDto(), new Long(88888));
	}

	private ResultadoListagemPendenciaDTO servico_resolve_pendencia_de_atendimento_invidual_de_servico_social_com_pre_cadastro_apos_geracao_de_atendimento_com_pre_cadastro(
			ResultadoAutenticacaoDTO resultadoDto, PreCadastroDTO preCadastroDto) throws RemoteException {
		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(18888));
		agendamentoDto.setPreCadastroDto(preCadastroDto);
		agendamentoDto.setData(DataHoraUtils.formatarData(Calendar.getInstance()));

		servicoSisLaraServerRmi.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		ResultadoListagemPendenciaDTO pendenciasAntesResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setUsuarioDto(null);
		atendimentoIndividualDto.setPreCadastroDto(agendamentoDto.getPreCadastroDto());
		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(agendamentoDto.getDescricaoTipoAtendimentoDto());
		atendimentoIndividualDto.setModuloDto(agendamentoDto.getModuloDto());
		atendimentoIndividualDto.setData(agendamentoDto.getData());
		atendimentoIndividualDto.setHorarioDto(agendamentoDto.getHorarioDto());
		AtendimentoProfissionalDTO atendimentoProfissionalDTO = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTOComAT();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		atendimentoIndividualDto.setAtendimentosProfissionalDto(Arrays.asList(atendimentoProfissionalDTO));

		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());
		return pendenciasAntesResolucaoDto;
	}

	private ResultadoEdicaoModuloPeriodoDTO servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
			ResultadoListagemGrupoDTO resultadoListagemGrupo, StatusRelacaoComModulo statusRelacaoComModulo,
			Long idGrupo, Long idModuloPeriodo, Long idModuloUsuario, TokenDTO tokenDto) throws RemoteException {

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), idGrupo);
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				idModuloPeriodo);
		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDto.getModulosUsuariosDto(), idModuloUsuario);
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(statusRelacaoComModulo.toString()));

		return servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDto, tokenDto);
	}

	private ResultadoEdicaoModuloPeriodoDTO integrar_usuario_ao_grupo_e_modulo_perido(
			ResultadoAutenticacaoDTO resultadoAutenticacaoDto, String nomeGrupo, Long idGrupo, Long idModuloPerido)
			throws RemoteException {
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupo);

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), idGrupo);
		ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				idModuloPerido);

		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.INTEGRADO.toString()));
		moduloPeriodoDTO.setModulosUsuariosDto(Arrays.asList(moduloUsuarioDTO));

		return servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDTO, resultadoAutenticacaoDto.getToken());
	}

	private UsuarioDTO servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
			ResultadoListagemGrupoDTO resultadoListagemGrupo, StatusRelacaoComModulo statusRelacaoComModulo,
			Long idGrupo, Long idModuloPeriodo, Long idModuloUsuario) throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
				resultadoListagemGrupo, statusRelacaoComModulo, idGrupo, idModuloPeriodo, idModuloUsuario,
				resultadoDto.getToken());

		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) resultadoEdicaoModuloPeriodoDTO.obterObjetoDtoEditado();

		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDto.getModulosUsuariosDto(), idModuloUsuario);

		EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDTO = new EspecificacaoPesquisaUsuarioDTO();
		especificacaoPesquisaUsuarioDTO.adicionarParametro(ChavePesquisaDTO.PRONTUARIO,
				moduloUsuarioDTO.getUsuarioDto().getId().toString());
		ResultadoListagemUsuarioDTO resultadoListagemUsuarioDto = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO);
		return resultadoListagemUsuarioDto.getObjetosDto().get(0);
	}

	private List<DocumentoSolicitacaoDoacaoDTO> obterTodosDocumentos(String conteudoArquivo) {
		List<DocumentoSolicitacaoDoacaoDTO> documentos = new ArrayList<>();

		for (NomeDocumento nomeDocumento : NomeDocumento.values()) {
			DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDTO = new DocumentoSolicitacaoDoacaoDTO();
			documentoSolicitacaoDoacaoDTO.setArquivoDTO(
					new ArquivoDTO(null, nomeDocumento.ordinal() + "Teste.pdf", conteudoArquivo.getBytes(), null));
			documentoSolicitacaoDoacaoDTO.setNomeDocumentoDTO(new NomeDocumentoDTO(nomeDocumento.toString()));
			documentos.add(documentoSolicitacaoDoacaoDTO);
		}
		return documentos;
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_login_bloqueado() throws RemoteException {
		CredencialDTO credencialDto = new CredencialDTO("marcos", "marcos");
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(credencialDto);

		Assert.assertFalse(resultadoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_todas_contas_acesso() throws RemoteException {
		EspecificacaoPesquisaContaAcessoDTO especificacaoPesquisaContasAcessoDTO = new EspecificacaoPesquisaContaAcessoDTO();
		especificacaoPesquisaContasAcessoDTO.adicionarParametro(ChavePesquisaDTO.TODOS_CONTAS_ACESSO, "");

		ResultadoListagemContaAcessoDTO listagemContaAcessoDto = servicoSisLaraServerRmi
				.pesquisarContaAcessoPor(especificacaoPesquisaContasAcessoDTO);

		Assert.assertTrue(listagemContaAcessoDto.sucesso());
		Assert.assertEquals(listagemContaAcessoDto.getObjetosDto().size(), 10);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_perfil() throws RemoteException {
		ResultadoListagemPerfilDTO resultadoListagemPerfil = servicoSisLaraServerRmi.obterListagemPerfil();

		Assert.assertTrue(resultadoListagemPerfil.sucesso());
		Assert.assertFalse(resultadoListagemPerfil.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemPerfil.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_pesquisa_listagem_perfil_atraves_de_especificacao() throws RemoteException {
		EspecificacaoPesquisaPerfilDTO especificacao = new EspecificacaoPesquisaPerfilDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_PERFIS, "");
		ResultadoListagemPerfilDTO resultadoListagemPerfilDto = servicoSisLaraServerRmi
				.pesquisarPerfilPor(especificacao);

		Assert.assertTrue(resultadoListagemPerfilDto.sucesso());
		Assert.assertFalse(resultadoListagemPerfilDto.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_pesquisa_listagem_contribuinte_por_nome() throws RemoteException {
		EspecificacaoPesquisaContribuinteDTO especificacao = new EspecificacaoPesquisaContribuinteDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME_CONTRIBUINTE, "ARAKEN");
		ResultadoListagemContribuinteDTO resultadoListagemContribuintesDto = servicoSisLaraServerRmi
				.pesquisarContribuintePor(especificacao);

		Assert.assertTrue(resultadoListagemContribuintesDto.sucesso());
		Assert.assertEquals(resultadoListagemContribuintesDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_pesquisa_listagem_todos_contribuintes() throws RemoteException {
		EspecificacaoPesquisaContribuinteDTO especificacao = new EspecificacaoPesquisaContribuinteDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_CONTRIBUINTES, "");
		ResultadoListagemContribuinteDTO resultadoListagemContribuintesDto = servicoSisLaraServerRmi
				.pesquisarContribuintePor(especificacao);

		Assert.assertTrue(resultadoListagemContribuintesDto.sucesso());
		Assert.assertEquals(resultadoListagemContribuintesDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_pesquisa_listagem_todos_recibos() throws RemoteException {
		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_RECIBOS, "");
		ResultadoListagemReciboDTO resultadoListagemRecibosDto = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);

		Assert.assertTrue(resultadoListagemRecibosDto.sucesso());
		Assert.assertEquals(resultadoListagemRecibosDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_pesquisa_listagem_recibo_sem_dados_para_pesquisa() throws RemoteException {
		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		ResultadoListagemReciboDTO resultadoListagemRecibosDto = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);

		Assert.assertFalse(resultadoListagemRecibosDto.sucesso());
		Assert.assertTrue(resultadoListagemRecibosDto.obterMensagens().contains("Insira o tipo e dados para pesquisa."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_pesquisa_listagem_projeto_sem_dados_para_pesquisa() throws RemoteException {
		EspecificacaoPesquisaProjetoDTO especificacao = new EspecificacaoPesquisaProjetoDTO();
		ResultadoListagemProjetoDTO resultadoListagemRecibosDto = servicoSisLaraServerRmi
				.pesquisarProjetoPor(especificacao);

		Assert.assertFalse(resultadoListagemRecibosDto.sucesso());
		Assert.assertTrue(resultadoListagemRecibosDto.obterMensagens().contains("Insira o tipo e dados para pesquisa."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_pesquisa_contribuintes() throws RemoteException {
		EspecificacaoPesquisaContribuinteDTO especificacao = new EspecificacaoPesquisaContribuinteDTO();

		ResultadoListagemContribuinteDTO resultadoListagemContribuintesDto = servicoSisLaraServerRmi
				.pesquisarContribuintePor(especificacao);

		Assert.assertFalse(resultadoListagemContribuintesDto.sucesso());
		Assert.assertEquals(resultadoListagemContribuintesDto.obterMensagens(),
				"Insira o tipo e dados para pesquisa.\nNenhum registro encontrado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_permissao() throws RemoteException {
		ResultadoListagemPermissaoDTO resultadoListagemPermissao = servicoSisLaraServerRmi.obterListagemPermissao();

		Assert.assertTrue(resultadoListagemPermissao.sucesso());
		Assert.assertFalse(resultadoListagemPermissao.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemPermissao.getObjetosDto().size(), 98);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_perfil_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		PerfilDTO perfilDto = new PerfilDTO(null, "Teste");

		ResultadoEdicaoPerfilDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarPerfil(perfilDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_perfil_sem_sucesso_por_falta_de_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		PerfilDTO perfilDto = new PerfilDTO(null, "Teste");

		ResultadoEdicaoPerfilDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarPerfil(perfilDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_autenticacao_a_partir_de_credencial_valida() throws Exception {
		CredencialDTO credencialDto = construirCredencialDtoValida();

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(credencialDto);

		Assert.assertTrue(resultadoDto.sucesso(), "A conta de acesso deveria ter sido autenticada.");
		Assert.assertNotNull(resultadoDto.getToken(), "Um token deveria ser gerado.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nega_autenticacao_a_partir_de_credencial_invalida() throws Exception {
		CredencialDTO credencialDto = new CredencialDTO("invalida", "999999");

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(credencialDto);

		Assert.assertFalse(resultadoDto.sucesso(), "A autenticação deveria ser negada.");
		Assert.assertEquals(resultadoDto.obterMensagens(), "Usuário ou senha inválido. Tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_conta_acesso_pelo_administrador_com_sucesso() throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setUsuario("josep");
		contaAcessoDto.setSenha("meaza");
		contaAcessoDto.setPerfilDto(new PerfilDTO(new Long(1111), "Administrador"));

		ResultadoEdicaoContaAcessoDTO resultadoEdicaoContaAcessoDto = servicoSisLaraServerRmi
				.editarContaAcesso(contaAcessoDto, tokenDto.getToken());

		Assert.assertTrue(resultadoEdicaoContaAcessoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_conta_acesso_pelo_administrador_sem_sucesso() throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setUsuario("josep");
		contaAcessoDto.setSenha("meaza");
		contaAcessoDto.setPerfilDto(new PerfilDTO(new Long(1111), "Administrador"));

		ResultadoEdicaoContaAcessoDTO resultadoEdicaoContaAcessoDto = servicoSisLaraServerRmi
				.editarContaAcesso(contaAcessoDto, tokenDto.getToken());

		Assert.assertFalse(resultadoEdicaoContaAcessoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_conta_acesso_invalida_pelo_administrador_sem_sucesso() throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();

		ResultadoEdicaoContaAcessoDTO resultadoEdicaoContaAcessoDto = servicoSisLaraServerRmi
				.editarContaAcesso(contaAcessoDto, tokenDto.getToken());

		Assert.assertFalse(resultadoEdicaoContaAcessoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_ativa_filtro_grupo_na_conta_cesso_sem_sucesso() throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDto = servicoSisLaraServerRmi
				.ativarFiltroGrupo(tokenDto.getToken());

		boolean filtroAtivo = servicoSisLaraServerRmi.filtroEstaAtivado(tokenDto.getToken());

		Assert.assertTrue(resultadoOperacaoFiltroGrupoDto.sucesso());
		Assert.assertTrue(filtroAtivo);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_desativa_filtro_grupo_na_conta_cesso_sem_sucesso() throws Exception {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		servicoSisLaraServerRmi.ativarFiltroGrupo(tokenDto.getToken());
		ResultadoOperacaoFiltroGrupoDTO resultadoOperacaoFiltroGrupoDto = servicoSisLaraServerRmi
				.desativarFiltroGrupo(tokenDto.getToken());

		boolean filtroAtivo = servicoSisLaraServerRmi.filtroEstaAtivado(tokenDto.getToken());

		Assert.assertTrue(resultadoOperacaoFiltroGrupoDto.sucesso());
		Assert.assertFalse(filtroAtivo);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_classificacaosocial() throws RemoteException {
		ResultadoListagemClassificacaoSocialDTO resultadoDto = servicoSisLaraServerRmi
				.obterListagemClassificacaoSocial();

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status() throws RemoteException {
		ResultadoListagemStatusDTO resultadoListagemStatus = servicoSisLaraServerRmi.obterListagemStatus();

		Assert.assertTrue(resultadoListagemStatus.sucesso());
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_filial() throws RemoteException {
		ResultadoListagemFilialDTO resultadoListagemFilial = servicoSisLaraServerRmi.obterListagemFilial();

		Assert.assertTrue(resultadoListagemFilial.sucesso());
		Assert.assertFalse(resultadoListagemFilial.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemFilial.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_contribuinte() throws RemoteException {
		ResultadoListagemStatusContribuinteDTO resultadoListagemStatus = servicoSisLaraServerRmi
				.obterListagemStatusContribuinte();

		Assert.assertTrue(resultadoListagemStatus.sucesso());
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_motivo_desativacao() throws RemoteException {
		ResultadoListagemMotivoDesativacaoDTO resultadoListagemMotivoDesativacao = servicoSisLaraServerRmi
				.obterListagemMotivoDesativacao();

		Assert.assertTrue(resultadoListagemMotivoDesativacao.sucesso());
		Assert.assertFalse(resultadoListagemMotivoDesativacao.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_familiar() throws RemoteException {
		ResultadoListagemStatusDTO resultadoListagemStatus = servicoSisLaraServerRmi.obterListagemStatusFamiliar();

		Assert.assertTrue(resultadoListagemStatus.sucesso());
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_atendimento_individual() throws RemoteException {
		ResultadoListagemStatusDTO resultadoListagemStatus = servicoSisLaraServerRmi
				.obterListagemStatusDisponiveisParaAtendimentoIndividual();

		Assert.assertTrue(resultadoListagemStatus.sucesso());
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_para_integracao() throws RemoteException {
		ResultadoListagemGrupoDTO resultadoListagemGrupoDTO = servicoSisLaraServerRmi.obterListagemGrupoAtivo("OSE-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoDTO.getObjetosDto(), new Long(821582));

		StatusRelacaoComModuloDTO resultadoStatus = servicoSisLaraServerRmi.obterStatusRelacaoPadrao(grupoDto);

		Assert.assertEquals(resultadoStatus.toString(), StatusRelacaoComModulo.EVENTUAL.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_para_agendamento() throws RemoteException {
		ResultadoListagemStatusDTO resultadoListagemStatus = servicoSisLaraServerRmi
				.obterListagemStatusDisponiveisParaAgendamento();

		Assert.assertTrue(resultadoListagemStatus.sucesso());
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_genero() throws RemoteException {
		ResultadoListagemGeneroDTO resultadoListagemGenero = servicoSisLaraServerRmi.obterListagemGenero();

		Assert.assertTrue(resultadoListagemGenero.sucesso());
		Assert.assertFalse(resultadoListagemGenero.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_beneficio() throws RemoteException {
		ResultadoListagemStatusBeneficioDTO resultadoListagemStatusBeneficio = servicoSisLaraServerRmi
				.obterListagemStatusBeneficio();

		Assert.assertTrue(resultadoListagemStatusBeneficio.sucesso());
		Assert.assertFalse(resultadoListagemStatusBeneficio.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_epoca_incidencia() throws RemoteException {
		ResultadoListagemEpocaIncidenciaDTO resultadoListagemEpocaIncidencia = servicoSisLaraServerRmi
				.obterListagemEpocaIncidencia();

		Assert.assertTrue(resultadoListagemEpocaIncidencia.sucesso());
		Assert.assertFalse(resultadoListagemEpocaIncidencia.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_uf() throws RemoteException {
		ResultadoListagemUfDTO resultadoListagemUf = servicoSisLaraServerRmi.obterListagemUf();

		Assert.assertTrue(resultadoListagemUf.sucesso());
		Assert.assertFalse(resultadoListagemUf.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_estadoscivil() throws RemoteException {
		ResultadoListagemEstadoCivilDTO resultadoListagemEstadoCivil = servicoSisLaraServerRmi
				.obterListagemEstadoCivil();

		Assert.assertTrue(resultadoListagemEstadoCivil.sucesso());
		Assert.assertFalse(resultadoListagemEstadoCivil.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_grupoetnico() throws RemoteException {
		ResultadoListagemGrupoEtnicoDTO resultadoListagemGrupoEtnico = servicoSisLaraServerRmi
				.obterListagemGrupoEtnico();

		Assert.assertTrue(resultadoListagemGrupoEtnico.sucesso());
		Assert.assertFalse(resultadoListagemGrupoEtnico.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_zona() throws RemoteException {
		ResultadoListagemZonaDTO resultadoListagemZona = servicoSisLaraServerRmi.obterListagemZona();

		Assert.assertTrue(resultadoListagemZona.sucesso());
		Assert.assertFalse(resultadoListagemZona.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_simnao() throws RemoteException {
		ResultadoListagemSimNaoDTO resultadoListagemSimNao = servicoSisLaraServerRmi.obterListagemSimNao();

		Assert.assertTrue(resultadoListagemSimNao.sucesso());
		Assert.assertFalse(resultadoListagemSimNao.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_setor() throws RemoteException {
		ResultadoListagemSetorDTO resultadoListagemSetor = servicoSisLaraServerRmi.obterListagemSetor();

		Assert.assertTrue(resultadoListagemSetor.sucesso());
		Assert.assertFalse(resultadoListagemSetor.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_paises() throws RemoteException {
		ResultadoListagemPaisDTO resultadoListagemPais = servicoSisLaraServerRmi.obterListagemPais();

		Assert.assertTrue(resultadoListagemPais.sucesso());
		Assert.assertFalse(resultadoListagemPais.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_area_formacao() throws RemoteException {
		ResultadoListagemAreaFormacaoDTO resultadoListagemAreaFormacao = servicoSisLaraServerRmi
				.obterListagemAreaFormacao();

		Assert.assertTrue(resultadoListagemAreaFormacao.sucesso());
		Assert.assertFalse(resultadoListagemAreaFormacao.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_municipios() throws RemoteException {
		ResultadoListagemMunicipioDTO resultadoListagemMunicipios = servicoSisLaraServerRmi
				.obterListagemMunicipio(new UfDTO("SP"));

		Assert.assertTrue(resultadoListagemMunicipios.sucesso());
		Assert.assertFalse(resultadoListagemMunicipios.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_escolaridade() throws RemoteException {
		ResultadoListagemEscolaridadeDTO resultadoListagemEscolaridade = servicoSisLaraServerRmi
				.obterListagemEscolaridade();

		Assert.assertTrue(resultadoListagemEscolaridade.sucesso());
		Assert.assertFalse(resultadoListagemEscolaridade.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_situacao() throws RemoteException {
		ResultadoListagemSituacaoDTO resultadoListagemSituacao = servicoSisLaraServerRmi.obterListagemSituacao();

		Assert.assertTrue(resultadoListagemSituacao.sucesso());
		Assert.assertFalse(resultadoListagemSituacao.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_periodo() throws RemoteException {
		ResultadoListagemPeriodoDTO resultadoListagemPeriodo = servicoSisLaraServerRmi.obterListagemPeriodo();

		Assert.assertTrue(resultadoListagemPeriodo.sucesso());
		Assert.assertFalse(resultadoListagemPeriodo.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_novo_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioASalvar = ContextoUsuario.construirUsuarioDTO();
		ResultadoEdicaoUsuarioDTO resultadoInclusaoUsuaro = servicoSisLaraServerRmi.editarUsuario(usuarioASalvar,
				resultadoDto.getToken());

		UsuarioDTO usuarioIncluido = (UsuarioDTO) resultadoInclusaoUsuaro.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoInclusaoUsuaro.sucesso());
		Assert.assertEquals(usuarioIncluido.getStatusUsuarioAtualDto().toString(), Status.CASO_NOVO.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_usuario_existente() throws RemoteException {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioNovo = ContextoUsuario.construirUsuarioDTO();
		ResultadoEdicaoUsuarioDTO resultadoInclusaoUsuario = servicoSisLaraServerRmi.editarUsuario(usuarioNovo,
				tokenDto.getToken());
		UsuarioDTO usuarioSalvo = (UsuarioDTO) resultadoInclusaoUsuario.obterObjetoDtoEditado();
		ResultadoEdicaoUsuarioDTO resultadoAlteracaoUsuario = servicoSisLaraServerRmi.editarUsuario(usuarioSalvo,
				tokenDto.getToken());

		Assert.assertTrue(resultadoAlteracaoUsuario.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_muda_status_usuario_ja_existente() throws RemoteException {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME, "Carlos");
		ResultadoListagemUsuarioDTO resultadoListagemUsuario = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacao);
		UsuarioDTO usuarioSemStatusJaExistente = (UsuarioDTO) obterDaRelacaoPorId(
				resultadoListagemUsuario.getObjetosDto(), new Long(13333));
		usuarioSemStatusJaExistente.setAssociadoAoSetorCTO(true);
		usuarioSemStatusJaExistente.getInformacaoEssencialDto().setRg("1234");
		usuarioSemStatusJaExistente.setResponsavelPorSiMesmo(true);
		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setCep("01151000");
		enderecoDto.setEndereco("Rua Brigadeiro Galvão");
		enderecoDto.setNumero("344A");
		enderecoDto.setComplemento("AP444");
		enderecoDto.setZonaDto(new ZonaDTO(Zona.LESTE.toString()));
		enderecoDto.setBairro("Marambaia");
		enderecoDto.setMunicipioDto(new MunicipioDTO(new Long(4850), "São Paulo", new UfDTO("SP")));
		enderecoDto.setUfDto(new UfDTO(UF.SP.toString()));
		enderecoDto.setPaisDto(new PaisDTO(new Long(1), "Brasil"));
		usuarioSemStatusJaExistente.getInformacaoEssencialDto().setEnderecoDto(enderecoDto);

		ResultadoEdicaoUsuarioDTO resultadoAlteracaoUsuario = servicoSisLaraServerRmi
				.editarUsuario(usuarioSemStatusJaExistente, tokenDto.getToken());

		UsuarioDTO usuarioSemStatusJaExistenteAposEdicao = (UsuarioDTO) resultadoAlteracaoUsuario
				.obterObjetoDtoEditado();
		Assert.assertTrue(resultadoAlteracaoUsuario.sucesso());
		Assert.assertNull(usuarioSemStatusJaExistenteAposEdicao.getStatusUsuarioAtualDto());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_usuario_devido_a_concorrencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME, "Augusto");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		UsuarioDTO usuarioNovo = (UsuarioDTO) obterDaRelacaoPorId(resultadoDto.getObjetosDto(), new Long(12222));
		usuarioNovo.setVersao("12222");

		ResultadoEdicaoUsuarioDTO resultadoAlteracaoUsuario = servicoSisLaraServerRmi.editarUsuario(usuarioNovo,
				resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoUsuario.sucesso());
		Assert.assertEquals(resultadoAlteracaoUsuario.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_usuario_com_inconsistencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioASalvar = ContextoUsuario.construirUsuarioDTO();
		usuarioASalvar.getInformacaoEssencialDto().setCpf("32487239847");
		ResultadoEdicaoUsuarioDTO resultadoInclusaoUsuaro = servicoSisLaraServerRmi.editarUsuario(usuarioASalvar,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoUsuaro.sucesso());
		Assert.assertNotNull(resultadoInclusaoUsuaro.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_usuario_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		UsuarioDTO usuarioASalvar = ContextoUsuario.construirUsuarioDTO();
		ResultadoEdicaoUsuarioDTO resultadoInclusaoUsuaro = servicoSisLaraServerRmi.editarUsuario(usuarioASalvar,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoUsuaro.sucesso());
		Assert.assertNotNull(resultadoInclusaoUsuaro.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_permissao_por_conta_acesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());
		ResultadoListagemPermissaoDTO resultadoListagemPermissaoDto = servicoSisLaraServerRmi
				.obterPermissoes(resultadoDto.getToken());

		Assert.assertTrue(resultadoListagemPermissaoDto.sucesso());
		Assert.assertEquals(resultadoListagemPermissaoDto.getObjetosDto().size(), 65);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_a_partir_de_nome() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME, "Augusto");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_a_partir_de_familiar() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.FAMILIAR, "Jose");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_a_partir_de_instituicao() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME, "Augusto");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_permite_autorizacao_acesso_a_funcionalidade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		boolean possuiAutorizacao = servicoSisLaraServerRmi.possuiAutorizacao(resultadoDto.getToken(),
				"USUARIO_EDICAO");

		Assert.assertTrue(possuiAutorizacao);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nega_autorizacao_acesso_a_funcionalidade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		boolean possuiNegado = servicoSisLaraServerRmi.possuiAutorizacao(resultadoDto.getToken(), "USUARIO_EDICAO");

		Assert.assertFalse(possuiNegado);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_a_partir_de_rg() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.RG, "23456");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_inexistentes_a_partir_de_nome() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME, "Chivas");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_inexistentes_a_partir_de_rg() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.RG, "178278");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuario_a_partir_de_prontuario() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, "12222");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuario_inexistente_a_partir_de_prontuario() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, "99999");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuario_a_partir_de_prontuario_invalido() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, "AAAAAAAAABBBBBAAAAAA");

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertFalse(resultadoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_usuarios_a_partir_de_especificacao_vazia() throws RemoteException {
		EspecificacaoPesquisaUsuarioDTO especificacao = new EspecificacaoPesquisaUsuarioDTO();

		ResultadoListagemUsuarioDTO resultadoDto = servicoSisLaraServerRmi.pesquisarUsuarioPor(especificacao);

		Assert.assertEquals(resultadoDto.sucesso(), false);
		Assert.assertFalse(resultadoDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipoinstituicao() throws RemoteException {
		ResultadoListagemTipoInstituicaoDTO resultadoListagemTipoInstituicao = servicoSisLaraServerRmi
				.obterListagemTipoInstituicao();

		Assert.assertTrue(resultadoListagemTipoInstituicao.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemTipoInstituicao.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_classificaoinstituicao() throws RemoteException {
		ResultadoListagemClassificacaoInstituicaoDTO resultadoListagemClassificaocaoInstituicao = servicoSisLaraServerRmi
				.obterListagemClassificacaoInstituicao();

		Assert.assertTrue(resultadoListagemClassificaocaoInstituicao.sucesso(),
				"A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemClassificaocaoInstituicao.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipocertidao() throws RemoteException {
		ResultadoListagemTipoCertidaoDTO resultadoListagemTipoCertidao = servicoSisLaraServerRmi
				.obterListagemTipoCertidao();

		Assert.assertTrue(resultadoListagemTipoCertidao.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemTipoCertidao.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipoleitura() throws RemoteException {
		ResultadoListagemTipoLeituraDTO resultadoListagemTipoLeitura = servicoSisLaraServerRmi
				.obterListagemTipoLeitura();

		Assert.assertTrue(resultadoListagemTipoLeitura.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemTipoLeitura.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_cargo() throws RemoteException {
		ResultadoListagemCargoDTO resultadoListagemCargo = servicoSisLaraServerRmi.obterListagemCargo();

		Assert.assertTrue(resultadoListagemCargo.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemCargo.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_local_trabalho() throws RemoteException {
		ResultadoListagemLocalTrabalhoDTO resultadoListagemLocalTrabalho = servicoSisLaraServerRmi
				.obterListagemLocalTrabalho();

		Assert.assertTrue(resultadoListagemLocalTrabalho.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemLocalTrabalho.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipoespecialidade() throws RemoteException {
		ResultadoListagemTipoEspecialidadeDTO resultadoListagemTipoEspecialidade = servicoSisLaraServerRmi
				.obterListagemTipoEspecialidade();

		Assert.assertTrue(resultadoListagemTipoEspecialidade.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemTipoEspecialidade.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipoapoio() throws RemoteException {
		ResultadoListagemTipoApoioDTO resultadoListagemTipoApoio = servicoSisLaraServerRmi.obterListagemTipoApoio();

		Assert.assertTrue(resultadoListagemTipoApoio.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemTipoApoio.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_drecefai() throws RemoteException {
		ResultadoListagemDreCefaiDTO resultadoListagemDreCefai = servicoSisLaraServerRmi.obterListagemDreCefai();

		Assert.assertTrue(resultadoListagemDreCefai.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemDreCefai.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_diretoriaensino() throws RemoteException {
		ResultadoListagemDiretoriaEnsinoDTO resultadoListagemDiretoriaEnsino = servicoSisLaraServerRmi
				.obterListagemDiretoriaEnsino();

		Assert.assertTrue(resultadoListagemDiretoriaEnsino.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemDiretoriaEnsino.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_instituicoes() throws RemoteException {
		ResultadoListagemInstituicaoDTO resultadoListagemInstituicoes = servicoSisLaraServerRmi
				.obterListagemInstituicao();

		Assert.assertTrue(resultadoListagemInstituicoes.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemInstituicoes.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_instituicoes_com_srms() throws RemoteException {
		ResultadoListagemInstituicaoDTO resultadoListagemInstituicoes = servicoSisLaraServerRmi
				.obterListagemInstituicaoComSRMs();

		Assert.assertTrue(resultadoListagemInstituicoes.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemInstituicoes.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_instituicoes_com_sala_recurso() throws RemoteException {
		ResultadoListagemInstituicaoDTO resultadoListagemInstituicoes = servicoSisLaraServerRmi
				.obterListagemInstituicaoComSalaRecurso();

		Assert.assertTrue(resultadoListagemInstituicoes.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemInstituicoes.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_instituicoes_com_outros_aee() throws RemoteException {
		ResultadoListagemInstituicaoDTO resultadoListagemInstituicoes = servicoSisLaraServerRmi
				.obterListagemInstituicaoComOutrosAEE();

		Assert.assertTrue(resultadoListagemInstituicoes.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemInstituicoes.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_recursos() throws RemoteException {
		ResultadoListagemRecursoDTO resultadoListagemRecurso = servicoSisLaraServerRmi.obterListagemRecurso();

		Assert.assertTrue(resultadoListagemRecurso.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemRecurso.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_instituicao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		InstituicaoDTO instituicaoASalvar = ContextoInstituicao.construirInstitucaoDTO();

		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicao = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoInstituicao.sucesso());
		Assert.assertEquals(instituicaoASalvar.getNome(),
				((InstituicaoDTO) resultadoInclusaoInstituicao.obterObjetoDtoEditado()).getNome());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_instituicao_repetida() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		InstituicaoDTO instituicaoASalvar = ContextoInstituicao.construirInstitucaoDTO();
		instituicaoASalvar.setId(null);
		instituicaoASalvar.setNome("TESTE");

		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicao = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoASalvar, resultadoDto.getToken());

		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicaoDuplicado = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoInstituicao.sucesso());
		Assert.assertFalse(resultadoInclusaoInstituicaoDuplicado.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_instituicao_existente() throws RemoteException {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		InstituicaoDTO instituicaoNovaDto = ContextoInstituicao.construirInstitucaoDTO();
		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicao = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoNovaDto, tokenDto.getToken());
		InstituicaoDTO instituicaoSalva = (InstituicaoDTO) resultadoInclusaoInstituicao.obterObjetoDtoEditado();
		instituicaoNovaDto.setId(instituicaoSalva.getId());
		instituicaoNovaDto.setNome("NOVO NOME");
		ResultadoEdicaoInstituicaoDTO resultadoAlteracaoUsuario = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoNovaDto, tokenDto.getToken());

		Assert.assertTrue(resultadoAlteracaoUsuario.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_instituicao_repetida() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		InstituicaoDTO instituicaoASalvar = ContextoInstituicao.construirInstitucaoDTO();
		instituicaoASalvar.setId(new Long(12222));

		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicao = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoInstituicao.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_instituicao_com_inconsistencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		List<TelefoneDTO> telefonesDto = new ArrayList<>();
		telefonesDto.add(new TelefoneDTO(new TipoTelefoneDTO("RESIDENCIAL"), "12345678901234567"));

		InstituicaoDTO instituicaoASalvar = ContextoInstituicao.construirInstitucaoDTO();
		instituicaoASalvar.getContatoDto().setTelefonesDto(telefonesDto);
		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicao = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoInstituicao.sucesso());
		Assert.assertNotNull(resultadoInclusaoInstituicao.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_instituicao_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		InstituicaoDTO instituicaoASalvar = ContextoInstituicao.construirInstitucaoDTO();
		ResultadoEdicaoInstituicaoDTO resultadoInclusaoInstituicao = servicoSisLaraServerRmi
				.editarInstituicao(instituicaoASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoInstituicao.sucesso());
		Assert.assertNotNull(resultadoInclusaoInstituicao.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_instituicao_a_partir_de_nome() throws RemoteException {
		EspecificacaoPesquisaInstituicaoDTO especificacao = new EspecificacaoPesquisaInstituicaoDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME_INSTITUICAO, "Escola Joao");

		ResultadoListagemInstituicaoDTO resultadoDto = servicoSisLaraServerRmi.pesquisarInstituicaoPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_solicitacao_relatorio_a_partir_de_prontuario() throws RemoteException {
		EspecificacaoPesquisaSolicitacaoRelatorioDTO especificacao = new EspecificacaoPesquisaSolicitacaoRelatorioDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, "12222");

		ResultadoListagemSolicitacaoRelatorioDTO resultadoDto = servicoSisLaraServerRmi
				.pesquisarSolicitacaoRelatorioPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_instituicao_inexistentes_a_partir_de_nome() throws RemoteException {
		EspecificacaoPesquisaInstituicaoDTO especificacao = new EspecificacaoPesquisaInstituicaoDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME_INSTITUICAO, "Instituto NOVO");

		ResultadoListagemInstituicaoDTO resultadoDto = servicoSisLaraServerRmi.pesquisarInstituicaoPor(especificacao);

		Assert.assertTrue(resultadoDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_instituicao_a_partir_de_especificacao_vazia() throws RemoteException {
		EspecificacaoPesquisaInstituicaoDTO especificacao = new EspecificacaoPesquisaInstituicaoDTO();

		ResultadoListagemInstituicaoDTO resultadoDto = servicoSisLaraServerRmi.pesquisarInstituicaoPor(especificacao);

		Assert.assertEquals(resultadoDto.sucesso(), false);
		Assert.assertFalse(resultadoDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_precadastro_a_partir_de_nome() throws RemoteException {
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRE_CADASTRO, "Josep Meaza");

		ResultadoListagemPreCadastroDTO resultadoDto = servicoSisLaraServerRmi.pesquisarPreCadastroPor(especificacao);

		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_novo_precadastro() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		PreCadastroDTO preCadastroASalvar = ContextoPreCadastro.construirPreCadastroDTOsemIdentificacao();
		preCadastroASalvar.getInformacaoEssencialDto().setRg("88888X");

		ResultadoEdicaoPreCadastroDTO resultadoInclusaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoPreCadastro.sucesso());
		Assert.assertEquals(preCadastroASalvar.getInformacaoEssencialDto().getNome(),
				((PreCadastroDTO) resultadoInclusaoPreCadastro.obterObjetoDtoEditado()).getInformacaoEssencialDto()
						.getNome());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_novo_precadastro_com_cpf_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		PreCadastroDTO preCadastroASalvar = ContextoPreCadastro.construirPreCadastroDTOsemIdentificacao();
		preCadastroASalvar.getInformacaoEssencialDto().setCpf("71894810287");

		servicoSisLaraServerRmi.editarPreCadastro(preCadastroASalvar, resultadoDto.getToken());
		ResultadoEdicaoPreCadastroDTO resultadoInclusaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoPreCadastro.sucesso());
		Assert.assertEquals(resultadoInclusaoPreCadastro.obterMensagens().trim(), "Cpf já foi cadastrado.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_precadastro_com_cpf_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRE_CADASTRO, "Josep Meaza");
		ResultadoListagemPreCadastroDTO resultadoPesquisaPreCadastroDto = servicoSisLaraServerRmi
				.pesquisarPreCadastroPor(especificacao);

		PreCadastroDTO preCadastroDto = (PreCadastroDTO) obterDaRelacaoPorId(
				resultadoPesquisaPreCadastroDto.getObjetosDto(), new Long(12222));

		preCadastroDto.getInformacaoEssencialDto().setCpf("71894810287");
		ResultadoEdicaoPreCadastroDTO resultadoAlteracaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoPreCadastro.sucesso());
		Assert.assertEquals(resultadoAlteracaoPreCadastro.obterMensagens().trim(), "Cpf já foi cadastrado.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_solicitacao_relatorio_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_solicitacao_relatorio_pois_ja_existe_solicitacao_feita_a_menos_de_seis_meses()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		MaquinaTempo.mudarDataAtual("01/01/2016");
		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setFinalidadesRelatoriosDto(Arrays.asList(new FinalidadeRelatorioDTO("ATESTADO")));
		solicitacaoRelatorioASalvar.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoA());

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertFalse(resultadoInclusaoSolicitacaoRelatorio.sucesso());
		Assert.assertEquals(resultadoInclusaoSolicitacaoRelatorio.obterMensagens(),
				"Não é possível realizar a operação. Já foi solicitado relatório nos últimos 6 meses.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_solicitacao_relatorio_pois_ja_passou_seis_meses_desde_a_ultima_solicitacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		MaquinaTempo.mudarDataAtual("01/07/2016");
		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setFinalidadesRelatoriosDto(Arrays.asList(new FinalidadeRelatorioDTO("ATESTADO")));
		solicitacaoRelatorioASalvar.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoA());

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertTrue(resultadoInclusaoSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_solicitacao_relatorio_com_menos_de_seis_meses_e_finalidade_inss()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		MaquinaTempo.mudarDataAtual("01/01/2016");
		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setFinalidadesRelatoriosDto(Arrays.asList(new FinalidadeRelatorioDTO("INSS")));
		solicitacaoRelatorioASalvar.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoA());

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertTrue(resultadoInclusaoSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_solicitacao_relatorio_com_menos_de_seis_meses_elaborado_pela_ortoptica()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		MaquinaTempo.mudarDataAtual("01/01/2016");
		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setElaboradorDto(new ElaboradorDTO("ORTOPTICA"));
		solicitacaoRelatorioASalvar.setFinalidadesRelatoriosDto(Arrays.asList(new FinalidadeRelatorioDTO("ATESTADO")));
		solicitacaoRelatorioASalvar.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoA());

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertTrue(resultadoInclusaoSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_solicitacao_relatorio_com_menos_de_seis_meses_elaborado_pela_oftalmologia_com_existencia_de_outra_solicitacao_de_elaboracao_da_ortoptica()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		MaquinaTempo.mudarDataAtual("01/01/2016");
		SolicitacaoRelatorioDTO solicitacaoRelatorioOrtopticaASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioOrtopticaASalvar.setElaboradorDto(new ElaboradorDTO("ORTOPTICA"));
		solicitacaoRelatorioOrtopticaASalvar
				.setFinalidadesRelatoriosDto(Arrays.asList(new FinalidadeRelatorioDTO("ATESTADO")));
		solicitacaoRelatorioOrtopticaASalvar
				.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoB());

		SolicitacaoRelatorioDTO solicitacaoRelatorioOftalmologiaASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioOftalmologiaASalvar.setElaboradorDto(new ElaboradorDTO("OFTALMOLOGIA"));
		solicitacaoRelatorioOftalmologiaASalvar
				.setFinalidadesRelatoriosDto(Arrays.asList(new FinalidadeRelatorioDTO("ATESTADO")));
		solicitacaoRelatorioOftalmologiaASalvar
				.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoB());

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorioOrtoptica = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioOrtopticaASalvar, resultadoDto.getToken());

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorioOftalmologia = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioOftalmologiaASalvar, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertTrue(resultadoInclusaoSolicitacaoRelatorioOrtoptica.sucesso());
		Assert.assertTrue(resultadoInclusaoSolicitacaoRelatorioOftalmologia.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_encaminhamento_recepcao_da_solicitacao_relatorio_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEncaminhamentoPelaRecepcaoDeSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarEncaminhamentoRecepcao(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoEncaminhamentoPelaRecepcaoDeSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_emissao_profissional_da_solicitacao_relatorio_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEmissaoProfissionalSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarEmissaoProfissional(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoEmissaoProfissionalSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_entrega_da_solicitacao_relatorio_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setStatusDaEntrega(
				new StatusSolicitacaoRelatorioDTO(StatusSolicitacaoRelatorio.ENTREGUE_PARA_FAMILIA.toString()));

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEntregaSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarEntrega(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoEntregaSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_cancelamento_da_solicitacao_relatorio_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEntregaSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarCancelamento(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertTrue(resultadoEntregaSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_da_solicitacao_relatorio_sem_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setUsuarioDto(null);

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEntregaSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarCancelamento(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoEntregaSolicitacaoRelatorio.sucesso());
		Assert.assertEquals(resultadoEntregaSolicitacaoRelatorio.obterMensagens(), "Insira um Usuário.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_emissao_da_solicitacao_relatorio_sem_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setUsuarioDto(null);

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEntregaSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarEmissaoProfissional(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoEntregaSolicitacaoRelatorio.sucesso());
		Assert.assertEquals(resultadoEntregaSolicitacaoRelatorio.obterMensagens(), "Insira um Usuário.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_encaminhamento_da_solicitacao_relatorio_sem_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setUsuarioDto(null);

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEntregaSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarEncaminhamentoRecepcao(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoEntregaSolicitacaoRelatorio.sucesso());
		Assert.assertEquals(resultadoEntregaSolicitacaoRelatorio.obterMensagens(), "Insira um Usuário.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_entrega_da_solicitacao_relatorio_sem_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setUsuarioDto(null);

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoEntregaSolicitacaoRelatorio = servicoSisLaraServerRmi
				.efetuarEntrega(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoEntregaSolicitacaoRelatorio.sucesso());
		Assert.assertEquals(resultadoEntregaSolicitacaoRelatorio.obterMensagens(),
				"Insira um Usuário.\nInsira um Status de Entrega.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_solicitacao_relatorio_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoSolicitacaoRelatorio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_solicitacao_relatorio_sem_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		SolicitacaoRelatorioDTO solicitacaoRelatorioASalvar = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();
		solicitacaoRelatorioASalvar.setUsuarioDto(null);

		ResultadoEdicaoSolicitacaoRelatorioDTO resultadoInclusaoSolicitacaoRelatorio = servicoSisLaraServerRmi
				.editarSolicitacaoRelatorio(solicitacaoRelatorioASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoSolicitacaoRelatorio.sucesso());
		Assert.assertEquals(resultadoInclusaoSolicitacaoRelatorio.obterMensagens(), "Insira um Usuário.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_precadastro_existente() throws RemoteException {
		ResultadoAutenticacaoDTO tokenDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		PreCadastroDTO preCadastroNovaDto = ContextoPreCadastro.construirPreCadastroDTOsemIdentificacao();

		ResultadoEdicaoPreCadastroDTO resultadoInclusaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroNovaDto, tokenDto.getToken());

		InformacaoEssencialDTO informacaoEssencialDto = ((PreCadastroDTO) resultadoInclusaoPreCadastro
				.obterObjetoDtoEditado()).getInformacaoEssencialDto();
		informacaoEssencialDto.setNome("NOVO NOME");

		PreCadastroDTO preCadastroSalva = (PreCadastroDTO) resultadoInclusaoPreCadastro.obterObjetoDtoEditado();
		preCadastroSalva.setInformacaoEssencialDto(informacaoEssencialDto);

		ResultadoEdicaoPreCadastroDTO resultadoAlteracaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroSalva, tokenDto.getToken());

		Assert.assertTrue(resultadoAlteracaoPreCadastro.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_precadastro_com_inconsistencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		List<TelefoneDTO> telefonesDTOs = new ArrayList<>();
		telefonesDTOs.add(new TelefoneDTO(new TipoTelefoneDTO("CELULAR"), "12345678901234567"));

		PreCadastroDTO preCadastroASalvar = ContextoPreCadastro.construirPreCadastroDTOsemIdentificacao();
		preCadastroASalvar.getInformacaoEssencialDto().getContatoDto().setTelefonesDto(telefonesDTOs);

		ResultadoEdicaoPreCadastroDTO resultadoInclusaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoPreCadastro.sucesso());
		Assert.assertNotNull(resultadoInclusaoPreCadastro.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_precadastro_com_rg_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		PreCadastroDTO preCadastroASalvar = ContextoPreCadastro.construirPreCadastroDTOsemIdentificacao();

		servicoSisLaraServerRmi.editarPreCadastro(preCadastroASalvar, resultadoDto.getToken());

		PreCadastroDTO preCadastroASalvarComRGRepetido = ContextoPreCadastro.construirPreCadastroDTOsemIdentificacao();
		preCadastroASalvarComRGRepetido.getInformacaoEssencialDto().setCpf("22804540170");

		ResultadoEdicaoPreCadastroDTO resultadoInclusaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroASalvarComRGRepetido, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoPreCadastro.sucesso());
		Assert.assertEquals(resultadoInclusaoPreCadastro.obterMensagens(), "O RG do Usuário já está Pré Cadastrado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_precadastro_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		PreCadastroDTO preCadastroASalvar = ContextoPreCadastro.construirPreCadastroDTO();
		ResultadoEdicaoPreCadastroDTO resultadoInclusaoPreCadastro = servicoSisLaraServerRmi
				.editarPreCadastro(preCadastroASalvar, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoPreCadastro.sucesso());
		Assert.assertNotNull(resultadoInclusaoPreCadastro.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_precadastro_a_partir_de_especificacao_vazia() throws RemoteException {
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();

		ResultadoListagemPreCadastroDTO resultadoDto = servicoSisLaraServerRmi.pesquisarPreCadastroPor(especificacao);

		Assert.assertFalse(resultadoDto.sucesso());
		Assert.assertFalse(resultadoDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_precadastro_inexistentes_a_partir_de_nome() throws RemoteException {
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.PRE_CADASTRO, "Pre-Cadastro NOVO");

		ResultadoListagemPreCadastroDTO resultadoDto = servicoSisLaraServerRmi.pesquisarPreCadastroPor(especificacao);

		Assert.assertTrue(resultadoDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_precadastro_inexistentes_a_partir_de_rg() throws RemoteException {
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.RG, "2323232");

		ResultadoListagemPreCadastroDTO resultadoDto = servicoSisLaraServerRmi.pesquisarPreCadastroPor(especificacao);

		Assert.assertTrue(resultadoDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_precadastro_existente_a_partir_de_rg() throws RemoteException {
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.RG, "384744");

		ResultadoListagemPreCadastroDTO resultadoDto = servicoSisLaraServerRmi.pesquisarPreCadastroPor(especificacao);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_precadastro_existente_a_partir_de_cpf() throws RemoteException {
		EspecificacaoPesquisaPreCadastroDTO especificacao = new EspecificacaoPesquisaPreCadastroDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.CPF, "71894810287");

		ResultadoListagemPreCadastroDTO resultadoDto = servicoSisLaraServerRmi.pesquisarPreCadastroPor(especificacao);

		Assert.assertTrue(resultadoDto.sucesso());
		Assert.assertEquals(resultadoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_tipoatendimento() throws RemoteException {
		ResultadoListagemTipoAtendimentoDTO resultadoListagemTipoAtendimento = servicoSisLaraServerRmi
				.obterListagemTipoAtendimento();

		Assert.assertTrue(resultadoListagemTipoAtendimento.sucesso());
		Assert.assertEquals(resultadoListagemTipoAtendimento.getObjetosDto().size(), 7,
				"A lista deveria conter 7 itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_parentesco() throws RemoteException {
		ResultadoListagemParentescoDTO resultadoListagemParentesco = servicoSisLaraServerRmi.obterListagemParentesco();

		Assert.assertTrue(resultadoListagemParentesco.sucesso());
		Assert.assertFalse(resultadoListagemParentesco.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_diasemanaehorario() throws RemoteException {
		DiaSemanaEHorarioDTO diaSemanaEHorarioDto = new DiaSemanaEHorarioDTO();
		ResultadoValidacaoDiaSemanaEHorarioDTO resultadoValidacaoDiaSemanaEHorarioDTOS = servicoSisLaraServerRmi
				.validarDiaSemanaEHorario(diaSemanaEHorarioDto);

		Assert.assertFalse(resultadoValidacaoDiaSemanaEHorarioDTOS.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_diasemanaehorario() throws RemoteException {
		DiaSemanaEHorarioDTO diaSemanaEHorarioDto = ContextoDiasSemanaEHorarios.construirDiaSemanaEHorarioDTO();
		ResultadoValidacaoDiaSemanaEHorarioDTO resultadoValidacaoDiaSemanaEHorarioDTOS = servicoSisLaraServerRmi
				.validarDiaSemanaEHorario(diaSemanaEHorarioDto);

		Assert.assertTrue(resultadoValidacaoDiaSemanaEHorarioDTOS.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_familiar() throws RemoteException {
		FamiliarDTO familiarDto = new FamiliarDTO();
		ResultadoValidacaoFamiliarDTO resultadoValidacaoFamiliar = servicoSisLaraServerRmi.validarFamiliar(familiarDto);

		Assert.assertFalse(resultadoValidacaoFamiliar.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_familiar() throws RemoteException {
		FamiliarDTO familiarDto = ContextoFamiliar.construirFamiliarDTO();
		familiarDto.setId(null);
		ResultadoValidacaoFamiliarDTO resultadoValidacaoFamiliar = servicoSisLaraServerRmi.validarFamiliar(familiarDto);

		Assert.assertTrue(resultadoValidacaoFamiliar.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_custo() throws RemoteException {
		CustoDTO custoDto = new CustoDTO();
		ResultadoValidacaoCustoDTO resultadoValidacaoCusto = servicoSisLaraServerRmi.validarCusto(custoDto);

		Assert.assertFalse(resultadoValidacaoCusto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_custo_valor_errado() throws RemoteException {
		CustoDTO custoDto = ContextoCusto.fabricarCustoDTOComTodosOsDados();
		custoDto.setValor("ASKDGKA");
		ResultadoValidacaoCustoDTO resultadoValidacaoCusto = servicoSisLaraServerRmi.validarCusto(custoDto);

		Assert.assertFalse(resultadoValidacaoCusto.sucesso());
		Assert.assertEquals(resultadoValidacaoCusto.obterMensagens(), "Insira um Valor de Custo válido.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_custo() throws RemoteException {
		CustoDTO custoDto = ContextoCusto.fabricarCustoDTOComTodosOsDados();
		ResultadoValidacaoCustoDTO resultadoValidacaoCusto = servicoSisLaraServerRmi.validarCusto(custoDto);

		Assert.assertTrue(resultadoValidacaoCusto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_situacao_guarda() throws RemoteException {
		ResultadoListagemSituacaoGuardaDTO resultadoListagemSituacaoGuarda = servicoSisLaraServerRmi
				.obterListagemSituacaoGuarda();

		Assert.assertTrue(resultadoListagemSituacaoGuarda.sucesso());
		Assert.assertFalse(resultadoListagemSituacaoGuarda.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_beneficio() throws RemoteException {
		ResultadoListagemBeneficioDTO resultadoListagemBeneficio = servicoSisLaraServerRmi.obterListagemBeneficio();

		Assert.assertTrue(resultadoListagemBeneficio.sucesso());
		Assert.assertFalse(resultadoListagemBeneficio.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_deficiencia() throws RemoteException {
		ResultadoListagemDeficienciaDTO resultadoListagemDeficiencia = servicoSisLaraServerRmi
				.obterListagemDeficiencia();

		Assert.assertTrue(resultadoListagemDeficiencia.sucesso());
		Assert.assertFalse(resultadoListagemDeficiencia.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_doenca() throws RemoteException {
		ResultadoListagemDoencaDTO resultadoListagemDoenca = servicoSisLaraServerRmi.obterListagemDoenca();

		Assert.assertTrue(resultadoListagemDoenca.sucesso());
		Assert.assertFalse(resultadoListagemDoenca.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_convenio() throws RemoteException {
		ResultadoListagemConvenioDTO resultadoListagemConvenio = servicoSisLaraServerRmi.obterListagemConvenio();

		Assert.assertTrue(resultadoListagemConvenio.sucesso());
		Assert.assertFalse(resultadoListagemConvenio.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_informacao_educacional() throws RemoteException {
		InformacaoEducacionalDTO informacaoEducacionalDto = new InformacaoEducacionalDTO();
		ResultadoValidacaoInformacaoEducacionalDTO resultadoValidacaoInformacaoEscolar = servicoSisLaraServerRmi
				.validarInformacaoEducacional(informacaoEducacionalDto);

		Assert.assertFalse(resultadoValidacaoInformacaoEscolar.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_informacao_educacional() throws RemoteException {
		InformacaoEducacionalDTO informacaoEducacionalDto = ContextoInformacaoEducacional
				.construirInformacaoEducacionalDTO();
		ResultadoValidacaoInformacaoEducacionalDTO resultadoValidacaoInformacaoEscolar = servicoSisLaraServerRmi
				.validarInformacaoEducacional(informacaoEducacionalDto);

		Assert.assertTrue(resultadoValidacaoInformacaoEscolar.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipo_telefone() throws RemoteException {
		ResultadoListagemTipoTelefoneDTO resultadoListagemTipoTelefone = servicoSisLaraServerRmi
				.obterListagemTipoTelefone();

		Assert.assertTrue(resultadoListagemTipoTelefone.sucesso());
		Assert.assertFalse(resultadoListagemTipoTelefone.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_telefone() throws RemoteException {
		TelefoneDTO telefoneDto = ContextoTelefone.construirTelefoneDTO();
		ResultadoValidacaoTelefoneDTO resultadoValidacaoTelefone = servicoSisLaraServerRmi.validarTelefone(telefoneDto);

		Assert.assertTrue(resultadoValidacaoTelefone.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_telefone() throws RemoteException {
		TelefoneDTO telefoneDto = ContextoTelefone.construirTelefoneDTOIncompleto();
		ResultadoValidacaoTelefoneDTO resultadoValidacaoTelefone = servicoSisLaraServerRmi.validarTelefone(telefoneDto);

		Assert.assertFalse(resultadoValidacaoTelefone.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_certidao() throws RemoteException {
		CertidaoDTO cetidaoDto = ContextoCertidao.fabricarCertidaoDTOComTodosOsDados();
		ResultadoValidacaoCertidaoDTO resultadoValidacaoCertidao = servicoSisLaraServerRmi.validarCertidao(cetidaoDto);

		Assert.assertTrue(resultadoValidacaoCertidao.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_certidao() throws RemoteException {
		CertidaoDTO cetidaoDto = new CertidaoDTO();
		ResultadoValidacaoCertidaoDTO resultadoValidacaoCertidao = servicoSisLaraServerRmi.validarCertidao(cetidaoDto);

		Assert.assertFalse(resultadoValidacaoCertidao.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_informacao_trabalho_completa() throws RemoteException {
		InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto = ContextoInformacaoTrabalhoCompleta
				.fabricarInformacaoTrabalhoDTOCompletaComTodosOsDados();
		ResultadoValidacaoInformacaoTrabalhoCompletaDTO resultadoValidacaoInformacaoTrabalhoCompleta = servicoSisLaraServerRmi
				.validarInformacaoTrabalhoCompleta(informacaoTrabalhoCompletaDto);

		Assert.assertTrue(resultadoValidacaoInformacaoTrabalhoCompleta.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_informacao_trabalho_completa() throws RemoteException {
		InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto = new InformacaoTrabalhoCompletaDTO();
		ResultadoValidacaoInformacaoTrabalhoCompletaDTO resultadoValidacaoInformacaoTrabalhoCompleta = servicoSisLaraServerRmi
				.validarInformacaoTrabalhoCompleta(informacaoTrabalhoCompletaDto);

		Assert.assertFalse(resultadoValidacaoInformacaoTrabalhoCompleta.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_periodo_beneficio() throws RemoteException {
		PeriodoBeneficioDTO periodoBeneficioDto = ContextoPeriodoBeneficio.construirPeriodoBeneficioDTO();
		ResultadoValidacaoPeriodoBeneficioDTO resultadoValidacaoPeriodoBeneficio = servicoSisLaraServerRmi
				.validarPeriodoBeneficio(periodoBeneficioDto);

		Assert.assertTrue(resultadoValidacaoPeriodoBeneficio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_periodo_beneficio() throws RemoteException {
		PeriodoBeneficioDTO periodoBeneficioDto = new PeriodoBeneficioDTO();
		ResultadoValidacaoPeriodoBeneficioDTO resultadoValidacaoPeriodoBeneficio = servicoSisLaraServerRmi
				.validarPeriodoBeneficio(periodoBeneficioDto);

		Assert.assertFalse(resultadoValidacaoPeriodoBeneficio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_periodo_deficiencia() throws RemoteException {
		PeriodoDeficienciaDTO periodoDeficienciaDto = ContextoPeriodoDeficiencia.construirPeriodoDeficienciaDTO();
		ResultadoValidacaoPeriodoDeficienciaDTO resultadoValidacaoPeriodoBeneficio = servicoSisLaraServerRmi
				.validarPeriodoDeficiencia(periodoDeficienciaDto);

		Assert.assertTrue(resultadoValidacaoPeriodoBeneficio.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_periodo_deficiencia() throws RemoteException {
		PeriodoDeficienciaDTO periodoDeficienciaDto = new PeriodoDeficienciaDTO();
		ResultadoValidacaoPeriodoDeficienciaDTO resultadoValidacaoPeriodoDeficiencia = servicoSisLaraServerRmi
				.validarPeriodoDeficiencia(periodoDeficienciaDto);

		Assert.assertFalse(resultadoValidacaoPeriodoDeficiencia.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_sem_erro_periodo_doenca() throws RemoteException {
		PeriodoDoencaDTO periodoDoencaDto = ContextoPeriodoDoenca.construirPeriodoDoencaDTO();
		ResultadoValidacaoPeriodoDoencaDTO resultadoValidacaoPeriodoDoenca = servicoSisLaraServerRmi
				.validarPeriodoDoenca(periodoDoencaDto);

		Assert.assertTrue(resultadoValidacaoPeriodoDoenca.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_com_erro_periodo_doenca() throws RemoteException {
		PeriodoDoencaDTO periodoDoencaDto = new PeriodoDoencaDTO();
		ResultadoValidacaoPeriodoDoencaDTO resultadoValidacaoPeriodoDoenca = servicoSisLaraServerRmi
				.validarPeriodoDoenca(periodoDoencaDto);

		Assert.assertFalse(resultadoValidacaoPeriodoDoenca.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_bloqueia_prontuario_em_edicao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoAutenticacaoDTO resultado2Dto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(12345));

		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		Assert.assertTrue(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(usuarioDTO.obterNome(), resultado2Dto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_bloqueia_grupo_em_edicao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoAutenticacaoDTO resultado2Dto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(232323));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G02"));
		grupoDTO.setTurma("02");
		grupoDTO.setDataInicio("31/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		Assert.assertTrue(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(grupoDTO.obterNome(), resultado2Dto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_adiciona_e_remove_bloqueio_usuario_em_edicao_com_identificacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(12345));

		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		servicoSisLaraServerRmi.desbloquearEdicaoGeral(new GeralContaAcessoBloqueadoDTO(usuarioDTO.obterNome(), null),
				resultadoDto.getToken());

		Assert.assertFalse(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(usuarioDTO.obterNome(), resultadoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_adiciona_e_remove_bloqueio_usuario_em_edicao_com_dto() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(344444));

		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		servicoSisLaraServerRmi.desbloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		Assert.assertFalse(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(usuarioDTO.obterNome(), resultadoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_adiciona_e_remove_bloqueio_grupo_em_edicao_com_identificacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(232323));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G02"));
		grupoDTO.setTurma("02");
		grupoDTO.setDataInicio("31/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		servicoSisLaraServerRmi.desbloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		Assert.assertFalse(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(grupoDTO.obterNome(), resultadoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_adiciona_e_remove_bloqueio_grupo_em_edicao_com_dto() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(2222));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G02"));
		grupoDTO.setTurma("02");
		grupoDTO.setDataInicio("31/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		servicoSisLaraServerRmi.desbloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		Assert.assertFalse(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(grupoDTO.obterNome(), resultadoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_conta_acesso_editando_prontuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(12345));

		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		ResultadoCoordenacaoEdicaoDTO nomeContaAcessoAlterandoUsuario = servicoSisLaraServerRmi
				.obterContaAcessoEditando(usuarioDTO.obterNome());

		Assert.assertTrue(nomeContaAcessoAlterandoUsuario.sucesso());
		Assert.assertEquals(nomeContaAcessoAlterandoUsuario.obterMensagens(),
				"O item está sendo editado pelo(a): pabsantos\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_conta_acesso_editando_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(1222));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO.setTurma("03");
		grupoDTO.setDataInicio("31/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		ResultadoCoordenacaoEdicaoDTO nomeContaAcessoAlterandoGrupo = servicoSisLaraServerRmi
				.obterContaAcessoEditando(grupoDTO.obterNome());

		Assert.assertTrue(nomeContaAcessoAlterandoGrupo.sucesso());
		Assert.assertEquals(nomeContaAcessoAlterandoGrupo.obterMensagens(),
				"O item está sendo editado pelo(a): pabsantos\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_nao_bloqueia_grupo_sendo_editado_por_mesmo_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(1222));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO.setTurma("03");
		grupoDTO.setDataInicio("31/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		Assert.assertFalse(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(grupoDTO.obterNome(), resultadoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_nao_bloqueia_usuario_sendo_editado_por_mesmo_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(12345));

		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		Assert.assertFalse(
				servicoSisLaraServerRmi.estaBloqueadoParaEdicao(usuarioDTO.obterNome(), resultadoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_usuarios_conta_acesso_bloqueados_para_edicao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoAutenticacaoDTO resultadoDto2 = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		UsuarioDTO usuarioDTO = new UsuarioDTO(new Long(12345));

		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO.obterNome(), resultadoDto.getToken());

		UsuarioDTO usuarioDTO2 = new UsuarioDTO(new Long(123456));
		servicoSisLaraServerRmi.bloquearEdicao(usuarioDTO2.obterNome(), resultadoDto2.getToken());

		ResultadoListagemGeralBloqueadosDTO resultadoListagemBloqueadosDTO = servicoSisLaraServerRmi
				.obterListagemBloqueados();

		Assert.assertTrue(resultadoListagemBloqueadosDTO.sucesso());
		Assert.assertEquals(resultadoListagemBloqueadosDTO.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_grupos_conta_acesso_bloqueados_para_edicao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoAutenticacaoDTO resultadoDto2 = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setId(new Long(233333));
		grupoDTO.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO.setTurma("22");
		grupoDTO.setDataInicio("31/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO.obterNome(), resultadoDto.getToken());

		GrupoDTO grupoDTO2 = new GrupoDTO();
		grupoDTO2.setId(new Long(12223));
		grupoDTO2.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G2"));
		grupoDTO2.setTurma("12");
		grupoDTO2.setDataInicio("11/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO2.obterNome(), resultadoDto.getToken());

		GrupoDTO grupoDTO3 = new GrupoDTO();
		grupoDTO3.setId(new Long(12223));
		grupoDTO3.setNomeGrupoDto(new NomeGrupoDTO(new Long(1002), "G1"));
		grupoDTO3.setTurma("1");
		grupoDTO3.setDataInicio("10/12/2012");

		servicoSisLaraServerRmi.bloquearEdicao(grupoDTO3.obterNome(), resultadoDto2.getToken());

		ResultadoListagemGeralBloqueadosDTO resultadoListagemGeralBloqueadosDTO = servicoSisLaraServerRmi
				.obterListagemBloqueados();

		Assert.assertTrue(resultadoListagemGeralBloqueadosDTO.sucesso());
		Assert.assertEquals(resultadoListagemGeralBloqueadosDTO.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_todos_grupos_ativos_a_partir_nome() throws RemoteException {
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		Assert.assertTrue(resultadoListagemGrupo.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemGrupo.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_nenhum_grupos_ativos_a_partir_nome() throws RemoteException {
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("XXX");

		Assert.assertFalse(resultadoListagemGrupo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_mais_de_um_grupos_ativos_a_partir_nome() throws RemoteException {
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G04-1");

		Assert.assertFalse(resultadoListagemGrupo.sucesso());
		Assert.assertEquals(resultadoListagemGrupo.obterMensagens(), "Mais de um grupo foi encontrado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_local_atendimento() throws RemoteException {
		ResultadoListagemLocalAtendimentoDTO resultadoListagemLocalAtendimento = servicoSisLaraServerRmi
				.obterListagemLocalAtendimento();

		Assert.assertTrue(resultadoListagemLocalAtendimento.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemLocalAtendimento.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_tipificacao_servico() throws RemoteException {
		ResultadoListagemTipificacaoServicoDTO resultadoListagemTipificacaoServico = servicoSisLaraServerRmi
				.obterListagemTipificacaoServico();

		Assert.assertTrue(resultadoListagemTipificacaoServico.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemTipificacaoServico.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_profissional_ativo() throws RemoteException {
		ResultadoListagemProfissionalDTO resultadoListagemProfissional = servicoSisLaraServerRmi
				.obterListagemProfissionalAtivos();

		Assert.assertTrue(resultadoListagemProfissional.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertTrue(resultadoListagemProfissional.getObjetosDto().size() == 7);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_voluntario_ativo() throws RemoteException {
		ResultadoListagemProfissionalDTO resultadoListagemVoluntario = servicoSisLaraServerRmi
				.obterListagemVoluntarioAtivos();

		Assert.assertTrue(resultadoListagemVoluntario.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertTrue(resultadoListagemVoluntario.getObjetosDto().size() == 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_item_custo_da_doenca() throws RemoteException {
		ResultadoListagemItensCustoDTO resultadoListagemItensCusto = servicoSisLaraServerRmi
				.obterListagemItensCustoDoenca();

		Assert.assertTrue(resultadoListagemItensCusto.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertTrue(resultadoListagemItensCusto.getObjetosDto().size() == 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_item_custo_da_deficiencia() throws RemoteException {
		ResultadoListagemItensCustoDTO resultadoListagemItensCusto = servicoSisLaraServerRmi
				.obterListagemItensCustoDeficiencia();

		Assert.assertTrue(resultadoListagemItensCusto.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertTrue(resultadoListagemItensCusto.getObjetosDto().size() == 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_todos_profissionais() throws RemoteException {
		ResultadoListagemProfissionalDTO resultadoListagemProfissional = servicoSisLaraServerRmi
				.obterListagemTodosProfissionais();

		Assert.assertTrue(resultadoListagemProfissional.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemProfissional.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_dias_semana() throws RemoteException {
		ResultadoListagemDiaSemanaDTO resultadoListagemDiaSemana = servicoSisLaraServerRmi.obterListagemDiaSemana();

		Assert.assertTrue(resultadoListagemDiaSemana.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemDiaSemana.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_status_relacao_usuario_modulo_cto() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDTO = new EspecificacaoPesquisaGrupoDTO();
		especificacaoPesquisaGrupoDTO.adicionarParametro(ChavePesquisaDTO.GRUPOS_ATIVOS, "G");

		ResultadoListagemGrupoDTO listagemGrupoDto = servicoSisLaraServerRmi
				.pesquisarGrupoPor(especificacaoPesquisaGrupoDTO);
		ModuloPeriodoDTO moduloPeriodoDTO = obterModuloPeriodoDto(listagemGrupoDto.getObjetosDto(), new Long(14444),
				new Long(77777));

		ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatus = servicoSisLaraServerRmi
				.obterListagemStatusRelacaoUsuarioModulo(moduloPeriodoDTO);

		Assert.assertTrue(resultadoListagemStatus.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertEquals(resultadoListagemStatus.getObjetosDto().size(), 9);
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_status_relacao_usuario_modulo_proceja() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDTO = new EspecificacaoPesquisaGrupoDTO();
		especificacaoPesquisaGrupoDTO.adicionarParametro(ChavePesquisaDTO.GRUPOS_ATIVOS, "G");

		ResultadoListagemGrupoDTO listagemGrupoDto = servicoSisLaraServerRmi
				.pesquisarGrupoPor(especificacaoPesquisaGrupoDTO);
		ModuloPeriodoDTO moduloPeriodoDTO = obterModuloPeriodoDto(listagemGrupoDto.getObjetosDto(), new Long(12222),
				new Long(11111));

		ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatus = servicoSisLaraServerRmi
				.obterListagemStatusRelacaoUsuarioModulo(moduloPeriodoDTO);

		Assert.assertTrue(resultadoListagemStatus.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertEquals(resultadoListagemStatus.getObjetosDto().size(), 11);
		Assert.assertFalse(resultadoListagemStatus.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_status_relacao_usuario_modulo_disponiveis_para_inclusao()
			throws RemoteException {
		ResultadoListagemStatusRelacaoUsuarioModuloDTO resultadoListagemStatus = servicoSisLaraServerRmi
				.obterListagemStatusRelacaoUsuarioModuloDisponiveisParaInclusao(ContextoGrupo.construirGrupoDTO());

		Assert.assertTrue(resultadoListagemStatus.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertEquals(resultadoListagemStatus.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_frequencia() throws RemoteException {
		ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDoUsuario = servicoSisLaraServerRmi
				.obterListagemFrequenciaDoUsuario();
		ResultadoListagemFrequenciaDTO resultadoListagemFrequenciaDoProfissional = servicoSisLaraServerRmi
				.obterListagemFrequenciaDoProfissional();

		Assert.assertTrue(resultadoListagemFrequenciaDoUsuario.sucesso(), "A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemFrequenciaDoUsuario.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertTrue(resultadoListagemFrequenciaDoProfissional.sucesso(),
				"A listagem deveria ser gerada com sucesso.");
		Assert.assertFalse(resultadoListagemFrequenciaDoProfissional.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_todos_grupos() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDTO = new EspecificacaoPesquisaGrupoDTO();
		especificacaoPesquisaGrupoDTO.adicionarParametro(ChavePesquisaDTO.GRUPOS_INATIVOS, "G");

		ResultadoListagemGrupoDTO listagemGrupoDto = servicoSisLaraServerRmi
				.pesquisarGrupoPor(especificacaoPesquisaGrupoDTO);

		Assert.assertTrue(listagemGrupoDto.sucesso());
		Assert.assertEquals(listagemGrupoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_apenas_grupos_ativos() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDTO = new EspecificacaoPesquisaGrupoDTO();
		especificacaoPesquisaGrupoDTO.adicionarParametro(ChavePesquisaDTO.GRUPOS_ATIVOS, "G02");

		ResultadoListagemGrupoDTO listagemGrupoDto = servicoSisLaraServerRmi
				.pesquisarGrupoPor(especificacaoPesquisaGrupoDTO);

		Assert.assertTrue(listagemGrupoDto.sucesso());
		Assert.assertEquals(listagemGrupoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_grupo_inativos_por_nome() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDTO = new EspecificacaoPesquisaGrupoDTO();
		especificacaoPesquisaGrupoDTO.adicionarParametro(ChavePesquisaDTO.GRUPOS_INATIVOS, "G00");

		ResultadoListagemGrupoDTO listagemGrupoDto = servicoSisLaraServerRmi
				.pesquisarGrupoPor(especificacaoPesquisaGrupoDTO);

		Assert.assertTrue(listagemGrupoDto.sucesso());
		Assert.assertEquals(listagemGrupoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_grupo_por_nomes_completos_por_nome() throws RemoteException {

		ResultadoListagemNomeCompletoGrupoDTO resultado = servicoSisLaraServerRmi.pesquisarNomeGrupoPor("G02");

		Assert.assertEquals(resultado.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_grupo_inexistente_por_nome() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacaoPesquisaGrupoDTO = new EspecificacaoPesquisaGrupoDTO();
		especificacaoPesquisaGrupoDTO.adicionarParametro(ChavePesquisaDTO.GRUPOS_INATIVOS, "AA");
		ResultadoListagemGrupoDTO listagemGrupoDto = servicoSisLaraServerRmi
				.pesquisarGrupoPor(especificacaoPesquisaGrupoDTO);

		Assert.assertFalse(listagemGrupoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_pesquisa_por_grupo_a_partir_de_especificacao_vazia() throws RemoteException {
		EspecificacaoPesquisaGrupoDTO especificacao = new EspecificacaoPesquisaGrupoDTO();

		ResultadoListagemGrupoDTO resultadoDto = servicoSisLaraServerRmi.pesquisarGrupoPor(especificacao);

		Assert.assertEquals(resultadoDto.sucesso(), false);
		Assert.assertFalse(resultadoDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_novo_grupo_e_gera_pendencias_de_atendimento_de_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoASalvarDto = ContextoGrupo.construirGrupoDTOSemAtendimentos();

		ResultadoEdicaoGrupoDTO resultadoInclusaoGrupo = servicoSisLaraServerRmi.editarGrupo(grupoASalvarDto,
				resultadoDto.getToken());
		
		MaquinaTempo.mudarDataAtual(grupoASalvarDto.getDataTermino());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServerRmi.obterListagemPendencia(resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(resultadoInclusaoGrupo.sucesso());
		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().size(), 6);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_novo_grupo_e_gera_pendencias_desativando_pendencias_apos_desativacao_de_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoASalvarDto = ContextoGrupo.construirGrupoDTOSemAtendimentos();

		ResultadoEdicaoGrupoDTO resultadoInclusaoGrupo = servicoSisLaraServerRmi.editarGrupo(grupoASalvarDto,
				resultadoDto.getToken());
		
		MaquinaTempo.mudarDataAtual(grupoASalvarDto.getDataTermino());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaAntesDesativacaoDTO = servicoSisLaraServerRmi.obterListagemPendencia(resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();
		
		GrupoDTO grupoDto = (GrupoDTO)resultadoInclusaoGrupo.obterObjetoDtoEditado();
		grupoDto.setAtivo(false);
		servicoSisLaraServerRmi.editarGrupo(grupoDto, resultadoDto.getToken());
		

		MaquinaTempo.mudarDataAtual(grupoDto.getDataTermino());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaAposDesativacaoDTO = servicoSisLaraServerRmi.obterListagemPendencia(resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();
		
		
		Assert.assertTrue(resultadoInclusaoGrupo.sucesso());
		Assert.assertTrue(resultadoListagemPendenciaAntesDesativacaoDTO.sucesso());
		Assert.assertEquals(resultadoListagemPendenciaAntesDesativacaoDTO.getObjetosDto().size(), 6);
		Assert.assertTrue(resultadoListagemPendenciaAposDesativacaoDTO.sucesso());
		Assert.assertEquals(resultadoListagemPendenciaAposDesativacaoDTO.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_pendencias_de_atendimento_de_grupo_e_listagem_de_pendencias_desconsidera_data_com_atendimento_de_grupo_efetuado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVVitalino());

		String dataInicial = "12/01/2016";
		MaquinaTempo.mudarDataAtual(dataInicial);
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		servicoSisLaraServerRmi.gerarAtendimentos(new EspecificacaoGeracaoAtendimentosDTO(grupoDto.getId(),
				obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(22222)).getId(), dataInicial, "09:00",
				"10:00"), resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAposGeracaoAtendimento = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDtoAposGeracaoAtendimento = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAposGeracaoAtendimento.getObjetosDto(), new Long(12222));
		grupoDtoAposGeracaoAtendimento.setDataTermino("31/01/2016");

		servicoSisLaraServerRmi.editarGrupo(grupoDtoAposGeracaoAtendimento, resultadoDto.getToken());

		MaquinaTempo.mudarDataAtual(grupoDto.getDataTermino());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_de_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemPendenciaDTO pendenciasAntesResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		PendenciaDTO pendenciaDTO = (PendenciaDTO) obterDaRelacaoPorId(pendenciasAntesResolucaoDto.getObjetosDto(),
				new Long(5000));

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = servicoSisLaraServerRmi
				.gerarAtendimentos(pendenciaDTO, resultadoDto.getToken());

		ResultadoListagemPendenciaDTO pendenciasAposResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(((ModuloPeriodoDTO) resultadoGeracaoAtendimentoDTO.obterObjetoDtoEditado())
				.getAtendimentosGrupoDto().size(), 2);
		Assert.assertEquals(pendenciasAntesResolucaoDto.getObjetosDto().size(), 2);
		Assert.assertEquals(pendenciasAposResolucaoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_de_atendimento_invidual_de_servico_social_avaliacao_e_triagem_com_geracao_de_atendimento_de_acompanhamento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemPendenciaDTO pendenciasAntesResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		AtendimentoProfissionalDTO atendimentoProfissionalDTO = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTOComAT();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional.construirProfissionalDTOComId3000());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAcompanhamentoDTO());
		atendimentoIndividualDto.getUsuarioDto().setId(new Long(82222));
		atendimentoIndividualDto.setData("31/01/2012");
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("09:00", "11:00"));
		atendimentoIndividualDto.getAtendimentosProfissionalDto().add(atendimentoProfissionalDTO);

		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO pendenciasAposResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(pendenciasAntesResolucaoDto.getObjetosDto().size(), 2);
		Assert.assertEquals(pendenciasAposResolucaoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_de_atendimento_invidual_de_avaliacao_de_servico_de_atencao_especializada_em_ortoptica_com_geracao_de_atendimento_de_acompanhamento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaAdailza());

		ResultadoListagemPendenciaDTO pendenciasAntesResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		AtendimentoProfissionalDTO atendimentoProfissionalDTO = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTOComAT();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional.construirProfissionalDTOComId4000());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAcompanhamentoServicoOrtoptica());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAEE());
		atendimentoIndividualDto.getUsuarioDto().setId(new Long(16666));
		atendimentoIndividualDto.setData("31/01/2012");
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("09:00", "11:00"));
		atendimentoIndividualDto.getAtendimentosProfissionalDto().add(atendimentoProfissionalDTO);

		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO pendenciasAposResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(pendenciasAntesResolucaoDto.getObjetosDto().size(), 1);
		Assert.assertEquals(pendenciasAposResolucaoDto.getObjetosDto().size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_resolve_pendencia_de_grupo_resolvida_por_outro_usuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());
		ResultadoAutenticacaoDTO resultadoAlternativoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		ResultadoListagemPendenciaDTO pendenciasDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		PendenciaDTO pendenciaDTO = (PendenciaDTO) obterDaRelacaoPorId(pendenciasDto.getObjetosDto(), new Long(5000));

		servicoSisLaraServerRmi.gerarAtendimentos(pendenciaDTO, resultadoAlternativoDto.getToken());

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = servicoSisLaraServerRmi
				.gerarAtendimentos(pendenciaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimentoDTO.sucesso());
		Assert.assertEquals(resultadoGeracaoAtendimentoDTO.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_resolve_pendencia_de_grupo_devido_alteracao_concorrente() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());
		ResultadoAutenticacaoDTO resultadoAlternativoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		ResultadoListagemPendenciaDTO pendenciasDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		PendenciaDTO pendenciaDTO = (PendenciaDTO) obterDaRelacaoPorId(pendenciasDto.getObjetosDto(), new Long(5000));

		ResultadoListagemGrupoDTO gruposDto = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(gruposDto.getObjetosDto(), new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(11111));
		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());
		servicoSisLaraServerRmi.bloquearEdicao("G02-1", resultadoAlternativoDto.getToken());
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = servicoSisLaraServerRmi
				.gerarAtendimentos(pendenciaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimentoDTO.sucesso());
		Assert.assertEquals(resultadoGeracaoAtendimentoDTO.obterMensagens(),
				"O item está sendo editado pelo(a): rleme\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_atendimento_no_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		String nomeArquivo = "teste.pdf";
		String nomeGrupo = "G02-1";
		String conteudoA = "Conteudo do arquivo de teste A.";
		String conteudoB = "Conteudo do arquivo de teste B.";
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupo);

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));
		atendimentoGrupoDto.setDescricao(conteudoA);
		ArquivoDTO arquivoAtendimetoGrupoDTO = new ArquivoDTO(null, nomeArquivo, conteudoA.getBytes(), null);
		atendimentoGrupoDto.setArquivos(Arrays.asList(arquivoAtendimetoGrupoDTO));
		AtendimentoUsuarioDTO atendimentoUsuarioDTO = (AtendimentoUsuarioDTO) obterDaRelacaoPorId(
				atendimentoGrupoDto.getAtendimentosUsuariosDto(), new Long(12222));
		ArquivoDTO arquivoAtendimentoUsuarioDTO = new ArquivoDTO(null, nomeArquivo, conteudoB.getBytes(), null);
		atendimentoUsuarioDTO.setArquivos(Arrays.asList(arquivoAtendimentoUsuarioDTO));

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO listagemGrupoAposAtualizacaoDto = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G02-1");
		AtendimentoGrupoDTO atendimentoGrupoAposAtualizacaoDto = obterAtendimentoGrupoDto(
				listagemGrupoAposAtualizacaoDto.getObjetosDto(), new Long(12222), new Long(11111), new Long(11111));
		AtendimentoUsuarioDTO atendimentoUsuarioAtualizadoDTO = (AtendimentoUsuarioDTO) obterDaRelacaoPorId(
				atendimentoGrupoAposAtualizacaoDto.getAtendimentosUsuariosDto(), new Long(12222));
		ArquivoDTO arquivoAtendimentoGrupoSalvoDTO = servicoSisLaraServerRmi.obterArquivoAtendimentoGrupoDTO(
				atendimentoGrupoAposAtualizacaoDto, atendimentoGrupoAposAtualizacaoDto.getArquivos().get(0));
		ArquivoDTO arquivoAtendimentoUsuarioSalvoDTO = servicoSisLaraServerRmi.obterArquivoAtendimentoUsuarioDTO(
				atendimentoUsuarioAtualizadoDTO, atendimentoUsuarioAtualizadoDTO.getArquivos().get(0));
		ArquivoDTO arquivoDisponivelAtendimentoUsuarioDto = servicoSisLaraServerRmi.obterArquivoDisponivelDTO(
				atendimentoUsuarioAtualizadoDTO.getId().toString(), nomeArquivo,
				TipoArquivoDisponivel.USUARIO_NO_GRUPO.toString());
		ArquivoDTO arquivoDisponivelAtendimentoGrupoBDto = servicoSisLaraServerRmi.obterArquivoDisponivelDTO(
				atendimentoGrupoDto.getId().toString(), nomeArquivo, TipoArquivoDisponivel.GRUPO.toString());
		ResultadoListagemArquivoDisponivelDTO resultadoListagemArquivoDisponivelSomenteGrupoDto = servicoSisLaraServerRmi
				.obterListagemArquivoDisponivelDTO(nomeGrupo + "-01/01/2016", true);

		Assert.assertTrue(resultadoAtualizacaoAtendimentoGrupo.sucesso());
		Assert.assertTrue(atendimentoGrupoAposAtualizacaoDto.getDescricao().equals(conteudoA));
		Assert.assertNotEquals(atendimentoGrupoDto.getVersao(), atendimentoGrupoAposAtualizacaoDto.getVersao());
		Assert.assertEquals(arquivoAtendimentoGrupoSalvoDTO.obterConteudo(), arquivoAtendimetoGrupoDTO.obterConteudo());
		Assert.assertEquals(arquivoAtendimentoUsuarioSalvoDTO.obterConteudo(),
				arquivoAtendimentoUsuarioDTO.obterConteudo());
		Assert.assertEquals(arquivoDisponivelAtendimentoUsuarioDto.obterConteudo(),
				arquivoAtendimentoUsuarioDTO.obterConteudo());
		Assert.assertEquals(arquivoDisponivelAtendimentoGrupoBDto.obterConteudo(),
				arquivoAtendimetoGrupoDTO.obterConteudo());
		Assert.assertEquals(resultadoListagemArquivoDisponivelSomenteGrupoDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_integra_usuario_em_modulo_reuniao_de_integracao_em_grupos_ativos_distintos()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		integrar_usuario_ao_grupo_e_modulo_perido(resultadoDto, "SS-1", new Long(66666), new Long(99999));

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = integrar_usuario_ao_grupo_e_modulo_perido(
				resultadoDto, "SS-2", new Long(77777), new Long(44444));

		Assert.assertFalse(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoModuloPeriodoDTO.obterMensagens(),
				"Usuários 12222 - Paulo já estão integrados em um grupo de reunião de integração.\n");
	}

	private EsperaDTO obterEsperaComObs(List<EsperaDTO> esperas, String obs) {
		return esperas.stream().filter(espera -> espera.getObs().contains(obs)).findFirst().orElse(null);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_atendimento_e_envia_para_lista_de_espera_por_excesso_de_faltas_e_inclui_pendencia()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		Long prontuario = new Long(12222);

		// Ref Módulo Período Informatica
		servico_gera_tres_atendimentos_de_grupo_com_datas_diferentes_e_altera_a_frenquencia_do_prontuario_para_fu(
				grupoDto, new Long(11111), resultadoDto, prontuario);

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(prontuario.toString());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);
		especificacao.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(resultado.getObjetosDto().size(), 3);
		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_atendimento_e_envia_para_lista_de_espera_por_excesso_de_faltas_e_inclui_pendencia_e_remove_pendencia_apos_resolucao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		Long prontuario = new Long(12222);

		// Ref Módulo Período Informatica
		servico_gera_tres_atendimentos_de_grupo_com_datas_diferentes_e_altera_a_frenquencia_do_prontuario_para_fu(
				grupoDto, new Long(11111), resultadoDto, prontuario);

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(prontuario.toString());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);
		especificacao.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaAntesResolucaoDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		EsperaDTO esperaIncluida = obterEsperaComObs(resultado.getObjetosDto(),
				"Incluído automaticamente por excesso de faltas não justificadas.");
		esperaIncluida.setJustificativaCancelamento("Justificativa.");
		servicoSisLaraServerRmi.cancelarEspera(esperaIncluida, resultadoDto.getToken());
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaAposResolucaoDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(resultadoListagemPendenciaAntesResolucaoDTO.getObjetosDto().size(), 3);
		Assert.assertEquals(resultadoListagemPendenciaAposResolucaoDTO.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_atendimento_e_nao_envia_para_lista_de_espera_devido_atendimento_no_mesmo_dia_de_aee_e_pisicosocial()
			throws RemoteException {
		ResultadoAutenticacaoDTO contaAcessoShivasDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaShivas());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisico = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");
		// Id 14444
		GrupoDTO grupoContendoModuloAeeEPisicoDto = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAEEPisico.getObjetosDto(), new Long(14444));
		Long prontuario = new Long(82222);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoA = servico_gera_atendimento_de_grupo_e_altera_frequencia_para_fu(
				new EspecificacaoGeracaoAtendimentosDTO(grupoContendoModuloAeeEPisicoDto.getId(),
						obterDaRelacaoPorId(grupoContendoModuloAeeEPisicoDto.getModulosPeriodosDto(), new Long(77777))
								.getId(),
						"03/03/2015", "09:00", "19:00"),
				prontuario, contaAcessoShivasDto);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoB = servico_gera_atendimento_de_grupo_e_altera_frequencia_para_fu(
				new EspecificacaoGeracaoAtendimentosDTO(grupoContendoModuloAeeEPisicoDto.getId(),
						obterDaRelacaoPorId(grupoContendoModuloAeeEPisicoDto.getModulosPeriodosDto(), new Long(77777))
								.getId(),
						"02/03/2015", "09:00", "19:00"),
				prontuario, contaAcessoShivasDto);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoC = servico_gera_atendimento_de_grupo_e_altera_frequencia_para_fu(
				new EspecificacaoGeracaoAtendimentosDTO(grupoContendoModuloAeeEPisicoDto.getId(),
						obterDaRelacaoPorId(grupoContendoModuloAeeEPisicoDto.getModulosPeriodosDto(), new Long(88888))
								.getId(),
						"02/03/2015", "09:00", "19:00"),
				prontuario, contaAcessoShivasDto);

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(prontuario.toString());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		Assert.assertEquals(resultado.getObjetosDto().size(), 0);
		Assert.assertTrue(resultadoA.sucesso());
		Assert.assertTrue(resultadoB.sucesso());
		Assert.assertTrue(resultadoC.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_atendimento_e_nao_envia_para_lista_de_espera_por_excesso_de_faltas_pois_esta_liberado_e_nao_envia_para_relacao_de_pendencias()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		Long prontuario = new Long(82222);

		// Ref. Módulo Período Ingles
		servico_gera_tres_atendimentos_de_grupo_com_datas_diferentes_e_altera_a_frenquencia_do_prontuario_para_fu(
				grupoDto, new Long(22222), resultadoDto, prontuario);

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(prontuario.toString());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);
		especificacao.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(resultado.getObjetosDto().size(), 0);
		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_atendimento_no_grupo_com_erro_obrigatoriedade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));
		atendimentoGrupoDto.setData(null);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAtualizacaoAtendimentoGrupo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_atendimento_no_grupo_devido_regra_que_obriga_frequencia_FP_em_todos_os_profissionais_e_usuarios()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));
		AtendimentoProfissionalDTO atendimentoProfissionalDTO = (AtendimentoProfissionalDTO) obterDaRelacaoPorId(
				atendimentoGrupoDto.getAtendimentosProfissionaisDto(), new Long(12222));
		atendimentoProfissionalDTO.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FP"));

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAtualizacaoAtendimentoGrupo.sucesso());
		Assert.assertTrue(resultadoAtualizacaoAtendimentoGrupo.obterMensagens()
				.contains("A frequencia FP é obrigatória aos usuários."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_devido_regra_que_obriga_FP_em_todos_os_profissionais_e_usuario()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDto.getAtendimentosProfissionalDto().get(0).getInformacaoAtendimentoDto()
				.setFrequenciaDto(new FrequenciaDTO("FP"));
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.obterMensagens()
				.contains("A frequencia FP é obrigatória aos usuários."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_atendimento_no_grupo_concorrente() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupoConcorrente = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoAtualizacaoAtendimentoGrupo.sucesso());
		Assert.assertTrue(resultadoAtualizacaoAtendimentoGrupoConcorrente.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_modulo_periodo_no_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		String vagas = "123";
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));
		moduloPeriodoDto.setVagas(vagas);

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupo = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO listagemGrupoAposAtualizacaoDto = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoAposAtualizacaoDto = obterModuloPeriodoDto(
				listagemGrupoAposAtualizacaoDto.getObjetosDto(), new Long(12222), new Long(11111));

		Assert.assertTrue(resultadoAtualizacaoModuloPeriodoGrupo.sucesso());
		Assert.assertTrue(moduloPeriodoAposAtualizacaoDto.getVagas().equals(vagas));
		Assert.assertNotEquals(moduloPeriodoDto.getVersao(), moduloPeriodoAposAtualizacaoDto.getVersao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_propaga_status_afastado_nos_modulos_periodos_no_mesmo_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Long idGrupo = new Long(12222);
		Long idModuloPeriodo = new Long(11111);
		Long idModuloUsuario = new Long(11111);
		String status = "AFASTADO";
		String nomeGrupo = "G02-1";

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo(nomeGrupo);
		ModuloPeriodoDTO moduloPeriodoDto = alterar_status_relacao_usuario_no_modulo_periodo(resultadoListagemGrupo,
				idGrupo, idModuloPeriodo, idModuloUsuario, status);

		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO listagemGrupoAposAtualizacaoDto = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo(nomeGrupo);

		ModuloPeriodoDTO moduloPeriodoAposAtualizacaoDto = obterModuloPeriodoDto(
				listagemGrupoAposAtualizacaoDto.getObjetosDto(), idGrupo, idModuloPeriodo);
		ModuloUsuarioDTO moduloUsuarioAPosAtualizacaoDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoAposAtualizacaoDto.getModulosUsuariosDto(), idModuloUsuario);

		Assert.assertEquals(new StatusDTO(status).toString(),
				moduloUsuarioAPosAtualizacaoDTO.getStatusDto().toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_desligado_apos_atualizacao_de_relacao_com_modulo_periodo_no_grupo()
			throws RemoteException {
		  
  		MaquinaTempo.mudarDataAtual("01/01/2016");
		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.DESLIGADO,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESLIGADO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_integrado_apos_atualizacao_de_relacao_com_modulo_periodo_no_grupo()
			throws RemoteException {
		
		MaquinaTempo.mudarDataAtual("01/01/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.AFASTADO,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.INTEGRADO,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.INTEGRADO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_do_cto_para_desistente_apos_periodo_de_retorno_explirar_e_remove_todas_as_esperas_aguardando()
			throws RemoteException {

		Long prontuario = new Long(12222);

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(prontuario.toString());
		especificacao.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO resultadoListagemEsperaAntesAplicacaoRegra = servicoSisLaraServerRmi
				.obterListagemEspera(especificacao);

		AutomatizadorTarefas automatizadorTarefas = Registro.obterAutomatizadorTarefas();
		automatizadorTarefas.executar();

		ResultadoListagemEsperaDTO resultadoListagemEsperaAposAplicacaoRegra = servicoSisLaraServerRmi
				.obterListagemEspera(especificacao);

		EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDTO = new EspecificacaoPesquisaUsuarioDTO();
		especificacaoPesquisaUsuarioDTO.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario.toString());
		ResultadoListagemUsuarioDTO resultadoPesquisaUsuarioDTO = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO);
		UsuarioDTO usuarioDto = (UsuarioDTO) obterDaRelacaoPorId(resultadoPesquisaUsuarioDTO.getObjetosDto(),
				prontuario);

		Assert.assertTrue(
				usuarioDto.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESISTENTE.toString()));
		Assert.assertTrue(resultadoListagemEsperaAntesAplicacaoRegra.getObjetosDto().size() == 2);
		Assert.assertTrue(resultadoListagemEsperaAposAplicacaoRegra.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_mantem_status_de_usuario_do_cto_como_desistente_apos_periodo_de_retorno_explirar_e_mais_de_uma_execucao_do_processo_ser_efetivada()
			throws RemoteException {

		AutomatizadorTarefas automatizadorTarefas = Registro.obterAutomatizadorTarefas();
		automatizadorTarefas.executar();
		automatizadorTarefas.executar();

		EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDTO = new EspecificacaoPesquisaUsuarioDTO();
		especificacaoPesquisaUsuarioDTO.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, "82222");
		ResultadoListagemUsuarioDTO resultadoPesquisaUsuarioDTO = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO);
		UsuarioDTO usuarioDto = (UsuarioDTO) obterDaRelacaoPorId(resultadoPesquisaUsuarioDTO.getObjetosDto(),
				new Long(82222));

		Assert.assertTrue(
				usuarioDto.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESISTENTE.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_arquivo_de_cobranca_de_contribuicoes_com_sucesso() throws Exception {
		RepositorioSislara repositorioSislara = Registro.obterRepositorioSislara();
		repositorioSislara.aplicarSequence(new Long(96755));
		AutomatizadorContribuicao automatizadorContribuicao = Registro.obterAutomatizadorContribuicao();
		automatizadorContribuicao.gerarArquivosCobranca(DataHoraUtils.criar("03/08/2015"));
		Collection<File> arquivoDeConfiguracaoECobranca = FileUtils.listFiles(
				new File(new Configuracao().obterConfiguracao(Configuracao.DIRETORIO_ARQUIVOS_COBRANCAS)), null, true);
		String conteudoArquivoCobranca = "";
		for (File arquivo : arquivoDeConfiguracaoECobranca) {
			if (!arquivo.getName().endsWith("zip")) {
				conteudoArquivoCobranca = FileUtils.readFileToString(arquivo, Charset.defaultCharset());
			}
		}

		// 2 arquivos, o original e o zipado.
		Assert.assertTrue(arquivoDeConfiguracaoECobranca.size() == 2);
		Assert.assertEquals(conteudoArquivoCobranca,
				"01REMESSA01COBRANCA       197600018000        LARAMARA ASS.BRAS.ASSIST.AO DE341BANCO ITAU SA  030815                                                                                                                                                                                                                                                                                                      000001\n"
						+ "611976000180001730009675590REAL000000000500096755           99A03081500000000000000000ARAKEN A. GOMES                        R. DONA EPONIMA AFONSECA, 226                       04720010SAO PAULO      SP                                                                                                                                                01000000000000000                               000002\n"
						+ "62LARAMARA ASSOC.BRAS.DE ASSIST.AO DEF.VISUAL                          CNPJ.67.640.441/0001-29                                              PABX 3660-6400                 SITE: WWW.LARAMARA.ORG.BR             TEL: 3660-6447                                                                                                                                                                           000003\n"
						+ "63                                                                     LARAMARA - AJUDANDO A TRANSFORMAR VIDAS                                                                                                   FINALIDADE: DONATIVO - PAGAVEL EM QUALQUER BANCO                                                                                                                                         000004\n"
						+ "611976000180001730009675670REAL000000000200096756           99A03081500000000000000000ANTONIO ALUIZIO RUSSO                  RUA FRANCISCO FAREL, 209 AP 100                     05436070SAO PAULO      SP                                                                                                                                                01000000000000000                               000005\n"
						+ "62LARAMARA ASSOC.BRAS.DE ASSIST.AO DEF.VISUAL                          CNPJ.67.640.441/0001-29                                              PABX 3660-6400                 SITE: WWW.LARAMARA.ORG.BR             TEL: 3660-6447                                                                                                                                                                           000006\n"
						+ "63                                                                     LARAMARA - AJUDANDO A TRANSFORMAR VIDAS                                                                                                   FINALIDADE: DONATIVO - PAGAVEL EM QUALQUER BANCO                                                                                                                                         000007\n"
						+ "9                                                                                                                                                                                                                                                                                                                                                                                                         000008\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_arquivo_de_cobranca_de_contribuicoes_com_sucesso_anternativo() throws Exception {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());
		boolean resultado = servicoSisLaraServerRmi.solicitarGeracaoArquivoCobranca(resultadoDto.getToken());

		Assert.assertTrue(resultado);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_arquivo_de_cobranca_de_contribuicoes_sem_permissao() throws Exception {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());
		boolean resultado = servicoSisLaraServerRmi.solicitarGeracaoArquivoCobranca(resultadoDto.getToken());

		Assert.assertFalse(resultado);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_retorno_por_ter_menos_de_21_anos_e_estar_com_status_desistente()
			throws RemoteException {

		AutomatizadorTarefas automatizadorTarefas = Registro.obterAutomatizadorTarefas();
		automatizadorTarefas.executar();

		EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDTO = new EspecificacaoPesquisaUsuarioDTO();
		especificacaoPesquisaUsuarioDTO.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, "72222");
		ResultadoListagemUsuarioDTO resultadoPesquisaUsuarioDTO = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO);
		UsuarioDTO usuarioDto = (UsuarioDTO) obterDaRelacaoPorId(resultadoPesquisaUsuarioDTO.getObjetosDto(),
				new Long(72222));

		Assert.assertTrue(
				usuarioDto.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.RETORNO.toString()));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_calcula_valor_total_a_partir_recurso_e_quantidade() throws RemoteException {
		String valorTotal = servicoSisLaraServerRmi.obterValorTotalPorDeRecurso(ContextoRecurso.construirRecursoDTO(),
				"3");

		Assert.assertEquals(valorTotal, "7500,00");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_recalcula_totais_projeto_com_sucesso() throws RemoteException {

		String quantidade = "3";
		ResultadoListagemProjetoDTO resultadoListagemProjetos = servicoSisLaraServerRmi.obterListagemProjetoAtivos();
		ProjetoDTO projetoDto = (ProjetoDTO) obterDaRelacaoPorId(resultadoListagemProjetos.getObjetosDto(),
				new Long(1222));

		String valorSomaTotalProdutoAntesRecalculacao = projetoDto.getSomaTotalProdutos();
		LoteRecursoDTO loteRecursoDTO = ((LoteRecursoDTO) obterDaRelacaoPorId(projetoDto.getLoteRecursoDto(),
				new Long(12345)));
		loteRecursoDTO.setQuantidade(quantidade);
		loteRecursoDTO
				.setValor(servicoSisLaraServerRmi.obterValorTotalPorDeRecurso(loteRecursoDTO.getRecursoDto(), quantidade));

		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoAposRecalculacaoDTO = servicoSisLaraServerRmi.calcularTotais(projetoDto);

		Assert.assertEquals(valorSomaTotalProdutoAntesRecalculacao, "5100,00");
		Assert.assertEquals(((ProjetoDTO) resultadoEdicaoProjetoAposRecalculacaoDTO.obterObjetoDtoEditado()).getSomaTotalProdutos(),
				"7600,00");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_remove_espera_com_data_expectavia_expirada_ha_mais_de_dois_anos_com_usuario_menor_de_21_anos_sem_nenhum_atendimento_no_periodo()
			throws RemoteException {
		
		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setDataInicio("01/01/2000");
		especificacaoPesquisaEsperaDto.setDataTermino("31/12/2999");
		especificacaoPesquisaEsperaDto.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);
		
		ResultadoListagemEsperaDTO resultadoListagemEsperaAntesDTO = servicoSisLaraServerRmi.obterListagemEspera(especificacaoPesquisaEsperaDto);
		
		MaquinaTempo.mudarDataAtual("03/11/2010");
		Registro.obterAutomatizadorTarefas().executar();
		ResultadoListagemEsperaDTO resultadoListagemEsperaAguardandoDTO = servicoSisLaraServerRmi.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaAguardandoDTO = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaAguardandoDTO.getObjetosDto(), new Long(15555));
		MaquinaTempo.restaurarDataOriginal();
		
		MaquinaTempo.mudarDataAtual("03/06/2017");
		Registro.obterAutomatizadorTarefas().executar();
		especificacaoPesquisaEsperaDto.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.CANCELADO.toString()));
		ResultadoListagemEsperaDTO resultadoListagemEsperaCanceladaDTO = servicoSisLaraServerRmi.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaCanceladaDTO = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaCanceladaDTO.getObjetosDto(), new Long(16666));
		MaquinaTempo.restaurarDataOriginal();
		
		especificacaoPesquisaEsperaDto.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		ResultadoListagemEsperaDTO resultadoListagemEsperaAposDTO = servicoSisLaraServerRmi.obterListagemEspera(especificacaoPesquisaEsperaDto);
		
		Assert.assertEquals(resultadoListagemEsperaAntesDTO.getObjetosDto().size(), 4);
		Assert.assertEquals(resultadoListagemEsperaAposDTO.getObjetosDto().size(), 1);
		Assert.assertEquals(esperaAguardandoDTO.getStatusDto(),
				new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		Assert.assertEquals(esperaCanceladaDTO.getStatusDto(), new StatusEsperaDTO(StatusEspera.CANCELADO.toString()));
		Assert.assertTrue(esperaCanceladaDTO.getJustificativaCancelamento().contains(
				"Em caso de interesse agendar SOMENTE SERVIÇO SOCIAL. Cancelamento efetuado automaticamente na espera de avaliação CTO com data de expectativa expirada há 2 anos, com usuário menor de 21 anos, sem nenhum atendimento no período."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_reserva_recursos_para_demandas_aguardando()
			throws RemoteException {

		StatusDemandaDTO statusDemandaReservadoDto = new StatusDemandaDTO("RESERVADO");
		StatusDemandaDTO statusDemandaAguardandoDto = new StatusDemandaDTO("AGUARDANDO");
		
		EspecificacaoPesquisaDemandaDTO especificacao = new EspecificacaoPesquisaDemandaDTO();
		especificacao.setRecursoDto(ContextoRecurso.construirRecursoDTO());
		ResultadoListagemDemandaDTO resultadoListagemDemandasMaquinaBrailleAntes = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);
		especificacao.setRecursoDto(ContextoRecurso.construirRecursoAlternativoDTO());
		ResultadoListagemDemandaDTO resultadoListagemDemandasBengalaAntes = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);

		ResultadoListagemProjetoDTO resultadoListagemProjetosAntesReservas = servicoSisLaraServerRmi.obterListagemProjetoAtivos();
		ProjetoDTO projetoAntesReservasDto = (ProjetoDTO) obterDaRelacaoPorId(
				resultadoListagemProjetosAntesReservas.getObjetosDto(), new Long(1222));
		
		DemandaDTO demandaMaquinaDto13333AguardandoAntes = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasMaquinaBrailleAntes.getObjetosDto(), new Long(13333));
		DemandaDTO demandaMaquinaDto14444AguardandoAntes = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasMaquinaBrailleAntes.getObjetosDto(), new Long(14444));	
		DemandaDTO demandaMaquinaDto15555AguardandoAntes = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasMaquinaBrailleAntes.getObjetosDto(), new Long(15555));	
		DemandaDTO demandaBengalaDto16666AguardandoAntes = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasBengalaAntes.getObjetosDto(), new Long(16666));	
		DemandaDTO demandaBengalaDto17777AguardandoAntes = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasBengalaAntes.getObjetosDto(), new Long(17777));	
				
		Registro.obterAutomatizadorTarefas().executar();

		especificacao.setRecursoDto(ContextoRecurso.construirRecursoDTO());
		ResultadoListagemDemandaDTO resultadoListagemDemandasMaquinaBrailleApos = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);
		especificacao.setRecursoDto(ContextoRecurso.construirRecursoAlternativoDTO());
		ResultadoListagemDemandaDTO resultadoListagemDemandasBengalaApos= servicoSisLaraServerRmi.obterListagemDemanda(especificacao);
				
		ResultadoListagemProjetoDTO resultadoListagemProjetosAposReservas = servicoSisLaraServerRmi.obterListagemProjetoAtivos();
		ProjetoDTO projetoAposReservasDto = (ProjetoDTO) obterDaRelacaoPorId(
				resultadoListagemProjetosAposReservas.getObjetosDto(), new Long(1222));
		
		DemandaDTO demandaMaquinaDto13333ReservadaApos = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasMaquinaBrailleApos.getObjetosDto(), new Long(13333));
		DemandaDTO demandaMaquinaDto14444ReservadaApos = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasMaquinaBrailleApos.getObjetosDto(), new Long(14444));		
		DemandaDTO demandaMaquinaDto15555AguardandoApos = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasMaquinaBrailleApos.getObjetosDto(), new Long(15555));		
		DemandaDTO demandaBengalaDto16666ReservadaApos = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasBengalaApos.getObjetosDto(), new Long(16666));		
		DemandaDTO demandaBengalaDto17777AguardandoApos = (DemandaDTO) obterDaRelacaoPorId(
				resultadoListagemDemandasBengalaApos.getObjetosDto(), new Long(17777));		
		
		Assert.assertEquals(demandaMaquinaDto13333AguardandoAntes.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertEquals(demandaMaquinaDto14444AguardandoAntes.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertEquals(demandaMaquinaDto15555AguardandoAntes.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertEquals(demandaMaquinaDto15555AguardandoApos.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertEquals(demandaBengalaDto16666AguardandoAntes.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertEquals(demandaBengalaDto17777AguardandoAntes.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertEquals(demandaBengalaDto17777AguardandoApos.getStatusDemandaDto(), statusDemandaAguardandoDto);
		Assert.assertTrue(projetoAntesReservasDto.getResumoReservas().isEmpty());
		
		Assert.assertEquals(demandaMaquinaDto13333ReservadaApos.getStatusDemandaDto(), statusDemandaReservadoDto);
		Assert.assertEquals(demandaMaquinaDto14444ReservadaApos.getStatusDemandaDto(), statusDemandaReservadoDto);
		Assert.assertEquals(demandaBengalaDto16666ReservadaApos.getStatusDemandaDto(), statusDemandaReservadoDto);
		Assert.assertTrue(projetoAposReservasDto.getResumoReservas().equals("Maquina Braille Laramara - 5000,00\nBengala - 100,00\n"));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_status_de_usuario_para_retorno_devido_falta_no_atendido_em_avaliacao_funcional()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(16666), new SetorDTO("CTO"), new FrequenciaDTO("FU"),
				ContextoDescricaoTipoAtendimento.construirDescricaoAvaliacaoFuncionalDTO(),
				ContextoModulo.construirModuloAEE());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.CASO_NOVO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_status_de_usuario_para_retorno_devido_falta_no_atendido_em_servico_social_avaliacao_e_triagem_do_PROCEJA()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(16666), new SetorDTO("PROCEJA"), new FrequenciaDTO("FU"),
				ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO(),
				ContextoModulo.construirModuloAvaliacaoETriagemDTO());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.CASO_NOVO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_usuario_caso_novo_para_retorno_por_ter_sido_efetivamente_atendido_outra_descricao_atendimento()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(16666), new SetorDTO("CTO"), new FrequenciaDTO("AT"),
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia(),
				ContextoModulo.construirModuloAEE());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.CASO_NOVO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_usuario_sem_status_para_retorno_por_ter_sido_efetivamente_atendido_em_avaliacao_funcional()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(13333), new SetorDTO("CTO"), new FrequenciaDTO("AT"),
				ContextoDescricaoTipoAtendimento.construirDescricaoAvaliacaoFuncionalDTO(),
				ContextoModulo.construirModuloAEE());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.RETORNO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_usuario_sem_status_para_retorno_por_ter_sido_efetivamente_atendido_em_servico_social_avaliacao_e_triagem_do_PROCEJA()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(13333), new SetorDTO("PROCEJA"), new FrequenciaDTO("AT"),
				ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO(),
				ContextoModulo.construirModuloAvaliacaoETriagemDTO());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.RETORNO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_usuario_caso_novo_para_retorno_por_ter_sido_efetivamente_atendido_em_avaliacao_funcional()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(16666), new SetorDTO("CTO"), new FrequenciaDTO("AT"),
				ContextoDescricaoTipoAtendimento.construirDescricaoAvaliacaoFuncionalDTO(),
				ContextoModulo.construirModuloAEE());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.RETORNO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_usuario_caso_novo_para_retorno_por_ter_sido_efetivamente_atendido_em_servico_social_avaliacao_e_triagem_do_PROCEJA()
			throws RemoteException {

		UsuarioDTO usuarioDTO = servico_salva_atendimento_individual_com_setor_frequencia_descricao_e_modulo(
				new Long(16666), new SetorDTO("PROCEJA"), new FrequenciaDTO("AT"),
				ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO(),
				ContextoModulo.construirModuloAvaliacaoETriagemDTO());

		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.RETORNO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_afastado_apos_atualizacao_de_relacao_com_modulo_periodo_no_grupo()
			throws RemoteException {
		MaquinaTempo.mudarDataAtual("31/12/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.DESISTENTE,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.AFASTADO,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.AFASTADO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_provisorio_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTO = servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
				StatusRelacaoComModulo.PROVISORIO, "G06-1", new Long(14444), new Long(77777), new Long(44444), "SS-1",
				new Long(66666), new Long(99999), new Long(12222));
		Assert.assertTrue(moduloUsuarioDTO.getObs()
				.contains("Inclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_acesso_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTO = servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
				StatusRelacaoComModulo.ACESSO, "G06-1", new Long(14444), new Long(77777), new Long(44444), "SS-1",
				new Long(66666), new Long(99999), new Long(12222));
		Assert.assertTrue(moduloUsuarioDTO.getObs()
				.contains("Inclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuarios_para_provisorio_e_acesso_e_inclui_em_grupos_distintos_com_modulo_de_reuniao_de_integracao_com_sucesso()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioAposInclusaoReuniaoIntegracaoSS1DTO = servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
				StatusRelacaoComModulo.ACESSO, "G06-1", new Long(14444), new Long(77777), new Long(44444), "SS-1",
				new Long(66666), new Long(99999), new Long(12222));

		ModuloUsuarioDTO moduloUsuarioAposInclusaoReuniaoIntegracaoSS2DTO = servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
				StatusRelacaoComModulo.PROVISORIO, "G08-1", new Long(15555), new Long(12345), new Long(12345), "SS-2",
				new Long(77777), new Long(44444), new Long(16666));

		Assert.assertTrue(moduloUsuarioAposInclusaoReuniaoIntegracaoSS1DTO.getObs()
				.contains("Inclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração. "));
		Assert.assertTrue(
				moduloUsuarioAposInclusaoReuniaoIntegracaoSS1DTO.getObs().contains("Solicitado pelo Grupo: G06-1"));
		Assert.assertTrue(
				moduloUsuarioAposInclusaoReuniaoIntegracaoSS1DTO.getObs().contains("Periodo referencia: [MATUTINO]"));
		Assert.assertTrue(moduloUsuarioAposInclusaoReuniaoIntegracaoSS2DTO.getObs()
				.contains("Inclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração. "));
		Assert.assertTrue(
				moduloUsuarioAposInclusaoReuniaoIntegracaoSS2DTO.getObs().contains("Solicitado pelo Grupo: G08-1"));
		Assert.assertTrue(
				moduloUsuarioAposInclusaoReuniaoIntegracaoSS2DTO.getObs().contains("Periodo referencia: [VESPERTINO]"));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_acesso_e_nao_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_devido_falta_vaga_e_deixa_na_pendencia()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		servico_altera_vagas_do_grupo_pelo_modulo_periodo("SS-1", new Long(99999), "0", resultadoDto.getToken());
		servico_altera_vagas_do_grupo_pelo_modulo_periodo("SS-2", new Long(44444), "0", resultadoDto.getToken());
		servico_altera_vagas_do_grupo_pelo_modulo_periodo("SS-5", new Long(54321), "0", resultadoDto.getToken());
		servico_altera_vagas_do_grupo_pelo_modulo_periodo("SS-6", new Long(654321), "0", resultadoDto.getToken());
		servico_altera_vagas_do_grupo_pelo_modulo_periodo("SS-7", new Long(7654321), "0", resultadoDto.getToken());

		servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.ACESSO,
				new Long(14444), new Long(77777), new Long(44444), resultadoDto.getToken());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().size(), 2);
		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().get(0).toString(),
				"Inclusão automática do Prontuário 82222 em reunião de integração será efetuada.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_integrado_e_inclui_em_diversos_grupos_com_modulo_de_reuniao_de_integracao_compativeis()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		servico_altera_vagas_do_grupo_pelo_modulo_periodo("SS-1", new Long(99999), "1", resultadoDto.getToken());

		servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.INTEGRADO,
				new Long(14444), new Long(77777), new Long(44444), resultadoDto.getToken());
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		List<Long> prontuariosAfetados = Arrays.asList(new Long[] { new Long(12222), new Long(82222) });

		ResultadoListagemGrupoDTO obterListagemGrupoSS1 = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1");
		GrupoDTO grupoSS1Dto = (GrupoDTO) obterDaRelacaoPorId(obterListagemGrupoSS1.getObjetosDto(), new Long(66666));
		ModuloPeriodoDTO moduloPeriodoSS1DTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoSS1Dto.getModulosPeriodosDto(), new Long(99999));
		ModuloUsuarioDTO moduloUsuarioSS1DTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoSS1DTO.getModulosUsuariosDto(), prontuariosAfetados);

		ResultadoListagemGrupoDTO obterListagemGrupoSS5 = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-5");
		GrupoDTO grupoSS5Dto = (GrupoDTO) obterDaRelacaoPorId(obterListagemGrupoSS5.getObjetosDto(), new Long(54321));
		ModuloPeriodoDTO moduloPeriodoSS5DTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoSS5Dto.getModulosPeriodosDto(), new Long(54321));
		ModuloUsuarioDTO moduloUsuarioSS5DTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoSS5DTO.getModulosUsuariosDto(), prontuariosAfetados);

		Assert.assertTrue(moduloUsuarioSS1DTO.getObs()
				.contains("Inclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração."));
		Assert.assertTrue(moduloUsuarioSS1DTO.getObs().contains("Solicitado pelo Grupo: G06-1"));
		Assert.assertTrue(moduloUsuarioSS1DTO.getObs().contains("Periodo referencia: [MATUTINO]"));
		Assert.assertTrue(moduloUsuarioSS5DTO.getObs()
				.contains("Inclusão automática de usuário INTEGRADO, PROVISORIO ou ACESSO em Reunião de Integração."));
		Assert.assertTrue(moduloUsuarioSS5DTO.getObs().contains("Solicitado pelo Grupo: G06-1"));
		Assert.assertTrue(moduloUsuarioSS5DTO.getObs().contains("Periodo referencia: [MATUTINO]"));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_acesso_antes_e_apos_atendimento_de_reuniao_de_integracao_e_nao_inclui_mais_de_uma_vez_em_grupo_com_modulo_de_reuniao_de_integracao_distintos()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.ACESSO,
				new Long(14444), new Long(77777), new Long(44444), resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoDto = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoDto.getObjetosDto(), new Long(66666));
		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), new Long(99999), DataHoraUtils.formatarData(Calendar.getInstance()), "09:00",
				"10:00");

		servicoSisLaraServerRmi.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.ACESSO,
				new Long(14444), new Long(77777), new Long(44444), resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoComReuniaoIntegracaoSemUsuarioIntegradoPorJaTerSidoIncluidoEmOutroGrupoDto = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("SS-2");
		GrupoDTO grupoComReuniaoIntegracaoSemUsuarioIntegradoPorJaTerSidoIncluidoEmOutroGrupoDto = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoComReuniaoIntegracaoSemUsuarioIntegradoPorJaTerSidoIncluidoEmOutroGrupoDto
						.getObjetosDto(),
				new Long(77777));
		ModuloPeriodoDTO moduloPeriodoReuniaoIntegracaoSemUsuarioIntegradosPorJaTerSidoIncluidoEmOutroGrupo = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoComReuniaoIntegracaoSemUsuarioIntegradoPorJaTerSidoIncluidoEmOutroGrupoDto.getModulosPeriodosDto(),
				new Long(44444));

		Assert.assertTrue(moduloPeriodoReuniaoIntegracaoSemUsuarioIntegradosPorJaTerSidoIncluidoEmOutroGrupo
				.getModulosUsuariosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_de_usuario_para_acesso_e_nao_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_por_nao_ser_grupo_de_atendimento_global()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servico_altera_relacao_de_modulo_usuario_e_retorno_modulo_periodo(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.ACESSO,
				new Long(12222), new Long(11111), new Long(11111), resultadoDto.getToken());

		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				((ModuloPeriodoDTO) resultadoEdicaoModuloPeriodoDTO.obterObjetoDtoEditado()).getModulosUsuariosDto(),
				new Long(11111));

		Assert.assertTrue(moduloUsuarioDTO.getObs().isEmpty());
		Assert.assertTrue(moduloUsuarioDTO.getStatusDto().toString().equals(StatusRelacaoComModulo.ACESSO.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_desistente_apos_atualizacao_de_relacao_com_modulo_periodo_no_grupo()
			throws RemoteException {
  		MaquinaTempo.mudarDataAtual("01/01/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.DESISTENTE,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.DESISTENTE,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESISTENTE.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_eventual_apos_atualizacao_de_relacao_com_modulo_periodo_no_grupo()
			throws RemoteException {
		  
  		MaquinaTempo.mudarDataAtual("01/01/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.EVENTUAL,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.EVENTUAL,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.EVENTUAL.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_desistente_apos_atualizacao_de_relacao_com_modulo_periodo_equivalente_concluido_no_grupo()
			throws RemoteException {
  		MaquinaTempo.mudarDataAtual("01/01/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.CONCLUIDO,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.CONCLUIDO,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESISTENTE.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_desistente_apos_atualizacao_de_relacao_com_modulo_periodo_equivalente_frequentou_no_grupo()
			throws RemoteException {
  		MaquinaTempo.mudarDataAtual("01/01/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.FREQUENTOU,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.FREQUENTOU,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESISTENTE.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	// Depende de data de inicio dos grupos id 12222 e 14444 atualizados para o
	// ano corrente
	public void servico_atualiza_status_de_usuario_para_desistente_apos_atualizacao_de_relacao_com_modulo_periodo_equivalente_participou_no_grupo()
			throws RemoteException {
  		MaquinaTempo.mudarDataAtual("01/01/2016");
		servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1"), StatusRelacaoComModulo.PARTICIPOU,
				new Long(14444), new Long(77777), new Long(44444));

		UsuarioDTO usuarioDTO = servico_altera_relacao_de_modulo_usuario_e_retornar_usuario_refenciado(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1"), StatusRelacaoComModulo.PARTICIPOU,
				new Long(12222), new Long(11111), new Long(11111));
		MaquinaTempo.restaurarDataOriginal();
		
		Assert.assertTrue(
				usuarioDTO.getStatusUsuarioAtualDto().toString().equals(StatusRelacaoComModulo.DESISTENTE.toString()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_modulo_periodo_no_grupo_com_erro_obrigatoriedade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));
		moduloPeriodoDto.getDiasSemanaEHorariosDaAtividadeDto().clear();

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupo = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAtualizacaoModuloPeriodoGrupo.sucesso());
		Assert.assertEquals(resultadoAtualizacaoModuloPeriodoGrupo.obterMensagens(),
				"Insira um conjunto de Dia da Semana e Horario.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_modulo_periodo_no_grupo_com_erro_concorrencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupo = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupoConcorrente = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoAtualizacaoModuloPeriodoGrupo.sucesso());
		Assert.assertFalse(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.sucesso());
		Assert.assertEquals(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_modulo_periodo_no_grupo_com_erro_concorrencia_apos_edicao_grupo()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));

		ResultadoEdicaoGrupoDTO resultadoAtualizacaoGrupo = servicoSisLaraServerRmi.editarGrupo(
				(GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222)),
				resultadoDto.getToken());

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupoConcorrente = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoAtualizacaoGrupo.sucesso());
		Assert.assertFalse(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.sucesso());
		Assert.assertEquals(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_modulo_periodo_no_grupo_com_erro_concorrencia_apos_geracao_atendimento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(),
				((ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(11111))).getId(),
				"31/12/2012", "22:22", "23:00");

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentosGrupo = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupoConcorrente = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoGeracaoAtendimentosGrupo.sucesso());
		Assert.assertFalse(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.sucesso());
		Assert.assertEquals(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_modulo_periodo_no_grupo_com_erro_concorrencia_apos_edicao_atendimento_grupo()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupoConcorrente = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoAtualizacaoAtendimentoGrupo.sucesso());
		Assert.assertFalse(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.sucesso());
		Assert.assertEquals(resultadoAtualizacaoModuloPeriodoGrupoConcorrente.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_atualiza_modulo_periodo_no_grupo_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111));

		ResultadoEdicaoModuloPeriodoDTO resultadoAtualizacaoModuloPeriodoGrupo = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAtualizacaoModuloPeriodoGrupo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_grupo_por_causa_da_concorrencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemDto = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		GrupoDTO grupoAAlterarDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemDto.getObjetosDto(),
				new Long(12222));
		grupoAAlterarDto.setVersao("1222");

		ResultadoEdicaoGrupoDTO resultadoAlteracaoGrupo = servicoSisLaraServerRmi.editarGrupo(grupoAAlterarDto,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoGrupo.sucesso());
		Assert.assertEquals(resultadoAlteracaoGrupo.obterMensagens(),
				"Os dados foram alterados por outro usuário do sistema. Por favor, tente novamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_novo_grupo_com_incosistencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoASalvarDto = ContextoGrupo.construirGrupoDTO();
		grupoASalvarDto.setNivel("AAA");

		ResultadoEdicaoGrupoDTO resultadoInclusaoGrupo = servicoSisLaraServerRmi.editarGrupo(grupoASalvarDto,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoGrupo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_novo_grupo_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoASalvarDto = ContextoGrupo.construirGrupoDTOSemAtendimentos();

		ResultadoEdicaoGrupoDTO resultadoInclusaoGrupo = servicoSisLaraServerRmi.editarGrupo(grupoASalvarDto,
				resultadoDto.getToken());

		ResultadoEdicaoGrupoDTO resultadoInclusaoGrupoComErro = servicoSisLaraServerRmi.editarGrupo(grupoASalvarDto,
				resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoGrupo.sucesso());
		Assert.assertFalse(resultadoInclusaoGrupoComErro.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_grupo_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		GrupoDTO grupoASalvarDto = ContextoGrupo.construirGrupoDTO();

		ResultadoEdicaoGrupoDTO resultadoInclusaoGrupo = servicoSisLaraServerRmi.editarGrupo(grupoASalvarDto,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoGrupo.sucesso());
		Assert.assertNotNull(resultadoInclusaoGrupo.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_modulo_periodo_com_erro() throws RemoteException {
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		ResultadoValidacaoModuloPeriodoDTO resultadoValidacaoModuloPeriodo = servicoSisLaraServerRmi
				.validarModuloPeriodo(moduloPeriodoDto);

		Assert.assertFalse(resultadoValidacaoModuloPeriodo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_modulo_pre_cadastro_e_usuario_com_erro_duplicacao() throws RemoteException {

		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());
		List<ModuloUsuarioDTO> modulosUsuario = new ArrayList<ModuloUsuarioDTO>();
		modulosUsuario.add(moduloUsuarioDTO);

		ModuloPreCadastroDTO moduloPreCadastroDTO = ContextoModuloPreCadastro.fabricarModuloPreCadastroDTO();
		moduloPreCadastroDTO.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		List<ModuloPreCadastroDTO> modulosPreCadastro = new ArrayList<ModuloPreCadastroDTO>();
		modulosPreCadastro.add(moduloPreCadastroDTO);

		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloUsuario = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(modulosUsuario, moduloUsuarioDTO);
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloPreCadastro = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(modulosPreCadastro, moduloPreCadastroDTO);

		Assert.assertFalse(resultadoValidacaoModuloUsuario.sucesso());
		Assert.assertTrue(resultadoValidacaoModuloUsuario.obterMensagens().contains("O Usuário já está integrado."));
		Assert.assertFalse(resultadoValidacaoModuloPreCadastro.sucesso());
		Assert.assertTrue(resultadoValidacaoModuloPreCadastro.obterMensagens().contains("O Pré-Cadastro já está integrado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_modulo_pre_cadastro_usuario_sem_erro_duplicacao() throws RemoteException {

		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());
		List<ModuloUsuarioDTO> modulosUsuario = new ArrayList<ModuloUsuarioDTO>();

		ModuloPreCadastroDTO moduloPreCadastroDTO = ContextoModuloPreCadastro.fabricarModuloPreCadastroDTOSemErro();
		moduloPreCadastroDTO.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		List<ModuloPreCadastroDTO> modulosPreCadastro = new ArrayList<ModuloPreCadastroDTO>();

		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloUsuario = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(modulosUsuario, moduloUsuarioDTO);
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloPreCadastro = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(modulosPreCadastro, moduloPreCadastroDTO);

		Assert.assertTrue(resultadoValidacaoModuloUsuario.sucesso());
		Assert.assertTrue(resultadoValidacaoModuloPreCadastro.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_modulo_periodo_sem_erro() throws RemoteException {
		ModuloPeriodoDTO moduloPeriodoDto = ContextoModuloPeriodo.fabricarModuloPeriodoDTO();
		ResultadoValidacaoModuloPeriodoDTO resultadoValidacaoModuloPeriodo = servicoSisLaraServerRmi
				.validarModuloPeriodo(moduloPeriodoDto);

		Assert.assertTrue(resultadoValidacaoModuloPeriodo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_usuario_com_erro() throws RemoteException {
		AtendimentoUsuarioDTO atendimentoUsuarioDto = new AtendimentoUsuarioDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoUsuarioDto = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoUsuarioDto);

		Assert.assertFalse(resultadoValidacaoAtendimentoUsuarioDto.sucesso());
		Assert.assertTrue(resultadoValidacaoAtendimentoUsuarioDto.obterMensagens().contains("Insira o Usuário."));
		Assert.assertTrue(resultadoValidacaoAtendimentoUsuarioDto.obterMensagens().contains("Insira uma Frequência."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_usuario_sem_erro() throws RemoteException {
		AtendimentoUsuarioDTO atendimentoUsuarioDto = ContextoAtendimentoUsuario.construirAtendimentoUsuarioDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoUsuario = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoUsuarioDto);

		Assert.assertTrue(resultadoValidacaoAtendimentoUsuario.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_pre_cadastro_com_erro() throws RemoteException {
		AtendimentoPreCadastroDTO atendimentoPreCadastroDto = new AtendimentoPreCadastroDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoPreCadastroDto = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoPreCadastroDto);

		Assert.assertFalse(resultadoValidacaoAtendimentoPreCadastroDto.sucesso());
		Assert.assertTrue(resultadoValidacaoAtendimentoPreCadastroDto.obterMensagens().contains("Insira o Pré-Cadastro."));
		Assert.assertTrue(resultadoValidacaoAtendimentoPreCadastroDto.obterMensagens().contains("Insira uma Frequência."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_pre_cadastro_sem_erro() throws RemoteException {
		AtendimentoPreCadastroDTO atendimentoPreCadastroDto = ContextoAtendimentoPreCadastro
				.construirAtendimentoPreCadastroDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoPreCadastro = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoPreCadastroDto);

		Assert.assertTrue(resultadoValidacaoAtendimentoPreCadastro.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_profissional_com_erro() throws RemoteException {
		AtendimentoProfissionalDTO atendimentoProfissinalDto = new AtendimentoProfissionalDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoProfissionalDto = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoProfissinalDto);

		Assert.assertFalse(resultadoValidacaoAtendimentoProfissionalDto.sucesso());
		Assert.assertTrue(resultadoValidacaoAtendimentoProfissionalDto.obterMensagens().contains("Insira o Profissional."));
		Assert.assertTrue(resultadoValidacaoAtendimentoProfissionalDto.obterMensagens().contains("Insira uma Frequência."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_profissional_sem_erro() throws RemoteException {
		AtendimentoProfissionalDTO atendimentoProfissionalDto = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoProfissional = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoProfissionalDto);

		Assert.assertTrue(resultadoValidacaoAtendimentoProfissional.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_comunidade_com_erro() throws RemoteException {
		AtendimentoComunidadeDTO atendimentoComunidadeDto = new AtendimentoComunidadeDTO();
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoComundiadeDto = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoComunidadeDto);

		Assert.assertFalse(resultadoValidacaoAtendimentoComundiadeDto.sucesso());
		Assert.assertTrue(resultadoValidacaoAtendimentoComundiadeDto.obterMensagens().contains("Insira um Pré Cadastro."));
		Assert.assertTrue(resultadoValidacaoAtendimentoComundiadeDto.obterMensagens().contains("Insira uma Formação."));
		Assert.assertTrue(resultadoValidacaoAtendimentoComundiadeDto.obterMensagens().contains("Insira uma Frequência."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_atendimento_comunidade_sem_erro() throws RemoteException {
		AtendimentoComunidadeDTO atendimentoComunidadeDto = new AtendimentoComunidadeDTO();
		atendimentoComunidadeDto.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		atendimentoComunidadeDto.setTipoVinculoDto(ContextoTipoVinculo.fabricarDTOComTodosOsDados());
		atendimentoComunidadeDto
				.setInformacaoAtendimentoDto(ContextoInformacaoAtendimento.construirInformacaoAtendimentoDTO());
		ResultadoValidacaoAtendimentoBaseDTO resultadoValidacaoAtendimentoComundiadeDto = servicoSisLaraServerRmi
				.validarAtendimentoAuxiliarBase(atendimentoComunidadeDto);

		Assert.assertTrue(resultadoValidacaoAtendimentoComundiadeDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_modulo_usuario_com_erro() throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDto = new ModuloUsuarioDTO();
		moduloUsuarioDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTO());
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloUsuario = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(moduloUsuarioDto);

		Assert.assertFalse(resultadoValidacaoModuloUsuario.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_modulo_usuario_sem_erro() throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDto = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloUsuario = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(moduloUsuarioDto);

		Assert.assertTrue(resultadoValidacaoModuloUsuario.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_modulo_pre_cadastro_com_erro() throws RemoteException {
		ModuloPreCadastroDTO moduloPreCadastroDto = new ModuloPreCadastroDTO();
		moduloPreCadastroDto.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloPreCadastro = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(moduloPreCadastroDto);

		Assert.assertFalse(resultadoValidacaoModuloPreCadastro.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_modulo_pre_cadastro_sem_erro() throws RemoteException {
		ModuloPreCadastroDTO moduloPreCadastroDto = ContextoModuloPreCadastro.fabricarModuloPreCadastroDTOSemErro();
		ResultadoValidacaoModuloRelacaoBaseDTO resultadoValidacaoModuloPreCadastro = servicoSisLaraServerRmi
				.validarModuloRelacaoBase(moduloPreCadastroDto);

		Assert.assertTrue(resultadoValidacaoModuloPreCadastro.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_programacao_com_erro() throws RemoteException {
		ProgramacaoDTO programacaoDto = new ProgramacaoDTO();
		ResultadoValidacaoProgramacaoDTO resultadoValidacaoProgramacao = servicoSisLaraServerRmi
				.validarProgramacao(programacaoDto);

		Assert.assertFalse(resultadoValidacaoProgramacao.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_validacao_programacao_sem_erro() throws RemoteException {
		ProgramacaoDTO programacaoDto = ContextoProgramacao.construirProgramacaoDTO();
		ResultadoValidacaoProgramacaoDTO resultadoValidacaoProgramacao = servicoSisLaraServerRmi
				.validarProgramacao(programacaoDto);

		Assert.assertTrue(resultadoValidacaoProgramacao.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_solicita_geracao_atendimentos_a_partir_de_grupo_sem_erro() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(11111)).getId(),
				"31/12/2012", "22:22", "23:00");

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO2 = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(11111)).getId(),
				"31/12/2012", "23:00", "23:30");
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento2 = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO2, resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoDtoAposGeracaoDeDoisAtendimentos = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G02-1");

		Assert.assertTrue(resultadoGeracaoAtendimento.sucesso());
		Assert.assertTrue(resultadoGeracaoAtendimento2.sucesso());
		Assert.assertEquals(
				obterModuloPeriodoDto(resultadoListagemGrupoDtoAposGeracaoDeDoisAtendimentos.getObjetosDto(),
						new Long(12222), new Long(11111)).getAtendimentosGrupoDto().size(),
				3);
		Assert.assertNotEquals(
				((ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(66666))).getVersao(),
				obterModuloPeriodoDto(resultadoListagemGrupoDtoAposGeracaoDeDoisAtendimentos.getObjetosDto(),
						new Long(12222), new Long(11111)).getVersao());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_geracao_atendimentos_a_partir_de_grupo_com_data_futura() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(66666)).getId(),
				"31/12/2090", "09:00", "19:00");

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimento.sucesso());
		Assert.assertEquals(resultadoGeracaoAtendimento.obterMensagens(),
				"Não é possível gerar atendimentos para um grupo sem integrantes.\nInsira uma data igual ou anterior ao dia de hoje.\n\n");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_atendimentos_de_reuniao_de_integracao_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1");
		ModuloPeriodoDTO moduloPeriodoDto = obterModuloPeriodoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(66666), new Long(99999));
		
		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.INTEGRADO.toString()));
		moduloPeriodoDto.setModulosUsuariosDto(Arrays.asList(moduloUsuarioDTO));
		
		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDto, resultadoDto.getToken());

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(66666));
		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(99999)).getId(),
				"31/12/2000", "09:00", "19:00");

		servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());
		
		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDuplicadoDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(99999)).getId(),
				"30/12/2000", "09:00", "19:00");
		
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDuplicadoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimento.sucesso());
		Assert.assertTrue(resultadoGeracaoAtendimento.obterMensagens()
				.contains("Não é possível existir mais de um atendimento em Reunião de Integração com status NORMAL."));
	}	

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_solicita_cancelamento_atendimento_a_partir_de_grupo_sem_erro() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		AtendimentoGrupoDTO atendimentoGropoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));

		ResultadoEdicaoAtendimentoGrupoDTO resultadoCancelamentoAtendimento = servicoSisLaraServerRmi
				.cancelarAtendimentoGrupo(atendimentoGropoDto, resultadoDto.getToken());

		AtendimentoGrupoDTO atendimentoGrupoDto = (AtendimentoGrupoDTO) resultadoCancelamentoAtendimento
				.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoCancelamentoAtendimento.sucesso());
		Assert.assertTrue(atendimentoGrupoDto.getStatusAtendimentoDto().equals(new StatusAtendimentoDTO("CANCELADO")));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_solicita_cancelamento_atendimento_com_sucesso_em_concorrencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		servicoSisLaraServerRmi.editarGrupo(
				(GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222)),
				resultadoDto.getToken());

		AtendimentoGrupoDTO atendimentoGropoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));

		ResultadoEdicaoAtendimentoGrupoDTO resultadoCancelamentoAtendimento = servicoSisLaraServerRmi
				.cancelarAtendimentoGrupo(atendimentoGropoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoCancelamentoAtendimento.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_solicita_geracao_atendimentos_de_grupo_a_partir_de_grupo_com_erro_por_falta_de_permissao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(66666)).getId(),
				"31/12/2012", "09:00", "19:00");
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimento.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_solicita_geracao_atendimentos_de_grupo_com_erro_de_horario_em_conflito()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(11111)).getId(),
				"31/12/2012", "09:00", "19:00");
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoConflito = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimento.sucesso());
		Assert.assertFalse(resultadoGeracaoAtendimentoConflito.sucesso());
		Assert.assertEquals(resultadoGeracaoAtendimentoConflito.obterMensagens(),
				"Não é possível gerar um novo atendimento de grupo em horário conflitante com um atendimento já existente.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_solicita_cancelamento_atendimento_de_grupo_a_partir_de_grupo_com_erro_por_falta_de_permissao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		AtendimentoGrupoDTO atendimentoGropoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));
		ResultadoEdicaoAtendimentoGrupoDTO resultadoCancelamentoAtendimento = servicoSisLaraServerRmi
				.cancelarAtendimentoGrupo(atendimentoGropoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoCancelamentoAtendimento.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_solicita_geracao_atendimentos_de_grupo_a_partir_de_grupo_com_erro_por_falta_de_usuarios_ou_precadastros()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoDto.getId(), obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(66666)).getId(),
				"31/12/2012", "09:00", "19:00");
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAtendimento.sucesso());
		Assert.assertEquals(resultadoGeracaoAtendimento.obterMensagens(),
				"Não é possível gerar atendimentos para um grupo sem integrantes.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_e_xls_de_atendimento_de_usuario_em_grupo_simples() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(22222));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(grupoDto,
				moduloPeriodoDto, resultadoAutenticacaoPDFDto.getToken());

		ResultadoAutenticacaoDTO resultadoAutenticacaoXLSDto = servicoSisLaraServerRmi
				.autenticarLogin(new CredencialDTO("vvitalino", "1234"));
		ArquivoDTO relatorioXLS = servicoSisLaraServerRmi.gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(grupoDto,
				moduloPeriodoDto, resultadoAutenticacaoXLSDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(relatorioXLS.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32749]));
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioXLS.obterConteudo(), new byte[43008]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_atendimento_de_usuario_em_grupo_detalhado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(22222));
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(12222));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAtendimentoDeUsuarioNoGrupoDetalhado(grupoDto,
				moduloPeriodoDto, usuarioDto, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32802]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_folha_de_rosto() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFolhaDeRosto(new Long(12222),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33236]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_geracao_atendimentos_individuais_e_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioGeracaoAtendimentosIndividuaisEGrupo(
				"31/01/2001", "31/12/2013", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32629]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_vagas_em_grupos_ativos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioVagasEmGruposAtivos(resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32757]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_vagas_em_grupos_ativos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoInvalida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioVagasEmGruposAtivos(resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todas_frequencias_ordenado_por_data_lancamento() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioTodasFrequenciasOrdenadoPorDataLancamento(
				new Long(12222), resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33003]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_acompanhamento_programacao_de_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
				"G02-1-01/01/2016", resultadoAutenticacaoPDFDto.getToken());
		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33080]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_fluxo_de_atendimento_casos_novos_de_avaliacao_funcional()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFluxoAtendimentoCasosNovosDeAvaliacaoFuncional(
				"01/01/2010", "31/12/2010", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32713]));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_xls_de_mailing_de_visita_monitorada() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		
		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				new Long(999999), new Long(999999), "01/01/2016", "09:00", "10:00");
		servicoSisLaraServerRmi.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO,
				resultadoAutenticacaoDto.getToken());

		ArquivoDTO relatorioXLS = servicoSisLaraServerRmi
				.gerarRelatorioMailingVisitaMonitorada("01/01/2016", "31/12/2016",
						Arrays.asList(ContextoTipoVinculo.fabricarEstudanteDTOComTodosOsDados(),
								ContextoTipoVinculo.fabricarPrivadoDTOComTodosOsDados()),
						resultadoAutenticacaoDto.getToken());

		Assert.assertTrue(relatorioXLS.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioXLS.obterConteudo(), new byte[41472]));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_xls_de_mailing_de_visita_monitorada_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioXLS = servicoSisLaraServerRmi
				.gerarRelatorioMailingVisitaMonitorada("01/01/2016", "31/12/2016",
						Arrays.asList(ContextoTipoVinculo.fabricarEstudanteDTOComTodosOsDados(),
								ContextoTipoVinculo.fabricarPrivadoDTOComTodosOsDados()),
						resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(relatorioXLS.sucesso());
		Assert.assertTrue(relatorioXLS.obterMensagens().contains("Você não possui autorização para realizar a operação.\n"));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_fluxo_de_atendimento_retornos_de_avaliacao_funcional()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFluxoAtendimentoRetornosDeAvaliacaoFuncional(
				"01/01/2015", "31/12/2015", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32712]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_acompanhamento_programacao_de_grupo_por_falta_permissao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
				"G02-1-31/12/2011", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_programacao_do_grupo_para_familia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(11111));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioProgramacaoDoGrupoParaFamilia("01/01/1900",
				"31/12/2999", grupoDto, moduloPeriodoDto, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32746]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_solicitacao_relatorio() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioSolicitacaoRelatorio(new Long(12222),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33271]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_solicitacao_relatorio() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioSolicitacaoRelatorio(new Long(12222),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_carga_horaria_de_usuarios_por_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioCargaHorariaDeUsuarioPorGrupo(
				"G02-1-01/01/2016", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32804]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_carga_horaria_de_usuarios_por_grupo_sem_permissao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioCargaHorariaDeUsuarioPorGrupo(
				"G02-1-31/12/2011", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_atendidos_por_comunidade_visita_monitorada()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendidosPorVisitaMonitorada(
				"01/01/2000", "31/12/2099", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32603]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todos_usuarios_com_status_provisorio_ou_acesso_nos_grupos()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodosUsuarioComStatusDeGrupoProvisorioOuAcesso(resultadoAutenticacaoPDFDto.getToken());
		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32964]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidade_atendidos_por_comunidade_visita_monitorada()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendidosPorVisitaMonitorada(
				"01/01/2000", "31/12/2099", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_participacao_usuario_em_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioParticipacaoUsuarioEmGrupos(new Long(12222),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32834]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todos_atendimentos_do_usuario_ordenado_por_grupo()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(
				new Long(12222), resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32824]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todos_atendimentos_do_usuario_ordenado_por_data_lancamento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(new Long(12222),
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32943]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todos_atendimentos_individuais_do_usuario_ordenado_por_data_lancamento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento(new Long(12222),
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33124]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_usuario_por_idade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioQuantidadeUsuarioOrdenadoPorIdade(resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32535]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_pessoas_na_lista_de_espera_ordernado_por_expectativa()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32746]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_pessoas_na_lista_de_espera_por_faixa_idade()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(
				new Long(0), new Long(90), resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32742]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_retirada_de_prontuarios_no_agendamento() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioRetiradaProntuariosNoAgendamento("31/01/2012",
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32650]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_atendimentos_individuais() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendimentosIndividuais("01/01/2000",
				"31/12/2994", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32825]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_todos_atendimentos_individuais_do_usuario_ordenado_por_data_lancamento_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodosAtendimentosIndividuaisDoUsuarioOrdenadoPorDataLancamento(new Long(12222),
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_tipo_atendimento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorTipoAtendimento(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1676]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_tipo_atendimento_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorTipoAtendimento(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_uf() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorUF("01/01/2012",
				"31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1508]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_uf_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorUF("01/01/2012",
				"31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_renda() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorRenda("01/01/2012",
				"31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1484]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_renda_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorRenda("01/01/2012",
				"31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_regiao_sp() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1512]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_regiao_sp_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorRegiaoSP(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_municipio_sp() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorMunicipioSP(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1515]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_municipio_SP_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorMunicipioSP(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_escolaridade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorEscolaridade(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1517]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_escolaridade_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorEscolaridade(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_deficiencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorDeficiencia(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1535]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_deficiencia_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorDeficiencia(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_classificacao_instituicao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioComasAtendidosAtendimentosPorClassificacaoInstituicao("01/01/2012", "31/12/2012",
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1516]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_classificacao_instituicao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioComasAtendidosAtendimentosPorClassificacaoInstituicao("01/01/2012", "31/12/2012",
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_beneficio() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorBeneficio(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1546]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_e_atendimentos_por_beneficio_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosAtendimentosPorBeneficio(
				"01/01/2012", "31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_do_comas_atendidos_por_idade_e_genero() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosPorIdadeEGenero("01/01/2012",
				"31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[1634]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_do_comas_atendidos_por_idade_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioComasAtendidosPorIdadeEGenero("01/01/2012",
				"31/12/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_atendidos_sem_informacao_deficiencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAtendidosSemInformacaoDeficiencia("01/01/2000",
				"31/12/2099", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_atendidos_sem_informacao_deficiencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAtendidosSemInformacaoDeficiencia("01/01/2000",
				"31/12/2099", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32656]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_atendidos_por_tipos_vinculos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendidosPorTiposVinculos(
				"01/01/2000", "31/12/2994", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32609]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_atendimentos_para_prefeitura() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendimentosParaPrefeitura(
				"01/01/2000", "31/12/2994", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32683]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidade_atendidos_por_tipos_vinculos_por_grupo()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendidosPorTiposVinculosPorGrupo(
				"01/01/2000", "31/12/2994", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32598]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todas_retiradas_pendentes() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodasRetiradasPendentes(resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32590]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_todas_retiradas_por_prontuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioTodasRetiradasPorProntuario(new Long(13333),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32601]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_participacao_usuario_em_grupo_por_falta_de_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioParticipacaoUsuarioEmGrupos(new Long(12222),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidade_atendimentos_para_prefeitura() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendimentosParaPrefeitura(
				"01/01/2000", "31/12/2994", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_folha_de_rosto() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFolhaDeRosto(new Long(12222),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_geracao_atendimentos_individuais_e_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioGeracaoAtendimentosIndividuaisEGrupo(
				"31/01/2001", "31/12/2013", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_acompanhamento_programacao_de_grupo_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAcompanhamentoProgramacaoNoGrupo(
				"G02-1-31/12/2011", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_atendimento_de_usuario_em_grupo_simples_por_falta_de_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(22222));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAtendimentoDeUsuarioNoGrupoSimples(grupoDto,
				moduloPeriodoDto, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_atendimento_de_usuario_em_grupo_detalhado_por_falta_de_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(22222));
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(12222));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioAtendimentoDeUsuarioNoGrupoDetalhado(grupoDto,
				moduloPeriodoDto, usuarioDto, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_todos_atendimentos_do_usuario_ordenado_por_grupo_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorGrupo(
				new Long(12222), resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_todos_atendimentos_do_usuario_ordenado_por_data_lancamento_por_falta_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodosAtendimentosDoUsuarioOrdenadoPorDataLancamento(new Long(12222),
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_retirada_de_prontuarios_no_agendamento_por_falta_de_autorizacao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioRetiradaProntuariosNoAgendamento("03/02/2012",
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidade_atendimentos_individuais() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendimentosIndividuais("01/01/2012",
				"01/01/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidade_atendidos_por_tipos_vinculos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadeAtendidosPorTiposVinculos(
				"01/01/2012", "01/01/2012", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidade_pessoas_na_lista_de_espera_ordernado_por_expectativa()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioQuantidadePessoasNaListaDeEsperaOrdenadoPorExpectativa(
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidade_pessoas_na_lista_de_espera_por_faixa_de_idade()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadePessoasNaListaDeEsperaPorFaixaIdade(
				new Long(23), new Long(23), resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_conferencia_agendamento_e_atendimentos_individual()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33007]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_conferencia_agendamento_e_atendimentos_individual()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioConferenciaDeAgendamentosEAtendimentosIndividuais(
						resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_todas_retiradas_pendentes() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi
				.gerarRelatorioTodasRetiradasPendentes(resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_todas_retiradas_por_prontuario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioTodasRetiradasPorProntuario(new Long(13333),
				resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_altera_extensao_relatorio_padrao_para_xls() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoDto = servicoSisLaraServerRmi
				.alterarExtensaoRelatorioParaXLS(resultadoAutenticacaoDto.getToken());

		Assert.assertTrue(resultadoAlteracaoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_altera_extensao_relatorio_padrao_para_pdf() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoAlteracaoExtensaoRelatorioDTO resultadoAlteracaoDto = servicoSisLaraServerRmi
				.alterarExtensaoRelatorioParaPDF(resultadoAutenticacaoDto.getToken());

		Assert.assertTrue(resultadoAlteracaoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_senha_conta_acesso_com_sucesso() throws RemoteException {
		CredencialDTO credencialDto = construirCredencialAlternativaDtoValida();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi.autenticarLogin(credencialDto);

		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = servicoSisLaraServerRmi.alterarSenha(credencialDto,
				resultadoAutenticacaoDto.getToken());

		ResultadoAutenticacaoDTO resultadoAutenticacaoAposAlteracaoDto = servicoSisLaraServerRmi
				.autenticarLogin(credencialDto);

		Assert.assertTrue(resultadoAlteracaoSenhaDto.sucesso());
		Assert.assertTrue(resultadoAutenticacaoAposAlteracaoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_senha_conta_acesso_com_senha_diferente() throws RemoteException {
		CredencialDTO credencialDto = construirCredencialAlternativaDtoInvalida();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi.autenticarLogin(credencialDto);

		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = servicoSisLaraServerRmi.alterarSenha(credencialDto,
				resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoSenhaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_senha_conta_acesso_sem_senha_em_branco() throws RemoteException {
		CredencialDTO credencialDto = construirCredencialDtoValida();

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi.autenticarLogin(credencialDto);

		ResultadoAlteracaoSenhaDTO resultadoAlteracaoSenhaDto = servicoSisLaraServerRmi
				.alterarSenha(construirCredencialDtoValidaSemSenha(), resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoSenhaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_prontuarios_escaneados_por_usuario() throws RemoteException {
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(12222));

		ResultadoListagemProntuarioEscaneadoDTO resultadoListagemProntuarioEscaneado = servicoSisLaraServerRmi
				.obterListagemProntuariosEscaneados(usuarioDto);

		Assert.assertTrue(resultadoListagemProntuarioEscaneado.sucesso());
		Assert.assertEquals(resultadoListagemProntuarioEscaneado.getObjetosDto().size(), 2);
		Assert.assertFalse(resultadoListagemProntuarioEscaneado.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_prontuario_escaneado() throws RemoteException {
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(12222));

		ResultadoListagemProntuarioEscaneadoDTO resultadoListagemProntuarioEscaneado = servicoSisLaraServerRmi
				.obterListagemProntuariosEscaneados(usuarioDto);

		ProntuarioEscaneadoDTO prontuarioEscaneado = servicoSisLaraServerRmi.obterProntuarioEscaneado(usuarioDto,
				resultadoListagemProntuarioEscaneado.getObjetosDto().get(0));

		Assert.assertNotNull(prontuarioEscaneado);
		Assert.assertTrue(prontuarioEscaneado.getArquivoDto().obterConteudo().length != 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_agendamento_unico_a_partir_de_especificacao_invalida()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = new EspecificacaoGeracaoAgendamentoDTO();
		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamento.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_agendamento_por_modulo_bloqueado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDto = new EspecificacaoGeracaoAgendamentoDTO();
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		especificacaoGeracaoAgendamentoDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		especificacaoGeracaoAgendamentoDto.setModuloDto(ContextoModulo.construirModuloDTO());
		List<ProfissionalDTO> profissionais = new ArrayList<>();
		profissionais.add(ContextoProfissional.construirProfissionalDTO());
		especificacaoGeracaoAgendamentoDto.setProfissionaisDto(profissionais);
		especificacaoGeracaoAgendamentoDto.setSetorDto(new SetorDTO("CTO"));
		especificacaoGeracaoAgendamentoDto
				.setLocalAtendimentoDto(ContextoLocalAtendimento.construirLocalAtendimentoDTO());
		especificacaoGeracaoAgendamentoDto.setDataInicio("01/01/2020");
		especificacaoGeracaoAgendamentoDto.setHorarioDto(new HorarioDTO("09:00", "10:00"));
		especificacaoGeracaoAgendamentoDto.setReservaStatusDto(new StatusDTO("CASO_NOVO"));

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoAgendamento.obterMensagens(), "Módulo / Atividade está bloqueado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_agendamento_unico_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDTO.setDataInicio("27/07/2099");

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertTrue(resultadoGeracaoAgendamento.sucesso());
		Assert.assertTrue(resultadoGeracaoAgendamento.getObjetosDto().size() == 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_por_bloqueio_de_excesso_de_faltas() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDTO.setDataInicio("27/07/2099");

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				especificacaoGeracaoAgendamentoDTO.getUsuarioDto().getId());

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertEquals(resultadoGeracaoAgendamento.obterMensagens(),
				"O Usuário está bloqueado por excesso de faltas não justificadas.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_unico_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamento.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_unico_por_conta_de_conflito_de_horario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();

		List<ProfissionalDTO> profissionalDTOs = new ArrayList<>();
		profissionalDTOs.add(ContextoProfissional.construirProfissionalDTOAlternativo());
		especificacaoGeracaoAgendamentoDTO.setProfissionaisDto(profissionalDTOs);

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoAgendamento.obterMensagens(),
				"O profissinal já possui agendamentos. VICTOR VITALINO, 31/01/2112, 09:00 às 11:00. VICTOR VITALINO, 31/01/2112, 09:00 às 11:00\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_unico_por_conta_de_conflito_de_data_anterior_atual()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDTO.setDataInicio("01/01/2000");

		List<ProfissionalDTO> profissionalDTOs = new ArrayList<>();
		profissionalDTOs.add(ContextoProfissional.construirProfissionalDTOAlternativo());
		especificacaoGeracaoAgendamentoDTO.setProfissionaisDto(profissionalDTOs);

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoAgendamento.obterMensagens(),
				"Agendamento com data anterior a data atual. \n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_agendamento_multiplo_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoMultiploComTodosOsDados();
		especificacaoGeracaoAgendamentoDTO.setDiaSemanaDto(new DiaSemanaDTO("QUARTA"));
		especificacaoGeracaoAgendamentoDTO.setDataInicio("04/02/2099");
		especificacaoGeracaoAgendamentoDTO.setDataTermino("25/02/2099");

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertTrue(resultadoGeracaoAgendamento.sucesso());
		Assert.assertEquals(resultadoGeracaoAgendamento.getObjetosDto().get(0).getData(), "04/02/2099");
		Assert.assertEquals(resultadoGeracaoAgendamento.getObjetosDto()
				.get(resultadoGeracaoAgendamento.getObjetosDto().size() - 1).getData(), "25/02/2099");
		Assert.assertTrue(resultadoGeracaoAgendamento.getObjetosDto().size() == 8);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_multiplo_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoMultiploComConflito();

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamento.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_multiplo_por_conta_de_conflito_de_horario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoMultiploComConflito();

		servicoSisLaraServerRmi.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamentoComConflito = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoAgendamentoComConflito.sucesso());
		Assert.assertFalse(resultadoGeracaoAgendamentoComConflito.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoAgendamentoComConflito.obterMensagens(),
				"O profissinal já possui agendamentos. PAULO AUGUSTO BANDEIRA DOS SANTOS, 05/01/2112, 09:00 às 11:00. PAULO AUGUSTO BANDEIRA DOS SANTOS, 12/01/2112, 09:00 às 11:00. PAULO AUGUSTO BANDEIRA DOS SANTOS, 19/01/2112, 09:00 às 11:00. PAULO AUGUSTO BANDEIRA DOS SANTOS, 26/01/2112, 09:00 às 11:00\n");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_agendamento_multiplo_filtrando_conflitos_de_feriado_e_ponte()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoMultiplo();

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamentoComConflito = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		Assert.assertTrue(resultadoGeracaoAgendamentoComConflito.sucesso());
		Assert.assertEquals(resultadoGeracaoAgendamentoComConflito.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_copia_agendamento_nao_cancelado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(18888));

		ResultadoEdicaoAgendamentoDTO resultadoGeracaoCopiaAgendamento = servicoSisLaraServerRmi
				.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoCopiaAgendamento.obterMensagens(), "O Agendamento não pode ser copiado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_copia_agendamento_modulo_bloqueado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(3000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(17777));

		ResultadoEdicaoAgendamentoDTO resultadoGeracaoCopiaAgendamento = servicoSisLaraServerRmi
				.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoCopiaAgendamento.obterMensagens(), "Módulo / Atividade está bloqueado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_copia_agendamento_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));

		ResultadoEdicaoAgendamentoDTO resultadoGeracaoCopiaAgendamento = servicoSisLaraServerRmi
				.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_copia_agendamento_por_conta_de_conflito_de_data_e_horario() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();
		especificacaoGeracaoCopiaAgendamentoDTO.setData("31/01/2112");

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Paulo", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));

		ResultadoEdicaoAgendamentoDTO resultadoGeracaoCopiaAgendamento = servicoSisLaraServerRmi
				.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoCopiaAgendamento.obterMensagens(),
				"O profissinal já possui agendamentos. VICTOR VITALINO, 31/01/2112, 09:00 às 11:00. VICTOR VITALINO, 31/01/2112, 09:00 às 11:00\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_copia_agendamento_por_conta_de_data_e_horario_invalido() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();
		especificacaoGeracaoCopiaAgendamentoDTO.setData("99/07/2012");

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Paulo", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));

		ResultadoEdicaoAgendamentoDTO resultadoGeracaoCopiaAgendamento = servicoSisLaraServerRmi
				.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.sucesso());
		Assert.assertFalse(resultadoGeracaoCopiaAgendamento.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoGeracaoCopiaAgendamento.obterMensagens(), "Insira uma Data válida.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_copia_agendamento_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoCopiaAgendamentoDTO especificacaoGeracaoCopiaAgendamentoDTO = ContextoEspecificacaoGeracaoCopiaAgendamento
				.fabricarComTodosOsDados();

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Paulo", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));

		ResultadoEdicaoAgendamentoDTO resultadoGeracaoCopiaAgendamento = servicoSisLaraServerRmi
				.copiarAgendamento(especificacaoGeracaoCopiaAgendamentoDTO, agendamentoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoGeracaoCopiaAgendamento.sucesso());
		Assert.assertNotNull(resultadoGeracaoCopiaAgendamento.obterObjetoDtoEditado());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_liberacao_agendamento_cancelando_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		AgendamentoDTO agendamentoDTO = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));
		agendamentoDTO.setMotivoCancelamentoDTO(new MotivoCancelamentoDTO(new Long(1), "Motivo"));

		ResultadoEdicaoAgendamentoDTO resultadoLiberadoDto = servicoSisLaraServerRmi
				.liberarAgendamentoCancelando(agendamentoDTO, resultadoDto.getToken());

		ResultadoListagemAgendamentoDTO resultadoAposLiberacao = servicoSisLaraServerRmi
				.obterListagemAgendamento(especificacao);

		Assert.assertTrue(resultadoLiberadoDto.sucesso());
		Assert.assertEquals(resultadoAposLiberacao.getObjetosDto().size(), 5);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_liberacao_agendamento_cancelando_sem_motivo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		AgendamentoDTO agendamentoDTO = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(15555));

		ResultadoEdicaoAgendamentoDTO resultadoLiberadoDto = servicoSisLaraServerRmi
				.liberarAgendamentoCancelando(agendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoLiberadoDto.sucesso());
		Assert.assertEquals(resultadoLiberadoDto.obterMensagens(),
				"O Agendamento não pode ser liberado por ausência de motivo.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_liberacao_agendamento_cancelando_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		AgendamentoDTO agendamentoDTO = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(15555));

		ResultadoEdicaoAgendamentoDTO resultadoLiberadoDto = servicoSisLaraServerRmi
				.liberarAgendamentoCancelando(agendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoLiberadoDto.sucesso());
		Assert.assertEquals(resultadoLiberadoDto.obterMensagens(),
				"Você não possui autorização para realizar a operação.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_liberacao_agendamento_cancelando_com_modulo_bloqueado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(3000), "Josep", "1234"));

		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		// Id 17777
		AgendamentoDTO agendamentoDTO = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(17777));
		agendamentoDTO.setMotivoCancelamentoDTO(new MotivoCancelamentoDTO(new Long(1), "Motivo"));

		ResultadoEdicaoAgendamentoDTO resultadoLiberadoDto = servicoSisLaraServerRmi
				.liberarAgendamentoCancelando(agendamentoDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoLiberadoDto.sucesso());
		Assert.assertEquals(resultadoLiberadoDto.obterMensagens(), "Módulo / Atividade está bloqueado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_agendamento_com_especificacao() throws RemoteException {
		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_agendamento_disponiveis_com_especificacao() throws RemoteException {

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(new Long(22222));
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDto);
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(21), "G0"));
		descricaoTipoAtendimentoDTO.setNomesGruposDto(nomesGruposDto);
		ModuloDTO moduloDto = new ModuloDTO(new Long(2), "Info");

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamento.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		especificacaoPesquisaAgendamento.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaAgendamento.setModuloDTO(moduloDto);
		ResultadoListagemAgendamentoDTO resultadoAgendamento = servicoSisLaraServerRmi
				.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamento);

		Assert.assertTrue(resultadoAgendamento.sucesso());
		Assert.assertFalse(resultadoAgendamento.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_obtem_listagem_agendamento_com_especificacao_invalida() throws RemoteException {
		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setDataInicio("erro");
		especificacao.setDataTermino("erro");
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		Assert.assertFalse(resultado.sucesso());
		Assert.assertFalse(resultado.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_cancelamento_agendamento_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));

		agendamentoDto.setMotivoCancelamentoDTO(new MotivoCancelamentoDTO(new Long(1), "Nao Justificou"));
		agendamentoDto.setJustificativaCancelamento("Grande texto de justificativa");

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.cancelarAgendamento(agendamentoDto, resultadoDto.getToken());

		ResultadoListagemAgendamentoDTO resultadoAposCancelamento = servicoSisLaraServerRmi
				.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDtoAposCancelamento = (AgendamentoDTO) obterDaRelacaoPorId(
				resultadoAposCancelamento.getObjetosDto(), new Long(12222));

		Assert.assertTrue(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertTrue(agendamentoDtoAposCancelamento.isEstaCancelado());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_agendamento_por_falta_motivo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.cancelarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAgendamentoDto.obterMensagens(), "Insira um motivo de cancelamento.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_agendamento_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));

		agendamentoDto.setMotivoCancelamentoDTO(new MotivoCancelamentoDTO(new Long(1), "Nao Justificou"));

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoEdicaoAgendamentoDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_com_usuario_e_pre_cadastro_atribuidos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));
		agendamentoDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());
		agendamentoDto.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoEdicaoAgendamentoDto.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoEdicaoAgendamentoDto.obterMensagens(),
				"É possível associar somente Oftalmologia ou Servico Social.\nInserir somente um Usuário ou um Pré-cadastro.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_agendamento_com_associacao_pre_cadastro_restrito() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));
		agendamentoDto.setUsuarioDto(null);
		agendamentoDto.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoEdicaoAgendamentoDto.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoEdicaoAgendamentoDto.obterMensagens(),
				"É possível associar somente Oftalmologia ou Servico Social.\nInserir somente um Usuário ou um Pré-cadastro.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_motivo_cancelamento_agendamento() throws RemoteException {
		ResultadoListagemMotivoCancelamentoDTO resultadoListagemMotivoCancelamentoAgendamentoDto = servicoSisLaraServerRmi
				.obterListagemMotivoCancelamentoAgendamento();

		Assert.assertTrue(resultadoListagemMotivoCancelamentoAgendamentoDto.sucesso());
		Assert.assertEquals(resultadoListagemMotivoCancelamentoAgendamentoDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_motivo_cancelamento_demanda() throws RemoteException {
		ResultadoListagemMotivoCancelamentoDTO resultadoListagemMotivoDemandaDto = servicoSisLaraServerRmi
				.obterListagemMotivoCancelamentoDemanda();

		Assert.assertTrue(resultadoListagemMotivoDemandaDto.sucesso());
		Assert.assertEquals(resultadoListagemMotivoDemandaDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_de_agendamento() throws RemoteException {
		ResultadoListagemStatusAgendamentoDTO resultadoListagemStatusAgendamentoDto = servicoSisLaraServerRmi
				.obterListagemStatusAgendamento();

		Assert.assertTrue(resultadoListagemStatusAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusAgendamentoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_entrega_de_solicitacao_relatorio() throws RemoteException {
		ResultadoListagemStatusSolicitacaoRelatorioDTO resultadoListagemStatusAgendamentoDto = servicoSisLaraServerRmi
				.obterListagemStatusEntrega();

		Assert.assertTrue(resultadoListagemStatusAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusAgendamentoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_de_solicitacao_relatorio() throws RemoteException {
		ResultadoListagemStatusSolicitacaoRelatorioDTO resultadoListagemStatusAgendamentoDto = servicoSisLaraServerRmi
				.obterListagemStatusSolicitacaoRelatorio();

		Assert.assertTrue(resultadoListagemStatusAgendamentoDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusAgendamentoDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_finalidades_relatorio() throws RemoteException {
		ResultadoListagemFinalidadeRelatorioDTO resultadoListagemFinalidadesRelatorioDto = servicoSisLaraServerRmi
				.obterListagemFinalidadeRelatorio();

		Assert.assertTrue(resultadoListagemFinalidadesRelatorioDto.sucesso());
		Assert.assertFalse(resultadoListagemFinalidadesRelatorioDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_elaborador_relatorio() throws RemoteException {
		ResultadoListagemElaboradorDTO resultadoListagemElaboradorDto = servicoSisLaraServerRmi
				.obterListagemElaborador();

		Assert.assertTrue(resultadoListagemElaboradorDto.sucesso());
		Assert.assertFalse(resultadoListagemElaboradorDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_de_espera() throws RemoteException {
		ResultadoListagemStatusEsperaDTO resultadoListagemStatusEsperaDto = servicoSisLaraServerRmi
				.obterListagemStatusEspera();

		Assert.assertTrue(resultadoListagemStatusEsperaDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusEsperaDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_tipo_de_espera() throws RemoteException {
		ResultadoListagemTipoEsperaDTO resultadoListagemTipoEsperaDto = servicoSisLaraServerRmi
				.obterListagemTipoEsperaTotal();

		Assert.assertTrue(resultadoListagemTipoEsperaDto.sucesso());
		Assert.assertFalse(resultadoListagemTipoEsperaDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_espera_com_especificacao() throws RemoteException {
		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setTipoEsperaDto(new TipoEsperaDTO(TipoEspera.RET.toString()));
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);
		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_obtem_listagem_espera_com_especificacao_invalida() throws RemoteException {
		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		Assert.assertFalse(resultado.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_observacoes_historicas_de_espera_com_informacao_essencial() throws RemoteException {

		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setId(new Long(44444));
		String resultado = servicoSisLaraServerRmi.obterObservacoesHistoricasEspera(informacaoEssencialDto);

		String esperadoA = "Descrição: Atendimento de Teste\nMódulo: Atendimento Especifico Especializado\nExpectativa: 01/01/2012\nobs 1\nSolicitada por: VICTOR VITALINO\n----";
		String esperadoB = "\nDescrição: Atendimento de Teste\nMódulo: Acompanhamento\nExpectativa: 02/01/2012\nobs 2\nSolicitada por: VICTOR VITALINO\n----";
		String esperadoC = "\nDescrição: Atendimento de Teste\nMódulo: Inglês\nExpectativa: 02/01/2012\nobs 2\nSolicitada por: VICTOR VITALINO\n----";

		Assert.assertTrue(resultado.contains(esperadoA));
		Assert.assertTrue(resultado.contains(esperadoB));
		Assert.assertTrue(resultado.contains(esperadoC));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_impossibilidade_modulo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.getPreCadastroDto().setId(new Long(12222));
		esperaDto.getDescricaoTipoAtendimentoDto().setId(new Long(12222));
		esperaDto.setModuloDto(new ModuloDTO(new Long(3), "Modulo Bloqueado"));

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoEspera.sucesso());
		Assert.assertEquals(resultadoInclusaoEspera.obterMensagens(), "Módulo/Atividade não permite a inclusão.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_espera() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		EsperaDTO esperaDTOIncluida = (EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertTrue(esperaDTOIncluida.estaAguardando());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_pendencia_atendimento_individual_apos_associacao_espera_ao_agendamento_com_sucesso()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		criar_espera_e_agendamento_e_efetuar_associacao_entre_os_dois(resultadoDto);

		ResultadoListagemPendenciaDTO listagemPendencia = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(listagemPendencia.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_atendimento_grupo_apos_criacao_atendimento_de_grupo_com_sucesso()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemPendenciaDTO listagemPendenciaAntesAtualizacao = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));

		servicoSisLaraServerRmi.gerarAtendimentos(new EspecificacaoGeracaoAtendimentosDTO(grupoDto.getId(),
				obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(), new Long(11111)).getId(), "01/01/2015", "09:00",
				"19:00"), resultadoDto.getToken());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO listagemPendenciaAposAtualizacao = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(listagemPendenciaAntesAtualizacao.getObjetosDto().size(), 2);
		Assert.assertEquals(listagemPendenciaAposAtualizacao.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_atendimento_individual_com_usapos_criacao_atendimento_individual_com_sucesso()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		criar_espera_e_agendamento_e_efetuar_associacao_entre_os_dois(resultadoDto);

		ResultadoListagemPendenciaDTO listagemPendencia = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		// Pendencia com prontuário 12222
		PendenciaDTO pendencia = listagemPendencia.getObjetosDto().get(2);

		servicoSisLaraServerRmi.editarAtendimentoIndividual(
				pendencia.getAtendimentoIndividualDTOReferenciaParaGeracao(), resultadoDto.getToken());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO listagemPendenciaAposResolucao = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(listagemPendencia.getObjetosDto().size(), 3);
		Assert.assertEquals(listagemPendenciaAposResolucao.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_atendimento_individual_apos_cancelamento_agendamento_com_sucesso()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		criar_espera_e_agendamento_e_efetuar_associacao_entre_os_dois(resultadoDto);

		ResultadoListagemPendenciaDTO listagemPendenciaAntesResolucao = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDTO = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamentoDTO.setDataInicio(DataHoraUtils.formatarData(Calendar.getInstance()));
		ResultadoListagemAgendamentoDTO agendamentosDto = servicoSisLaraServerRmi
				.obterListagemAgendamento(especificacaoPesquisaAgendamentoDTO);
		AgendamentoDTO agendamentoDTO = agendamentosDto.getObjetosDto().get(0);
		agendamentoDTO.setMotivoCancelamentoDTO(new MotivoCancelamentoDTO(new Long(1), "Nao explicou"));
		servicoSisLaraServerRmi.cancelarAgendamento(agendamentoDTO, resultadoDto.getToken());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO listagemPendenciaAposResolucao = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(listagemPendenciaAntesResolucao.getObjetosDto().size(), 3);
		Assert.assertEquals(listagemPendenciaAposResolucao.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_altera_contribuinte_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaContribuinteDTO especificacao = new EspecificacaoPesquisaContribuinteDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.NOME_CONTRIBUINTE, "ARAKEN");
		ResultadoListagemContribuinteDTO resultadoListagemContribuintesDto = servicoSisLaraServerRmi
				.pesquisarContribuintePor(especificacao);

		ContribuinteDTO contribuinteIncluidoDto = (ContribuinteDTO) obterDaRelacaoPorId(
				resultadoListagemContribuintesDto.getObjetosDto(), new Long(88888));
		contribuinteIncluidoDto.setNomeEmpresa("TESTE");
		contribuinteIncluidoDto.getEnderecoDto().setNumero("123");
		contribuinteIncluidoDto.setStatusContribuinteDto(new StatusContribuinteDTO("DESATIVADO"));

		ResultadoEdicaoContribuinteDTO resultadoInclusaoContribuinte = servicoSisLaraServerRmi
				.editarContribuinte(contribuinteIncluidoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoContribuinte.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_contribuinte() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ContribuinteDTO contribuinteDto = ContextoContribuinte.fabricarContribuinteDtoComTodosOsDados();
		contribuinteDto.setId(null);

		ResultadoEdicaoContribuinteDTO resultadoInclusaoContribuinte = servicoSisLaraServerRmi
				.editarContribuinte(contribuinteDto, resultadoDto.getToken());

		ContribuinteDTO contribuinteIncluidoDto = (ContribuinteDTO) resultadoInclusaoContribuinte
				.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoInclusaoContribuinte.sucesso());
		Assert.assertNotNull(contribuinteIncluidoDto.getId());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_contribuinte_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ContribuinteDTO contribuinteDto = ContextoContribuinte.fabricarContribuinteDtoComTodosOsDados();

		ResultadoEdicaoContribuinteDTO resultadoInclusaoContribuinte = servicoSisLaraServerRmi
				.editarContribuinte(contribuinteDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoContribuinte.sucesso());
		Assert.assertEquals(resultadoInclusaoContribuinte.obterMensagens(),
				"Você não possui autorização para realizar a operação.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_espera_triagem_oftalmologica() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		EsperaDTO esperaDTOIncluida = (EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertTrue(esperaDTOIncluida.estaTriando());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_triagem_oftalmologica_duplicada() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaDuplicada = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertFalse(resultadoInclusaoEsperaDuplicada.sucesso());
		Assert.assertEquals(resultadoInclusaoEsperaDuplicada.obterMensagens(),
				"O Usuário não pode estar na espera para Triagem e Avaliação Oftalmológica simultaneamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_triagem_oftalmologica_duplicada_aguardando() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		ResultadoEdicaoEsperaDTO resultadoConclusaoEspera = servicoSisLaraServerRmi
				.concluirEspera((EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado(), resultadoDto.getToken());

		EsperaDTO esperaDTOAguardando = (EsperaDTO) resultadoConclusaoEspera.obterObjetoDtoEditado();
		esperaDTOAguardando.setId(null);
		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaDuplicada = servicoSisLaraServerRmi
				.editarEspera(esperaDTOAguardando, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertFalse(resultadoInclusaoEsperaDuplicada.sucesso());
		Assert.assertEquals(resultadoInclusaoEsperaDuplicada.obterMensagens(),
				"O Usuário ou Pré-cadastro já está incluído na lista de espera do tipo selecionado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_triagem_oftalmologica_duplicada_triando() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		EsperaDTO esperaDTOTriando = (EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado();
		esperaDTOTriando.setId(null);
		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaDuplicada = servicoSisLaraServerRmi
				.editarEspera(esperaDTOTriando, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertFalse(resultadoInclusaoEsperaDuplicada.sucesso());
		Assert.assertEquals(resultadoInclusaoEsperaDuplicada.obterMensagens(),
				"O Usuário não pode estar na espera para Triagem e Avaliação Oftalmológica simultaneamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_triagem_oftalmologica_simultanea_e_atendimento_especifico_especializado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaTriagemOftalmologica = servicoSisLaraServerRmi
				.editarEspera(esperaDto, resultadoDto.getToken());

		esperaDto.setModuloDto(ContextoModulo.construirModuloDTO());
		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaAtendimentoEspecificoEspecializado = servicoSisLaraServerRmi
				.editarEspera(esperaDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoEsperaTriagemOftalmologica.sucesso());
		Assert.assertFalse(resultadoInclusaoEsperaAtendimentoEspecificoEspecializado.sucesso());
		Assert.assertEquals(resultadoInclusaoEsperaAtendimentoEspecificoEspecializado.obterMensagens(),
				"O Usuário não pode estar na espera para Triagem e Avaliação Oftalmológica simultaneamente.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_nova_espera_outra_descricao_mesmo_com_triagem_oftalmologica_existente()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaTriagemOftalmologica = servicoSisLaraServerRmi
				.editarEspera(esperaDto, resultadoDto.getToken());

		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO());
		esperaDto.setModuloDto(ContextoModulo.construirModuloInglesDTO());
		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaIngles = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoEsperaTriagemOftalmologica.sucesso());
		Assert.assertTrue(resultadoInclusaoEsperaIngles.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_por_conta_de_data_invalida() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDataExpectativa("928374SS");

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoEspera.sucesso());
		Assert.assertEquals(resultadoInclusaoEspera.obterMensagens(), "Insira a Data de Expectativa válida.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_por_conta_de_duplicacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());
		servicoSisLaraServerRmi.editarEspera(esperaDto, resultadoDto.getToken());

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDTO = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDTO.setDescricaoTipoAtendimentoDto(esperaDto.getDescricaoTipoAtendimentoDto());
		especificacaoPesquisaEsperaDTO.setModuloDto(esperaDto.getModuloDto());
		especificacaoPesquisaEsperaDTO.setInteresse(false);
		especificacaoPesquisaEsperaDTO.setLmLigou(false);
		especificacaoPesquisaEsperaDTO.setPendencias(false);

		ResultadoListagemEsperaDTO resultadoListagemEsperaDtoIncluida = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDTO);
		EsperaDTO esperaDtoIncluida = resultadoListagemEsperaDtoIncluida.getObjetosDto().get(0);
		esperaDtoIncluida.setId(null);

		ResultadoEdicaoEsperaDTO resultadoInclusaoEsperaDuplicado = servicoSisLaraServerRmi
				.editarEspera(esperaDtoIncluida, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoEsperaDuplicado.sucesso());
		Assert.assertEquals(resultadoInclusaoEsperaDuplicado.obterMensagens(),
				"O Usuário ou Pré-cadastro já está incluído na lista de espera do tipo selecionado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_cancela_espera() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setTipoEsperaDto(new TipoEsperaDTO(TipoEspera.RET.toString()));
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		EsperaDTO espera = (EsperaDTO) obterDaRelacaoPorId(resultado.getObjetosDto(), new Long(13333));
		espera.setJustificativaCancelamento("Justificativa do cancelamento.");
		ResultadoEdicaoEsperaDTO resultadoCancelamentoEspera = servicoSisLaraServerRmi.cancelarEspera(espera,
				resultadoDto.getToken());

		Assert.assertTrue(resultadoCancelamentoEspera.sucesso());
		Assert.assertTrue(((EsperaDTO) resultadoCancelamentoEspera.obterObjetoDtoEditado()).estaCancelado());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_conclui_espera() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setTipoEsperaDto(new TipoEsperaDTO(TipoEspera.RET.toString()));
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		EsperaDTO espera = (EsperaDTO) obterDaRelacaoPorId(resultado.getObjetosDto(), new Long(13333));

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.concluirEspera(espera,
				resultadoDto.getToken());

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertEquals(((EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado()).getStatusDto().toString(),
				new StatusDTO(StatusEspera.AGENDADO.toString()).toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_conclui_espera_em_triagem() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		EsperaDTO esperaDTOIncluida = (EsperaDTO) servicoSisLaraServerRmi
				.editarEspera(esperaDto, resultadoDto.getToken()).obterObjetoDtoEditado();

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.concluirEspera(esperaDTOIncluida,
				resultadoDto.getToken());

		EsperaDTO esperaDtoAgendada = (EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoInclusaoEspera.sucesso());
		Assert.assertTrue(esperaDtoAgendada.estaAguardando());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_conclui_espera_em_triagem_por_falta_de_autorizacao() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		esperaDto.setModuloDto(ContextoModulo.construirModuloDTOTriagemOftalmologia());
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacao());

		EsperaDTO esperaDTOIncluida = (EsperaDTO) servicoSisLaraServerRmi
				.editarEspera(esperaDto, resultadoDto.getToken()).obterObjetoDtoEditado();

		ResultadoEdicaoEsperaDTO resultadoConlusaoEspera = servicoSisLaraServerRmi.concluirEspera(esperaDTOIncluida,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoConlusaoEspera.sucesso());
		Assert.assertEquals(resultadoConlusaoEspera.obterMensagens(),
				"Somente a oftalmologia tem autorização para concluir a triagem.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_agendamento_atraves_de_espera() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(new Long(22222));
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDto);
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(21), "G0"));
		descricaoTipoAtendimentoDTO.setNomesGruposDto(nomesGruposDto);
		ModuloDTO moduloDto = new ModuloDTO(new Long(2), "Info");

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamento.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		especificacaoPesquisaAgendamento.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaAgendamento.setModuloDTO(moduloDto);
		ResultadoListagemAgendamentoDTO resultadoAgendamento = servicoSisLaraServerRmi
				.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamento);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultadoAgendamento.getObjetosDto(),
				new Long(15555));
		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaEsperaDto.setModuloDto(moduloDto);
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);
		ResultadoListagemEsperaDTO resultadoListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaDto = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaDto.getObjetosDto(),
				new Long(13333));

		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacao.setAgendamentoDto(agendamentoDto);
		especificacao.setEsperaDto(esperaDto);

		ResultadoEdicaoEsperaDTO resultado = servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacao,
				resultadoDto.getToken());

		EsperaDTO espera = (EsperaDTO) resultado.obterObjetoDtoEditado();

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoAposAssociacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamentoAposAssociacao
				.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		especificacaoPesquisaAgendamentoAposAssociacao.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaAgendamentoAposAssociacao.setModuloDTO(moduloDto);
		ResultadoListagemAgendamentoDTO resultadoAgendamentoAposAssociacao = servicoSisLaraServerRmi
				.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamento);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertEquals(espera.getStatusDto().toString(), StatusEspera.AGENDADO.toString());
		Assert.assertTrue(resultadoAgendamentoAposAssociacao.getObjetosDto().isEmpty());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_associa_agendamento_atraves_de_espera_com_usuario_de_setor_incompativel() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(new Long(22222));
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDto);
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(21), "G0"));
		descricaoTipoAtendimentoDTO.setNomesGruposDto(nomesGruposDto);
		ModuloDTO moduloDto = new ModuloDTO(new Long(2), "Info");

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamento.setDataInicio("01/01/0001");
		especificacaoPesquisaAgendamento.setDataTermino("01/01/2999");
		ResultadoListagemAgendamentoDTO resultadoAgendamento = servicoSisLaraServerRmi
				.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamento);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultadoAgendamento.getObjetosDto(),
				new Long(16666));
		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaEsperaDto.setModuloDto(moduloDto);
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);
		ResultadoListagemEsperaDTO resultadoListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaDto = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaDto.getObjetosDto(),
				new Long(13333));

		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacao.setAgendamentoDto(agendamentoDto);
		especificacao.setEsperaDto(esperaDto);

		ResultadoEdicaoEsperaDTO resultado = servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacao,
				resultadoDto.getToken());

		Assert.assertFalse(resultado.sucesso());
		Assert.assertTrue(resultado.obterMensagens().contains("Não é possível agendar um usuário de setor incompatível com o solicitado pelo agendamento."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_de_atendimento_invidual_de_servico_social_com_pre_cadastro_apos_geracao_de_atendimento_com_pre_cadastro_contendo_prontuario_associado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemPendenciaDTO pendenciasAntesResolucaoDto = servico_resolve_pendencia_de_atendimento_invidual_de_servico_social_com_pre_cadastro_apos_geracao_de_atendimento_com_pre_cadastro(
				resultadoDto, ContextoPreCadastro.construirPreCadastroDTO());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO pendenciasAposResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(pendenciasAntesResolucaoDto.getObjetosDto().size(), 3);
		Assert.assertEquals(pendenciasAposResolucaoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_resolve_pendencia_de_atendimento_invidual_de_servico_social_com_pre_cadastro_apos_geracao_de_atendimento_com_pre_cadastro_sem_conter_prontuario_associado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemPendenciaDTO pendenciasAntesResolucaoDto = servico_resolve_pendencia_de_atendimento_invidual_de_servico_social_com_pre_cadastro_apos_geracao_de_atendimento_com_pre_cadastro(
				resultadoDto, ContextoPreCadastro.construirPreCadastroDTOAlternativo());

		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ResultadoListagemPendenciaDTO pendenciasAposResolucaoDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertEquals(pendenciasAntesResolucaoDto.getObjetosDto().size(), 3);
		Assert.assertEquals(pendenciasAposResolucaoDto.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_agendamento_atraves_de_espera_e_inclui_pendencia_de_atendimento_individual_com_pre_cadastro()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		esperaDto.setUsuarioDto(null);

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		EsperaDTO esperaDTOIncluida = (EsperaDTO) resultadoInclusaoEspera.obterObjetoDtoEditado();

		EspecificacaoGeracaoAgendamentoDTO especificacaoGeracaoAgendamentoDTO = ContextoEspecificacaoGeracaoAgendamento
				.fabricarAgendamentoUnicoComTodosOsDados();
		especificacaoGeracaoAgendamentoDTO
				.setDescricaoTipoAtendimentoDto(esperaDTOIncluida.getDescricaoTipoAtendimentoDto());
		especificacaoGeracaoAgendamentoDTO.setModuloDto(esperaDTOIncluida.getModuloDto());
		especificacaoGeracaoAgendamentoDTO.setDataInicio(DataHoraUtils.formatarData(Calendar.getInstance()));
		especificacaoGeracaoAgendamentoDTO.setUsuarioDto(null);

		ResultadoGeracaoAgendamentoDTO resultadoGeracaoAgendamento = servicoSisLaraServerRmi
				.gerarAgendamento(especificacaoGeracaoAgendamentoDTO, resultadoDto.getToken());

		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacao.setAgendamentoDto(resultadoGeracaoAgendamento.getObjetosDto().get(0));
		especificacao.setEsperaDto(esperaDTOIncluida);

		ResultadoEdicaoEsperaDTO resultado = servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacao,
				resultadoDto.getToken());

		ResultadoListagemPendenciaDTO pendenciasDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertTrue(resultado.sucesso());
		Assert.assertEquals(pendenciasDto.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_agendamento_servico_social_e_inclui_pendencia_de_atendimento_individual_com_pre_cadastro()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);

		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(18888));
		agendamentoDto.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTO());
		agendamentoDto.setData(DataHoraUtils.formatarData(Calendar.getInstance()));

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		ResultadoListagemPendenciaDTO pendenciasDto = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertEquals(pendenciasDto.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_agendamento_servico_social_avaliacao_e_triagem_atraves_de_espera_com_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDTO = ContextoUsuario.construirUsuarioDTOComIdentificacao();

		EspecificacaoPesquisaAgendamentoDTO especificacaoAgendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoAgendamento.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi
				.obterListagemAgendamento(especificacaoAgendamento);
		// Ref. id 18888
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(18888));
		agendamentoDto.setUsuarioDto(usuarioDTO);

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				usuarioDTO.getId());

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setProntuario(usuarioDTO.getId().toString());
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);
		ResultadoListagemEsperaDTO resultadoListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaDto = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaDto.getObjetosDto(),
				new Long(13333));

		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacao.setAgendamentoDto(agendamentoDto);
		especificacao.setEsperaDto(esperaDto);

		ResultadoEdicaoEsperaDTO resultadoEdicaoEspera = servicoSisLaraServerRmi
				.editarEsperaEAssociarAgendamento(especificacao, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoEspera.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_associa_agendamento_a_espera_ja_agendada() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(new Long(22222));
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDto);
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(21), "G0"));
		descricaoTipoAtendimentoDTO.setNomesGruposDto(nomesGruposDto);
		ModuloDTO moduloDto = new ModuloDTO(new Long(2), "Info");

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamento.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		especificacaoPesquisaAgendamento.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaAgendamento.setModuloDTO(moduloDto);
		ResultadoListagemAgendamentoDTO resultadoAgendamento = servicoSisLaraServerRmi
				.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamento);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultadoAgendamento.getObjetosDto(),
				new Long(15555));

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaEsperaDto.setModuloDto(moduloDto);
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);

		ResultadoListagemEsperaDTO resultadoListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaDto = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaDto.getObjetosDto(),
				new Long(13333));

		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacao.setAgendamentoDto(agendamentoDto);
		especificacao.setEsperaDto(esperaDto);

		servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacao, resultadoDto.getToken());

		ResultadoEdicaoEsperaDTO resultadoComErro = servicoSisLaraServerRmi
				.editarEsperaEAssociarAgendamento(especificacao, resultadoDto.getToken());
		Assert.assertFalse(resultadoComErro.sucesso());
		Assert.assertEquals(resultadoComErro.obterMensagens(),
				"A Espera já foi agendada ou o Módulo / Atividade está bloqueado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_associa_agendamento_a_espera_com_usuario_bloqueado_por_excesso_de_falta()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(new Long(22222));
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(12222));
		descricaoTipoAtendimentoDTO.setTipoAtendimentoDto(tipoAtendimentoDto);
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(21), "G0"));
		descricaoTipoAtendimentoDTO.setNomesGruposDto(nomesGruposDto);
		ModuloDTO moduloDto = new ModuloDTO(new Long(2), "Info");

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamento.setProfissionalDto(new ProfissionalDTO(new Long(2000), "Josep", "1234"));
		especificacaoPesquisaAgendamento.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDTO);

		especificacaoPesquisaAgendamento.setModuloDTO(moduloDto);
		ResultadoListagemAgendamentoDTO resultadoAgendamento = servicoSisLaraServerRmi
				.obterListagemAgendamentoDisponiveis(especificacaoPesquisaAgendamento);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultadoAgendamento.getObjetosDto(),
				new Long(15555));

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setId(null);
		atendimentoIndividualDto.setUsuarioDto(agendamentoDto.getUsuarioDto());
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));
		for (int i = 1; i <= 3; i++) {
			atendimentoIndividualDto.setData("0" + i + "/03/2015");
			servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());
		}

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		especificacaoPesquisaEsperaDto.setModuloDto(moduloDto);
		especificacaoPesquisaEsperaDto.setInteresse(false);
		especificacaoPesquisaEsperaDto.setLmLigou(false);
		especificacaoPesquisaEsperaDto.setPendencias(false);

		ResultadoListagemEsperaDTO resultadoListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);
		EsperaDTO esperaDto = (EsperaDTO) obterDaRelacaoPorId(resultadoListagemEsperaDto.getObjetosDto(),
				new Long(13333));

		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacao.setAgendamentoDto(agendamentoDto);
		especificacao.setEsperaDto(esperaDto);

		ResultadoEdicaoEsperaDTO resultadoComErro = servicoSisLaraServerRmi
				.editarEsperaEAssociarAgendamento(especificacao, resultadoDto.getToken());
		Assert.assertFalse(resultadoComErro.sucesso());
		Assert.assertEquals(resultadoComErro.obterMensagens(),
				"O Usuário está bloqueado por excesso de faltas não justificadas.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_associa_agendamento_atraves_de_espera_sem_permissao() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());
		EspecificacaoAssociacaoAgendamentoDTO especificacao = new EspecificacaoAssociacaoAgendamentoDTO();

		ResultadoEdicaoEsperaDTO resultado = servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacao,
				resultadoDto.getToken());

		Assert.assertFalse(resultado.sucesso());
		Assert.assertFalse(resultado.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_por_falta_de_justificativa_invalida() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setTipoEsperaDto(new TipoEsperaDTO(TipoEspera.RET.toString()));
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi.obterListagemEspera(especificacao);

		EsperaDTO espera = (EsperaDTO) obterDaRelacaoPorId(resultado.getObjetosDto(), new Long(13333));
		espera.setJustificativaCancelamento(ContextoGenerico.obterGrande());

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.cancelarEspera(espera,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoEspera.sucesso());
		Assert.assertEquals(resultadoInclusaoEspera.obterMensagens(),
				"Insira uma Justificativa de Cancelamento contendo até 20000 caracteres.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_atendimento_individual_de_pre_cadastro_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		String conteudoArquivo = "Conteúdo do arquivo.";

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO("PRIMEIRA_VEZ"));
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));
		ArquivoDTO arquivoDTO = new ArquivoDTO(null, "Teste.pdf", conteudoArquivo.getBytes(), null);
		atendimentoIndividualDto.setArquivos(Arrays.asList(arquivoDTO));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		AtendimentoIndividualDTO atendimentoIndividualSalvoDTO = (AtendimentoIndividualDTO) resultadoEdicaoAtendimentoIndividualDto
				.obterObjetoDtoEditado();
		ArquivoDTO arquivoDtoSalvo = servicoSisLaraServerRmi.obterArquivoAtendimentoIndividualDTO(
				atendimentoIndividualSalvoDTO, atendimentoIndividualSalvoDTO.getArquivos().get(0));
		ArquivoDTO arquivoDisponivelDto = servicoSisLaraServerRmi.obterArquivoDisponivelDTO(
				atendimentoIndividualSalvoDTO.getId().toString(), "Teste.pdf",
				TipoArquivoDisponivel.INDIVIDUAL.toString());
		ResultadoListagemArquivoDisponivelDTO resultadoListagemArquivoDisponivelDto = servicoSisLaraServerRmi
				.obterListagemArquivoDisponivelDTO(atendimentoIndividualSalvoDTO.getUsuarioDto().getId().toString(),
						false);

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(arquivoDtoSalvo.obterConteudo(), arquivoDTO.obterConteudo());
		Assert.assertEquals(arquivoDisponivelDto.obterConteudo(), arquivoDTO.obterConteudo());
		Assert.assertEquals(resultadoListagemArquivoDisponivelDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_atendimento_individual_de_usuario_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_status_do_usuario_menor_de_21_anos_desligado_ha_mais_de_um_ano_para_retorno_apos_atendimento_de_servico_social_de_retorno()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Long prontuario = new Long(17777);
		EspecificacaoPesquisaUsuarioDTO especificacaoPesquisaUsuarioDTO = new EspecificacaoPesquisaUsuarioDTO();
		especificacaoPesquisaUsuarioDTO.adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario.toString());
		ResultadoListagemUsuarioDTO resultadoListagemUsuarioDTO = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO);
		UsuarioDTO usuarioDtoAntesAtualizacaoStatus = (UsuarioDTO) obterDaRelacaoPorId(resultadoListagemUsuarioDTO.getObjetosDto(),
				prontuario);

		String data = "27/07/1982";
		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));
		atendimentoIndividualDto.setUsuarioDto(usuarioDtoAntesAtualizacaoStatus);
		atendimentoIndividualDto.setData(data);
		atendimentoIndividualDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAvaliacaoETriagemDTO());

		MaquinaTempo.mudarDataAtual(data);
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		ResultadoListagemUsuarioDTO resultadoListagemUsuarioDTOAposAtualizacaoStatus = servicoSisLaraServerRmi
				.pesquisarUsuarioPor(especificacaoPesquisaUsuarioDTO);
		UsuarioDTO usuarioDtoAposAtualizacaoStatus = (UsuarioDTO) obterDaRelacaoPorId(
				resultadoListagemUsuarioDTOAposAtualizacaoStatus.getObjetosDto(), prontuario);

		Assert.assertEquals(usuarioDtoAntesAtualizacaoStatus.getStatusUsuarioAtualDto().toString(),
				new StatusDTO("DESLIGADO").toString());
		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(usuarioDtoAposAtualizacaoStatus.getStatusUsuarioAtualDto().toString(),
				new StatusDTO("RETORNO").toString());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_atendimento_individual_de_oftalmologia_pela_primeira_vez_e_incluir_automaticamente_em_espera_de_avaliacao_funcional()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoB());
		atendimentoIndividualDto.setData("01/01/2000");
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO("PRIMEIRA_VEZ"));
		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAEE());

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualPrimeiraVezDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());
		
		atendimentoIndividualDto.setData("01/02/2000");
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO("RETORNO"));
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualRetornoDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(atendimentoIndividualDto.getUsuarioDto().getId().toString());
		especificacao.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		especificacao.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoAvaliacaoFuncionalDTO());
		especificacao.setTipoEsperaDto(new TipoEsperaDTO("CN"));
		especificacao.setModuloDto(ContextoModulo.construirModuloAEE());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO listagemEsperaAvaliacaoFuncional = servicoSisLaraServerRmi
				.obterListagemEspera(especificacao);

		EsperaDTO esperaIncluidaAutomaticamenteEmAvaliacaoFuncional = listagemEsperaAvaliacaoFuncional.getObjetosDto()
				.get(0);

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualPrimeiraVezDto.sucesso());
		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualRetornoDto.sucesso());
		Assert.assertEquals(listagemEsperaAvaliacaoFuncional.getObjetosDto().size(), 1);
		Assert.assertTrue(esperaIncluidaAutomaticamenteEmAvaliacaoFuncional.getObs()
				.contains("Incluído automaticamente em consequência ao primeiro atendimento em oftalmologia."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_de_oftalmologia_de_retorno_automaticamente_em_espera_de_avaliacao_funcional()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setUsuarioDto(ContextoUsuario.construirUsuarioDTOComIdentificacaoAlternativoB());
		atendimentoIndividualDto.setData("01/01/2000");
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO("RETORNO"));
		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAEE());

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(atendimentoIndividualDto.getUsuarioDto().getId().toString());
		especificacao.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		especificacao.setDescricaoTipoAtendimentoDto(
				ContextoDescricaoTipoAtendimento.construirDescricaoAvaliacaoFuncionalDTO());
		especificacao.setModuloDto(ContextoModulo.construirModuloAEE());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);

		ResultadoListagemEsperaDTO listagemEsperaAvaliacaoFuncional = servicoSisLaraServerRmi
				.obterListagemEspera(especificacao);

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(listagemEsperaAvaliacaoFuncional.getObjetosDto().size(), 0);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_atendimento_individual_com_sucesso_e_envia_lista_de_espera_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Long prontuario = new Long(13333);

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto, prontuario);

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = ContextoEspecificacaoPesquisaEspera
				.fabricarDtoPesquisaEsperaDescricaoServicoSocialModuloExcessoDeFaltas(prontuario);

		ResultadoListagemEsperaDTO resultado = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);

		Assert.assertEquals(resultado.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_nova_espera_por_existencia_de_espera_servico_social_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDTO = ContextoUsuario.construirUsuarioDTOComIdentificacao();

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				usuarioDTO.getId());

		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();
		esperaDto.setPreCadastroDto(null);
		esperaDto.setUsuarioDto(usuarioDTO);

		ResultadoEdicaoEsperaDTO resultadoInclusaoEspera = servicoSisLaraServerRmi.editarEspera(esperaDto,
				resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoEspera.sucesso());
		Assert.assertEquals(resultadoInclusaoEspera.obterMensagens(),
				"O Usuário está bloqueado por excesso de faltas não justificadas.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_agendamento_por_existencia_de_espera_servico_social_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDto = ContextoUsuario.construirUsuarioDTOComIdentificacao();

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				usuarioDto.getId());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));
		agendamentoDto.setUsuarioDto(usuarioDto);

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAgendamentoDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAgendamentoDto.obterMensagens(),
				"O Usuário está bloqueado por excesso de faltas não justificadas.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_agendamento_servico_social_mesmo_com_existencia_de_espera_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDto = ContextoUsuario.construirUsuarioDTOComIdentificacao();

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				usuarioDto.getId());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		// Ref. id 18888
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(18888));
		agendamentoDto.setUsuarioDto(usuarioDto);

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAgendamentoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_reagenda_agendamento_com_sucesso() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(37));

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(3000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		// Id 17777
		AgendamentoDTO agendamentoAntigoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(17777));
		// Id 19999
		AgendamentoDTO agendamentoNovoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(19999));

		ResultadoEdicaoAgendamentoDTO resultadoReagendamentoAgendamentoDto = servicoSisLaraServerRmi
				.reagendarAgendamento(agendamentoAntigoDto, agendamentoNovoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoReagendamentoAgendamentoDto.sucesso());
		Assert.assertEquals(resultadoReagendamentoAgendamentoDto.obterMensagens(),
				"Não foi possível efetuar o reagendamento.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_reagenda_agendamento_de_usuario_em_espera_por_excesso_de_faltas_com_sucesso()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(37));

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setDataInicio("01/01/1000");
		especificacao.setDataTermino("01/01/2999");
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		// Id 13333
		AgendamentoDTO agendamentoAntigoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				agendamentoAntigoDto.getUsuarioDto().getId());

		// Id 14444
		AgendamentoDTO agendamentoNovoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(14444));

		MaquinaTempo.mudarDataAtual("01/01/2012");
		ResultadoEdicaoAgendamentoDTO resultadoReagendamentoAgendamentoDto = servicoSisLaraServerRmi
				.reagendarAgendamento(agendamentoAntigoDto, agendamentoNovoDto, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertFalse(resultadoReagendamentoAgendamentoDto.sucesso());
		Assert.assertEquals(resultadoReagendamentoAgendamentoDto.obterMensagens().trim(),
				"O Usuário está bloqueado por excesso de faltas não justificadas.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_reagenda_agendamento_de_usuario_devido_expiracao_do_prazo_de_24_horas()
			throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(37));

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setDataInicio("01/01/1000");
		especificacao.setDataTermino("01/01/2999");
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		// Id 13333
		AgendamentoDTO agendamentoAntigoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));

		// Id 14444
		AgendamentoDTO agendamentoNovoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(14444));

		ResultadoEdicaoAgendamentoDTO resultadoReagendamentoAgendamentoDto = servicoSisLaraServerRmi
				.reagendarAgendamento(agendamentoAntigoDto, agendamentoNovoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoReagendamentoAgendamentoDto.sucesso());
		Assert.assertEquals(resultadoReagendamentoAgendamentoDto.obterMensagens().trim(),
				"Prazo máximo de 24 horas para efetuar o reagendamento está expirado. O usuário deverá retornar para a lista de espera.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_reagenda_agendamento_de_usuario_devido_setor_incompativel() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setDataInicio("01/01/1000");
		especificacao.setDataTermino("01/01/2999");
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
	
		AgendamentoDTO agendamentoAntigoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(26666));
	
		AgendamentoDTO agendamentoNovoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(16666));

		MaquinaTempo.mudarDataAtual("01/01/2012");
		ResultadoEdicaoAgendamentoDTO resultadoReagendamentoAgendamentoDto = servicoSisLaraServerRmi
				.reagendarAgendamento(agendamentoAntigoDto, agendamentoNovoDto, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		Assert.assertFalse(resultadoReagendamentoAgendamentoDto.sucesso());
		Assert.assertTrue(resultadoReagendamentoAgendamentoDto.obterMensagens().contains(
				"Não é possível agendar um usuário de setor incompatível com o solicitado pelo agendamento."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_reagenda_agendamento_com_sucesso() throws RemoteException {

		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setDataInicio("01/01/1000");
		especificacao.setDataTermino("01/01/2999");
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		// Id 13333
		AgendamentoDTO agendamentoAntigoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(13333));
		// Id 15555
		AgendamentoDTO agendamentoNovoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(15555));

		MaquinaTempo.mudarDataAtual("01/01/2012");
		ResultadoEdicaoAgendamentoDTO resultadoReagendamentoAgendamentoDto = servicoSisLaraServerRmi
				.reagendarAgendamento(agendamentoAntigoDto, agendamentoNovoDto, resultadoDto.getToken());
		MaquinaTempo.restaurarDataOriginal();

		AgendamentoDTO agendamentoDtoLiberado = (AgendamentoDTO) resultadoReagendamentoAgendamentoDto
				.obterObjetoDtoEditado();

		EspecificacaoPesquisaAgendamentoDTO especificacaoAposReagendamento = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoAposReagendamento.setProntuario("12222");
		ResultadoListagemAgendamentoDTO resultadoAposReagendamento = servicoSisLaraServerRmi
				.obterListagemAgendamento(especificacaoAposReagendamento);
		// id 13333
		AgendamentoDTO agendamentoDtoReagendado = (AgendamentoDTO) obterDaRelacaoPorId(
				resultadoAposReagendamento.getObjetosDto(), new Long(13333));
		// id 15555
		AgendamentoDTO agendamentoDtoAgendado = (AgendamentoDTO) obterDaRelacaoPorId(
				resultadoAposReagendamento.getObjetosDto(), new Long(15555));

		Assert.assertTrue(resultadoReagendamentoAgendamentoDto.sucesso());
		Assert.assertNull(agendamentoDtoLiberado.getUsuarioDto());
		Assert.assertTrue(agendamentoDtoLiberado.isEstaDisponivel());
		Assert.assertTrue(agendamentoDtoReagendado.isEstaReagendado());
		Assert.assertTrue(agendamentoDtoAgendado.isEstaAgendado());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_agendamento_na_avaliacao_e_triagem_mesmo_com_existencia_de_espera_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		UsuarioDTO usuarioDto = ContextoUsuario.construirUsuarioDTOComIdentificacao();

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto,
				usuarioDto.getId());

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDTO.setId(new Long(37));

		ModuloDTO moduloDTO = new ModuloDTO(new Long(37), "SS");

		EspecificacaoPesquisaAgendamentoDTO especificacao = new EspecificacaoPesquisaAgendamentoDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAgendamentoDTO resultado = servicoSisLaraServerRmi.obterListagemAgendamento(especificacao);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(resultado.getObjetosDto(),
				new Long(12222));
		agendamentoDto.setDescricaoTipoAtendimentoDto(descricaoTipoAtendimentoDTO);
		agendamentoDto.setModuloDto(moduloDTO);
		agendamentoDto.setUsuarioDto(usuarioDto);

		ResultadoEdicaoAgendamentoDTO resultadoEdicaoAgendamentoDto = servicoSisLaraServerRmi
				.editarAgendamento(agendamentoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAgendamentoDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_em_conflito() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAtendimentoIndividualDto.obterMensagens(),
				"Já existe atendimento individual cadastrado com a data e hora especificado. Detalhes: PAULO AUGUSTO BANDEIRA DOS SANTOS, 31/12/2012, 09:00 às 10:00.\n PAULO AUGUSTO BANDEIRA DOS SANTOS, 31/12/2012, 09:00 às 10:00.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_atendimento_individual_com_liberacao_atendimento_individual_simultaneo()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDto.getDescricaoTipoAtendimentoDto().setId(new Long(52));
		atendimentoIndividualDto.setModuloDto(new ModuloDTO(new Long(4), "Modulo Liberrado"));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_dia_posterior_ao_dia_atual() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDto.setData("31/12/2090");

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAtendimentoIndividualDto.obterMensagens(),
				"Insira uma data igual ou anterior ao dia de hoje.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_altera_atendimento_individual_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		especificacao.setDataInicio("31/12/2014");
		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);
		// Ref. id 14444
		AtendimentoIndividualDTO atendimentoIndividualDto = (AtendimentoIndividualDTO) obterDaRelacaoPorId(
				resultado.getObjetosDto(), new Long(16666));
		atendimentoIndividualDto.setSetorDto(new SetorDTO("PROCEJA"));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_devido_obrigatoriedade_de_primeira_vez_ou_retorno()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("12:00", "13:00"));
		atendimentoIndividualDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAvaliacaoETriagemDTO());
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(null);
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAtendimentoIndividualDto.obterMensagens().trim(),
				"Selecione o campo Primeira Vez ou Retorno.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_altera_atendimento_individual_em_conflito() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		especificacao.setDataInicio("31/12/2012");
		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);
		// Ref id 12222
		AtendimentoIndividualDTO atendimentoIndividualDto = (AtendimentoIndividualDTO) obterDaRelacaoPorId(
				resultado.getObjetosDto(), new Long(12222));
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("09:00", "10:00"));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAtendimentoIndividualDto.obterMensagens(),
				"Já existe atendimento individual cadastrado com a data e hora especificado. Detalhes: PAULO AUGUSTO BANDEIRA DOS SANTOS, 31/12/2012, 09:00 às 10:00.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_tipo_vinculo_com_sucesso() throws RemoteException {
		ResultadoListagemTipoVinculoDTO resultadoListagemTipoVinculoDto = servicoSisLaraServerRmi
				.obterListagemTipoVinculo();

		Assert.assertTrue(resultadoListagemTipoVinculoDto.sucesso());
		Assert.assertEquals(resultadoListagemTipoVinculoDto.getObjetosDto().size(), 4);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_participacao_com_sucesso() throws RemoteException {
		ResultadoListagemParticipacaoDTO resultadoListagemParticipacaoDto = servicoSisLaraServerRmi
				.obterListagemParticipacao();

		Assert.assertTrue(resultadoListagemParticipacaoDto.sucesso());
		Assert.assertEquals(resultadoListagemParticipacaoDto.getObjetosDto().size(), 3);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_de_recursos_com_sucesso() throws RemoteException {
		ResultadoListagemRecursoDTO resultadoListagemRecursosDto = servicoSisLaraServerRmi
				.obterListagemRecurso();

		Assert.assertTrue(resultadoListagemRecursosDto.sucesso());
		Assert.assertEquals(resultadoListagemRecursosDto.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_projeto_com_especificacao() throws RemoteException {
		EspecificacaoPesquisaProjetoDTO especificacao = new EspecificacaoPesquisaProjetoDTO();
		especificacao.setProjeto("Projeto Alcoa 1");
		
		ResultadoListagemProjetoDTO resultadoListagemProjetosDto = servicoSisLaraServerRmi
				.pesquisarProjetoPor(especificacao);

		Assert.assertTrue(resultadoListagemProjetosDto.sucesso());
		Assert.assertEquals(resultadoListagemProjetosDto.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_lote_recurso_com_sucesso() throws RemoteException {
		ResultadoValidacaoLoteRecursoDTO resultadoValidacaoLoteRecursoDto = servicoSisLaraServerRmi
				.validarLoteRecurso(ContextoLoteRecurso.fabricarComTodosOsDadosDTO());
		
		Assert.assertTrue(resultadoValidacaoLoteRecursoDto.sucesso());
	}
		
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosPreCadastro();

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.sucesso());
		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_atendimento_individual_com_especificacao() throws RemoteException {
		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_obtem_listagem_atendimento_individual_com_especificacao_invalida() throws RemoteException {
		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();

		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);

		Assert.assertFalse(resultado.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_tipo_espera_disponives_para_inclusao() throws RemoteException {
		ResultadoListagemTipoEsperaDTO resultado = servicoSisLaraServerRmi
				.obterListagemTipoEsperaDisponiveisParaInclusao();

		Assert.assertTrue(resultado.sucesso());
		Assert.assertEquals(resultado.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_copia_atendimento_individual_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDTO = new EspecificacaoCopiaAtendimentoIndividualDTO();
		especificacaoCopiaAtendimentoIndividualDTO.setHorarioDto(new HorarioDTO("12:00", "13:00"));

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);
		// Ref id 13333
		AtendimentoIndividualDTO atendimentoIndividualDto = (AtendimentoIndividualDTO) obterDaRelacaoPorId(
				resultado.getObjetosDto(), new Long(13333));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoCopiaAtendimentoIndividual = servicoSisLaraServerRmi
				.copiarAtendimentoIndividual(especificacaoCopiaAtendimentoIndividualDTO, atendimentoIndividualDto,
						resultadoDto.getToken());
		AtendimentoIndividualDTO atendimentoIndividualDTOCopiado = (AtendimentoIndividualDTO) resultadoCopiaAtendimentoIndividual
				.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoCopiaAtendimentoIndividual.sucesso());
		Assert.assertNotEquals(atendimentoIndividualDTOCopiado.getId(), atendimentoIndividualDto.getId());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_copia_atendimento_individual_com_excesso_de_faltas_e_envia_para_lista_de_espera()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDTO = new EspecificacaoCopiaAtendimentoIndividualDTO();

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		especificacao.setDataInicio("29/11/2014");

		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);
		// Ref id 14444
		AtendimentoIndividualDTO atendimentoIndividualDto = (AtendimentoIndividualDTO) obterDaRelacaoPorId(
				resultado.getObjetosDto(), new Long(14444));

		for (int i = 1; i <= 6; i++) {
			especificacaoCopiaAtendimentoIndividualDTO
					.setHorarioDto(new HorarioDTO("0" + i + ":00", "0" + (i + 1) + ":00"));
			servicoSisLaraServerRmi.copiarAtendimentoIndividual(especificacaoCopiaAtendimentoIndividualDTO,
					atendimentoIndividualDto, resultadoDto.getToken());
		}

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEspera = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEspera.setProntuario(atendimentoIndividualDto.getUsuarioDto().getId().toString());
		especificacaoPesquisaEspera.setInteresse(false);
		especificacaoPesquisaEspera.setLmLigou(false);
		especificacaoPesquisaEspera.setPendencias(false);

		ResultadoListagemEsperaDTO resultadoEspera = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEspera);

		Assert.assertEquals(resultadoEspera.getObjetosDto().size(), 4);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_nao_copia_atendimento_individual_em_conflito() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDTO = new EspecificacaoCopiaAtendimentoIndividualDTO();
		especificacaoCopiaAtendimentoIndividualDTO.setHorarioDto(new HorarioDTO("09:00", "10:00"));

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		especificacao.setDataInicio("31/12/2012");
		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);
		// Ref id 12222
		AtendimentoIndividualDTO atendimentoIndividualDto = (AtendimentoIndividualDTO) obterDaRelacaoPorId(
				resultado.getObjetosDto(), new Long(12222));

		ResultadoEdicaoAtendimentoIndividualDTO resultadoCopiaAtendimentoIndividual = servicoSisLaraServerRmi
				.copiarAtendimentoIndividual(especificacaoCopiaAtendimentoIndividualDTO, atendimentoIndividualDto,
						resultadoDto.getToken());

		Assert.assertFalse(resultadoCopiaAtendimentoIndividual.sucesso());
		Assert.assertEquals(resultadoCopiaAtendimentoIndividual.obterMensagens(),
				"Já existe atendimento individual cadastrado com a data e hora especificado. Detalhes: PAULO AUGUSTO BANDEIRA DOS SANTOS, 31/12/2012, 09:00 às 10:00.\n PAULO AUGUSTO BANDEIRA DOS SANTOS, 31/12/2012, 09:00 às 10:00.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_nao_copia_atendimento_individual_invalido() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDTO = new EspecificacaoCopiaAtendimentoIndividualDTO();
		especificacaoCopiaAtendimentoIndividualDTO.setHorarioDto(new HorarioDTO("09:00", "10:00"));

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacao = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacao.setProfissionalDto(new ProfissionalDTO(new Long(1000), "Josep", "1234"));
		especificacao.setDataInicio("31/12/2012");
		ResultadoListagemAtendimentoIndividualDTO resultado = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacao);
		// Ref id 12222
		AtendimentoIndividualDTO atendimentoIndividualDto = (AtendimentoIndividualDTO) obterDaRelacaoPorId(
				resultado.getObjetosDto(), new Long(12222));
		atendimentoIndividualDto.setUsuarioDto(null);
		atendimentoIndividualDto.setPreCadastroDto(null);

		ResultadoEdicaoAtendimentoIndividualDTO resultadoCopiaAtendimentoIndividual = servicoSisLaraServerRmi
				.copiarAtendimentoIndividual(especificacaoCopiaAtendimentoIndividualDTO, atendimentoIndividualDto,
						resultadoDto.getToken());

		Assert.assertFalse(resultadoCopiaAtendimentoIndividual.sucesso());
		Assert.assertEquals(resultadoCopiaAtendimentoIndividual.obterMensagens(),
				"Insira um Usuário ou Pré-Cadastro.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_demanda_com_especificacao() throws RemoteException {
		EspecificacaoPesquisaDemandaDTO especificacao = ContextoEspecificacaoPesquidaDemanda
				.fabricarDtoComTodosOsDados();
		especificacao.setPreCadastroDto(null);
		especificacao.setCpf(null);
		ResultadoListagemDemandaDTO resultado = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_demanda_com_especificacao_contendo_cpf() throws RemoteException {
		EspecificacaoPesquisaDemandaDTO especificacao = new EspecificacaoPesquisaDemandaDTO();
		especificacao.setCpf("90353388122");
		ResultadoListagemDemandaDTO resultado = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
		Assert.assertEquals(resultado.getObjetosDto().size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_demanda_com_especificacao_contendo_aguardando() throws RemoteException {
		EspecificacaoPesquisaDemandaDTO especificacao = new EspecificacaoPesquisaDemandaDTO();
		especificacao.setStatusDemandaDto(new StatusDemandaDTO(StatusDemanda.AGUARDANDO.toString()));
		ResultadoListagemDemandaDTO resultado = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
		Assert.assertEquals(resultado.getObjetosDto().size(), 6);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_obtem_listagem_demanda_com_especificacao_invalida() throws RemoteException {
		EspecificacaoPesquisaDemandaDTO especificacao = new EspecificacaoPesquisaDemandaDTO();
		ResultadoListagemDemandaDTO resultado = servicoSisLaraServerRmi.obterListagemDemanda(especificacao);

		Assert.assertFalse(resultado.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_projeto_ativos() throws RemoteException {
		ResultadoListagemProjetoDTO resultado = servicoSisLaraServerRmi.obterListagemProjetoAtivos();

		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.getObjetosDto().isEmpty(), "A lista deveria conter os itens.");
		Assert.assertEquals(resultado.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_lote_recurso_com_erro() throws RemoteException {
		LoteRecursoDTO loteRecursoDto = new LoteRecursoDTO();
		ResultadoValidacaoLoteRecursoDTO resultadoValidacaoLoteRecurso = servicoSisLaraServerRmi
				.validarLoteRecurso(loteRecursoDto);

		Assert.assertFalse(resultadoValidacaoLoteRecurso.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_validacao_lote_recurso_sem_erro() throws RemoteException {
		LoteRecursoDTO loteRecursoDto = ContextoLoteRecurso.fabricarComTodosOsDadosDTO();
		ResultadoValidacaoLoteRecursoDTO resultadoValidacaoLoteRecurso = servicoSisLaraServerRmi
				.validarLoteRecurso(loteRecursoDto);

		Assert.assertTrue(resultadoValidacaoLoteRecurso.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_geracao_demanda_cartela_de_selos_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();

		String conteudoArquivo = "Conteúdo do arquivo.";

		PreCadastroDTO preCadastroDto = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDto.getInformacaoEssencialDto().setDataNascimento("27/07/1982");
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);
		especificacaoGeracaoDemandaDTO.setDocumentosSolicitacaoDocacaoDto(obterTodosDocumentos(conteudoArquivo));

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		DemandaDTO demandaDtoSalva = resultadoGeracaoDemanda.getObjetosDto().get(0);

		Assert.assertTrue(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(demandaDtoSalva.isCartelaDeSelos());
		Assert.assertEquals(demandaDtoSalva.getValorCartela(), "2500,00");
		Assert.assertEquals(demandaDtoSalva.getStatusDemandaDto(), new StatusDemandaDTO(StatusDemanda.AGUARDANDO.toString()));
		for (DocumentoSolicitacaoDoacaoDTO documentoSolicitacaoDoacaoDTO : demandaDtoSalva
				.getDocumentosSolicitacaoDoacaoDto()) {
			ArquivoDTO arquivoDtoSalvo = servicoSisLaraServerRmi.obterArquivoDocumentoSolicitacaoDoacaoDTO(
					demandaDtoSalva, documentoSolicitacaoDoacaoDTO.getArquivoDTO());
			Assert.assertEquals(arquivoDtoSalvo.obterConteudo(), conteudoArquivo.getBytes());
		}
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_demanda_normal_por_profissional_nao_e_servico_social() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();

		String conteudoArquivo = "Conteúdo do arquivo.";

		PreCadastroDTO preCadastroDto = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDto.getInformacaoEssencialDto().setDataNascimento("27/07/1982");
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setDocumentosSolicitacaoDocacaoDto(obterTodosDocumentos(conteudoArquivo));

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens()
				.contains("Somente profissionais do Serviço Social podem realizar essa operação."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_demanda_cartela_de_selos_pre_cadastro_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();

		String conteudoArquivo = "Conteúdo do arquivo.";

		PreCadastroDTO preCadastroDto = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDto.getInformacaoEssencialDto().setDataNascimento("27/07/1982");
		preCadastroDto.getInformacaoEssencialDto().setCpf("90353388122");
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);
		especificacaoGeracaoDemandaDTO.setDocumentosSolicitacaoDocacaoDto(obterTodosDocumentos(conteudoArquivo));

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens()
				.contains("Já existe uma demanda cadastrada para o CPF selecionado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_demanda_cartela_de_selos_pre_cadastro_duplicado_legado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();

		String conteudoArquivo = "Conteúdo do arquivo.";

		PreCadastroDTO preCadastroDto = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDto.getInformacaoEssencialDto().setDataNascimento("27/07/1982");
		preCadastroDto.getInformacaoEssencialDto().setCpf("37925071000105");
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);
		especificacaoGeracaoDemandaDTO.setDocumentosSolicitacaoDocacaoDto(obterTodosDocumentos(conteudoArquivo));

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens()
				.contains("Já existe uma cartela de selos no legado para o CPF selecionado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_demanda_cartela_de_selos_usuario_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();

		String conteudoArquivo = "Conteúdo do arquivo.";

		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(null);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(ContextoUsuario.construirUsuarioDTO());
		especificacaoGeracaoDemandaDTO.getUsuariosDto().getInformacaoEssencialDto().setCpf("90353388122");
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);
		especificacaoGeracaoDemandaDTO.setDocumentosSolicitacaoDocacaoDto(obterTodosDocumentos(conteudoArquivo));

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens()
				.contains("Já existe uma demanda cadastrada para o CPF selecionado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_demanda_a_partir_de_especificacao_invalida() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertFalse(resultadoGeracaoDemanda.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_demanda_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertFalse(resultadoGeracaoDemanda.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_de_demanda() throws RemoteException {
		ResultadoListagemStatusDemandaDTO resultadoListagemStatusDemandaDto = servicoSisLaraServerRmi
				.obterListagemStatusDemandaLimitada();

		Assert.assertTrue(resultadoListagemStatusDemandaDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusDemandaDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemStatusDemandaDto.getObjetosDto().size(), 3);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void servico_obtem_listagem_status_de_demanda_limitado() throws RemoteException {
		ResultadoListagemStatusDemandaDTO resultadoListagemStatusDemandaDto = servicoSisLaraServerRmi
				.obterListagemStatusDemanda();

		Assert.assertTrue(resultadoListagemStatusDemandaDto.sucesso());
		Assert.assertFalse(resultadoListagemStatusDemandaDto.getObjetosDto().isEmpty(),
				"A lista deveria conter os itens.");
		Assert.assertEquals(resultadoListagemStatusDemandaDto.getObjetosDto().size(), 6);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_consulta_endereco_por_cep_com_sucesso() throws RemoteException {
		ResultadoConsultaCEP resultado = servicoSisLaraServerRmi.consultarEndereco("01151000");

		EnderecoCEPDTO enderecoCEPDTO = (EnderecoCEPDTO) resultado.obterObjetoDtoEditado();
		Assert.assertTrue(resultado.sucesso());
		Assert.assertFalse(resultado.obterMensagens().isEmpty());
		Assert.assertNotNull(enderecoCEPDTO.getPaisDto());
		Assert.assertNotNull(enderecoCEPDTO.getMunicipioDto());
		Assert.assertNotNull(enderecoCEPDTO.getUfDto());
		Assert.assertNotNull(enderecoCEPDTO.getBairro());
		Assert.assertNotNull(enderecoCEPDTO.getEndereco());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_contatos_de_integrantes_do_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(11111));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioContatosIntegrantesGrupo(grupoDto,
				moduloPeriodoDto, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[33226]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_lista_frequencia_vertical_por_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(11111));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFrequenciaPorGrupo(grupoDto, moduloPeriodoDto,
				false, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32821]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_lista_frequencia_paisagem_por_grupo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(11111));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFrequenciaPorGrupo(grupoDto, moduloPeriodoDto,
				true, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(relatorioPDF.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[34586]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_lista_frequencia_vertical_por_grupo_por_falta_permissao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(11111));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFrequenciaPorGrupo(grupoDto, moduloPeriodoDto,
				false, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_lista_frequencia_paisagem_por_grupo_por_falta_permissao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(11111));

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioFrequenciaPorGrupo(grupoDto, moduloPeriodoDto,
				true, resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_retirada_sem_erro() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProntuario(new Long(12345));
		retiradaDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		retiradaDto.setVoluntarioDto(ContextoProfissional.construirProfissionalDTOAlternativo());

		ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaDto = servicoSisLaraServerRmi.efetuarRetirada(retiradaDto,
				resultadoAutenticacaoDto.getToken());

		Assert.assertTrue(resultadoEdicaoRetiradaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_retirada_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		RetiradaDTO retiradaDto = new RetiradaDTO();

		ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaDto = servicoSisLaraServerRmi.efetuarRetirada(retiradaDto,
				resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoRetiradaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_retirada_com_erro_de_ja_existencia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProntuario(new Long(12345));
		retiradaDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());
		retiradaDto.setVoluntarioDto(ContextoProfissional.construirProfissionalDTOAlternativo());

		servicoSisLaraServerRmi.efetuarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaComErroDto = servicoSisLaraServerRmi
				.efetuarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoRetiradaComErroDto.sucesso());
		Assert.assertFalse(resultadoEdicaoRetiradaComErroDto.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoEdicaoRetiradaComErroDto.obterMensagens(), "O prontuário já está retirado. \n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_retirada_com_erro_de_obritadoriedade() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		RetiradaDTO retiradaDto = new RetiradaDTO();

		ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaComErroDto = servicoSisLaraServerRmi
				.efetuarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoRetiradaComErroDto.sucesso());
		Assert.assertFalse(resultadoEdicaoRetiradaComErroDto.obterMensagens().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_baixa_de_retirada_sem_erro() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoAutenticacaoDTO resultadoAutenticacaoAlternativaDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProntuario(new Long(12345));
		retiradaDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());

		servicoSisLaraServerRmi.efetuarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		ResultadoEdicaoRetiradaDTO resultadoEdicaoBaixaRetiradaDto = servicoSisLaraServerRmi.baixarRetirada(retiradaDto,
				resultadoAutenticacaoAlternativaDto.getToken());

		Assert.assertTrue(resultadoEdicaoBaixaRetiradaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_baixa_de_retirada_sem_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProntuario(new Long(12345));
		retiradaDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());

		ResultadoEdicaoRetiradaDTO resultadoEdicaoBaixaRetiradaDto = servicoSisLaraServerRmi.baixarRetirada(retiradaDto,
				resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoBaixaRetiradaDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_baixa_de_retirada_com_erro_de_ja_existente() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		RetiradaDTO retiradaDto = new RetiradaDTO();
		retiradaDto.setProntuario(new Long(12345));
		retiradaDto.setProfissionalDto(ContextoProfissional.construirProfissionalDTO());

		servicoSisLaraServerRmi.efetuarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		servicoSisLaraServerRmi.baixarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		ResultadoEdicaoRetiradaDTO resultadoEdicaoBaixaRetiradaComErroDto = servicoSisLaraServerRmi
				.baixarRetirada(retiradaDto, resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoBaixaRetiradaComErroDto.sucesso());
		Assert.assertFalse(resultadoEdicaoBaixaRetiradaComErroDto.obterMensagens().isEmpty());
		Assert.assertEquals(resultadoEdicaoBaixaRetiradaComErroDto.obterMensagens(),
				"O prontuário não está retirado.\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_cid_a_partir_de_codigo() throws RemoteException {
		String descricao = "A00.1 Colera dev Vibrio cholerae 01 biot El Tor";
		ResultadoConsultaCidDTO resultadoConsultaCidDto = servicoSisLaraServerRmi.consultarCid("A001");

		Assert.assertTrue(resultadoConsultaCidDto.sucesso());
		Assert.assertEquals(resultadoConsultaCidDto.obterObjetoDtoEditado().toString(), descricao);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_obtem_cid_a_partir_de_codigo() throws RemoteException {
		ResultadoConsultaCidDTO resultadoConsultaCidDto = servicoSisLaraServerRmi.consultarCid("A001AAAAA");

		Assert.assertFalse(resultadoConsultaCidDto.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_aviso_atualizacao_ativado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		servicoSisLaraServerRmi.ativarDesativarAvisoDeAtualizacao();

		Assert.assertTrue(servicoSisLaraServerRmi.exibirAvisoDeAtualizacao(resultadoAutenticacaoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_aviso_atualizacao_desativado_apos_leitura_aviso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		servicoSisLaraServerRmi.ativarDesativarAvisoDeAtualizacao();

		servicoSisLaraServerRmi.confirmarLeituraDeAvisoDeAtualizacao(resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(servicoSisLaraServerRmi.exibirAvisoDeAtualizacao(resultadoAutenticacaoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_aviso_atualizacao_ativado_e_desativado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		servicoSisLaraServerRmi.ativarDesativarAvisoDeAtualizacao();

		Assert.assertFalse(servicoSisLaraServerRmi.exibirAvisoDeAtualizacao(resultadoAutenticacaoDto.getToken()));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_desligamento_automatico_de_usuarios_nos_grupos_ativos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(11111));
		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDto.getModulosUsuariosDto(), new Long(11111));
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.DESLIGADO.toString()));
		moduloUsuarioDTO.setDataOcorrencia("01/01/2000");

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAposDesligamento = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");
		GrupoDTO grupoDtoAposDesligamento = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAposDesligamento.getObjetosDto(), new Long(14444));
		ModuloPeriodoDTO moduloPeriodoDtoAposDesligamento = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoDtoAposDesligamento.getModulosPeriodosDto(), new Long(77777));
		ModuloUsuarioDTO moduloUsuarioDTOAposDesligamento = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDtoAposDesligamento.getModulosUsuariosDto(), new Long(44444));

		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(moduloUsuarioDTOAposDesligamento.getStatusDto().toString(),
				StatusRelacaoComModulo.DESLIGADO.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_integracao_comunidade_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(11111));

		ModuloPreCadastroDTO moduloPreCadastroDTO = ContextoModuloPreCadastro.fabricarModuloPreCadastroDTO();
		moduloPreCadastroDTO.setPreCadastroDto(ContextoPreCadastro.construirPreCadastroDTOAlternativo());
		moduloPreCadastroDTO.setStatusDto(null);
		List<ModuloPreCadastroDTO> modulosPreCadastro = new ArrayList<ModuloPreCadastroDTO>();
		modulosPreCadastro.add(moduloPreCadastroDTO);

		moduloPeriodoDto.setModulosUsuariosDto(new ArrayList<>());
		moduloPeriodoDto.setModulosPreCadastroDto(modulosPreCadastro);

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());

		ModuloPreCadastroDTO moduloPreCadastroDTOAposInclusao = obterAtendimentoDoUsuarioPeloPreCadastro(
				((ModuloPeriodoDTO) resultadoEdicaoModuloPeriodoDTO.obterObjetoDtoEditado()).getModulosPreCadastroDto(),
				ContextoPreCadastro.construirPreCadastroDTOAlternativo().getId());

		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(moduloPreCadastroDTOAposInclusao.getStatusDto().toString(),
				StatusRelacaoComModulo.INTEGRADO.toString());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_integracao_em_grupo_de_reuniao_de_integracao_a_parir_de_usuario_integrado_com_opcao_ignorar_reuniao_de_integracao_selecionado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(14444));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(77777));
		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioAlternativoDTO();
		moduloUsuarioDTO.setId(null);
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.INTEGRADO.toString()));
		moduloUsuarioDTO.setIgnorarRegraDeReuniaoDeIntegracao(true);
		moduloPeriodoDto.setModulosUsuariosDto(Arrays.asList(moduloUsuarioDTO));

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ModuloPeriodoDTO moduloPeriodoDtoSS1 = obterModuloPeriodoDto(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1").getObjetosDto(), new Long(66666),
				new Long(99999));

		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.sucesso());
		// Aplicou duas integrações automaticas em RI e ignorou uma.
		Assert.assertEquals(moduloPeriodoDtoSS1.getModulosUsuariosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_integracao_em_grupo_de_reuniao_de_integracao_a_partir_de_grupo_excepcional()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("CL-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(888888));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(888888));
		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioAlternativoDTO();
		moduloUsuarioDTO.setId(null);
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.EVENTUAL.toString()));
		moduloUsuarioDTO.setIgnorarRegraDeReuniaoDeIntegracao(true);
		moduloPeriodoDto.setModulosUsuariosDto(Arrays.asList(moduloUsuarioDTO));

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		ModuloPeriodoDTO moduloPeriodoDtoSS1 = obterModuloPeriodoDto(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1").getObjetosDto(), new Long(66666),
				new Long(99999));

		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(moduloPeriodoDtoSS1.getModulosUsuariosDto().size(), 0);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_integracao_em_grupo_ose() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("OSE-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(821582));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(821582));
		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setId(null);
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.INTEGRADO.toString()));
		moduloPeriodoDto.setModulosUsuariosDto(Arrays.asList(moduloUsuarioDTO));

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoModuloPeriodoDTO.obterMensagens().trim(),
				"Não é possível existir um usuário INTEGRADO em grupos OSE ou CL.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_relacao_psicossocial_para_provisorio_devido_alteracao_para_status_no_aee_para_provisorio()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTOAposAtualizacao = servico_atualiza_status_de_relacao_do_aee_e_retorna_o_psicossocial_do_grupo(
				StatusRelacaoComModulo.PROVISORIO);

		Assert.assertTrue(moduloUsuarioDTOAposAtualizacao.getStatusDto().toString()
				.equals(StatusRelacaoComModulo.PROVISORIO.toString()));
		Assert.assertEquals(moduloUsuarioDTOAposAtualizacao.getObs(),
				"Status alterado automaticamente pelo sistema. \nObs do modulo original: \nTexto de OBS.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_relacao_psicossocial_para_integrado_devido_alteracao_para_status_no_aee_para_acesso()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTOAposAtualizacao = servico_atualiza_status_de_relacao_do_aee_e_retorna_o_psicossocial_do_grupo(
				StatusRelacaoComModulo.ACESSO);

		Assert.assertTrue(moduloUsuarioDTOAposAtualizacao.getStatusDto().toString()
				.equals(StatusRelacaoComModulo.INTEGRADO.toString()));
		Assert.assertEquals(moduloUsuarioDTOAposAtualizacao.getObs(),
				"Status alterado automaticamente pelo sistema. \nObs do modulo original: \nTexto de OBS.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_atualiza_status_relacao_psicossocial_para_integrado_devido_alteracao_para_status_no_aee_para_integrado()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTOAposAtualizacao = servico_atualiza_status_de_relacao_do_aee_e_retorna_o_psicossocial_do_grupo(
				StatusRelacaoComModulo.INTEGRADO);

		Assert.assertTrue(moduloUsuarioDTOAposAtualizacao.getStatusDto().toString()
				.equals(StatusRelacaoComModulo.INTEGRADO.toString()));
		Assert.assertEquals(moduloUsuarioDTOAposAtualizacao.getObs(),
				"Status alterado automaticamente pelo sistema. \nObs do modulo original: \nTexto de OBS.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_incluir_status_provisorio_na_relacao_com_psicossocial_devido_inclusao_de_usuario_com_status_de_relacao_provisorio_no_aee()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTOAposAtualizacao = servico_inclui_usuario_com_status_de_relacao_no_aee_e_retorna_o_psicossocial_do_grupo(
				StatusRelacaoComModulo.PROVISORIO);

		Assert.assertTrue(moduloUsuarioDTOAposAtualizacao.getStatusDto().toString()
				.equals(StatusRelacaoComModulo.PROVISORIO.toString()));
		Assert.assertEquals(moduloUsuarioDTOAposAtualizacao.getObs(),
				"Status alterado automaticamente pelo sistema. \nObs do modulo original: \nA analisar.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_incluir_status_integrado_na_relacao_com_psicossocial_devido_inclusao_de_usuario_com_status_de_relacao_acesso_no_aee()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTOAposAtualizacao = servico_inclui_usuario_com_status_de_relacao_no_aee_e_retorna_o_psicossocial_do_grupo(
				StatusRelacaoComModulo.ACESSO);

		Assert.assertTrue(moduloUsuarioDTOAposAtualizacao.getStatusDto().toString()
				.equals(StatusRelacaoComModulo.INTEGRADO.toString()));
		Assert.assertEquals(moduloUsuarioDTOAposAtualizacao.getObs(),
				"Status alterado automaticamente pelo sistema. \nObs do modulo original: \nA analisar.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_incluir_status_integrado_na_relacao_com_psicossocial_devido_inclusao_de_usuario_com_status_de_relacao_integrado_no_aee()
			throws RemoteException {
		ModuloUsuarioDTO moduloUsuarioDTOAposAtualizacao = servico_inclui_usuario_com_status_de_relacao_no_aee_e_retorna_o_psicossocial_do_grupo(
				StatusRelacaoComModulo.INTEGRADO);

		Assert.assertTrue(moduloUsuarioDTOAposAtualizacao.getStatusDto().toString()
				.equals(StatusRelacaoComModulo.INTEGRADO.toString()));
		Assert.assertEquals(moduloUsuarioDTOAposAtualizacao.getObs(),
				"Status alterado automaticamente pelo sistema. \nObs do modulo original: \nA analisar.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inativa_grupo_por_causa_de_existencia_de_usuario_com_status_de_relacao_provisorio()
			throws RemoteException {
		ResultadoEdicaoGrupoDTO resultadoEdicaoGrupoDTO = servico_tenta_desativar_grupo_com_usuarios_no_status(
				StatusRelacaoComModulo.PROVISORIO);

		Assert.assertFalse(resultadoEdicaoGrupoDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoGrupoDTO.obterMensagens(),
				"Existe pelo menos um usuário com status Provisório ou Acesso.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inativa_grupo_por_causa_de_existencia_de_usuario_com_status_de_relacao_acesso()
			throws RemoteException {
		ResultadoEdicaoGrupoDTO resultadoEdicaoGrupoDTO = servico_tenta_desativar_grupo_com_usuarios_no_status(
				StatusRelacaoComModulo.ACESSO);

		Assert.assertFalse(resultadoEdicaoGrupoDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoGrupoDTO.obterMensagens(),
				"Existe pelo menos um usuário com status Provisório ou Acesso.\n\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_atendimento_retornando_nome_de_usuarios_com_status_de_relacao_provisorio_ou_acesso()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisico = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupoAEEPisico.getObjetosDto(),
				new Long(14444));

		ModuloPeriodoDTO moduloPeriodoAEEDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(
				grupoAeePisicoDto.getModulosPeriodosDto(), new Long(77777));

		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoAEEDto.getModulosUsuariosDto(), new Long(77777));
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.ACESSO.toString()));
		moduloUsuarioDTO.setDataOcorrencia("01/01/2000");

		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoAEEDto, resultadoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupoAEEPisicoAposAtualizacao = servicoSisLaraServerRmi
				.obterListagemGrupoAtivo("G06-1");

		GrupoDTO grupoAeePisicoDtoAposAtualizacao = (GrupoDTO) obterDaRelacaoPorId(
				resultadoListagemGrupoAEEPisicoAposAtualizacao.getObjetosDto(), new Long(14444));

		EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDTO = new EspecificacaoGeracaoAtendimentosDTO(
				grupoAeePisicoDtoAposAtualizacao.getId(),
				((ModuloPeriodoDTO) obterDaRelacaoPorId(grupoAeePisicoDto.getModulosPeriodosDto(), new Long(77777)))
						.getId(),
				"31/12/2012", "09:00", "19:00");

		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimento = servicoSisLaraServerRmi
				.gerarAtendimentos(especificacaoGeracaoAtendimentosDTO, resultadoDto.getToken());

		Assert.assertEquals(resultadoGeracaoAtendimento.obterMensagens(),
				"Dados armazenados com sucesso. \n<br>Usuário(s) com status PROVISÓRIO ou ACESSO no módulo:<br>82222 - Terezinha<br>\n");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_espera_por_excesso_de_faltas_acarretada_mais_de_uma_vez_por_conjunto_de_faltas_nao_justificadas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Long prontuario = new Long(13333);
		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoDto, prontuario);

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDto = ContextoEspecificacaoPesquisaEspera
				.fabricarDtoPesquisaEsperaDescricaoServicoSocialModuloExcessoDeFaltas(prontuario);

		ResultadoListagemEsperaDTO resultadoListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);

		EsperaDTO esperaDto = resultadoListagemEsperaDto.getObjetosDto().get(0);
		esperaDto.setStatusDto(new StatusEsperaDTO("CANCELADO"));
		esperaDto.setJustificativaCancelamento("Justificativa do cancelamento.");

		servicoSisLaraServerRmi.cancelarEspera(esperaDto, resultadoDto.getToken());

		EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDto = new EspecificacaoPesquisaAtendimentoIndividualDTO();
		especificacaoPesquisaAtendimentoIndividualDto.setProntuario(prontuario.toString());
		especificacaoPesquisaAtendimentoIndividualDto.setDataInicio("03/03/2015");
		ResultadoListagemAtendimentoIndividualDTO listagemAtendimentoIndividual = servicoSisLaraServerRmi
				.obterListagemAtendimentoIndividual(especificacaoPesquisaAtendimentoIndividualDto);
		AtendimentoIndividualDTO atendimentoIndividualDTO = listagemAtendimentoIndividual.getObjetosDto().get(0);
		atendimentoIndividualDTO.getInformacaoAtendimentoDto().setDescricao("Texto de descricao do atendiemtno");
		ResultadoEdicaoAtendimentoIndividualDTO editarAtendimentoIndividual = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDTO, resultadoDto.getToken());

		ResultadoListagemEsperaDTO resultadoListagemEsperaAposTentativaDeReenvioDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDto);

		Assert.assertTrue(editarAtendimentoIndividual.sucesso());
		Assert.assertTrue(resultadoListagemEsperaAposTentativaDeReenvioDto.getObjetosDto().isEmpty());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_quantidades_avaliacoes_funcionais() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadesAvaliacoesFuncionais("01/01/2015",
				"31/12/2015", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertTrue(variacaoMaximaDeBytes(relatorioPDF.obterConteudo(), new byte[32800]));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_de_quantidades_avaliacoes_funcionais() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoPDFDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioPDF = servicoSisLaraServerRmi.gerarRelatorioQuantidadesAvaliacoesFuncionais("01/01/2015",
				"31/12/2015", resultadoAutenticacaoPDFDto.getToken());

		Assert.assertFalse(relatorioPDF.sucesso());
		Assert.assertNotNull(relatorioPDF.obterMensagens());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_pendencias_aos_profissionais_vinculados_ao_usuario_apos_regra_de_envio_para_espera_por_excesso_de_faltas_no_atendimento_de_grupo()
			throws RemoteException {
		ResultadoAutenticacaoDTO contaAcessoShivasDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaShivas());
		ResultadoAutenticacaoDTO contaAcessoPabsantosDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		ResultadoAutenticacaoDTO contaAcessoRLemeDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1");
		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(14444));
		Long prontuario = new Long(12222);

		servico_gera_tres_atendimentos_de_grupo_com_datas_diferentes_e_altera_a_frenquencia_do_prontuario_para_fu(
				grupoDto, new Long(77777), contaAcessoShivasDto, prontuario);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaShivasDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoShivasDto.getToken());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaPabsantosDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoPabsantosDto.getToken());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaRLemeDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoRLemeDto.getToken());
		Assert.assertEquals(resultadoListagemPendenciaShivasDTO.getObjetosDto().get(0).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
		Assert.assertEquals(resultadoListagemPendenciaPabsantosDTO.getObjetosDto().get(2).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
		Assert.assertEquals(resultadoListagemPendenciaRLemeDTO.getObjetosDto().get(0).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_associa_pendencias_aos_profissionais_vinculados_ao_usuario_apos_regra_de_envio_para_espera_por_excesso_de_faltas_no_atendimento_individual()
			throws RemoteException {
		ResultadoAutenticacaoDTO contaAcessoShivasDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaShivas());
		ResultadoAutenticacaoDTO contaAcessoPabsantosDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		ResultadoAutenticacaoDTO contaAcessoRLemeDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialAlternativaDtoValida());

		Long prontuario = new Long(12222);

		AtendimentoProfissionalDTO atendimentoProfissionalDTO = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTO();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional.construirProfissionalDTOComId3000());

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes_adicionando_profissional(
				contaAcessoShivasDto, prontuario, atendimentoProfissionalDTO);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaShivasDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoShivasDto.getToken());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaPabsantosDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoPabsantosDto.getToken());
		ResultadoListagemPendenciaDTO resultadoListagemPendenciaRLemeDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoRLemeDto.getToken());
		Assert.assertEquals(resultadoListagemPendenciaShivasDTO.getObjetosDto().get(0).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
		Assert.assertEquals(resultadoListagemPendenciaPabsantosDTO.getObjetosDto().get(2).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
		Assert.assertEquals(resultadoListagemPendenciaRLemeDTO.getObjetosDto().get(0).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_transferencias_de_usuarios_com_fu_e_fj_para_outros_grupos_de_reuniao_de_integracao_e_envia_para_lista_de_espera_por_excesso_de_faltas_quando_necessario()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaShivas());

		ResultadoAutenticacaoDTO contaAcessoVPereiraDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());
		
		ResultadoAutenticacaoDTO contaAcessoVVitalinoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVVitalino());
		
		MaquinaTempo.mudarDataAtual("01/01/2016");

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G06-1");
		GrupoDTO grupoDTO = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(14444));
		ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDTO.getModulosPeriodosDto(),
				new Long(77777));
		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDTO.getModulosUsuariosDto(), new Long(77777));
		UsuarioDTO usuarioDto = moduloUsuarioDTO.getUsuarioDto();
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO("ACESSO"));
		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDTO, resultadoDto.getToken());
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		String dataA = "01/01/2100";
		MaquinaTempo.mudarDataAtual(dataA);
		ResultadoEdicaoAtendimentoGrupoDTO resultadoEdicaoAtendimentoGrupo = gerar_atendimento_de_grupo_com_frequencia_fu(
				resultadoDto, "SS-1", new Long(66666), new Long(99999), dataA, new Long(82222));
		Registro.obterAutomatizadorPendencia().atualizarPendenciasDeTransferenciasDeReuniaoIntegracao();
		String dataB = "01/01/2150";
		MaquinaTempo.mudarDataAtual(dataB);
		gerar_atendimento_de_grupo_com_frequencia_fu(contaAcessoVPereiraDto, "SS-5", new Long(54321), new Long(54321),
				dataB, new Long(82222));
		Registro.obterAutomatizadorPendencia().atualizarPendenciasDeTransferenciasDeReuniaoIntegracao();
		String dataC = "01/02/2150";
		MaquinaTempo.mudarDataAtual(dataC);
		gerar_atendimento_de_grupo_com_frequencia_fu(contaAcessoVPereiraDto, "SS-6", new Long(654321), new Long(654321),
				dataC, new Long(82222));
		Registro.obterAutomatizadorPendencia().atualizarPendenciasDeTransferenciasDeReuniaoIntegracao();
		MaquinaTempo.restaurarDataOriginal();

		ModuloUsuarioDTO moduloUsuarioDtoSS1 = obterPeloIdUsuario(
				obterModuloPeriodoDto(servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1").getObjetosDto(),
						new Long(66666), new Long(99999)),
				usuarioDto);

		ModuloUsuarioDTO moduloUsuarioDtoSS5 = obterPeloIdUsuario(
				obterModuloPeriodoDto(servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-5").getObjetosDto(),
						new Long(54321), new Long(54321)),
				usuarioDto);

		ModuloUsuarioDTO moduloUsuarioDtoSS6 = obterPeloIdUsuario(
				obterModuloPeriodoDto(servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-6").getObjetosDto(),
						new Long(654321), new Long(654321)),
				usuarioDto);

		ModuloPeriodoDTO moduloPeriodoDtoSS7 = obterModuloPeriodoDto(
				servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-7").getObjetosDto(), new Long(7654321),
				new Long(7654321));

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(contaAcessoVVitalinoDto.getToken());

		Assert.assertTrue(
				moduloUsuarioDtoSS1.getStatusDto().toString().equals(StatusRelacaoComModulo.REMOVIDO.toString()));
		Assert.assertTrue(moduloUsuarioDtoSS1.getObs().contains(
				"Removido automaticamente para outro grupo de Reunião de Integração por consequência de falta no grupo original. Data da remoção: 01/01/2100"));
		Assert.assertTrue(
				moduloUsuarioDtoSS5.getStatusDto().toString().equals(StatusRelacaoComModulo.REMOVIDO.toString()));
		Assert.assertTrue(moduloUsuarioDtoSS5.getObs().contains(
				"Removido automaticamente para outro grupo de Reunião de Integração por consequência de falta no grupo original. Data da remoção: 01/01/2150"));
		Assert.assertTrue(
				moduloUsuarioDtoSS6.getStatusDto().toString().equals(StatusRelacaoComModulo.REMOVIDO.toString()));
		Assert.assertTrue(moduloUsuarioDtoSS6.getObs()
				.contains("Removido automaticamente por Excesso de Faltas. Data da remoção: 01/02/2150"));
		Assert.assertTrue(moduloPeriodoDtoSS7.getModulosUsuariosDto().isEmpty(), "Deveria conter relação vazia.");
		Assert.assertEquals(resultadoListagemPendenciaDTO.getObjetosDto().get(0).toString(),
				"(IMPORTANTE)Prontuário 82222 foi enviado para a lista de espera do SS por excesso de faltas.");
		Assert.assertTrue(resultadoEdicaoAtendimentoGrupo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_pendencia_por_excesso_de_faltas_somente_para_os_profissionais_do_grupo_ao_aqual_o_usuario_esta_integrado_apos_atendimento_individual_com_fu()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoVpereiraDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());
		ResultadoAutenticacaoDTO resultadoShivasDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaShivas());

		Long prontuario = new Long(12222);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaShivasDTOAntes = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoShivasDto.getToken());

		AtendimentoProfissionalDTO atendimentoProfissionalDTO = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTO();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional.construirProfissionalVPereiraDTO());

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes_adicionando_profissional(
				resultadoVpereiraDto, prontuario, atendimentoProfissionalDTO);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaShivasDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoShivasDto.getToken());

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaVPereiraDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoVpereiraDto.getToken());

		EspecificacaoPesquisaEsperaDTO especificacao = new EspecificacaoPesquisaEsperaDTO();
		especificacao.setProntuario(prontuario.toString());
		especificacao.setInteresse(false);
		especificacao.setLmLigou(false);
		especificacao.setPendencias(false);
		especificacao
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		especificacao.setModuloDto(ContextoModulo.construirModuloExcessoDeFaltasDTO());
		ResultadoListagemEsperaDTO resultadoListagemEsperaDTO = servicoSisLaraServerRmi
				.obterListagemEspera(especificacao);

		Assert.assertTrue(resultadoListagemPendenciaShivasDTOAntes.getObjetosDto().isEmpty());
		Assert.assertEquals(resultadoListagemPendenciaShivasDTO.getObjetosDto().get(0).toString(),
				"(IMPORTANTE)Prontuário 12222 foi enviado para a lista de espera do SS por excesso de faltas.");
		Assert.assertTrue(resultadoListagemPendenciaVPereiraDTO.getObjetosDto().isEmpty());
		Assert.assertEquals(resultadoListagemEsperaDTO.getObjetosDto().size(), 1);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_desativacao_de_grupo_com_usuario_na_espera_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoVpereiraDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		Long prontuario = new Long(12222);

		AtendimentoProfissionalDTO atendimentoProfissionalDTO = ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTO();
		atendimentoProfissionalDTO.setProfissionalDto(ContextoProfissional.construirProfissionalVPereiraDTO());

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes_adicionando_profissional(
				resultadoVpereiraDto, prontuario, atendimentoProfissionalDTO);

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		grupoDto.setAtivo(false);

		ResultadoEdicaoGrupoDTO resultadoEdicaoGrupoDTO = servicoSisLaraServerRmi.editarGrupo(grupoDto,
				resultadoVpereiraDto.getToken());

		Assert.assertEquals(resultadoEdicaoGrupoDTO.obterMensagens().trim(),
				"Usuário(s) 12222 - Jose Augusto Siqueira está aguardando na espera por excesso de faltas. Por favor, analise o(s) caso(s) antes de desativar o grupo.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_remove_pendencias_de_excesso_de_faltas_apos_desligamento_de_usuario_do_grupo()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutorizacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		Long prontuario = new Long(12222);

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoAutorizacaoDto,
				prontuario);

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaAntesDesligamentoDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoAutorizacaoDto.getToken());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(12222));
		ModuloPeriodoDTO moduloPeriodoDTO = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(11111));
		ModuloUsuarioDTO moduloUsuarioDTO = (ModuloUsuarioDTO) obterDaRelacaoPorId(
				moduloPeriodoDTO.getModulosUsuariosDto(), new Long(11111));
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.DESLIGADO.toString()));
		servicoSisLaraServerRmi.editarModuloPeriodo(moduloPeriodoDTO, resultadoAutorizacaoDto.getToken());

		ResultadoListagemPendenciaDTO resultadoListagemPendenciaAposDesligamentoDTO = servicoSisLaraServerRmi
				.obterListagemPendencia(resultadoAutorizacaoDto.getToken());

		Assert.assertEquals(resultadoListagemPendenciaAntesDesligamentoDTO.getObjetosDto().size(), 3);
		Assert.assertEquals(resultadoListagemPendenciaAposDesligamentoDTO.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_retorna_bloqueio_por_excesso_de_faltas_apos_FU_ou_FJ_em_avaliacao_e_triagem_agendada_por_excesso_de_faltas()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutorizacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		Long prontuario = new Long(72222);

		servico_cria_tres_atendimentos_individuais_com_frequencia_fu_em_dias_diferentes(resultadoAutorizacaoDto,
				prontuario);

		EspecificacaoPesquisaEsperaDTO especificacaoPesquisaEsperaDTO = new EspecificacaoPesquisaEsperaDTO();
		especificacaoPesquisaEsperaDTO.setProntuario(prontuario.toString());
		especificacaoPesquisaEsperaDTO.setInteresse(false);
		especificacaoPesquisaEsperaDTO.setLmLigou(false);
		especificacaoPesquisaEsperaDTO.setPendencias(false);
		especificacaoPesquisaEsperaDTO
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		especificacaoPesquisaEsperaDTO.setModuloDto(ContextoModulo.construirModuloExcessoDeFaltasDTO());
		especificacaoPesquisaEsperaDTO.setStatusEsperadoDto(new StatusEsperaDTO(StatusEspera.AGUARDANDO.toString()));
		ResultadoListagemEsperaDTO resultadoPrimeiraListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDTO);
		EsperaDTO esperaDto = resultadoPrimeiraListagemEsperaDto.getObjetosDto().get(0);

		EspecificacaoPesquisaAgendamentoDTO especificacaoPesquisaAgendamentoDto = new EspecificacaoPesquisaAgendamentoDTO();
		especificacaoPesquisaAgendamentoDto.setDataInicio("28/02/2012");
		ResultadoListagemAgendamentoDTO resultadoListagemAgendamentoDTO = servicoSisLaraServerRmi
				.obterListagemAgendamento(especificacaoPesquisaAgendamentoDto);
		AgendamentoDTO agendamentoDto = (AgendamentoDTO) obterDaRelacaoPorId(
				resultadoListagemAgendamentoDTO.getObjetosDto(), new Long(22222));

		EspecificacaoAssociacaoAgendamentoDTO especificacaoAssociacaoAgendamentoDto = new EspecificacaoAssociacaoAgendamentoDTO();
		especificacaoAssociacaoAgendamentoDto.setEsperaDto(esperaDto);
		especificacaoAssociacaoAgendamentoDto.setAgendamentoDto(agendamentoDto);

		servicoSisLaraServerRmi.editarEsperaEAssociarAgendamento(especificacaoAssociacaoAgendamentoDto,
				resultadoAutorizacaoDto.getToken());
		Registro.obterAutomatizadorPendencia().atualizarPendencias();

		// Evita que regra de sequencia de faltas seja acionada.
		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto
				.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoServicoSocialDTO());
		atendimentoIndividualDto.setModuloDto(ContextoModulo.construirModuloAvaliacaoETriagemDTO());
		atendimentoIndividualDto.getUsuarioDto().setId(prontuario);
		atendimentoIndividualDto.setData("01/01/2016");
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("09:00", "11:00"));
		atendimentoIndividualDto.setAtendimentosProfissionalDto(
				Arrays.asList(ContextoAtendimentoProfissional.construirAtendimentoProfissional1000DTOComAT()));
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("AT"));
		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto,
				resultadoAutorizacaoDto.getToken());

		atendimentoIndividualDto.setData("28/02/2012");
		atendimentoIndividualDto.setHorarioDto(new HorarioDTO("09:00", "11:00"));
		atendimentoIndividualDto.setAtendimentosProfissionalDto(
				Arrays.asList(ContextoAtendimentoProfissional.construirAtendimentoProfissional1000DTOComAT()));
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));
		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto,
				resultadoAutorizacaoDto.getToken());

		ResultadoListagemEsperaDTO resultadoSegundaListagemEsperaDto = servicoSisLaraServerRmi
				.obterListagemEspera(especificacaoPesquisaEsperaDTO);

		Assert.assertEquals(resultadoPrimeiraListagemEsperaDto.getObjetosDto().size(), 1);
		Assert.assertEquals(resultadoPrimeiraListagemEsperaDto.getObjetosDto().get(0).getObs(),
				"Incluído automaticamente por excesso de faltas não justificadas.");
		Assert.assertEquals(resultadoSegundaListagemEsperaDto.getObjetosDto().size(), 1);
		Assert.assertEquals(resultadoSegundaListagemEsperaDto.getObjetosDto().get(0).getObs(),
				"Incluído automaticamente em decorrência de falta no atendimento individual de avaliação e triagem agendado por causa de excesso de faltas.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_edicao_de_atendimento_individual_de_conta_acesso_nao_vinculado_aos_profissionais_do_atendimento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutorizacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaShivas());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();

		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualDTO = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoAutorizacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoAtendimentoIndividualDTO.obterMensagens().trim(),
				"Somente os profissionais vinculados ao atendimento podem alterar o registro.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_edicao_de_atendimento_grupo_de_conta_acesso_nao_vinculado_aos_profissionais_do_atendimento()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		String descricao = "OPA";
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));
		atendimentoGrupoDto.setDescricao(descricao);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAtualizacaoAtendimentoGrupo.sucesso());
		Assert.assertEquals(resultadoAtualizacaoAtendimentoGrupo.obterMensagens().trim(),
				"Somente os profissionais vinculados ao atendimento podem alterar o registro.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_permite_edicao_de_atendimento_grupo_de_conta_acesso_nao_vinculado_aos_profissionais_do_atendimento_porem_com_profissional_equivalente()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVVitalino());

		String descricao = "OPA";
		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("G02-1");

		AtendimentoGrupoDTO atendimentoGrupoDto = obterAtendimentoGrupoDto(resultadoListagemGrupo.getObjetosDto(),
				new Long(12222), new Long(11111), new Long(11111));
		atendimentoGrupoDto.setDescricao(descricao);

		ResultadoEdicaoAtendimentoGrupoDTO resultadoAtualizacaoAtendimentoGrupo = servicoSisLaraServerRmi
				.editarAtendimentoGrupo(atendimentoGrupoDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoAtualizacaoAtendimentoGrupo.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_de_primeira_vez_duplicado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();
		atendimentoIndividualDto.setData("01/01/2000");
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO(Status.PRIMEIRA_VEZ.toString()));
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("FU"));

		servicoSisLaraServerRmi.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		atendimentoIndividualDto.setData("02/01/2000");
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("AT"));
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualADto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		atendimentoIndividualDto.setData("03/01/2000");
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("AT"));
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualBDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualADto.sucesso());
		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualBDto.sucesso());
		Assert.assertEquals(resultadoEdicaoAtendimentoIndividualBDto.obterMensagens().trim(),
				"Atendimento individual da primeira vez já foi efetuado.");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_de_primeira_vez_duplicado_em_avaliacao_de_oftalmologia_e_acompanhamento_de_oftalmologia() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();

		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOftalmologia());
		atendimentoIndividualDto.setData("02/01/2000");
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("AT"));
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO(Status.PRIMEIRA_VEZ.toString()));
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualADto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAcompanhamentoServicoOftalmologia());
		atendimentoIndividualDto.setData("03/01/2000");
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualBDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualADto.sucesso());
		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualBDto.sucesso());
		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualBDto.obterMensagens().contains("Atendimento individual da primeira vez já foi efetuado."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_atendimento_individual_de_primeira_vez_duplicado_em_avaliacao_de_ortoptica_e_acompanhamento_de_ortoptica() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		AtendimentoIndividualDTO atendimentoIndividualDto = ContextoAtendimentoIndividual
				.fabricarDtoComTodosOsDadosUsuario();

		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAcompanhamentoServicoOrtoptica());
		atendimentoIndividualDto.setData("02/01/2000");
		atendimentoIndividualDto.getInformacaoAtendimentoDto().setFrequenciaDto(new FrequenciaDTO("AT"));
		atendimentoIndividualDto.setPrimeiraVezOuRetornoDto(new StatusDTO(Status.PRIMEIRA_VEZ.toString()));
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualADto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		atendimentoIndividualDto.setDescricaoTipoAtendimentoDto(ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTOAvaliacaoServicoOrtoptica());
		atendimentoIndividualDto.setData("03/01/2000");
		ResultadoEdicaoAtendimentoIndividualDTO resultadoEdicaoAtendimentoIndividualBDto = servicoSisLaraServerRmi
				.editarAtendimentoIndividual(atendimentoIndividualDto, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualADto.sucesso());
		Assert.assertFalse(resultadoEdicaoAtendimentoIndividualBDto.sucesso());
		Assert.assertTrue(resultadoEdicaoAtendimentoIndividualBDto.obterMensagens().contains("Atendimento individual da primeira vez já foi efetuado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_inclusao_de_status_invalido_em_grupo_cto_de_reuniao_de_integracao()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(66666));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(99999));
		ModuloUsuarioDTO moduloUsuarioDTO = ContextoModuloUsuario.fabricarModuloUsuarioDTO();
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.DESISTENTE.toString()));
		moduloPeriodoDto.setModulosUsuariosDto(Arrays.asList(moduloUsuarioDTO));

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertEquals(resultadoEdicaoModuloPeriodoDTO.obterMensagens().trim(),
				"Não é possível existir um usuário com status diferente de INTEGRADO, REMOVIDO ou DESLIGADO na Reunião de Integração.");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_inclusao_de_usuario_e_alteracao_de_status_e_data_ocorrencia_em_grupo_grupo_cto_de_reuniao_de_integracao_apartir_de_conta_acesso_sem_autorizacao()
			throws RemoteException {
		servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
				StatusRelacaoComModulo.PROVISORIO, "G06-1", new Long(14444), new Long(77777), new Long(44444), "SS-1",
				new Long(66666), new Long(99999), new Long(12222));

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(66666));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(99999));
		ModuloUsuarioDTO moduloUsuarioDTO = obterPeloIdUsuario(moduloPeriodoDto,
				ContextoModuloUsuario.fabricarModuloUsuarioDTO().getUsuarioDto());
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.REMOVIDO.toString()));
		moduloUsuarioDTO.setDataOcorrencia("31/12/2015");

		ModuloUsuarioDTO moduloUsuarioAAdicionarDTO = ContextoModuloUsuario.fabricarModuloUsuarioAlternativoDTO();
		moduloPeriodoDto.getModulosUsuariosDto().add(moduloUsuarioAAdicionarDTO);

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());

		Assert.assertFalse(resultadoEdicaoModuloPeriodoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.obterMensagens()
				.contains("Somente profissionais do Serviço Social podem alterar o status e a data de ocorrencia."));
		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.obterMensagens()
				.contains("Somente profissionais do Serviço Social podem incluir um usuário manualmente."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_inclusao_de_usuario_e_alteracao_de_status_e_data_ocorrencia_em_grupo_grupo_cto_de_reuniao_de_integracao_apartir_de_conta_acesso_com_autorizacao()
			throws RemoteException {
		servico_atualiza_status_de_usuario_e_inclui_em_grupo_com_modulo_de_reuniao_de_integracao_com_sucesso(
				StatusRelacaoComModulo.PROVISORIO, "G06-1", new Long(14444), new Long(77777), new Long(44444), "SS-1",
				new Long(66666), new Long(99999), new Long(12222));

		ResultadoAutenticacaoDTO resultadoAutenticacaoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValidaVeraPereira());

		ResultadoListagemGrupoDTO resultadoListagemGrupo = servicoSisLaraServerRmi.obterListagemGrupoAtivo("SS-1");

		GrupoDTO grupoDto = (GrupoDTO) obterDaRelacaoPorId(resultadoListagemGrupo.getObjetosDto(), new Long(66666));
		ModuloPeriodoDTO moduloPeriodoDto = (ModuloPeriodoDTO) obterDaRelacaoPorId(grupoDto.getModulosPeriodosDto(),
				new Long(99999));
		ModuloUsuarioDTO moduloUsuarioDTO = obterPeloIdUsuario(moduloPeriodoDto,
				ContextoModuloUsuario.fabricarModuloUsuarioDTO().getUsuarioDto());
		moduloUsuarioDTO.setStatusDto(new StatusRelacaoComModuloDTO(StatusRelacaoComModulo.REMOVIDO.toString()));
		moduloUsuarioDTO.setDataOcorrencia("31/12/2015");

		ModuloUsuarioDTO moduloUsuarioAAdicionarDTO = ContextoModuloUsuario.fabricarModuloUsuarioAlternativoDTO();
		moduloPeriodoDto.getModulosUsuariosDto().add(moduloUsuarioAAdicionarDTO);

		ResultadoEdicaoModuloPeriodoDTO resultadoEdicaoModuloPeriodoDTO = servicoSisLaraServerRmi
				.editarModuloPeriodo(moduloPeriodoDto, resultadoAutenticacaoDto.getToken());

		Assert.assertTrue(resultadoEdicaoModuloPeriodoDTO.sucesso());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_se_selos_com_pre_cadastro_sem_cpf()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.getPreCadastrosDto().getInformacaoEssencialDto().setCpf(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("O solicitante não possui o CPF cadastrado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_se_selos_com_usuario_sem_cpf() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(null);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(ContextoUsuario.construirUsuarioDTO());
		especificacaoGeracaoDemandaDTO.getUsuariosDto().getInformacaoEssencialDto().setCpf(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("O solicitante não possui o CPF cadastrado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_de_selos_com_usuario_com_data_nascimento_menor_5_anos()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDTO.getPreCadastrosDto().getInformacaoEssencialDto().setCpf("71894810287");
		especificacaoGeracaoDemandaDTO.getPreCadastrosDto().getInformacaoEssencialDto().setDataNascimento(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("Solicitante não possui data de nascimento."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_de_selos_com_mais_de_um_recurso()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDTO.setRecursosDto(
				Arrays.asList(ContextoRecurso.construirRecursoAlternativoDTO(), ContextoRecurso.construirRecursoDTO()));
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("Não é possível incluir mais de um recurso na cartela de selos."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_de_selos_sem_documentos_obrigatorios()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDTO.setRecursosDto(Arrays.asList());
		PreCadastroDTO preCadastroDto = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDto.getInformacaoEssencialDto()
				.setDataNascimento(DataHoraUtils.formatarData(Calendar.getInstance()));
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens().contains("Insira um Recurso."));
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens()
				.contains("Insira uma autodeclaração ou indicação de usuário de sistema braille."));
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("Solicitante não possui idade mínima de 5 anos."));
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("Insira uma cópia da declaração de matrícula."));
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens().contains("Insira uma foto recente."));
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens().contains("Insira uma cópia do CPF e RG."));
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("Insira uma cópia do comprovante de endereço."));
		Assert.assertTrue(
				resultadoGeracaoDemanda.obterMensagens().contains("Insira uma cópia do histórico do solicitante."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_de_selos_sem_usuario_ou_pre_cadastro()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDados();
		especificacaoGeracaoDemandaDTO.setRecursosDto(Arrays.asList());
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(null);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens().contains("Insira um Usuário ou Pré Cadastro."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_prorrogacao_demanda_cartela_de_selos_por_falta_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ResultadoProrrogacaoCartelaDeSelosDTO resultadoProrrogacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.prorrogar(demandaDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoProrrogacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoProrrogacaoCartelaDeSelosDto.obterMensagens()
				.contains("Você não possui autorização para realizar a operação."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_prorrogacao_demanda_normal() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setCpf("90353388122");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(13333));

		ResultadoProrrogacaoCartelaDeSelosDTO resultadoProrrogacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.prorrogar(demandaDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoProrrogacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoProrrogacaoCartelaDeSelosDto.obterMensagens()
				.contains("Somente projeto cartela de selos pode ser prorrogado."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_prorrogacao_demanda_cartela_de_selos_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ResultadoProrrogacaoCartelaDeSelosDTO resultadoProrrogacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.prorrogar(demandaDto, resultadoDto.getToken());

		DemandaDTO demandaProrrogadaDto = (DemandaDTO) resultadoProrrogacaoCartelaDeSelosDto.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoProrrogacaoCartelaDeSelosDto.sucesso());
		Assert.assertEquals(demandaProrrogadaDto.getDataPrazoCaptacao(), "30/04/2000");
		Assert.assertEquals(demandaProrrogadaDto.getStatusDemandaDto().toString(), StatusDemanda.PRORROGADA.toString());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_disponibilizacao_e_entrega_demanda_nao_cartela_de_selos_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Registro.obterAutomatizadorTarefas().executar();
		
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		StatusDemandaDTO statusDemandaDisponivelDTO = new StatusDemandaDTO(StatusDemanda.DISPONIVEL.toString());
		StatusDemandaDTO statusDemandaEntregaDTO = new StatusDemandaDTO(StatusDemanda.ENTREGUE.toString());
				
		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(16666));

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDisponibilizadaDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDisponivelDTO, null, null, resultadoDto.getToken());

		DemandaDTO demandaAposDisponibilizacaoDto = (DemandaDTO) resultadoEdicaoDemandaDisponibilizadaDto.obterObjetoDtoEditado();
		
		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaEntregaDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaEntregaDTO, null, null, resultadoDto.getToken());

		DemandaDTO demandaAposEntregaDto = (DemandaDTO) resultadoEdicaoDemandaEntregaDto.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoEdicaoDemandaDisponibilizadaDto.sucesso());
		Assert.assertTrue(resultadoEdicaoDemandaEntregaDto.sucesso());
		Assert.assertTrue(demandaAposDisponibilizacaoDto.getStatusDemandaDto().equals(statusDemandaDisponivelDTO));
		Assert.assertTrue(demandaAposEntregaDto.getStatusDemandaDto().equals(statusDemandaEntregaDTO));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_entrega_demanda_nao_cartela_de_selos_devido_marcacao_indevida_de_motivo_de_cancelamento() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Registro.obterAutomatizadorTarefas().executar();
		
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		StatusDemandaDTO statusDemandaDTO = new StatusDemandaDTO(StatusDemanda.DISPONIVEL.toString());
		
		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(16666));

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDTO, ContextoMotivoCancelamento.construirMotivoCancelamentoDto(), null, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoDemandaDto.sucesso());
		Assert.assertTrue(resultadoEdicaoDemandaDto.obterMensagens().contains("O motivo de cancelamento só é permitido em status CANCELADA."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_entrega_demanda_nao_cartela_de_selos_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		StatusDemandaDTO statusDemandaDTO = new StatusDemandaDTO(StatusDemanda.ENTREGUE.toString());
		
		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDTO, null, null, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoDemandaDto.sucesso());
		Assert.assertTrue(resultadoEdicaoDemandaDto.obterMensagens().contains("Não é possível realizar a mudança de status."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_demanda_nao_cartela_de_selos_devido_falta_de_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		StatusDemandaDTO statusDemandaDTO = new StatusDemandaDTO(StatusDemanda.CANCELADA.toString());
		
		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDTO, null, null, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoDemandaDto.sucesso());
		Assert.assertTrue(resultadoEdicaoDemandaDto.obterMensagens().contains("Não é possível realizar a mudança de status."));
	}
	

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_demanda_nao_cartela_de_selos_devido_falta_de_motivo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		StatusDemandaDTO statusDemandaDTO = new StatusDemandaDTO(StatusDemanda.CANCELADA.toString());
		
		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDTO, null, null, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoDemandaDto.sucesso());
		Assert.assertTrue(resultadoEdicaoDemandaDto.obterMensagens().contains("Insira um motivo de cancelamento."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_cancelamento_duplicado_demanda_nao_cartela_de_selos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		StatusDemandaDTO statusDemandaDTO = new StatusDemandaDTO(StatusDemanda.CANCELADA.toString());
		
		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaAposCancelamentoDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDTO, ContextoMotivoCancelamento.construirMotivoCancelamentoDto(), null, resultadoDto.getToken());

		ResultadoEdicaoDemandaDTO resultadoEdicaoDemandaAposCancalamentoDuplicadoDto = servicoSisLaraServerRmi.mudarStatusDemanda(demandaDto,
				statusDemandaDTO, ContextoMotivoCancelamento.construirMotivoCancelamentoDto(), null, resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoDemandaAposCancelamentoDto.sucesso());
		Assert.assertFalse(resultadoEdicaoDemandaAposCancalamentoDuplicadoDto.sucesso());
		Assert.assertTrue(resultadoEdicaoDemandaAposCancalamentoDuplicadoDto.obterMensagens().contains("Não é possível realizar a mudança de status."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_geracao_de_demanda_cartela_de_selos_usando_recurso_inabilitado()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDTO = ContextoEspecificacaoGeracaoDemanda
				.fabricarDemandaComTodosOsDadosAlternativo();
		especificacaoGeracaoDemandaDTO.setRecursosDto(Arrays.asList(ContextoRecurso.construirRecursoAlternativoDTO()));

		String conteudoArquivo = "Conteúdo do arquivo.";

		PreCadastroDTO preCadastroDto = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDto.getInformacaoEssencialDto().setDataNascimento("27/07/1982");
		especificacaoGeracaoDemandaDTO.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDTO.setUsuariosDto(null);
		especificacaoGeracaoDemandaDTO.setCartelaDeSelos(true);
		especificacaoGeracaoDemandaDTO.setDocumentosSolicitacaoDocacaoDto(obterTodosDocumentos(conteudoArquivo));

		ResultadoGeracaoDemandaDTO resultadoGeracaoDemanda = servicoSisLaraServerRmi
				.gerarDemanda(especificacaoGeracaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoGeracaoDemanda.sucesso());
		Assert.assertTrue(resultadoGeracaoDemanda.obterMensagens()
				.contains("Não é possível usar um recurso não disponível para cartela de selos."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_captacao_de_demanda_cartela_de_selos_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboDto());

		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		DemandaDTO demandaCaptadaDto = (DemandaDTO) resultadoCaptacaoCartelaDeSelosDto.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertEquals(demandaCaptadaDto.getValorCartela(), "2500,00");
		Assert.assertEquals(demandaCaptadaDto.getValorTotalCaptado(), "1000,00");
		Assert.assertEquals(demandaCaptadaDto.getValorSaldo(), "1500,00");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_de_selos_com_valor_total_captado_maior_que_necessario_para_producao_do_recurso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("1251,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboAlternativoDto());

		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("O valor total captado não pode ser maior que o valor da cartela."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_de_selos_por_falta_de_saldo_no_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboDto());

		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("Valor total do recibo insuficiente para captação."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_sem_dados_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("Insira um recibo."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_com_dados_recibo_invalidos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		ReciboDTO reciboDTO = ContextoRecibo.fabricarReciboDto();
		reciboDTO.setId(null);
		reciboDTO.setValorTotalRecibo(null);
		especificacaoCaptacaoDemandaDTO.setReciboDto(reciboDTO);
		
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("Insira um número de recibo."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obter_valor_total_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		ReciboDTO reciboDto = ContextoRecibo.fabricarReciboDto();

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(reciboDto);

		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		
		ReciboDTO reciboDtoObtido = servicoSisLaraServerRmi.obterReciboDto(reciboDto.getId().toString());

		Assert.assertEquals(reciboDtoObtido.getId(), reciboDto.getId());
		Assert.assertEquals(reciboDtoObtido.getValorTotalRecibo(), reciboDto.getValorTotalRecibo());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_captacao_de_demanda_cartela_de_selos_e_possibilita_reserva_automatica() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaAntesReservaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaAntesReservaDto);
		especificacaoCaptacaoDemandaDTO.setValor("1250,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboAlternativoDto());

		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Registro.obterAutomatizadorTarefas().executar();
		
		ResultadoListagemDemandaDTO resultadoListagemDemandaAposReserva = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);
		DemandaDTO demandaDtoAposReserva = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemandaAposReserva.getObjetosDto(),
				new Long(12222));
		
		Assert.assertEquals(demandaAntesReservaDto.getStatusDemandaDto(), new StatusDemandaDTO(StatusDemanda.AGUARDANDO.toString()));
		Assert.assertEquals(demandaDtoAposReserva.getStatusDemandaDto(), new StatusDemandaDTO(StatusDemanda.RESERVADO.toString()));
		Assert.assertEquals(demandaDtoAposReserva.getValorCartela(), "2500,00");
		Assert.assertEquals(demandaDtoAposReserva.getValorTotalCaptado(), "2500,00");
		Assert.assertEquals(demandaDtoAposReserva.getValorSaldo(), "0,00");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_captacao_de_demanda_cartela_de_selos_prorrogada_e_possibilita_reserva_automatica() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setProntuario("12222");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);
		
		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(12222));
		
		servicoSisLaraServerRmi.prorrogar(demandaDto, resultadoDto.getToken());
		
		ResultadoListagemDemandaDTO resultadoListagemDemandaAntesReserva = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);
		
		DemandaDTO demandaDtoAntesReserva = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemandaAntesReserva.getObjetosDto(),
				new Long(12222));
		
		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDtoAntesReserva);
		especificacaoCaptacaoDemandaDTO.setValor("1250,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboAlternativoDto());

		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());
		servicoSisLaraServerRmi.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Registro.obterAutomatizadorTarefas().executar();
		
		ResultadoListagemDemandaDTO resultadoListagemDemandaAposReserva = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);
		DemandaDTO demandaDtoAposReserva = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemandaAposReserva.getObjetosDto(),
				new Long(12222));
		
		Assert.assertEquals(demandaDtoAntesReserva.getStatusDemandaDto(), new StatusDemandaDTO(StatusDemanda.PRORROGADA.toString()));
		Assert.assertEquals(demandaDtoAposReserva.getStatusDemandaDto(), new StatusDemandaDTO(StatusDemanda.RESERVADO.toString()));
		Assert.assertEquals(demandaDtoAposReserva.getValorCartela(), "2500,00");
		Assert.assertEquals(demandaDtoAposReserva.getValorTotalCaptado(), "2500,00");
		Assert.assertEquals(demandaDtoAposReserva.getValorSaldo(), "0,00");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_pesquisa_de_demanda_sem_dados() throws RemoteException {
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		Assert.assertFalse(resultadoListagemDemanda.sucesso());
		Assert.assertTrue(resultadoListagemDemanda.obterMensagens().contains("Insira um parâmetro para pesquisa."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_de_selos_sem_dados_obrigatorios()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();

		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("Insira uma demanda."));
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("Insira um valor da captação válido."));
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens().contains("Insira um recibo."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_de_selos_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();

		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens()
				.contains("Você não possui autorização para realizar a operação."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_nao_cartela_de_selos() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setCpf("90353388122");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(13333));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboAlternativoDto());
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens()
				.contains("Somente o projeto carteda de selos permite captação."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_efetua_captacao_de_demanda_cartela_de_selos_com_status_diferente_de_aguardando_ou_prorrogada() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi
				.autenticarLogin(construirCredencialDtoValida());
		
		EspecificacaoPesquisaDemandaDTO especificacaoPesquisaDemandaDTO = new EspecificacaoPesquisaDemandaDTO();
		especificacaoPesquisaDemandaDTO.setCpf("72880474310");

		ResultadoListagemDemandaDTO resultadoListagemDemanda = servicoSisLaraServerRmi
				.obterListagemDemanda(especificacaoPesquisaDemandaDTO);

		DemandaDTO demandaDto = (DemandaDTO) obterDaRelacaoPorId(resultadoListagemDemanda.getObjetosDto(),
				new Long(18888));

		EspecificacaoCaptacaoDemandaDTO especificacaoCaptacaoDemandaDTO = new EspecificacaoCaptacaoDemandaDTO();
		especificacaoCaptacaoDemandaDTO.setDemandaDto(demandaDto);
		especificacaoCaptacaoDemandaDTO.setValor("500,00");
		especificacaoCaptacaoDemandaDTO.setReciboDto(ContextoRecibo.fabricarReciboAlternativoDto());
		ResultadoCaptacaoCartelaDeSelosDTO resultadoCaptacaoCartelaDeSelosDto = servicoSisLaraServerRmi
				.captar(especificacaoCaptacaoDemandaDTO, resultadoDto.getToken());

		Assert.assertFalse(resultadoCaptacaoCartelaDeSelosDto.sucesso());
		Assert.assertTrue(resultadoCaptacaoCartelaDeSelosDto.obterMensagens()
				.contains("Não é possível efetuar uma captação em demandas com status diferente de AGUARDANDO ou PRORROGADA."));
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_localizacao_recibo_mais_recente_por_cpf_ou_cnpj_com_sucesso()
			throws RemoteException {
		ResultadoReciboDTO resultadoRecibo = servicoSisLaraServerRmi
				.obterReciboMaisRecentePorCpfCnpj("71894810287");
		ReciboDTO reciboDTO = (ReciboDTO) resultadoRecibo.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoRecibo.sucesso());
		Assert.assertEquals(reciboDTO.getNome(), "Paulo Augusto Bandeira dos Santos");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_localizacao_todos_recibo_por_cpf_ou_cnpj_com_sucesso()
			throws RemoteException {
		EspecificacaoPesquisaReciboDTO especificacaoPesquisaReciboDTO = new EspecificacaoPesquisaReciboDTO();
		especificacaoPesquisaReciboDTO.adicionarParametro(ChavePesquisaDTO.CPF_CNPJ, "71894810287");
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi.pesquisarReciboPor(especificacaoPesquisaReciboDTO);
		
		Assert.assertTrue(resultadoListagemRecibo.sucesso());
		Assert.assertEquals(resultadoListagemRecibo.getObjetosDto().size(), 2);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_efetua_localizacao_todos_recibo_por_filial_com_sucesso()
			throws RemoteException {
		EspecificacaoPesquisaReciboDTO especificacaoPesquisaReciboDTO = new EspecificacaoPesquisaReciboDTO();
		especificacaoPesquisaReciboDTO.adicionarParametro(ChavePesquisaDTO.FILIAL, "7");
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi.pesquisarReciboPor(especificacaoPesquisaReciboDTO);
		
		Assert.assertTrue(resultadoListagemRecibo.sucesso());
		Assert.assertEquals(resultadoListagemRecibo.getObjetosDto().size(), 2);
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_obtem_listagem_nome_documento() throws RemoteException {
		ResultadoListagemNomeDocumentoDTO resultadoNomeDocumento = servicoSisLaraServerRmi.obterListagemNomeDocumento();

		Assert.assertTrue(resultadoNomeDocumento.sucesso());
		Assert.assertEquals(resultadoNomeDocumento.getObjetosDto().size(), 9);
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_verifica_se_valor_total_recursos_reservados_e_maior_que_total_disponivel_para_produtos()
			throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		Registro.obterAutomatizadorTarefas().executar();
		
		ResultadoListagemProjetoDTO resultadoListagemProjetos = servicoSisLaraServerRmi
				.obterListagemProjetoAtivos();
		ProjetoDTO projetoDto = (ProjetoDTO) obterDaRelacaoPorId(
				resultadoListagemProjetos.getObjetosDto(), new Long(1222));
		projetoDto.setLoteRecursoDto(Arrays.asList());

		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDTO = servicoSisLaraServerRmi
				.editarProjeto(projetoDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoProjetoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoProjetoDTO.obterMensagens()
				.contains("Não é possível haver um valor total de recursos Maquina Braille Laramara reservados maior que o total disponível."));
		Assert.assertTrue(resultadoEdicaoProjetoDTO.obterMensagens()
				.contains("Não é possível haver um valor total de recursos Bengala reservados maior que o total disponível."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_incluir_projeto_com_sucesso() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDTO = servicoSisLaraServerRmi
				.editarProjeto(ContextoProjeto.construirProjetoDTO(), resultadoDto.getToken());

		Assert.assertTrue(resultadoEdicaoProjetoDTO.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_incluir_projeto_por_falta_de_permissao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoSemPermissao());

		ResultadoEdicaoProjetoDTO resultadoEdicaoProjetoDTO = servicoSisLaraServerRmi
				.editarProjeto(ContextoProjeto.construirProjetoDTO(), resultadoDto.getToken());

		Assert.assertFalse(resultadoEdicaoProjetoDTO.sucesso());
		Assert.assertTrue(resultadoEdicaoProjetoDTO.obterMensagens().contains("Você não possui autorização para realizar a operação."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_inclui_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ReciboDTO reciboDto = ContextoRecibo.fabricarReciboDto();
		reciboDto.setId(null);

		ResultadoEdicaoReciboDTO resultadoInclusaoRecibo = servicoSisLaraServerRmi
				.editarRecibo(reciboDto, resultadoDto.getToken());

		ReciboDTO reciboIncluidoDto = (ReciboDTO) resultadoInclusaoRecibo
				.obterObjetoDtoEditado();

		Assert.assertTrue(resultadoInclusaoRecibo.sucesso());
		Assert.assertNotNull(reciboIncluidoDto.getId());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_recibo_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoSemPermissao());

		ReciboDTO reciboDto = ContextoRecibo.fabricarReciboDto();
		reciboDto.setId(null);

		ResultadoEdicaoReciboDTO resultadoInclusaoRecibo = servicoSisLaraServerRmi
				.editarRecibo(reciboDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoRecibo.sucesso());
		Assert.assertTrue(resultadoInclusaoRecibo.obterMensagens()
				.contains("Você não possui autorização para realizar a operação."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_inclui_recibo_de_doacao_para_filial_diferente_de_1() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValidaAdailza());

		ReciboDTO reciboDto = ContextoRecibo.fabricarReciboDto();
		reciboDto.setId(null);

		ResultadoEdicaoReciboDTO resultadoInclusaoRecibo = servicoSisLaraServerRmi
				.editarRecibo(reciboDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoInclusaoRecibo.sucesso());
		Assert.assertTrue(resultadoInclusaoRecibo.obterMensagens()
				.contains("Não é possível gerar o recibo para a filial informada."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_edicao_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_RECIBOS, "");
		
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);
		
		ReciboDTO reciboDto = (ReciboDTO) obterDaRelacaoPorId(resultadoListagemRecibo.getObjetosDto(), new Long(9999));
		
		ResultadoEdicaoReciboDTO resultadoAlteracaoRecibo = servicoSisLaraServerRmi
				.editarRecibo(reciboDto, resultadoDto.getToken());

		Assert.assertFalse(resultadoAlteracaoRecibo.sucesso());
		Assert.assertTrue(resultadoAlteracaoRecibo.obterMensagens().contains("Não é possível alterar o recibo."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_permite_cancelamento_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_RECIBOS, "");
		
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);
		
		ReciboDTO reciboDto = (ReciboDTO) obterDaRelacaoPorId(resultadoListagemRecibo.getObjetosDto(), new Long(9999));
		
		ResultadoEdicaoReciboDTO resultadoCancelamentoRecibo = servicoSisLaraServerRmi
				.cancelarRecibo(reciboDto.getId(), "Motivo de cancelamento.", resultadoDto.getToken());

		Assert.assertTrue(resultadoCancelamentoRecibo.sucesso());
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_cancelamento_recibo_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoSemPermissao());

		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_RECIBOS, "");
		
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);
		
		ReciboDTO reciboDto = (ReciboDTO) obterDaRelacaoPorId(resultadoListagemRecibo.getObjetosDto(), new Long(9999));
		
		ResultadoEdicaoReciboDTO resultadoCancelamentoRecibo = servicoSisLaraServerRmi
				.cancelarRecibo(reciboDto.getId(), "Motivo de cancelamento.", resultadoDto.getToken());

		Assert.assertFalse(resultadoCancelamentoRecibo.sucesso());
		Assert.assertTrue(resultadoCancelamentoRecibo.obterMensagens().contains("Você não possui autorização para realizar a operação."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_cancelamento_de_recibo_ja_cancelado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_RECIBOS, "");
		
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);
		
		ReciboDTO reciboDto = (ReciboDTO) obterDaRelacaoPorId(resultadoListagemRecibo.getObjetosDto(), new Long(8888));
		
		ResultadoEdicaoReciboDTO resultadoCancelamentoRecibo = servicoSisLaraServerRmi
				.cancelarRecibo(reciboDto.getId(), "Motivo de cancelamento.", resultadoDto.getToken());

		Assert.assertFalse(resultadoCancelamentoRecibo.sucesso());
		Assert.assertTrue(resultadoCancelamentoRecibo.obterMensagens().contains("Não é possível cancelar o recibo novamente."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_permite_cancelamento_recibo_sem_motivo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		EspecificacaoPesquisaReciboDTO especificacao = new EspecificacaoPesquisaReciboDTO();
		especificacao.adicionarParametro(ChavePesquisaDTO.TODOS_RECIBOS, "");
		
		ResultadoListagemReciboDTO resultadoListagemRecibo = servicoSisLaraServerRmi
				.pesquisarReciboPor(especificacao);
		
		ReciboDTO reciboDto = (ReciboDTO) obterDaRelacaoPorId(resultadoListagemRecibo.getObjetosDto(), new Long(9999));
		
		ResultadoEdicaoReciboDTO resultadoCancelamentoRecibo = servicoSisLaraServerRmi
				.cancelarRecibo(reciboDto.getId(), "", resultadoDto.getToken());
		
		Assert.assertFalse(resultadoCancelamentoRecibo.sucesso());
		Assert.assertTrue(resultadoCancelamentoRecibo.obterMensagens().contains("Insira um motivo de cancelamento."));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_de_recibo() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioRecibo = servicoSisLaraServerRmi.obterRelatorioRecibo(new Long(9999), resultadoDto.getToken());
		
		Assert.assertTrue(relatorioRecibo.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioRecibo.obterConteudo(), new byte[33479]));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_gera_relatorio_pdf_vazio_de_recibo_cancelado() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoValida());

		ArquivoDTO relatorioRecibo = servicoSisLaraServerRmi.obterRelatorioRecibo(new Long(8888), resultadoDto.getToken());
		
		Assert.assertTrue(relatorioRecibo.sucesso());
		Assert.assertTrue(variacaoMaximaDeBytes(relatorioRecibo.obterConteudo(), new byte[919]));
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void servico_nao_gera_relatorio_pdf_sem_autorizacao() throws RemoteException {
		ResultadoAutenticacaoDTO resultadoDto = servicoSisLaraServerRmi.autenticarLogin(construirCredencialDtoSemPermissao());

		ArquivoDTO relatorioRecibo = servicoSisLaraServerRmi.obterRelatorioRecibo(new Long(8888), resultadoDto.getToken());
		
		Assert.assertFalse(relatorioRecibo.sucesso());
		Assert.assertTrue(relatorioRecibo.obterMensagens().contains("Você não possui autorização para realizar a operação."));
	}
}
package br.laramara.ti.sislaraserver.fachadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DiaSemanaEHorarioDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoGeracaoAtendimentosDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.EspecificacaoPesquisaGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloRelacaoBaseDTO;
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
import br.laramara.ti.sislaracommons.dtos.pendencia.PendenciaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.espera.Resultado;
import br.laramara.ti.sislaraserver.dominio.grupo.AutomatizadorModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.AutomatizadorStatusGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.DiaSemana;
import br.laramara.ti.sislaraserver.dominio.grupo.Frequencia;
import br.laramara.ti.sislaraserver.dominio.grupo.GerenciadorReuniaoIntegracao;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.StatusRelacaoComModulo;
import br.laramara.ti.sislaraserver.dominio.pendencia.AutomatizadorPendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.dominio.usuario.AutomatizadorStatusUsuario;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoGrupo;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoUsuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaDiaSemana;
import br.laramara.ti.sislaraserver.fabricas.FabricaDiaSemanaEHorario;
import br.laramara.ti.sislaraserver.fabricas.FabricaFrequencia;
import br.laramara.ti.sislaraserver.fabricas.FabricaGrupo;
import br.laramara.ti.sislaraserver.fabricas.FabricaLocalAtendimento;
import br.laramara.ti.sislaraserver.fabricas.FabricaLoteRecurso;
import br.laramara.ti.sislaraserver.fabricas.FabricaModuloPeriodo;
import br.laramara.ti.sislaraserver.fabricas.FabricaProfissional;
import br.laramara.ti.sislaraserver.fabricas.FabricaProgramacao;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecurso;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusRelacaoComModulo;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipificacaoServico;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoAtendimento;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoGrupoEdicao;
import br.laramara.ti.sislaraserver.fachadas.validadores.Validador;
import br.laramara.ti.sislaraserver.repositorios.QualificadorRepositorioProfissionalSistema;
import br.laramara.ti.sislaraserver.repositorios.RepositorioArquivo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioLocalAtendimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioModuloPeriodo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProfissional;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecurso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipificacaoServico;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;

@Component
public class FachadaGrupo extends Fachada {

	@Inject
	private RepositorioGrupo repositorioGrupo;
	@Inject
	private RepositorioAtendimentoGrupo repositorioAtendimentoGrupo;
	@Inject
	private RepositorioModuloPeriodo repositorioModuloPeriodo;
	@Inject
	private RepositorioLocalAtendimento repositorioLocalAtendimento;
	@Inject
	private RepositorioTipificacaoServico repositorioTipificacaoServico;
	@Inject
	private RepositorioRecurso repositorioRecurso;
	@Inject
	@QualificadorRepositorioProfissionalSistema
	private RepositorioProfissional repositorioProfissional;
	@Inject
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	@Inject
	private AutomatizadorEspera automatizadorEspera;
	@Inject
	private AutomatizadorStatusUsuario automatizadorStatusUsuario; 
	@Inject
	private AutomatizadorStatusGrupo automatizadorStatusGrupo;
	@Inject
	private AutomatizadorModuloPeriodo automatizadorModuloPeriodo;
	@Inject
	private AutomatizadorPendencia automatizadoPendencia;
	@Inject
	private GerenciadorReuniaoIntegracao gerenciadorReuniaoIntegracao;
	@Inject
	private RepositorioPendencia repositorioPendencia;
	@Inject
	private RepositorioSislara repositorioSislara;
	@Inject
	private FachadaSeguranca fachadaSeguranca;
	@Inject
	private RepositorioEspera repositorioEspera;
	@Inject
	private RepositorioArquivo repositorioArquivo;
	
	public ResultadoListagemDiaSemanaDTO obterListagemDiaSemana() {
		return (ResultadoListagemDiaSemanaDTO) obterListagem(
				Arrays.asList(DiaSemana.values()), new FabricaDiaSemana(),
				"Dias da Semana", new ResultadoListagemDiaSemanaDTO());
	}

	public ResultadoListagemLocalAtendimentoDTO obterListagemLocalAtendimento() {
		return (ResultadoListagemLocalAtendimentoDTO) obterListagem(
				repositorioLocalAtendimento.obterTodos(),
				new FabricaLocalAtendimento(), "Local de Atendimento",
				new ResultadoListagemLocalAtendimentoDTO());
	}

	public ResultadoListagemTipificacaoServicoDTO obterListagemTipificacaoServico() {
		return (ResultadoListagemTipificacaoServicoDTO) obterListagem(
				repositorioTipificacaoServico.obterTodos(),
				new FabricaTipificacaoServico(), "Tipificação do Serviço",
				new ResultadoListagemTipificacaoServicoDTO());
	}

	public ResultadoListagemRecursoDTO obterListagemRecurso() {
		return (ResultadoListagemRecursoDTO) obterListagem(
				repositorioRecurso.obterTodos(), new FabricaRecurso(),
				"Recursos", new ResultadoListagemRecursoDTO());
	}

	public synchronized ResultadoEdicaoGrupoDTO editarGrupo(GrupoDTO grupoDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoGrupoEdicao(new FabricaGrupo(), repositorioGrupo, repositorioPendencia,
				repositorioSislara, repositorioEspera, repositorioTipoAtendimento, grupoDto);
		return (ResultadoEdicaoGrupoDTO) verificarPermissaoEProcessar(operacao,
				Permissao.GRUPO_EDICAO, new ResultadoEdicaoGrupoDTO(), tokenDto);
	}

	public synchronized ResultadoEdicaoAtendimentoGrupoDTO editarAtendimentoGrupo(
			AtendimentoGrupoDTO atendimentoGrupoDto, TokenDTO tokenDto) {
		return editarAtendimentoGrupo(atendimentoGrupoDto, false, tokenDto);
	}

	public synchronized ResultadoEdicaoAtendimentoGrupoDTO editarAtendimentoGrupo(
			AtendimentoGrupoDTO atendimentoGrupoDto, boolean cancelar,
			TokenDTO tokenDto) {
		ResultadoEdicaoAtendimentoGrupoDTO resultadoEdicaoAtendimentoGrupoDto = new ResultadoEdicaoAtendimentoGrupoDTO();
		AtendimentoGrupo atendimentoGrupo = new FabricaAtendimentoGrupo()
				.converterParaDominio(atendimentoGrupoDto);
		atendimentoGrupo.vincularContaAcessoResponsavelPorOperacao(obterContaAcesso(tokenDto));
		atendimentoGrupo.validarObrigatoriedadeETamanhoMaximoDeDados();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.GRUPO_EDICAO)) {
			if (atendimentoGrupo.validado()) {
				if (cancelar) {
					atendimentoGrupo.cancelar();
					logger.info("Solicitação de Cancelamento de Atendimento no "
							+ atendimentoGrupo
							+ " solicitado pela "
							+ contaAcesso);
				}
				AtendimentoGrupoDTO atendimentoGrupoDTO = new FabricaAtendimentoGrupo()
						.converterParaDTO(repositorioAtendimentoGrupo
								.salvar(atendimentoGrupo));
				Resultado resultadoExcessoFaltas = automatizadorEspera
						.processarAtendimentoGrupoInclusaoNaListaDeEsperaPorExecessoDeFaltas(
								atendimentoGrupo,
								contaAcesso);
				automatizadoPendencia.transferirPendenciaDeReuniaoDeIntegracaoSeNecessario(atendimentoGrupo);
				resultadoEdicaoAtendimentoGrupoDto.efetuadoComSucesso(
						atendimentoGrupoDTO,
						resultadoExcessoFaltas.getMensagem());
			} else {
				resultadoEdicaoAtendimentoGrupoDto
						.adicionarErro(atendimentoGrupo.obterDescricaoErros());
			}
		} else {
			resultadoEdicaoAtendimentoGrupoDto
					.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoEdicaoAtendimentoGrupoDto;
	}

	public synchronized ResultadoEdicaoModuloPeriodoDTO editarModuloPeriodo(
			ModuloPeriodoDTO moduloPeriodoDto, TokenDTO tokenDto) {
		ResultadoEdicaoModuloPeriodoDTO resultadoDto = new ResultadoEdicaoModuloPeriodoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		ModuloPeriodo moduloPeriodo = new FabricaModuloPeriodo().converterParaDominio(moduloPeriodoDto,
				repositorioModuloPeriodo.obterPorId(moduloPeriodoDto.getId()));
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodo.getId());
		ModuloPeriodo moduloPeriodoReuniaoIntegracaoOriginal = grupo.obterModuloPeriodoDeReuniaoDeIntegracao();

		moduloPeriodo.validarAutorizacaoAlteracaoDeGruposDeReunisaoDeIntegracao(contaAcesso,
				moduloPeriodoReuniaoIntegracaoOriginal);
		moduloPeriodo.validarExistenciaDeIntegradosNoOSEeCL(grupo);
		moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();
		
		if (contaAcesso.possuiPermissao(Permissao.GRUPO_EDICAO)) {
			if (repositorioGrupo.confirmaVersaoAtualPorIdModuloPeriodo(
					moduloPeriodoDto.getId(), moduloPeriodoDto.getVersao())) {
				if (moduloPeriodo.validado()) {
					List<Usuario> usuariosIntegradosEmOutroGrupoAtivoComDescricaoServicoSocialModuloReuniaoDeIntegracao = gerenciadorReuniaoIntegracao
							.obterUsuariosIntegradosEmGruposAtivosComDescricaoServicoSocialModuloReuniaoDeIntegracao(
									moduloPeriodo);
					if (usuariosIntegradosEmOutroGrupoAtivoComDescricaoServicoSocialModuloReuniaoDeIntegracao
							.isEmpty()) {
						ModuloPeriodo moduloPeriodoSalvo = repositorioModuloPeriodo.salvar(moduloPeriodo);
						automatizadorModuloPeriodo
								.aplicarRegraPropagacaoStatusNaoAcessoOuProvisorioAosOutrosModulosDoGrupo(
										moduloPeriodoSalvo);
						automatizadorModuloPeriodo
								.aplicarRegraDeIntegradoOuProvisorioOuAcessoAosModulosPeriodosDePssicossocialNosGruposDeAtendimentoEspecializadoGlobalOuTranstornoGlobalDoDesenvolvimento(
										moduloPeriodoSalvo);
						automatizadorStatusGrupo
								.efetuarDesligamentosEmTodosOsGruposAtivosDosUsuariosDesligados(moduloPeriodoSalvo);
						automatizadoPendencia
								.cancelarPendenciasPorExcessoDeFaltasDosUsuariosDesligados(moduloPeriodoSalvo);
						automatizadorStatusUsuario.atualizarStatusDoUsuario(moduloPeriodoSalvo);
						gerenciadorReuniaoIntegracao.incluirEmReuniaoDeIntegracaoSeNecessario(moduloPeriodoSalvo);
						resultadoDto.efetuadoComSucesso(new FabricaModuloPeriodo()
								.converterParaDTO(repositorioModuloPeriodo.obterPorId(moduloPeriodoSalvo.getId())));
						logger.info(contaAcesso + " editou os integrantes do " + moduloPeriodoSalvo);
					} else {
						resultadoDto.adicionarErro("Usuários "
								+ TextoUtils.removerChaves(Usuario.obterNomesDosUsuarios(
										usuariosIntegradosEmOutroGrupoAtivoComDescricaoServicoSocialModuloReuniaoDeIntegracao))
								+ " já estão integrados em um grupo de reunião de integração.");
					}
				} else {
					resultadoDto.adicionarErro(moduloPeriodo
							.obterDescricaoErros());
				}
			} else {
				logger.error(Fachada.MENSAGEM_DADOS_DESATUALIZADOS);
				resultadoDto
						.adicionarErro(Fachada.MENSAGEM_DADOS_DESATUALIZADOS);
			}
		} else {
			resultadoDto.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultadoDto;
	}

	public synchronized ResultadoGeracaoAtendimentoDTO gerarAtendimentos(PendenciaDTO pendenciaDto,
			TokenDTO tokenDto) {
		ResultadoGeracaoAtendimentoDTO resultadoGeracaoAtendimentoDTO = new ResultadoGeracaoAtendimentoDTO();

		Pendencia pendencia = repositorioPendencia.obterPorId(pendenciaDto.getId());
		Grupo grupo = repositorioGrupo.obterGrupoPorId(pendenciaDto.getEspecificacaoGeracaoAtendimentosDTO().getIdGrupo());
		String nomeDoGrupoETurma = grupo.obterNomeGrupoETurma();
		if (!pendencia.estaResolvida()) {
			if (!fachadaSeguranca.estaBloqueadoParaEdicao(nomeDoGrupoETurma, tokenDto)) {
				resultadoGeracaoAtendimentoDTO = gerarAtendimentos(pendenciaDto.getEspecificacaoGeracaoAtendimentosDTO(), tokenDto);
				if (resultadoGeracaoAtendimentoDTO.sucesso()) {
					repositorioSislara.resolverPendencia(pendencia.getId());
				}
			} else {
				resultadoGeracaoAtendimentoDTO
						.adicionarErro(fachadaSeguranca.obterContaAcessoEditando(nomeDoGrupoETurma).obterMensagens());
			}
		} else {
			resultadoGeracaoAtendimentoDTO.adicionarErro(MENSAGEM_DADOS_DESATUALIZADOS);
		}
		return resultadoGeracaoAtendimentoDTO;
	}

	public synchronized ResultadoGeracaoAtendimentoDTO gerarAtendimentos(
			EspecificacaoGeracaoAtendimentosDTO especificacaoGeracaoAtendimentosDto,
			TokenDTO tokenDto) {
		ResultadoGeracaoAtendimentoDTO resultado = new ResultadoGeracaoAtendimentoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(tokenDto);
		if (contaAcesso.possuiPermissao(Permissao.GRUPO_EDICAO)) {
			ModuloPeriodo moduloPeriodo = repositorioGrupo
					.obterModuloPeriodoPorId(especificacaoGeracaoAtendimentosDto
							.getIdModuloPeriodoDto());

			moduloPeriodo.gerarAtendimentos(
					especificacaoGeracaoAtendimentosDto.getData(),
					especificacaoGeracaoAtendimentosDto.getHoraInicial(),
					especificacaoGeracaoAtendimentosDto.getHoraFinal());

			moduloPeriodo.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (moduloPeriodo.validado()) {
				ModuloPeriodo moduloPeriodoSalvo = repositorioModuloPeriodo
						.salvar(moduloPeriodo);
				resultado.efetuadoComSucesso(new FabricaModuloPeriodo()
						.converterParaDTO(moduloPeriodoSalvo));
				resultado.setGrupoDto(new FabricaGrupo().converterParaDTO(repositorioGrupo
						.obterGrupoPorIdModuloPeriodo(moduloPeriodoSalvo.getId())));
				resultado.setAtendimentoGrupoGeradoDTO(new FabricaAtendimentoGrupo()
						.converterParaDTO(moduloPeriodoSalvo.obterAtendimentoGrupoCriadoMaisRecentemente()));
				resultado.adicionarMensagem(moduloPeriodoSalvo.obterRelacaoUsuariosComStatusProvisorioOuAcesso());
				logger.info(contaAcesso
						+ " efetuou geração de Atendimentos Padrões para "
						+ moduloPeriodoSalvo);

			} else {
				resultado.adicionarErro(moduloPeriodo.obterDescricaoErros());
			}
		} else {
			resultado.adicionarErro(MENSAGEM_NAO_POSSUI_AUTORIZACAO);
		}
		return resultado;
	}

	public synchronized ResultadoEdicaoAtendimentoGrupoDTO cancelarAtendimentoGrupo(
			AtendimentoGrupoDTO atendimentoGrupoDto, TokenDTO tokenDto) {
		return editarAtendimentoGrupo(atendimentoGrupoDto, true, tokenDto);
	}

	public ResultadoListagemGrupoDTO obterListagemGrupoAtivo(String nomeGrupo) {
		ResultadoListagemGrupoDTO resultado = new ResultadoListagemGrupoDTO();
		List<Grupo> grupos = repositorioGrupo.obterAtivos(false, nomeGrupo, true);
		if (grupos.isEmpty()) {
			resultado.adicionarErro("Nenhum grupo encontrado.");
		} else if (grupos.size() > 1) {
			resultado.adicionarErro("Mais de um grupo foi encontrado.");
		} else {
			resultado.efetuadoComSucesso(new FabricaGrupo()
					.converterParaDTO(grupos));
		}
		return resultado;
	}

	public ResultadoListagemProfissionalDTO obterListagemProfissionaisAtivos() {
		return (ResultadoListagemProfissionalDTO) obterListagem(
				repositorioProfissional.obterProfissionaisAtivos(),
				new FabricaProfissional(), "Profissionais Ativos",
				new ResultadoListagemProfissionalDTO());
	}

	public ResultadoListagemProfissionalDTO obterListagemTodosProfissionais() {
		return (ResultadoListagemProfissionalDTO) obterListagem(
				repositorioProfissional.obterTodos(),
				new FabricaProfissional(), "Todos os Profissionais",
				new ResultadoListagemProfissionalDTO());
	}

	public ResultadoListagemStatusRelacaoUsuarioModuloDTO obterListagemStatusRelacaoUsuarioModulo(
			ModuloPeriodoDTO moduloPeriodoDto) {
		Grupo grupo = repositorioGrupo.obterGrupoPorIdModuloPeriodo(moduloPeriodoDto.getId());
		return (ResultadoListagemStatusRelacaoUsuarioModuloDTO) obterListagem(
				StatusRelacaoComModulo.disponivelParaRelacaoComModulos(grupo.getSetor()),
				new FabricaStatusRelacaoComModulo(), "Status de Relação dos Usuários e Módulos",
				new ResultadoListagemStatusRelacaoUsuarioModuloDTO());
	}
	
	public ResultadoListagemStatusRelacaoUsuarioModuloDTO obterListagemStatusRelacaoUsuarioModuloDisponiveisParaInclusao(
			GrupoDTO grupoDto) {
		return (ResultadoListagemStatusRelacaoUsuarioModuloDTO) obterListagem(
				StatusRelacaoComModulo.obterDisponivelParaInclusao(new FabricaGrupo().converterParaDominio(grupoDto)),
				new FabricaStatusRelacaoComModulo(),
				"Status de Relação dos Usuários e Módulos disponíveis para Inclusão",
				new ResultadoListagemStatusRelacaoUsuarioModuloDTO());
	}

	public ResultadoListagemFrequenciaDTO obterListagemFrequenciaDoUsuario() {
		return (ResultadoListagemFrequenciaDTO) obterListagem(
				Frequencia.deUsuarios(), new FabricaFrequencia(),
				"Frequências do Usuário", new ResultadoListagemFrequenciaDTO());
	}

	public ResultadoListagemFrequenciaDTO obterListagemFrequenciaDoProfissional() {
		return (ResultadoListagemFrequenciaDTO) obterListagem(
				Frequencia.deProfissionais(), new FabricaFrequencia(),
				"Frequências do Profissional",
				new ResultadoListagemFrequenciaDTO());
	}

	public ResultadoValidacaoModuloPeriodoDTO validarModuloPeriodo(
			ModuloPeriodoDTO moduloPeriodoDto) {
		return (ResultadoValidacaoModuloPeriodoDTO) Validador.efetuarValidacao(
				moduloPeriodoDto, new FabricaModuloPeriodo(),
				"Módulo/Atividade", new ResultadoValidacaoModuloPeriodoDTO());
	}

	public ResultadoValidacaoLoteRecursoDTO validarLoteRecurso(
			LoteRecursoDTO loteRecursoDto) {
		return (ResultadoValidacaoLoteRecursoDTO) Validador.efetuarValidacao(
				loteRecursoDto, new FabricaLoteRecurso(), "Recurso",
				new ResultadoValidacaoLoteRecursoDTO());
	}

	public ResultadoValidacaoProgramacaoDTO validarProgramacao(
			ProgramacaoDTO programacaoDto) {
		return (ResultadoValidacaoProgramacaoDTO) Validador.efetuarValidacao(
				programacaoDto, new FabricaProgramacao(), "Programação",
				new ResultadoValidacaoProgramacaoDTO());
	}

	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(
			List<? extends ModuloRelacaoBaseDTO> modulosRelacaoBaseDto,
			ModuloRelacaoBaseDTO moduloRelacaoBaseDto) {
		return new FabricaValidadorModuloRelacaoBase().obterValidador(moduloRelacaoBaseDto)
				.validarModuloRelacaoBase(modulosRelacaoBaseDto, moduloRelacaoBaseDto);
	}

	public ResultadoValidacaoModuloRelacaoBaseDTO validarModuloRelacaoBase(ModuloRelacaoBaseDTO moduloRelacaoBaseDto) {
		return new FabricaValidadorModuloRelacaoBase().obterValidador(moduloRelacaoBaseDto)
				.validarModulo(moduloRelacaoBaseDto);
	}

	public ResultadoValidacaoAtendimentoBaseDTO validarAtendimentoBase(AtendimentoBaseDTO atendimentoBaseDto) {
		return new FabricaValidadorAtendimentoBase().obterValidador(atendimentoBaseDto)
				.validarAtendimento(atendimentoBaseDto);
	}

	public ResultadoListagemGrupoDTO pesquisarGrupoPor(
			EspecificacaoPesquisaGrupoDTO especificacao) {
		ResultadoListagemGrupoDTO resultado = new ResultadoListagemGrupoDTO();
		List<Grupo> grupos = new ArrayList<>();
		try {
			if (especificacao.existeChave(ChavePesquisaDTO.GRUPOS_INATIVOS)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.GRUPOS_INATIVOS);
				grupos = repositorioGrupo.obterInativos(parametro);
			} else if (especificacao
					.existeChave(ChavePesquisaDTO.GRUPOS_ATIVOS)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.GRUPOS_ATIVOS);
				grupos = repositorioGrupo
						.obterGruposAtivosSemAtendimentosEIntegrantes(parametro);
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(grupos, new FabricaGrupo(), resultado);
		} catch (IllegalArgumentException e) {
			String erro = "Nenhum registro escontrado.";
			resultado.nenhumRegistroEncontrado();
			logger.error(erro + " \nDetalhes: " + e);
		} catch (Exception e) {
			String erro = "Ocorreu um erro durante a listagem de Grupo.";
			resultado.adicionarErro(erro);
			logger.error(erro + " \nDetalhes: " + e);
		}
		return resultado;
	}

	public ResultadoListagemNomeCompletoGrupoDTO pesquisarNomeGrupoPor(
			String nomeGrupo) {
		ResultadoListagemNomeCompletoGrupoDTO resutlado = new ResultadoListagemNomeCompletoGrupoDTO();
		List<String> nomesCompletosGrupos = (List<String>) repositorioGrupo
				.obterNomesGrupos(nomeGrupo);
		resutlado.efetuadoComSucesso(nomesCompletosGrupos);
		return resutlado;
	}

	public ResultadoListagemTipoAtendimentoDTO obterListagemTipoAtendimento() {
		return (ResultadoListagemTipoAtendimentoDTO) obterListagem(
				repositorioTipoAtendimento.obterTodos(),
				new FabricaTipoAtendimento(), "Tipos de Atendimento",
				new ResultadoListagemTipoAtendimentoDTO());
	}

	public ResultadoValidacaoDiaSemanaEHorarioDTO validarDiaSemanaEHorario(DiaSemanaEHorarioDTO diaSemanaEHorarioDTO) {
		return (ResultadoValidacaoDiaSemanaEHorarioDTO) Validador.efetuarValidacao(diaSemanaEHorarioDTO,
				new FabricaDiaSemanaEHorario(), "Dia da semana e horário",
				new ResultadoValidacaoDiaSemanaEHorarioDTO());
	}

	public StatusRelacaoComModuloDTO obterStatusRelacaoPadrao(GrupoDTO grupoDto) {
		Grupo grupo = repositorioGrupo.obterGrupoPorId(grupoDto.getId());
		return new FabricaStatusRelacaoComModulo().converterParaDTO(grupo.obterStatusRelacaoPadrao());
	}

	public ArquivoDTO obterArquivoAtendimentoGrupo(AtendimentoGrupoDTO atendimentoGrupoDTO, ArquivoDTO arquivoDTO) {
		return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoAtendimentoGrupo(
				new FabricaAtendimentoGrupo().converterParaDominio(atendimentoGrupoDTO),
				new FabricaArquivo().converterParaDominio(arquivoDTO)));
	}

	public ArquivoDTO obterArquivoAtendimentoUsuario(AtendimentoUsuarioDTO atendimentoUsuarioDTO,
			ArquivoDTO arquivoDTO) {
		return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoAtendimentoUsuario(
				new FabricaAtendimentoUsuario().converterParaDominio(atendimentoUsuarioDTO),
				new FabricaArquivo().converterParaDominio(arquivoDTO)));
	}
}

package br.laramara.ti.sislaraserver.fachadas;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoCopiaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.EspecificacaoPesquisaAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoEdicaoAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemAtendimentoIndividualDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemOpcaoIntegracaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemParticipacaoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoListagemTipoVinculoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.ResultadoValidacaoAcaoCondutaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemStatusDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.OpcaoIntegracao;
import br.laramara.ti.sislaraserver.dominio.atendimento.Participacao;
import br.laramara.ti.sislaraserver.dominio.atendimento.TipoAcaoConduta;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.dominio.usuario.AutomatizadorStatusUsuario;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.fabricas.FabricaAcaoConduta;
import br.laramara.ti.sislaraserver.fabricas.FabricaArquivo;
import br.laramara.ti.sislaraserver.fabricas.FabricaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.fabricas.FabricaOpcaoIntegracao;
import br.laramara.ti.sislaraserver.fabricas.FabricaParticipacao;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatus;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoAcaoConduta;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoVinculo;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoAtendimentoIndividualCopia;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoAtendimentoIndividualEdicao;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorModuloRelacaoBase;
import br.laramara.ti.sislaraserver.repositorios.RepositorioArquivo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoVinculo;

@Component
public class FachadaAtendimentoIndividual extends Fachada {

	@Inject
	private RepositorioAtendimentoIndividual repositorioAtendimentoIndividual;
	@Inject
	private RepositorioTipoVinculo repositorioTipoVinculo;
	@Inject
	private RepositorioEspera repositorioEspera;
	@Inject
	private RepositorioTipoAtendimento repositorioTipoAtendimento;
	@Inject
	private RepositorioPreCadastro repositorioPreCadastro;
	@Inject
	private RepositorioBloqueio repositorioBloqueio;
	@Inject
	private AutomatizadorEspera automatizadorEspera;
	@Inject
	private AutomatizadorStatusUsuario automatizadorStatusUsuario;
	@Inject
	private RepositorioArquivo repositorioArquivo;
	
	public synchronized ResultadoEdicaoAtendimentoIndividualDTO editarAtendimentoIndividual(
			AtendimentoIndividualDTO atendimentoIndividualDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoAtendimentoIndividualEdicao(
				new FabricaAtendimentoIndividual(),
				repositorioAtendimentoIndividual, repositorioBloqueio,
				repositorioEspera, repositorioTipoAtendimento, repositorioPreCadastro,
				automatizadorEspera, automatizadorStatusUsuario, atendimentoIndividualDto);
		return (ResultadoEdicaoAtendimentoIndividualDTO) verificarPermissaoEProcessar(
				operacao, Permissao.ATENDIMENTO_INDIVIDUAL_EDICAO,
				new ResultadoEdicaoAtendimentoIndividualDTO(), tokenDto);
	}

	public ResultadoListagemAtendimentoIndividualDTO obterListagemAtendimentoIndividual(
			EspecificacaoPesquisaAtendimentoIndividualDTO especificacaoPesquisaAtendimentoIndividualDto) {
		ResultadoListagemAtendimentoIndividualDTO resultadoDto = new ResultadoListagemAtendimentoIndividualDTO();

		EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimento = new FabricaEspecificacaoPesquisaAtendimentoIndividual()
				.converterParaDominio((EspecificacaoPesquisaAtendimentoIndividualDTO) especificacaoPesquisaAtendimentoIndividualDto);
		especificacaoPesquisaAtendimento
				.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (especificacaoPesquisaAtendimento.validado()) {
			resultadoDto = (ResultadoListagemAtendimentoIndividualDTO) obterListagem(
					repositorioAtendimentoIndividual
							.obterPor(especificacaoPesquisaAtendimento),
					new FabricaAtendimentoIndividual(), "Atendimento Individual",
					new ResultadoListagemAtendimentoIndividualDTO());
		} else {
			resultadoDto.adicionarErro(especificacaoPesquisaAtendimento
					.obterDescricaoErros());
		}
		return resultadoDto;
	}

	public synchronized ResultadoEdicaoAtendimentoIndividualDTO copiarAtendimento(
			EspecificacaoCopiaAtendimentoIndividualDTO especificacaoCopiaAtendimentoIndividualDto,
			AtendimentoIndividualDTO atendimentoIndividualDto, TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoAtendimentoIndividualCopia(
				especificacaoCopiaAtendimentoIndividualDto,
				new FabricaAtendimentoIndividual(),
				repositorioAtendimentoIndividual, repositorioBloqueio,
				automatizadorEspera, atendimentoIndividualDto);
		return (ResultadoEdicaoAtendimentoIndividualDTO) verificarPermissaoEProcessar(
				operacao, Permissao.ATENDIMENTO_INDIVIDUAL_EDICAO,
				new ResultadoEdicaoAtendimentoIndividualDTO(), tokenDto);
	}

	public ResultadoListagemTipoVinculoDTO obterListagemTipoVinculo() {
		return (ResultadoListagemTipoVinculoDTO) obterListagem(
				repositorioTipoVinculo.obterTodos(),
				new FabricaTipoVinculo(), "Tipo de Vínculo",
				new ResultadoListagemTipoVinculoDTO());
	}

	public ResultadoListagemOpcaoIntegracaoDTO obterListagemOpcaoIntegracao() {
		return (ResultadoListagemOpcaoIntegracaoDTO) obterListagem(Arrays.asList(OpcaoIntegracao.values()),
				new FabricaOpcaoIntegracao(), "Opção de integração", new ResultadoListagemOpcaoIntegracaoDTO());
	}
	
	public ResultadoListagemParticipacaoDTO obterListagemParticipacao() {
		return (ResultadoListagemParticipacaoDTO) obterListagem(
				Arrays.asList(Participacao.values()),
				new FabricaParticipacao(), "Participação",
				new ResultadoListagemParticipacaoDTO());
	}
	
	public String obterResumoIntegracao(String prontuario){
		return repositorioAtendimentoIndividual.obterResumoIntegracao(prontuario);
	}
	
	public ResultadoListagemStatusDTO obterListagemStatusDisponiveisParaAtendimentoIndividual() {
		ResultadoListagemStatusDTO resultado = new ResultadoListagemStatusDTO();
		resultado.efetuadoComSucesso(
				new FabricaStatus().converterParaDTO(Status.statusDisponiveisParaAtendimentoIndividual()));
		return resultado;
	}

	public ArquivoDTO obterArquivoAtendimentoIndividual(AtendimentoIndividualDTO atendimentoIndividualDTO,
			ArquivoDTO arquivoDTO) {
		return new FabricaArquivo().converterParaDTO(repositorioArquivo.obterArquivoAtendimentoIndividual(
				new FabricaAtendimentoIndividual().converterParaDominio(atendimentoIndividualDTO),
				new FabricaArquivo().converterParaDominio(arquivoDTO)));
	}

	public ResultadoListagemTipoAcaoCondutaDTO obterListagemTipoAcaoConduta() {
		return (ResultadoListagemTipoAcaoCondutaDTO) obterListagem(Arrays.asList(TipoAcaoConduta.values()),
				new FabricaTipoAcaoConduta(), "Tipo de acão de condutas", new ResultadoListagemTipoAcaoCondutaDTO());
	}

	public ResultadoValidacaoAcaoCondutaDTO validarAcaoConduta(AcaoCondutaDTO acaoCondutaDto) {
		return (ResultadoValidacaoAcaoCondutaDTO) ValidadorModuloRelacaoBase.efetuarValidacao(acaoCondutaDto,
				new FabricaAcaoConduta(), "Ação de conduta", new ResultadoValidacaoAcaoCondutaDTO());
	}
}

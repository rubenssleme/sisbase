package br.laramara.ti.sislaraserver.fachadas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemSimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoListagemTipoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.ResultadoValidacaoTelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemAreaFormacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemEscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoListagemSituacaoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.ResultadoValidacaoInformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoListagemParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ResultadoValidacaoFamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemCargoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoListagemLocalTrabalhoDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.ResultadoValidacaoInformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CertidaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.CustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EspecificacaoPesquisaUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.PeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ProntuarioEscaneadoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.RecursoRelacionamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoConsultaCidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoEdicaoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemClassificacaoSocialDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemCondicaoMoradiaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemConvenioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemEncaminhamentoDTO;
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
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemRelacaoRecursoDTO;
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
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoCustoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoBeneficioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoComprometimentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDeficienciaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoPeriodoDoencaDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoValidacaoRecursoRelacionamentoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.atendimento.EspecificacaoPesquisaAtendimentoIndividual;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;
import br.laramara.ti.sislaraserver.dominio.endereco.Zona;
import br.laramara.ti.sislaraserver.dominio.escola.Periodo;
import br.laramara.ti.sislaraserver.dominio.escola.SituacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.seguranca.Permissao;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;
import br.laramara.ti.sislaraserver.dominio.usuario.ClassificacaoSocial;
import br.laramara.ti.sislaraserver.dominio.usuario.EpocaIncidencia;
import br.laramara.ti.sislaraserver.dominio.usuario.Genero;
import br.laramara.ti.sislaraserver.dominio.usuario.GrupoEtnico;
import br.laramara.ti.sislaraserver.dominio.usuario.ProntuarioEscaneado;
import br.laramara.ti.sislaraserver.dominio.usuario.PossuiNecessita;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;
import br.laramara.ti.sislaraserver.dominio.usuario.StatusBeneficio;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoCertidao;
import br.laramara.ti.sislaraserver.dominio.usuario.TipoLeitura;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaAreaFormacao;
import br.laramara.ti.sislaraserver.fabricas.FabricaBeneficio;
import br.laramara.ti.sislaraserver.fabricas.FabricaCargo;
import br.laramara.ti.sislaraserver.fabricas.FabricaCertidao;
import br.laramara.ti.sislaraserver.fabricas.FabricaCid;
import br.laramara.ti.sislaraserver.fabricas.FabricaClassificacaoSocial;
import br.laramara.ti.sislaraserver.fabricas.FabricaComprometimento;
import br.laramara.ti.sislaraserver.fabricas.FabricaCondicaoMoradia;
import br.laramara.ti.sislaraserver.fabricas.FabricaConvenio;
import br.laramara.ti.sislaraserver.fabricas.FabricaCusto;
import br.laramara.ti.sislaraserver.fabricas.FabricaDeficiencia;
import br.laramara.ti.sislaraserver.fabricas.FabricaDoenca;
import br.laramara.ti.sislaraserver.fabricas.FabricaEncaminhamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaEpocaIncidencia;
import br.laramara.ti.sislaraserver.fabricas.FabricaEscolaridade;
import br.laramara.ti.sislaraserver.fabricas.FabricaEstadoCivil;
import br.laramara.ti.sislaraserver.fabricas.FabricaExpectativa;
import br.laramara.ti.sislaraserver.fabricas.FabricaFamiliar;
import br.laramara.ti.sislaraserver.fabricas.FabricaGenero;
import br.laramara.ti.sislaraserver.fabricas.FabricaGrupoEtnico;
import br.laramara.ti.sislaraserver.fabricas.FabricaHabitacao;
import br.laramara.ti.sislaraserver.fabricas.FabricaInformacaoEducacional;
import br.laramara.ti.sislaraserver.fabricas.FabricaInformacaoTrabalhoCompleta;
import br.laramara.ti.sislaraserver.fabricas.FabricaInfraestruturaBasica;
import br.laramara.ti.sislaraserver.fabricas.FabricaItemCusto;
import br.laramara.ti.sislaraserver.fabricas.FabricaLocalTrabalho;
import br.laramara.ti.sislaraserver.fabricas.FabricaMunicipio;
import br.laramara.ti.sislaraserver.fabricas.FabricaNecessidade;
import br.laramara.ti.sislaraserver.fabricas.FabricaOrigemEncaminhamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaPais;
import br.laramara.ti.sislaraserver.fabricas.FabricaParentesco;
import br.laramara.ti.sislaraserver.fabricas.FabricaPeriodo;
import br.laramara.ti.sislaraserver.fabricas.FabricaPeriodoBeneficio;
import br.laramara.ti.sislaraserver.fabricas.FabricaPeriodoComprometimento;
import br.laramara.ti.sislaraserver.fabricas.FabricaPeriodoDeficiencia;
import br.laramara.ti.sislaraserver.fabricas.FabricaPeriodoDoenca;
import br.laramara.ti.sislaraserver.fabricas.FabricaProntuarioEscaneado;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursoMoradia;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecursoRelacionamento;
import br.laramara.ti.sislaraserver.fabricas.FabricaPossuiNecessita;
import br.laramara.ti.sislaraserver.fabricas.FabricaServico;
import br.laramara.ti.sislaraserver.fabricas.FabricaSetor;
import br.laramara.ti.sislaraserver.fabricas.FabricaSimNao;
import br.laramara.ti.sislaraserver.fabricas.FabricaSituacao;
import br.laramara.ti.sislaraserver.fabricas.FabricaSituacaoGuarda;
import br.laramara.ti.sislaraserver.fabricas.FabricaSituacaoHabitacional;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatus;
import br.laramara.ti.sislaraserver.fabricas.FabricaStatusBeneficio;
import br.laramara.ti.sislaraserver.fabricas.FabricaTelefone;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoCertidao;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoConstrucao;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoLeitura;
import br.laramara.ti.sislaraserver.fabricas.FabricaTipoTelefone;
import br.laramara.ti.sislaraserver.fabricas.FabricaUf;
import br.laramara.ti.sislaraserver.fabricas.FabricaUsuario;
import br.laramara.ti.sislaraserver.fabricas.FabricaZona;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoFachada;
import br.laramara.ti.sislaraserver.fachadas.operacoes.OperacaoUsuarioEdicao;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorModuloRelacaoBase;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAreaFormacao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBeneficio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCargo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCid;
import br.laramara.ti.sislaraserver.repositorios.RepositorioComprometimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCondicaoMoradia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioConvenio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDeficiencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDoenca;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEncaminhamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEscolaridade;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEstadoCivil;
import br.laramara.ti.sislaraserver.repositorios.RepositorioExpectativa;
import br.laramara.ti.sislaraserver.repositorios.RepositorioHabitacao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInfraestruturaBasica;
import br.laramara.ti.sislaraserver.repositorios.RepositorioItemCusto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioLocalTrabalho;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMunicipio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioNecessidade;
import br.laramara.ti.sislaraserver.repositorios.RepositorioOrigemEncaminhamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPais;
import br.laramara.ti.sislaraserver.repositorios.RepositorioParentesco;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProntuarioEscaneado;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecursoMoradia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioServico;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSituacaoGuarda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSituacaoHabitacional;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoConstrucao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;

@Component
public class FachadaUsuario extends Fachada {

	@Inject
	private RepositorioPais repositorioPais;
	@Inject
	private RepositorioEstadoCivil repositorioEstadoCivil;
	@Inject
	private RepositorioParentesco repositorioParentesco;
	@Inject
	private RepositorioEscolaridade repositorioEscolaridade;
	@Inject
	private RepositorioMunicipio repositorioMunicipio;
	@Inject
	private RepositorioUsuario repositorioUsuario;
	@Inject
	private RepositorioSituacaoGuarda repositorioSituacaoGuarda;
	@Inject
	private RepositorioBeneficio repositorioBeneficio;
	@Inject
	private RepositorioConvenio repositorioConvenio;
	@Inject
	private RepositorioProntuarioEscaneado repositorioProntuarioEscaneado;
	@Inject
	private RepositorioDeficiencia repositorioDeficiencia;
	@Inject
	private RepositorioComprometimento repositorioComprometimento;
	@Inject
	private RepositorioDoenca repositorioDoenca;
	@Inject
	private RepositorioCid repositorioCid;
	@Inject
	private RepositorioAreaFormacao repositorioAreaFormacao;
	@Inject
	private RepositorioCargo repositorioCargo;
	@Inject
	private RepositorioLocalTrabalho repositorioLocalTrabalho;
	@Inject
	private RepositorioItemCusto repositorioItemCusto;
	@Inject
	private RepositorioServico repositorioServico;
	@Inject
	private RepositorioEncaminhamento repositorioEncaminhamento;
	@Inject
	private RepositorioNecessidade repositorioNecessidade;
	@Inject
	private RepositorioExpectativa repositorioExpectativa;
	@Inject
	private RepositorioRecursoMoradia repositorioRecursoMoradia;
	@Inject
	private RepositorioCondicaoMoradia repositorioCondicaoMoradia;
	@Inject
	private RepositorioSituacaoHabitacional repositorioSituacaoHabitacional;
	@Inject
	private RepositorioHabitacao repositorioHabitacao;
	@Inject
	private RepositorioTipoConstrucao repositorioTipoConstrucao;
	@Inject
	private RepositorioInfraestruturaBasica repositorioInfraestruturaBasica;
	@Inject
	private RepositorioOrigemEncaminhamento repositorioOrigemEncaminhamento;
	@Inject
	private RepositorioAtendimentoIndividual repositorioAtendimentoIndividual;
	
		
	public ResultadoListagemClassificacaoSocialDTO obterListagemClassificacaoSocial() {
		return (ResultadoListagemClassificacaoSocialDTO) obterListagem(
				Arrays.asList(ClassificacaoSocial.values()),
				new FabricaClassificacaoSocial(), "Classificação Social",
				new ResultadoListagemClassificacaoSocialDTO());
	}

	public ResultadoListagemStatusDTO obterListagemStatus() {
		return (ResultadoListagemStatusDTO) obterListagem(
				Status.statusDisponiveisParaUsuarios(), new FabricaStatus(), "Status",
				new ResultadoListagemStatusDTO());
	}
	
	public ResultadoListagemStatusDTO obterListagemStatusFamiliar() {
		return (ResultadoListagemStatusDTO) obterListagem(
				Status.statusDisponiveisParaFamiliar(), new FabricaStatus(),
				"Status do Familiar", new ResultadoListagemStatusDTO());
	}

	public ResultadoListagemGeneroDTO obterListagemGenero() {
		return (ResultadoListagemGeneroDTO) obterListagem(
				Arrays.asList(Genero.values()), new FabricaGenero(), "Gênero",
				new ResultadoListagemGeneroDTO());
	}
	
	public ResultadoListagemEpocaIncidenciaDTO obterListagemEpocaIncidencia() {
		return (ResultadoListagemEpocaIncidenciaDTO) obterListagem(
				Arrays.asList(EpocaIncidencia.values()),
				new FabricaEpocaIncidencia(), "Epoca de Incidência",
				new ResultadoListagemEpocaIncidenciaDTO());
	}

	public ResultadoListagemUfDTO obterListagemUf() {
		return (ResultadoListagemUfDTO) obterListagem(
				Arrays.asList(UF.values()), new FabricaUf(), "UFs",
				new ResultadoListagemUfDTO());
	}

	public ResultadoListagemMunicipioDTO obterListagemMunicipio(UfDTO ufDto) {
		return (ResultadoListagemMunicipioDTO) obterListagem(
				repositorioMunicipio.pesquisarPor(new FabricaUf()
						.converterParaDominio(ufDto)), new FabricaMunicipio(),
				"Municípios", new ResultadoListagemMunicipioDTO());
	}

	public ResultadoListagemEstadoCivilDTO obterListagemEstadoCivil() {
		return (ResultadoListagemEstadoCivilDTO) obterListagem(
				repositorioEstadoCivil.obterTodos(), new FabricaEstadoCivil(),
				"Estados Civis", new ResultadoListagemEstadoCivilDTO());
	}

	public ResultadoListagemGrupoEtnicoDTO obterListagemGrupoEtnico() {
		return (ResultadoListagemGrupoEtnicoDTO) obterListagem(
				Arrays.asList(GrupoEtnico.values()), new FabricaGrupoEtnico(),
				"Grupos Etnicos", new ResultadoListagemGrupoEtnicoDTO());
	}
		
	public ResultadoListagemZonaDTO obterListagemZona() {
		return (ResultadoListagemZonaDTO) obterListagem(
				Arrays.asList(Zona.values()), new FabricaZona(), "Zonas",
				new ResultadoListagemZonaDTO());
	}
	
	public ResultadoListagemSimNaoDTO obterListagemSimNao() {
		return (ResultadoListagemSimNaoDTO) obterListagem(
				Arrays.asList(SimNao.values()), new FabricaSimNao(), "SimNao",
				new ResultadoListagemSimNaoDTO());
	}

	public ResultadoListagemSetorDTO obterListagemSetor() {
		return (ResultadoListagemSetorDTO) obterListagem(
				Setor.statusDisponiveis(), new FabricaSetor(), "Setor",
				new ResultadoListagemSetorDTO());
	}
	
	public ResultadoListagemSetorDTO obterListagemSetorParaProjetos() {
		return (ResultadoListagemSetorDTO) obterListagem(
				Setor.statusDisponiveisParaProjetos(), new FabricaSetor(), "Setor para Projetos",
				new ResultadoListagemSetorDTO());
	}

	public ResultadoListagemAreaFormacaoDTO obterListagemAreaFormacao() {
		return  (ResultadoListagemAreaFormacaoDTO) obterListagem(
				repositorioAreaFormacao.obterTodos(), new FabricaAreaFormacao(),
				"Area de Formação", new ResultadoListagemAreaFormacaoDTO());
	}
	
	public ResultadoListagemPaisDTO obterListagemPais() {
		return (ResultadoListagemPaisDTO) obterListagem(
				repositorioPais.obterTodos(), new FabricaPais(), "Paises",
				new ResultadoListagemPaisDTO());
	}

	public synchronized ResultadoEdicaoUsuarioDTO editarUsuario(UsuarioDTO usuarioDto,
			TokenDTO tokenDto) {
		OperacaoFachada operacao = new OperacaoUsuarioEdicao(new FabricaUsuario(),
				repositorioUsuario, usuarioDto);
		return (ResultadoEdicaoUsuarioDTO) verificarPermissaoEProcessar(operacao,
				Permissao.USUARIO_EDICAO, new ResultadoEdicaoUsuarioDTO(),
				tokenDto);
	}
	
	public ResultadoListagemUsuarioDTO pesquisarUsuarioPor(
			EspecificacaoPesquisaUsuarioDTO especificacao) {
		ResultadoListagemUsuarioDTO resultado = new ResultadoListagemUsuarioDTO();
		List<Usuario> usuarios =  new ArrayList<>();
		try {
			if (especificacao.existeChave(ChavePesquisaDTO.NOME)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.NOME);

				usuarios = repositorioUsuario
						.pesquisarPorNome(parametro);
			}else if (especificacao.existeChave(ChavePesquisaDTO.FAMILIAR)){
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.FAMILIAR);

				usuarios = repositorioUsuario
						.pesquisarPorFamiliar(parametro);
			}else if (especificacao
					.existeChave(ChavePesquisaDTO.RG)) {
				String parametro = (String) especificacao
						.obterParametro(ChavePesquisaDTO.RG);

				usuarios = repositorioUsuario
						.pesquisarPorRG(parametro);
			} else if (especificacao.existeChave(ChavePesquisaDTO.PRONTUARIO)) {
				Long parametro = new Long(
						TextoUtils
								.removerCaracteresInvalidos((String) especificacao
										.obterParametro(ChavePesquisaDTO.PRONTUARIO)));

				
				Usuario usuario = repositorioUsuario.obterPorId(parametro);
				preencherResumoDeAtendimentosDoPsicossocial(usuario);
				if (usuario != null){
					usuarios.add(usuario);
				}
			} else {
				resultado.adicionarErro(MENSAGEM_INSIRA_TIPO_DADO_PESQUISA);
			}
			gravarResultadoSeNaoForVazio(usuarios, new FabricaUsuario(), resultado);
		} catch (NumberFormatException e) {
			String erro = "Insira um parâmetro de pesquisa válido.";
			resultado.adicionarErro(erro);
			logger.error(erro + "\nDetalhes: " + e);
		} catch (Exception e) {
			String erro = "Erro durante a pesquisa por usuário.";
			resultado.adicionarErro(erro);
			logger.fatal(erro + "\nDetalhes: " + e);
		}
		return resultado;
	}

	private void preencherResumoDeAtendimentosDoPsicossocial(Usuario usuario) {
		if (usuario != null){
			String textoAtendimentosIndividuais = "";
			List<DescricaoTipoAtendimento> descricoes = Arrays.asList(
					DescricaoTipoAtendimento.fabricarDescricaoPsicologia(),
					DescricaoTipoAtendimento.fabricarDescricaoServicoSocial());
			List<Modulo> modulos = Arrays.asList(Modulo.fabricaModuloAcompanhamento(),
					Modulo.fabricaModuloAvaliacaoETriagem(), Modulo.fabricaModuloExcessoFaltas());
			List<AtendimentoIndividual> atendimentosIndividuaisDoPsicossocial = new ArrayList<>();
			EspecificacaoPesquisaAtendimentoIndividual especificacaoPesquisaAtendimentoIndividual = new EspecificacaoPesquisaAtendimentoIndividual();
			especificacaoPesquisaAtendimentoIndividual.setProntuario(usuario.getId().toString());
			for(DescricaoTipoAtendimento descricao : descricoes){
				for(Modulo modulo : modulos){
					especificacaoPesquisaAtendimentoIndividual.setDescricaoTipoAtendimento(descricao);
					especificacaoPesquisaAtendimentoIndividual.setModulo(modulo);
					atendimentosIndividuaisDoPsicossocial.addAll(repositorioAtendimentoIndividual.obterPor(especificacaoPesquisaAtendimentoIndividual));
				}
			}
			Collections.sort(atendimentosIndividuaisDoPsicossocial, AtendimentoIndividual.obterComparador());
			for(AtendimentoIndividual atendimentoIndividualDoPsicossocial : atendimentosIndividuaisDoPsicossocial){
				textoAtendimentosIndividuais += atendimentoIndividualDoPsicossocial.getDescricaoTipoAtendimento().getTipoAtendimento()
						.getNome() + " - " + atendimentoIndividualDoPsicossocial.getDescricaoTipoAtendimento().getNome() + " - " + atendimentoIndividualDoPsicossocial.getModulo().getNome() 
						+ "\n" + atendimentoIndividualDoPsicossocial.getData() + " - " + atendimentoIndividualDoPsicossocial.getHorario().getHoraInicio() + " às " + atendimentoIndividualDoPsicossocial.getHorario().getHoraTermino() + " - " + TextoUtils.removerChaves(atendimentoIndividualDoPsicossocial.obterProfissionais().toString())
						+ "\n\n" + atendimentoIndividualDoPsicossocial.getInformacaoAtendimento().getDescricao() 
						+ "\n--------------------------------------------------\n";
			}
			usuario.setResumoAtendimentosPsicossocial(textoAtendimentosIndividuais);
		}
	}

	public ResultadoListagemEscolaridadeDTO obterListagemEscolaridade() {
		return (ResultadoListagemEscolaridadeDTO) obterListagem(
				repositorioEscolaridade.obterTodos(),
				new FabricaEscolaridade(), "Escolaridades",
				new ResultadoListagemEscolaridadeDTO());
	}

	public ResultadoListagemPeriodoDTO obterListagemPeriodo() {
		return (ResultadoListagemPeriodoDTO) obterListagem(
				Arrays.asList(Periodo.values()), new FabricaPeriodo(),
				"Periodos", new ResultadoListagemPeriodoDTO());
	}

	public ResultadoListagemSituacaoDTO obterListagemSituacao() {
		return (ResultadoListagemSituacaoDTO) obterListagem(
				Arrays.asList(SituacaoEducacional.values()),
				new FabricaSituacao(), "Situações",
				new ResultadoListagemSituacaoDTO());
	}

	public ResultadoValidacaoFamiliarDTO validarFamiliar(FamiliarDTO familiarDto) {
		return (ResultadoValidacaoFamiliarDTO) ValidadorModuloRelacaoBase.efetuarValidacao(familiarDto,
				new FabricaFamiliar(), "Familiar",
				new ResultadoValidacaoFamiliarDTO());
	}
	
	public ResultadoValidacaoCustoDTO validarCusto(CustoDTO custoDto) {
		return (ResultadoValidacaoCustoDTO) ValidadorModuloRelacaoBase.efetuarValidacao(custoDto,
				new FabricaCusto(), "Custo", new ResultadoValidacaoCustoDTO());
	}

	public ResultadoListagemParentescoDTO obterListagemParentesco() {
		return (ResultadoListagemParentescoDTO) obterListagem(
				repositorioParentesco.obterTodos(), new FabricaParentesco(),
				"Parentesco", new ResultadoListagemParentescoDTO());
	}

	public ResultadoListagemSituacaoGuardaDTO obterListagemSituacaoGuarda() {
		return (ResultadoListagemSituacaoGuardaDTO) obterListagem(
				repositorioSituacaoGuarda.obterTodos(),
				new FabricaSituacaoGuarda(), "Situação de Guarda",
				new ResultadoListagemSituacaoGuardaDTO());
	}

	public ResultadoListagemBeneficioDTO obterListagemBeneficio() {
		return (ResultadoListagemBeneficioDTO) obterListagem(
				repositorioBeneficio.obterTodos(), new FabricaBeneficio(),
				"Beneficios", new ResultadoListagemBeneficioDTO());
	}
	
	public ResultadoListagemConvenioDTO obterListagemConvenio() {
		return (ResultadoListagemConvenioDTO) obterListagem(
				repositorioConvenio.obterTodos(), new FabricaConvenio(),
				"Convênios", new ResultadoListagemConvenioDTO());
	}

	public ResultadoValidacaoInformacaoEducacionalDTO validarInformacaoEducacional(
			InformacaoEducacionalDTO informacaoEducacionalDto) {
		return (ResultadoValidacaoInformacaoEducacionalDTO) ValidadorModuloRelacaoBase.efetuarValidacao(
				informacaoEducacionalDto, new FabricaInformacaoEducacional(),
				"Informação Educacional",
				new ResultadoValidacaoInformacaoEducacionalDTO());
	}

	public ResultadoListagemTipoTelefoneDTO obterListagemTipoTelefone() {
		return (ResultadoListagemTipoTelefoneDTO) obterListagem(
				Arrays.asList(TipoTelefone.values()),
				new FabricaTipoTelefone(), "Tipos de Telefones",
				new ResultadoListagemTipoTelefoneDTO());
	}

	public ResultadoValidacaoTelefoneDTO validarTelefone(TelefoneDTO telefoneDto) {
		return (ResultadoValidacaoTelefoneDTO) ValidadorModuloRelacaoBase.efetuarValidacao(telefoneDto,
				new FabricaTelefone(), "Telefone",
				new ResultadoValidacaoTelefoneDTO());
	}

	public ResultadoValidacaoCertidaoDTO validarCertidao(CertidaoDTO certidaoDto) {
		return (ResultadoValidacaoCertidaoDTO) ValidadorModuloRelacaoBase.efetuarValidacao(certidaoDto,
				new FabricaCertidao(), "Certidão",
				new ResultadoValidacaoCertidaoDTO());
	}
	
	public ResultadoValidacaoInformacaoTrabalhoCompletaDTO validarInformacaoTrabalhoCompleta(
			InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto) {
		return (ResultadoValidacaoInformacaoTrabalhoCompletaDTO) ValidadorModuloRelacaoBase.efetuarValidacao(
				informacaoTrabalhoCompletaDto,
				new FabricaInformacaoTrabalhoCompleta(),
				"Informação de Trabalho Completa",
				new ResultadoValidacaoInformacaoTrabalhoCompletaDTO());
	}

	public ResultadoValidacaoPeriodoBeneficioDTO validarPeriodoBeneficio(
			PeriodoBeneficioDTO periodoBeneficioDto) {
		return (ResultadoValidacaoPeriodoBeneficioDTO) ValidadorModuloRelacaoBase.efetuarValidacao(
				periodoBeneficioDto, new FabricaPeriodoBeneficio(),
				"Período de Benefício",
				new ResultadoValidacaoPeriodoBeneficioDTO());
	}
	
	public ResultadoValidacaoPeriodoDeficienciaDTO validarPeriodoDeficiencia(
			PeriodoDeficienciaDTO periodoDeficienciaDto) {
		return (ResultadoValidacaoPeriodoDeficienciaDTO) ValidadorModuloRelacaoBase.efetuarValidacao(
				periodoDeficienciaDto, new FabricaPeriodoDeficiencia(),
				"Período de Deficiência",
				new ResultadoValidacaoPeriodoDeficienciaDTO());
	}
	
	public ResultadoValidacaoPeriodoComprometimentoDTO validarPeriodoComprometimento(
			PeriodoComprometimentoDTO periodoComprometimentoDto) {
		return (ResultadoValidacaoPeriodoComprometimentoDTO) ValidadorModuloRelacaoBase.efetuarValidacao(
				periodoComprometimentoDto, new FabricaPeriodoComprometimento(), "Período de Comprometimento",
				new ResultadoValidacaoPeriodoComprometimentoDTO());
	}
	
	public ResultadoListagemProntuarioEscaneadoDTO obterListagemProntuariosEscaneados(
			UsuarioDTO usuarioDto) {
		return (ResultadoListagemProntuarioEscaneadoDTO) obterListagem(
				repositorioProntuarioEscaneado.obterPorId(usuarioDto.getId()),
				new FabricaProntuarioEscaneado(), "Prontuarios Escaneados",
				new ResultadoListagemProntuarioEscaneadoDTO());
	}

	public ProntuarioEscaneadoDTO obterProntuarioEscaneado(
			UsuarioDTO usuarioDto, ProntuarioEscaneadoDTO prontuarioEscaneadoDto) {
		ProntuarioEscaneado prontuarioEscaneado = new FabricaProntuarioEscaneado()
				.converterParaDominio(prontuarioEscaneadoDto);

		ProntuarioEscaneadoDTO retorno = new FabricaProntuarioEscaneado()
				.converterParaDTO(repositorioProntuarioEscaneado
						.obterProntuarioEscaneado(usuarioDto.getId(),
								prontuarioEscaneado));
		return retorno;
	}
	
	public ResultadoListagemDeficienciaDTO obterListagemDeficiencia() {
		return (ResultadoListagemDeficienciaDTO) obterListagem(
				repositorioDeficiencia.obterTodos(), new FabricaDeficiencia(),
				"Deficiencia", new ResultadoListagemDeficienciaDTO());
	}
	
	public ResultadoListagemComprometimentoDTO obterListagemComprometimento() {
		return (ResultadoListagemComprometimentoDTO) obterListagem(
				repositorioComprometimento.obterTodos(), new FabricaComprometimento(),
				"Comprometimento", new ResultadoListagemComprometimentoDTO());
	}

	public ResultadoConsultaCidDTO consultarCid(String codigo) {
		ResultadoConsultaCidDTO resultadoConsultaCidDTO = new ResultadoConsultaCidDTO();
		Cid cidEncontrado = repositorioCid.obterPorCid(codigo);
		if (cidEncontrado != null) {
			resultadoConsultaCidDTO.efetuadoComSucesso(new FabricaCid()
					.converterParaDTO(cidEncontrado));
		} else {
			resultadoConsultaCidDTO.adicionarErro("CID não encontrado.");
		}
		return resultadoConsultaCidDTO;
	}

	public ResultadoListagemTipoCertidaoDTO obterListagemTipoCertidao() {
		return (ResultadoListagemTipoCertidaoDTO) obterListagem(
				Arrays.asList(TipoCertidao.values()),
				new FabricaTipoCertidao(), "Tipo de Certidao",
				new ResultadoListagemTipoCertidaoDTO());
	}

	public ResultadoListagemTipoLeituraDTO obterListagemTipoLeitura() {
		return (ResultadoListagemTipoLeituraDTO) obterListagem(
				Arrays.asList(TipoLeitura.values()), new FabricaTipoLeitura(),
				"Tipo de Leitura", new ResultadoListagemTipoLeituraDTO());
	}

	public ResultadoListagemCargoDTO obterListagemCargo() {
		return (ResultadoListagemCargoDTO) obterListagem(
				repositorioCargo.obterTodos(), new FabricaCargo(), "Cargo",
				new ResultadoListagemCargoDTO());
	}

	public ResultadoListagemLocalTrabalhoDTO obterListagemLocalTrabalho() {
		return (ResultadoListagemLocalTrabalhoDTO) obterListagem(
				repositorioLocalTrabalho.obterTodos(),
				new FabricaLocalTrabalho(), "Local Trabalho",
				new ResultadoListagemLocalTrabalhoDTO());
	}

	public ResultadoListagemStatusBeneficioDTO obterListagemStatusBeneficio() {
		return (ResultadoListagemStatusBeneficioDTO) obterListagem(
				Arrays.asList(StatusBeneficio.values()),
				new FabricaStatusBeneficio(), "Status do Benefício",
				new ResultadoListagemStatusBeneficioDTO());
	}

	public ResultadoValidacaoPeriodoDoencaDTO validarPeriodoDoenca(
			PeriodoDoencaDTO periodoDoencaDto) {
		return (ResultadoValidacaoPeriodoDoencaDTO) ValidadorModuloRelacaoBase.efetuarValidacao(
				periodoDoencaDto, new FabricaPeriodoDoenca(),
				"Período de Doença", new ResultadoValidacaoPeriodoDoencaDTO());
	}

	public ResultadoListagemDoencaDTO obterListagemDoenca() {
		return (ResultadoListagemDoencaDTO) obterListagem(
				repositorioDoenca.obterTodos(), new FabricaDoenca(),
				"Doença", new ResultadoListagemDoencaDTO());
	}

	public ResultadoListagemItensCustoDTO obterListagemItensCustoDoenca() {
		return (ResultadoListagemItensCustoDTO) obterListagem(
				repositorioItemCusto.obterTodosDaDoenca(),
				new FabricaItemCusto(), "Itens de Custo da Doença",
				new ResultadoListagemItensCustoDTO());
	}

	public ResultadoListagemItensCustoDTO obterListagemItensCustoDeficiencia() {
		return (ResultadoListagemItensCustoDTO) obterListagem(
				repositorioItemCusto.obterTodosDaDeficiencia(),
				new FabricaItemCusto(), "Itens de Custo da Deficiencia",
				new ResultadoListagemItensCustoDTO());
	}
	
	public ResultadoListagemEncaminhamentoDTO obterListagemEncaminhamento() {
		return (ResultadoListagemEncaminhamentoDTO) obterListagem(
				repositorioEncaminhamento.obterTodos(), new FabricaEncaminhamento(),
				"Encaminhamento", new ResultadoListagemEncaminhamentoDTO());
	}
	
	public ResultadoListagemServicoDTO obterListagemServico() {
		return (ResultadoListagemServicoDTO) obterListagem(
				repositorioServico.obterTodos(), new FabricaServico(),
				"Servico", new ResultadoListagemServicoDTO());
	}

	public ResultadoListagemNecessidadeDTO obterListagemNecessidade() {
		return (ResultadoListagemNecessidadeDTO) obterListagem(
				repositorioNecessidade.obterTodos(), new FabricaNecessidade(),
				"Necessidade", new ResultadoListagemNecessidadeDTO());
	}
	
	public ResultadoListagemExpectativaDTO obterListagemExpectativa() {
		return (ResultadoListagemExpectativaDTO) obterListagem(
				repositorioExpectativa.obterTodos(), new FabricaExpectativa(),
				"Expectativa", new ResultadoListagemExpectativaDTO());
	}

	public ResultadoListagemRecursoMoradiaDTO obterListagemRecursoProximoMoradia() {
		return (ResultadoListagemRecursoMoradiaDTO) obterListagem(
				repositorioRecursoMoradia.obterTodos(), new FabricaRecursoMoradia(),
				"Recursos próximo à moradia", new ResultadoListagemRecursoMoradiaDTO());
	}

	public ResultadoListagemCondicaoMoradiaDTO obterListagemCondicaoMoradia() {
		return (ResultadoListagemCondicaoMoradiaDTO) obterListagem(
				repositorioCondicaoMoradia.obterTodos(), new FabricaCondicaoMoradia(),
				"Condições de Moradia", new ResultadoListagemCondicaoMoradiaDTO());
	}

	public ResultadoListagemSituacaoHabitacionalDTO obterListagemSituacaoHabitacional() {
		return (ResultadoListagemSituacaoHabitacionalDTO) obterListagem(
				repositorioSituacaoHabitacional.obterTodos(), new FabricaSituacaoHabitacional(),
				"Situação Habitacional", new ResultadoListagemSituacaoHabitacionalDTO());
	}

	public ResultadoListagemHabitacaoDTO obterListagemHabitacao() {
		return (ResultadoListagemHabitacaoDTO) obterListagem(
				repositorioHabitacao.obterTodos(), new FabricaHabitacao(),
				"Habitação", new ResultadoListagemHabitacaoDTO());
	}

	public ResultadoListagemTipoConstrucaoDTO obterListagemTipoConstrucao() {
		return (ResultadoListagemTipoConstrucaoDTO) obterListagem(
				repositorioTipoConstrucao.obterTodos(), new FabricaTipoConstrucao(),
				"Tipo de Construção", new ResultadoListagemTipoConstrucaoDTO());
	}

	public ResultadoListagemInfraestruturaBasicaDTO obterListagemInfraestrututraBasica() {
		return (ResultadoListagemInfraestruturaBasicaDTO) obterListagem(
				repositorioInfraestruturaBasica.obterTodos(), new FabricaInfraestruturaBasica(),
				"Infraestrutura básica", new ResultadoListagemInfraestruturaBasicaDTO());
	}

	public ResultadoListagemOrigemEncaminhamentoDTO obterListagemOrigemEncaminhamento() {
		return (ResultadoListagemOrigemEncaminhamentoDTO) obterListagem(
				repositorioOrigemEncaminhamento.obterTodos(), new FabricaOrigemEncaminhamento(),
				"Origem Encaminhamento", new ResultadoListagemOrigemEncaminhamentoDTO());
	}

	public ResultadoListagemRelacaoRecursoDTO obterListagemRelacaoRecurso() {
		return (ResultadoListagemRelacaoRecursoDTO) obterListagem(Arrays.asList(PossuiNecessita.values()),
				new FabricaPossuiNecessita(), "Relação de recurso", new ResultadoListagemRelacaoRecursoDTO());
	}

	public ResultadoValidacaoRecursoRelacionamentoDTO validarRecursoRelacionamento(
			RecursoRelacionamentoDTO recursoRelacionamentoDto) {
		return (ResultadoValidacaoRecursoRelacionamentoDTO) ValidadorModuloRelacaoBase.efetuarValidacao(recursoRelacionamentoDto,
				new FabricaRecursoRelacionamento(), "Recurso relacionamento",
				new ResultadoValidacaoRecursoRelacionamentoDTO());
	}
}

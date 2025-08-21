package br.laramara.sistelemarketingserver.servico;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.sistelemarketingcommons.dtos.BairroResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.EstadoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.MunicipioResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.MetodoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.ValorDetalhadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.ValorDetalhadoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoAutenticacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.CredencialDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalResultadoDTO;
import br.laramara.sistelemarketingserver.dominio.campanha.AlocacaoOperador;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;
import br.laramara.sistelemarketingserver.dominio.campanha.Criterio;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuidorContato;
import br.laramara.sistelemarketingserver.dominio.doacao.Doacao;
import br.laramara.sistelemarketingserver.dominio.doacao.Metodo;
import br.laramara.sistelemarketingserver.dominio.doacao.ValorDetalhado;
import br.laramara.sistelemarketingserver.dominio.seguranca.Autenticador;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcessoNaoAutenticadoException;
import br.laramara.sistelemarketingserver.dominio.seguranca.Nivel;
import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;
import br.laramara.sistelemarketingserver.dominio.seguranca.Turno;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ligacao;
import br.laramara.sistelemarketingserver.dominio.telefonia.MecanismoTelefonia;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;
import br.laramara.sistelemarketingserver.fabricas.AlocacaoOperadorFabrica;
import br.laramara.sistelemarketingserver.fabricas.BairroFabrica;
import br.laramara.sistelemarketingserver.fabricas.CampanhaFabrica;
import br.laramara.sistelemarketingserver.fabricas.ContaAcessoFabrica;
import br.laramara.sistelemarketingserver.fabricas.CredencialFabrica;
import br.laramara.sistelemarketingserver.fabricas.CriterioFabrica;
import br.laramara.sistelemarketingserver.fabricas.DistribuicaoContatoFabrica;
import br.laramara.sistelemarketingserver.fabricas.DoacaoFabrica;
import br.laramara.sistelemarketingserver.fabricas.EstadoFabrica;
import br.laramara.sistelemarketingserver.fabricas.FabricaBase;
import br.laramara.sistelemarketingserver.fabricas.LigacaoFabrica;
import br.laramara.sistelemarketingserver.fabricas.MetodoFabrica;
import br.laramara.sistelemarketingserver.fabricas.MunicipioFabrica;
import br.laramara.sistelemarketingserver.fabricas.NivelFabrica;
import br.laramara.sistelemarketingserver.fabricas.PermissaoFabrica;
import br.laramara.sistelemarketingserver.fabricas.RamalFabrica;
import br.laramara.sistelemarketingserver.fabricas.TurnoFabrica;
import br.laramara.sistelemarketingserver.fabricas.ValorDetalhadoFabrica;
import br.laramara.sistelemarketingserver.repositorios.CampanhaRepositorio;
import br.laramara.sistelemarketingserver.repositorios.ContaAcessoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.DistribuicaoContatoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.DoacaoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.EstadoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.LogradouroRepositorio;
import br.laramara.sistelemarketingserver.repositorios.MunicipioRepositorio;
import br.laramara.sistelemarketingserver.repositorios.NivelRepositorio;
import br.laramara.sistelemarketingserver.repositorios.PermissaoRepositorio;

@Component
public class ServicoTelemarketingServerImplementacao implements ServicoTelemarketingServer {

	private final Logger logger = Logger.getLogger(ServicoTelemarketingServerImplementacao.class);
	
	private static final String NAO_AUTORIZADO = "Não autorizado.";
	
	@Inject
	private NivelRepositorio nivelRepositorio;
	@Inject
	private CampanhaRepositorio campanhaRepositorio;
	@Inject
	private ContaAcessoRepositorio contaAcessoRepositorio;
	@Inject
	private PermissaoRepositorio permissaoRepositorio;
	@Inject
	private EstadoRepositorio estadoRepositorio;
	@Inject
	private MunicipioRepositorio municipioRepositorio;
	@Inject
	private LogradouroRepositorio logradouroRepositorio;
	@Inject
	private Autenticador autenticador;
	@Inject
	private MecanismoTelefonia mecanismoTelefonia;
	@Inject
	private DistribuidorContato distribuidorContato;
	@Inject
	private DoacaoRepositorio doacaoRepositorio;
	@Inject
	private DistribuicaoContatoRepositorio distribuicaoContatoRepositorio;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ResultadoDTO obterListagem(List<?> objetosDominio,
			FabricaBase fabrica, String descricaoDaListagem,
			ResultadoDTO objetoResultado) {
		try {
			List<? extends ModeloDTO> objetosDto = fabrica
					.converterParaDTO(objetosDominio);
			objetoResultado.efetuadoComSucesso(objetosDto);
		} catch (Exception e) {
			String erro = "Ocorreu um erro durante a obtenção da lista de "
					+ descricaoDaListagem + ".";
			objetoResultado.adicionarErro(erro);
			logger.error(erro + " \nDetalhes: " + e);
		}
		return objetoResultado;
	}
		
	protected ContaAcesso obterContaAcesso(String token) {
		return contaAcessoRepositorio.obterPorToken(token);
	}
	
	@Override
	public NivelResultadoDTO nivelListar() {
		NivelResultadoDTO nivelResultadoDTO = new NivelResultadoDTO();
		nivelResultadoDTO.efetuadoComSucesso(new NivelFabrica().converterParaDTO(nivelRepositorio.obterTodos()));
		return nivelResultadoDTO;
	}

	@Override
	public NivelResultadoDTO nivelObter(Long id) {
		NivelResultadoDTO nivelResultadoDTO = new NivelResultadoDTO();
		nivelResultadoDTO.efetuadoComSucesso(new NivelFabrica().converterParaDTO(nivelRepositorio.obter(id)));
		return nivelResultadoDTO;
	}

	@Override
	public NivelResultadoDTO nivelEditar(NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDto) {
		NivelResultadoDTO nivelResultadoDTO = new NivelResultadoDTO();
		
		ContaAcesso contaAcesso = obterContaAcesso(nivelSolicitacaoEdicaoDto.getToken());
		if (contaAcesso.possuiPermissao(Permissao.obterPermissaoNivelEditar())) {
			Nivel nivelASalvar = new NivelFabrica().converterParaDominio(nivelSolicitacaoEdicaoDto.getNivelDto());
			nivelASalvar.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (nivelASalvar.validado()) {
				nivelResultadoDTO
						.efetuadoComSucesso(new NivelFabrica().converterParaDTO(nivelRepositorio.salvar(nivelASalvar)));
			}else {
				nivelResultadoDTO.adicionarErro(nivelASalvar.obterDescricaoErros());
			}
		}else {
			nivelResultadoDTO.adicionarErro(NAO_AUTORIZADO);
		}
		return nivelResultadoDTO;
	}

	@Override
	public PermissaoResultadoDTO permissaoListar() {
		PermissaoResultadoDTO permissaoResultadoListagemDTO = new PermissaoResultadoDTO();
		permissaoResultadoListagemDTO.efetuadoComSucesso(new PermissaoFabrica().converterParaDTO(permissaoRepositorio.obterTodos()));
		return permissaoResultadoListagemDTO;
	}
	
	@Override
	public PermissaoResultadoDTO permissaoObter(String token) {
		PermissaoResultadoDTO permissaoResultadoListagemDTO = new PermissaoResultadoDTO();
		ContaAcesso contaAcesso = contaAcessoRepositorio.obterPorToken(token);
		permissaoResultadoListagemDTO
				.efetuadoComSucesso(new PermissaoFabrica().converterParaDTO(contaAcesso.obterPermissoes()));
		return permissaoResultadoListagemDTO;
	}

	@Override
	public ContaAcessoResultadoDTO contaAcessoListar() {
		ContaAcessoResultadoDTO contaAcessoResultadoDTO = new ContaAcessoResultadoDTO();
		contaAcessoResultadoDTO.efetuadoComSucesso(new ContaAcessoFabrica().converterParaDTO(contaAcessoRepositorio.obterTodos()));
		return contaAcessoResultadoDTO;
	}

	@Override
	public ContaAcessoResultadoDTO contaAcessoObter(Long id) {
		ContaAcessoResultadoDTO contaAcessoResultadoDTO = new ContaAcessoResultadoDTO();
		contaAcessoResultadoDTO
				.efetuadoComSucesso(new ContaAcessoFabrica().converterParaDTO(contaAcessoRepositorio.obter(id)));
		return contaAcessoResultadoDTO;
	}
	
	@Override
	public ContaAcessoResultadoDTO listarTodosOperadoresAtivos() {
		ContaAcessoResultadoDTO contaAcessoResultadoDTO = new ContaAcessoResultadoDTO();
		contaAcessoResultadoDTO.efetuadoComSucesso(
				new ContaAcessoFabrica().converterParaDTO(contaAcessoRepositorio.obterTodosOperadoresAtivos()));
		return contaAcessoResultadoDTO;
	}

	@Override
	public ContaAcessoResultadoDTO contaAcessoEditar(ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDto) {
		ContaAcessoResultadoDTO contaAcessoResultadoDTO = new ContaAcessoResultadoDTO();
		ContaAcesso contaAcesso = obterContaAcesso(contaAcessoSolicitacaoEdicaoDto.getToken());

		if (contaAcesso.possuiPermissao(Permissao.obterPermissaoContaAcessoEditar())) {
			ContaAcesso contaAcessoASalvar = new ContaAcessoFabrica().converterParaDominio(
					contaAcessoSolicitacaoEdicaoDto.getContaAcessoDto(),
					contaAcessoRepositorio.obter(contaAcessoSolicitacaoEdicaoDto.getContaAcessoDto().getId()));
			contaAcessoASalvar.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (contaAcessoASalvar.validado()) {
				contaAcessoResultadoDTO.efetuadoComSucesso(
						new ContaAcessoFabrica().converterParaDTO(contaAcessoRepositorio.salvar(contaAcessoASalvar)));
			} else {
				contaAcessoResultadoDTO.adicionarErro(contaAcessoASalvar.obterDescricaoErros());
			}
		} else {
			contaAcessoResultadoDTO.adicionarErro(NAO_AUTORIZADO);
		}
		return contaAcessoResultadoDTO;
	}
	
	@Override
	public ContaAcessoResultadoAutenticacaoDTO contaAcessoAutenticar(CredencialDTO credencialDto) {
		ContaAcessoResultadoAutenticacaoDTO resultadoAutenticacaoDTO = new ContaAcessoResultadoAutenticacaoDTO();
		try {
			ContaAcesso contaAcesso = autenticador
					.autentica(new CredencialFabrica().converterParaDominio(credencialDto));
			resultadoAutenticacaoDTO.efetuadoComSucesso(new ContaAcessoFabrica().converterParaDTO(contaAcesso),
					contaAcesso.getToken());
		} catch (ContaAcessoNaoAutenticadoException e) {
			resultadoAutenticacaoDTO.adicionarErro(NAO_AUTORIZADO);
		}
		return resultadoAutenticacaoDTO;
	}

	@Override
	public TurnoResultadoDTO turnoListar() {
		return (TurnoResultadoDTO) obterListagem(
				Arrays.asList(Turno.values()), new TurnoFabrica(), "Turno",
				new TurnoResultadoDTO());
	}

	@Override
	public EstadoResultadoDTO estadoListar() {
		return (EstadoResultadoDTO) obterListagem(estadoRepositorio.obterTodos(), new EstadoFabrica(), "Estado",
				new EstadoResultadoDTO());
	}

	@Override
	public MunicipioResultadoDTO municipioListar(Long idEstado) {
		return (MunicipioResultadoDTO) obterListagem(municipioRepositorio.obterTodos(idEstado), new MunicipioFabrica(),
				"Município", new MunicipioResultadoDTO());
	}

	@Override
	public BairroResultadoDTO bairroListar(Long idMunicipio) {
		return (BairroResultadoDTO) obterListagem(logradouroRepositorio.obterTodos(idMunicipio), new BairroFabrica(),
				"Município", new BairroResultadoDTO());
	}

	@Override
	public CampanhaResultadoDTO campanhaEditar(CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDto) {
		CampanhaResultadoDTO campanhaResultadoDTO = new CampanhaResultadoDTO();
		
		ContaAcesso contaAcesso = obterContaAcesso(campanhaSolicitacaoEdicaoDto.getToken());
		if (contaAcesso.possuiPermissao(Permissao.obterPermissaoCampanhaEditar())) {
			Campanha campanhaASalvar = new CampanhaFabrica().converterParaDominio(
					campanhaSolicitacaoEdicaoDto.getCampanhaDto(),
					campanhaRepositorio.obter(campanhaSolicitacaoEdicaoDto.getCampanhaDto().getId()));
			campanhaASalvar.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (campanhaASalvar.validado()) {
				campanhaResultadoDTO
						.efetuadoComSucesso(new CampanhaFabrica().converterParaDTO(campanhaRepositorio.salvar(campanhaASalvar)));
			}else {
				campanhaResultadoDTO.adicionarErro(campanhaASalvar.obterDescricaoErros());
			}
		}else {
			campanhaResultadoDTO.adicionarErro(NAO_AUTORIZADO);
		}
		return campanhaResultadoDTO;
	}

	@Override
	public CampanhaResultadoDTO campanhaObter(Long id) {
		CampanhaResultadoDTO campanhaResultadoDTO = new CampanhaResultadoDTO();
		campanhaResultadoDTO
				.efetuadoComSucesso(new CampanhaFabrica().converterParaDTO(campanhaRepositorio.obter(id)));
		return campanhaResultadoDTO;
	}

	@Override
	public CampanhaResultadoDTO campanhaListar() {
		CampanhaResultadoDTO campanhaResultadoDTO = new CampanhaResultadoDTO();
		campanhaResultadoDTO
				.efetuadoComSucesso(new CampanhaFabrica().converterParaDTO(campanhaRepositorio.obterTodos()));
		return campanhaResultadoDTO;
	}

	@Override
	public CriterioResultadoDTO criterioValidar(CriterioDTO criterioDto) {
		CriterioResultadoDTO criterioResultadoDTO = new CriterioResultadoDTO();

		Criterio criterio = new CriterioFabrica().converterParaDominio(criterioDto);
		criterio.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (criterio.validado()) {
			criterioResultadoDTO.efetuadoComSucesso(criterioDto);
		} else {
			criterioResultadoDTO.efetuadoComErro(criterioDto, criterio.obterDescricaoErros());
		}
		return criterioResultadoDTO;
	}

	@Override
	public AlocacaoOperadorResultadoDTO alocacaoOperadorValidar(AlocacaoOperadorDTO alocacaoOperadorDto) {
		AlocacaoOperadorResultadoDTO alocacaoOperadorResultadoDTO = new AlocacaoOperadorResultadoDTO();

		AlocacaoOperador alocacaoOperador = new AlocacaoOperadorFabrica().converterParaDominio(alocacaoOperadorDto);
		alocacaoOperador.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (alocacaoOperador.validado()) {
			alocacaoOperadorResultadoDTO.efetuadoComSucesso(alocacaoOperadorDto);
		} else {
			alocacaoOperadorResultadoDTO.efetuadoComErro(alocacaoOperadorDto, alocacaoOperador.obterDescricaoErros());
		}
		return alocacaoOperadorResultadoDTO;
	}

	@Override
	public LigacaoResultadoDTO ligar(LigacaoDTO ligacaoDto) {
		LigacaoResultadoDTO ligacaoResultadoDTO = new LigacaoResultadoDTO();
		try {
			Ligacao ligacao = new LigacaoFabrica().converterParaDominio(ligacaoDto);
			mecanismoTelefonia.ligar(ligacao);
			ligacaoResultadoDTO.efetuadoComSucesso(Arrays.asList(ligacaoDto));
		} catch (Exception e) {
			ligacaoResultadoDTO.efetuadoComErro(ligacaoDto, "Erro durante realização da ligação.");
		}
		return ligacaoResultadoDTO;
	}
	
	@Override
	public String obterStatusRamal(String token) {
		try {
			return mecanismoTelefonia.obterStatusRamal(token);
		} catch (Exception e) {
			return "Erro durante obtenção de status do ramal.";
		}
	}

	@Override
	public DistribuicaoContatoResultadoDTO distribuicaoContatoObter(String token) {
		DistribuicaoContatoResultadoDTO distribuicaoContatoResultadoDTO = new DistribuicaoContatoResultadoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(token);
		try {
			DistribuicaoContato distribuicaoContato = distribuidorContato.obterContato(contaAcesso);
			if (distribuicaoContato != null) {
				distribuicaoContatoResultadoDTO
						.efetuadoComSucesso(new DistribuicaoContatoFabrica().converterParaDTO(distribuicaoContato));
			}else {
				distribuicaoContatoResultadoDTO.adicionarErro("Não existem contatos disponíveis.");
			}
		} catch (Exception e) {
			distribuicaoContatoResultadoDTO.adicionarErro("Erro durante obtenção de distribuição de contato.");
		}
		return distribuicaoContatoResultadoDTO;
	}

	@Override
	public RamalResultadoDTO ramalListar() {
		RamalResultadoDTO ramalResultadoDTO = new RamalResultadoDTO();
		ramalResultadoDTO.efetuadoComSucesso(new RamalFabrica().converterParaDTO(Arrays.asList(Ramal.values())));
		return ramalResultadoDTO;
	}

	@Override
	public DoacaoResultadoDTO doacaoEditar(DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDto) {
		DoacaoResultadoDTO doacaoResultadoDTO = new DoacaoResultadoDTO();

		ContaAcesso contaAcesso = obterContaAcesso(doacaoSolicitacaoEdicaoDto.getToken());
		Doacao doacaoASalvar = new DoacaoFabrica().converterParaDominio(doacaoSolicitacaoEdicaoDto.getDoacaoDto(),
				doacaoRepositorio.obter(doacaoSolicitacaoEdicaoDto.getDoacaoDto().getId()));
		doacaoASalvar.validarObrigatoriedadeETamanhoMaximoDeDados();
		DistribuicaoContato distribuicaoContato = distribuicaoContatoRepositorio
				.obterPorId(doacaoASalvar.getDistribuicaoContato().getId());
		if (doacaoASalvar.eNova() && contaAcesso.possuiPermissao(Permissao.obterPermissaoDoacaoEfetuar())) {
			if (distribuicaoContato.eAtuacaoEmContatoIniciado() && contaAcesso.eOperador()) {
				if (doacaoASalvar.validado()) {
					doacaoResultadoDTO.efetuadoComSucesso(
							new DoacaoFabrica().converterParaDTO(doacaoRepositorio.salvar(doacaoASalvar)));
					distribuicaoContato.finalizarAtuacaoNoContato(contaAcesso);
					distribuicaoContatoRepositorio.salvar(distribuicaoContato);
				} else {
					doacaoResultadoDTO.adicionarErro(doacaoASalvar.obterDescricaoErros());
				}
			}else {
				doacaoResultadoDTO.adicionarErro("Doações devem ser efetuadas somente em contatos que estão sendo atuados pelo operador.");
			}
		} else {
			doacaoResultadoDTO.adicionarErro(NAO_AUTORIZADO);
		}
		return doacaoResultadoDTO;
	}

	@Override
	public MetodoResultadoDTO metodoListar() {
		MetodoResultadoDTO metodoResultadoDTO = new MetodoResultadoDTO();
		metodoResultadoDTO.efetuadoComSucesso(new MetodoFabrica().converterParaDTO(Arrays.asList(Metodo.values())));
		return metodoResultadoDTO;
	}

	@Override
	public ValorDetalhadoResultadoDTO valorDetalhadoValidar(ValorDetalhadoDTO valorDetalhadoDto) {
		ValorDetalhadoResultadoDTO valorDetalhadoResultadoDTO = new ValorDetalhadoResultadoDTO();

		ValorDetalhado valorDetalhado = new ValorDetalhadoFabrica().converterParaDominio(valorDetalhadoDto);
		valorDetalhado.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (valorDetalhado.validado()) {
			valorDetalhadoResultadoDTO.efetuadoComSucesso(valorDetalhadoDto);
		} else {
			valorDetalhadoResultadoDTO.efetuadoComErro(valorDetalhadoDto, valorDetalhado.obterDescricaoErros());
		}
		return valorDetalhadoResultadoDTO;
	}
}

package br.laramara.sistelemarketingserver.servico.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.laramara.sistelemarketingcommons.dtos.BairroResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.EstadoResultadoDTO;
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
import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalResultadoDTO;
import br.laramara.sistelemarketingcommons.servicos.rest.ContratoRest;
import br.laramara.sistelemarketingserver.servico.ServicoTelemarketingServer;

@RestController
public class ServicoSistelemarketingServerRest {

	@Inject
	private ServicoTelemarketingServer servicoSistelemarketingServer;

	@PostMapping(ContratoRest.URL_CONTA_ACESSO_EDITAR)
	public ContaAcessoResultadoDTO contaAcessoEditar(
			@RequestBody ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDto) {
		return servicoSistelemarketingServer.contaAcessoEditar(contaAcessoSolicitacaoEdicaoDto);
	}
	
	@GetMapping(ContratoRest.URL_CONTA_ACESSO_LISTAR_TODOS_OPERADORES_ATIVOS)
	public ContaAcessoResultadoDTO listarTodosOperadoresAtivos() {
		return servicoSistelemarketingServer.listarTodosOperadoresAtivos();
	}
	
	@GetMapping(ContratoRest.URL_CONTA_ACESSO_OBTER)
	public ContaAcessoResultadoDTO contaAcessoObter(@PathVariable Long id) {
		return servicoSistelemarketingServer.contaAcessoObter(id);
	}
	
	@GetMapping(ContratoRest.URL_CONTA_ACESSO_LISTAR)
	public ContaAcessoResultadoDTO contaAcessoListar() {
		return servicoSistelemarketingServer.contaAcessoListar();
	}

	@PostMapping(ContratoRest.URL_CONTA_ACESSO_AUTENTICAR)
	public ContaAcessoResultadoAutenticacaoDTO contaAcessoAutenticar(
			@RequestBody CredencialDTO credencialDto) {
		return servicoSistelemarketingServer.contaAcessoAutenticar(credencialDto);
	}
	
	@GetMapping(ContratoRest.URL_NIVEL_LISTAR)
	public NivelResultadoDTO nivelListar() {
		return servicoSistelemarketingServer.nivelListar();
	}
	
	@GetMapping(ContratoRest.URL_NIVEL_OBTER)
	public NivelResultadoDTO nivelObter(@PathVariable Long id) {
		return servicoSistelemarketingServer.nivelObter(id);
	}
	
	@PostMapping(ContratoRest.URL_NIVEL_EDITAR)
	public NivelResultadoDTO nivelEditar(
			@RequestBody NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDto) {
		return servicoSistelemarketingServer.nivelEditar(nivelSolicitacaoEdicaoDto);
	}

	@GetMapping(ContratoRest.URL_PERMISSAO_OBTER)
	public PermissaoResultadoDTO permissaoObter(@PathVariable String token) {
		return servicoSistelemarketingServer.permissaoObter(token);
	}
	
	@GetMapping(ContratoRest.URL_PERMISSAO_LISTAR)
	public PermissaoResultadoDTO permissaoListar() {
		return servicoSistelemarketingServer.permissaoListar();
	}
	
	@GetMapping(ContratoRest.URL_TURNO_LISTAR)
	public TurnoResultadoDTO turnoListar() {
		return servicoSistelemarketingServer.turnoListar();
	}
	
	@GetMapping(ContratoRest.URL_ESTADO_LISTAR)
	public EstadoResultadoDTO estadoListar() {
		return servicoSistelemarketingServer.estadoListar();
	}
	
	@GetMapping(ContratoRest.URL_MUNICIPIO_LISTAR)
	public MunicipioResultadoDTO estadoListar(@PathVariable Long id) {
		return servicoSistelemarketingServer.municipioListar(id);
	}
	
	@GetMapping(ContratoRest.URL_BAIRRO_LISTAR)
	public BairroResultadoDTO bairroListar(@PathVariable Long id) {
		return servicoSistelemarketingServer.bairroListar(id);
	}
	
	@PostMapping(ContratoRest.URL_CAMPANHA_EDITAR)
	public CampanhaResultadoDTO campanhaEditar(
			@RequestBody CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDto) {
		return servicoSistelemarketingServer.campanhaEditar(campanhaSolicitacaoEdicaoDto);
	}
	
	@GetMapping(ContratoRest.URL_CAMPANHA_OBTER)
	public CampanhaResultadoDTO campanhaObter(@PathVariable Long id) {
		return servicoSistelemarketingServer.campanhaObter(id);
	}
	
	@GetMapping(ContratoRest.URL_CAMPANHA_LISTAR)
	public CampanhaResultadoDTO campanhaListar() {
		return servicoSistelemarketingServer.campanhaListar();
	}
	
	@PostMapping(ContratoRest.URL_CRITERIO_VALIDAR)
	public CriterioResultadoDTO criterioValidar(@RequestBody CriterioDTO criterioDto) {
		return servicoSistelemarketingServer.criterioValidar(criterioDto);
	}
	
	@PostMapping(ContratoRest.URL_ALOCACAO_OPERADOR_VALIDAR)
	public AlocacaoOperadorResultadoDTO alocacaoOperadorValidar(@RequestBody AlocacaoOperadorDTO alocacaoOperadorDto) {
		return servicoSistelemarketingServer.alocacaoOperadorValidar(alocacaoOperadorDto);
	}
	
	@PostMapping(ContratoRest.URL_LIGAR)
	public LigacaoResultadoDTO ligar(@RequestBody LigacaoDTO ligacaoDto) {
		return servicoSistelemarketingServer.ligar(ligacaoDto);
	}
	
	@GetMapping(ContratoRest.URL_STATUS_RAMAL_OBTER)
	public String obterStatusRamal(@PathVariable String token) {
		return servicoSistelemarketingServer.obterStatusRamal(token);
	}
	
	@GetMapping(ContratoRest.URL_DISTRIBUICAO_CONTATO_OBTER)
	public DistribuicaoContatoResultadoDTO distribuicaoContatoObter(@PathVariable String token) {
		return servicoSistelemarketingServer.distribuicaoContatoObter(token);
	}
	
	@GetMapping(ContratoRest.URL_RAMAL_LISTAR)
	public RamalResultadoDTO ramalListar() {
		return servicoSistelemarketingServer.ramalListar();
	}
	
	@PostMapping(ContratoRest.URL_DOACAO_EDITAR)
	public DoacaoResultadoDTO doacaoEditar(
			@RequestBody DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDto) {
		return servicoSistelemarketingServer.doacaoEditar(doacaoSolicitacaoEdicaoDto);
	}
	
	@GetMapping(ContratoRest.URL_METODO_LISTAR)
	public MetodoResultadoDTO metodoListar() {
		return servicoSistelemarketingServer.metodoListar();
	}
	
	@PostMapping(ContratoRest.URL_VALOR_DETALHADO_VALIDAR)
	public ValorDetalhadoResultadoDTO valorDetalhadoValidar(@RequestBody ValorDetalhadoDTO valorDetalhadoDto) {
		return servicoSistelemarketingServer.valorDetalhadoValidar(valorDetalhadoDto);
	}
}

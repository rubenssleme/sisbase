package br.laramara.sistelemarketingclient.servicos.rest;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

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

@Stateless
@Default
public class ServicoSistelemarketingRest extends ServicoSistelemarketingRestBase
		implements ServicoSistelemarketingClient {
	
	@Override
	public ContaAcessoResultadoDTO contaAcessoEditar(ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDto) {
		return (ContaAcessoResultadoDTO) executarRequisicaoPost(obterUrlContaAcessoEditar(), contaAcessoSolicitacaoEdicaoDto,
				new ContaAcessoResultadoDTO());
	}
	
	@Override
	public ContaAcessoResultadoDTO contaAcessoListar() {
		return (ContaAcessoResultadoDTO) executarRequisicaoGet(obterUrlContaAcessoListar(), new ContaAcessoResultadoDTO());
	}
	
	@Override
	public ContaAcessoResultadoDTO contaAcessoListarTodosOperadoresAtivos() {
		return (ContaAcessoResultadoDTO) executarRequisicaoGet(obterUrlContaAcessoListarTodosOperadoresAtivos(), new ContaAcessoResultadoDTO());
	}

	@Override
	public ContaAcessoResultadoDTO contaAcessoObter(String id) {
		return (ContaAcessoResultadoDTO) executarRequisicaoGet(
				obterUrlContaAcessoObter().replace(ContratoRest.URL_ID, id), new ContaAcessoResultadoDTO());
	}

	@Override
	public ContaAcessoResultadoAutenticacaoDTO contaAcessoAutenticar(CredencialDTO credencialDTO) {
		return (ContaAcessoResultadoAutenticacaoDTO) executarRequisicaoPost(obterUrlContaAcessoAutenticar(), credencialDTO,
				new ContaAcessoResultadoAutenticacaoDTO());
	}
	
	@Override
	public NivelResultadoDTO nivelListar() {
		return (NivelResultadoDTO) executarRequisicaoGet(obterUrlNivelListar(), new NivelResultadoDTO());
	}

	@Override
	public NivelResultadoDTO nivelObter(String id) {
		return (NivelResultadoDTO) executarRequisicaoGet(obterUrlNivelObter().replace(ContratoRest.URL_ID, id),
				new NivelResultadoDTO());
	}

	@Override
	public NivelResultadoDTO nivelEditar(NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDto) {
		return (NivelResultadoDTO) executarRequisicaoPost(obterUrlNivelEditar(), nivelSolicitacaoEdicaoDto,
				new NivelResultadoDTO());
	}

	@Override
	public PermissaoResultadoDTO permissaoListar() {
		return (PermissaoResultadoDTO) executarRequisicaoGet(obterUrlPermissaoListar(), new PermissaoResultadoDTO());
	}

	@Override
	public PermissaoResultadoDTO permissaoObter(String token) {
		return (PermissaoResultadoDTO) executarRequisicaoGet(
				obterUrlPermissaoObter().replace(ContratoRest.URL_TOKEN, token), new PermissaoResultadoDTO());
	}

	@Override
	public TurnoResultadoDTO turnoListar() {
		return (TurnoResultadoDTO) executarRequisicaoGet(obterUrlTurnoListar(), new TurnoResultadoDTO());
	}

	@Override
	public EstadoResultadoDTO estadoListar() {
		return (EstadoResultadoDTO) executarRequisicaoGet(obterUrlEstadoListar(), new EstadoResultadoDTO());
	}

	@Override
	public MunicipioResultadoDTO municipioListar(String idEstado) {
		return (MunicipioResultadoDTO) executarRequisicaoGet(
				obterUrlMunicipioListar().replace(ContratoRest.URL_ID, idEstado.toString()),
				new MunicipioResultadoDTO());
	}

	@Override
	public BairroResultadoDTO bairroListar(String idMunicipio) {
		return (BairroResultadoDTO) executarRequisicaoGet(
				obterUrlBairroListar().replace(ContratoRest.URL_ID, idMunicipio.toString()),
				new BairroResultadoDTO());
	}

	@Override
	public CampanhaResultadoDTO campanhaEditar(CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDto) {
		return (CampanhaResultadoDTO) executarRequisicaoPost(obterUrlCampanhaEditar(), campanhaSolicitacaoEdicaoDto,
				new CampanhaResultadoDTO());
	}

	@Override
	public CampanhaResultadoDTO campanhaObter(String id) {
		return (CampanhaResultadoDTO) executarRequisicaoGet(
				obterUrlCampanhaObter().replace(ContratoRest.URL_ID, id), new CampanhaResultadoDTO());
	}

	@Override
	public CampanhaResultadoDTO campanhaListar() {
		return (CampanhaResultadoDTO) executarRequisicaoGet(obterUrlCampanhaListar(), new CampanhaResultadoDTO());
	}

	@Override
	public CriterioResultadoDTO criterioValidar(CriterioDTO criterioDto) {
		return (CriterioResultadoDTO) executarRequisicaoPost(obterUrlCriterioValidar(), criterioDto,
				new CriterioResultadoDTO());
	}

	@Override
	public AlocacaoOperadorResultadoDTO alocacaoOperadorValidar(AlocacaoOperadorDTO alocacaoOperadorDto) {
		return (AlocacaoOperadorResultadoDTO) executarRequisicaoPost(obterUrlAlocacaoOperadorValidar(),
				alocacaoOperadorDto, new AlocacaoOperadorResultadoDTO());
	}

	@Override
	public String obterStatusRamal(String token) {
		return executarRequisicaoGet(obterUrlStatusRamalObter().replace(ContratoRest.URL_TOKEN, token));
	}

	@Override
	public LigacaoResultadoDTO ligar(LigacaoDTO ligacaoDto) {
		return (LigacaoResultadoDTO) executarRequisicaoPost(obterUrlLigar(), ligacaoDto, new LigacaoResultadoDTO());
	}

	@Override
	public DistribuicaoContatoResultadoDTO distribuicaoContatoObter(String token) {
		return (DistribuicaoContatoResultadoDTO) executarRequisicaoGet(
				obterUrlDistribuicaoContatoObter().replace(ContratoRest.URL_TOKEN, token),
				new DistribuicaoContatoResultadoDTO());
	}

	@Override
	public RamalResultadoDTO ramalListar() {
		return (RamalResultadoDTO) executarRequisicaoGet(obterUrlRamalListar(), new RamalResultadoDTO());
	}

	@Override
	public DoacaoResultadoDTO doacaoEditar(DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDto) {
		return (DoacaoResultadoDTO) executarRequisicaoPost(obterUrlDoacaoEditar(), doacaoSolicitacaoEdicaoDto,
				new DoacaoResultadoDTO());
	}

	@Override
	public MetodoResultadoDTO metodoListar() {
		return (MetodoResultadoDTO) executarRequisicaoGet(obterUrlMetodoListar(), new MetodoResultadoDTO());
	}

	@Override
	public ValorDetalhadoResultadoDTO valorDetalhadoValidar(ValorDetalhadoDTO valorDetalhadoDto) {
		return (ValorDetalhadoResultadoDTO) executarRequisicaoPost(obterUrlValorDetalhadoValidar(), valorDetalhadoDto,
				new ValorDetalhadoResultadoDTO());
	}
}

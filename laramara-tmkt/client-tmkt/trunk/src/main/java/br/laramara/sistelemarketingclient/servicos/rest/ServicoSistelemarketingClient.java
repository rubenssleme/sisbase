package br.laramara.sistelemarketingclient.servicos.rest;

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

public interface ServicoSistelemarketingClient {

	public ContaAcessoResultadoDTO contaAcessoEditar(ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDto);
	
	public ContaAcessoResultadoDTO contaAcessoListar();
	
	public ContaAcessoResultadoDTO contaAcessoListarTodosOperadoresAtivos();
	
	public ContaAcessoResultadoDTO contaAcessoObter(String id);
	
	public ContaAcessoResultadoAutenticacaoDTO contaAcessoAutenticar(CredencialDTO credencialDTO);
	
	public NivelResultadoDTO nivelListar();

	public NivelResultadoDTO nivelObter(String id);
	
	public NivelResultadoDTO nivelEditar(NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDto);
	
	public PermissaoResultadoDTO permissaoListar();
	
	public PermissaoResultadoDTO permissaoObter(String token);
	
	public TurnoResultadoDTO turnoListar();
	
	public EstadoResultadoDTO estadoListar();
	
	public MunicipioResultadoDTO municipioListar(String idEstado);
	
	public BairroResultadoDTO bairroListar(String idMunicipio);
	
	public CampanhaResultadoDTO campanhaEditar(CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDto);

	public CampanhaResultadoDTO campanhaObter(String id);
	
	public CampanhaResultadoDTO campanhaListar();
	
	public CriterioResultadoDTO criterioValidar(CriterioDTO criterioDto);
	
	public AlocacaoOperadorResultadoDTO alocacaoOperadorValidar(AlocacaoOperadorDTO alocacaoOperadorDto);
	
	public String obterStatusRamal(String token);
	
	public LigacaoResultadoDTO ligar(LigacaoDTO ligacaoDto);
	
	public DistribuicaoContatoResultadoDTO distribuicaoContatoObter(String token);
	
	public RamalResultadoDTO ramalListar();
	
	public DoacaoResultadoDTO doacaoEditar(DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDto);
	
	public MetodoResultadoDTO metodoListar();
	
	public ValorDetalhadoResultadoDTO valorDetalhadoValidar(ValorDetalhadoDTO valorDetalhadoDto);
}

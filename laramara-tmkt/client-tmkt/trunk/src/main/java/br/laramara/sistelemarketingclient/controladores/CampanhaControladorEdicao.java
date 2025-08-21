package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.BairroResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.EstadoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.MunicipioDTO;
import br.laramara.sistelemarketingcommons.dtos.MunicipioResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.AlocacaoOperadorResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CampanhaSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioDTO;
import br.laramara.sistelemarketingcommons.dtos.campanha.CriterioResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoDTO;

@Named
@ViewScoped
public class CampanhaControladorEdicao extends Controlador implements Serializable {

	private static final String CRITERIO_ADICIONADO_COM_SUCESSO = "Critério adicionado com sucesso.";
	private static final String CRITERIO_REMOVIDO_COM_SUCESSO = "Critério removido com sucesso.";
	private static final String META_OPERADOR_SALVA_COM_SUCESSO = "Meta do operador salvo com sucesso.";
	private static final String META_OPERADOR_REMOVIDO_COM_SUCESSO = "Meta do operador removido com sucesso.";

	private static final long serialVersionUID = -9116291933055007095L;
	
	private CampanhaDTO campanhaDto;
	
	private EstadoResultadoDTO estadosResultadoDto;
	private MunicipioResultadoDTO municipioResultadoDto;
	private BairroResultadoDTO bairrosResultadoDto;
	private List<SelectItem> estadosDto;
	private List<SelectItem> municipiosDto;
	private List<SelectItem> bairrosDto;
	private String nomeCriterio;
	private String estadoSelecionado;
	private String municipioSelecionado;
	private String bairroSelecionado;
	private Integer quantidadeADistribuir;
	private CriterioDTO criterioDtoSelecionado;
	
	private boolean modoInclusaoAlocacaoOperador;
	private ContaAcessoResultadoDTO contaAcessoOperadoresDto;
	private List<SelectItem> operadoresDto;
	private String operadorSelecionado;
	private BigDecimal metaFinanceiraOperador;
	private Integer metaQuantidadeLigacoesOperador;
	private AlocacaoOperadorDTO alocacaoOperadorDtoSelecionado;
	
	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoEditarCampanha()) {
			carregarDadosAuxiliares();
			carregarCampanha();
			modoInclusaoAlocacaoOperador = true;
		}else {
			redirecionarSemAutorizacao();
		}
	}
	
	private void carregarCampanha() {
		if (possuiParametroId()) {
			CampanhaResultadoDTO campanhaResultadoDto = servicoSisLaraClient.campanhaObter(obterParametroId());
			campanhaDto = campanhaResultadoDto.obterCampanhaDto();
		}else{
			campanhaDto = new CampanhaDTO();
		}
	}

	private void obterListagemTodosOperadoresAtivos() {
		contaAcessoOperadoresDto = (ContaAcessoResultadoDTO)servicoSisLaraClient.contaAcessoListarTodosOperadoresAtivos();
	}

	private void obterListagemEstados() {
		estadosResultadoDto = (EstadoResultadoDTO)servicoSisLaraClient.estadoListar();
	}
	
	private void obterListagemMunicipios() {
		municipioResultadoDto = (MunicipioResultadoDTO) servicoSisLaraClient.municipioListar(estadoSelecionado);
	}
	
	private void obterListagemBairros() {
		bairrosResultadoDto = (BairroResultadoDTO) servicoSisLaraClient.bairroListar(municipioSelecionado);
	}
	
	private void carregarDadosAuxiliares() {
		obterListagemEstados();
		obterListagemTodosOperadoresAtivos();
		estadosDto = converterDTOParaSelectItemComIdEDescricao(estadosResultadoDto.getEstadosDto());
		operadoresDto = converterDTOParaSelectItemComIdEDescricao(contaAcessoOperadoresDto.getContasAcessosDto());
	}
		
	public void mudouEstado() {
	    if (estaComItemSelecionado(estadoSelecionado)) {
	    	deselecionarELimparMunicipioEBairro();
	    	obterListagemMunicipios();
			municipiosDto = converterDTOParaSelectItemComIdEDescricao((municipioResultadoDto).getMunicipiosDto());
	    }else {
	    	limparMunicipios();
	    	limparBairros();
	    }
	}

	public void mudouMunicipio() {
	    if (estaComItemSelecionado(municipioSelecionado)) {
	    	deselecionarBairro();
	    	obterListagemBairros();
			bairrosDto = converterDTOParaSelectItemComDescricao((bairrosResultadoDto).getBairrosDto());
	    }else {
	    	limparBairros();
	    }
	}
	
	private void deselecionarELimparMunicipioEBairro() {
		deselecionarMunicipio();
		deselecionarBairro();
		limparMunicipios();
		limparBairros();
	}
	
	public void adicionarCriterio(ActionEvent actionEvent) {
		CriterioDTO criterioDTO = new CriterioDTO();
		criterioDTO.setNome(nomeCriterio);
		criterioDTO.setMunicipioDto((MunicipioDTO) obterDTOApartirDeId(municipioResultadoDto.getMunicipiosDto(),
				municipioSelecionado));
		criterioDTO.setBairro(bairroSelecionado);
		criterioDTO.setQuantidadeAAdistribuir(quantidadeADistribuir);
		CriterioResultadoDTO criterioResultadoDTO = servicoSisLaraClient.criterioValidar(criterioDTO);
		if (criterioResultadoDTO.sucesso()) {
			campanhaDto.adicionarCriterioDto(criterioDTO);
			resetarCriterio();
			deselecionarELimparMunicipioEBairro();
			exibirMensagemInformacao(CRITERIO_ADICIONADO_COM_SUCESSO);
		} else {
			exibirMensagemErro(criterioResultadoDTO.obterMensagens());
		}
	}

	private void resetarCriterio() {
		nomeCriterio = null;
		estadoSelecionado = null;
		municipioSelecionado = null;
		bairroSelecionado = null;
		quantidadeADistribuir = null;
	}
	
	public void removerCriterio() {
		campanhaDto.removerCriterioDto(criterioDtoSelecionado);
		exibirMensagemInformacao(CRITERIO_REMOVIDO_COM_SUCESSO);
	}

	public void salvar(ActionEvent actionEvent) {
		CampanhaSolicitacaoEdicaoDTO campanhaSolicitacaoEdicaoDto = new CampanhaSolicitacaoEdicaoDTO();
		campanhaSolicitacaoEdicaoDto.setCampanhaDto(campanhaDto);
		campanhaSolicitacaoEdicaoDto.setToken(obterToken());
		CampanhaResultadoDTO campanhaResultadoEdicaoDto = servicoSisLaraClient.campanhaEditar(campanhaSolicitacaoEdicaoDto);
		if (campanhaResultadoEdicaoDto.sucesso()) {
			redirecionarCampanhaGerenciamento();
		} else {
			exibirMensagemErro(campanhaResultadoEdicaoDto.obterMensagens());
		}
	}
	
	public void adicionarAlocacaoOperador(ActionEvent actionEvent) {
		AlocacaoOperadorDTO alocacaoOperadorDtoAAdicionar = null;
		if (modoInclusaoAlocacaoOperador) {
			alocacaoOperadorDtoAAdicionar = new AlocacaoOperadorDTO();
			alocacaoOperadorDtoAAdicionar.setContaAcessoDto((ContaAcessoDTO) obterDTOApartirDeId(
					contaAcessoOperadoresDto.getContasAcessosDto(), operadorSelecionado));
		}else {
			alocacaoOperadorDtoAAdicionar = alocacaoOperadorDtoSelecionado;
		}
		alocacaoOperadorDtoAAdicionar.setMetaFinanceira(metaFinanceiraOperador);
		alocacaoOperadorDtoAAdicionar.setMetaQuantidadeLigacoes(metaQuantidadeLigacoesOperador);
		AlocacaoOperadorResultadoDTO alocacaoOperadorResultadoDTO = servicoSisLaraClient
				.alocacaoOperadorValidar(alocacaoOperadorDtoAAdicionar);
		if (alocacaoOperadorResultadoDTO.sucesso()) {
			campanhaDto.adicionarAlocacaoOperadorDto(alocacaoOperadorDtoAAdicionar);
			resetarAlocacaoOperador();
			exibirMensagemInformacao(META_OPERADOR_SALVA_COM_SUCESSO);
		} else {
			exibirMensagemErro(alocacaoOperadorResultadoDTO.obterMensagens());
		}
	}
	
	public void cancelarAlteracaoAlocacaoOperador(ActionEvent actionEvent) {
		resetarAlocacaoOperador();
	}

	private void resetarAlocacaoOperador() {
		modoInclusaoAlocacaoOperador = true;
		alocacaoOperadorDtoSelecionado = null;
		operadorSelecionado = null;
		metaFinanceiraOperador = null;
		metaQuantidadeLigacoesOperador = null;
	}

	public void removerAlocacaoOperador() {
		campanhaDto.removerAlocacaoOperadorDto(alocacaoOperadorDtoSelecionado);
		resetarAlocacaoOperador();
		exibirMensagemInformacao(META_OPERADOR_REMOVIDO_COM_SUCESSO);
	}
	
	public void alterarAlocacaoOperador() {
		metaFinanceiraOperador = alocacaoOperadorDtoSelecionado.getMetaFinanceira();
		metaQuantidadeLigacoesOperador = alocacaoOperadorDtoSelecionado.getMetaQuantidadeLigacoes();
		modoInclusaoAlocacaoOperador = false;
	}
	
	public Integer getQuantidadeADistribuir() {
		return quantidadeADistribuir;
	}

	public void setQuantidadeADistribuir(Integer quantidadeADistribuir) {
		this.quantidadeADistribuir = quantidadeADistribuir;
	}

	public CampanhaDTO getCampanhaDto() {
		return campanhaDto;
	}

	public void setCampanhaDto(CampanhaDTO campanhaDto) {
		this.campanhaDto = campanhaDto;
	}

	private void deselecionarMunicipio() {
		municipioSelecionado = null;
	}

	private void limparMunicipios() {
		municipiosDto = new ArrayList<>();
	}

	private void deselecionarBairro() {
		bairroSelecionado = null;
	}
	
	private void limparBairros() {
		bairrosDto = new ArrayList<>();
	}

	public List<SelectItem> getEstadosDto() {
		return estadosDto;
	}

	public void setEstadosDto(List<SelectItem> estadosDto) {
		this.estadosDto = estadosDto;
	}
	
	public List<SelectItem> getMunicipiosDto() {
		return municipiosDto;
	}

	public void setMunicipiosDto(List<SelectItem> municipiosDto) {
		this.municipiosDto = municipiosDto;
	}

	public String getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public void setEstadoSelecionado(String estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}
	
	public String getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(String municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}	

	public List<SelectItem> getBairrosDto() {
		return bairrosDto;
	}

	public void setBairrosDto(List<SelectItem> bairrosDto) {
		this.bairrosDto = bairrosDto;
	}

	public String getBairroSelecionado() {
		return bairroSelecionado;
	}

	public void setBairroSelecionado(String bairroSelecionado) {
		this.bairroSelecionado = bairroSelecionado;
	}
	
	public boolean isModoInclusaoAlocacaoOperador() {
		return modoInclusaoAlocacaoOperador;
	}

	public String getOperadorSelecionado() {
		return operadorSelecionado;
	}

	public void setOperadorSelecionado(String operadorSelecionado) {
		this.operadorSelecionado = operadorSelecionado;
	}
	
	public List<SelectItem> getOperadoresDto() {
		return operadoresDto;
	}

	public void setOperadoresDto(List<SelectItem> operadoresDto) {
		this.operadoresDto = operadoresDto;
	}
	
	public BigDecimal getMetaFinanceiraOperador() {
		return metaFinanceiraOperador;
	}

	public void setMetaFinanceiraOperador(BigDecimal metaFinanceiraOperador) {
		this.metaFinanceiraOperador = metaFinanceiraOperador;
	}

	public Integer getMetaQuantidadeLigacoesOperador() {
		return metaQuantidadeLigacoesOperador;
	}

	public void setMetaQuantidadeLigacoesOperador(Integer metaQuantidadeLigacoesOperador) {
		this.metaQuantidadeLigacoesOperador = metaQuantidadeLigacoesOperador;
	}
	
	public AlocacaoOperadorDTO getAlocacaoOperadorDtoSelecionado() {
		return alocacaoOperadorDtoSelecionado;
	}

	public void setAlocacaoOperadorDtoSelecionado(AlocacaoOperadorDTO alocacaoOperadorDtoSelecionado) {
		this.alocacaoOperadorDtoSelecionado = alocacaoOperadorDtoSelecionado;
	}
	
	public CriterioDTO getCriterioDtoSelecionado() {
		return criterioDtoSelecionado;
	}

	public void setCriterioDtoSelecionado(CriterioDTO criterioDtoSelecionado) {
		this.criterioDtoSelecionado = criterioDtoSelecionado;
	}
	
	public String getNomeCriterio() {
		return nomeCriterio;
	}

	public void setNomeCriterio(String nomeCriterio) {
		this.nomeCriterio = nomeCriterio;
	}
}

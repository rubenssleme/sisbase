package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.TelefoneDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.DoacaoSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.MetodoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.MetodoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.TipoRetiradaDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.ValorDetalhadoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.ValorDetalhadoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.LigacaoDTO;

@Named
@ViewScoped
public class ListagemControlador extends Controlador implements Serializable {

	private static final long serialVersionUID = -4055651199753830947L;

	private static final String VALOR_DETALHADO_ADICIONADO_COM_SUCESSO = "Valor de doação adicionado com sucesso.";
	private static final String VALOR_DETALHADO_REMOVIDO_COM_SUCESSO = "Valor de doação removido com sucesso.";
	private static final String DOACAO_EFETUADA_COM_SUCESSO = "Doação efetuada com sucesso.";
	
	private boolean parado;
	private String statusObtido;
	private String statusAExibir;
	private DistribuicaoContatoDTO distribuicaoContatoDtoSelecionado;
	private TelefoneDTO telefoneDtoSelecionado;
	
	private MetodoResultadoDTO metodoResultadoDto;
	private boolean exibirBotaoEfetuarDoacao;
	private boolean modoInclusaoDoacao;
	private List<SelectItem> metodosDto;
	private List<SelectItem> tiposRetiradasDto;
	
	private boolean modoInclusaoValorDetalhado;
	private String metodoSelecionado;
	private String tipoRetiradaSelecionado;
	private BigDecimal valorDoacao;
	private Date dataEfetuacao;
	private DoacaoDTO doacaoDto;
	private ValorDetalhadoDTO valorDetalhadoSelecionadoDto;
	
	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoVisualizarListagem()) {
			carregarDadosAuxiliares();
		}else {
			redirecionarSemAutorizacao();
		}
	}
	
	private void carregarDadosAuxiliares() {
		obterListagemMetodos();
		metodosDto = converterDTOParaSelectItemComDescricao(metodoResultadoDto.getMetodosDto());
	}
	
	private void obterListagemMetodos() {
		metodoResultadoDto = ((MetodoResultadoDTO) servicoSisLaraClient.metodoListar());
	}

	public void exibirMensagemAtualizada(){
		statusObtido = servicoSisLaraClient.obterStatusRamal(obterToken());
		if (statusAExibir == null || !statusAExibir.equals(statusObtido)) {
			statusAExibir = statusObtido;
			exibirMensagemInformacaoForcandoAtualizacao(statusAExibir.toString());
		}
	}
	
	public void obterContato() {
		DistribuicaoContatoResultadoDTO distribuicaoContatoResultadoDto = servicoSisLaraClient.distribuicaoContatoObter(obterToken());
		if (distribuicaoContatoResultadoDto.sucesso()) {
			distribuicaoContatoDtoSelecionado = distribuicaoContatoResultadoDto.obterDistribuicaoContatoDto();
			exibirMensagemInformacao("Contato obtido com sucesso.");
			habilitarBotaoEfetuarDoacao();
		}else {
			exibirMensagemErro(distribuicaoContatoResultadoDto.obterMensagens());
		}
	}
	
	public void ligar() {
		LigacaoDTO ligacaoDTO = new LigacaoDTO();
		ligacaoDTO.setToken(obterToken());
		ligacaoDTO.setTelefoneDto(telefoneDtoSelecionado);
		servicoSisLaraClient.ligar(ligacaoDTO);
	}
	
	public void efetuarDoacao() {
		if (!modoInclusaoDoacao) {
			limparDoacaoDto();
			habilitarInclusaoDoacao();
			habilitarInclusaoValorDetalhado();
		}
	}

	private void habilitarInclusaoDoacao() {
		modoInclusaoDoacao = true;
	}
	
	private void desabilitarInclusaoDoacao() {
		modoInclusaoDoacao = false;
	}

	private void habilitarInclusaoValorDetalhado() {
		modoInclusaoValorDetalhado = true;
	}
	
	public void salvarValorDetalhado() {
		ValorDetalhadoDTO valorDetalhadoAAdicionar = null;
		if (valorDetalhadoSelecionadoDto == null) {
			valorDetalhadoAAdicionar = new ValorDetalhadoDTO();
		} else {
			valorDetalhadoAAdicionar = valorDetalhadoSelecionadoDto;
		}
		valorDetalhadoAAdicionar.setMetodoDto(obterMetodoDTOSelecionado());
		valorDetalhadoAAdicionar.setTipoRetiradaDto((TipoRetiradaDTO) obterDTOApartirDeDescricao(
				obterMetodoDTOSelecionado().getTiposRetiradasDto(), tipoRetiradaSelecionado));
		valorDetalhadoAAdicionar.setValor(valorDoacao);
		valorDetalhadoAAdicionar.setDataEfetuacao(dataEfetuacao);
		ValorDetalhadoResultadoDTO valorDetalhadoResultadoDTO = servicoSisLaraClient
				.valorDetalhadoValidar(valorDetalhadoAAdicionar);
		if (valorDetalhadoResultadoDTO.sucesso()) {
			doacaoDto.adicionar(valorDetalhadoAAdicionar);
			limparCamposValorDetalhado();
			desabilitarAdicaoValorDetalhado();
			exibirMensagemInformacao(VALOR_DETALHADO_ADICIONADO_COM_SUCESSO);
		} else {
			exibirMensagemErro(valorDetalhadoResultadoDTO.getMensagem());
		}
	}
	
	public void salvarDoacao() {
		doacaoDto.setDistribuicaoContatoDto(distribuicaoContatoDtoSelecionado);
		DoacaoSolicitacaoEdicaoDTO doacaoSolicitacaoEdicaoDto = new DoacaoSolicitacaoEdicaoDTO();
		doacaoSolicitacaoEdicaoDto.setDoacaoDto(doacaoDto);
		doacaoSolicitacaoEdicaoDto.setToken(obterToken());
		DoacaoResultadoDTO doacaoResultadoEdicaoDto = servicoSisLaraClient.doacaoEditar(doacaoSolicitacaoEdicaoDto);
		if (doacaoResultadoEdicaoDto.sucesso()) {
			exibirMensagemInformacao(DOACAO_EFETUADA_COM_SUCESSO);
			desabilitarBotaoEfetuarDoacao();
			desabilitarInclusaoDoacao();
		} else {
			exibirMensagemErro(doacaoResultadoEdicaoDto.obterMensagens());
		}
	}

	private void limparCamposValorDetalhado() {
		valorDetalhadoSelecionadoDto = null;
		tipoRetiradaSelecionado = null;
		metodoSelecionado = null;
		valorDoacao = null;
		dataEfetuacao = null;
	}
	
	public void adcionarValorDetalhado() {
		limparCamposValorDetalhado();
		habilitarInclusaoValorDetalhado();
	}
	
	public void cancelarValorDetalhado() {
		desabilitarAdicaoValorDetalhado();
		limparCamposValorDetalhado();
	}

	private void desabilitarAdicaoValorDetalhado() {
		modoInclusaoValorDetalhado = false;
	}
	
	public void mudouMetodo() {
		if (estaComItemSelecionado(metodoSelecionado)) {
			deselecionarTipoRetirada();
			obterListagemTipoRetirada();
		}
	}
	
	private void obterListagemTipoRetirada() {
		MetodoDTO metodoDto = obterMetodoDTOSelecionado();
		tiposRetiradasDto = converterDTOParaSelectItemComDescricao((metodoDto.getTiposRetiradasDto()));
	}

	private MetodoDTO obterMetodoDTOSelecionado() {
		return (MetodoDTO) obterDTOApartirDeDescricao(metodoResultadoDto.getMetodosDto(), metodoSelecionado);
	}
	
	private void deselecionarTipoRetirada() {
		tipoRetiradaSelecionado = null;
	}
	
	public void alterarValorDetalhado() {
		habilitarInclusaoValorDetalhado();
		metodoSelecionado = valorDetalhadoSelecionadoDto.getMetodoDto().getMetodo();
		tipoRetiradaSelecionado = valorDetalhadoSelecionadoDto.getTipoRetiradaDto().getTipoRetirada();
		dataEfetuacao = valorDetalhadoSelecionadoDto.getDataEfetuacao();
		valorDoacao = valorDetalhadoSelecionadoDto.getValor();
	}
	
	public void removerValorDetalhado() {
		doacaoDto.removerValorDetalhadoDto(valorDetalhadoSelecionadoDto);
		limparCamposValorDetalhado();
		exibirMensagemInformacao(VALOR_DETALHADO_REMOVIDO_COM_SUCESSO);
	}
	
	public void cancelarDoacao() {
		limparDoacaoDto();
		limparCamposValorDetalhado();
		desabilitarInclusaoDoacao();
		cancelarValorDetalhado();
	}

	private void desabilitarBotaoEfetuarDoacao() {
		exibirBotaoEfetuarDoacao = false;
	}
	
	private void habilitarBotaoEfetuarDoacao() {
		exibirBotaoEfetuarDoacao = true;
	}

	private void limparDoacaoDto() {
		doacaoDto = new DoacaoDTO();
	}
	
	public boolean isParado() {
		return parado;
	}

	public void setParado(boolean parado) {
		this.parado = parado;
	}

	public DistribuicaoContatoDTO getDistribuicaoContatoDtoSelecionado() {
		return distribuicaoContatoDtoSelecionado;
	}

	public void setDistribuicaoContatoDtoSelecionado(DistribuicaoContatoDTO distribuicaoContatoDtoSelecionado) {
		this.distribuicaoContatoDtoSelecionado = distribuicaoContatoDtoSelecionado;
	}

	public TelefoneDTO getTelefoneDtoSelecionado() {
		return telefoneDtoSelecionado;
	}

	public void setTelefoneDtoSelecionado(TelefoneDTO telefoneDtoSelecionado) {
		this.telefoneDtoSelecionado = telefoneDtoSelecionado;
	}

	public boolean isModoInclusaoDoacao() {
		return modoInclusaoDoacao;
	}

	public void setModoInclusaoDoacao(boolean modoInclusaoDoacao) {
		this.modoInclusaoDoacao = modoInclusaoDoacao;
	}

	public DoacaoDTO getDoacaoDto() {
		return doacaoDto;
	}

	public void setDoacaoDto(DoacaoDTO doacaoDto) {
		this.doacaoDto = doacaoDto;
	}

	public ValorDetalhadoDTO getValorDetalhadoSelecionadoDto() {
		return valorDetalhadoSelecionadoDto;
	}

	public void setValorDetalhadoSelecionadoDto(ValorDetalhadoDTO valorDetalhadoSelecionadoDto) {
		this.valorDetalhadoSelecionadoDto = valorDetalhadoSelecionadoDto;
	}

	public String getMetodoSelecionado() {
		return metodoSelecionado;
	}

	public void setMetodoSelecionado(String metodoSelecionado) {
		this.metodoSelecionado = metodoSelecionado;
	}

	public String getTipoRetiradaSelecionado() {
		return tipoRetiradaSelecionado;
	}

	public void setTipoRetiradaSelecionado(String tipoRetiradaSelecionado) {
		this.tipoRetiradaSelecionado = tipoRetiradaSelecionado;
	}

	public List<SelectItem> getMetodosDto() {
		return metodosDto;
	}

	public void setMetodosDto(List<SelectItem> metodosDto) {
		this.metodosDto = metodosDto;
	}

	public List<SelectItem> getTiposRetiradasDto() {
		return tiposRetiradasDto;
	}

	public void setTiposRetiradasDto(List<SelectItem> tiposRetiradasDto) {
		this.tiposRetiradasDto = tiposRetiradasDto;
	}

	public BigDecimal getValorDoacao() {
		return valorDoacao;
	}

	public void setValorDoacao(BigDecimal valorDoacao) {
		this.valorDoacao = valorDoacao;
	}

	public Date getDataEfetuacao() {
		return dataEfetuacao;
	}

	public void setDataEfetuacao(Date dataEfetuacao) {
		this.dataEfetuacao = dataEfetuacao;
	}

	public boolean isModoInclusaoValorDetalhado() {
		return modoInclusaoValorDetalhado;
	}

	public void setModoInclusaoValorDetalhado(boolean modoInclusaoValorDetalhado) {
		this.modoInclusaoValorDetalhado = modoInclusaoValorDetalhado;
	}

	public boolean isExibirBotaoEfetuarDoacao() {
		return exibirBotaoEfetuarDoacao;
	}

	public void setExibirBotaoEfetuarDoacao(boolean exibirBotaoEfetuarDoacao) {
		this.exibirBotaoEfetuarDoacao = exibirBotaoEfetuarDoacao;
	}
}

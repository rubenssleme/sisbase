package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoResultadoDTO;

@Named
@ViewScoped
public class ContaAcessoControladorEdicao extends Controlador implements Serializable {

	private static final long serialVersionUID = -3577484978922611850L;

	private List<SelectItem> niveisDto;
	private List<SelectItem> turnosDto;
	
	private String nivelSelecionadoDto;
	private String turnoSelecionadoDto;
	
	private ContaAcessoDTO contaAcessoDto;
	
	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoVisualizarContaAcesso()) {
			carregarContaAcesso();
		}else {
			redirecionarSemAutorizacao();
		}
	}
	
	private void carregarContaAcesso() {
		carregarDadosAuxiliares();
		if (possuiParametroId()) {
			ContaAcessoResultadoDTO contaAcessoResultadoDto = servicoSisLaraClient.contaAcessoObter(obterParametroId());
			contaAcessoDto = contaAcessoResultadoDto.obterContaAcessoDto();
			marcarNivelSelecionado();
		}else{
			contaAcessoDto = new ContaAcessoDTO();
		}
	}

	private void marcarNivelSelecionado() {
		nivelSelecionadoDto = contaAcessoDto.getNivelDto().getId().toString();
		turnoSelecionadoDto = contaAcessoDto.getTurnoDto() != null ? contaAcessoDto.getTurnoDto().toString() : "";
	}

	private void carregarDadosAuxiliares() {
		niveisDto = converterDTOParaSelectItemComIdEDescricao(((NivelResultadoDTO)servicoSisLaraClient.nivelListar()).getNiveisDto());
		turnosDto = converterDTOParaSelectItemComDescricao(((TurnoResultadoDTO)servicoSisLaraClient.turnoListar()).getTurnosDto());
	}

	public ContaAcessoDTO getContaAcessoDto() {
		return contaAcessoDto;
	}

	public List<SelectItem> getNiveisDto() {
		return niveisDto;
	}

	public void setNiveisDto(List<SelectItem> niveisDto) {
		this.niveisDto = niveisDto;
	}
	
	public List<SelectItem> getTurnosDto() {
		return turnosDto;
	}

	public void setTurnosDto(List<SelectItem> turnosDto) {
		this.turnosDto = turnosDto;
	}

	public String getNivelSelecionadoDto() {
		return nivelSelecionadoDto;
	}

	public void setNivelSelecionadoDto(String nivelSelecionadoDto) {
		this.nivelSelecionadoDto = nivelSelecionadoDto;
	}
	
	public String getTurnoSelecionadoDto() {
		return turnoSelecionadoDto;
	}

	public void setTurnoSelecionadoDto(String turnoSelecionadoDto) {
		this.turnoSelecionadoDto = turnoSelecionadoDto;
	}

	private NivelDTO obterNivelSelecionado() {
		NivelDTO nivelDTO = new NivelDTO();
		nivelDTO.setId(Long.parseLong(nivelSelecionadoDto));
		return nivelDTO;
	}
	
	private TurnoDTO obterTurnoSelecionado() {
		return estaComItemSelecionado(turnoSelecionadoDto) ? new TurnoDTO(turnoSelecionadoDto) : null;
	}

	public void salvar(ActionEvent actionEvent) {
		contaAcessoDto.setNivelDto(obterNivelSelecionado());
		contaAcessoDto.setTurnoDto(obterTurnoSelecionado());
		
		ContaAcessoSolicitacaoEdicaoDTO contaAcessoSolicitacaoEdicaoDto = new ContaAcessoSolicitacaoEdicaoDTO();
		contaAcessoSolicitacaoEdicaoDto.setContaAcessoDto(contaAcessoDto);
		contaAcessoSolicitacaoEdicaoDto.setToken(obterToken());
		ContaAcessoResultadoDTO contaAcessoResultadoEdicaoDto = servicoSisLaraClient.contaAcessoEditar(contaAcessoSolicitacaoEdicaoDto);
		if (contaAcessoResultadoEdicaoDto.sucesso()) {
			redirecionarContaAcessoGerenciamento();
		} else {
			exibirMensagemErro(contaAcessoResultadoEdicaoDto.obterMensagens());
		}
	}
}


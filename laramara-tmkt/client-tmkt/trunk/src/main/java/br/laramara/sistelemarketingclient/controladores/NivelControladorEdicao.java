package br.laramara.sistelemarketingclient.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelResultadoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.NivelSolicitacaoEdicaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoResultadoDTO;

@Named
@ViewScoped
public class NivelControladorEdicao extends Controlador implements Serializable {

	private static final long serialVersionUID = -3577484978922611850L;

	private List<SelectItem> permissoesDto;
	private List<String> permissoesSelecionadasDto;
	
	private NivelDTO nivelDto;
	
	@PostConstruct
	public void inicializar() {
		if (possuiPermissaoVisualizarNivel()) {
			carregarNivel();
		}else {
			redirecionarSemAutorizacao();
		}
	}
	
	private void carregaPermissoes() {
		PermissaoResultadoDTO permissaoResultadoListagemDto = servicoSisLaraClient.permissaoListar();
		permissoesDto = converterDTOParaSelectItem(permissaoResultadoListagemDto.getPermissoesDto());
	}
	
	private void carregarNivel() {
		carregaPermissoes();
		if (possuiParametroId()) {
			NivelResultadoDTO nivelResultadoDto = servicoSisLaraClient.nivelObter(obterParametroId());
			nivelDto = nivelResultadoDto.obterNivelDto();
			marcarPermissoesSelecionadas();
		}else{
			nivelDto = new NivelDTO();
		}
	}

	private void marcarPermissoesSelecionadas() {
		permissoesSelecionadasDto = nivelDto.getPermissoesDto().stream().map(permissao -> permissao.getId().toString())
				.collect(Collectors.toList());
	}

	private List<SelectItem> converterDTOParaSelectItem(List<PermissaoDTO> permissoesDto){
		List<SelectItem> listas = new ArrayList<>();
		for(PermissaoDTO permissaoDTO : permissoesDto) {
			listas.add(new SelectItem(permissaoDTO.getId(), permissaoDTO.getDescricao()));
		}
		return listas;
	}

	public NivelDTO getNivelDto() {
		return nivelDto;
	}
	
	public List<SelectItem> getPermissoesDto() {
		return permissoesDto;
	}

	public void setPermissoesDto(List<SelectItem> permissoesDto) {
		this.permissoesDto = permissoesDto;
	}
	
	public List<String> getPermissoesSelecionadasDto() {
		return permissoesSelecionadasDto;
	}

	public void setPermissoesSelecionadasDto(List<String> permissoesSelecionadasDto) {
		this.permissoesSelecionadasDto = permissoesSelecionadasDto;
	}

	private List<PermissaoDTO> obterPermissoesSelecionadas(List<String> permissoes){
		List<PermissaoDTO> permissoesDtos = new ArrayList<>();
		for(String permissao : permissoes) {
			PermissaoDTO permissaoDTO = new PermissaoDTO();
			permissaoDTO.setId(Long.parseLong(permissao));
			permissoesDtos.add(permissaoDTO);
		}
		return permissoesDtos;
	}
	
	public void salvar(ActionEvent actionEvent) {
		nivelDto.setPermissoesDto(obterPermissoesSelecionadas(permissoesSelecionadasDto));
		
		NivelSolicitacaoEdicaoDTO nivelSolicitacaoEdicaoDto = new NivelSolicitacaoEdicaoDTO();
		nivelSolicitacaoEdicaoDto.setNivelDto(nivelDto);
		nivelSolicitacaoEdicaoDto.setToken(obterToken());
		NivelResultadoDTO nivelResultadoEdicaoDto = servicoSisLaraClient.nivelEditar(nivelSolicitacaoEdicaoDto);
		if (nivelResultadoEdicaoDto.sucesso()) {
			redirecionarNivelGerenciamento();
		} else {
			exibirMensagemErro(nivelResultadoEdicaoDto.obterMensagens());
		}
	}
}

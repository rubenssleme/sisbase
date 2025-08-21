package br.laramara.ti.sislaracommons.dtos.doacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;

public class EspecificacaoGeracaoDemandaDTO implements Serializable {

	private static final long serialVersionUID = -1454840199962796438L;
	
	private ProjetoDTO projetoDto;
	private List<RecursoDTO> recursosDto;
	private GrupoDTO grupoDto;
	private UsuarioDTO usuariosDto;
	private PreCadastroDTO preCadastrosDto;
	private boolean cartelaDeSelos;
	private List<DocumentoSolicitacaoDoacaoDTO> documentosSolicitacaoDocacaoDto;

	public EspecificacaoGeracaoDemandaDTO() {
		documentosSolicitacaoDocacaoDto = new ArrayList<>();
		recursosDto = new ArrayList<>();
	}

	public ProjetoDTO getProjetoDto() {
		return projetoDto;
	}

	public void setProjetoDto(ProjetoDTO projetoDto) {
		this.projetoDto = projetoDto;
	}

	public List<RecursoDTO> getRecursosDto() {
		return recursosDto;
	}

	public void setRecursosDto(List<RecursoDTO> recursosDto) {
		this.recursosDto = recursosDto;
	}

	public GrupoDTO getGrupoDto() {
		return grupoDto;
	}

	public void setGrupoDto(GrupoDTO grupoDto) {
		this.grupoDto = grupoDto;
	}

	public UsuarioDTO getUsuariosDto() {
		return usuariosDto;
	}

	public void setUsuariosDto(UsuarioDTO usuariosDto) {
		this.usuariosDto = usuariosDto;
	}

	public PreCadastroDTO getPreCadastrosDto() {
		return preCadastrosDto;
	}

	public void setPreCadastrosDto(PreCadastroDTO preCadastrosDto) {
		this.preCadastrosDto = preCadastrosDto;
	}

	public List<DocumentoSolicitacaoDoacaoDTO> getDocumentosSolicitacaoDocacaoDto() {
		return documentosSolicitacaoDocacaoDto;
	}

	public void setDocumentosSolicitacaoDocacaoDto(List<DocumentoSolicitacaoDoacaoDTO> documentosSolicitacaoDocacaoDto) {
		this.documentosSolicitacaoDocacaoDto = documentosSolicitacaoDocacaoDto;
	}

	public boolean isCartelaDeSelos() {
		return cartelaDeSelos;
	}

	public void setCartelaDeSelos(boolean cartelaDeSelos) {
		this.cartelaDeSelos = cartelaDeSelos;
	}
}

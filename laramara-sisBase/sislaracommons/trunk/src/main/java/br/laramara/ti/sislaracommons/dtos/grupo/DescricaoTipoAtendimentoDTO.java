package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;

public class DescricaoTipoAtendimentoDTO extends ModeloDTO {

	private static final long serialVersionUID = 5648456169150561672L;

	private Long id;
	private String nome;
	private String sigla;
	private String descricao;
	private List<SetorDTO> setoresDto;
	private TipoAtendimentoDTO tipoAtendimentoDto;
	private List<NomeGrupoDTO> nomesGruposDto;
	private List<ModuloDTO> modulosDto;

	public DescricaoTipoAtendimentoDTO() {
		setoresDto = new ArrayList<>();
		modulosDto = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescrica() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<SetorDTO> getSetoresDto() {
		return setoresDto;
	}

	public void setSetoresDto(List<SetorDTO> setoresDto) {
		this.setoresDto = setoresDto;
	}

	public TipoAtendimentoDTO getTipoAtendimentoDto() {
		return tipoAtendimentoDto;
	}

	public void setTipoAtendimentoDto(TipoAtendimentoDTO tipoAtendimentoDto) {
		this.tipoAtendimentoDto = tipoAtendimentoDto;
	}
	
	public List<NomeGrupoDTO> getNomesGruposDto() {
		return nomesGruposDto;
	}

	public void setNomesGruposDto(List<NomeGrupoDTO> nomesGruposDto) {
		this.nomesGruposDto = nomesGruposDto;
	}

	public List<ModuloDTO> getModulosDto() {
		return modulosDto;
	}

	public void setModulosDto(List<ModuloDTO> modulosDto) {
		this.modulosDto = modulosDto;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescricaoTipoAtendimentoDTO other = (DescricaoTipoAtendimentoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

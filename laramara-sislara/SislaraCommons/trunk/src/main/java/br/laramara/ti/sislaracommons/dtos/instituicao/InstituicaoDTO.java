package br.laramara.ti.sislaracommons.dtos.instituicao;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DiretoriaEnsinoDTO;
import br.laramara.ti.sislaracommons.dtos.escola.DreCefaiDTO;
import br.laramara.ti.sislaracommons.dtos.escola.EscolaridadeDTO;
import br.laramara.ti.sislaracommons.dtos.escola.TipoApoioDTO;
import br.laramara.ti.sislaracommons.dtos.escola.TipoEspecialidadeDTO;
import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;

public class InstituicaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 7814153768208862803L;

	private Long id;
	private TipoInstituicaoDTO tipoInstituicaoDto;
	private String nome;
	private EnderecoDTO enderecoDto;
	private ContatoDTO contatoDto;
	private String nomeCoordenadorResponsavel;
	private ClassificacaoInstituicaoDTO classificacaoDto;
	private String obs;
	private TipoApoioDTO tipoApoioDto;
	private List<TipoEspecialidadeDTO> tiposEspecialidadeDTO;
	private DreCefaiDTO dreCefaiDto;
	private DiretoriaEnsinoDTO diretoriaEnsinoDto;
	private List<EscolaridadeDTO> escolaridadesDto;
	
	public InstituicaoDTO() {
		enderecoDto = new EnderecoDTO();
		contatoDto = new ContatoDTO();
		tiposEspecialidadeDTO = new ArrayList<>();
		escolaridadesDto = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoInstituicaoDTO getTipoInstituicaoDto() {
		return tipoInstituicaoDto;
	}

	public void setTipoInstituicaoDto(TipoInstituicaoDTO tipo) {
		this.tipoInstituicaoDto = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnderecoDTO getEnderecoDto() {
		return enderecoDto;
	}

	public void setEnderecoDto(EnderecoDTO enderecoDto) {
		this.enderecoDto = enderecoDto;
	}
	
	public ContatoDTO getContatoDto() {
		return contatoDto;
	}
	
	public void setContatoDto(ContatoDTO contatoDto) {
		this.contatoDto = contatoDto;
	}

	public String getNomeCoordenadorResponsavel() {
		return nomeCoordenadorResponsavel;
	}

	public void setNomeCoordenadorResponsavel(String nomeCoordenadorResponsavel) {
		this.nomeCoordenadorResponsavel = nomeCoordenadorResponsavel;
	}

	public ClassificacaoInstituicaoDTO getClassificacaoInstituicaoDto() {
		return classificacaoDto;
	}

	public void setClassificacaoInstituicaoDto(
			ClassificacaoInstituicaoDTO classificacaoDto) {
		this.classificacaoDto = classificacaoDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public TipoApoioDTO getTipoApoioDto() {
		return tipoApoioDto;
	}

	public void setTipoApoioDto(TipoApoioDTO tipoApoioDto) {
		this.tipoApoioDto = tipoApoioDto;
	}

	public List<TipoEspecialidadeDTO> getTiposEspecialidadeDTO() {
		return tiposEspecialidadeDTO;
	}

	public void setTiposEspecialidadeDTO(List<TipoEspecialidadeDTO> tiposEspecialidadeDTO) {
		this.tiposEspecialidadeDTO = tiposEspecialidadeDTO;
	}

	public DreCefaiDTO getDreCefaiDto() {
		return dreCefaiDto;
	}
	
	public void setDreCefaiDto(DreCefaiDTO dreCefaiDto) {
		this.dreCefaiDto = dreCefaiDto;
	}

	public DiretoriaEnsinoDTO getDiretoriaEnsinoDto() {
		return diretoriaEnsinoDto;
	}

	public void setDiretoriaEnsinoDto(DiretoriaEnsinoDTO diretoriaEnsinoDto) {
		this.diretoriaEnsinoDto = diretoriaEnsinoDto;
	}

	public List<EscolaridadeDTO> getEscolaridadesDto() {
		return escolaridadesDto;
	}

	public void setEscolaridadesDto(List<EscolaridadeDTO> escolaridadesDto) {
		this.escolaridadesDto = escolaridadesDto;
	}

	@Override
	public String toString() {
		return nome
				+ (tipoApoioDto != null ? " / " + tipoApoioDto.toString() : "")
				+ (!tiposEspecialidadeDTO.isEmpty() ? " / "
						+ TextoUtils.removerChaves(tiposEspecialidadeDTO
								.toString()) : "")
				+ (dreCefaiDto != null ? " / " + dreCefaiDto.toString() : "")
				+ (diretoriaEnsinoDto != null ? " / " + diretoriaEnsinoDto.toString() : "")
				+ (enderecoDto != null ? " / Município: " + enderecoDto.getMunicipioDto() : "");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstituicaoDTO other = (InstituicaoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

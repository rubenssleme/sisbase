package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;

public class CertidaoDTO extends ModeloDTO {
	private static final long serialVersionUID = 8829144191875846490L;

	private Long id;
	private TipoCertidaoDTO tipoCertidaoDto;
	private String numero;
	private String livro;
	private String folha;
	private String distrito;
	private UfDTO ufDto;
	private MunicipioDTO municipioDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCertidaoDTO getTipoCertidaoDto() {
		return tipoCertidaoDto;
	}

	public void setTipoCertidaoDto(TipoCertidaoDTO tipoCertidaoDto) {
		this.tipoCertidaoDto = tipoCertidaoDto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public UfDTO getUfDto() {
		return ufDto;
	}

	public void setUfDto(UfDTO ufDto) {
		this.ufDto = ufDto;
	}

	public MunicipioDTO getMunicipioDto() {
		return municipioDto;
	}

	public void setMunicipioDto(MunicipioDTO municipioDto) {
		this.municipioDto = municipioDto;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertidaoDTO other = (CertidaoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

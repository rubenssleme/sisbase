package br.laramara.sistelemarketingcommons.dtos.campanha;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.MunicipioDTO;

public class CriterioDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 3919127384421846754L;

	private Long id;
	private String nome;
	private MunicipioDTO municipioDto;
	private String bairro;
	private Integer quantidadeAAdistribuir;

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
	
	public MunicipioDTO getMunicipioDto() {
		return municipioDto;
	}

	public void setMunicipioDto(MunicipioDTO municipioDto) {
		this.municipioDto = municipioDto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Integer getQuantidadeAAdistribuir() {
		return quantidadeAAdistribuir;
	}

	public void setQuantidadeAAdistribuir(Integer quantidadeAAdistribuir) {
		this.quantidadeAAdistribuir = quantidadeAAdistribuir;
	}

	@Override
	public String toString() {
		return nome + "(Distribuir " + quantidadeAAdistribuir + " de " + municipioDto.obterEstadoEMunicipio() + ", "
				+ bairro + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CriterioDTO other = (CriterioDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}

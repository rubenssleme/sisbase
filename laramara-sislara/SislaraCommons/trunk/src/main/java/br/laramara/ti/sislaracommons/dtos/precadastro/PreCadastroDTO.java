package br.laramara.ti.sislaracommons.dtos.precadastro;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PreCadastroDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 4115190444626205990L;
	
	private Long id;
	private String dataCadastro;
	private InformacaoEssencialDTO informacaoEssencialDto;
	private String obs;
		
	public PreCadastroDTO(){
		informacaoEssencialDto = new InformacaoEssencialDTO();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public InformacaoEssencialDTO getInformacaoEssencialDto() {
		return informacaoEssencialDto;
	}

	public void setInformacaoEssencialDto(
			InformacaoEssencialDTO informacaoEssencialDto) {
		this.informacaoEssencialDto = informacaoEssencialDto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreCadastroDTO other = (PreCadastroDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return informacaoEssencialDto.getNome();
	}
}

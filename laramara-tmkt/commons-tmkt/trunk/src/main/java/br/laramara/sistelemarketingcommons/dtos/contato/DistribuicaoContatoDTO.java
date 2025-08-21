package br.laramara.sistelemarketingcommons.dtos.contato;

import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class DistribuicaoContatoDTO extends ModeloDTO{
	
	private static final long serialVersionUID = 928337629246444284L;
	
	private Long id;
	
	private ContatoDTO contatoDto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContatoDTO getContatoDto() {
		return contatoDto;
	}

	public void setContatoDto(ContatoDTO contatoDto) {
		this.contatoDto = contatoDto;
	}
}

package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class PatrocinioDTO extends ModeloDTO {

	private static final long serialVersionUID = 6526881775584192408L;

	private Long id;
	private EmpresaDTO empresaDto;
	private String valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmpresaDTO getEmpresaDto() {
		return empresaDto;
	}

	public void setEmpresaDto(EmpresaDTO empresaDto) {
		this.empresaDto = empresaDto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return empresaDto.toString() + ", Valor: " + valor;
	}
}

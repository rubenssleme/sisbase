package br.laramara.ti.sislaracommons.dtos.familiar;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.trabalho.CargoDTO;

public class InformacaoTrabalhoDTO extends ModeloDTO {

	private static final long serialVersionUID = -6271453208290735984L;

	private Long id;
	private String empresa;
	private String funcao;
	private CargoDTO cargoDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public CargoDTO getCargoDto() {
		return cargoDto;
	}

	public void setCargoDto(CargoDTO cargoDto) {
		this.cargoDto = cargoDto;
	}
}

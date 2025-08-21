package br.laramara.ti.sislaracommons.dtos.grupo;

import java.io.Serializable;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;

public class EspecificacaoGeracaoAtendimentoDTO implements Serializable{
	
	private static final long serialVersionUID = 6127343287988660867L;
	
	private Long idModuloPeriodoDto;
	private String data;
	private HorarioDTO horarioDto;

	public EspecificacaoGeracaoAtendimentoDTO(Long idModuloPeriodoDto, String data, HorarioDTO horarioDTO) {
		super();
		this.idModuloPeriodoDto = idModuloPeriodoDto;
		this.data = data;
		this.horarioDto = horarioDTO;
	}

	public Long getIdModuloPeriodoDto() {
		return idModuloPeriodoDto;
	}

	public String getData() {
		return data;
	}

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}
}

package br.laramara.ti.sislaracommons.dtos.grupo;

import java.io.Serializable;

public class EspecificacaoGeracaoAtendimentosDTO implements Serializable{
	
	private static final long serialVersionUID = 6127343287988660867L;
	
	private Long idGrupo;
	private Long idModuloPeriodoDto;
	private String data;
	private String horaInicial;
	private String horaFinal;

	public EspecificacaoGeracaoAtendimentosDTO(Long idGrupo,
			Long idModuloPeriodoDto, String data, String horaInicial,
			String horaFinal) {
		super();
		this.idGrupo = idGrupo;
		this.idModuloPeriodoDto = idModuloPeriodoDto;
		this.data = data;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public Long getIdModuloPeriodoDto() {
		return idModuloPeriodoDto;
	}

	public String getData() {
		return data;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}
}

package br.laramara.ti.sislaracommons.dtos.doacao;

import java.io.Serializable;

public class EspecificacaoReciboCaptacaoCartelaDeSelosDTO implements Serializable {
		
	private static final long serialVersionUID = 7190538346080701779L;
	
	private DemandaDTO demandaDto;
	private String dataRecibo;

	public EspecificacaoReciboCaptacaoCartelaDeSelosDTO(DemandaDTO demandaDto, String dataRecibo) {
		super();
		this.demandaDto = demandaDto;
		this.dataRecibo = dataRecibo;
	}

	public DemandaDTO getDemandaDto() {
		return demandaDto;
	}

	public String getDataRecibo() {
		return dataRecibo;
	}
}

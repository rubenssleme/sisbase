package br.laramara.ti.sislaracommons.dtos.doacao;

import java.io.Serializable;

public class EspecificacaoCaptacaoDemandaDTO implements Serializable {

	private static final long serialVersionUID = -1454840199962796438L;

	private DemandaDTO demandaDto;
	private String valor;
	private ReciboDTO reciboDto;

	public DemandaDTO getDemandaDto() {
		return demandaDto;
	}

	public void setDemandaDto(DemandaDTO demandaDto) {
		this.demandaDto = demandaDto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public ReciboDTO getReciboDto() {
		return reciboDto;
	}

	public void setReciboDto(ReciboDTO reciboDto) {
		this.reciboDto = reciboDto;
	}
}

package br.laramara.sistelemarketingcommons.dtos.doacao;

import java.math.BigDecimal;
import java.util.Date;

public class ValorDetalhadoDTO {

	private Long id;

	private MetodoDTO metodoDto;

	private TipoRetiradaDTO tipoRetiradaDto;

	private BigDecimal valor;

	private Date dataEfetuacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MetodoDTO getMetodoDto() {
		return metodoDto;
	}

	public void setMetodoDto(MetodoDTO metodoDto) {
		this.metodoDto = metodoDto;
	}

	public TipoRetiradaDTO getTipoRetiradaDto() {
		return tipoRetiradaDto;
	}

	public void setTipoRetiradaDto(TipoRetiradaDTO tipoRetiradaDto) {
		this.tipoRetiradaDto = tipoRetiradaDto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataEfetuacao() {
		return dataEfetuacao;
	}

	public void setDataEfetuacao(Date dataEfetuacao) {
		this.dataEfetuacao = dataEfetuacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValorDetalhadoDTO other = (ValorDetalhadoDTO) obj;
		if (dataEfetuacao == null) {
			if (other.dataEfetuacao != null)
				return false;
		} else if (!dataEfetuacao.equals(other.dataEfetuacao))
			return false;
		if (metodoDto == null) {
			if (other.metodoDto != null)
				return false;
		} else if (!metodoDto.equals(other.metodoDto))
			return false;
		if (tipoRetiradaDto == null) {
			if (other.tipoRetiradaDto != null)
				return false;
		} else if (!tipoRetiradaDto.equals(other.tipoRetiradaDto))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}

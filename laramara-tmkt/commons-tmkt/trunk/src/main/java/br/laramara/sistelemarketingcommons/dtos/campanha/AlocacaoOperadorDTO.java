package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.math.BigDecimal;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComIdEDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;

public class AlocacaoOperadorDTO extends ModeloDTO implements Identificavel, ItemComboboxComIdEDescricao {

	private static final long serialVersionUID = 3919127384421846754L;

	private Long id;
	private ContaAcessoDTO contaAcessoDto;
	private BigDecimal metaFinanceira;
	private Integer metaQuantidadeLigacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContaAcessoDTO getContaAcessoDto() {
		return contaAcessoDto;
	}

	public void setContaAcessoDto(ContaAcessoDTO contaAcessoDto) {
		this.contaAcessoDto = contaAcessoDto;
	}

	public BigDecimal getMetaFinanceira() {
		return metaFinanceira;
	}

	public void setMetaFinanceira(BigDecimal metaFinanceira) {
		this.metaFinanceira = metaFinanceira;
	}

	public Integer getMetaQuantidadeLigacoes() {
		return metaQuantidadeLigacoes;
	}

	public void setMetaQuantidadeLigacoes(Integer metaQuantidadeLigacoes) {
		this.metaQuantidadeLigacoes = metaQuantidadeLigacoes;
	}

	@Override
	public String toString() {
		return contaAcessoDto.getNome();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlocacaoOperadorDTO other = (AlocacaoOperadorDTO) obj;
		if (contaAcessoDto == null) {
			if (other.contaAcessoDto != null)
				return false;
		} else if (!contaAcessoDto.equals(other.contaAcessoDto))
			return false;
		return true;
	}
}

package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;

public class RecursoDisponivelDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -7530827602087153565L;

	private Long id;
	private RecursoDTO recursoDto;
	private String quantidade;
	private String valorUnitario;
	private String valorTotal;

	public RecursoDisponivelDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RecursoDTO getRecursoDto() {
		return recursoDto;
	}

	public void setRecursoDto(RecursoDTO recursoDto) {
		this.recursoDto = recursoDto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return recursoDto.toString() + " - Valor unitário: R$" + valorUnitario + " - Quantidade: " + quantidade
				+ " - Valor total: R$" + valorTotal;
	}
}

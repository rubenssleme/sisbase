package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;


public class ProjetoDTO extends ModeloDTO implements Identificavel{
	
	private static final long serialVersionUID = 6526881775584192408L;
	
	private Long id;
	private String nome;
	private String dataInicial;
	private String dataFinal;
	private String valorTotal;
	private boolean ativo;
	private String valorProdutos;
	private String valorOutros;
	private String somaTotalProdutos;
	private List<LoteRecursoDTO> loteRecursoDto;
	private String resumoReservas;
		
	public ProjetoDTO() {
		loteRecursoDto = new ArrayList<>();
	}
	 	
	public Long getId() {
		return id;
	}

	public List<LoteRecursoDTO> getLoteRecursoDto() {
		return loteRecursoDto;
	}

	public String getValorTotal(){
		return valorTotal;
	}
	
	public String getDataInicial() {
		return dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setLoteRecursoDto(List<LoteRecursoDTO> loteRecursoDto) {
		this.loteRecursoDto = loteRecursoDto;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getValorProdutos() {
		return valorProdutos;
	}

	public void setValorProdutos(String valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public String getValorOutros() {
		return valorOutros;
	}

	public void setValorOutros(String valorOutros) {
		this.valorOutros = valorOutros;
	}
	
	public String getSomaTotalProdutos() {
		return somaTotalProdutos;
	}

	public void setSomaTotalProdutos(String somaTotalProdutos) {
		this.somaTotalProdutos = somaTotalProdutos;
	}
	
	public String getResumoReservas() {
		return resumoReservas;
	}

	public void setResumoReservas(String resumoReservas) {
		this.resumoReservas = resumoReservas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoDTO other = (ProjetoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString() {
		return nome + " - " + dataInicial +  " a " + dataFinal;
	}
}
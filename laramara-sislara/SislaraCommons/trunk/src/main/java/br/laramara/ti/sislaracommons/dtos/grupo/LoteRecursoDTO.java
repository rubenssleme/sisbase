package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;


public class LoteRecursoDTO extends ModeloDTO implements Identificavel {
	
	private static final long serialVersionUID = -7530827602087153565L;
	
	private Long id;
	private RecursoDTO recursoDto;
	private String quantidade;
	private String valor;
	
	public LoteRecursoDTO(){
	}
	
	public LoteRecursoDTO(Long id, RecursoDTO recursoDto, String quantidade, String valor) {
		this(recursoDto, quantidade);
		this.id = id;
		this.valor = valor;
	}
	
	public LoteRecursoDTO(RecursoDTO recursoDto, String quantidade, String valor) {
		this(null, recursoDto, quantidade, valor);
	}
	
	public LoteRecursoDTO(RecursoDTO recursoDto, String quantidade) {
		this.recursoDto = recursoDto;
		this.quantidade = quantidade;
	}
	
	public Long getId() {
		return id;
	}

	public RecursoDTO getRecursoDto() {
		return recursoDto;
	}

	public String getQuantidade() {
		return quantidade;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setRecursoDto(RecursoDTO recursoDto) {
		this.recursoDto = recursoDto;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String toString() {
		return recursoDto + " - " + quantidade + " - " + valor;
	}

}
package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.DescricaoRecursoDTO;

public class RecursoDTO extends ModeloDTO{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private boolean cartelaDeSelos;
	private boolean disponivelParaDemanda;
	private String valor;
	private List<DescricaoRecursoDTO> descricoesRecursosDto;

	public RecursoDTO(Long id, String descricao, boolean cartelaDeSelos, boolean disponivelParaDemanda, String valor,
			List<DescricaoRecursoDTO> descricoesRecursos) {
		this.id = id;
		this.descricao = descricao;
		this.cartelaDeSelos = cartelaDeSelos;
		this.disponivelParaDemanda = disponivelParaDemanda;
		this.valor = valor;
		this.descricoesRecursosDto = descricoesRecursos;
	}

	public Long getId() {
		return id;
	}
	
	public String getValor() {
		return valor;
	}
	
	public boolean isCartelaDeSelos() {
		return cartelaDeSelos;
	}
	
	public boolean isDisponivelParaDemanda() {
		return disponivelParaDemanda;
	}

	public List<DescricaoRecursoDTO> getDescricoesRecursosDto() {
		return descricoesRecursosDto;
	}

	@Override
	public String toString() {
		return descricao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoDTO other = (RecursoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

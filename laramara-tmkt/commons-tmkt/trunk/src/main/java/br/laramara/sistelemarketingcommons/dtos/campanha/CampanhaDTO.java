package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;

public class CampanhaDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 3919127384421846754L;

	private Long id;
	private String nome;
	private String descricao;
	private String dataInicio;
	private String dataTermino;
	private BigDecimal metaFinanceira;
	private Integer metaQuantidadeLigacoes;
	private List<CriterioDTO> criteriosDto;
	private List<AlocacaoOperadorDTO> alocacoesOperadoresDto;
	
	public CampanhaDTO() {
		criteriosDto = new ArrayList<>();
		alocacoesOperadoresDto = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
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

	public List<CriterioDTO> getCriteriosDto() {
		return criteriosDto;
	}

	public void setCriteriosDto(List<CriterioDTO> criteriosDto) {
		this.criteriosDto = criteriosDto;
	}
	
	public List<AlocacaoOperadorDTO> getAlocacoesOperadoresDto() {
		return alocacoesOperadoresDto;
	}

	public void setAlocacoesOperadoresDto(List<AlocacaoOperadorDTO> alocacoesOperadoresDto) {
		this.alocacoesOperadoresDto = alocacoesOperadoresDto;
	}

	public void adicionarAlocacaoOperadorDto(AlocacaoOperadorDTO alocacaoOperadorDto) {
		if (!alocacoesOperadoresDto.contains(alocacaoOperadorDto)) {
			alocacoesOperadoresDto.add(alocacaoOperadorDto);
		} else {
			alocacoesOperadoresDto.set(alocacoesOperadoresDto.indexOf(alocacaoOperadorDto), alocacaoOperadorDto);
		}
	}
	
	public void removerAlocacaoOperadorDto(AlocacaoOperadorDTO alocacaoOperadorARemoverDTO) {
		if (alocacoesOperadoresDto.contains(alocacaoOperadorARemoverDTO)) {
			alocacoesOperadoresDto.remove(alocacaoOperadorARemoverDTO);
		}
	}

	public void adicionarCriterioDto(CriterioDTO criterioDto) {
		if (!criteriosDto.contains(criterioDto)) {
			criteriosDto.add(criterioDto);
		} else {
			criteriosDto.set(criteriosDto.indexOf(criterioDto), criterioDto);
		}
	}
	
	public void removerCriterioDto(CriterioDTO criterioARemoverDTO) {
		if (criteriosDto.contains(criterioARemoverDTO)) {
			criteriosDto.remove(criterioARemoverDTO);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampanhaDTO other = (CampanhaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

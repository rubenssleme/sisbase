package br.laramara.ti.sislaracommons.dtos.evento;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class DetalheCursoDTO extends ModeloDTO {
	private static final long serialVersionUID = -8929748812218204962L;

	private Long idGrupo;
	private String nomeCurso;
	private String periodoPreInscricoes;
	private String dataCurso;
	private String cargaHoraria;
	private String numeroVagas;
	private String investimento;
	private List<DescricaoEventoDTO> descricoesEventoDto;
	private String valorTotalAlmoco;

	public DetalheCursoDTO() {
		descricoesEventoDto = new ArrayList<>();
	}

	public DetalheCursoDTO(Long idGrupo, String nomeCurso, String periodoPreInscricoes, String dataCurso,
			String cargaHoraria, String numeroVagas, String investimento, List<DescricaoEventoDTO> descricoesEventoDto,
			String valorTotalAlmoco) {
		this();
		setIdGrupo(idGrupo);
		setNomeCurso(nomeCurso);
		setPeriodoPreInscricoes(periodoPreInscricoes);
		setDataCurso(dataCurso);
		setCargaHoraria(cargaHoraria);
		setNumeroVagas(numeroVagas);
		setInvestimento(investimento);
		setDescricoesEventoDto(descricoesEventoDto);
		setValorTotalAlmoco(valorTotalAlmoco);
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getPeriodoPreInscricoes() {
		return periodoPreInscricoes;
	}

	public void setPeriodoPreInscricoes(String periodoPreInscricoes) {
		this.periodoPreInscricoes = periodoPreInscricoes;
	}

	public String getDataCurso() {
		return dataCurso;
	}

	public void setDataCurso(String dataCurso) {
		this.dataCurso = dataCurso;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(String numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public String getInvestimento() {
		return investimento;
	}

	public void setInvestimento(String investimento) {
		this.investimento = investimento;
	}

	public List<DescricaoEventoDTO> getDescricoesEventoDto() {
		return descricoesEventoDto;
	}

	public void setDescricoesEventoDto(List<DescricaoEventoDTO> descricoesEventoDto) {
		this.descricoesEventoDto = descricoesEventoDto;
	}

	public String getValorTotalAlmoco() {
		return valorTotalAlmoco;
	}
	
	public void setValorTotalAlmoco(String valorTotalAlmoco) {
		this.valorTotalAlmoco = valorTotalAlmoco;
	}

	@Override
	public String toString() {
		return "DetalheCursoDTO [idGrupo=" + idGrupo + ", nomeCurso=" + nomeCurso + ", periodoPreInscricoes="
				+ periodoPreInscricoes + ", dataCurso=" + dataCurso + ", cargaHoraria=" + cargaHoraria
				+ ", numeroVagas=" + numeroVagas + ", investimento=" + investimento + ", descricoesEventoDto="
				+ descricoesEventoDto + ", valorTotalAlmoco=" + valorTotalAlmoco + "]";
	}

}

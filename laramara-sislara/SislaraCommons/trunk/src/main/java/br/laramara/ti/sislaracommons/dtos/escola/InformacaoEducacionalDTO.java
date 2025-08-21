package br.laramara.ti.sislaracommons.dtos.escola;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;

public class InformacaoEducacionalDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = -1048883179408933189L;

	private Long id;
	private InstituicaoDTO instituicaoDto;
	private EscolaridadeDTO escolaridadeDto;
	private SerieDTO serieDto;
	private PeriodoDTO periodoDto;
	private SituacaoEducacionalDTO situacaoEducacionalDto;
	private String nomeProfessor;
	private AreaFormacaoDTO areaFormacaoDto;
	private String dataReferencia;

	public InformacaoEducacionalDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InstituicaoDTO getInstituicaoDto() {
		return instituicaoDto;
	}

	public void setInstituicaoDto(InstituicaoDTO instituicaoDto) {
		this.instituicaoDto = instituicaoDto;
	}

	public EscolaridadeDTO getEscolaridadeDto() {
		return escolaridadeDto;
	}

	public void setEscolaridadeDto(EscolaridadeDTO escolaridadeDto) {
		this.escolaridadeDto = escolaridadeDto;
	}
	
	public SerieDTO getSerieDto() {
		return serieDto;
	}

	public void setSerieDto(SerieDTO serieDto) {
		this.serieDto = serieDto;
	}

	public PeriodoDTO getPeriodoDto() {
		return periodoDto;
	}

	public void setPeriodoDto(PeriodoDTO periodoDto) {
		this.periodoDto = periodoDto;
	}

	public SituacaoEducacionalDTO getSituacaoEducacionalDto() {
		return situacaoEducacionalDto;
	}

	public void setSituacaoEducacionalDto(SituacaoEducacionalDTO situacaoEducacionalDto) {
		this.situacaoEducacionalDto = situacaoEducacionalDto;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public AreaFormacaoDTO getAreaFormacaoDto() {
		return areaFormacaoDto;
	}

	public void setAreaFormacaoDto(AreaFormacaoDTO areaFormacaoDto) {
		this.areaFormacaoDto = areaFormacaoDto;
	}

	public String getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
}

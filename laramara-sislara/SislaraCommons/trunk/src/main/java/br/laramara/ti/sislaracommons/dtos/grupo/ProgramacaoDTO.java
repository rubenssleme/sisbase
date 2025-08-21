package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ProgramacaoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 1675684108039725196L;

	private Long id;
	private String aula;
	private String data;
	private String temaConteudo;
	private String recadoFamilia;
	private String estrategias;
	private LocalAtendimentoDTO localAtendimentoDTO;

	public ProgramacaoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTemaConteudo() {
		return temaConteudo;
	}

	public void setTemaConteudo(String temaConteudo) {
		this.temaConteudo = temaConteudo;
	}

	public String getRecadoFamilia() {
		return recadoFamilia;
	}

	public void setRecadoFamilia(String recadoFamilia) {
		this.recadoFamilia = recadoFamilia;
	}
	
	public String getEstrategias() {
		return estrategias;
	}

	public void setEstrategias(String estrategias) {
		this.estrategias = estrategias;
	}

	public LocalAtendimentoDTO getLocalAtendimentoDTO() {
		return localAtendimentoDTO;
	}

	public void setLocalAtendimentoDTO(LocalAtendimentoDTO localAtendimentoDTO) {
		this.localAtendimentoDTO = localAtendimentoDTO;
	}
}
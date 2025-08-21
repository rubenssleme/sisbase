package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ProntuarioEscaneadoDTO extends ModeloDTO{

	private static final long serialVersionUID = -4963416747766329326L;

	private String nomeArquivo;
	private ArquivoDTO arquivo;

	public ProntuarioEscaneadoDTO(String nomeArquivo, ArquivoDTO arquivoDTO) {
		this.nomeArquivo = nomeArquivo;
		this.arquivo = arquivoDTO;
	}
	
	public ArquivoDTO getArquivoDto() {
		return arquivo;
	}

	@Override
	public String toString() {
		return nomeArquivo;
	}
}

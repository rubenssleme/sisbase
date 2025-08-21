package br.laramara.ti.sislaracommons.dtos.atendimento;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.InformacaoAtendimentoDTO;

public abstract class AtendimentoBaseDTO extends ModeloDTO implements Identificavel{

	private static final long serialVersionUID = -6536555493596083716L;
	
	private Long id;
	private InformacaoAtendimentoDTO informacaoAtendimentoDto;
	private List<ArquivoDTO> arquivos;
	
	public AtendimentoBaseDTO(){
		informacaoAtendimentoDto = new InformacaoAtendimentoDTO();
		arquivos = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ArquivoDTO> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<ArquivoDTO> arquivos) {
		this.arquivos = arquivos;
	}
	
	public InformacaoAtendimentoDTO getInformacaoAtendimentoDto() {
		return informacaoAtendimentoDto;
	}

	public void setInformacaoAtendimentoDto(
			InformacaoAtendimentoDTO informacaoAtendimentoDto) {
		this.informacaoAtendimentoDto = informacaoAtendimentoDto;
	}

	@Override
	public String toString() {
		return obterNome();
	}
	
	protected abstract String obterNome();
}

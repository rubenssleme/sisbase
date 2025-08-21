package br.laramara.ti.sislaracommons.dtos.atendimento;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;

public class EspecificacaoPesquisaAtendimentoIndividualDTO extends
		EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 4153187596713188405L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.PROFISSIONAL);
		adicionar(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO);
		adicionar(ChavePesquisaDTO.MODULO);
		adicionar(ChavePesquisaDTO.DATA_INICIO);
		adicionar(ChavePesquisaDTO.DATA_TERMINO);
		adicionar(ChavePesquisaDTO.PRONTUARIO);
	}

	public DescricaoTipoAtendimentoDTO obterDescricaoTipoAtendimentoDto() {
		return (DescricaoTipoAtendimentoDTO) obterParametro(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO);
	}

	public void setDescricaoTipoAtendimentoDto(
			DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto) {
		adicionarParametro(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO,
				descricaoTipoAtendimentoDto);
	}

	public ModuloDTO obterModuloDto() {
		return (ModuloDTO) obterParametro(ChavePesquisaDTO.MODULO);
	}

	public void setModuloDto(ModuloDTO moduloDto) {
		adicionarParametro(ChavePesquisaDTO.MODULO, moduloDto);
	}

	public String getDataInicio() {
		return (String) obterParametro(ChavePesquisaDTO.DATA_INICIO);
	}

	public void setDataInicio(String dataInicio) {
		adicionarParametro(ChavePesquisaDTO.DATA_INICIO, dataInicio);
	}

	public String getDataTermino() {
		return (String) obterParametro(ChavePesquisaDTO.DATA_TERMINO);
	}

	public void setDataTermino(String dataTermino) {
		adicionarParametro(ChavePesquisaDTO.DATA_TERMINO, dataTermino);
	}

	public ProfissionalDTO obterProfissionalDto() {
		return (ProfissionalDTO) obterParametro(ChavePesquisaDTO.PROFISSIONAL);
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		adicionarParametro(ChavePesquisaDTO.PROFISSIONAL, profissionalDto);
	}
	
	public String getProntuario() {
		return (String) obterParametro(ChavePesquisaDTO.PRONTUARIO);
	}

	public void setProntuario(String prontuario) {
		adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario);
	}

}

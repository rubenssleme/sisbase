package br.laramara.ti.sislaracommons.dtos.agenda;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;

public class EspecificacaoPesquisaAgendamentoDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 7997968623906524742L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.PROFISSIONAL);
		adicionar(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO);
		adicionar(ChavePesquisaDTO.MODULO);
		adicionar(ChavePesquisaDTO.DATA_INICIO);
		adicionar(ChavePesquisaDTO.DATA_TERMINO);
		adicionar(ChavePesquisaDTO.STATUS_AGENDAMENTO);
		adicionar(ChavePesquisaDTO.PRONTUARIO);
		adicionar(ChavePesquisaDTO.RG);
		adicionar(ChavePesquisaDTO.DATA_FUTURA);
	}
	
	public EspecificacaoPesquisaAgendamentoDTO(){
		adicionarParametro(ChavePesquisaDTO.DATA_FUTURA, false);
	}
	
	public ProfissionalDTO getProfissionalDto() {
		return (ProfissionalDTO) obterParametro(ChavePesquisaDTO.PROFISSIONAL);
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		adicionarParametro(ChavePesquisaDTO.PROFISSIONAL, profissionalDto);
	}

	public DescricaoTipoAtendimentoDTO getDescricaoTipoAtendimentoDTO() {
		return (DescricaoTipoAtendimentoDTO) obterParametro(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO);
	}

	public void setDescricaoTipoAtendimentoDTO(
			DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDTO) {
		adicionarParametro(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO, descricaoTipoAtendimentoDTO);
	}
	
	public ModuloDTO getModuloDTO() {
		return (ModuloDTO) obterParametro(ChavePesquisaDTO.MODULO);
	}

	public void setModuloDTO(ModuloDTO moduloDto) {
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

	public StatusAgendamentoDTO getStatusAgendamentoDTO() {
		return (StatusAgendamentoDTO) obterParametro(ChavePesquisaDTO.STATUS_AGENDAMENTO);
	}

	public void setStatusAgendamentoDTO(StatusAgendamentoDTO statusAgendamentoDTO) {
		adicionarParametro(ChavePesquisaDTO.STATUS_AGENDAMENTO, statusAgendamentoDTO);
	}

	public String getProntuario() {
		return (String) obterParametro(ChavePesquisaDTO.PRONTUARIO);
	}

	public void setProntuario(String prontuario) {
		adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario);
	}
	
	public String getRgPreCadastro() {
		return (String) obterParametro(ChavePesquisaDTO.RG);
	}

	public void setRgPreCadastro(String rg) {
		adicionarParametro(ChavePesquisaDTO.RG, rg);
	}

	public void marcarDataFutura() {
		adicionarParametro(ChavePesquisaDTO.DATA_FUTURA, true);
	}

	public boolean estaDataFutura() {
		return (boolean) obterParametro(ChavePesquisaDTO.DATA_FUTURA);
	}
}

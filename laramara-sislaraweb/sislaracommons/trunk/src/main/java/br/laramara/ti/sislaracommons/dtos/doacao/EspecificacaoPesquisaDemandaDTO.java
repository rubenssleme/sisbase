package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;

public class EspecificacaoPesquisaDemandaDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 7997968623906524742L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.STATUS_DEMANDA);
		adicionar(ChavePesquisaDTO.RECURSO);
		adicionar(ChavePesquisaDTO.PRE_CADASTRO);
		adicionar(ChavePesquisaDTO.PRONTUARIO);
		adicionar(ChavePesquisaDTO.CPF);
	}

	public PreCadastroDTO getPreCadastro() {
		return (PreCadastroDTO) obterParametro(ChavePesquisaDTO.PRE_CADASTRO);
	}

	public void setPreCadastroDto(PreCadastroDTO preCadastro) {
		adicionarParametro(ChavePesquisaDTO.PRE_CADASTRO, preCadastro);
	}

	public String getProntuario() {
		return (String) obterParametro(ChavePesquisaDTO.PRONTUARIO);
	}

	public void setProntuario(String prontuario) {
		adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario);
	}

	public String getCpf() {
		return (String) obterParametro(ChavePesquisaDTO.CPF);
	}

	public void setCpf(String cpf) {
		adicionarParametro(ChavePesquisaDTO.CPF, cpf);
	}

	public RecursoDTO getRecurso() {
		return (RecursoDTO) obterParametro(ChavePesquisaDTO.RECURSO);
	}

	public void setRecursoDto(RecursoDTO recursoDto) {
		adicionarParametro(ChavePesquisaDTO.RECURSO, recursoDto);
	}

	public StatusDemandaDTO getStatusDemandaDTO() {
		return (StatusDemandaDTO) obterParametro(ChavePesquisaDTO.STATUS_DEMANDA);
	}

	public void setStatusDemandaDto(StatusDemandaDTO statusDemandaDto) {
		adicionarParametro(ChavePesquisaDTO.STATUS_DEMANDA, statusDemandaDto);
	}
}

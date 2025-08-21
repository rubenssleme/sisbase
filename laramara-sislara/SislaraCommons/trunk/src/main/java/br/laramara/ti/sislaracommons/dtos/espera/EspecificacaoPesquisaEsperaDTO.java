package br.laramara.ti.sislaracommons.dtos.espera;

import br.laramara.ti.sislaracommons.dtos.ChavePesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.EspecificacaoPesquisaDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;

public class EspecificacaoPesquisaEsperaDTO extends EspecificacaoPesquisaDTO {

	private static final long serialVersionUID = 4153187596713188405L;

	@Override
	protected void configurar() {
		adicionar(ChavePesquisaDTO.DESCRICAO_TIPO_ATENDIMENTO);
		adicionar(ChavePesquisaDTO.MODULO);
		adicionar(ChavePesquisaDTO.DATA_INICIO);
		adicionar(ChavePesquisaDTO.DATA_TERMINO);
		adicionar(ChavePesquisaDTO.TIPO_ESPERA);
		adicionar(ChavePesquisaDTO.STATUS_ESPERA);
		adicionar(ChavePesquisaDTO.SETOR);
		adicionar(ChavePesquisaDTO.PRONTUARIO);
		adicionar(ChavePesquisaDTO.RG);
		adicionar(ChavePesquisaDTO.GRUPOS_INATIVOS);
		adicionar(ChavePesquisaDTO.INTERESSE);
		adicionar(ChavePesquisaDTO.LMLIGOU);
		adicionar(ChavePesquisaDTO.PENDENCIAS);
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

	public TipoEsperaDTO obterTipoEsperaDto() {
		return (TipoEsperaDTO) obterParametro(ChavePesquisaDTO.TIPO_ESPERA);
	}

	public void setTipoEsperaDto(TipoEsperaDTO tipoEsperaDto) {
		adicionarParametro(ChavePesquisaDTO.TIPO_ESPERA, tipoEsperaDto);
	}

	public StatusEsperaDTO obterStatusEsperaDto() {
		return (StatusEsperaDTO) obterParametro(ChavePesquisaDTO.STATUS_ESPERA);
	}

	public void setStatusEsperadoDto(StatusEsperaDTO statusEsperaDto) {
		adicionarParametro(ChavePesquisaDTO.STATUS_ESPERA, statusEsperaDto);
	}

	public SetorDTO obterSetorDto() {
		return (SetorDTO) obterParametro(ChavePesquisaDTO.SETOR);
	}

	public void setSetorDto(SetorDTO setorDto) {
		adicionarParametro(ChavePesquisaDTO.SETOR, setorDto);
	}
	
	public String getProntuario() {
		return (String) obterParametro(ChavePesquisaDTO.PRONTUARIO);
	}

	public void setProntuario(String prontuario) {
		adicionarParametro(ChavePesquisaDTO.PRONTUARIO, prontuario);
	}
	
	public String getRg() {
		return (String) obterParametro(ChavePesquisaDTO.RG);
	}

	public void setRg(String rg) {
		adicionarParametro(ChavePesquisaDTO.RG, rg);
	}
	
	public NomeGrupoDTO getNomeGrupo() {
		return (NomeGrupoDTO) obterParametro(ChavePesquisaDTO.GRUPOS_INATIVOS);
	}

	public void setNomeGrupo(NomeGrupoDTO nomeGrupoDto) {
		adicionarParametro(ChavePesquisaDTO.GRUPOS_INATIVOS, nomeGrupoDto);
	}
	
	public boolean getInteresse() {
		return (boolean) obterParametro(ChavePesquisaDTO.INTERESSE);
	}

	public void setInteresse(boolean interesse) {
		adicionarParametro(ChavePesquisaDTO.INTERESSE, interesse);
	}
	
	public boolean getLmLigou() {
		return (boolean) obterParametro(ChavePesquisaDTO.LMLIGOU);
	}

	public void setLmLigou(boolean lmLigou) {
		adicionarParametro(ChavePesquisaDTO.LMLIGOU, lmLigou);
	}
		
	public boolean getPendencias() {
		return (boolean) obterParametro(ChavePesquisaDTO.PENDENCIAS);
	}

	public void setPendencias(boolean pendencias) {
		adicionarParametro(ChavePesquisaDTO.PENDENCIAS, pendencias);
	}
}

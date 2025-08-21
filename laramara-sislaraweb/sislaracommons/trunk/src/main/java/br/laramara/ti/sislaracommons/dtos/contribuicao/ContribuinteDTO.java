package br.laramara.ti.sislaracommons.dtos.contribuicao;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ContatoDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;

public class ContribuinteDTO extends ModeloDTO implements Identificavel{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String dataCadastro;
	
	private String nomeEmpresa;
	
	private EnderecoDTO enderecoDto;
	
	private String indicadoPor;
	
	private FrequenciaContribuicaoDTO frequenciaDto;
	
	private ContatoDTO contatoDto;
	
	private StatusContribuinteDTO statusContribuinteDto;
	
	private String contribuicao;
	
	private MotivoDesativacaoDTO motivoDesativacaoDTO;
	
	private String cpf;
		
	public ContribuinteDTO(){
		contatoDto = new ContatoDTO();
		enderecoDto = new EnderecoDTO();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public EnderecoDTO getEnderecoDto() {
		return enderecoDto;
	}

	public void setEnderecoDto(EnderecoDTO enderecoDto) {
		this.enderecoDto = enderecoDto;
	}

	public String getIndicadoPor() {
		return indicadoPor;
	}

	public void setIndicadoPor(String indicadoPor) {
		this.indicadoPor = indicadoPor;
	}

	public FrequenciaContribuicaoDTO getFrequenciaDto() {
		return frequenciaDto;
	}

	public void setFrequenciaDto(FrequenciaContribuicaoDTO frequenciaDto) {
		this.frequenciaDto = frequenciaDto;
	}

	public ContatoDTO getContatoDto() {
		return contatoDto;
	}

	public void setContatoDto(ContatoDTO contatoDto) {
		this.contatoDto = contatoDto;
	}

	public StatusContribuinteDTO getStatusContribuinteDto() {
		return statusContribuinteDto;
	}

	public void setStatusContribuinteDto(StatusContribuinteDTO statusContribuinteDto) {
		this.statusContribuinteDto = statusContribuinteDto;
	}
	
	public String getContribuicao() {
		return contribuicao;
	}

	public void setContribuicao(String contribuicao) {
		this.contribuicao = contribuicao;
	}
	
	public MotivoDesativacaoDTO getMotivoDesativacaoDTO() {
		return motivoDesativacaoDTO;
	}

	public void setMotivoDesativacaoDTO(MotivoDesativacaoDTO motivoDesativacaoDTO) {
		this.motivoDesativacaoDTO = motivoDesativacaoDTO;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContribuinteDTO other = (ContribuinteDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

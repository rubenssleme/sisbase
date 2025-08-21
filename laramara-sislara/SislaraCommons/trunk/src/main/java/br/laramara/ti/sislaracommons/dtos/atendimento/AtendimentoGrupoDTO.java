package br.laramara.ti.sislaracommons.dtos.atendimento;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class AtendimentoGrupoDTO extends ModeloDTO implements Identificavel {

	private static final long serialVersionUID = 4734258372343099497L;

	private Long id;
	
	private String data;
	
	private StatusAtendimentoDTO statusAtendimentoDto;

	private HorarioDTO horarioDto;

	private String descricao;

	private String interdisciplinar;

	private List<AtendimentoUsuarioDTO> atendimentosUsuariosDto;
	
	private List<AtendimentoPreCadastroDTO> atendimentosPreCadastroDto;

	private List<AtendimentoProfissionalDTO> atendimentosProfissionaisDto;
	
	private List<ArquivoDTO> arquivos;

	public AtendimentoGrupoDTO() {
		super();
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
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public StatusAtendimentoDTO getStatusAtendimentoDto() {
		return statusAtendimentoDto;
	}

	public void setStatusAtendimentoDto(StatusAtendimentoDTO status) {
		this.statusAtendimentoDto = status;
	}

	public HorarioDTO getHorarioDto() {
		return horarioDto;
	}

	public void setHorarioDto(HorarioDTO horarioDto) {
		this.horarioDto = horarioDto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInterdisciplinar() {
		return interdisciplinar;
	}

	public void setInterdisciplinar(String interdisciplinar) {
		this.interdisciplinar = interdisciplinar;
	}

	public List<AtendimentoUsuarioDTO> getAtendimentosUsuariosDto() {
		return atendimentosUsuariosDto;
	}

	public void setAtendimentosUsuariosDto(
			List<AtendimentoUsuarioDTO> atendimentosUsuariosDto) {
		this.atendimentosUsuariosDto = atendimentosUsuariosDto;
	}
	
	public List<AtendimentoPreCadastroDTO> getAtendimentosPreCadastroDto() {
		return atendimentosPreCadastroDto;
	}

	public void setAtendimentosPreCadastroDto(
			List<AtendimentoPreCadastroDTO> atendimentosPreCadastroDto) {
		this.atendimentosPreCadastroDto = atendimentosPreCadastroDto;
	}

	public List<AtendimentoProfissionalDTO> getAtendimentosProfissionaisDto() {
		return atendimentosProfissionaisDto;
	}

	public void setAtendimentosProfissionaisDto(
			List<AtendimentoProfissionalDTO> atendimentosProfissionaisDto) {
		this.atendimentosProfissionaisDto = atendimentosProfissionaisDto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtendimentoGrupoDTO other = (AtendimentoGrupoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

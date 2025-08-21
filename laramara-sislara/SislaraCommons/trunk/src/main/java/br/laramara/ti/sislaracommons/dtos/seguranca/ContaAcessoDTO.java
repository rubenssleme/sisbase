package br.laramara.ti.sislaracommons.dtos.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.Criptografia;

public class ContaAcessoDTO extends ModeloDTO implements Identificavel {
	
	private static final long serialVersionUID = 3919127384421846754L;
	
	private Long id;
	private String login;
	private String senha;
	private boolean bloqueado;
	private PerfilDTO perfilDto;
	private ProfissionalDTO profissionalDto;
	private String palavraChaveGrupo;
	private List<PermissaoDTO> permissoesDto;
	private List<ProfissionalDTO> profissionaisEquivalentesDto;

	public ContaAcessoDTO() {
		super();
		this.permissoesDto = new ArrayList<>();
		this.profissionaisEquivalentesDto = new ArrayList<>();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setUsuario(String usuario) {
		this.login = usuario;
	}

	public void setSenha(String senha) {
		this.senha = Criptografia.converterParaMD5(senha);
	}

	public void setPerfilDto(PerfilDTO perfilDto) {
		this.perfilDto = perfilDto;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public PerfilDTO getPerfilDto() {
		return perfilDto;
	}

	public ProfissionalDTO getProfissionalDto() {
		return profissionalDto;
	}

	public void setProfissionalDto(ProfissionalDTO profissionalDto) {
		this.profissionalDto = profissionalDto;
	}

	public List<ProfissionalDTO> getProfissionaisEquivalentesDto() {
		return profissionaisEquivalentesDto;
	}

	public void setProfissionaisEquivalentesDto(List<ProfissionalDTO> profissionaisEquivalentesDto) {
		this.profissionaisEquivalentesDto = profissionaisEquivalentesDto;
	}

	public String getPalavraChaveGrupo() {
		return palavraChaveGrupo;
	}

	public void setPalavraChaveGrupo(String palavraChaveGrupo) {
		this.palavraChaveGrupo = palavraChaveGrupo;
	}
	
	public List<PermissaoDTO> getPermissoesDto() {
		return permissoesDto;
	}

	public void setPermissoesDto(List<PermissaoDTO> permissoesDto) {
		this.permissoesDto = permissoesDto;
	}
}

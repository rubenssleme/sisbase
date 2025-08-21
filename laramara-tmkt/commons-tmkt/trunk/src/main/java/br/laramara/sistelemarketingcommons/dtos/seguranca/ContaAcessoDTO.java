package br.laramara.sistelemarketingcommons.dtos.seguranca;

import br.laramara.sistelemarketingcommons.Identificavel;
import br.laramara.sistelemarketingcommons.dtos.ItemComboboxComIdEDescricao;
import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.telefonia.RamalDTO;

public class ContaAcessoDTO extends ModeloDTO implements Identificavel, ItemComboboxComIdEDescricao{
	
	private static final long serialVersionUID = 3919127384421846754L;
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private boolean ativo;
	private NivelDTO nivelDto;
	private TurnoDTO turnoDto;
	private RamalDTO ramalDto;
		
	public ContaAcessoDTO() {
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public NivelDTO getNivelDto() {
		return nivelDto;
	}

	public void setNivelDto(NivelDTO nivelDto) {
		this.nivelDto = nivelDto;
	}
	
	public TurnoDTO getTurnoDto() {
		return turnoDto;
	}

	public void setTurnoDto(TurnoDTO turnoDto) {
		this.turnoDto = turnoDto;
	}
	
	public RamalDTO getRamalDto() {
		return ramalDto;
	}

	public void setRamalDto(RamalDTO ramalDto) {
		this.ramalDto = ramalDto;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaAcessoDTO other = (ContaAcessoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

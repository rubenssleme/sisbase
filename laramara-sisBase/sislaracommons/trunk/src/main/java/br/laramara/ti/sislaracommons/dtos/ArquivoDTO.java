package br.laramara.ti.sislaracommons.dtos;


public class ArquivoDTO extends MecanismoTransferenciaDTO{
	
	private static final long serialVersionUID = 8148260346527969433L;
	
	private Long id;
	
	private String nome;
	
	private byte[] conteudo;
	
	private String extensao;
	
	public ArquivoDTO(){
		
	}
	
	public ArquivoDTO(Long id, String nome, byte[] conteudo, String extensao){
		this(conteudo, extensao);
		this.id = id;
		this.nome = nome;
	}
	
	public ArquivoDTO(byte[] conteudo, String extensao){
		this.conteudo = conteudo;
		this.extensao = extensao;
		super.efetuadoComSucesso();
	}
	
	public byte[] obterConteudo() {
		return conteudo;
	}
	
	public boolean possuiConteudo(){
		return conteudo != null;
	}
	
	public String obterExtensao(){
		return extensao;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
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
		ArquivoDTO other = (ArquivoDTO) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}

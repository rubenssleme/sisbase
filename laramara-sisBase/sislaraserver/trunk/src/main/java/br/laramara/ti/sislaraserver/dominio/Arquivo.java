package br.laramara.ti.sislaraserver.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;

@Entity
@Table(name = "arquivo")
public class Arquivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "nome", length = TamanhoMaximoGenerico.ARQUIVO)
	private String nome;
		
	@Transient
	private byte[] conteudo;
	
	@Transient
	private ExtensaoArquivo extensao;

	public Arquivo(){
		
	}
	public Arquivo(String nome) {
		this.nome = nome;
	}
	
	public Arquivo(Long id, String nome, byte[] conteudo) {
		this(conteudo, null);
		this.id = id;
		this.nome = nome;
	}
	
	public Arquivo(byte[] conteudo, ExtensaoArquivo extensao) {
		super();
	
		this.conteudo = conteudo;
		this.extensao = extensao;
	}

	public byte[] getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public boolean possuiId() {
		return id != null;
	}

	public boolean possuiConteudo() {
		return conteudo != null;
	}

	public ExtensaoArquivo getExtensao() {
		return extensao;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean possuiRelatorioDeAVFUN() {
		return nome.toUpperCase().startsWith("AVFUN".toUpperCase());
	}

	@Override
	public String toString() {
		return "Arquivo [nome=" + nome + "]";
	}
}

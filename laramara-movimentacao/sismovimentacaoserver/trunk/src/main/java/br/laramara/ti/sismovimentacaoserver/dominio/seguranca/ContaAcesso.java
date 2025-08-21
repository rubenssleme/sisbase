package br.laramara.ti.sismovimentacaoserver.dominio.seguranca;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.laramara.ti.sismovimentacaocommons.Identificavel;
import br.laramara.ti.sismovimentacaocommons.utilitarios.Criptografia;
import br.laramara.ti.sismovimentacaocommons.utilitarios.TextoUtils;
import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;
import br.laramara.ti.sismovimentacaoserver.dominio.TamanhoMaximoGenerico;
import br.laramara.ti.sismovimentacaoserver.dominio.Validavel;
import br.laramara.ti.sismovimentacaoserver.utilitarios.Configuracao;


@Entity
@Table(name = "conta_acesso")
public class ContaAcesso extends Validavel implements Serializable, Identificavel {
	
	private static final long serialVersionUID = -99748161922449073L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login", length = 100, nullable = false)
	private String login;

	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	@Column(name = "bloqueado", nullable = false)
	private boolean bloqueado;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "extensao_relatorios", length = 3, nullable = false)
	private ExtensaoArquivo extensaoRelatorios;
	
	@Column(name = "filtro_grupo", nullable = false)
	private boolean filtroGrupo;

	@OneToOne
	@JoinColumn(name = "id_profissional")
	private Profissional profissional;
	
	@Column(name = "token", length = TamanhoMaximoGenerico.TOKEN)
	private String token;

	@ManyToOne(optional=false)
	@JoinColumn(name="id_perfil") 
	private Perfil perfil;
	
	@Column(name = "palavra_chave_grupo", length = TamanhoMaximoGenerico.TEXTO_GRANDE)
	private String palavraChaveGrupo;
	
	public ContaAcesso(){
		inicializarPdfExtensaoRelatoriosPadrao();
	}
	
	public ContaAcesso(Long id, String login, String senha, Perfil perfil) {
		this();
		this.id = id;
		this.login = login;
		configurarSenha(senha);
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void setSenha(String senha) {
		configurarSenha(senha);
	}
	
	private void configurarSenha(String senha) {
		if (id == null && this.senha == null && senha == null) {
			this.senha = Criptografia.converterParaMD5(new Configuracao()
					.obterConfiguracao(Configuracao.SENHA_PADRAO_CONTA_ACESSO));
		} else if (senha != null && !TextoUtils.estaVazio(senha)) {
			this.senha = senha;
		}
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public boolean possuiPermissao(Permissao permissaoNecessaria) {
		return perfil.possuiPermissao(permissaoNecessaria) ? true : false;
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

	private void inicializarPdfExtensaoRelatoriosPadrao() {
		if (this.extensaoRelatorios == null) {
			this.extensaoRelatorios = ExtensaoArquivo.pdf;
		}
	}

	public ExtensaoArquivo getExtensaoRelatorios() {
		return extensaoRelatorios;
	}
	
	public void marcarExtensaoDeRelatorioXls(){
		this.extensaoRelatorios = ExtensaoArquivo.xls;
	}
			
	public void marcarExtensaoDeRelatorioPdf(){
		this.extensaoRelatorios = ExtensaoArquivo.pdf;
	}
	
	public boolean isFiltroGrupoAtivado() {
		return filtroGrupo;
	}

	public void setFiltroGrupo(boolean filtroGrupo) {
		this.filtroGrupo = filtroGrupo;
	}

	public void ativarFiltroGrupo(){
		this.filtroGrupo = true;
	}
	
	public void desativarFiltroGrupo(){
		this.filtroGrupo = false;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getPalavraChaveGrupo() {
		return palavraChaveGrupo;
	}

	public void setPalavraChaveGrupo(String palavraChaveGrupo) {
		this.palavraChaveGrupo = palavraChaveGrupo;
	}

	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (login == null || login != null && TextoUtils.estaVazio(login)) {
			adicionarErro("Insira um Login válido.");
		}
		if (perfil == null) {
			adicionarErro("Insira um Perfil.");
		}
		if (tamanhoMaximoViolado(palavraChaveGrupo,
				TamanhoMaximoGenerico.TEXTO_GRANDE)) {
			adicionarErro("Insira uma Palavra Chave de Grupo contendo até "
					+ TamanhoMaximoGenerico.TEXTO_GRANDE + " caracteres.");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaAcesso other = (ContaAcesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toStringCompleto() {
		return "ContaAcesso [id=" + id + ", login=" + login + ", bloqueado="
				+ bloqueado + ", extensaoRelarotios=" + extensaoRelatorios
				+ ", filtroGrupo=" + filtroGrupo + ", profissional="
				+ profissional + ", perfil=" + perfil + ", palavraChaveGrupo="
				+ palavraChaveGrupo + "]";
	}
	
	@Override
	public String toString() {
		return "ContaAcesso [id=" + id + ", login=" + login + ", token="
				+ token + "]";
	}
}

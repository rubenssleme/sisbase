package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.Versionavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Entity
@Table(name = "atendimento_usuario")
public class AtendimentoUsuario extends AtendimentoAuxiliarBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel, Versionavel{
	
	@Transient
	private String versao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "atendimento_usuario_arquivo", joinColumns = { @JoinColumn(name = "id_atendimento_usuario", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_arquivo", referencedColumnName = "id") })
	private List<Arquivo> arquivos;
	
	public AtendimentoUsuario() {
		informacaoAtendimento = new InformacaoAtendimento();
		arquivos = new ArrayList<>();
	}

	public AtendimentoUsuario(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}
	
	public String getVersao(){
		return versao;
	}

	public void incializarAtendimento(Grupo grupo, Modulo modulo) {
		informacaoAtendimento.inicializarParticipacaoDetalhada(grupo, modulo);
	}

	private void validarExistenciaDeArquivosDuplicados() {
		if (AtendimentoBase.validarDuplicacaoArquivos(arquivos)){
			adicionarErro("Existem arquivos duplicados.");
		}
	}
	
	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();	
		validarInformacaoAtendimento();
		validarExistenciaParticipacaoDetalhada();
		validarExistenciaDeArquivosDuplicados();
	}

	private void validarObrigatoriedade(){
		if (usuario == null) {
			adicionarErro("Insira o Usuário. ");
		}
	}
	
	public static final Comparator<AtendimentoUsuario> obterComparador() {
		return new Comparator<AtendimentoUsuario>() {
			public int compare(AtendimentoUsuario o1, AtendimentoUsuario o2) {
				return o1
						.getUsuario()
						.getInformacaoEssencial()
						.getNome()
						.compareTo(
								o2.getUsuario().getInformacaoEssencial()
										.getNome());
			}
		};
	}
	
	@Override
	public String toString() {
		return "AtendimentoDoUsuario [id=" + id + ", usuario=" + usuario
				+ ", informaçõesAtendimento = " + informacaoAtendimento
				+ "]";
	}
}

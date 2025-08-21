package br.laramara.ti.sislaraserver.dominio.atendimento;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.escola.DiretoriaEnsino;
import br.laramara.ti.sislaraserver.dominio.escola.DreCefai;
import br.laramara.ti.sislaraserver.dominio.grupo.InformacaoAtendimento;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

@Entity
@Table(name = "atendimento_pre_cadastro")
public class AtendimentoPreCadastro extends AtendimentoAuxiliarBase implements
		ValidavelObrigatoriedadeETamanhoMaximo, Identificavel {
	
	@ManyToOne
	@JoinColumn(name = "id_pre_cadastro", nullable = false)
	private PreCadastro preCadastro;
	@Transient
	private Instituicao instituicao;
	@Transient
	private DreCefai dreCefai;
	@Transient
	private DiretoriaEnsino diretoriaEnsino;
	@Transient
	private TipoVinculo tipoVinculo;
	@Transient
	private String nomeOrigem;

	public AtendimentoPreCadastro() {
		informacaoAtendimento = new InformacaoAtendimento();
	}

	public PreCadastro getPreCadastro() {
		return preCadastro;
	}

	public void setPreCadastro(PreCadastro preCadastro) {
		this.preCadastro = preCadastro;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	public DreCefai getDreCefai() {
		return dreCefai;
	}

	public void setDreCefai(DreCefai dreCefai) {
		this.dreCefai = dreCefai;
	}

	public DiretoriaEnsino getDiretoriaEnsino() {
		return diretoriaEnsino;
	}

	public void setDiretoriaEnsino(DiretoriaEnsino diretoriaEnsino) {
		this.diretoriaEnsino = diretoriaEnsino;
	}
	
	public TipoVinculo getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(TipoVinculo tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public String getNomeOrigem() {
		return nomeOrigem;
	}

	public void setNomeOrigem(String nomeOrigem) {
		this.nomeOrigem = nomeOrigem;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarInformacaoAtendimento();
	}

	private void validarObrigatoriedade() {
		if (preCadastro == null) {
			adicionarErro("Insira o Pré-Cadastro. ");
		}
	}

	public static final Comparator<AtendimentoPreCadastro> obterComparador() {
		return new Comparator<AtendimentoPreCadastro>() {
			public int compare(AtendimentoPreCadastro o1,
					AtendimentoPreCadastro o2) {
				return o1
						.getPreCadastro()
						.getInformacaoEssencial()
						.getNome()
						.compareTo(
								o2.getPreCadastro().getInformacaoEssencial()
										.getNome());
			}
		};
	}

	@Override
	public String toString() {
		return "AtendimentoDoUsuario [id=" + id + ", preCadastro="
				+ preCadastro + ", informaçõesAtendimento = "
				+ informacaoAtendimento + "]";
	}
}

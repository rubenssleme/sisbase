package br.laramara.ti.sislaraserver.dominio.grupo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaraserver.dominio.TamanhoMaximoGenerico;

@Entity
@Table(name = "modulo")
public class Modulo {

	public static final Long ID_MODULO_ATENDIMENTO_ESPECIFICO_ESPECIALIZADO = new Long(1);
	public static final Long ID_MODULO_EXCESSO_FALTA = new Long(129);
	public static final Long ID_MODULO_AVALIACAO_TRIAGEM = new Long(37);
	public static final Long ID_MODULO_TRIAGEM_OFTALMOLOGICA = new Long(130);
	public static final Long ID_MODULO_PSICOSSOCIAL_PARA_AS_FAMILIAS = new Long(90);
	public static final Long ID_MODULO_REUNIAO_INTEGRACAO = new Long(127);
	public static final Long ID_MODULO_PSICOSSOCIAL_PARA_OS_JOVENS = new Long(105);
	public static final Long ID_MODULO_REUNIAO_COM_AS_INSTITUICOES_DE_ENSINO = new Long(106);
	public static final Long ID_MODULO_ACOMPANHAMENTO = new Long(38);
				
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = TamanhoMaximoGenerico.NOME, nullable = false)
	private String nome;

	public Modulo() {
	}

	public Modulo(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public static Modulo fabricaModuloAcompanhamento(){
		return new Modulo(ID_MODULO_ACOMPANHAMENTO, "");
	}
	
	public static Modulo fabricaModuloAtendimentoEspecificoEspecializado() {
		return new Modulo(ID_MODULO_ATENDIMENTO_ESPECIFICO_ESPECIALIZADO, "");
	}
	
	public boolean eReuniaoComAsInstituicoesDeEnsino() {
		return id.equals(ID_MODULO_REUNIAO_COM_AS_INSTITUICOES_DE_ENSINO);
	}

	public boolean eAtendimentoEspecificoEspecializado() {
		return id.equals(ID_MODULO_ATENDIMENTO_ESPECIFICO_ESPECIALIZADO);
	}

	public boolean ePsicossocialParaAsFamilias() {
		return id.equals(ID_MODULO_PSICOSSOCIAL_PARA_AS_FAMILIAS);
	}
	
	public boolean ePsicossocialParaOsJovens(){
		return id.equals(ID_MODULO_PSICOSSOCIAL_PARA_OS_JOVENS);
	}
	
	public boolean eAvaliacaoETriagem() {
		return id.equals(ID_MODULO_AVALIACAO_TRIAGEM);
	}

	public boolean eTriagemOftalmologica() {
		return id.equals(ID_MODULO_TRIAGEM_OFTALMOLOGICA);
	}
	
	public boolean eReuniaoIntegracao() {
		return id.equals(ID_MODULO_REUNIAO_INTEGRACAO);
	}

	public boolean eExcessoDeFaltas() {
		return id.equals(ID_MODULO_EXCESSO_FALTA);
	}
	
	public boolean eAcompanhamento() {
		return id.equals(ID_MODULO_ACOMPANHAMENTO);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modulo other = (Modulo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Modulo [id=" + id + ", nome=" + nome + "]";
	}
}

package br.laramara.ti.sislaraserver.dominio.usuario;

import java.util.Comparator;
import java.util.Date;

import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;

public class ProntuarioEscaneado {

	private String nomeArquivo;
	private Date data;
	private Arquivo arquivo;

	public ProntuarioEscaneado(String nomeArquivo, long dataMiliSegundos, Arquivo arquivo) {
		super();
		this.nomeArquivo = removerExtencao(nomeArquivo);
		this.data = new Date(dataMiliSegundos);
		this.arquivo = arquivo;
	}
	
	public ProntuarioEscaneado(String nomeArquivo, Arquivo arquivo) {
		this(nomeArquivo, 0, arquivo);
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo + "." + arquivo.getExtensao().toString();
	}
	
	public Date obterDataAlteracao(){
		 return data;
	}

	private String removerExtencao(String nome) {
		if (nome != null && nome.endsWith(ExtensaoArquivo.jpg.toString())) {
			return nome.replace("." + ExtensaoArquivo.jpg.toString(), "");
		} else {
			return nome;
		}
	}

	public static final Comparator<ProntuarioEscaneado> obterComparador() {
		return new Comparator<ProntuarioEscaneado>() {
			public int compare(ProntuarioEscaneado o1, ProntuarioEscaneado o2) {
				return (o1.obterDataAlteracao().compareTo(o2.obterDataAlteracao()));
			}
		};
	}
}

package br.laramara.ti.sislaraserver.dominio.doacao;

import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;
import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;

public class RecursoEDescricaoRecurso extends Validavel implements ValidavelObrigatoriedadeETamanhoMaximo {

	private Recurso recurso;
	private DescricaoRecurso descricaoRecurso;

	public RecursoEDescricaoRecurso(Recurso recurso, DescricaoRecurso descricaoRecurso) {
		super();
		this.recurso = recurso;
		this.descricaoRecurso = descricaoRecurso;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public DescricaoRecurso getDescricaoRecurso() {
		return descricaoRecurso;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (recurso == null) {
			adicionarErro("Insira um Recurso.");
		}		
	}

}

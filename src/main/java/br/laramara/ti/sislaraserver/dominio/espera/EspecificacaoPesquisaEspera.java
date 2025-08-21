package br.laramara.ti.sislaraserver.dominio.espera;

import br.laramara.ti.sislaraserver.dominio.EspecificacaoPesquisaBase;
import br.laramara.ti.sislaraserver.dominio.ValidavelObrigatoriedadeETamanhoMaximo;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class EspecificacaoPesquisaEspera extends EspecificacaoPesquisaBase
		implements ValidavelObrigatoriedadeETamanhoMaximo {

	private TipoEspera tipoEspera;
	private StatusEspera statusEspera;
	private Setor setor;
	private NomeGrupo nomeGrupo;
	private boolean interesse;
	private boolean lmLigou;
	private boolean pendencias;
	
	public TipoEspera getTipoEspera() {
		return tipoEspera;
	}

	public void setTipoEspera(TipoEspera tipoEspera) {
		this.tipoEspera = tipoEspera;
	}

	public StatusEspera getStatusEspera() {
		return statusEspera;
	}

	public void setStatusEspera(StatusEspera statusEspera) {
		this.statusEspera = statusEspera;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public NomeGrupo getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(NomeGrupo nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public boolean isInteresse() {
		return interesse;
	}

	public void setInteresse(boolean interesse) {
		this.interesse = interesse;
	}
	
	public boolean isLmLigou() {
		return lmLigou;
	}

	public void setLmLigou(boolean lmLigou) {
		this.lmLigou = lmLigou;
	}

	public void setPendencias(boolean pendencias) {
		this.pendencias = pendencias;
	}
	
	public boolean isPendencias() {
		return pendencias;
	}

	@Override
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		if (possuiTodosItensBaseNulos() && nomeGrupo == null && tipoEspera == null && statusEspera == null
				&& setor == null && interesse == false && lmLigou == false && pendencias == false) {
			adicionarErro("Insira um parâmetro para pesquisa.");
		}
		super.validarObrigatoriedadeETamanhoMaximoDeDados();
	}

	@Override
	public String toString() {
		return "EspecificacaoPesquisaEspera [tipoEspera=" + tipoEspera
				+ ", statusEspera=" + statusEspera + ", setor=" + setor
				+ ", nomeGrupo=" + nomeGrupo + ", " + super.toString() + ", interesse=" + interesse 
				+ ", lmLigou=" + lmLigou +  ", pendencias=" + pendencias + "]";
	}
}

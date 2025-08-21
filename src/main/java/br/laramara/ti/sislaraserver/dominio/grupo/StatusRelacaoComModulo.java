package br.laramara.ti.sislaraserver.dominio.grupo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public enum StatusRelacaoComModulo {
	ACESSO(null), AFASTADO(null), DESISTENTE(null), CONCLUIDO(DESISTENTE), DESLIGADO(null), EVENTUAL(null), FREQUENTOU(DESISTENTE), INTEGRADO(null), PARTICIPOU(DESISTENTE), PROVISORIO(null), REMOVIDO(DESISTENTE), CASO_NOVO(null), RETORNO(null);

	private StatusRelacaoComModulo equivalente;
	
	private StatusRelacaoComModulo(StatusRelacaoComModulo equivalente) {
		this.equivalente = equivalente;
	}
	
	private boolean possuiEquivalente(){
		return equivalente != null;
	}
	
	public StatusRelacaoComModulo obterExatoOuEquivalente(){
		return possuiEquivalente() ? equivalente : this;
	}
	
	public static final List<StatusRelacaoComModulo> obterDisponivelParaInclusao(Grupo grupo) {
		List<StatusRelacaoComModulo> retorno = new ArrayList<>();
		retorno.add(INTEGRADO);
		retorno.add(PROVISORIO);
		retorno.add(ACESSO);
		if (grupo.eDescricaoOrientacaoSocioeducativa() || grupo.eDescricaoAtividadeDeCulturaELazer()){
			retorno.add(EVENTUAL);
		}
		return retorno;
	}
	
	public static final List<StatusRelacaoComModulo> disponivelParaRelacaoComModulos(Setor setorSolicitante) {
		List<StatusRelacaoComModulo> retorno = new ArrayList<>();
		retorno.add(ACESSO);
		retorno.add(AFASTADO);
		retorno.add(CONCLUIDO);
		retorno.add(DESISTENTE);
		retorno.add(DESLIGADO);
		if (setorSolicitante.equals(Setor.PROCEJA)) {
			retorno.add(FREQUENTOU);
			retorno.add(PARTICIPOU);
		}
		retorno.add(EVENTUAL);
		retorno.add(INTEGRADO);
		retorno.add(PROVISORIO);
		retorno.add(REMOVIDO);
		Collections.sort(retorno, StatusRelacaoComModulo.obterComparador());
		return retorno;
	}
	
	//CUIDADO: A ordem é importante na execução da regra de negócio.
	public static final List<StatusRelacaoComModulo> statusOrdenadoPorImportancia() {
		List<StatusRelacaoComModulo> retorno = new ArrayList<>();
		retorno.add(DESLIGADO);
		retorno.add(INTEGRADO);
		retorno.add(AFASTADO);
		retorno.add(DESISTENTE);
		retorno.add(EVENTUAL);
		return retorno;
	}
	
	private static final Comparator<StatusRelacaoComModulo> obterComparador() {
		return new Comparator<StatusRelacaoComModulo>() {
			public int compare(StatusRelacaoComModulo o1, StatusRelacaoComModulo o2) {
				return o1.name().compareTo(o2.name());
			}
		};
	}

	public boolean eDesligado() {
		return this.equals(DESLIGADO);
	}
}

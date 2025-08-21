package br.laramara.sistelemarketingserver.dominio.doacao;

import java.util.Arrays;

import br.laramara.sistelemarketingcommons.dtos.doacao.MetodoDTO;
import br.laramara.sistelemarketingcommons.dtos.doacao.TipoRetiradaDTO;

public class ContextoMetodo {

	public static MetodoDTO fabricarDto() {
		return new MetodoDTO("DINHEIRO", Arrays.asList(new TipoRetiradaDTO("MENSAGEIRO")));
	}

}

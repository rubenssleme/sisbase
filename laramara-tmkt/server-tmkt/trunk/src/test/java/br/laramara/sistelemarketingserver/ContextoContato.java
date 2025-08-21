package br.laramara.sistelemarketingserver;

import java.util.Arrays;

import br.laramara.sistelemarketingserver.dominio.contato.Contato;

public class ContextoContato {

	public static Contato fabricarContato() {
		Contato contato = new Contato();
		contato.setId(new Long(3478));
		contato.setCep(ContextoLogradouro.fabricarLogradouro());
		contato.setNumero("573");
		contato.setComplemento("AP 63");
		contato.setCpf("98291283933");
		contato.setEmail("sadkjd@dsadas.com");
		contato.setTelefones(Arrays.asList(ContextoTelefone.fabricar()));
		return contato;
	}

}

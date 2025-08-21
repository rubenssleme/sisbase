package br.laramara.sistelemarketingserver.dominio.seguranca;

import br.laramara.sistelemarketingcommons.dtos.seguranca.PermissaoDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.Permissao;

public class ContextoPermissao {

	public static Permissao fabricarPermissao() {
		Permissao permissao = new Permissao();
		permissao.setId(new Long(1));
		permissao.setDescricao("Nivel Visualizar");
		return permissao;
	}

	public static PermissaoDTO fabricarPermissaoDto() {
		PermissaoDTO permissao = new PermissaoDTO();
		permissao.setId(new Long(1));
		permissao.setDescricao("Nivel Visualizar");
		return permissao;
	}
}

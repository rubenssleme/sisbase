package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EmpresaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Empresa;

public class ContextoEmpresa {

	public static Empresa criarEmpresa() {
		return new Empresa(new Long(1000), "EMPRESA TESTE");
	}

	public static EmpresaDTO criarEmpresaDto() {
		return new EmpresaDTO(new Long(1000), "EMPRESA TESTE");
	}

}

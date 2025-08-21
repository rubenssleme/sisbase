package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.doacao.EmpresaDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Empresa;

public class FabricaEmpresa extends FabricaBase<EmpresaDTO, Empresa> {
	public final EmpresaDTO converterParaDTO(Empresa empresa) {
		return empresa != null ? new EmpresaDTO(empresa.getId(), empresa.getNome()) : null;
	}

	public final Empresa converterParaDominio(EmpresaDTO empresaDto) {
		return empresaDto != null ? new Empresa(empresaDto.getId(), empresaDto.toString()) : null;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.doacao.EmpresaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.doacao.Empresa;

public class TestesFabricaEmpresa {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_empresa_converte_objeto_de_dominio_para_dto() {
		Empresa empresa = ContextoEmpresa.criarEmpresa();

		EmpresaDTO empresaDTO = new FabricaEmpresa().converterParaDTO(empresa);

		Assert.assertEquals(empresaDTO.getId(), empresa.getId());
		Assert.assertEquals(empresaDTO.toString(), empresa.getNome());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_empresa_converte_objeto_de_dto_para_dominio() {
		EmpresaDTO empresaDto = ContextoEmpresa.criarEmpresaDto();

		Empresa empresaObtido = new FabricaEmpresa().converterParaDominio(empresaDto);

		Assert.assertEquals(empresaObtido.getId(), empresaDto.getId());
		Assert.assertEquals(empresaObtido.getNome(), empresaDto.toString());
	}
}

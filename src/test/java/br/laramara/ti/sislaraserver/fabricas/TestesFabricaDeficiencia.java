package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.DeficienciaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.usuario.Deficiencia;

public class TestesFabricaDeficiencia {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_deficiencia_converte_objeto_de_dominio_para_dto() {
		Deficiencia deficiencia = new Deficiencia(new Long(12222), "AUDITIVA",
				true);

		DeficienciaDTO deficienciaDTO = new FabricaDeficiencia()
				.converterParaDTO(deficiencia);

		Assert.assertEquals(deficienciaDTO.getId(), deficiencia.getId());
		Assert.assertEquals(deficienciaDTO.toString(),
				deficiencia.getDescricao());
		Assert.assertEquals(deficienciaDTO.isEtiologiaObrigatorio(),
				deficiencia.isEtiologiaObrigatorio());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_deficiencia_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(122222);
		String descricao = "AUDITIVA";
		boolean etiologia = true;

		DeficienciaDTO deficienciaDto = new DeficienciaDTO(id, descricao,
				etiologia);

		Deficiencia deficienciaObtido = new FabricaDeficiencia()
				.converterParaDominio(deficienciaDto);

		Assert.assertEquals(deficienciaObtido.getDescricao(), descricao);
		Assert.assertEquals(deficienciaObtido.getId(), id);
		Assert.assertEquals(deficienciaObtido.isEtiologiaObrigatorio(),
				etiologia);
	}
}

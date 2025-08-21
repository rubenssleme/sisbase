package br.laramara.ti.sislaraserver.fabricas.externa;

import org.junit.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.fabricas.ContextoGrupo;
import br.laramara.ti.sislaraserver.fabricas.FabricaDetalheCurso;

public class TestesFabricaDetalheCurso {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_detalhecurso_converte_dominio_para_dto() {
		Grupo grupo = ContextoGrupo.fabricarGrupoComDescricaoTipoAtendimentoCursosValido();

		DetalheCursoDTO detalheCursoDto = new FabricaDetalheCurso().converterParaDTO(grupo);

		Assert.assertEquals(detalheCursoDto.getIdGrupo(), grupo.getId());
		Assert.assertEquals(detalheCursoDto.getCargaHoraria(), grupo.getModulosPeriodos().get(0).getCargaHoraria());
		Assert.assertEquals(detalheCursoDto.getNumeroVagas(), grupo.getModulosPeriodos().get(0).getVagas());
		Assert.assertEquals(detalheCursoDto.getDataCurso(), grupo.getDataCurso());
		Assert.assertEquals(detalheCursoDto.getPeriodoPreInscricoes(), grupo.getPeriodoPreInscricoes());
		Assert.assertEquals(detalheCursoDto.getDescricoesEventoDto().size(), grupo.getDescricoesEvento().size());
		Assert.assertEquals(detalheCursoDto.getInvestimento(), grupo.getInvestimento());
		Assert.assertEquals(detalheCursoDto.getNomeCurso(), grupo.getNomeCurso());
		Assert.assertEquals(detalheCursoDto.getValorTotalAlmoco(), grupo.getValorTotalAlmoco());
	}

}

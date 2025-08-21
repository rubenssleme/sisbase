package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;

public class TestesFabricaModuloPeriodo {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_moduloperiodo_converte_objeto_de_dominio_para_dto() {
		ModuloPeriodo moduloPeriodo = ContextoModuloPeriodo
				.fabricarComTodosOsDadosInformatica();
		moduloPeriodo.setId(null);

		ModuloPeriodoDTO moduloPeriodoDTO = new FabricaModuloPeriodo()
				.converterParaDTO(moduloPeriodo);

		Assert.assertEquals(moduloPeriodoDTO.getId(), moduloPeriodo.getId());
		Assert.assertEquals(moduloPeriodoDTO.getVersao(), moduloPeriodo.getVersao());
		Assert.assertEquals(moduloPeriodoDTO.getModuloDto().toString(),
				moduloPeriodo.getModulo().getNome());
		Assert.assertEquals(moduloPeriodoDTO.getProfissionaisDto().size(),
				moduloPeriodo.obterSomenteProfissionais().size());
		Assert.assertEquals(moduloPeriodoDTO.getProfissionaisVinculoDto().size(),
				moduloPeriodo.getProfissionaisVinculo().size());
		Assert.assertEquals(moduloPeriodoDTO.getLocalAtendimentoDTO()
				.toString(), moduloPeriodo.getLocalAtendimento().getNome());
		Assert.assertEquals(moduloPeriodoDTO.getVagas(), moduloPeriodo.getVagas());
		Assert.assertEquals(moduloPeriodoDTO.getCargaHoraria(), moduloPeriodo.getCargaHoraria());
		Assert.assertEquals(moduloPeriodoDTO.getCargaHorariaMinima(), moduloPeriodo.getCargaHorariaMinima());
		Assert.assertEquals(moduloPeriodoDTO.getAtendimentosGrupoDto().size(),
				moduloPeriodo.getAtendimentosGrupo().size());
		Assert.assertEquals(moduloPeriodoDTO.getModulosUsuariosDto().size(),
				moduloPeriodo.getModulosUsuarios().size());
		Assert.assertEquals(moduloPeriodoDTO.getDiasSemanaEHorariosDaAtividadeDto().size(),
				moduloPeriodo.getDiasSemanaEHorariosDaAtividade().size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_moduloperiodo_converte_objeto_de_dto_para_dominio() {
		ModuloPeriodoDTO moduloPeriodoDto = ContextoModuloPeriodo
				.fabricarModuloPeriodoDTO();
		moduloPeriodoDto.setId(new Long(1233));

		ModuloPeriodo moduloPeriodoObtido =  new FabricaModuloPeriodo()
				.converterParaDominio(moduloPeriodoDto);

		Assert.assertEquals(moduloPeriodoObtido.getId(),
				moduloPeriodoDto.getId());
		Assert.assertEquals(moduloPeriodoObtido.getModulo().getNome(),
				moduloPeriodoDto.getModuloDto().toString());
		Assert.assertEquals(moduloPeriodoObtido.getProfissionaisVinculo().size(),
				moduloPeriodoDto.getProfissionaisVinculoDto().size());
		Assert.assertEquals(
				moduloPeriodoObtido.getLocalAtendimento().getNome(),
				moduloPeriodoDto.getLocalAtendimentoDTO().toString());
		Assert.assertEquals(moduloPeriodoObtido.getVagas(),
				moduloPeriodoDto.getVagas());
		Assert.assertEquals(moduloPeriodoObtido.getCargaHoraria(),
				moduloPeriodoDto.getCargaHoraria());
		Assert.assertEquals(moduloPeriodoObtido.getCargaHorariaMinima(),
				moduloPeriodoDto.getCargaHorariaMinima());
		Assert.assertEquals(moduloPeriodoObtido.getAtendimentosGrupo().size(),
				moduloPeriodoDto.getAtendimentosGrupoDto().size());
		Assert.assertEquals(moduloPeriodoObtido.getModulosUsuarios().size(),
				moduloPeriodoDto.getModulosUsuariosDto().size());
		Assert.assertEquals(moduloPeriodoObtido.getDiasSemanaEHorariosDaAtividade().size(),
				moduloPeriodoDto.getDiasSemanaEHorariosDaAtividadeDto().size());
	}
}

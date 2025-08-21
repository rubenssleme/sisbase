package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;

public class TestesFabricaNomeGrupo {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_nomegrupo_converte_objeto_de_dominio_para_dto() {
		NomeGrupo nomeGrupo = new NomeGrupo(new Long(1222), "G00");

		NomeGrupoDTO nomeGrupoDTO = new FabricaNomeGrupo()
				.converterParaDTO(nomeGrupo);

		Assert.assertEquals(nomeGrupoDTO.getId(), nomeGrupo.getId());
		Assert.assertEquals(nomeGrupoDTO.toString(), nomeGrupo.getNome());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_nomegrupo_converte_objeto_de_dto_para_dominio() {
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(new Long(1222), "G02");

		NomeGrupo nomeGrupoObtido = new FabricaNomeGrupo()
				.converterParaDominio(nomeGrupoDto);

		Assert.assertEquals(nomeGrupoObtido.getId(), nomeGrupoDto.getId());
		Assert.assertEquals(nomeGrupoObtido.getNome(), nomeGrupoDto.toString());
	}
}

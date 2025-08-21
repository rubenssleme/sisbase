package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDescricaoTipoAtendimentoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void tipodescricaoatendimentodto_foi_construida_com_sucesso() {
		Long id = new Long(12222);
		String nome = "ATENDIMENTO SUPER";
		String descricao = "Descrição do atendimento.";
		String sigla = "OOAD";
		Long idTipoAtendimento = new Long(2222);

		List<SetorDTO> setoresDto = new ArrayList<>();
		setoresDto.add(new SetorDTO("CTO"));
		
		List<NomeGrupoDTO> nomesGruposDto = new ArrayList<>();
		nomesGruposDto.add(new NomeGrupoDTO(new Long(1), "G01"));
		
		List<ModuloDTO> modulosDto = new ArrayList<>();
		modulosDto.add(new ModuloDTO(new Long(1), "Informática"));

		TipoAtendimentoDTO tipoAtendimentoDto = new TipoAtendimentoDTO();
		tipoAtendimentoDto.setId(idTipoAtendimento);

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(id);
		descricaoTipoAtendimentoDto.setNome(nome);
		descricaoTipoAtendimentoDto.setDescricao(descricao);
		descricaoTipoAtendimentoDto.setSigla(sigla);
		descricaoTipoAtendimentoDto.setSetoresDto(setoresDto);
		descricaoTipoAtendimentoDto.setTipoAtendimentoDto(tipoAtendimentoDto);
		descricaoTipoAtendimentoDto.setNomesGruposDto(nomesGruposDto);
		descricaoTipoAtendimentoDto.setModulosDto(modulosDto);

		Assert.assertEquals(descricaoTipoAtendimentoDto.getId(), id);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getNome(), nome);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getDescrica(),
				descricao);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getSigla(), sigla);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getSetoresDto().size(),
				1);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getTipoAtendimentoDto()
				.getId(), idTipoAtendimento);
		Assert.assertEquals(descricaoTipoAtendimentoDto.getNomesGruposDto()
				.size(), nomesGruposDto.size());
		Assert.assertEquals(descricaoTipoAtendimentoDto.getModulosDto().size(),
				modulosDto.size());
	}
}

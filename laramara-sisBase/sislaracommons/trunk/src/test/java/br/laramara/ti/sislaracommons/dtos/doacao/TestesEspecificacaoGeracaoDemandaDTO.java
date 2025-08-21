package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesEspecificacaoGeracaoDemandaDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void especificacaogeracaodemanda_com_um_parametro_construida_com_sucesso() {
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(1));
	
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(new Long(1));
		
		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(new Long(3));

		ProjetoDTO projetoDto = new ProjetoDTO();
		preCadastroDto.setId(new Long(12));

		RecursoDTO recursoDto = new RecursoDTO(new Long(1222), "TESTE", false, "100,00");

		List<ProfissionalDTO> profissionaisDto = new ArrayList<>();
		ProfissionalDTO profissionalDto = new ProfissionalDTO(new Long(1),
				"Paulo", "1234");
		profissionaisDto.add(profissionalDto);
		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = new DescricaoTipoAtendimentoDTO();
		descricaoTipoAtendimentoDto.setId(new Long(23));

		EspecificacaoGeracaoDemandaDTO especificacaoGeracaoDemandaDto = new EspecificacaoGeracaoDemandaDTO();
		especificacaoGeracaoDemandaDto.setGrupoDto(grupoDto);
		especificacaoGeracaoDemandaDto.setPreCadastrosDto(preCadastroDto);
		especificacaoGeracaoDemandaDto.setUsuariosDto(usuarioDto);
		especificacaoGeracaoDemandaDto.setProjetoDto(projetoDto);
		especificacaoGeracaoDemandaDto.setRecursosDto(Arrays.asList(recursoDto));

		Assert.assertEquals(especificacaoGeracaoDemandaDto.getGrupoDto()
				.getId(), grupoDto.getId());
		Assert.assertEquals(especificacaoGeracaoDemandaDto.getPreCadastrosDto().getId(), preCadastroDto.getId());
		Assert.assertEquals(especificacaoGeracaoDemandaDto.getUsuariosDto().getId(), usuarioDto.getId());
		Assert.assertEquals(especificacaoGeracaoDemandaDto.getProjetoDto()
				.getId(), projetoDto.getId());
		Assert.assertEquals(especificacaoGeracaoDemandaDto.getRecursosDto()
				.get(0).getId(), recursoDto.getId());
	}
}

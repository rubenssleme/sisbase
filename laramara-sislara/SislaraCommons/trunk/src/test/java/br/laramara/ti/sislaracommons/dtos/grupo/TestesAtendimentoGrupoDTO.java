package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.StatusAtendimentoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesAtendimentoGrupoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void atendimentogrupodto_foi_construida_com_sucesso() {
		Long id = new Long(12222);
		ModuloPeriodoDTO moduloPeriodoDto = new ModuloPeriodoDTO();
		moduloPeriodoDto.setId(id);
		String data = "2010-12-31";
		String hora = "12:12";
		String texto = "grande texto";
		StatusAtendimentoDTO status = new StatusAtendimentoDTO("NORMAL");
		List<AtendimentoUsuarioDTO> atendimentosUsuariosDto = new ArrayList<>();
		atendimentosUsuariosDto.add(new AtendimentoUsuarioDTO());
		atendimentosUsuariosDto.add(new AtendimentoUsuarioDTO());
		List<AtendimentoPreCadastroDTO> atendimentosPreCadastroDto = new ArrayList<>();
		atendimentosPreCadastroDto.add(new AtendimentoPreCadastroDTO());
		atendimentosPreCadastroDto.add(new AtendimentoPreCadastroDTO());
		List<AtendimentoProfissionalDTO> atendimentoProfissionalDto = new ArrayList<>();
		atendimentoProfissionalDto.add(new AtendimentoProfissionalDTO());
		atendimentoProfissionalDto.add(new AtendimentoProfissionalDTO());

		AtendimentoGrupoDTO atendimentoDto = new AtendimentoGrupoDTO();
		atendimentoDto.setId(id);
		atendimentoDto.setData(data);
		atendimentoDto.setStatusAtendimentoDto(status);
		atendimentoDto.setHorarioDto(new HorarioDTO(hora, hora));
		atendimentoDto.setDescricao(texto);
		atendimentoDto.setInterdisciplinar(texto);
		atendimentoDto.setAtendimentosUsuariosDto(atendimentosUsuariosDto);
		atendimentoDto.setAtendimentosPreCadastroDto(atendimentosPreCadastroDto);
		atendimentoDto
				.setAtendimentosProfissionaisDto(atendimentoProfissionalDto);

		Assert.assertEquals(atendimentoDto.getId(), id);
		Assert.assertEquals(atendimentoDto.getData(), data);
		Assert.assertEquals(atendimentoDto.getStatusAtendimentoDto(), status);
		Assert.assertEquals(atendimentoDto.getHorarioDto().getHoraInicio(), hora);
		Assert.assertEquals(atendimentoDto.getHorarioDto().getHoraTermino(), hora);
		Assert.assertEquals(atendimentoDto.getDescricao(), texto);
		Assert.assertEquals(atendimentoDto.getInterdisciplinar(), texto);
		Assert.assertEquals(atendimentoDto.getAtendimentosUsuariosDto().size(),
				atendimentosUsuariosDto.size());
		Assert.assertEquals(atendimentoDto.getAtendimentosPreCadastroDto()
				.size(), atendimentosPreCadastroDto.size());
		Assert.assertEquals(atendimentoDto.getAtendimentosProfissionaisDto()
				.size(), atendimentoProfissionalDto.size());
	}
}

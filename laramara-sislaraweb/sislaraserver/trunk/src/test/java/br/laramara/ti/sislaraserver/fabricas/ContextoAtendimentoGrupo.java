package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.HorarioDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaraserver.dominio.Horario;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoPreCadastro;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoProfissional;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoUsuario;

public class ContextoAtendimentoGrupo {

	public static AtendimentoGrupo fabricarAtendimentoComTodosOsDados() {
		AtendimentoGrupo atendimento = new AtendimentoGrupo();
		atendimento.setData("31/12/2012");
		atendimento.setHorario(new Horario("09:00", "12:00"));
		atendimento.setDescricao("Grande texto de descrição");
		atendimento.setInterdisciplinar("Grande texto interdisciplinar");
		List<AtendimentoUsuario> atendimentosUsuarios = new ArrayList<>();
		atendimentosUsuarios.add(ContextoAtendimentoUsuario
				.fabricarAtendimentoUsuarioComTodosOsDados());
		atendimento.setAtendimentosUsuarios(atendimentosUsuarios);
		List<AtendimentoPreCadastro> atendimentosPreCadastro = new ArrayList<>();
		atendimentosPreCadastro.add(ContextoAtendimentoPreCadastro
				.fabricarAtendimentoPreCadastroComTodosOsDados());
		atendimento.setAtendimentosPreCadastro(atendimentosPreCadastro);
		List<AtendimentoProfissional> atendimentosProfissionais = new ArrayList<>();
		atendimentosProfissionais.add(ContextoAtendimentoProfissional
				.fabricarAtendimentoProfissionalComTodosOsDados());
		atendimento.setAtendimentosProfissionais(atendimentosProfissionais);
		atendimento.setPessoasSemCadastro("10");
		return atendimento;
	}

	public static AtendimentoGrupoDTO construirAtendimentoDTO() {
		AtendimentoGrupoDTO atendimentoDto = new AtendimentoGrupoDTO();
		atendimentoDto.setId(new Long(11111));
		atendimentoDto.setData("31/12/2012");
		atendimentoDto.setHorarioDto(new HorarioDTO("09:00", "12:00"));
		atendimentoDto.setDescricao("Grande texto de descrição");
		atendimentoDto.setInterdisciplinar("Grande texto interdisciplinar.");
		List<AtendimentoUsuarioDTO> atendimentosUsuariosDto = new ArrayList<>();
		atendimentosUsuariosDto.add(ContextoAtendimentoUsuario
				.construirAtendimentoUsuarioDTO());
		atendimentoDto.setAtendimentosUsuariosDto(atendimentosUsuariosDto);
		List<AtendimentoPreCadastroDTO> atendimentosPreCadastroDto = new ArrayList<>();
		atendimentosPreCadastroDto.add(ContextoAtendimentoPreCadastro
				.construirAtendimentoPreCadastroDTO());
		atendimentoDto
				.setAtendimentosPreCadastroDto(atendimentosPreCadastroDto);
		List<AtendimentoProfissionalDTO> atendimentosProfissionaisDto = new ArrayList<>();
		atendimentosProfissionaisDto.add(ContextoAtendimentoProfissional
				.construirAtendimentoProfissionalDTO());
		atendimentoDto
				.setAtendimentosProfissionaisDto(atendimentosProfissionaisDto);
		atendimentoDto.setPessoasSemCadastro("10");
		return atendimentoDto;
	}
}

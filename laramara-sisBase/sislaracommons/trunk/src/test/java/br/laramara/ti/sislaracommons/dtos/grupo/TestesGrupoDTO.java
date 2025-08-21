package br.laramara.ti.sislaracommons.dtos.grupo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesGrupoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void grupodto_foi_construida_com_sucesso() {
		Long id = new Long(1222);
		String versao = "1234";
		boolean ativo = true;
		SetorDTO setor = new SetorDTO("PROCEJA");
		String dataInicio = "31/01/2012";
		String dataTermino = "31/12/2012";
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(new Long(1000), "G0");
		String turma = "1";
		String nivel = "1";
		String descricao = "Descrição do texto.";
		List<TipificacaoServicoDTO> tipificacoesServicoDto = new ArrayList<>();
		tipificacoesServicoDto.add(new TipificacaoServicoDTO(new Long(1), "Assessoria"));
		List<UsuarioDTO> todosUsuariosDto = new ArrayList<>();
		todosUsuariosDto.add(new UsuarioDTO(new Long(1222)));
		todosUsuariosDto.add(new UsuarioDTO(new Long(1333)));

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setId(id);
		grupoDto.setVersao(versao);
		grupoDto.setAtivo(ativo);
		grupoDto.setInicializado(ativo);
		grupoDto.setSetorDto(setor);
		grupoDto.setDescricaoTipoAtendimentoDTO(new DescricaoTipoAtendimentoDTO());
		grupoDto.setDataInicio(dataInicio);
		grupoDto.setDataTermino(dataTermino);
		grupoDto.setNomeGrupoDto(nomeGrupoDto);
		grupoDto.setTurma(turma);
		grupoDto.setNivel(nivel);
		grupoDto.setDescricao(descricao);
		grupoDto.setTipificacoesServicoDto(tipificacoesServicoDto);
		grupoDto.setTodosUsuariosDto(todosUsuariosDto);

		List<ModuloPeriodoDTO> modulosPeriodosDto = new ArrayList<>();
		modulosPeriodosDto.add(new ModuloPeriodoDTO());
		modulosPeriodosDto.add(new ModuloPeriodoDTO());
		grupoDto.setModulosPeriodosDto(modulosPeriodosDto);

		Assert.assertEquals(grupoDto.getId(), id);
		Assert.assertEquals(grupoDto.getVersao(), versao);
		Assert.assertEquals(grupoDto.isAtivo(), ativo);
		Assert.assertEquals(grupoDto.isInicializado(), ativo);
		Assert.assertEquals(grupoDto.getSetorDto(), setor);
		Assert.assertNotNull(grupoDto.getDescricaoTipoAtendimentoDTO());
		Assert.assertEquals(grupoDto.getDataInicio(), dataInicio);
		Assert.assertEquals(grupoDto.getDataTermino(), dataTermino);
		Assert.assertEquals(grupoDto.getNomeGrupoDto(), nomeGrupoDto);
		Assert.assertEquals(grupoDto.getTurma(), turma);
		Assert.assertEquals(grupoDto.getNivel(), nivel);
		Assert.assertEquals(grupoDto.getModulosPeriodosDto().size(), 2);
		Assert.assertEquals(grupoDto.getDescricao(), descricao);
		Assert.assertEquals(grupoDto.getTipificacoesServicoDto().size(),
				tipificacoesServicoDto.size());
		Assert.assertEquals(grupoDto.getTodosUsuariosDto().size(),
				todosUsuariosDto.size());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void grupodto_obtem_nome_modulos_com_sucesso() {
		GrupoDTO grupoDTO = construirGrupo();

		Assert.assertEquals(grupoDTO.toString(),
				"CAMT-02 - Informática, Inglês - 31/12/2012");
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void grupodto_obtem_modulo_periodo_por_nome() {
		GrupoDTO grupoDTO = construirGrupo();
		ModuloPeriodoDTO moduloPeriodoDTO = new ModuloPeriodoDTO();
		moduloPeriodoDTO.setId(new Long(1));

		Assert.assertNotNull(grupoDTO.obterModuloPeriodo(moduloPeriodoDTO));
	}

	private GrupoDTO construirGrupo() {
		ModuloPeriodoDTO moduloPeriodoDTO = new ModuloPeriodoDTO();
		moduloPeriodoDTO.setId(new Long(1));
		ModuloDTO moduloDTO = new ModuloDTO(new Long(1221), "Informática");
		moduloPeriodoDTO.setModuloDto(moduloDTO);

		ModuloPeriodoDTO moduloPeriodo2DTO = new ModuloPeriodoDTO();
		moduloPeriodo2DTO.setId(new Long(2));
		ModuloDTO modulo2DTO = new ModuloDTO(new Long(1222), "Inglês");
		moduloPeriodo2DTO.setModuloDto(modulo2DTO);

		List<ModuloPeriodoDTO> modulosPeriodosDto = new ArrayList<>();
		modulosPeriodosDto.add(moduloPeriodoDTO);
		modulosPeriodosDto.add(moduloPeriodo2DTO);

		NomeGrupoDTO nomeGrupoDTO = new NomeGrupoDTO(new Long(1222), "CAMT");
		String turma = "02";
		String dataInicio = "31/12/2012";

		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO.setNomeGrupoDto(nomeGrupoDTO);
		grupoDTO.setTurma(turma);
		grupoDTO.setModulosPeriodosDto(modulosPeriodosDto);
		grupoDTO.setDataInicio(dataInicio);
		return grupoDTO;
	}
}

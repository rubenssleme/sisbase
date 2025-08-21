package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipificacaoServicoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class ContextoGrupo {

	public static Grupo fabricarGrupoComTodosOsDados() {
		Grupo grupo = new Grupo();
		grupo.mudarVersao();
		grupo.setSetor(Setor.PROCEJA);
		grupo.setDescricaoTipoAtendimento(ContextoDescricaoTipoAtendimento
				.fabricarComTodosOsDados());
		grupo.setDataInicio("31/01/2012");
		grupo.setDataTermino("31/12/2012");
		grupo.setNomeGrupo(new NomeGrupo(new Long(1002), "G02"));
		grupo.setTurma("1");
		grupo.setNivel("1");
		grupo.setAtivo(true);

		List<ModuloPeriodo> modulosPeriodos = new ArrayList<>();
		modulosPeriodos.add(ContextoModuloPeriodo.fabricarComTodosOsDadosInformatica());
		modulosPeriodos.add(ContextoModuloPeriodo.fabricarComTodosOsDadosIngles());
		grupo.setModulosPeriodos(modulosPeriodos);
		
		List<TipificacaoServico> tipificacoesServico = new ArrayList<>();
		tipificacoesServico.add(ContextoTipificacaoServico.fabricarComTodosOsDados());
		
		grupo.setDescricao("Descrição do grupo.");
		grupo.setTipificacoesServico(tipificacoesServico);
		
		grupo.setInstituicaoDoacao(ContextoInstituicao.fabricarInstituicaoComTodosOsDados());
		List<LoteRecurso> loteRecursos = new ArrayList<>();
		loteRecursos.add(ContextoLoteRecurso.fabricarComTodosOsDados());
		grupo.setDoacaoRecurso(loteRecursos);
		
		return grupo;
	}

	public static Grupo fabricarGrupoComTodosOsDadosSemAtendimentos() {
		Grupo grupo = fabricarGrupoComTodosOsDados();
		grupo.getModulosPeriodos().get(0)
				.setAtendimentosGrupo(new ArrayList<AtendimentoGrupo>());
		grupo.getModulosPeriodos().get(1)
				.setAtendimentosGrupo(new ArrayList<AtendimentoGrupo>());
		return grupo;
	}

	public static GrupoDTO construirGrupoDTOComIdentificacao(){
		GrupoDTO grupoDto = construirGrupoDTO();
		grupoDto.setId(new Long(12222));
		return grupoDto;
	} 
	
	public static GrupoDTO construirGrupoDTO() {
		
		SetorDTO setor = new SetorDTO("PROCEJA");
		String versao = "1";
		DescricaoTipoAtendimentoDTO tipoAtendimentoDTO = ContextoDescricaoTipoAtendimento.construirDescricaoTipoAtendimentoDTO();
		String dataInicio = "01/01/2112";
		String dataTermino = "31/01/2112";
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(new Long(1000),"G00");
		String turma = "01";
		String nivel = "1";
		String descricao = "Descrição do texto.";

		GrupoDTO grupoDto = new GrupoDTO();
		grupoDto.setVersao(versao);
		grupoDto.setSetorDto(setor);
		grupoDto.setDescricaoTipoAtendimentoDTO(tipoAtendimentoDTO);
		grupoDto.setDataInicio(dataInicio);
		grupoDto.setDataTermino(dataTermino);
		grupoDto.setNomeGrupoDto(nomeGrupoDto);
		grupoDto.setTurma(turma);
		grupoDto.setNivel(nivel);
		grupoDto.setDescricao(descricao);
		grupoDto.setAtivo(true);
		
		List<ModuloPeriodoDTO> modulosPeriodos = new ArrayList<>();
		modulosPeriodos.add(ContextoModuloPeriodo.fabricarModuloPeriodoDTO());
		grupoDto.setModulosPeriodosDto(modulosPeriodos);
		
		List<TipificacaoServicoDTO> tipificacoesServicoDto = new ArrayList<>();
		tipificacoesServicoDto.add(ContextoTipificacaoServico
				.construirTipificacaoServicoDTO());
		grupoDto.setTipificacoesServicoDto(tipificacoesServicoDto);
		
		grupoDto.setInstituicaoDoacaoDto(ContextoInstituicao.construirInstitucaoDTO());
		List<LoteRecursoDTO> loteRecursosDto = new ArrayList<>();
		loteRecursosDto.add(ContextoLoteRecurso.fabricarComTodosOsDadosDTO());
		grupoDto.setDoacaoRecursoDto(loteRecursosDto);
		
		return grupoDto;
	}

	public static GrupoDTO construirGrupoDTOSemAtendimentos() {
		GrupoDTO grupoDto = construirGrupoDTO();
		grupoDto.getModulosPeriodosDto().get(0)
				.setAtendimentosGrupoDto(new ArrayList<AtendimentoGrupoDTO>());
		return grupoDto;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.DescricaoTipoAtendimentoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.GrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.LoteRecursoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ModuloPeriodoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.NomeGrupoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.TipificacaoServicoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.atendimento.AtendimentoGrupo;
import br.laramara.ti.sislaraserver.dominio.doacao.LoteRecurso;
import br.laramara.ti.sislaraserver.dominio.evento.DescricaoEvento;
import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Grupo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.NomeGrupo;
import br.laramara.ti.sislaraserver.dominio.grupo.TipificacaoServico;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
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
		grupo.setDataInicioPreInscricao("01/12/2111");
		grupo.setDataTerminoPreInscricao("31/12/2111");
		grupo.setNomeCurso("Ensino e Aplicação do Sistema Braille em Nível Básico");
		grupo.setValorTotalAlmoco("30,00");
		grupo.setInvestimento("300,00");
		grupo.setPublicado(false);
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
		
		List<DescricaoEvento> descricoesEvento = new ArrayList<>();
		descricoesEvento.add(ContextoDescricaoEvento.fabricarDescricaoEmentaComTodosOsDados());
		grupo.setDescricoesEvento(descricoesEvento);
		
		grupo.setDoacaoRecurso(loteRecursos);
		
		return grupo;
	}
	
	public static GrupoDTO fabricarGrupoDTOComDescricaoTipoAtendimentoCursosValido() {
		NomeGrupoDTO nomeGrupoDto = new NomeGrupoDTO(new Long(1011), "CURS");

		GrupoDTO grupoSemAtendimentosGrupoDto = construirGrupoDTOSemAtendimentos();

		grupoSemAtendimentosGrupoDto.setNomeGrupoDto(nomeGrupoDto);

		grupoSemAtendimentosGrupoDto.setPublicado(true);
		
		List<ModuloPeriodoDTO> modulosPeriodoDtos = Arrays
				.asList(ContextoModuloPeriodo.fabricarModuloPeriodoDTOComTodosOsDadosBraille());
		grupoSemAtendimentosGrupoDto.setModulosPeriodosDto(modulosPeriodoDtos);

		List<DescricaoEventoDTO> descricoesEventoDto = Arrays
				.asList(ContextoDescricaoEvento.construirDescricaoEventoDescricaoEmentaDTO());
		grupoSemAtendimentosGrupoDto.setDescricoesEventoDto(descricoesEventoDto);

		List<TipificacaoServicoDTO> tipificacoesServicoDto = Arrays
				.asList(ContextoTipificacaoServico.construirTipificacaoServicoDTO());
		grupoSemAtendimentosGrupoDto.setTipificacoesServicoDto(tipificacoesServicoDto);

		InstituicaoDTO instituicoesDto = ContextoInstituicao.construirInstitucaoDTO();
		grupoSemAtendimentosGrupoDto.setInstituicaoDoacaoDto(instituicoesDto);

		List<LoteRecursoDTO> lotesRecursoDto = Arrays.asList(ContextoLoteRecurso.fabricarComTodosOsDadosDTO());
		grupoSemAtendimentosGrupoDto.setDoacaoRecursoDto(lotesRecursoDto);

		DescricaoTipoAtendimentoDTO descricaoTipoAtendimentoDto = ContextoDescricaoTipoAtendimento
				.construirDescricaoTipoAtendimentoDTOCursos();
		grupoSemAtendimentosGrupoDto.setDescricaoTipoAtendimentoDTO(descricaoTipoAtendimentoDto);

		return grupoSemAtendimentosGrupoDto;
	}
	
	public static GrupoDTO fabricarGrupoDTOComDescricaoTipoAtendimentoCursosInvalido() {
		GrupoDTO grupoSemAtendimentosGrupoDto = fabricarGrupoDTOComDescricaoTipoAtendimentoCursosValido();
		
		grupoSemAtendimentosGrupoDto.setNomeCurso("");
		grupoSemAtendimentosGrupoDto.setDataInicioPreInscricao("");
		grupoSemAtendimentosGrupoDto.setDataTerminoPreInscricao("");
		grupoSemAtendimentosGrupoDto.setInvestimento("");
		grupoSemAtendimentosGrupoDto.setValorTotalAlmoco("VALOR_INVALIDO");
		grupoSemAtendimentosGrupoDto.setDescricoesEventoDto(Arrays.asList());		
		
		return grupoSemAtendimentosGrupoDto;
	}
	
	public static Grupo fabricarGrupoComDescricaoTipoAtendimentoCursosInvalido() {
		Grupo grupoSemAtendimentosGrupo = fabricarGrupoComDescricaoTipoAtendimentoCursosValido();
		
		grupoSemAtendimentosGrupo.setNomeCurso("");
		grupoSemAtendimentosGrupo.setDataInicioPreInscricao("");
		grupoSemAtendimentosGrupo.setDataTerminoPreInscricao("");
		grupoSemAtendimentosGrupo.setInvestimento("");
		grupoSemAtendimentosGrupo.setValorTotalAlmoco("VALOR_INVALIDO");
		grupoSemAtendimentosGrupo.setDescricoesEvento(Arrays.asList());
		
		return grupoSemAtendimentosGrupo;
	}
	
	public static Grupo fabricarGrupoComDescricaoTipoAtendimentoCursosValido() {
		NomeGrupo nomeGrupo = new NomeGrupo(new Long(1011), "CURS");

		Grupo grupoSemAtendimentosGrupo = fabricarGrupoComTodosOsDadosSemAtendimentos();

		grupoSemAtendimentosGrupo.setId(16666L);
		
		grupoSemAtendimentosGrupo.setNomeGrupo(nomeGrupo);
		
		grupoSemAtendimentosGrupo.setPublicado(true);

		List<ModuloPeriodo> modulosPeriodoDtos = Arrays
				.asList(ContextoModuloPeriodo.fabricarModuloPeriodoComTodosOsDadosBraille());
		grupoSemAtendimentosGrupo.setModulosPeriodos(modulosPeriodoDtos);

		List<DescricaoEvento> descricoesEventoDto = Arrays.asList(ContextoDescricaoEvento.fabricarDescricaoEmentaComTodosOsDados());
		grupoSemAtendimentosGrupo.setDescricoesEvento(descricoesEventoDto);

		List<TipificacaoServico> tipificacoesServicoDto = Arrays
				.asList(ContextoTipificacaoServico.fabricarComTodosOsDados());
		grupoSemAtendimentosGrupo.setTipificacoesServico(tipificacoesServicoDto);

		Instituicao instituicoes = ContextoInstituicao.fabricarInstituicaoComTodosOsDados();
		grupoSemAtendimentosGrupo.setInstituicaoDoacao(instituicoes);

		List<LoteRecurso> lotesRecurso = Arrays.asList(ContextoLoteRecurso.fabricarComTodosOsDados());
		grupoSemAtendimentosGrupo.setDoacaoRecurso(lotesRecurso);

		DescricaoTipoAtendimento descricaoTipoAtendimentoDto = ContextoDescricaoTipoAtendimento.fabricarCursos();
		grupoSemAtendimentosGrupo.setDescricaoTipoAtendimento(descricaoTipoAtendimentoDto);

		return grupoSemAtendimentosGrupo;
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
		String dataInicioPreInscricao = "01/12/2111";
		String dataTerminoPreInscricao = "31/12/2111";
		String nomeCurso = "Ensino e Aplicação do Sistema Braille em Nível Básico";
		String valorTotalAlmoco = "30,00";
		String investimento = "300,00";
		boolean publicado = false;
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
		grupoDto.setDataInicioPreInscricao(dataInicioPreInscricao);
		grupoDto.setDataTerminoPreInscricao(dataTerminoPreInscricao);
		grupoDto.setNomeCurso(nomeCurso);
		grupoDto.setValorTotalAlmoco(valorTotalAlmoco);
		grupoDto.setInvestimento(investimento);
		grupoDto.setPublicado(publicado);		
		grupoDto.setAtivo(true);
		
		List<ModuloPeriodoDTO> modulosPeriodos = new ArrayList<>();
		modulosPeriodos.add(ContextoModuloPeriodo.fabricarModuloPeriodoDTO());
		grupoDto.setModulosPeriodosDto(modulosPeriodos);
		
		List<DescricaoEventoDTO> descricoesEventoDto = new ArrayList<>();
		descricoesEventoDto.add(ContextoDescricaoEvento
				.construirDescricaoEventoDescricaoEmentaDTO());
		grupoDto.setDescricoesEventoDto(descricoesEventoDto);
		
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

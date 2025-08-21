package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.PrestacaoContaDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.RecursoDisponivelDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaraserver.dominio.SimNao;
import br.laramara.ti.sislaraserver.dominio.doacao.PrestacaoConta;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;
import br.laramara.ti.sislaraserver.dominio.doacao.RecursoDisponivel;
import br.laramara.ti.sislaraserver.dominio.usuario.Setor;

public class ContextoProjeto {
	public static Projeto fabricarProjetoComTodosOsDados() {
		Projeto projeto = new Projeto();
		projeto.setId(new Long(14444));
		projeto.setAtivo(true);
		projeto.setDataInicialVigencia("31/12/2012");
		projeto.setNome("Projeto X");
		projeto.setEditalInvestimento("EDITAL 93248");
		projeto.setOrgaoParceiroFinanciador("ORGAO A");
		projeto.setLei("LEI A");
		projeto.setIncentivado(SimNao.SIM);
		projeto.setClassificacao(Setor.DI);
		projeto.setObjetivoGeral("Grande texto do objetivo");
		projeto.setPublicoAlvo("Grande texto do publico alvo");
		projeto.setProfissionalAdministrativoResponsavel(ContextoProfissional.fabricarComTodosOsDados());
		projeto.setResponsaveisTecnicos(Arrays.asList(ContextoResponsavelTecnico.fabricarProjetoComTodosOsDados()));
		projeto.setPrestacaoConta(PrestacaoConta.MENSAL);
		projeto.setPatrocinios(Arrays.asList(ContextoPatrocinio.criarPatrocinio()));
		projeto.setNumeroTermoFomentoParceria("Fomento 4387");
		projeto.setArquivos(ContextoArquivo.obterArquivo());
		projeto.setDuracao("6");
		projeto.setAditamento("2");
		projeto.setValorOutros("1000,00");
		projeto.setValorProdutos("5100,00");
		projeto.setIdadeMinima("10");
		projeto.setIdadeMaxima("30");
		List<RecursoDisponivel> recursosDisponiveis = new ArrayList<>();
		recursosDisponiveis.add(ContextoRecursoDisponivel.fabricarComTodosOsDados());
		projeto.setRecursosDisponiveis(recursosDisponiveis);
		return projeto;
	}

	public static ProjetoDTO construirProjetoDTO() {
		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(new Long(12222));
		projetoDto.setAtivo(false);
		projetoDto.setDataInicialVigencia("31/12/2012");
		projetoDto.setDataFinalVigencia("31/12/2012");
		projetoDto.setNome("Projeto X");
		projetoDto.setEditalInvestimento("EDITAL 93248");
		projetoDto.setOrgaoParceiroFinanciador("ORGAO A");
		projetoDto.setLei("LEI A");
		projetoDto.setIncentivadoDto(new SimNaoDTO(SimNao.SIM.toString()));
		projetoDto.setClassificacaoDto(new SetorDTO(Setor.DI.toString()));
		projetoDto.setObjetivoGeral("Grande texto do objetivo");
		projetoDto.setPublicoAlvo("Grande texto do publico alvo");
		projetoDto.setProfissionalAdministrativoResponsavelDto(ContextoProfissional.construirProfissionalDTO());
		projetoDto.setResponsaveisTecnicosDto(Arrays.asList(ContextoResponsavelTecnico.construirResponsavelTecnicoDTO()));
		projetoDto.setPrestacaoContaDto(new PrestacaoContaDTO("MENSAL"));
		projetoDto.setPatrociniosDto(Arrays.asList(ContextoPatrocinio.criarPatrocinioDto()));
		projetoDto.setNumeroTermoFomentoParceria("Fomento 4387");
		projetoDto.setArquivos(ContextoArquivo.obterArquivoDtoDocumentoAVFUN());
		projetoDto.setDuracao("6");
		projetoDto.setAditamento("3");
		projetoDto.setValorTotal("12222,22");
		projetoDto.setValorProdutos("26000,00");
		projetoDto.setValorOutros("343,44");
		projetoDto.setIdadeMinima("10");
		projetoDto.setIdadeMaxima("30");
		List<RecursoDisponivelDTO> recursoDisponivelDto = new ArrayList<>();
		recursoDisponivelDto.add(ContextoRecursoDisponivel.fabricarComTodosOsDadosDTO());
		projetoDto.setRecursoDisponivelDto(recursoDisponivelDto);
		return projetoDto;
	}
}

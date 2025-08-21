package br.laramara.ti.sislaracommons.dtos.doacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.ArquivoDTO;
import br.laramara.ti.sislaracommons.dtos.SimNaoDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.RecursoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.SetorDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesProjetoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void projetodto_foi_construida_com_sucesso() {

		Long id = new Long(1222);
		String nome = "Projeto Alcoa Teste";
		String editalInvestimento = "Edital A";
		String orgaoFinanciador = "Orgao A";
		String lei = "Lei A";
		String objetivoGeral = "Objetivo geral";
		String publicoAlvo = "Publico alvo";
		String numeroTermoFomentoParceria = "3443";
		String valor = "35000,00";
		String data = "32/12/2012";
		boolean ativo = true;
		SimNaoDTO simNaoDto = new SimNaoDTO("SIM");
		SetorDTO setorDto = new SetorDTO("CTO");
		ProfissionalDTO profissionalDTO = new ProfissionalDTO(new Long(1), "PAULO", "123");
		PrestacaoContaDTO prestacaoContaDTO = new PrestacaoContaDTO("MENSAL");
		String meses = "12";
		List<ArquivoDTO> arquivos = Arrays.asList(new ArquivoDTO(), new ArquivoDTO());

		RecursoDisponivelDTO recursoDisponivelDto = new RecursoDisponivelDTO();
		recursoDisponivelDto.setId(id);
		recursoDisponivelDto.setRecursoDto(new RecursoDTO(id, "Bengala", false, true, "150,00", Arrays.asList()));
		recursoDisponivelDto.setValorUnitario("150,00");
		recursoDisponivelDto.setQuantidade("10");
		
		List<RecursoDisponivelDTO> recursosDisponiveisDto = new ArrayList<>();
		recursosDisponiveisDto.add(recursoDisponivelDto);

		List<PatrocinioDTO> patrociniosDtos = Arrays.asList(new PatrocinioDTO(), new PatrocinioDTO());

		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(id);
		projetoDto.setNome(nome);
		projetoDto.setEditalInvestimento(editalInvestimento);
		projetoDto.setOrgaoParceiroFinanciador(orgaoFinanciador);
		projetoDto.setLei(lei);
		projetoDto.setIncentivadoDto(simNaoDto);
		projetoDto.setClassificacaoDto(setorDto);
		projetoDto.setObjetivoGeral(objetivoGeral);
		projetoDto.setPublicoAlvo(publicoAlvo);
		projetoDto.setProfissionalAdministrativoResponsavelDto(profissionalDTO);
		projetoDto.setPrestacaoContaDto(prestacaoContaDTO);
		projetoDto.setNumeroTermoFomentoParceria(numeroTermoFomentoParceria);
		projetoDto.setDuracao(meses);
		projetoDto.setAditamento(meses);
		projetoDto.setValorTotal(valor);
		projetoDto.setValorProdutos(valor);
		projetoDto.setValorOutros(valor);
		projetoDto.setRecursoDisponivelDto(recursosDisponiveisDto);
		projetoDto.setDataInicialVigencia(data);
		projetoDto.setDataFinalVigencia(data);
		projetoDto.setAtivo(ativo);
		projetoDto.setSomaTotalProdutos(valor);
		projetoDto.setArquivos(arquivos);
		projetoDto.setPatrociniosDto(patrociniosDtos);

		Assert.assertEquals(projetoDto.getId(), id);
		Assert.assertEquals(projetoDto.getNome(), nome);
		Assert.assertEquals(projetoDto.getEditalInvestimento(), editalInvestimento);
		Assert.assertEquals(projetoDto.getOrgaoParceiroFinanciador(), orgaoFinanciador);
		Assert.assertEquals(projetoDto.getLei(), lei);
		Assert.assertEquals(projetoDto.getIncentivadoDto(), simNaoDto);
		Assert.assertEquals(projetoDto.getClassificacaoDto(), setorDto);
		Assert.assertEquals(projetoDto.getObjetivoGeral(), objetivoGeral);
		Assert.assertEquals(projetoDto.getPublicoAlvo(), publicoAlvo);
		Assert.assertEquals(projetoDto.getProfissionalAdministrativoResponsavelDto(), profissionalDTO);
		Assert.assertEquals(projetoDto.getPrestacaoContaDto(), prestacaoContaDTO);
		Assert.assertEquals(projetoDto.getNumeroTermoFomentoParceria(), numeroTermoFomentoParceria);
		Assert.assertEquals(projetoDto.getDuracao(), meses);
		Assert.assertEquals(projetoDto.getAditamento(), meses);
		Assert.assertEquals(projetoDto.getValorTotal(), valor);
		Assert.assertEquals(projetoDto.getValorProdutos(), valor);
		Assert.assertEquals(projetoDto.getValorOutros(), valor);
		Assert.assertEquals(projetoDto.getDataInicialVigencia(), data);
		Assert.assertEquals(projetoDto.getDataFinalVigencia(), data);
		Assert.assertEquals(projetoDto.getRecursoDisponivelDto().size(), recursosDisponiveisDto.size());
		Assert.assertEquals(projetoDto.isAtivo(), ativo);
		Assert.assertEquals(projetoDto.getSomaTotalProdutos(), valor);
		Assert.assertEquals(projetoDto.getArquivos().size(), arquivos.size());
		Assert.assertEquals(projetoDto.getPatrociniosDto().size(), patrociniosDtos.size());
	}
}
package br.laramara.ti.sislaracommons.dtos.evento;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesDetalheCursoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void detalhecursodto_foi_construido_com_sucesso() {
		Long idGrupo = new Long(12222);
		String cargaHoraria = "255:00";
		String dataCurso = "29/12/2017 até 31/12/2017";
		String investimento = "300,00";
		String nomeCurso = "Ensino e Aplicação do Sistema Braille em Nível Básico";
		String numeroVagas = "1222";
		String periodoPreInscricoes = "29/11/2017 até 30/11/2017";
		int quantidadeDescricoesEventoDto = 2;
		String valorTotalAlmoco = "100.00";

		List<DescricaoEventoDTO> descricoesEventoDto = new ArrayList<>();

		DescricaoEventoDTO descricaoEmenta = new DescricaoEventoDTO();

		descricaoEmenta.setTipoDescricaoEvento(new TipoDescricaoEventoDTO("Descrição / Ementa"));
		descricaoEmenta.setConteudo("Este curso visa blablabla");

		DescricaoEventoDTO publicoAlvo = new DescricaoEventoDTO();

		publicoAlvo.setTipoDescricaoEvento(new TipoDescricaoEventoDTO("Público Alvo"));
		publicoAlvo.setConteudo("Educadores\nEstudantes\nProfissionais da Saúde");

		descricoesEventoDto.add(descricaoEmenta);
		descricoesEventoDto.add(publicoAlvo);

		DetalheCursoDTO detalheCursoDTO = new DetalheCursoDTO(idGrupo, nomeCurso, periodoPreInscricoes, dataCurso,
				cargaHoraria, numeroVagas, investimento, descricoesEventoDto, valorTotalAlmoco);

		Assert.assertFalse(detalheCursoDTO.toString().isEmpty());
		Assert.assertEquals(detalheCursoDTO.getIdGrupo(), idGrupo);
		Assert.assertEquals(detalheCursoDTO.getCargaHoraria(), cargaHoraria);
		Assert.assertEquals(detalheCursoDTO.getDataCurso(), dataCurso);
		Assert.assertEquals(detalheCursoDTO.getDescricoesEventoDto().size(), quantidadeDescricoesEventoDto);
		Assert.assertEquals(detalheCursoDTO.getInvestimento(), investimento);
		Assert.assertEquals(detalheCursoDTO.getNomeCurso(), nomeCurso);
		Assert.assertEquals(detalheCursoDTO.getNumeroVagas(), numeroVagas);
		Assert.assertEquals(detalheCursoDTO.getPeriodoPreInscricoes(), periodoPreInscricoes);
		Assert.assertEquals(detalheCursoDTO.getValorTotalAlmoco(), valorTotalAlmoco);
	}

}

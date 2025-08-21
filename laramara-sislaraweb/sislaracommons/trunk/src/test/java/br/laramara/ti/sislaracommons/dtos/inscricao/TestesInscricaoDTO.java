package br.laramara.ti.sislaracommons.dtos.inscricao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.TipoDescricaoEventoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesInscricaoDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void testes_inscricaodto_foi_construido_com_sucesso() {
		String nomeParaCracha = "carlos kafka";
		String observacoes = "obs";
		String areaFormacao = "Tecnologia da Informação";
		String localTrabalho = "Laramara";
		String cargoOuFuncao = "Analista de Sistemas";
		boolean usuarioExternoPossuiCadeiraDeRodas = true;
		boolean usuarioExternoPossuiCaoGuia = true;
		Long idGrupo = new Long(16666L);
		String cargaHoraria = "255:00";
		String dataCurso = "29/12/2017 até 31/12/2017";
		String investimento = "300,00";
		String nomeCurso = "Ensino e Aplicação do Sistema Braille em Nível Básico";
		String numeroVagas = "1222";
		String periodoPreInscricoes = "29/11/2017 até 30/11/2017";
		String valorTotalAlmoco = "100.00";
		boolean valorTotalAlmocoIncluso = true;

		List<DescricaoEventoDTO> descricoesEventoDto = new ArrayList<>();

		DescricaoEventoDTO descricaoEmenta = new DescricaoEventoDTO();

		descricaoEmenta.setTipoDescricaoEvento(new TipoDescricaoEventoDTO("Descrição / Ementa"));
		descricaoEmenta.setConteudo("Este curso visa blablabla");

		DescricaoEventoDTO publicoAlvo = new DescricaoEventoDTO();

		publicoAlvo.setTipoDescricaoEvento(new TipoDescricaoEventoDTO("Público Alvo"));
		publicoAlvo.setConteudo("Educadores\nEstudantes\nProfissionais da Saúde");

		descricoesEventoDto.add(descricaoEmenta);
		descricoesEventoDto.add(publicoAlvo);

		DetalheCursoDTO detalheCursoDto = new DetalheCursoDTO(idGrupo, nomeCurso, periodoPreInscricoes, dataCurso,
				cargaHoraria, numeroVagas, investimento, descricoesEventoDto, valorTotalAlmoco);
		
		EnderecoDTO enderecoDto = new EnderecoDTO();
		
		enderecoDto.setId(4L);
		enderecoDto.setCep("99922222");
		enderecoDto.setEndereco("Rua do Paraiso");
		enderecoDto.setNumero("333");
		enderecoDto.setComplemento("AP22");
		enderecoDto.setBairro("Santa Cecilia");
		enderecoDto.setMunicipioDto(new MunicipioDTO(1L, "São Paulo", new UfDTO("SP")));
		enderecoDto.setUfDto(new UfDTO("SP"));
		
		InscricaoDTO inscricaoDto = new InscricaoDTO();
		
		inscricaoDto.setEnderecoDto(enderecoDto);
		inscricaoDto.setNomeParaCracha(nomeParaCracha);
		inscricaoDto.setObservacoes(observacoes);
		inscricaoDto.setAreaFormacao(areaFormacao);
		inscricaoDto.setLocalTrabalho(localTrabalho);
		inscricaoDto.setCargoOuFuncao(cargoOuFuncao);
		inscricaoDto.setUsuarioExternoPossuiCadeiraDeRodas(usuarioExternoPossuiCadeiraDeRodas);
		inscricaoDto.setUsuarioExternoPossuiCaoGuia(usuarioExternoPossuiCaoGuia);
		inscricaoDto.setDetalheCursoDto(detalheCursoDto);
		inscricaoDto.setValorTotalAlmocoIncluso(valorTotalAlmocoIncluso);
		
		Assert.assertNull(inscricaoDto.getId());
		Assert.assertNotNull(inscricaoDto.getEnderecoDto());
		Assert.assertEquals(inscricaoDto.getNomeParaCracha(), nomeParaCracha);
		Assert.assertEquals(inscricaoDto.getObservacoes(), observacoes);
		Assert.assertEquals(inscricaoDto.getAreaFormacao(), areaFormacao);
		Assert.assertEquals(inscricaoDto.getLocalTrabalho(), localTrabalho);
		Assert.assertEquals(inscricaoDto.getCargoOuFuncao(), cargoOuFuncao);
		Assert.assertEquals(inscricaoDto.isUsuarioExternoPossuiCadeiraDeRodas(), usuarioExternoPossuiCadeiraDeRodas);
		Assert.assertEquals(inscricaoDto.isUsuarioExternoPossuiCaoGuia(), usuarioExternoPossuiCaoGuia);
		Assert.assertEquals(inscricaoDto.isValorTotalAlmocoIncluso(), valorTotalAlmocoIncluso);
		Assert.assertNotNull(inscricaoDto.getDetalheCursoDto());
	}
}

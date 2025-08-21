package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.TestesIntegracaoAbstrato;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;

public class TestesFabricaInformacaoEducacional extends TestesIntegracaoAbstrato{
	
	public TestesFabricaInformacaoEducacional() {
		super("DadosTestesFabricaInformacaoEducacional.xml");
	}
	
	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_informacao_educacional_converte_objeto_de_dto_para_dominio() {
		InformacaoEducacionalDTO informacaoEducacionalDto = ContextoInformacaoEducacional
				.construirInformacaoEducacionalDTO();
		informacaoEducacionalDto.setId(new Long(88888));
		InformacaoEducacional informacaoEducacionalConvertido = new FabricaInformacaoEducacional()
				.converterParaDominio(informacaoEducacionalDto);

		Assert.assertEquals(informacaoEducacionalConvertido.getInstituicao()
				.getId(), informacaoEducacionalDto.getInstituicaoDto().getId());
		Assert.assertEquals(informacaoEducacionalConvertido.getEscolaridade()
				.getDescricao(), informacaoEducacionalDto.getEscolaridadeDto()
				.toString());
		Assert.assertEquals(informacaoEducacionalConvertido.getSerie()
				.getDescricao(), informacaoEducacionalDto.getSerieDto().toString());
		Assert.assertEquals(
				informacaoEducacionalConvertido.getPeriodo().toString(),
				informacaoEducacionalDto.getPeriodoDto().toString());
		Assert.assertEquals(informacaoEducacionalConvertido.getSituacao()
				.toString(), informacaoEducacionalDto.getSituacaoEducacionalDto().toString());
		Assert.assertEquals(informacaoEducacionalConvertido.getNomeProfessor(),
				informacaoEducacionalDto.getNomeProfessor());
		Assert.assertEquals(informacaoEducacionalConvertido.getAreaFormacao()
				.getId(), informacaoEducacionalDto.getAreaFormacaoDto().getId());
		Assert.assertEquals(
				informacaoEducacionalConvertido.getDataReferencia(),
				informacaoEducacionalDto.getDataReferencia());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_educacional_converte_objeto_de_dominio_para_dto() {
		InformacaoEducacional informacaoEducacionalDominio = ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados();
		InformacaoEducacionalDTO informacaoEducacionalConvertido = new FabricaInformacaoEducacional()
				.converterParaDTO(informacaoEducacionalDominio);

		Assert.assertEquals(informacaoEducacionalConvertido.getInstituicaoDto()
				.getId(), informacaoEducacionalDominio.getInstituicao().getId());
		Assert.assertEquals(informacaoEducacionalConvertido.getEscolaridadeDto()
				.toString(), informacaoEducacionalDominio.getEscolaridade()
				.getDescricao());
		Assert.assertEquals(informacaoEducacionalConvertido.getSerieDto()
				.toString(), informacaoEducacionalDominio.getSerie().getDescricao());
		Assert.assertEquals(informacaoEducacionalConvertido.getPeriodoDto()
				.toString(), informacaoEducacionalDominio.getPeriodo().toString());
		Assert.assertEquals(informacaoEducacionalConvertido.getSituacaoEducacionalDto()
				.toString(), informacaoEducacionalDominio.getSituacao().toString());
		Assert.assertEquals(informacaoEducacionalConvertido.getNomeProfessor(),
				informacaoEducacionalDominio.getNomeProfessor());
		Assert.assertEquals(informacaoEducacionalConvertido
				.getAreaFormacaoDto().getId(), informacaoEducacionalDominio.getAreaFormacao()
				.getId());
		Assert.assertEquals(
				informacaoEducacionalConvertido.getDataReferencia(),
				informacaoEducacionalDominio.getDataReferencia());
	}
}

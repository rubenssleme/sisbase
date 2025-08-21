package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.trabalho.InformacaoTrabalhoCompletaDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.trabalho.InformacaoTrabalhoCompleta;

public class TestesFabricaInformacaoTrabalhoCompleta {
	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_informacao_trabalho_completa_converte_objeto_de_dominio_para_dto() {
		InformacaoTrabalhoCompleta informacaoTrabalhoCompleta = ContextoInformacaoTrabalhoCompleta
				.fabricarInformacaoTrabalhoCompletaComTodosOsDados();

		InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDTO = new FabricaInformacaoTrabalhoCompleta()
				.converterParaDTO(informacaoTrabalhoCompleta);

		Assert.assertEquals(informacaoTrabalhoCompleta.getId(),
				informacaoTrabalhoCompletaDTO.getId());
		Assert.assertEquals(informacaoTrabalhoCompleta.getDataInicial(),
				informacaoTrabalhoCompletaDTO.getDataInicialVigencia());
		Assert.assertEquals(informacaoTrabalhoCompleta.getDataFinal(),
				informacaoTrabalhoCompletaDTO.getDataFinalVigencia());
		Assert.assertEquals(informacaoTrabalhoCompleta.getCargo().getId(),
				informacaoTrabalhoCompletaDTO.getCargoDto().getId());
		Assert.assertEquals(informacaoTrabalhoCompleta.getLocalTrabalho()
				.getId(), informacaoTrabalhoCompletaDTO.getCargoDto().getId());
		Assert.assertEquals(informacaoTrabalhoCompleta.getVoluntario()
				.toString(), informacaoTrabalhoCompletaDTO.getVoluntarioDto()
				.toString());
		Assert.assertEquals(informacaoTrabalhoCompleta
				.getEncaminhadoPorLaramara().toString(),
				informacaoTrabalhoCompletaDTO.getEncaminhadoPorLaramaraDto()
						.toString());
		Assert.assertEquals(informacaoTrabalhoCompleta.getEstagiario().toString(),
				informacaoTrabalhoCompletaDTO.getEstagiarioDto().toString());
		Assert.assertEquals(informacaoTrabalhoCompleta.getObs(), informacaoTrabalhoCompletaDTO.getObs());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_informacao_trabalho_completa_converte_objeto_de_dto_para_dominio() {
		InformacaoTrabalhoCompletaDTO informacaoTrabalhoCompletaDto = ContextoInformacaoTrabalhoCompleta
				.fabricarInformacaoTrabalhoDTOCompletaComTodosOsDados();

		InformacaoTrabalhoCompleta informacaoTrabalhoCompleta = new FabricaInformacaoTrabalhoCompleta()
				.converterParaDominio(informacaoTrabalhoCompletaDto);

		Assert.assertEquals(informacaoTrabalhoCompletaDto.getId(),
				informacaoTrabalhoCompleta.getId());
		Assert.assertEquals(
				informacaoTrabalhoCompletaDto.getDataInicialVigencia(),
				informacaoTrabalhoCompleta.getDataInicial());
		Assert.assertEquals(
				informacaoTrabalhoCompletaDto.getDataFinalVigencia(),
				informacaoTrabalhoCompleta.getDataFinal());
		Assert.assertEquals(
				informacaoTrabalhoCompletaDto.getCargoDto().getId(),
				informacaoTrabalhoCompleta.getCargo().getId());
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getLocalTrabalhoDto()
				.getId(), informacaoTrabalhoCompleta.getCargo().getId());
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getVoluntarioDto()
				.toString(), informacaoTrabalhoCompleta.getVoluntario()
				.toString());
		Assert.assertEquals(informacaoTrabalhoCompletaDto
				.getEncaminhadoPorLaramaraDto().toString(),
				informacaoTrabalhoCompleta.getEncaminhadoPorLaramara()
						.toString());
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getEstagiarioDto().toString(),
				informacaoTrabalhoCompleta.getEstagiario().toString());
		Assert.assertEquals(informacaoTrabalhoCompletaDto.getObs(), informacaoTrabalhoCompleta.getObs());
	}
}

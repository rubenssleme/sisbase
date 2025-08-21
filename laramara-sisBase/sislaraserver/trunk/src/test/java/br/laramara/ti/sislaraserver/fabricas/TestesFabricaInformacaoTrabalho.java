package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.familiar.InformacaoTrabalhoDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.familiar.InformacaoTrabalho;

public class TestesFabricaInformacaoTrabalho {

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_trabalho_converte_objeto_de_dto_para_dominio() {
		Long id = new Long(34324);
		InformacaoTrabalhoDTO informacaoTrabalhoDto = ContextoInformacaoTrabalho
				.construirInformacaoTrabalhoDTO();
		informacaoTrabalhoDto.setId(id);
		InformacaoTrabalho informacaoTrabalhoConvertido = new FabricaInformacaoTrabalho()
				.converterParaDominio(informacaoTrabalhoDto);

		Assert.assertEquals(informacaoTrabalhoConvertido.getId().toString(),
				informacaoTrabalhoDto.getId().toString());
		Assert.assertEquals(informacaoTrabalhoConvertido.getFuncao(),
				informacaoTrabalhoDto.getFuncao());
		Assert.assertEquals(informacaoTrabalhoConvertido.getEmpresa(),
				informacaoTrabalhoDto.getEmpresa());
		Assert.assertEquals(informacaoTrabalhoConvertido.getCargo().getId(), informacaoTrabalhoDto.getCargoDto().getId());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_SEM_BD })
	public void fabrica_informacao_trabalho_converte_objeto_de_dominio_para_dto() {
		Long id = new Long("34324");
		InformacaoTrabalho informacaoTrabalhoDominio = ContextoInformacaoTrabalho
				.fabricarInformacaoTrabalhoComTodosOsDados();
		informacaoTrabalhoDominio.setId(id);
		InformacaoTrabalhoDTO informacaoTrabalhoDtoConvertido = new FabricaInformacaoTrabalho()
				.converterParaDTO(informacaoTrabalhoDominio);

		Assert.assertEquals(informacaoTrabalhoDtoConvertido.getId(),
				informacaoTrabalhoDominio.getId());
		Assert.assertEquals(informacaoTrabalhoDtoConvertido.getEmpresa(),
				informacaoTrabalhoDominio.getEmpresa());
		Assert.assertEquals(informacaoTrabalhoDtoConvertido.getFuncao(),
				informacaoTrabalhoDominio.getFuncao());
		Assert.assertEquals(informacaoTrabalhoDominio.getCargo().getId(), informacaoTrabalhoDominio.getCargo().getId());
	}
}

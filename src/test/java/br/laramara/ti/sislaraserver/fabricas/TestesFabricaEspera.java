package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaracommons.utilitarios.DataHoraUtils;
import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;

public class TestesFabricaEspera {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_espera_converte_objeto_de_dominio_para_dto() {
		Espera espera = ContextoEspera.fabricarEsperaComTodosOsDadosUsuario();

		EsperaDTO esperaDtoCovertido = new FabricaEspera()
				.converterParaDTO(espera);

		Assert.assertEquals(esperaDtoCovertido.getId(), espera.getId());
		Assert.assertEquals(esperaDtoCovertido.getUsuarioDto().getId(), espera
				.getUsuario().getId());
		Assert.assertEquals(esperaDtoCovertido.getDataExpectativa(),
				espera.getDataExpectativa());
		Assert.assertNotNull(esperaDtoCovertido.getDataCadastro());
		Assert.assertEquals(esperaDtoCovertido.getDescricaoTipoAtendimentoDto()
				.getId(), espera.getDescricaoTipoAtendimento().getId());
		Assert.assertEquals(esperaDtoCovertido.getModuloDto().getId(), espera
				.getModulo().getId());
		Assert.assertEquals(esperaDtoCovertido.getSetorDto().toString(), espera
				.getSetor().toString());
		Assert.assertEquals(esperaDtoCovertido.getNomeGrupoDto().toString(),
				espera.getNomeGrupo().getNome());
		Assert.assertEquals(esperaDtoCovertido.getProfissionalSolicitioDto()
				.toString(), espera.getProfissionalSolicitou().getNome());
		Assert.assertEquals(esperaDtoCovertido.getTipoEsperaDto().toString(),
				espera.getTipoEspera().toString());
		Assert.assertEquals(esperaDtoCovertido.getStatusDto().toString(),
				espera.obterStatus().toString());
		Assert.assertEquals(esperaDtoCovertido.estaCancelado(),
				espera.estaCancelado());
		Assert.assertEquals(esperaDtoCovertido.estaAguardando(),
				espera.estaAguardando());
		Assert.assertEquals(esperaDtoCovertido.estaTriando(),
				espera.estaTriando());
		Assert.assertEquals(esperaDtoCovertido.getObs(), espera.getObs());
		Assert.assertEquals(esperaDtoCovertido.getJustificativaCancelamento(),
				espera.getJustificativaCancelamento());
		Assert.assertFalse(esperaDtoCovertido.getHistorioOperacoes().isEmpty());
		Assert.assertEquals(esperaDtoCovertido.getSetoresUsuario().size(),
				espera.getUsuario().obterHistoricosSetorVigentes().size());
		Assert.assertEquals(esperaDtoCovertido.isInteresse(), espera.isInteresse());
		Assert.assertEquals(esperaDtoCovertido.isLmLigou(), espera.isLmLigou());
		Assert.assertEquals(esperaDtoCovertido.isPendencias(), espera.isPendencias());
		Assert.assertEquals(esperaDtoCovertido.getDataUltimaLigacaoInteresse(), espera.getDataUltimaLigacaoInteresse());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_espera_converte_objeto_dto_para_dominio() {
		EsperaDTO esperaDto = ContextoEspera.construirEsperaDTO();

		Espera esperaCovertido = new FabricaEspera()
				.converterParaDominio(esperaDto);

		Assert.assertEquals(esperaCovertido.getId(), esperaDto.getId());
		Assert.assertEquals(esperaCovertido.getPreCadastro().getId(), esperaDto
				.getPreCadastroDto().getId());
		Assert.assertEquals(esperaCovertido.getDataExpectativa(),
				esperaDto.getDataExpectativa());
		Assert.assertEquals(esperaCovertido.getDescricaoTipoAtendimento()
				.getId(), esperaDto.getDescricaoTipoAtendimentoDto().getId());
		Assert.assertEquals(esperaCovertido.getModulo().getId(), esperaDto
				.getModuloDto().getId());
		Assert.assertEquals(esperaCovertido.getSetor().toString(), esperaDto
				.getSetorDto().toString());
		Assert.assertEquals(esperaCovertido.getNomeGrupo().getNome(), esperaDto
				.getNomeGrupoDto().toString());
		Assert.assertEquals(esperaCovertido.getProfissionalSolicitou()
				.getNome(), esperaDto.getProfissionalSolicitioDto().toString().toUpperCase());
		Assert.assertEquals(esperaCovertido.getTipoEspera().toString(),
				esperaDto.getTipoEsperaDto().toString());
		Assert.assertEquals(esperaCovertido.getObs(), esperaDto.getObs());
		Assert.assertEquals(esperaCovertido.getJustificativaCancelamento(),
				esperaDto.getJustificativaCancelamento());
		Assert.assertEquals(esperaCovertido.isInteresse(), esperaDto.isInteresse());
		Assert.assertEquals(esperaCovertido.isLmLigou(), esperaDto.isLmLigou());
		Assert.assertEquals(esperaCovertido.isPendencias(), esperaDto.isPendencias());
		Assert.assertEquals(esperaCovertido.getDataUltimaLigacaoInteresse(),
				DataHoraUtils.formatarData(MaquinaTempo.obterInstancia().obterCalendarioAtual()));
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;

public class TestesFabricaSolicitacaoRelatorio {

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_solicitacao_relatorio_objeto_de_dominio_para_dto() {
		SolicitacaoRelatorio solicitacaoRelatorio = ContextoSolicitacaoRelatorio
				.fabricarRecursoComTodosOsDados();
		solicitacaoRelatorio
				.setContaAcessoResponsavelPorOperacaoSolicitacao(new ContaAcesso());

		SolicitacaoRelatorioDTO solicitacaoRelatorioDtoCovertido = new FabricaSolicitacaoRelatorio()
				.converterParaDTO(solicitacaoRelatorio);

		Assert.assertEquals(solicitacaoRelatorioDtoCovertido.getId(),
				solicitacaoRelatorio.getId());
		Assert.assertEquals(
				solicitacaoRelatorioDtoCovertido.getNomeSolicitante(),
				solicitacaoRelatorio.getNomeSolicitante());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido.getElaboradorDto()
				.toString(), solicitacaoRelatorio.getElaborador().toString());
		Assert.assertEquals(
				solicitacaoRelatorioDtoCovertido.getRgSolicitante(),
				solicitacaoRelatorio.getRgSolicitante());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido
				.getQuantidadeRelatoriosEmitidos(), solicitacaoRelatorio
				.getQuantidadeRelatoriosEmitidos());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido
				.getQuantidadeRelatoriosEntregues(), solicitacaoRelatorio
				.getQuantidadeRelatoriosEntregues());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido.getUsuarioDto()
				.getId(), solicitacaoRelatorio.getUsuario().getId());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido.getObs(),
				solicitacaoRelatorio.getObs());
		Assert.assertTrue(solicitacaoRelatorioDtoCovertido.estaSolicitado());
		Assert.assertFalse(solicitacaoRelatorioDtoCovertido
				.estaEncaminhadoPelaRecepcao());
		Assert.assertFalse(solicitacaoRelatorioDtoCovertido
				.estaEmitidoPorProfissional());
		Assert.assertFalse(solicitacaoRelatorioDtoCovertido
				.estaEntregueParaRecepcao());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido.getStatusAtual()
				.toString(), solicitacaoRelatorio.obterStatusAtual().toString());
		Assert.assertFalse(solicitacaoRelatorioDtoCovertido.estaCancelado());
		Assert.assertEquals(solicitacaoRelatorioDtoCovertido
				.getFinalidadesRelatoriosDto().size(), solicitacaoRelatorio
				.getFinalidadeRelatorios().size());
		Assert.assertEquals(
				solicitacaoRelatorioDtoCovertido.isEnviarPeloCorreio(),
				solicitacaoRelatorio.isEnviarPeloCorreio());
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void fabrica_solicitacao_relatorio_converte_objeto_dto_para_dominio() {

		SolicitacaoRelatorioDTO solicitacaoRelatorioDto = ContextoSolicitacaoRelatorio
				.construirSolicitacaoRelatorioDTO();

		SolicitacaoRelatorio solicitacaoRelatorio = new FabricaSolicitacaoRelatorio()
				.converterParaDominio(solicitacaoRelatorioDto);

		Assert.assertEquals(solicitacaoRelatorio.getId(),
				solicitacaoRelatorioDto.getId());
		Assert.assertEquals(solicitacaoRelatorio.getNomeSolicitante(),
				solicitacaoRelatorioDto.getNomeSolicitante());
		Assert.assertEquals(solicitacaoRelatorio.getRgSolicitante(),
				solicitacaoRelatorioDto.getRgSolicitante());
		Assert.assertEquals(solicitacaoRelatorio.getElaborador().toString(),
				solicitacaoRelatorioDto.getElaboradorDto().toString());
		Assert.assertEquals(
				solicitacaoRelatorio.getQuantidadeRelatoriosEmitidos(),
				solicitacaoRelatorioDto.getQuantidadeRelatoriosEmitidos());
		Assert.assertEquals(
				solicitacaoRelatorio.getQuantidadeRelatoriosEntregues(),
				solicitacaoRelatorioDto.getQuantidadeRelatoriosEntregues());
		Assert.assertEquals(solicitacaoRelatorio.getUsuario().getId(),
				solicitacaoRelatorioDto.getUsuarioDto().getId());
		Assert.assertEquals(solicitacaoRelatorio.getObs(),
				solicitacaoRelatorioDto.getObs());
		Assert.assertEquals(solicitacaoRelatorio.getFinalidadeRelatorios()
				.size(), solicitacaoRelatorioDto.getFinalidadesRelatoriosDto()
				.size());
		Assert.assertEquals(solicitacaoRelatorio.isEnviarPeloCorreio(),
				solicitacaoRelatorioDto.isEnviarPeloCorreio());

	}
}

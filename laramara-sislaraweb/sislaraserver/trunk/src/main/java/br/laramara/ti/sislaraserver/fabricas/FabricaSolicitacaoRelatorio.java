package br.laramara.ti.sislaraserver.fabricas;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;

@Component
public class FabricaSolicitacaoRelatorio extends
		FabricaRecursiva<SolicitacaoRelatorioDTO, SolicitacaoRelatorio> {

	@Override
	public SolicitacaoRelatorio converterParaDominio(
			SolicitacaoRelatorioDTO solicitacaoRelatorioDto,
			SolicitacaoRelatorio solicitacaoRelatorio) {
		if (solicitacaoRelatorioDto != null) {
			if (solicitacaoRelatorio == null) {
				solicitacaoRelatorio = new SolicitacaoRelatorio();
			}
			solicitacaoRelatorio.setId(solicitacaoRelatorioDto.getId());
			solicitacaoRelatorio.setNomeSolicitante(solicitacaoRelatorioDto
					.getNomeSolicitante());
			solicitacaoRelatorio.setRgSolicitante(solicitacaoRelatorioDto
					.getRgSolicitante());
			solicitacaoRelatorio.setElaborador(new FabricaElaborador()
					.converterParaDominio(solicitacaoRelatorioDto
							.getElaboradorDto()));
			solicitacaoRelatorio
					.setQuantidadeRelatoriosEmitidos(solicitacaoRelatorioDto
							.getQuantidadeRelatoriosEmitidos());
			solicitacaoRelatorio
					.setQuantidadeRelatoriosEntregues(solicitacaoRelatorioDto
							.getQuantidadeRelatoriosEntregues());
			solicitacaoRelatorio.setUsuario(new FabricaUsuario()
					.converterParaDominio(solicitacaoRelatorioDto
							.getUsuarioDto()));
			solicitacaoRelatorio
					.setStatusDaEntrega(new FabricaStatusSolicitacaoRelatorio()
							.converterParaDominio(solicitacaoRelatorioDto
									.getStatusDaEntrega()));
			solicitacaoRelatorio.setObs(solicitacaoRelatorioDto.getObs());
			solicitacaoRelatorio.setFinalidadesRelatorios(new FabricaFinalidadeRelatorio()
					.converterParaDominio(solicitacaoRelatorioDto
							.getFinalidadesRelatoriosDto()));
			solicitacaoRelatorio.setEnviarPeloCorreio(solicitacaoRelatorioDto
					.isEnviarPeloCorreio());
		}
		return solicitacaoRelatorio;
	}

	@Override
	public SolicitacaoRelatorio obterNovo() {
		return new SolicitacaoRelatorio();
	}

	@Override
	public SolicitacaoRelatorioDTO converterParaDTO(
			SolicitacaoRelatorio solicitacaoRelatorioDominio) {
		SolicitacaoRelatorioDTO solicitacaoRelatorioDto = new SolicitacaoRelatorioDTO();
		solicitacaoRelatorioDto.setId(solicitacaoRelatorioDominio.getId());
		solicitacaoRelatorioDto.setNomeSolicitante(solicitacaoRelatorioDominio
				.getNomeSolicitante());
		solicitacaoRelatorioDto.setRgSolicitante(solicitacaoRelatorioDominio
				.getRgSolicitante());
		solicitacaoRelatorioDto
				.setQuantidadeRelatoriosEmitidos(solicitacaoRelatorioDominio
						.getQuantidadeRelatoriosEmitidos());
		solicitacaoRelatorioDto
				.setQuantidadeRelatoriosEntregues(solicitacaoRelatorioDominio
						.getQuantidadeRelatoriosEntregues());
		solicitacaoRelatorioDto.setUsuarioDto(new FabricaUsuario()
				.converterParaDTO(solicitacaoRelatorioDominio.getUsuario()));
		solicitacaoRelatorioDto.setElaboradorDto(new FabricaElaborador()
				.converterParaDTO(solicitacaoRelatorioDominio.getElaborador()));
		solicitacaoRelatorioDto.setObs(solicitacaoRelatorioDominio.getObs());
		solicitacaoRelatorioDto
				.setResponsavelPorOperacao(solicitacaoRelatorioDominio
						.getHistoricoOperacoes());
		solicitacaoRelatorioDto.setEstaSolicitado(solicitacaoRelatorioDominio
				.estaSolicitado());
		solicitacaoRelatorioDto
				.setEstaEncaminhadoPelaRecepcao(solicitacaoRelatorioDominio
						.estaEncaminhadoPelaRecepcao());
		solicitacaoRelatorioDto
				.setEstaEntregueParaRecepcao(solicitacaoRelatorioDominio
						.estaEntregueParaRecepcao());
		solicitacaoRelatorioDto.setEstaCancelado(solicitacaoRelatorioDominio
				.estaCancelado());
		solicitacaoRelatorioDto
				.setEstaEmitidoPorProfissional(solicitacaoRelatorioDominio
						.estaEmitidoPeloProfissional());
		solicitacaoRelatorioDto
				.setStatusAtual(new FabricaStatusSolicitacaoRelatorio()
						.converterParaDTO(solicitacaoRelatorioDominio
								.obterStatusAtual()));
		solicitacaoRelatorioDto
				.setFinalidadesRelatoriosDto(new FabricaFinalidadeRelatorio()
						.converterParaDTO(solicitacaoRelatorioDominio
								.getFinalidadeRelatorios()));
		solicitacaoRelatorioDto.setTiposRelatorios(solicitacaoRelatorioDominio
				.obterFinalidadesRelatorios());
		solicitacaoRelatorioDto
				.setEnviarPeloCorreio(solicitacaoRelatorioDominio
						.isEnviarPeloCorreio());
		return solicitacaoRelatorioDto;
	}
}

package br.laramara.ti.sislaracommons.dtos.solicitacao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.usuario.UsuarioDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;

public class TestesSolicitacaoRelatorioDTO {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void solicitacao_relatorio_dto_foi_construida_pelo_com_sucesso() {

		Long id = new Long(12222);
		String nomeSolicitante = "Paulo";
		String rgSolicitante = "1234";
		String quantidadeRelatoriosEmitidos = "12";
		String quantidadeRelatoriosEntregues = "34";
		String responsavelPorOperacao = "JOAP";
		boolean enviarPeloCorreio = true;
		UsuarioDTO usuarioDto = new UsuarioDTO(new Long(12));

		
		ElaboradorDTO elaboradorDto = new ElaboradorDTO("OFTALMOLOGIA");

		List<FinalidadeRelatorioDTO> tiposRelatoriosDto = new ArrayList<>();
		tiposRelatoriosDto.add(new FinalidadeRelatorioDTO("INSS"));

		StatusSolicitacaoRelatorioDTO statusSolicitacaoRelatorioDTO = new StatusSolicitacaoRelatorioDTO(
				"SOLICITADO");

		SolicitacaoRelatorioDTO solicitacaoRelatorioDTO = new SolicitacaoRelatorioDTO();
		solicitacaoRelatorioDTO.setId(id);
		solicitacaoRelatorioDTO.setNomeSolicitante(nomeSolicitante);
		solicitacaoRelatorioDTO.setRgSolicitante(rgSolicitante);
		solicitacaoRelatorioDTO
				.setQuantidadeRelatoriosEmitidos(quantidadeRelatoriosEmitidos);
		solicitacaoRelatorioDTO
				.setQuantidadeRelatoriosEntregues(quantidadeRelatoriosEntregues);
		solicitacaoRelatorioDTO
				.setResponsavelPorOperacao(responsavelPorOperacao);
		solicitacaoRelatorioDTO
				.setStatusDaEntrega(statusSolicitacaoRelatorioDTO);
		solicitacaoRelatorioDTO.setUsuarioDto(usuarioDto);
		solicitacaoRelatorioDTO.setStatusAtual(statusSolicitacaoRelatorioDTO);
		solicitacaoRelatorioDTO.setFinalidadesRelatoriosDto(tiposRelatoriosDto);
		solicitacaoRelatorioDTO.setElaboradorDto(elaboradorDto);
		solicitacaoRelatorioDTO.setEnviarPeloCorreio(enviarPeloCorreio);

		Assert.assertEquals(solicitacaoRelatorioDTO.getId(), id);
		Assert.assertEquals(solicitacaoRelatorioDTO.getNomeSolicitante(),
				nomeSolicitante);
		Assert.assertEquals(solicitacaoRelatorioDTO.getRgSolicitante(),
				rgSolicitante);
		Assert.assertEquals(
				solicitacaoRelatorioDTO.getQuantidadeRelatoriosEmitidos(),
				quantidadeRelatoriosEmitidos);
		Assert.assertEquals(
				solicitacaoRelatorioDTO.getQuantidadeRelatoriosEntregues(),
				quantidadeRelatoriosEntregues);
		Assert.assertEquals(
				solicitacaoRelatorioDTO.getResponsavelPorOperacao(),
				responsavelPorOperacao);
		Assert.assertEquals(solicitacaoRelatorioDTO.getStatusDaEntrega(),
				statusSolicitacaoRelatorioDTO);
		Assert.assertEquals(solicitacaoRelatorioDTO.getUsuarioDto().getId(),
				usuarioDto.getId());
		Assert.assertEquals(
				solicitacaoRelatorioDTO.getStatusAtual().toString(),
				statusSolicitacaoRelatorioDTO.toString());
		Assert.assertEquals(solicitacaoRelatorioDTO.getFinalidadesRelatoriosDto()
				.size(), tiposRelatoriosDto.size());
		Assert.assertEquals(solicitacaoRelatorioDTO.getElaboradorDto()
				.toString(), elaboradorDto.toString());
		Assert.assertEquals(solicitacaoRelatorioDTO.isEnviarPeloCorreio(),
				enviarPeloCorreio);
	}
}

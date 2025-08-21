package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.solicitacao.ElaboradorDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.FinalidadeRelatorioDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.SolicitacaoRelatorioDTO;
import br.laramara.ti.sislaraserver.dominio.solicitacao.Elaborador;
import br.laramara.ti.sislaraserver.dominio.solicitacao.FinalidadeRelatorio;
import br.laramara.ti.sislaraserver.dominio.solicitacao.SolicitacaoRelatorio;

public class ContextoSolicitacaoRelatorio {
	public static SolicitacaoRelatorioDTO construirSolicitacaoRelatorioDTO() {
		List<FinalidadeRelatorioDTO> tiposRelatoriosDto = new ArrayList<>();
		tiposRelatoriosDto.add(new FinalidadeRelatorioDTO("INSS"));
		SolicitacaoRelatorioDTO solicitacaoRelatorioDTO = new SolicitacaoRelatorioDTO();
		solicitacaoRelatorioDTO.setId(new Long(124));
		solicitacaoRelatorioDTO.setNomeSolicitante("Paulo");
		solicitacaoRelatorioDTO.setRgSolicitante("1245");
		solicitacaoRelatorioDTO.setQuantidadeRelatoriosEmitidos("12");
		solicitacaoRelatorioDTO.setQuantidadeRelatoriosEntregues("21");
		solicitacaoRelatorioDTO.setUsuarioDto(ContextoUsuario
				.construirUsuarioDTOComIdentificacao());
		solicitacaoRelatorioDTO.setObs("Grande observação");
		solicitacaoRelatorioDTO.setElaboradorDto(new ElaboradorDTO("OFTALMOLOGIA"));
		solicitacaoRelatorioDTO.setFinalidadesRelatoriosDto(tiposRelatoriosDto);
		solicitacaoRelatorioDTO.setEnviarPeloCorreio(true);
		return solicitacaoRelatorioDTO;
	}

	public static SolicitacaoRelatorio fabricarRecursoComTodosOsDados() {
		List<FinalidadeRelatorio> tiposRelatorios = new ArrayList<>();
		tiposRelatorios.add(FinalidadeRelatorio.INSS);
		SolicitacaoRelatorio solicitacaoRelatorio = new SolicitacaoRelatorio();
		solicitacaoRelatorio.setId(new Long(124));
		solicitacaoRelatorio.setNomeSolicitante("Paulo");
		solicitacaoRelatorio.setRgSolicitante("1245");
		solicitacaoRelatorio.setQuantidadeRelatoriosEmitidos("12");
		solicitacaoRelatorio.setQuantidadeRelatoriosEntregues("21");
		solicitacaoRelatorio.setUsuario(ContextoUsuario
				.fabricaUsuarioComTodosOsDadosEProntuario());
		solicitacaoRelatorio.setObs("Grande observação");
		solicitacaoRelatorio.setElaborador(Elaborador.ORTOPTICA);
		solicitacaoRelatorio.setFinalidadesRelatorios(tiposRelatorios);
		solicitacaoRelatorio.setEnviarPeloCorreio(true);
		return solicitacaoRelatorio;
	}
}

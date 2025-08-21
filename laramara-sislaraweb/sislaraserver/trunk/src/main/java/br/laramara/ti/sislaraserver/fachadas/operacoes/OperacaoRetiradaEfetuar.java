package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.ResultadoEdicaoRetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaraserver.dominio.retirada.Retirada;
import br.laramara.ti.sislaraserver.dominio.retirada.StatusRetirada;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaRetirada;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRetirada;

public class OperacaoRetiradaEfetuar implements OperacaoFachada {

	private RepositorioRetirada repositorioRetirada;
	private RetiradaDTO retiradaDto;

	public OperacaoRetiradaEfetuar(RepositorioRetirada repositorioRetirada,
			RetiradaDTO retiradaDto) {
		this.repositorioRetirada = repositorioRetirada;
		this.retiradaDto = retiradaDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaDTO = new ResultadoEdicaoRetiradaDTO();

		List<Retirada> retiradas = repositorioRetirada.obterRetiradas(
				retiradaDto.getProntuario(), StatusRetirada.RETIRADO);

		Retirada retirada = new FabricaRetirada()
				.converterParaDominio(retiradaDto);
		retirada.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (retirada.validado() && !possuiRetiradaAtiva(retiradas)) {
			retirada.setContaAcessoResponsavelPorOperacaoRetirada(contaAcesso);
			repositorioRetirada.salvar(retirada);
			resultadoEdicaoRetiradaDTO
					.efetuadoComSucesso("Retirada efetuada com sucesso.");
		} else {
			resultadoEdicaoRetiradaDTO
					.adicionarErro((!retirada.validado() ? retirada
							.obterDescricaoErros()
							: "O prontuário já está retirado. "));
		}
		return resultadoEdicaoRetiradaDTO;
	}

	private boolean possuiRetiradaAtiva(List<Retirada> retiradas) {
		boolean retorno = false;
		for (Retirada retirada : retiradas) {
			if (retirada.obterStatusRetiradaAtual().equals(
					StatusRetirada.RETIRADO)) {
				retorno = true;
			}
		}
		return retorno;
	}
}

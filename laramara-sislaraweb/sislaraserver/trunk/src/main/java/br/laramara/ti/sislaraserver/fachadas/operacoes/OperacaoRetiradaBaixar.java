package br.laramara.ti.sislaraserver.fachadas.operacoes;

import java.util.List;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.ResultadoEdicaoRetiradaDTO;
import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaraserver.dominio.retirada.Retirada;
import br.laramara.ti.sislaraserver.dominio.retirada.StatusRetirada;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRetirada;

public class OperacaoRetiradaBaixar implements OperacaoFachada {

	private RepositorioRetirada repositorioRetirada;
	private RetiradaDTO retiradaDto;

	public OperacaoRetiradaBaixar(RepositorioRetirada repositorioRetirada,
			RetiradaDTO retiradaDto) {
		this.repositorioRetirada = repositorioRetirada;
		this.retiradaDto = retiradaDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		ResultadoEdicaoRetiradaDTO resultadoEdicaoRetiradaDTO = new ResultadoEdicaoRetiradaDTO();
		List<Retirada> retiradasAtivas = repositorioRetirada.obterRetiradas(
				retiradaDto.getProntuario(), StatusRetirada.RETIRADO);
		if (!retiradasAtivas.isEmpty()) {
			Retirada retirada = retiradasAtivas.get(0);
			retirada.setContaAcessoResponsavelPorOperacaoBaixa(contaAcesso);
			repositorioRetirada.salvar(retirada);
			resultadoEdicaoRetiradaDTO
					.efetuadoComSucesso("Baixa da retirada efetuada com sucesso.");
		} else {
			resultadoEdicaoRetiradaDTO
					.adicionarErro("O prontuário não está retirado.");
		}
		return resultadoEdicaoRetiradaDTO;
	}
}

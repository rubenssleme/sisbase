package br.laramara.ti.sismovimentacaoserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sismovimentacaocommons.dtos.ResultadoDTO;
import br.laramara.ti.sismovimentacaocommons.dtos.movimentacao.MovimentacaoDTO;
import br.laramara.ti.sismovimentacaoserver.dominio.movimentacao.Movimentacao;
import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sismovimentacaoserver.fabricas.FabricaMovimentacao;
import br.laramara.ti.sismovimentacaoserver.repositorios.RepositorioMovimentacao;

public class OperacaoMovimentacaoEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoMovimentacaoEdicao.class);

	private FabricaMovimentacao fabricaMovimentacao;
	private RepositorioMovimentacao repositorioMovimentacao;
	private MovimentacaoDTO movimentacaoDto;

	public OperacaoMovimentacaoEdicao(FabricaMovimentacao fabricaMovimentacao,
			RepositorioMovimentacao repositorioMovimentacao,
			MovimentacaoDTO movimentacaoDto) {
		this.fabricaMovimentacao = fabricaMovimentacao;
		this.repositorioMovimentacao = repositorioMovimentacao;
		this.movimentacaoDto = movimentacaoDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		Movimentacao movimentacao = fabricaMovimentacao.converterParaDominio(
				movimentacaoDto,
				repositorioMovimentacao.obterPorId(movimentacaoDto.getId()));
		movimentacao.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (movimentacao.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Edição do "
					+ movimentacao);
			Movimentacao movimentacaoSalvo = repositorioMovimentacao
					.salvar(movimentacao);
			resultadoDto.efetuadoComSucesso(fabricaMovimentacao
					.converterParaDTO(movimentacaoSalvo));
		} else {
			resultadoDto.adicionarErro(movimentacao.obterDescricaoErros());
		}
		return resultadoDto;
	}
}

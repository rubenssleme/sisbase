package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;

public class OperacaoContaAcessoEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoContaAcessoEdicao.class);

	private FabricaContaAcesso fabricaContaAcesso;
	private RepositorioContaAcesso repositorioContaAcesso;
	private ContaAcessoDTO contaAcessoDto;

	public OperacaoContaAcessoEdicao(FabricaContaAcesso fabricaContaAcesso,
			RepositorioContaAcesso repositorioContaAcesso,
			ContaAcessoDTO contaAcessoDto) {
		this.fabricaContaAcesso = fabricaContaAcesso;
		this.repositorioContaAcesso = repositorioContaAcesso;
		this.contaAcessoDto = contaAcessoDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		ContaAcesso contaAcessoAEditar = fabricaContaAcesso
				.converterParaDominio(contaAcessoDto, repositorioContaAcesso
						.obterPorId(contaAcessoDto.getId()));
		contaAcessoAEditar.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (contaAcessoAEditar.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Edição do " + contaAcessoAEditar);
			repositorioContaAcesso.salvar(contaAcessoAEditar);
			resultado.efetuadoComSucesso(fabricaContaAcesso
					.converterParaDTO(contaAcessoAEditar));
		} else {
			resultado.adicionarErro(contaAcessoAEditar.obterDescricaoErros());
		}
		return resultado;
	}

}

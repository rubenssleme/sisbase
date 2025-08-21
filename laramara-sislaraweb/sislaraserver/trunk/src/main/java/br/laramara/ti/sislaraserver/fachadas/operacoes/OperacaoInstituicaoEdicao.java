package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.instituicao.InstituicaoDTO;
import br.laramara.ti.sislaraserver.dominio.instituicao.Instituicao;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaInstituicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInstituicao;

public class OperacaoInstituicaoEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoInstituicaoEdicao.class);

	private RepositorioInstituicao repositorioInstituicao;
	private InstituicaoDTO instituicaoDto;

	public OperacaoInstituicaoEdicao(
			RepositorioInstituicao repositorioInstituicao,
			InstituicaoDTO instituicaoDto) {
		this.repositorioInstituicao = repositorioInstituicao;
		this.instituicaoDto = instituicaoDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		Instituicao instituicao = new FabricaInstituicao()
				.converterParaDominio(instituicaoDto);
		instituicao.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (instituicao.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Edição do "
					+ instituicao);
			if (!repositorioInstituicao.possuiInstituicao(instituicao)) {
				repositorioInstituicao.salvar(instituicao);
				resultado.efetuadoComSucesso(new FabricaInstituicao()
						.converterParaDTO(instituicao));
			} else {
				resultado.adicionarErro("A instituição "
						+ instituicao.getNome() + " já está cadastrada.");
			}

		} else {
			resultado.adicionarErro(instituicao.obterDescricaoErros());
		}
		return resultado;
	}
}

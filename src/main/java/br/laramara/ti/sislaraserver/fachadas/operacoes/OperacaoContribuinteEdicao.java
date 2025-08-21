package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.contribuicao.ContribuinteDTO;
import br.laramara.ti.sislaraserver.dominio.contribuicao.Contribuinte;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaContribuinte;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContribuinte;

public class OperacaoContribuinteEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoContribuinteEdicao.class);
	
	private RepositorioContribuinte repositorioContribuinte;
	private FabricaContribuinte fabricaContribuinte;
	private ContribuinteDTO contribuinteDto;

	public OperacaoContribuinteEdicao(FabricaContribuinte fabricaContribuinte,
			RepositorioContribuinte repositorioContribuinte,
			ContribuinteDTO contribuinteDto) {
		this.fabricaContribuinte = fabricaContribuinte;
		this.repositorioContribuinte = repositorioContribuinte;
		this.contribuinteDto = contribuinteDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		Contribuinte contribuinte = fabricaContribuinte.converterParaDominio(
				contribuinteDto,
				repositorioContribuinte.obterPorId(contribuinteDto.getId()));
		contribuinte.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (contribuinte.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Edição do "
					+ contribuinte);
			Contribuinte contribuinteSalvo = repositorioContribuinte
					.salvar(contribuinte);
			resultadoDto.efetuadoComSucesso(fabricaContribuinte
					.converterParaDTO(contribuinteSalvo));
		} else {
			resultadoDto.adicionarErro(contribuinte.obterDescricaoErros());
		}
		return resultadoDto;
	}
}

package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ProjetoDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Projeto;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaProjeto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProjeto;


public class OperacaoProjetoEdicao implements OperacaoFachada {

	private static final Logger logger = Logger
			.getLogger(OperacaoProjetoEdicao.class);
	
	private RepositorioProjeto repositorioProjeto;
	private ProjetoDTO projetoDto;
	
	public OperacaoProjetoEdicao(FabricaProjeto fabricaProjeto,
			RepositorioProjeto repositorioProjeto, ProjetoDTO projetoDto) {
		super();
		this.repositorioProjeto = repositorioProjeto;
		this.projetoDto = projetoDto;
	}
	
	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultado) {
		Projeto projeto = new FabricaProjeto()
				.converterParaDominio(projetoDto, repositorioProjeto.obterPorId(projetoDto.getId()));
		projeto.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (projeto.validado()) {
			logger.info(contaAcesso + "efetuou Solicitação de Edição do " + projeto);
			repositorioProjeto.salvar(projeto);
			resultado.efetuadoComSucesso(new FabricaProjeto()
					.converterParaDTO(projeto));
		} else {
			resultado.adicionarErro(projeto.obterDescricaoErros());
		}
		return resultado;
	}
}
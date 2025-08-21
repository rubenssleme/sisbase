package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.ReciboDTO;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaRecibo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecibo;

public class OperacaoReciboEdicao implements OperacaoFachada {

	private static final Logger logger = Logger.getLogger(OperacaoContribuinteEdicao.class);

	private RepositorioRecibo repositorioRecibo;
	private FabricaRecibo fabricaRecibo;
	private ReciboDTO reciboDto;

	public OperacaoReciboEdicao(FabricaRecibo fabricaRecibo, RepositorioRecibo repositorioRecibo, ReciboDTO reciboDto) {
		this.fabricaRecibo = fabricaRecibo;
		this.repositorioRecibo = repositorioRecibo;
		this.reciboDto = reciboDto;
	}

	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso, ResultadoDTO resultadoDto) {
		Recibo recibo = fabricaRecibo.converterParaDominio(reciboDto, repositorioRecibo.obterPorId(reciboDto.getId()));
		recibo.setContaAcessoResponsavelOperacao(contaAcesso);
		recibo.validarObrigatoriedadeETamanhoMaximoDeDados();
		if (recibo.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de Edição do " + recibo);
			Recibo reciboSalvo = repositorioRecibo.salvar(recibo);
			resultadoDto.efetuadoComSucesso(fabricaRecibo.converterParaDTO(reciboSalvo));
		} else {
			resultadoDto.adicionarErro(recibo.obterDescricaoErros());
		}
		return resultadoDto;
	}

}

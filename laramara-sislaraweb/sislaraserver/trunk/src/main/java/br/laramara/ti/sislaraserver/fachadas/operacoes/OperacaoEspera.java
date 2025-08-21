package br.laramara.ti.sislaraserver.fachadas.operacoes;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.espera.EsperaDTO;
import br.laramara.ti.sislaraserver.dominio.espera.Espera;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.fabricas.FabricaEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;

public abstract class OperacaoEspera implements OperacaoFachada {

	protected final Logger logger;
	
	private FabricaEspera fabricaEspera;
	private RepositorioEspera repositorioEspera;
	private EsperaDTO esperaDto;
	protected boolean obsAutomaticaDeAssociacao;
	
	public OperacaoEspera(FabricaEspera fabricaEspera,
			RepositorioEspera repositorioEspera, EsperaDTO esperaDto, boolean obsAutomaticaDeAssociacao) {
		this.fabricaEspera = fabricaEspera;
		this.repositorioEspera = repositorioEspera;
		this.esperaDto = esperaDto;
		this.obsAutomaticaDeAssociacao = obsAutomaticaDeAssociacao;
		this.logger = Logger.getLogger(getClass());
	}
	
	@Override
	public ResultadoDTO processar(ContaAcesso contaAcesso,
			ResultadoDTO resultadoDto) {
		Espera espera = fabricaEspera.converterParaDominio(esperaDto,
				repositorioEspera.obterPorId(esperaDto.getId()));
		executar(espera, contaAcesso);
		if (obsAutomaticaDeAssociacao){
			espera.adicionarObs("Agendamento feito via associação pela lista de espera.");
		}
		espera.validarObrigatoriedadeETamanhoMaximoDeDados();

		if (espera.validado()) {
			logger.info(contaAcesso + " efetuou Solicitação de " + descricao() + " do " + espera);
			Espera esperaSalvo = repositorioEspera.salvar(espera);
			resultadoDto.efetuadoComSucesso(fabricaEspera
					.converterParaDTO(esperaSalvo));
		} else {
			resultadoDto.adicionarErro(espera.obterDescricaoErros());
		}
		return resultadoDto;
	}
	
	protected abstract void executar(Espera espera, ContaAcesso contaAcesso);
	
	protected abstract String descricao();
}

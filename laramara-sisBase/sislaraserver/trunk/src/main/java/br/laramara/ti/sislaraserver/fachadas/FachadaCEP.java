package br.laramara.ti.sislaraserver.fachadas;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;
import br.laramara.ti.sislaraserver.fabricas.FabricaEnderecoCEP;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCEP;

@Component
public class FachadaCEP extends Fachada {

	@Inject
	private RepositorioCEP repositorioCEP;

	public ResultadoConsultaCEP consultarPorCEP(String cep) {
		ResultadoConsultaCEP resultado = new ResultadoConsultaCEP();

		EnderecoCEP enderecoCEP = repositorioCEP.obterPorCEP(cep);
		if (enderecoCEP != null) {
			resultado.efetuadoComSucesso(new FabricaEnderecoCEP()
					.converterParaDTO(enderecoCEP));
		}else{
			resultado.adicionarMensagem("Endereço do CEP inexistente.");
		}
		return resultado;
	}
}

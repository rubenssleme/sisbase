package br.laramara.sistelemarketingcommons.dtos.contato;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class DistribuicaoContatoResultadoDTO extends ResultadoDTO<DistribuicaoContatoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(DistribuicaoContatoDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}
	
	public DistribuicaoContatoDTO obterDistribuicaoContatoDto() {
		return obterUnico();
	}

	public List<DistribuicaoContatoDTO> getDistribuicoesContatosDto() {
		return objetos;
	}

	public void setDistribuicoesContatosDto(List<DistribuicaoContatoDTO> distribuicoesContatosDto) {
		this.objetos = distribuicoesContatosDto;
	}
}

package br.laramara.sistelemarketingcommons.dtos.doacao;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class ValorDetalhadoResultadoDTO extends ResultadoDTO<ValorDetalhadoDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(ValorDetalhadoDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}

	public void efetuadoComErro(ValorDetalhadoDTO objetosDto, String mensagem) {
		efetuadoComErro(Arrays.asList(objetosDto), mensagem);
	}

	public ValorDetalhadoDTO obterValorDetalhadoDto() {
		return obterUnico();
	}

	public List<ValorDetalhadoDTO> getValoresDetalhadosDto() {
		return objetos;
	}

	public void setValoresDetalhadosDto(List<ValorDetalhadoDTO> valoresDetalhadosDto) {
		this.objetos = valoresDetalhadosDto;
	}
}

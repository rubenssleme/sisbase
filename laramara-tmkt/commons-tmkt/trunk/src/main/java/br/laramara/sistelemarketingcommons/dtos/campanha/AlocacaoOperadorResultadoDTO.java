package br.laramara.sistelemarketingcommons.dtos.campanha;

import java.util.Arrays;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ResultadoDTO;

public class AlocacaoOperadorResultadoDTO extends ResultadoDTO<AlocacaoOperadorDTO> {

	private static final long serialVersionUID = 3569479888192117969L;

	public void efetuadoComSucesso(AlocacaoOperadorDTO objetosDto) {
		efetuadoComSucesso(Arrays.asList(objetosDto));
	}

	public void efetuadoComErro(AlocacaoOperadorDTO objetosDto, String mensagem) {
		efetuadoComErro(Arrays.asList(objetosDto), mensagem);
	}

	public AlocacaoOperadorDTO obterAlocacaoOperadorDto() {
		return obterUnico();
	}

	public List<AlocacaoOperadorDTO> getAlocacoesOperadoresDto() {
		return objetos;
	}

	public void setAlocacoesOperadoresDto(List<AlocacaoOperadorDTO> alocacoesOperadoresDto) {
		this.objetos = alocacoesOperadoresDto;
	}
}

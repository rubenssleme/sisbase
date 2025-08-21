package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;

import br.laramara.ti.sismovimentacaocommons.dtos.ModeloDTO;

public class ContaAcessoBloqueadoDTO extends ModeloDTO {
	
	private static final long serialVersionUID = -502888320258004481L;
	
	protected String identificacao;
	protected ContaAcessoDTO contaAcessoDTO;
	
	public ContaAcessoBloqueadoDTO(String identificacao,
			ContaAcessoDTO contaAcessoDTO) {
		super();
		this.identificacao = identificacao;
		this.contaAcessoDTO = contaAcessoDTO;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public ContaAcessoDTO getContaAcessoDTO() {
		return contaAcessoDTO;
	}
}

package br.laramara.ti.sismovimentacaocommons.dtos.seguranca;



public class UsuarioContaAcessoBloqueadoDTO extends ContaAcessoBloqueadoDTO {

	private static final long serialVersionUID = 2216493397377968301L;
	
	public UsuarioContaAcessoBloqueadoDTO(String identificacao) {
		super(identificacao, null);
	}
	
	public UsuarioContaAcessoBloqueadoDTO(String identificacao,
			ContaAcessoDTO contaAcessoDto) {
		super(identificacao, contaAcessoDto);
	}

	@Override
	public String toString() {
		return "Prontuário " + identificacao + " bloqueado por: "
				+ contaAcessoDTO.getLogin();
	}
}

package br.laramara.ti.sislaracommons.dtos.seguranca;


public class GrupoContaAcessoBloqueadoDTO extends ContaAcessoBloqueadoDTO {

	private static final long serialVersionUID = 8840750958870955123L;

	public GrupoContaAcessoBloqueadoDTO(String identificacao) {
		super(identificacao, null);
	}
	
	public GrupoContaAcessoBloqueadoDTO(String identificacao,
			ContaAcessoDTO contaAcessoDto) {
		super(identificacao, contaAcessoDto);
	}

	@Override
	public String toString() {
		return "Grupo " + identificacao + " bloqueado por: "
				+ contaAcessoDTO.getLogin();
	}
}

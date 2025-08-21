package br.laramara.ti.sislaracommons.dtos.seguranca;

public class GeralContaAcessoBloqueadoDTO extends ContaAcessoBloqueadoDTO {

	private static final long serialVersionUID = 2216493397377968301L;
	
	public GeralContaAcessoBloqueadoDTO(String identificacao,
			ContaAcessoDTO contaAcessoDto) {
		super(identificacao, contaAcessoDto);
	}

	@Override
	public String toString() {
		return identificacao + " bloqueado por: "
				+ contaAcessoDTO.getLogin();
	}
}

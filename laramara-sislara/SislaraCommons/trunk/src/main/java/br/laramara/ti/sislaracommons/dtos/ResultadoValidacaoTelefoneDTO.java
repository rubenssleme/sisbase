package br.laramara.ti.sislaracommons.dtos;


public class ResultadoValidacaoTelefoneDTO extends ResultadoDTO {

	private static final long serialVersionUID = 8189620152330934777L;

	public void efetuadoComSucesso(TelefoneDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}

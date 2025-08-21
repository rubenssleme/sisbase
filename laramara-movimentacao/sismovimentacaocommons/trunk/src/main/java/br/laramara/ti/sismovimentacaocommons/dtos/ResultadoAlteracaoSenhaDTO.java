package br.laramara.ti.sismovimentacaocommons.dtos;

public class ResultadoAlteracaoSenhaDTO extends ResultadoDTO {

	private static final long serialVersionUID = -3952318344650927808L;

	public void efetuadoComSucesso() {
		efetuadoComSucesso("Alteração de senha realizada com sucesso.", null);
	}
}

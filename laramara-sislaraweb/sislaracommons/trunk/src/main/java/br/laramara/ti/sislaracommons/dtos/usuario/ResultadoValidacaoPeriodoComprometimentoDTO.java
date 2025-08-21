package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoPeriodoComprometimentoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 8504607579375038571L;

	public void efetuadoComSucesso(ComprometimentoDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}

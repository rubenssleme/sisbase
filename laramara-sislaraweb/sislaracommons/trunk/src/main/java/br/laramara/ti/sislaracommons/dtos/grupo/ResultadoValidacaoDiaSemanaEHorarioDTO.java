package br.laramara.ti.sislaracommons.dtos.grupo;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoDiaSemanaEHorarioDTO extends ResultadoDTO {

	private static final long serialVersionUID = 8504607579375038571L;

	public void efetuadoComSucesso(DiaSemanaEHorarioDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}

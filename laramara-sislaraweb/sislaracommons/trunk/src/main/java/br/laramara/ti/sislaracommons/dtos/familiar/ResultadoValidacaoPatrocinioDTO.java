package br.laramara.ti.sislaracommons.dtos.familiar;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;
import br.laramara.ti.sislaracommons.dtos.doacao.PatrocinioDTO;

public class ResultadoValidacaoPatrocinioDTO extends ResultadoDTO {

	private static final long serialVersionUID = 4438233700108387388L;

	public void efetuadoComSucesso(PatrocinioDTO objetoDtoEditado) {
		efetuadoComSucesso("Dados adicionados com sucesso.", objetoDtoEditado);
	}
}

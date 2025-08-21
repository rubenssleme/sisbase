package br.laramara.ti.sislaracommons.dtos.usuario;

import br.laramara.ti.sislaracommons.dtos.ResultadoDTO;

public class ResultadoValidacaoCertidaoDTO extends ResultadoDTO {

	private static final long serialVersionUID = 8189620152330934777L;

	public void efetuadoComSucesso(CertidaoDTO certidaoDto) {
		efetuadoComSucesso("Dados adicionados com sucesso.", certidaoDto);
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.ProntuarioEscaneadoDTO;
import br.laramara.ti.sislaraserver.dominio.Arquivo;
import br.laramara.ti.sislaraserver.dominio.seguranca.ExtensaoArquivo;
import br.laramara.ti.sislaraserver.dominio.usuario.ProntuarioEscaneado;

public class FabricaProntuarioEscaneado extends
		FabricaBase<ProntuarioEscaneadoDTO, ProntuarioEscaneado> {

	public final ProntuarioEscaneadoDTO converterParaDTO(
			ProntuarioEscaneado prontuarioEscaneado) {
		return prontuarioEscaneado != null ? new ProntuarioEscaneadoDTO(
				prontuarioEscaneado.getNomeArquivo(),
				new FabricaArquivo().converterParaDTO(prontuarioEscaneado
						.getArquivo())) : null;
	}

	public final ProntuarioEscaneado converterParaDominio(
			ProntuarioEscaneadoDTO prontuarioEscaneadoDto) {
		return prontuarioEscaneadoDto != null ? new ProntuarioEscaneado(
				prontuarioEscaneadoDto.toString(), 0, new Arquivo(new byte[0],
						ExtensaoArquivo.valueOf(ExtensaoArquivo.class,
								prontuarioEscaneadoDto.getArquivoDto()
										.obterExtensao()))) : null;
	}
}

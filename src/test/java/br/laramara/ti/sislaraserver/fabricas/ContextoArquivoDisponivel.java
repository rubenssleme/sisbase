package br.laramara.ti.sislaraserver.fabricas;

import java.util.Date;

import br.laramara.ti.sislaraserver.dominio.ArquivoDisponivel;

public class ContextoArquivoDisponivel {

	public static ArquivoDisponivel fabricarComTodosOsDados() {
		ArquivoDisponivel arquivoDisponivel = new ArquivoDisponivel();
		arquivoDisponivel.setDataAtendimento(new Date());
		arquivoDisponivel.setHoraInicio("09:00");
		arquivoDisponivel.setHoraTermino("10:00");
		arquivoDisponivel.setIdAtendimento(new Long(1));
		arquivoDisponivel.setNomeArquivo("teste.pdf");
		arquivoDisponivel.setNomeGrupo("G02-01");
		arquivoDisponivel.setTipo("GRUPO");
		return arquivoDisponivel;
	}

}

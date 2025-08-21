package br.laramara.sistelemarketingclient.paginas;

import br.laramara.sistelemarketingclient.utils.DriverUtils;

public class PaginaListagem extends Pagina{

	public static final String URL_EDICAO_CAMPANHA_A = "/listagem.xhtml";

	public PaginaListagem(boolean forcado) {
		DriverUtils.ir(URL_BASE + "/listagem.xhtml", forcado);
	}
}

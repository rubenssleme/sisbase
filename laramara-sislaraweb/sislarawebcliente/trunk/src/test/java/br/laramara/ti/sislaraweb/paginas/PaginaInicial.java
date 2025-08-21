package br.laramara.ti.sislaraweb.paginas;

public class PaginaInicial extends PaginaBase {
	private static final String TITULO_PAGINA_INICIAL = "SisLaraWeb - Página Inicial";
	
	public boolean paginaValida() {
		return super.paginaValida(TITULO_PAGINA_INICIAL);
	}

}

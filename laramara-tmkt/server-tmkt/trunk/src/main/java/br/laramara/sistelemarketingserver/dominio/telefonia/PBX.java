package br.laramara.sistelemarketingserver.dominio.telefonia;

public interface PBX {
	public void ligar(String ramal, String numero);
	public String obterStatusRamais(String ramal);
}

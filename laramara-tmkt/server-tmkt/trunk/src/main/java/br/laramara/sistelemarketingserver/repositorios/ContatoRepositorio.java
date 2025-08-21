package br.laramara.sistelemarketingserver.repositorios;

import java.util.List;

import br.laramara.sistelemarketingserver.dominio.Municipio;
import br.laramara.sistelemarketingserver.dominio.contato.Contato;

public interface ContatoRepositorio {
	
	public List<Contato> obterNovosPor(Municipio municipio, String bairro, Integer pagina);

	public Long obterNumeroPaginasNovosPor(Municipio municipio, String bairro);

}

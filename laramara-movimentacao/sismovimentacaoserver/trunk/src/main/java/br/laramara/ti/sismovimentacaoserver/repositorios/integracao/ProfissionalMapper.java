package br.laramara.ti.sismovimentacaoserver.repositorios.integracao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import br.laramara.ti.sismovimentacaoserver.dominio.Profissional;

public interface ProfissionalMapper {

	final String SELECT_ALL = "select nome, chapa " +
			"from pessoal " +
			"where local in (001, 006) " +
			"	and cod_deslig=00 " +
			"	and instrucao in (07, 08, 09, 10, 11)";

	@Select(SELECT_ALL)
	@Results(value = { @Result(property = "nome", column = "nome"),
			@Result(property = "chapa", column = "chapa") })
	List<Profissional> obterTodos();
}

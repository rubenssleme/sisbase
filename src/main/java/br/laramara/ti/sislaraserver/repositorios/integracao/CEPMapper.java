package br.laramara.ti.sislaraserver.repositorios.integracao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import br.laramara.ti.sislaraserver.dominio.endereco.EnderecoCEP;

public interface CEPMapper {
	final String SELECT_ALL = "select pais, uf, cidade as municipio, bairro, endereco " +
			"from tend_endereco tend " +
			"inner join tend_cidade tcid " +
			"	on (tend.id_cidade = tcid.id_cidade)" +
			"inner join tend_bairro tbar " +
			"	on (tend.id_bairro = tbar.id_bairro)" +
			"where tend.cep = #{cep}";
	
	@Select(SELECT_ALL)
	@Results(value = { 	@Result(property = "nomePais", column = "pais"),
						@Result(property = "uf", column = "uf"),
						@Result(property = "nomeMunicipio", column = "municipio"),
						@Result(property = "bairro", column = "bairro"),
						@Result(property = "endereco", column = "endereco") })
	EnderecoCEP obterTodos(@Param("cep") String cep);
}

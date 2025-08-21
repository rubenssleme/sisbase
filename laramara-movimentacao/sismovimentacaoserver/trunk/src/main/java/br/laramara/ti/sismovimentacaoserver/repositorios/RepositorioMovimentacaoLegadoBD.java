package br.laramara.ti.sismovimentacaoserver.repositorios;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.laramara.ti.sismovimentacaoserver.utilitarios.Registro;

@Repository
public class RepositorioMovimentacaoLegadoBD implements
		RepositorioMovimentacaoLegado {

	@Override
	public String obterHistorico(Long id) {
		String resultado = "";
		JdbcTemplate jdbcTemplate = Registro
				.obterJdbcTemplateSisMovimentacaoLegado();
		SqlRowSet sqlRowSet = jdbcTemplate
				.queryForRowSet("SELECT dt_entrada||' Hora: '||hr_entrada as \"Data de entrada\", dt_retorno||' Hora: '||hr_retorno as \"Data de arte finaliza\", dt_envio_cli||' Hora: '||hr_envio_cli as \"Data de envio para o cliente\",  dt_aprova||' Hora: '||hr_aprova as \"Data de aprovação\",  dt_requis_filme||' Hora: '||hr_requis_filme as \"Data de requisição de filme\",  dt_saida_filme||' Hora: '||hr_saida_filme as \"Data de saida de filme\",  dt_rec_filme||' Hora: '||hr_rec_filme as \"Data de recebimento de filme\",  dt_entrega||' Hora: '||hr_entrega_filme as \"Data de entrega do filme\" FROM MOVIMENTOS a where id = "
						+ id);
		int contadorColunas = sqlRowSet.getMetaData().getColumnCount();
		while (sqlRowSet.next()) {
			for (int i = 1; i <= contadorColunas; i++) {
				resultado += sqlRowSet.getMetaData().getColumnName(i) + ": "
						+ sqlRowSet.getString(i) + " \n";
			}
		}
		return resultado.replace("00:00:00.0000" , "").replace(":00.0000", "");
	}
}

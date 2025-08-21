package br.laramara.sistelemarketingserver.repositorios;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;
import br.laramara.sistelemarketingserver.dominio.telefonia.TipoEventoTelefonia;
import br.laramara.sistelemarketingserver.utilitarios.Registro;

@Repository
public class PBXRepositorioBD implements PBXRepositorio{
	
	private final Logger logger = Logger.getLogger(PBXRepositorioBD.class);

	private JdbcTemplate jdbcTemplate;
	
	@Resource(name = Registro.NOME_DATA_SOURCE_PBX)
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public List<EventoTelefonia> obterEventosAposId(Long id) {
		//368865
		List<EventoTelefonia> eventosTelefonia = new ArrayList<>();
		if (id != null) {
			String sql = "select cel.id, cdr.disposition, cdr.calldate, cdr.billsec, cdr.dst, cel.uniqueid, cdr.cnum, cel.extra, cel.eventtype, cel.eventtime from cdr " + 
					"  inner join cel " + 
					"    on cdr.uniqueid = cel.uniqueid " + 
					"where eventtype in ('ANSWER', 'HANGUP') and cel.extra like '' and cel.id > " + id.toString() + " " +
					"order by cel.id; ";
			 
			List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql);
			for (@SuppressWarnings("rawtypes") Map resultado : resultados) {
				try {
					EventoTelefonia eventoTelefonia = new EventoTelefonia();
					String telefone = (String) resultado.get("dst");
					eventoTelefonia.setId(new Long((Integer) resultado.get("id")));
					eventoTelefonia.setDdd(telefone.substring(0, 3));
					eventoTelefonia.setTelefone(telefone.substring(3, telefone.length()));
					eventoTelefonia.setDuracaoLigacao((Integer) resultado.get("billsec"));
					eventoTelefonia.setUniqueId((String) resultado.get("uniqueid"));
					eventoTelefonia.setDataOcorrencia(((Timestamp) resultado.get("calldate")).toLocalDateTime());
					eventoTelefonia.setRamal(Ramal.obter((String) resultado.get("cnum")));
					eventoTelefonia.setTipoEvento(TipoEventoTelefonia.obter(((String) resultado.get("disposition"))));
					eventosTelefonia.add(eventoTelefonia);
				} catch (Exception e) {
					logger.fatal("Erro durante adicao de evento de telefonia. \nDetalhes: " + e);
				}
			}
		}
		return eventosTelefonia;
	}
}

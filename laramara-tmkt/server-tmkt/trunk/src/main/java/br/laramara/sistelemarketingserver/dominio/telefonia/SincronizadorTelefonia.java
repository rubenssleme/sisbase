package br.laramara.sistelemarketingserver.dominio.telefonia;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;
import br.laramara.sistelemarketingserver.repositorios.DistribuicaoContatoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.EventoTelefoniaRepositorio;
import br.laramara.sistelemarketingserver.repositorios.PBXRepositorio;

@Component
public class SincronizadorTelefonia {

	private final Logger logger = Logger.getLogger(SincronizadorTelefonia.class);

	@Inject
	private PBXRepositorio pbxRepositorio;
	@Inject
	private DistribuicaoContatoRepositorio distribuicaoContatoRepositorio;
	@Inject
	private EventoTelefoniaRepositorio eventoTelefoniaRepositorio;
	
	public synchronized void sincronizar() {
		try {
			Long obterUltimoIdentificador = eventoTelefoniaRepositorio.obterUltimoIdentificador();
			List<EventoTelefonia> eventosDeTelefonia = pbxRepositorio.obterEventosAposId(obterUltimoIdentificador);
			for (EventoTelefonia eventoTelefonia : eventosDeTelefonia) {
				DistribuicaoContato distribuicaoContato = distribuicaoContatoRepositorio
						.obterDistribuicaoMaisRecentePorEvento(eventoTelefonia);
				if (distribuicaoContato != null) {
					distribuicaoContato.adicionarEventoTelefonia(eventoTelefonia);
					distribuicaoContatoRepositorio.salvar(distribuicaoContato);
				}
			}
		} catch (Exception e) {
			logger.fatal("Erro durante sincronização de telefonia. \nDetalhes: " + e);
		}
	}
}

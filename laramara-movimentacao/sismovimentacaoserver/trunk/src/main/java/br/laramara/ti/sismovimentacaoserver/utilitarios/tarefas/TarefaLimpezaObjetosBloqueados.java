package br.laramara.ti.sismovimentacaoserver.utilitarios.tarefas;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.laramara.ti.sismovimentacaoserver.dominio.seguranca.CoordenadorEdicaoGeral;

@Service
public class TarefaLimpezaObjetosBloqueados {
	@Inject
	private CoordenadorEdicaoGeral coordenadorEdicaoGeral;

	@Scheduled(cron = "0 0 0 * * ?")
	public void execute() {
		coordenadorEdicaoGeral.desbloquearTodos();
	}
}

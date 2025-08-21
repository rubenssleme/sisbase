package br.laramara.sistelemarketingserver.dominio.telefonia;

import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;
import br.laramara.sistelemarketingserver.utilitarios.MaquinaTempo;

public class ContextoEventoTelefonia {

	public static EventoTelefonia criarEventoPrincipal() {
		EventoTelefonia eventoTelefonia = new EventoTelefonia();
		eventoTelefonia.setId(new Long(1001));
		eventoTelefonia.setRamal(Ramal.RAMAL_6489);
		eventoTelefonia.setDdd("011");
		eventoTelefonia.setTelefone("957878870");
		eventoTelefonia.setUniqueId("15420539779558");
		eventoTelefonia.setTipoEvento(TipoEventoTelefonia.ANSWERED);
		eventoTelefonia.setDataOcorrencia(MaquinaTempo.obterDataEHoraAtual());
		eventoTelefonia.setDuracaoLigacao(120);
		return eventoTelefonia;
	}
	
	public static EventoTelefonia criarEventoAlternativo() {
		EventoTelefonia eventoTelefonia = new EventoTelefonia();
		eventoTelefonia.setId(new Long(1002));
		eventoTelefonia.setRamal(Ramal.RAMAL_6489);
		eventoTelefonia.setDdd("091");
		eventoTelefonia.setTelefone("922222222");
		eventoTelefonia.setUniqueId("15420539779558");
		eventoTelefonia.setTipoEvento(TipoEventoTelefonia.NO_ANSWER);
		eventoTelefonia.setDataOcorrencia(MaquinaTempo.obterDataEHoraAtual());
		eventoTelefonia.setDuracaoLigacao(60);
		return eventoTelefonia;
	}
}

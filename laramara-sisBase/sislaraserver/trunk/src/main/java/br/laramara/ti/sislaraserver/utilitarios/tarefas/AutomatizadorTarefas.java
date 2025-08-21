package br.laramara.ti.sislaraserver.utilitarios.tarefas;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.laramara.ti.sislaraserver.dominio.atendimento.AutomatizadorAcaoConduta;
import br.laramara.ti.sislaraserver.dominio.doacao.AutomatizadorDoacao;
import br.laramara.ti.sislaraserver.dominio.espera.AutomatizadorEspera;
import br.laramara.ti.sislaraserver.dominio.pendencia.AutomatizadorPendencia;
import br.laramara.ti.sislaraserver.dominio.seguranca.CoordenadorEdicaoGeral;
import br.laramara.ti.sislaraserver.dominio.usuario.AutomatizadorStatusUsuario;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheEspera;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheGrupo;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheInstituicao;
import br.laramara.ti.sislaraserver.utilitarios.AvisoAtualizacao;

@Service
public class AutomatizadorTarefas {
	
	private boolean atualizacaoPendenciasDesativada;
		
	@Inject
	private CoordenadorEdicaoGeral coordenadorEdicaoGeral;
	
	@Inject
	private AutomatizadorStatusUsuario automatizadorStatusUsuario;
	
	@Inject
	private AvisoAtualizacao avisoAtualizacao;
	
	@Inject
	private AutomatizadorPendencia automatizadorPendencia;
	
	@Inject
	private AutomatizadorDoacao automatizadorDoacao;
	
	@Inject
	private AutomatizadorEspera automatizadorEspera;
	
	@Inject
	private AutomatizadorAcaoConduta automatizadorAcaoConduta;
	
	@Inject
	private CacheEspera cacheEspera;
	
	@Inject
	private CacheGrupo cacheGrupo;
	
	@Inject
	private CacheInstituicao cacheInstuicao;
	
	public void desativarAtualizacaoPendencias(){
		atualizacaoPendenciasDesativada = true;
	}
	
	//Executar todos os dias às 17:44
	@Scheduled(cron = "0 44 17 * * ?")
	public void fecharClient() {
		avisoAtualizacao.ativarFechamentoAutomaticoClient();
	}
	
	//Executar todos os dias às 0hs (Nunca mudar, pois gera impacto nos subprocessos)
	@Scheduled(cron = "0 0 0 * * ?")
	public void executar() {
		atualizarCaches();
		automatizadorEspera.cancelarEsperasDeAvaliacaoCTOComDataDeExpectativaExpiradaHaMaisDe2AnosComUsuariosMenoresDe21AnosESemNenhumAtendimentoNoPeriodo();
		automatizadorStatusUsuario.atualizarStatusDeUsuariosParaDesistenteOuRetorno();
		automatizadorPendencia.atualizarPendenciasDeTransferenciasDeReuniaoIntegracao();
		automatizadorAcaoConduta.processarAcoesCondutas();
		automatizadorDoacao.atualizarDoacoes();
		coordenadorEdicaoGeral.desbloquearTodos();
		avisoAtualizacao.desativar();
	}

	private void atualizarCaches() {
		cacheEspera.atualizarCache();
		cacheGrupo.atualizarCache();
		cacheInstuicao.atualizarCache();
	}
	
	//Executar a cada 10 segundos
	@Scheduled(fixedDelay = 10000)
	public void atualizarPendencias() {
		if (!atualizacaoPendenciasDesativada){
			automatizadorPendencia.atualizarPendencias();
		}
	}
}

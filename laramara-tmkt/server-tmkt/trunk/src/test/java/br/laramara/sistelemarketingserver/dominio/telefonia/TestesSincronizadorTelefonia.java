package br.laramara.sistelemarketingserver.dominio.telefonia;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

import br.laramara.sistelemarketingcommons.utilitarios.TiposDeTeste;
import br.laramara.sistelemarketingserver.TestesIntegracaoAbstrato;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;
import br.laramara.sistelemarketingserver.repositorios.PBXRepositorio;
import br.laramara.sistelemarketingserver.utilitarios.Registro;

public class TestesSincronizadorTelefonia extends TestesIntegracaoAbstrato {
	
	protected TestesSincronizadorTelefonia() {
		super("DadosTestesSincronizadorTelefonia.xml");
	}

	@Test(groups = { TiposDeTeste.INTEGRACAO_COM_DB })
	public void sincronizador_telefonia_sincroniza_com_sucesso() {
		EventoTelefonia eventoTelefoniaPrincipal = ContextoEventoTelefonia.criarEventoPrincipal();
		EventoTelefonia eventoTelefoniaAlternativa = ContextoEventoTelefonia.criarEventoAlternativo();
 				
		PBXRepositorio pbxStub = mock(PBXRepositorio.class);
		when(pbxStub.obterEventosAposId(new Long(1000)))
				.thenReturn(Arrays.asList(eventoTelefoniaPrincipal, eventoTelefoniaAlternativa));
		
		SincronizadorTelefonia sincronizadorTelefonia = Registro.obterSincronizadorTelefonia();
		ReflectionTestUtils.setField(sincronizadorTelefonia, "pbxRepositorio", pbxStub);
		
		sincronizadorTelefonia.sincronizar();
		sincronizadorTelefonia.sincronizar();
		
		DistribuicaoContato distribuicaoContato = Registro.obterDistribuidorContato()
				.obterContato(ContextoContaAcesso.fabricarContaAcessoPaulo());

		Assert.assertTrue(
				distribuicaoContato.getEventosTelefonia().get(0).getId().equals(eventoTelefoniaPrincipal.getId()));
		Assert.assertTrue(
				distribuicaoContato.getEventosTelefonia().get(1).getId().equals(eventoTelefoniaAlternativa.getId()));
	}
}

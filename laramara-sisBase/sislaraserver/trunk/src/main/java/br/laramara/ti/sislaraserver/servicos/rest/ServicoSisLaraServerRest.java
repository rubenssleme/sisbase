package br.laramara.ti.sislaraserver.servicos.rest;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.laramara.ti.sislaracommons.dtos.seguranca.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;

@RestController
public class ServicoSisLaraServerRest {

	public static final String URL_INSCRICAO = "/inscricao";
	public static final String URL_AUTENTICACAO_USUARIO_EXTERNO = URL_INSCRICAO + "/autenticacao/usuario-externo";
	
	@Inject
	private ServicoSisLaraServer servicoSisLaraServer; 
	
	@RequestMapping(value = URL_AUTENTICACAO_USUARIO_EXTERNO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoAutenticacaoDTO autenticarUsuarioExterno(@RequestBody CredencialExternaDTO credencialExternaDto) throws Exception {
		return servicoSisLaraServer.autenticarUsuarioExterno(credencialExternaDto);
	}
}

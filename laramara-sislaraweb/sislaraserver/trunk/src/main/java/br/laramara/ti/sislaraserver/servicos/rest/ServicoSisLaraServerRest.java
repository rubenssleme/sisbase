package br.laramara.ti.sislaraserver.servicos.rest;

import java.rmi.RemoteException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoConsultaDetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoListagemDetalhesCursoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.ResultadoAutenticacaoDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.TokenDTO;
import br.laramara.ti.sislaracommons.dtos.seguranca.externa.CredencialExternaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaPerfilPreenchidoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoConsultaUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoSolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.ResultadoSolicitacaoRecuperacaoSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroInscricaoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroNovaSenhaDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoCadastroUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.solicitacao.externa.SolicitacaoEdicaoUsuarioExternoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;
import br.laramara.ti.sislaracommons.servicos.rest.ContratoRest;
import br.laramara.ti.sislaraserver.dominio.endereco.UF;

@RestController
public class ServicoSisLaraServerRest {

	public static final String SISLARAWEB = "sislaraweb";

	@Inject
	private ServicoSisLaraServer servicoSisLaraServer;

	@RequestMapping(value = ContratoRest.URL_DETALHE_CURSO_LISTAGEM, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoListagemDetalhesCursoDTO obterDetalhesCursos() throws RemoteException {
		return servicoSisLaraServer.obterListagemDetalhesCursos();
	}

	@RequestMapping(value = ContratoRest.URL_DETALHE_CURSO_CONSULTA_POR_IDGRUPO, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoConsultaDetalheCursoDTO obterDetalheCursoPorIdGrupo(@PathVariable(name = "idGrupo") Long idGrupo)
			throws RemoteException {
		return servicoSisLaraServer.obterDetalheCursoPorIdGrupo(idGrupo);
	}

	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_AUTENTICACAO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoAutenticacaoDTO autenticarUsuarioExterno(@RequestBody CredencialExternaDTO credencialExternaDto)
			throws Exception {
		return servicoSisLaraServer.autenticarUsuarioExterno(credencialExternaDto);
	}

	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_SOLICITACAO_RECUPERACAO_SENHA, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoSolicitacaoRecuperacaoSenhaDTO efetuarSolicitacaoRecuperacaoSenha(
			@RequestBody String emailSolicitante) throws RemoteException {
		return servicoSisLaraServer.efetuarSolicitacaoRecuperacaoSenha(emailSolicitante);
	}

	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_CADASTRO_NOVA_SENHA, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoSolicitacaoCadastroNovaSenhaDTO cadastrarNovaSenha(
			@RequestBody SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDTO) throws RemoteException {
		return servicoSisLaraServer.cadastrarNovaSenha(solicitacaoCadastroNovaSenhaDTO);
	}

	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_CADASTRO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoCadastroUsuarioExternoDTO cadastrarUsuarioExterno(
			@RequestBody SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDTO)
			throws RemoteException {
		return servicoSisLaraServer.cadastrarUsuarioExterno(solicitacaoCadastroUsuarioExternoDTO);
	}
	
	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_CONSULTA_PERFIL_PREENCHIDO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoConsultaPerfilPreenchidoDTO consultarPerfilPreenchidoUsuarioExterno(
			@RequestBody TokenDTO tokenDto)
			throws RemoteException {
		return servicoSisLaraServer.consultarPerfilPreenchido(tokenDto);
	}

	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_EDICAO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoEdicaoUsuarioExternoDTO editarUsuarioExterno(
			@RequestBody SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDto)
			throws RemoteException {
		return servicoSisLaraServer.editarUsuarioExterno(solicitacaoEdicaoUsuarioExternoDto);
	}
	
	@RequestMapping(value = ContratoRest.URL_USUARIO_EXTERNO_CONSULTA_POR_TOKEN, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoConsultaUsuarioExternoDTO obterUsuarioExternoPorToken(
			@PathVariable(name = "token") String tokenDto)
			throws RemoteException {
		return servicoSisLaraServer.obterUsuarioExternoPorToken(tokenDto);
	}
	
	@RequestMapping(value = ContratoRest.URL_ENDERECO_CONSULTA_POR_CEP, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoConsultaCEP obterEnderecoPorCep(
			@PathVariable(name = "cep") String cep)
			throws RemoteException {
		return servicoSisLaraServer.consultarEndereco(cep);
	}
	
	@RequestMapping(value = ContratoRest.URL_UF_LISTAGEM, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoListagemUfDTO obterListagemUf()
			throws RemoteException {
		return servicoSisLaraServer.obterListagemUf();
	}
	
	@RequestMapping(value = ContratoRest.URL_MUNICIPIO_LISTAGEM_POR_UF, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoListagemMunicipioDTO obterListagemMunicipioPorUf(@PathVariable(name = "uf") String uf)
			throws RemoteException {
		return servicoSisLaraServer.obterListagemMunicipio(UF.existe(uf) ? new UfDTO(uf) : null);
	}

	@RequestMapping(value = ContratoRest.URL_INSCRICAO_CADASTRO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultadoCadastroInscricaoDTO cadastrarInscricao(
			@RequestBody SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDTO)
			throws RemoteException {
		return servicoSisLaraServer.cadastrarInscricao(solicitacaoCadastroInscricaoDTO);
	}
}

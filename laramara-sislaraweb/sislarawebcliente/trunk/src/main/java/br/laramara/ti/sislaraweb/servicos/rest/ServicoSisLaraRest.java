package br.laramara.ti.sislaraweb.servicos.rest;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
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

@Stateless
@Default
public class ServicoSisLaraRest extends ServicoSisLaraRestBase implements ServicoSisLaraClient {

	@Override
	public ResultadoListagemDetalhesCursoDTO obterDetalhesCurso() {
		return (ResultadoListagemDetalhesCursoDTO) executarRequisicaoGet(obterUrlListagemDetalhesCursos(),
				new ResultadoListagemDetalhesCursoDTO());
	}

	@Override
	public ResultadoConsultaDetalheCursoDTO obterDetalheCursoPorIdGrupo(Long idGrupo) {
		return (ResultadoConsultaDetalheCursoDTO) executarRequisicaoGet(obterUrlDetalheCursoPorIdGrupo(idGrupo),
				new ResultadoConsultaDetalheCursoDTO());
	}

	@Override
	public ResultadoCadastroUsuarioExternoDTO cadastrarUsuarioExterno(
			SolicitacaoCadastroUsuarioExternoDTO solicitacaoCadastroUsuarioExternoDTO) {
		return (ResultadoCadastroUsuarioExternoDTO) executarRequisicaoPost(obterUrlCadastroUsuarioExterno(),
				solicitacaoCadastroUsuarioExternoDTO, new ResultadoCadastroUsuarioExternoDTO());
	}

	@Override
	public ResultadoAutenticacaoDTO autenticarUsuarioExterno(CredencialExternaDTO credencialExternaDto) {
		return (ResultadoAutenticacaoDTO) executarRequisicaoPost(obterUrlAutenticacaoUsuarioExterno(),
				credencialExternaDto, new ResultadoAutenticacaoDTO());
	}

	@Override
	public ResultadoSolicitacaoRecuperacaoSenhaDTO efetuarSolicitacaoRecuperacaoSenha(String email) {
		return (ResultadoSolicitacaoRecuperacaoSenhaDTO) executarRequisicaoPost(obterUrlSolicitacaoRecuperacaoSenha(),
				email, new ResultadoSolicitacaoRecuperacaoSenhaDTO());
	}

	@Override
	public ResultadoSolicitacaoCadastroNovaSenhaDTO cadastrarNovaSenha(
			SolicitacaoCadastroNovaSenhaDTO solicitacaoCadastroNovaSenhaDto) {
		return (ResultadoSolicitacaoCadastroNovaSenhaDTO) executarRequisicaoPost(obterUrlAlteracaoSenha(),
				solicitacaoCadastroNovaSenhaDto, new ResultadoSolicitacaoCadastroNovaSenhaDTO());
	}

	@Override
	public ResultadoConsultaPerfilPreenchidoDTO consultarPerfilPreenchido(TokenDTO token) {
		return (ResultadoConsultaPerfilPreenchidoDTO) executarRequisicaoPost(
				obterUrlConsultaPerfilPreenchidoUsuarioExterno(), token, new ResultadoConsultaPerfilPreenchidoDTO());
	}

	@Override
	public ResultadoEdicaoUsuarioExternoDTO editarUsuarioExterno(
			SolicitacaoEdicaoUsuarioExternoDTO solicitacaoEdicaoUsuarioExternoDTO) {
		return (ResultadoEdicaoUsuarioExternoDTO) executarRequisicaoPost(obterUrlEdicaoUsuarioExterno(),
				solicitacaoEdicaoUsuarioExternoDTO, new ResultadoEdicaoUsuarioExternoDTO());
	}

	@Override
	public ResultadoConsultaUsuarioExternoDTO obterUsuarioExternoPorToken(String token) {
		return (ResultadoConsultaUsuarioExternoDTO) executarRequisicaoGet(
				obterUrlConsultaUsuarioExternoPorToken(token), new ResultadoConsultaUsuarioExternoDTO());
	}

	@Override
	public ResultadoConsultaCEP consultarEnderecoPorCep(String cep) {
		return (ResultadoConsultaCEP) executarRequisicaoGet(
				obterUrlConsultaEnderecoPorCep(cep), new ResultadoConsultaCEP());
	}
	
	@Override
	public ResultadoListagemUfDTO obterListagemUf() {
		return (ResultadoListagemUfDTO) executarRequisicaoGet(
				obterUrlListagemUf(), new ResultadoListagemUfDTO());
	}
	
	@Override
	public ResultadoListagemMunicipioDTO obterListagemMunicipioPorUf(String uf) {
		return (ResultadoListagemMunicipioDTO) executarRequisicaoGet(
				obterUrlListagemMunicipioPorUf(uf), new ResultadoListagemMunicipioDTO());
	}

	@Override
	public ResultadoCadastroInscricaoDTO cadastrarInscricao(
			SolicitacaoCadastroInscricaoDTO solicitacaoCadastroInscricaoDto) {
		return (ResultadoCadastroInscricaoDTO) executarRequisicaoPost(obterUrlCadastroInscricao(),
				solicitacaoCadastroInscricaoDto, new ResultadoCadastroInscricaoDTO());
	}
}
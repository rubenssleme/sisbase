package br.laramara.ti.sislaraserver.dominio.doacao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.dominio.grupo.Recurso;
import br.laramara.ti.sislaraserver.dominio.seguranca.ContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDemanda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecurso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;

@Component
public class Demandador {

	@Inject
	private RepositorioDemanda repositorioDemanda;
	@Inject
	private RepositorioRecurso repositorioRecurso;
	@Inject
	private RepositorioUsuario repositorioUsuario;
	@Inject
	private RepositorioPreCadastro repositorioPreCadastro;
	@Inject
	private RepositorioSislara repositorioSislara;
	
	public List<Demanda> gerarDemandas(EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda,
			ContaAcesso contaAcessoOperacao) throws NaoPermiteCartelaDeSelosException, CartelaDeSelosDuplicada {
		List<Demanda> demandas = new ArrayList<>();
		verificarExistenciaDuplicadaDeDemandaPorCFP(especificacaoGeracaoDemanda);
		verificarExistenciaDeCartelaDeSelosNoLegado(especificacaoGeracaoDemanda);
		
		String sequenciaCriacao = UUID.randomUUID().toString();
		for (Recurso recurso : especificacaoGeracaoDemanda.getRecursos()) {
			Demanda demanda = new Demanda();
			demanda.setSequenciaCricao(sequenciaCriacao);
			demanda.setGrupo(especificacaoGeracaoDemanda.getGrupo());
			demanda.setCarteloDeSelosEInicializarDataPrazoCaptacao(especificacaoGeracaoDemanda.isCartelaDeSelos());
			demanda.setDocumentosSolicitacaoDoacao(especificacaoGeracaoDemanda.getDocumentosSolicitacaoDoacao());
			demanda.setRecurso(repositorioRecurso.obterPorId((recurso.getId())));
			demanda.setContaAcessoResponsavelPorOperacao(contaAcessoOperacao);
			demanda.setUsuario(especificacaoGeracaoDemanda.possuiUsuario()
					? repositorioUsuario.obterPorId(especificacaoGeracaoDemanda.getUsuario().getId()) : null);
			demanda.setPreCadastro(especificacaoGeracaoDemanda.possuiPreCadastro()
					? repositorioPreCadastro.obterPorId(especificacaoGeracaoDemanda.getPreCadastro().getId()) : null);
			demanda.validarObrigatoriedadeETamanhoMaximoDeDados();
			if (!demanda.validado()) {
				throw new NaoPermiteCartelaDeSelosException(demanda.obterDescricaoErros());
			}
			demandas.add(demanda);
		}
		return repositorioDemanda.salvar(demandas);
	}

	private void verificarExistenciaDeCartelaDeSelosNoLegado(EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda)
			throws CartelaDeSelosDuplicada {
		if (especificacaoGeracaoDemanda.isCartelaDeSelos()) {
			if (repositorioSislara.cpfCnpjJaExisteDoacaoMaquinaBrailleCartelaDeSelos(
					especificacaoGeracaoDemanda.obterInformacaoEssencial())) {
				throw new CartelaDeSelosDuplicada(
						"Já existe uma cartela de selos no legado para o CPF selecionado.");
			}
		}
	}

	private void verificarExistenciaDuplicadaDeDemandaPorCFP(
			EspecificacaoGeracaoDemanda especificacaoGeracaoDemanda) throws CartelaDeSelosDuplicada {
		for (Recurso recurso : especificacaoGeracaoDemanda.getRecursos()) {
			EspecificacaoPesquisaDemanda especificacaoPesquisaDemanda = new EspecificacaoPesquisaDemanda();
			especificacaoPesquisaDemanda.setCpf(especificacaoGeracaoDemanda.obterInformacaoEssencial().getCpf());
			especificacaoPesquisaDemanda.setRecurso(recurso);
			if (!repositorioDemanda.obterPor(especificacaoPesquisaDemanda).isEmpty()) {
				throw new CartelaDeSelosDuplicada("Já existe uma demanda cadastrada para o CPF selecionado.");
			}
		}
	}
}

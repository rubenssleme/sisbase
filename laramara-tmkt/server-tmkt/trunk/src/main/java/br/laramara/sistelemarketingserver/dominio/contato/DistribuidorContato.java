package br.laramara.sistelemarketingserver.dominio.contato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.sistelemarketingserver.dominio.campanha.AlocacaoOperador;
import br.laramara.sistelemarketingserver.dominio.campanha.Campanha;
import br.laramara.sistelemarketingserver.dominio.campanha.Criterio;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.repositorios.CampanhaRepositorio;
import br.laramara.sistelemarketingserver.repositorios.ContaAcessoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.ContatoRepositorio;
import br.laramara.sistelemarketingserver.repositorios.DistribuicaoContatoRepositorio;

@Component
public class DistribuidorContato {
	
	private static final int NUMERO_MAXIMO_TENTATIVAS_SELECAO_DISTRIBUICAO_CONTATO = 10;

	private final Logger logger = Logger.getLogger(DistribuidorContato.class);

	@Inject
	private ContatoRepositorio contatoRepositorio;
	@Inject
	private CampanhaRepositorio campanhaRepositorio;
	@Inject
	private ContaAcessoRepositorio contaAcessoRepositorio;
	@Inject
	private DistribuicaoContatoRepositorio distribuicaoContatoRepositorio;
	
	private List<DistribuicaoContato> distribuicoesContatos;
	private Map<ContaAcesso, DistribuicaoContato> distribuicoesContatosSelecionadas;

	public DistribuidorContato() {
		limparDistribuicaoContatos();
		limparDistribuicoesSelecionadas();
	}
	
	public void limparDistribuicoesSelecionadas() {
		distribuicoesContatosSelecionadas = new HashMap<>();
	}

	private void limparDistribuicaoContatos() {
		distribuicoesContatos = Collections.synchronizedList(new ArrayList<>());
	}
	
	public synchronized DistribuicaoContato obterContato(ContaAcesso contaAcessoSolicitante) {
		ContaAcesso operador = contaAcessoRepositorio.obter(contaAcessoSolicitante.getId());
		if (operador.possuiNivelOperadorAtivo()) {
			DistribuicaoContato distribuicaoContatoEmAtuacao = distribuicaoContatoRepositorio
					.obterEmAtuacao(contaAcessoSolicitante);
			if (distribuicaoContatoEmAtuacao == null) {
				int numeroMaximoTentativas = 0;
				distribuicoesContatosSelecionadas.remove(operador);
				while (numeroMaximoTentativas < NUMERO_MAXIMO_TENTATIVAS_SELECAO_DISTRIBUICAO_CONTATO) {
					List<DistribuicaoContato> distribuicaoContatos = obterDistribuicoes(operador);
					if (!distribuicaoContatos.isEmpty()) {
						DistribuicaoContato distribuicaoContato = distribuicaoContatos
								.get(new Random().nextInt(distribuicaoContatos.size()));
						if (!distribuicoesContatosSelecionadas.containsValue(distribuicaoContato)) {
							distribuicaoContato.inicializarAtuacaoNoContato(contaAcessoSolicitante);
							DistribuicaoContato distribuicaoContatoSalva = distribuicaoContatoRepositorio.salvar(distribuicaoContato);
							distribuicoesContatosSelecionadas.put(operador, distribuicaoContatoSalva);
							return distribuicaoContatoSalva;
						} else {
							numeroMaximoTentativas++;
						}
					} else {
						return null;
					}
				}
			} else {
				return distribuicaoContatoEmAtuacao;
			}
		}
		return null;
	}
	
	public List<DistribuicaoContato> obterDistribuicoes(ContaAcesso operador) {
		return distribuicoesContatos.stream().filter(distribuicao -> distribuicao.possuiOperador(operador))
				.collect(Collectors.toList());
	}

	public synchronized void distribuirContatos() {
		try {
			limparDistribuicaoContatos();
			for (Campanha campanhaAtiva : campanhaRepositorio.obterTodosAtivos()) {
				for (Criterio criterio : campanhaAtiva.getCriterios()) {
					if (criterio.eCriterioDeBairro()) {
						Long quantidadePaginas = contatoRepositorio.obterNumeroPaginasNovosPor(criterio.getMunicipio(),
								criterio.getBairro());
						for (int pagina = 1; pagina <= quantidadePaginas; pagina++) {
							for (Contato contato : contatoRepositorio.obterNovosPor(criterio.getMunicipio(),
									criterio.getBairro(), pagina)) {
								for (AlocacaoOperador alocacaoOperador : campanhaAtiva.getAlocacoesOperadores()) {
									if (alocacaoOperador.getContaAcesso().possuiNivelOperadorAtivo()) {
										distribuicoesContatos.add(new DistribuicaoContato(campanhaAtiva, contato,
												alocacaoOperador.getContaAcesso()));
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.fatal("Erro durante a distribuição de contatos. \nDetalhes: " + e);
		}
	}
}

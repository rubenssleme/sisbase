package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.atendimento.AcaoConduta;

@Repository
public class RepositorioAcaoCondutaBD extends RepositorioDB<AcaoConduta> implements RepositorioAcaoConduta {

	@Override
	public List<AcaoConduta> obterAcaoCondutasNaoProcessadas() {
		List<AcaoConduta> acoesCondutasNaoProcessadas = new ArrayList<>();

		TypedQuery<AcaoConduta> query = em.createQuery("SELECT s FROM AcaoConduta s WHERE s.dataProcessamento is NULL",
				AcaoConduta.class);
		try {
			acoesCondutasNaoProcessadas = query.getResultList();
		} catch (Exception e) {
			logger.error("N�o foi poss�vel obter a lista de A��es de conduta n�o processadas. \nDetalhe:" + e);
		}
		return acoesCondutasNaoProcessadas;
	}

	@Transactional
	public AcaoConduta salvar(AcaoConduta acaoConduta) {
		String acao;
		try {
			if (acaoConduta.getId() == null) {
				em.persist(acaoConduta);
				acao = "Inclus�o";
			} else {
				em.merge(acaoConduta);
				acao = "Altera��o";
			}
			logger.info(acao + " do " + acaoConduta + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + acaoConduta + ". \nDetalhes: " + e);
		}
		return acaoConduta;
	}
}

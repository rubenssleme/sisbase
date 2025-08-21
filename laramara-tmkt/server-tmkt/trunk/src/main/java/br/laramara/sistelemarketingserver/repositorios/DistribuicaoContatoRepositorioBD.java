package br.laramara.sistelemarketingserver.repositorios;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.contato.EventoContato;
import br.laramara.sistelemarketingserver.dominio.contato.EventoTelefonia;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;

@Repository
public class DistribuicaoContatoRepositorioBD extends RepositorioDB<DistribuicaoContato>
		implements DistribuicaoContatoRepositorio {

	@Transactional
	public DistribuicaoContato salvar(DistribuicaoContato distriuicaoContato) {
		DistribuicaoContato distribuicaoContatoSalvo = null;
		try {
			distribuicaoContatoSalvo = em.merge(distriuicaoContato);
			logger.info("Edição do " + distribuicaoContatoSalvo + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error(
					"Ocorreu algum erro durante o armazenamento do " + distribuicaoContatoSalvo + ". \nDetalhes: " + e);
		}
		return distribuicaoContatoSalvo;
	}

	@Override
	public DistribuicaoContato obterEmAtuacao(ContaAcesso contaAcesso) {
		DistribuicaoContato resultado = null;
		Query query = em.createQuery("SELECT d FROM DistribuicaoContato d INNER JOIN d.eventosDistribuicoesContatos eve"
				+ " WHERE d.operador = :operador AND eve.evento = :evento AND eve.dataFinalVigencia is NULL"
				+ " ORDER BY d.id DESC",
				DistribuicaoContato.class).setMaxResults(1);
		query.setParameter("operador", contaAcesso);
		query.setParameter("evento", EventoContato.ATUACAO_EM_CONTATO_INICIADA);
		try {
			resultado = (DistribuicaoContato) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhuma distribuição de contato foi obtida a partir da " + contaAcesso);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de distribuição de contato. \nDetalhes: " + e);
		}
		return resultado;
	}

	@Override
	public DistribuicaoContato obterDistribuicaoMaisRecentePorEvento(EventoTelefonia eventoTelefonia) {
		DistribuicaoContato resultado = null;
		Query query = em.createQuery(
				"SELECT d FROM DistribuicaoContato d INNER JOIN d.contato.telefones tel" 
				+ " WHERE tel.ddd = :ddd AND tel.telefone = :telefone AND d.operador.ramal = :ramal"
				+ " ORDER BY d.id DESC",
				DistribuicaoContato.class).setMaxResults(1);
		query.setParameter("ddd", eventoTelefonia.getDdd());
		query.setParameter("telefone", eventoTelefonia.getTelefone());
		query.setParameter("ramal", eventoTelefonia.getRamal());
		try {
			resultado = (DistribuicaoContato) query.getSingleResult();
		} catch (NoResultException ex) {
			logger.info("Nenhuma distribuição de contato foi obtida a partir da " + eventoTelefonia);
		} catch (Exception e) {
			logger.error("Ocorreu um erro durante a consulta de distribuição de contato. \nDetalhes: " + e);
		}
		return resultado;
	}
}

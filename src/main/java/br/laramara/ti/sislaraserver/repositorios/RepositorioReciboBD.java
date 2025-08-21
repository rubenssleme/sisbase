package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.doacao.Filial;
import br.laramara.ti.sislaraserver.dominio.doacao.Recibo;

@Repository
public class RepositorioReciboBD extends RepositorioDB<Recibo> implements RepositorioRecibo{

	@Transactional
	public Recibo salvar(Recibo reciboASalvar) {
		String acao = "";
		try {
			if (reciboASalvar.getId() == null) {
				em.persist(reciboASalvar);
				acao = "Inclusão";
			} else {
				em.merge(reciboASalvar);
				acao = "Alteração";
			}
			logger.info(acao + " do " + reciboASalvar + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + reciboASalvar + ". \nDetalhes: " + e);
		}
		return reciboASalvar;
	}
	

	@Override
	public List<Recibo> obterTodos() {
		List<Recibo> recibos = new ArrayList<>();

		TypedQuery<Recibo> query = em.createQuery(
				"SELECT b FROM Recibo b ORDER BY b.id DESC",
				Recibo.class);
		try {
			recibos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Recibos. \nDetalhe:"
					+ e);
		}
		return recibos;
	}

	@Override
	public Recibo obterMaisRecentePorCpfCnpj(String cpfCnpj) {
		List<Recibo> recibos = obterPorCpfCnpj(cpfCnpj);
		return !recibos.isEmpty() ? recibos.get(0) : null;
	}
	
	public List<Recibo> obterTodosPorCpfCnpj(String cpfCnpj) {
		List<Recibo> recibos = obterPorCpfCnpj(cpfCnpj);
		return recibos;
	}

	private List<Recibo> obterPorCpfCnpj(String cpfCnpj) {

		List<Recibo> recibos = new ArrayList<>();

		TypedQuery<Recibo> query = em.createQuery(
				"SELECT p FROM Recibo p WHERE p.cpfCnpj like :cpfCnpj ORDER BY id DESC", Recibo.class);
		query.setParameter("cpfCnpj", cpfCnpj.trim());
		try {
			recibos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de recibos. \nDetalhe:" + e);
		}
		return recibos;
	}

	@Override
	public List<Recibo> obterPorFilial(String filial) {
		List<Recibo> recibos = new ArrayList<>();

		TypedQuery<Recibo> query = em.createQuery(
				"SELECT p FROM Recibo p WHERE p.filial = :filial ORDER BY id DESC", Recibo.class);
		query.setParameter("filial", new Filial(Long.valueOf(filial.trim())));
		try {
			recibos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de recibos. \nDetalhe:" + e);
		}
		return recibos;
	}
}

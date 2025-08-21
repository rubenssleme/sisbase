package br.laramara.ti.sislaraserver.repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaraserver.dominio.inscricao.Inscricao;

@Repository
public class RepositorioInscricaoBD extends RepositorioDB<Inscricao> implements RepositorioInscricao {

	@Transactional
	public synchronized Inscricao salvar(Inscricao inscricao) {
		String acao;
		try {
			if (inscricao.getId() == null) {
				em.persist(inscricao);
				acao = "Inclusão";
			} else {
				em.merge(inscricao);
				acao = "Alteração";
			}
			logger.info(acao + " do " + inscricao.toString() + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento do " + inscricao.toString() + ". \nDetalhes: "
					+ e);
		}

		return inscricao;
	}

}

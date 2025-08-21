package br.laramara.ti.sislaraserver.repositorios;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;

@Repository
public class RepositorioInformacaoEssencialBD extends RepositorioDB<InformacaoEssencial> implements
		RepositorioInformacaoEssencial {

	public InformacaoEssencial obterPorId(Long id) {
		InformacaoEssencial informacaoEssencial = null;
		try {
			informacaoEssencial = em.find(InformacaoEssencial.class, id);
		} catch (Exception e) {
			logger.error("Não foi possível obter a Informação Essencial com o id "
					+ id + ".\n Detalhe:" + e);
		}
		return informacaoEssencial;
	}
}

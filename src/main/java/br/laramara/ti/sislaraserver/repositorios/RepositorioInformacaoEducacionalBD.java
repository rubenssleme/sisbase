package br.laramara.ti.sislaraserver.repositorios;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;

@Repository
public class RepositorioInformacaoEducacionalBD extends RepositorioDB<InformacaoEducacional> implements
		RepositorioInformacaoEducacional {

	public InformacaoEducacional obterPorId(Long id) {
		InformacaoEducacional informacaoEducacional = null;
		try {
			informacaoEducacional = em.find(InformacaoEducacional.class, id);
		} catch (Exception e) {
			logger.error("N�o foi poss�vel obter a Informa��o Educacional com o id "
					+ id + ".\n Detalhe:" + e);
		}
		return informacaoEducacional;
	}
}

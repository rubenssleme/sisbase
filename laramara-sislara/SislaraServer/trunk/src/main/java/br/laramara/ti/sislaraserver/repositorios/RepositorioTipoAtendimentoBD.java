package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.laramara.ti.sislaraserver.dominio.grupo.DescricaoTipoAtendimento;
import br.laramara.ti.sislaraserver.dominio.grupo.Modulo;
import br.laramara.ti.sislaraserver.dominio.grupo.TipoAtendimento;

@Repository
public class RepositorioTipoAtendimentoBD extends RepositorioDB<TipoAtendimento> implements
		RepositorioTipoAtendimento {

	@Override
	public List<TipoAtendimento> obterTodos() {
		List<TipoAtendimento> tiposAtendimentos = new ArrayList<>();

		TypedQuery<TipoAtendimento> query = em.createQuery(
				"SELECT s FROM TipoAtendimento s WHERE s.inativo = false ORDER BY s.nome",
				TipoAtendimento.class);
		try {
			tiposAtendimentos = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de Tipos de Atendimentos. \nDetalhe:"
					+ e);
		}
		return tiposAtendimentos;
	}

	@Override
	public DescricaoTipoAtendimento obterDescricaoTipoAtendimentoServicoSocial() {
		return obterDescricaoTipoAtendimento(DescricaoTipoAtendimento.ID_SERVICO_SOCIAL);
	}
	
	private DescricaoTipoAtendimento obterDescricaoTipoAtendimento(Long id) {
		DescricaoTipoAtendimento descricaoTipoAtendimento = null;
		if (descricaoTipoAtendimento == null) {
			List<TipoAtendimento> tiposAtendimento = obterTodos();
			for (TipoAtendimento tipoAtendimento : tiposAtendimento) {
				for (DescricaoTipoAtendimento descricaoTipoAtendimentoExistente : tipoAtendimento
						.getDescricoesTipoAtendimentoAtivos()) {
					if (descricaoTipoAtendimentoExistente.getId().equals(id)) {
						descricaoTipoAtendimento = descricaoTipoAtendimentoExistente;
					}
				}
			}
		}
		return descricaoTipoAtendimento;
	}
	
	public Modulo obterModuloExcessoDeFalta() {
		return obterModulo(obterDescricaoTipoAtendimentoServicoSocial(),
				Modulo.ID_MODULO_EXCESSO_FALTA);
	}
	
	private Modulo obterModulo(DescricaoTipoAtendimento descricaoTipoAtendimento, Long idModulo) {
		if (descricaoTipoAtendimento.possuiModulos()) {
			for (Modulo modulo : descricaoTipoAtendimento.getModulos()) {
				if (modulo.getId().equals(idModulo)) {
					return modulo;
				}
			}
		}
		return null;
	}

	@Override
	public DescricaoTipoAtendimento obterDescricaoAvaliacaoDeServicoEmOftalmologia() {
		return obterDescricaoTipoAtendimento(DescricaoTipoAtendimento.ID_AVALIACAO_SERVICO_ATENCAO_ESPECIALIZADA_OFTALMOLOGIA);
	}

	@Override
	public Modulo obterModuloAtendimentoEspecificoEspecialidado() {
		return obterModulo(obterDescricaoAvaliacaoDeServicoEmOftalmologia(),
				Modulo.ID_MODULO_ATENDIMENTO_ESPECIFICO_ESPECIALIZADO);
	}

	@Override
	public Modulo obterModuloTriagemOftalmologica() {
		return obterModulo(obterDescricaoAvaliacaoDeServicoEmOftalmologia(),
				Modulo.ID_MODULO_TRIAGEM_OFTALMOLOGICA);
	}

	@Override
	public Modulo obterAvaliacaoETriagem() {
		return obterModulo(obterDescricaoTipoAtendimentoServicoSocial(), Modulo.ID_MODULO_AVALIACAO_TRIAGEM);
	}

	@Override
	public DescricaoTipoAtendimento obterDescricaoTipoAvaliacaoFuncional() {
		return obterDescricaoTipoAtendimento(DescricaoTipoAtendimento.ID_AVALIACAO_FUNCIONAL);
	}
}

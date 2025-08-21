package br.laramara.ti.sislaraserver.repositorios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.laramara.ti.sislaracommons.utilitarios.MaquinaTempo;
import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.TipoPendencia;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

@Repository
public class RepositorioPendenciaBD extends RepositorioDB<Pendencia> implements RepositorioPendencia {

	private static final String CODICIONAL_NAO_RESOLVIDA_E_TIPO_DE_PENDENCIA = "p.resolvida = false AND p.tipoPendencia IN :tipoPendencia";
	
	private void atribuirParametroProfissional(TypedQuery<Pendencia> query, Profissional profissional){
		query.setParameter("profissional", profissional);
	}
	
	private void atribuirParametroTipoPendencia(TypedQuery<Pendencia> query, List<TipoPendencia> tipoPendencia){
		query.setParameter("tipoPendencia", tipoPendencia);
	}
	
	private void atribuirParametroUsuario(TypedQuery<Pendencia> query, Usuario usuario){
		query.setParameter("prontuario",  usuario.getId());
	}
	
	@Override
	public List<Pendencia> obterPendenciasComDataPassadaOuNulaPor(Profissional profissional) {
		List<Pendencia> pendencias = new ArrayList<>();
		try {
			TypedQuery<Pendencia> query = em.createQuery(
					"SELECT p FROM Pendencia p " 
					+ "JOIN p.profissionaisAfetados a " 
					+ "WHERE a IN :profissional AND p.resolvida = false AND (p.data <= :dataAtual OR p.data IS NULL)"
					+ "ORDER BY p.id DESC", Pendencia.class);
			atribuirParametroProfissional(query, profissional);
			query.setParameter("dataAtual", MaquinaTempo.obterInstancia().obterCalendarioAtual().getTime(), TemporalType.DATE);
			pendencias = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de pendencias por profissional. \nDetalhe:" + e);
		}
		return pendencias;
	}

	@Override
	public List<Pendencia> obterTodasNaoResolvidas(List<TipoPendencia> tipoPendencia) {
		List<Pendencia> pendencias = new ArrayList<>();
		
		try {
			TypedQuery<Pendencia> query = em.createQuery(
					"SELECT p FROM Pendencia p WHERE " + CODICIONAL_NAO_RESOLVIDA_E_TIPO_DE_PENDENCIA, Pendencia.class);
			atribuirParametroTipoPendencia(query, tipoPendencia);
			pendencias = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de pendencias nao resolvidas. \nDetalhe:" + e);
		}
		return pendencias; 
	}
	
	private boolean possuiPendenciaDeReuniaoDeIntegracaoNaoResolvidaPorUsuario(List<TipoPendencia> tipoPendencia, Usuario usuario) {
		Long total = null;
		try {
			TypedQuery<Long> query = em.createQuery(
					"SELECT count(p) FROM Pendencia p WHERE p.prontuario = :prontuario AND " + CODICIONAL_NAO_RESOLVIDA_E_TIPO_DE_PENDENCIA, Long.class);
			query.setParameter("tipoPendencia", tipoPendencia);
			query.setParameter("prontuario", usuario.getId());
			total = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de pendencias nao resolvidas. \nDetalhe:" + e);
		}
		return maiorQueZero(total);
	}
	
	@Override
	public List<Pendencia> obterTodasNaoResolvidas(List<TipoPendencia> tipoPendencia, Profissional profissional) {
		List<Pendencia> pendencias = new ArrayList<>();
		try {
			TypedQuery<Pendencia> query = em.createQuery(
					"SELECT p FROM Pendencia p " 
					+ "JOIN p.profissionaisAfetados a " 
					+ "WHERE a IN :profissional AND " + CODICIONAL_NAO_RESOLVIDA_E_TIPO_DE_PENDENCIA, Pendencia.class);
			atribuirParametroTipoPendencia(query, tipoPendencia);
			atribuirParametroProfissional(query, profissional);
			pendencias = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de pendencias nao resolvidas. \nDetalhe:" + e);
		}
		return pendencias; 
	}
	
	@Override
	public List<Pendencia> obterTodasPendenciasDeExcessoDeFaltasNaoResolvidasPor(Usuario usuario) {
		List<Pendencia> pendencias = new ArrayList<>();
		try {
			TypedQuery<Pendencia> query = em.createQuery(
					"SELECT p FROM Pendencia p WHERE p.resolvida = false and p.prontuario = :prontuario", Pendencia.class);
			atribuirParametroUsuario(query, usuario);
			pendencias = query.getResultList();
		} catch (Exception e) {
			logger.error("Não foi possível obter a lista de pendencias nao resolvidas. \nDetalhe:" + e);
		}
		return pendencias;
	}

	@Transactional
	public void salvar(Pendencia pendencia) {
		String acao;
		try {
			if (pendencia.getId() == null) {
				em.persist(pendencia);
				acao = "Inclusão";
			} else {
				em.merge(pendencia);
				acao = "Alteração";
			}
			logger.info(acao + " do " + pendencia + " realizada com sucesso.");
		} catch (Exception e) {
			logger.error("Ocorreu algum erro durante o armazenamento da " + pendencia + ". \nDetalhes: " + e);
		}
	}
	
	@Override
	public Pendencia obterPorId(Long id){
		Pendencia pendencia = null;
		try {
			TypedQuery<Pendencia> query = em.createQuery(
					"SELECT p FROM Pendencia p WHERE p.id = :idPendencia", Pendencia.class);
			query.setParameter("idPendencia", id);
			pendencia = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Não foi possível obter a pendencia. \nDetalhe:" + e);
		}
		return pendencia;
	}

	@Override
	public boolean possuiPendenciaDeReuniaoDeIntegracaoNaoResolvidaPorUsuario(Usuario usuario) {
		return possuiPendenciaDeReuniaoDeIntegracaoNaoResolvidaPorUsuario(Arrays.asList(TipoPendencia.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO,
				TipoPendencia.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO), usuario);
	}

	@Override
	public boolean jaExistiuPendenciaDeReunicaoDeIntegracao(ModuloPeriodo moduloPeriodoSolicitante, Usuario usuario) {
		Long total = null;
		try {
			TypedQuery<Long> query = em.createQuery(
					"SELECT count(p) FROM Pendencia p WHERE p.prontuario = :prontuario AND p.moduloPeriodo = :moduloPeriodo AND p.resolvida = TRUE",
					Long.class);
			query.setParameter("prontuario", usuario.getId());
			query.setParameter("moduloPeriodo", moduloPeriodoSolicitante);
			total = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Não foi possível obter a pendencia. \nDetalhe:" + e);
		}
		return maiorQueZero(total);
	}

	private boolean maiorQueZero(Long total) {
		return total != null && total.longValue() > 0;
	}
}

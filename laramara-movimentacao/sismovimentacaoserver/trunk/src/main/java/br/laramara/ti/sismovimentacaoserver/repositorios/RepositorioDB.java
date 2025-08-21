package br.laramara.ti.sismovimentacaoserver.repositorios;

import java.lang.reflect.ParameterizedType;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

public abstract class RepositorioDB<DOMINIO> {

	protected final Logger logger;
	protected static final String CONDICIONAL_JUNCAO_USUARIO_PRE_CADASTRO_COM_HISTORICO = " LEFT JOIN a.usuario u LEFT JOIN a.preCadastro p LEFT JOIN a.historicoStatus h LEFT JOIN p.informacaoEssencial.historicoRg rgp LEFT JOIN u.informacaoEssencial.historicoRg rgu WHERE";
	protected static final String CONDICIONAL_DESCRICAO_TIPO_ATENDIMENTO = " a.descricaoTipoAtendimento = :descricaoTipoAtendimento";
	protected static final String CONDICIONAL_DATA_INICIO_E_TERMINO = " a.data BETWEEN :dataInicio AND :dataTermino";
	protected static final String CONDICIONAL_DATA_INICIO = " a.data = :data";
	protected static final String CONDICIONAL_MODULO = " a.modulo = :modulo";
	protected static final String CONDICIONAL_PRONTUARIO = " u.id = :prontuario";
	protected static final String CONDICIONAL_RG_PRE_CADASTRO = " ((LOWER(rgp.rg) LIKE LOWER(:rg) AND rgp.dataFinalVigencia IS NULL) or (LOWER(rgu.rg) LIKE LOWER(:rg) AND rgu.dataFinalVigencia IS NULL))";
	
	@PersistenceContext
	protected EntityManager em;
	
	public RepositorioDB() {
		this.logger = Logger.getLogger(getClass());
	}

	protected Long obterValorIdentificador(String nomeIdentificador) {
		Query e = em.createNativeQuery("SELECT currval('" + nomeIdentificador
				+ "')");
		Long identificador = Long.valueOf(e.getSingleResult().toString());
		return identificador;
	}

	protected boolean naoEstaVazio(String texto) {
		return texto != null && !texto.isEmpty();
	}

	protected boolean possuiMaisDeTresCaracteres(String texto) {
		return texto != null && texto.length() > 3;
	}

	@SuppressWarnings("unchecked")
	private Class<DOMINIO> obterEntidade() {
		return ((Class<DOMINIO>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	private String obterNomeEntidade() {
		return obterEntidade().getSimpleName();
	}

	public DOMINIO obterPorId(Long id) {
		DOMINIO objeto = null;
		if (id != null) {
			try {
				objeto = (DOMINIO) em.find(obterEntidade(), id);
			} catch (Exception e) {
				logger.error("Não foi possível obter o " + obterNomeEntidade()
						+ " com o id " + id + ".\n Detalhe:" + e);
			}
		}
		return objeto;
	}

	@SuppressWarnings("unchecked")
	protected TypedQuery<DOMINIO> obterQuery(
			ComandoSQLEParametros comandoSQLEParametros) {
		TypedQuery<DOMINIO> query = (TypedQuery<DOMINIO>) em
				.createQuery(comandoSQLEParametros.getComando());

		for (Entry<String, Object> entry : comandoSQLEParametros
				.getParametros().entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}
}

package br.laramara.sistelemarketingserver.repositorios;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public abstract class RepositorioDB<DOMINIO> {

	protected final Logger logger;
	protected static final String CONDICIONAL_JUNCAO_USUARIO = " LEFT JOIN a.usuario u";
	protected static final String CONDICIONAL = " WHERE";
	protected static final String CONDICIONAL_JUNCAO_USUARIO_PRE_CADASTRO_COM_HISTORICO = CONDICIONAL_JUNCAO_USUARIO
			+ " LEFT JOIN a.preCadastro p LEFT JOIN a.historicoStatus h LEFT JOIN p.informacaoEssencial.historicoRg rgp LEFT JOIN u.informacaoEssencial.historicoRg rgu"
			+ " LEFT JOIN u.informacaoEssencial iu"
			+ " LEFT JOIN p.informacaoEssencial ip"
			+ CONDICIONAL;
	protected static final String CONDICIONAL_DESCRICAO_TIPO_ATENDIMENTO = " a.descricaoTipoAtendimento = :descricaoTipoAtendimento";
	protected static final String CONDICIONAL_DATA_INICIO_E_TERMINO = " a.data BETWEEN :dataInicio AND :dataTermino";
	protected static final String CONDICIONAL_DATA = " a.data = :data";
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
}

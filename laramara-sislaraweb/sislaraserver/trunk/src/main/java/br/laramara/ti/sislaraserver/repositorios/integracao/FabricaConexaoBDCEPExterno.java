package br.laramara.ti.sislaraserver.repositorios.integracao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.utilitarios.Registro;

@Component
public class FabricaConexaoBDCEPExterno {
	
	private static final Logger logger = Logger
			.getLogger(FabricaConexaoBDCEPExterno.class);

	private FabricaConexaoBDCEPExterno() {
	}

	public static final SqlSessionFactory getObterSessao() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			if (sqlSessionFactory == null) {
				sqlSessionFactory = (SqlSessionFactory) Registro
						.obterSqlSessionFactoryBeanCEP().getObject();
				if (!sqlSessionFactory.getConfiguration().hasMapper(
						CEPMapper.class)) {
					sqlSessionFactory.getConfiguration().addMapper(
							CEPMapper.class);
				}
			}
		} catch (Exception e) {
			logger.fatal("Erro durante obten��o de conex�o. Detalhes: " + e);
		}
		return sqlSessionFactory;
	}
}

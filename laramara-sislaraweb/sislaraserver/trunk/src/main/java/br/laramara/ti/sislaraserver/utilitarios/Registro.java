package br.laramara.ti.sislaraserver.utilitarios;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.laramara.ti.sislaracommons.servicos.ServicoSisLaraServer;
import br.laramara.ti.sislaraserver.AplicacaoSisLaraServer;
import br.laramara.ti.sislaraserver.dominio.contribuicao.AutomatizadorContribuicao;
import br.laramara.ti.sislaraserver.dominio.pendencia.AutomatizadorPendencia;
import br.laramara.ti.sislaraserver.dominio.seguranca.CoordenadorEdicaoGeral;
import br.laramara.ti.sislaraserver.repositorios.QualificadorRepositorioProfissionalSistema;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAcaoConduta;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAgendamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAreaFormacao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioArquivo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioAtendimentoIndividual;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBeneficio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioBloqueio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCargo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCid;
import br.laramara.ti.sislaraserver.repositorios.RepositorioComprometimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioCondicaoMoradia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContaAcesso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioContribuinte;
import br.laramara.ti.sislaraserver.repositorios.RepositorioConvenio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDeficiencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDemanda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDiretoriaEnsino;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDoenca;
import br.laramara.ti.sislaraserver.repositorios.RepositorioDreCefai;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEmpresa;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEscolaridade;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEspera;
import br.laramara.ti.sislaraserver.repositorios.RepositorioEstadoCivil;
import br.laramara.ti.sislaraserver.repositorios.RepositorioExpectativa;
import br.laramara.ti.sislaraserver.repositorios.RepositorioFamiliar;
import br.laramara.ti.sislaraserver.repositorios.RepositorioFilial;
import br.laramara.ti.sislaraserver.repositorios.RepositorioFoto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioGrupo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioHabitacao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInformacaoEducacional;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInformacaoEssencial;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInfraestruturaBasica;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInscricao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioInstituicao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioItemCusto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioLocalAtendimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioLocalTrabalho;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMotivoCancelamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMotivoDesativacao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioMunicipio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioNecessidade;
import br.laramara.ti.sislaraserver.repositorios.RepositorioOrigemEncaminhamento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPais;
import br.laramara.ti.sislaraserver.repositorios.RepositorioParentesco;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPendencia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPerfil;
import br.laramara.ti.sislaraserver.repositorios.RepositorioPreCadastro;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProfissional;
import br.laramara.ti.sislaraserver.repositorios.RepositorioProjeto;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecibo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecurso;
import br.laramara.ti.sislaraserver.repositorios.RepositorioRecursoMoradia;
import br.laramara.ti.sislaraserver.repositorios.RepositorioServico;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSislara;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSituacaoGuarda;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSituacaoHabitacional;
import br.laramara.ti.sislaraserver.repositorios.RepositorioSolicitacaoRelatorio;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipificacaoServico;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoAtendimento;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoConstrucao;
import br.laramara.ti.sislaraserver.repositorios.RepositorioTipoVinculo;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuario;
import br.laramara.ti.sislaraserver.repositorios.RepositorioUsuarioExterno;
import br.laramara.ti.sislaraserver.repositorios.RepositorioVulnerabilidade;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheEspera;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheGrupo;
import br.laramara.ti.sislaraserver.repositorios.cache.CacheInstituicao;
import br.laramara.ti.sislaraserver.utilitarios.tarefas.AutomatizadorTarefas;

public class Registro {
	
	public static final String NOME_DATA_SOURCE_SISLARA = "dataSourceSisLara";
	
	private static ApplicationContext contexto;
	
	public static final RepositorioUsuarioExterno obterRepositorioUsuarioExterno() {
		inicializarContexto();
		return (RepositorioUsuarioExterno) (contexto.getBean(RepositorioUsuarioExterno.class));
	}
	
	public static final RepositorioInscricao obterRepositorioInscricao() {
		inicializarContexto();
		return (RepositorioInscricao) (contexto.getBean(RepositorioInscricao.class));
	}
	
	public static final RepositorioUsuario obterRepositorioUsuario() {
		inicializarContexto();
		return (RepositorioUsuario) (contexto.getBean(RepositorioUsuario.class));
	}
	
	public static RepositorioAreaFormacao obterRepositorioAreaFormacao() {
		inicializarContexto();
		return (RepositorioAreaFormacao) (contexto
				.getBean(RepositorioAreaFormacao.class));
	}

	public static RepositorioBloqueio obterRepositorioBloqueio() {
		inicializarContexto();
		return (RepositorioBloqueio) (contexto
				.getBean(RepositorioBloqueio.class));
	}
	
	public static final RepositorioMunicipio obterRepositorioMunicipio() {
		inicializarContexto();
		return (RepositorioMunicipio) (contexto
				.getBean(RepositorioMunicipio.class));
	}
	
	public static RepositorioRecibo obterRepositorioRecibo() {
		inicializarContexto();
		return (RepositorioRecibo) (contexto
				.getBean(RepositorioRecibo.class));
	}

	public static final RepositorioContaAcesso obterRepositorioContaAcesso() {
		inicializarContexto();
		return (RepositorioContaAcesso) (contexto
				.getBean(RepositorioContaAcesso.class));
	}
	
	public static final ServicoSisLaraServer obterServicoSisLaraServer() {
		inicializarContexto();
		return (ServicoSisLaraServer) (contexto
				.getBean(ServicoSisLaraServer.class));
	}
	
	public static RepositorioAtendimentoIndividual obterRepositorioAtendimentoIndividual() {
		inicializarContexto();
		return (RepositorioAtendimentoIndividual) (contexto
				.getBean(RepositorioAtendimentoIndividual.class));
	}

	public static RepositorioFoto obterRepositorioFoto() {
		inicializarContexto();
		return (RepositorioFoto) (contexto.getBean(RepositorioFoto.class));
	}
	
	public static final RepositorioLocalAtendimento obterRepositorioLocalAtendimento() {
		inicializarContexto();
		return (RepositorioLocalAtendimento) (contexto
				.getBean(RepositorioLocalAtendimento.class));
	}

	public static final RepositorioGrupo obterRepositorioGrupo() {
		inicializarContexto();
		return (RepositorioGrupo) (contexto.getBean(RepositorioGrupo.class));
	}

	public static final RepositorioInstituicao obterRepositorioInstituicao() {
		inicializarContexto();
		return (RepositorioInstituicao) (contexto
				.getBean(RepositorioInstituicao.class));
	}

	public static final RepositorioPais obterRepositorioPais() {
		inicializarContexto();
		return (RepositorioPais) (contexto.getBean(RepositorioPais.class));
	}

	public static final RepositorioEscolaridade obterRepositorioEscolaridade() {
		inicializarContexto();
		return (RepositorioEscolaridade) (contexto
				.getBean(RepositorioEscolaridade.class));
	}
	
	public static RepositorioPerfil obterRepositorioPerfil() {
		inicializarContexto();
		return (RepositorioPerfil) (contexto
				.getBean(RepositorioPerfil.class));
	}
	
	public static void inicialiarCaches() {
		((CacheGrupo) (contexto.getBean(CacheGrupo.class))).inicializar();
		((CacheEspera) (contexto.getBean(CacheEspera.class))).inicializar();
		((CacheInstituicao) (contexto.getBean(CacheInstituicao.class))).inicializar();
	}

	public static final RepositorioPreCadastro obterRepositorioPreCadastro() {
		inicializarContexto();
		return (RepositorioPreCadastro) (contexto
				.getBean(RepositorioPreCadastro.class));
	}

	public static RepositorioServico obterRepositorioServico() {
		inicializarContexto();
		return (RepositorioServico) (contexto.getBean(RepositorioServico.class));
	}
	
	public static final RepositorioParentesco obterRepositorioParentesco() {
		inicializarContexto();
		return (RepositorioParentesco) (contexto
				.getBean(RepositorioParentesco.class));
	}

	public static final RepositorioSituacaoGuarda obterRepositorioSituacaoGuarda() {
		inicializarContexto();
		return (RepositorioSituacaoGuarda) (contexto
				.getBean(RepositorioSituacaoGuarda.class));
	}

	public static final RepositorioBeneficio obterRepositorioBeneficio() {
		inicializarContexto();
		return (RepositorioBeneficio) (contexto
				.getBean(RepositorioBeneficio.class));
	}

	public static final RepositorioConvenio obterRepositorioConvenio() {
		inicializarContexto();
		return (RepositorioConvenio) (contexto
				.getBean(RepositorioConvenio.class));
	}
	
	public static final RepositorioDeficiencia obterRepositorioDeficiencia() {
		inicializarContexto();
		return (RepositorioDeficiencia) (contexto
				.getBean(RepositorioDeficiencia.class));
	}
	
	public static RepositorioDoenca obterRepositorioDoenca() {
		inicializarContexto();
		return (RepositorioDoenca) (contexto.getBean(RepositorioDoenca.class));
	}

	public static final RepositorioInformacaoEssencial obterRepositorioInformacaoEssencial() {
		inicializarContexto();
		return (RepositorioInformacaoEssencial) (contexto
				.getBean(RepositorioInformacaoEssencial.class));
	}

	public static RepositorioAgendamento obterRepositorioAgendamento() {
		inicializarContexto();
		return (RepositorioAgendamento) (contexto
				.getBean(RepositorioAgendamento.class));
	}

	public static final RepositorioInformacaoEducacional obterRepositorioInformacaoEscolar() {
		inicializarContexto();
		return (RepositorioInformacaoEducacional) (contexto
				.getBean(RepositorioInformacaoEducacional.class));
	}
	
	public static RepositorioVulnerabilidade obterRepositorioVulnerabilidade() {
		inicializarContexto();
		return (RepositorioVulnerabilidade) (contexto
				.getBean(RepositorioVulnerabilidade.class));
	}

	public static final RepositorioFamiliar obterRepositorioFamiliar() {
		inicializarContexto();
		return (RepositorioFamiliar) (contexto
				.getBean(RepositorioFamiliar.class));
	}

	public static final RepositorioContribuinte obterRepositorioContribuinte() {
		inicializarContexto();
		return (RepositorioContribuinte) (contexto
				.getBean(RepositorioContribuinte.class));
	}

	public static final RepositorioEstadoCivil obterRepositorioEstadoCivil() {
		inicializarContexto();
		return (RepositorioEstadoCivil) (contexto
				.getBean(RepositorioEstadoCivil.class));
	}

	public static final RepositorioDreCefai obterRepositorioDreCefai() {
		inicializarContexto();
		return (RepositorioDreCefai) (contexto
				.getBean(RepositorioDreCefai.class));
	}

	public static final RepositorioDiretoriaEnsino obterRepositorioDiretoriaEnsino() {
		inicializarContexto();
		return (RepositorioDiretoriaEnsino) (contexto
				.getBean(RepositorioDiretoriaEnsino.class));
	}

	public static final RepositorioProfissional obterRepositorioProfissional() {
		inicializarContexto();
		return (RepositorioProfissional) obterObjetoComAnotacao(QualificadorRepositorioProfissionalSistema.class);
	}
	
	public static final RepositorioTipoAtendimento obterRepositorioTipoAtendimento() {
		inicializarContexto();
		return (RepositorioTipoAtendimento) (contexto
				.getBean(RepositorioTipoAtendimento.class));
	}
	
	public static final RepositorioMotivoCancelamento obterRepositorioMotivoCancelamento() {
		inicializarContexto();
		return (RepositorioMotivoCancelamento) (contexto
				.getBean(RepositorioMotivoCancelamento.class));
	}
	
	public static final RepositorioEspera obterRepositorioEspera() {
		inicializarContexto();
		return (RepositorioEspera) (contexto.getBean(RepositorioEspera.class));
	}

	public static RepositorioTipificacaoServico obterRepositorioTipificacaoServico() {
		inicializarContexto();
		return (RepositorioTipificacaoServico) (contexto
				.getBean(RepositorioTipificacaoServico.class));
	}
	
	public static RepositorioRecurso obterRepositorioRecurso() {
		inicializarContexto();
		return (RepositorioRecurso) (contexto
				.getBean(RepositorioRecurso.class));
	}
	
	public static RepositorioDemanda obterRepositorioDemanda() {
		inicializarContexto();
		return (RepositorioDemanda) (contexto.getBean(RepositorioDemanda.class));
	}
	
	public static RepositorioProjeto obterRepositorioProjeto() {
		inicializarContexto();
		return (RepositorioProjeto) (contexto.getBean(RepositorioProjeto.class));
	}
	
	public static RepositorioTipoVinculo obterRepositorioTipoVinculo() {
		inicializarContexto();
		return (RepositorioTipoVinculo) (contexto.getBean(RepositorioTipoVinculo.class));
	}
	
	public static RepositorioLocalTrabalho obterRepositorioLocalTrabalho() {
		inicializarContexto();
		return (RepositorioLocalTrabalho)(contexto.getBean(RepositorioLocalTrabalho.class));
	}
	
	public static RepositorioCargo obterRepositorioCargo() {
		inicializarContexto();
		return (RepositorioCargo)(contexto.getBean(RepositorioCargo.class));
	}
	
	public static RepositorioOrigemEncaminhamento obterRepositorioOrigemEncaminhamento() {
		inicializarContexto();
		return (RepositorioOrigemEncaminhamento)(contexto.getBean(RepositorioOrigemEncaminhamento.class));
	}

	public static RepositorioComprometimento obterRepositorioComprometimento() {
		inicializarContexto();
		return (RepositorioComprometimento)(contexto.getBean(RepositorioComprometimento.class));
	}

	public static RepositorioHabitacao obterRepositorioHabitacao() {
		inicializarContexto();
		return (RepositorioHabitacao)(contexto.getBean(RepositorioHabitacao.class));
	}
	
	public static RepositorioSislara obterRepositorioSislara() {
		inicializarContexto();
		return (RepositorioSislara) (contexto.getBean(RepositorioSislara.class));
	}
	
	public static RepositorioNecessidade obterRepositorioNecessidade() {
		inicializarContexto();
		return (RepositorioNecessidade) (contexto.getBean(RepositorioNecessidade.class));
	}
	
	public static RepositorioCid obterRepositorioCid() {
		inicializarContexto();
		return (RepositorioCid) (contexto.getBean(RepositorioCid.class));
	}
	
	public static RepositorioRecursoMoradia obterRepositorioRecursoMoradia() {
		inicializarContexto();
		return (RepositorioRecursoMoradia) (contexto.getBean(RepositorioRecursoMoradia.class));
	}

	public static RepositorioPendencia obterRepositorioPendencia() {
		inicializarContexto();
		return (RepositorioPendencia) (contexto.getBean(RepositorioPendencia.class));
	}
	
	public static RepositorioEmpresa obterRepositorioEmpresa() {
		inicializarContexto();
		return (RepositorioEmpresa) (contexto.getBean(RepositorioEmpresa.class));
	}
	
	public static RepositorioItemCusto obterRepositorioItemCusto() {
		inicializarContexto();
		return (RepositorioItemCusto) (contexto.getBean(RepositorioItemCusto.class));
	}

	public static RepositorioExpectativa obterRepositorioExpectativa() {
		inicializarContexto();
		return (RepositorioExpectativa) (contexto.getBean(RepositorioExpectativa.class));
	}

	public static RepositorioSituacaoHabitacional obterRepositorioSituacaoHabitacional() {
		inicializarContexto();
		return (RepositorioSituacaoHabitacional) (contexto.getBean(RepositorioSituacaoHabitacional.class));
	}
	
	public static RepositorioTipoConstrucao obterRepositorioTipoConstrucao() {
		inicializarContexto();
		return (RepositorioTipoConstrucao) (contexto.getBean(RepositorioTipoConstrucao.class));
	}

	public static RepositorioMotivoDesativacao obterRepositorioMotivoDesativacao() {
		inicializarContexto();
		return (RepositorioMotivoDesativacao) (contexto.getBean(RepositorioMotivoDesativacao.class));
	}

	public static AutomatizadorContribuicao obterAutomatizadorContribuicao() {
		inicializarContexto();
		return (AutomatizadorContribuicao) (contexto.getBean(AutomatizadorContribuicao.class));
	}
	
	public static RepositorioSolicitacaoRelatorio obterRepositorioSolicitacaoRelatorio() {
		inicializarContexto();
		return (RepositorioSolicitacaoRelatorio) (contexto
				.getBean(RepositorioSolicitacaoRelatorio.class));
	}

	public static RepositorioFilial obterRepositorioFilial() {
		inicializarContexto();
		return (RepositorioFilial) (contexto
				.getBean(RepositorioFilial.class));
	}
	
	public static RepositorioCondicaoMoradia obterRepositorioCondicaoMoradia() {
		inicializarContexto();
		return (RepositorioCondicaoMoradia) (contexto.getBean(RepositorioCondicaoMoradia.class));
	}
	
	public static RepositorioArquivo obterRepositorioArquivo() {
		inicializarContexto();
		return (RepositorioArquivo) (contexto
				.getBean(RepositorioArquivo.class));
	}
	

	public static CoordenadorEdicaoGeral obterCoordenadorEdicaoGeral() {
		inicializarContexto();
		return (CoordenadorEdicaoGeral) (contexto.getBean(CoordenadorEdicaoGeral.class));
	}

	public static RepositorioAcaoConduta obterRepositorioAcaoConduta() {
		inicializarContexto();
		return (RepositorioAcaoConduta) (contexto.getBean(RepositorioAcaoConduta.class));
	}
	
	public static RepositorioInfraestruturaBasica obterRepositorioSaneamentoBasico() {
		inicializarContexto();
		return (RepositorioInfraestruturaBasica) (contexto.getBean(RepositorioInfraestruturaBasica.class));
	}
	
	public static AutomatizadorTarefas obterAutomatizadorTarefas() {
		inicializarContexto();
		return (AutomatizadorTarefas) (contexto.getBean(AutomatizadorTarefas.class));
	}
	
	public static AutomatizadorPendencia obterAutomatizadorPendencia() {
		inicializarContexto();
		return (AutomatizadorPendencia) (contexto.getBean(AutomatizadorPendencia.class));
	}
	
	public static final CacheGrupo obterCacheGrupo() {
		return (CacheGrupo) (contexto.getBean(CacheGrupo.class));
	}

	public static CacheInstituicao obterCacheInstituicao() {
		return (CacheInstituicao) (contexto.getBean(CacheInstituicao.class));
	}
	
	public static CacheEspera obterCacheEspera() {
		return (CacheEspera) (contexto.getBean(CacheEspera.class));
	}
	
	public static final SqlSessionFactoryBean obterSqlSessionFactoryBeanOracleRH() {
		return obterFabricaSessaoPorNome("fabricaOracleRH");
	}
	
	public static SqlSessionFactoryBean obterSqlSessionFactoryBeanCEP() {
		return obterFabricaSessaoPorNome("fabricaCEP");
	}
	
	public static SqlSessionFactoryBean obterSqlSessionFactoryBeanSislara() {
		return obterFabricaSessaoPorNome("fabricaSislara");
	}
		
	public static final EntityManagerFactory  obterEntityManagerFactory () {
		return (EntityManagerFactory ) (contexto
				.getBean(EntityManagerFactory.class));
	}
	
	public static final DataSource obterDataSourceSisLara() {
		inicializarContexto();
		return (DataSource) (contexto.getBean(NOME_DATA_SOURCE_SISLARA));
	}
	
	public static final DataSource obterDataSourceSisLaraLegado() {
		inicializarContexto();
		return (DataSource) (contexto.getBean("dataSourceSisLaraLegado"));
	}
	
	public static void inicializarContexto() {
		if (contexto == null) {
			contexto = (ApplicationContext) new SpringApplication(AplicacaoSisLaraServer.class).run();
		}
	}
	
	public static void setContexto(ApplicationContext contexto) {
		Registro.contexto = contexto;
	}

	public static void finalizarContexto() {
		((AbstractApplicationContext) contexto).close();
	}
	
	private static final SqlSessionFactoryBean obterFabricaSessaoPorNome(
			String nome) {
		SqlSessionFactoryBean retorno = null;
		Map<String, SqlSessionFactoryBean> fabricasSessao = contexto
				.getBeansOfType(SqlSessionFactoryBean.class);
		for (String fabricaSessao : fabricasSessao.keySet()) {
			String chaveFabrica = (String) fabricaSessao.subSequence(1,
					fabricaSessao.length());
			if (nome.equals(chaveFabrica)) {
				retorno = fabricasSessao.get(fabricaSessao);
			}
		}
		return retorno;
	}
	
	@SuppressWarnings("rawtypes")
	private static final Object obterObjetoComAnotacao(Class anotacao) {
		Object retorno = null;

		@SuppressWarnings("unchecked")
		Map<String, Object> objetosComAnotacao = contexto
				.getBeansWithAnnotation(anotacao);

		if (objetosComAnotacao.size() == 1) {
			for (Object objetoComAnotacao : objetosComAnotacao.values()) {
				retorno = objetoComAnotacao;
			}
		}
		return retorno;
	}
}

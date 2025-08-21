package br.laramara.ti.sislaraserver.dominio.seguranca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import br.laramara.ti.sislaraserver.utilitarios.Configuracao;

public abstract class Armazenavel<CHAVE, VALOR> implements
		Bloqueador<CHAVE, VALOR> {

	protected final Logger logger;

	private Map<CHAVE, VALOR> objetosArmazenados;

	public Armazenavel() {
		this.logger = Logger.getLogger(getClass());
		this.objetosArmazenados = inicializarSessoes();
	}

	public synchronized void desbloquearTodos() {
		objetosArmazenados.clear();
		gravar(objetosArmazenados);
		logger.info("Desbloqueio de todos os objetos do executado.");
	}

	public synchronized void bloquear(CHAVE chave, VALOR contaAcesso) {
		if (chave != null) {
			armazenar(chave, contaAcesso);
			logger.info(contaAcesso.toString() + " bloqueou o " + chave + " para edição.");
		}
	}

	public synchronized void desbloquearChave(CHAVE chave,
			ContaAcesso contaAcesso) {
		logger.info("Solicitação de desbloqueio pelo " + contaAcesso + " do " + chave + ".");
		if (chave != null) {
			atualizar(chave, Operacao.REMOVER);
			logger.info(contaAcesso + " desbloqueou o " + chave + ".");
		}
	}

	protected synchronized void armazenar(CHAVE chave, VALOR valor) {
		for (Entry<CHAVE, VALOR> chaveValorExistente : objetosArmazenados
				.entrySet()) {
			if (valor.equals(chaveValorExistente.getValue())) {
				atualizar(chaveValorExistente.getKey(), Operacao.REMOVER);
			}
		}
		atualizar(chave, valor, Operacao.INCLUIR);
	}

	private synchronized void atualizar(CHAVE chave, Operacao operacao) {
		atualizar(chave, null, operacao);
	}
	private synchronized void atualizar(CHAVE chave, VALOR valor, Operacao operacao) {
		if (operacao.equals(Operacao.REMOVER)){
			objetosArmazenados.remove(chave);
		}else if(operacao.equals(Operacao.INCLUIR)){
			objetosArmazenados.put(chave, valor);
			
		}
		gravar(objetosArmazenados);
	}

	private synchronized void gravar(Map<CHAVE, VALOR> objetos) {
		try {
			File arquivoChaveValor = new File(
					new Configuracao().obterConfiguracao(obterNomeArquivo()));
			FileOutputStream f = new FileOutputStream(arquivoChaveValor);

			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(objetos);
			s.flush();
			s.close();
		} catch (Exception e) {
			logger.fatal("Erro durante gravação de sessões existentes.\n Detalhes:"
					+ e);
		}
	}

	protected synchronized void desbloquearValor(
			VALOR contaAcessoParaDesbloquear) {
		if (contaAcessoParaDesbloquear != null) {
			for (CHAVE chave : objetosArmazenados.keySet()) {
				if (objetosArmazenados.get(chave).equals(
						contaAcessoParaDesbloquear)) {
					atualizar(chave, Operacao.REMOVER);
					logger.info("Desbloqueando "+ chave + " em edição pela "
							+ contaAcessoParaDesbloquear);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Map<CHAVE, VALOR> inicializarSessoes() {
		Map<CHAVE, VALOR> mapeamentoChaveValor = new ConcurrentHashMap<>();
		try {
			FileInputStream arquivoChaveValor = new FileInputStream(new File(
					new Configuracao().obterConfiguracao(obterNomeArquivo())));
			ObjectInputStream streamArquivo = new ObjectInputStream(
					arquivoChaveValor);
			mapeamentoChaveValor = (ConcurrentHashMap<CHAVE, VALOR>) streamArquivo
					.readObject();
			streamArquivo.close();
		} catch (FileNotFoundException fe) {
			logger.warn("Arquivo " + obterNomeArquivo()
					+ " não encontrado. Um novo arquivo será criado.");
		} catch (Exception e) {
			logger.fatal("Erro durante restauração de arquivo "
					+ obterNomeArquivo() + ".\n Detalhes:" + e);
		}
		return mapeamentoChaveValor;
	}

	public synchronized boolean estaBloqueadoParaEdicao(CHAVE chave) {
		return (chave != null) ? objetosArmazenados.containsKey(chave) : false;
	}

	public synchronized Map<CHAVE, VALOR> obterObjetosBloqueados() {
		return objetosArmazenados;
	}

	public synchronized VALOR obterContaAcessoEditando(CHAVE chave) {
		return objetosArmazenados.get(chave);
	}

	protected abstract String obterNomeArquivo();
}

package br.laramara.ti.sislaraserver.dominio.seguranca;

import org.springframework.stereotype.Component;

import br.laramara.ti.sislaraserver.utilitarios.Configuracao;

@Component
public class CoordenadorEdicaoGeral extends Armazenavel<String, ContaAcesso> {

	@Override
	protected String obterNomeArquivo() {
		return Configuracao.ARQUIVO_COORDENACAO_GERAL;
	}
}

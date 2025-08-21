package br.laramara.sistelemarketingserver.dominio.telefonia;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.repositorios.ContaAcessoRepositorio;

@Service
public class MecanismoTelefonia {
	
	@Inject 
	private ContaAcessoRepositorio contaAcessoRepositorio;
	@Inject
	private PBX pbx;

	public void ligar(Ligacao ligacao) {
		ContaAcesso contaAcesso = contaAcessoRepositorio.obterPorToken(ligacao.getToken());
		pbx.ligar(contaAcesso.getRamal().getNumero(), ligacao.obterTelefoneCompleto());
	}

	public synchronized String obterStatusRamal(String token) {
		ContaAcesso contaAcesso = contaAcessoRepositorio.obterPorToken(token);
		StatusRamal statusRamal = StatusRamal.obter(pbx.obterStatusRamais(contaAcesso.getRamal().getNumero()));
		return statusRamal.obterStatusTelefoniaTraduzido();
	}
}

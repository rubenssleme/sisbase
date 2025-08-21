package br.laramara.sistelemarketingserver.fabrica;

import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoDTO;
import br.laramara.sistelemarketingserver.ContextoContato;
import br.laramara.sistelemarketingserver.dominio.campanha.ContextoCampanha;
import br.laramara.sistelemarketingserver.dominio.contato.DistribuicaoContato;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContextoContaAcesso;

public class ContextoDistriucaoContato {

	public static DistribuicaoContato fabricarDistribuicaoContato() {
		DistribuicaoContato distribuicaoContato = new DistribuicaoContato(ContextoCampanha.fabricar(), ContextoContato.fabricarContato(),
				ContextoContaAcesso.fabricarContaAcessoCarlos());
		distribuicaoContato.setId(new Long(1000));
		return distribuicaoContato;
	}
	
	public static DistribuicaoContatoDTO fabricarDistribuicaoContatoDto() {
		DistribuicaoContatoDTO distribuicaoContatoDto = new DistribuicaoContatoDTO();
		distribuicaoContatoDto.setId(new Long(1000));
		return distribuicaoContatoDto;
	}
}

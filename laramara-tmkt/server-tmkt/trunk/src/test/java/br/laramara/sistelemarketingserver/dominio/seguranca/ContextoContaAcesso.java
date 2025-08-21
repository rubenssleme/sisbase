package br.laramara.sistelemarketingserver.dominio.seguranca;

import br.laramara.sistelemarketingcommons.dtos.seguranca.ContaAcessoDTO;
import br.laramara.sistelemarketingcommons.dtos.seguranca.TurnoDTO;
import br.laramara.sistelemarketingserver.dominio.seguranca.ContaAcesso;
import br.laramara.sistelemarketingserver.dominio.telefonia.Ramal;

public class ContextoContaAcesso {

	public static ContaAcesso fabricarContaAcessoCarlos() {
		ContaAcesso contaAcesso = new ContaAcesso();
		contaAcesso.setId(new Long(1000));
		contaAcesso.setNome("Carlos Eduardo");
		contaAcesso.setLogin("carlos.eduardo");
		contaAcesso.setNivel(ContextoNivel.fabricarNivel());
		contaAcesso.setAtivo(true);
		contaAcesso.setTurno(Turno.MANHA);
		contaAcesso.setToken("81dc9bdb52d04dc20036dbd8313ed055");
		contaAcesso.setRamal(Ramal.RAMAL_6489);
		return contaAcesso;
	}

	public static ContaAcessoDTO fabricar() {
		ContaAcessoDTO contaAcessoDto = new ContaAcessoDTO();
		contaAcessoDto.setId(new Long(1000));
		contaAcessoDto.setNome("Carlos Eduardo");
		contaAcessoDto.setLogin("carlos.eduardo");
		contaAcessoDto.setNivelDto(ContextoNivel.fabricarNivelDto());
		contaAcessoDto.setAtivo(true);
		contaAcessoDto.setTurnoDto(new TurnoDTO(Turno.MANHA.toString()));
		return contaAcessoDto;
	}

	public static ContaAcesso fabricarContaAcessoPaulo() {
		ContaAcesso contaAcesso = fabricarContaAcessoCarlos();
		contaAcesso.setId(new Long(2000));
		contaAcesso.setNome("Paulo Augusto");
		contaAcesso.setLogin("paulo.bandeira");
		return contaAcesso;
	}
	
	public static ContaAcesso fabricarContaAcessoPriscila() {
		ContaAcesso contaAcesso = fabricarContaAcessoCarlos();
		contaAcesso.setId(new Long(3000));
		contaAcesso.setNome("Priscila Bandeira");
		contaAcesso.setLogin("priscila.bandeira");
		return contaAcesso;
	}
	
	public static ContaAcesso fabricarContaAcessoTereza() {
		ContaAcesso contaAcesso = fabricarContaAcessoCarlos();
		contaAcesso.setId(new Long(4000));
		contaAcesso.setNome("Tereza Bandeira");
		contaAcesso.setLogin("tereza.bandeira");
		return contaAcesso;
	}
	
	public static ContaAcesso fabricarContaAcessoMarcos() {
		ContaAcesso contaAcesso = fabricarContaAcessoCarlos();
		contaAcesso.setId(new Long(5000));
		contaAcesso.setNome("Marcos Bandeira");
		contaAcesso.setLogin("marcos.bandeira");
		return contaAcesso;
	}
	
	public static ContaAcesso fabricarContaAcessoRubens() {
		ContaAcesso contaAcesso = fabricarContaAcessoCarlos();
		contaAcesso.setId(new Long(6000));
		contaAcesso.setNome("Rubens Leme");
		contaAcesso.setLogin("rubens.leme");
		return contaAcesso;
	}
}

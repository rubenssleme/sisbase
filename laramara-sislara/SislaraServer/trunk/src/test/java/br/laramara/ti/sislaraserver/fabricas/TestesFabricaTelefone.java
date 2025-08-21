package br.laramara.ti.sislaraserver.fabricas;

import org.testng.Assert;
import org.testng.annotations.Test;

import br.laramara.ti.sislaracommons.dtos.TelefoneDTO;
import br.laramara.ti.sislaracommons.dtos.TipoTelefoneDTO;
import br.laramara.ti.sislaracommons.utilitarios.TiposDeTeste;
import br.laramara.ti.sislaraserver.dominio.Telefone;
import br.laramara.ti.sislaraserver.dominio.TipoTelefone;

public class TestesFabricaTelefone {

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_telefone_converte_objeto_de_dominio_para_dto() {
		TipoTelefone tipoTelefone = TipoTelefone.RESIDENCIAL;
		String textoTelefone = "1122223333";
		Telefone telefone = new Telefone(tipoTelefone, textoTelefone);

		TelefoneDTO telefoneDTO = new FabricaTelefone()
				.converterParaDTO(telefone);

		Assert.assertEquals(telefoneDTO.getDDDTelefone(), textoTelefone);
		Assert.assertEquals(telefoneDTO.getTipoTelefoneDto().toString(),
				tipoTelefone.toString());
	}

	@Test(groups = { TiposDeTeste.UNITARIO })
	public void fabrica_tipotelefone_converte_objeto_de_dto_para_dominio() {
		TipoTelefoneDTO tipoTelefoneEsperado = new TipoTelefoneDTO(
				TipoTelefone.COMERCIAL.toString());
		String textoTelefone = "1122223333";
		TelefoneDTO telefoneDto = new TelefoneDTO(tipoTelefoneEsperado,
				textoTelefone);

		Telefone telefoneObtido = new FabricaTelefone()
				.converterParaDominio(telefoneDto);

		Assert.assertEquals(telefoneObtido.getDDDTelefone(), textoTelefone);
		Assert.assertEquals(telefoneObtido.getTipo().toString(),
				tipoTelefoneEsperado.toString());
	}
}

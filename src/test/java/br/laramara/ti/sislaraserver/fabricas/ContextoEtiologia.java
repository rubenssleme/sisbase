package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.usuario.CidDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EtiologiaDTO;
import br.laramara.ti.sislaraserver.dominio.usuario.Cid;
import br.laramara.ti.sislaraserver.dominio.usuario.Etiologia;

public class ContextoEtiologia {
	
	public static Etiologia construirEtiologia() {
		Long id = new Long(1);
		Cid cid = new Cid("A12", "Teste");
		Etiologia etiologia = new Etiologia();
		etiologia.setId(id);
		etiologia.setCid(cid);
		return etiologia;
	}

	public static EtiologiaDTO construirEtiologiaDTO() {
		Long id = new Long(1);
		CidDTO cid = new CidDTO("A12", "Teste");
		EtiologiaDTO etiologiaDto = new EtiologiaDTO();
		etiologiaDto.setId(id);
		etiologiaDto.setCidDto(cid);
		return etiologiaDto;
	}
}

package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.retirada.RetiradaDTO;
import br.laramara.ti.sislaraserver.dominio.retirada.Retirada;

public class FabricaRetirada extends FabricaRecursiva<RetiradaDTO, Retirada> {

	@Override
	public Retirada converterParaDominio(RetiradaDTO retiradaDto,
			Retirada retirada) {
		if (retiradaDto != null) {
			if (retirada == null) {
				retirada = new Retirada();
			}
			retirada.setProntuario(retiradaDto.getProntuario());
			retirada.setProfissional(new FabricaProfissional()
					.converterParaDominio(retiradaDto.getProfissionalDto()));
			retirada.setVoluntario(new FabricaProfissional()
					.converterParaDominio(retiradaDto.getVoluntarioDto()));
		}
		return retirada;
	}

	public final RetiradaDTO converterParaDTO(Retirada preCadastro) {
		return null;
	}

	@Override
	public Retirada obterNovo() {
		return new Retirada();
	}

}

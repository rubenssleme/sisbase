package br.laramara.ti.sislaraserver.fachadas;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoComunidadeDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoPreCadastroDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoProfissionalDTO;
import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoUsuarioDTO;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorAtendimentoBase;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorAtendimentoComunidade;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorAtendimentoPreCadastro;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorAtendimentoProfissional;
import br.laramara.ti.sislaraserver.fachadas.validadores.ValidadorAtendimentoUsuario;

public class FabricaValidadorAtendimentoBase {
	public ValidadorAtendimentoBase obterValidador(AtendimentoBaseDTO atendimentoBaseDto) {
		if (atendimentoBaseDto instanceof AtendimentoPreCadastroDTO) {
			return new ValidadorAtendimentoPreCadastro();
		} else if (atendimentoBaseDto instanceof AtendimentoUsuarioDTO) {
			return new ValidadorAtendimentoUsuario();
		} else if (atendimentoBaseDto instanceof AtendimentoProfissionalDTO) {
			return new ValidadorAtendimentoProfissional();
		} else if (atendimentoBaseDto instanceof AtendimentoComunidadeDTO) {
			return new ValidadorAtendimentoComunidade();
		} else {
			return null;
		}
	}
}

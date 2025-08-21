package br.laramara.ti.sislaraserver.fachadas.validadores;

import br.laramara.ti.sislaracommons.dtos.atendimento.AtendimentoBaseDTO;
import br.laramara.ti.sislaracommons.dtos.grupo.ResultadoValidacaoAtendimentoBaseDTO;

public abstract class ValidadorAtendimentoBase extends Validador{

	public abstract ResultadoValidacaoAtendimentoBaseDTO validarAtendimento(AtendimentoBaseDTO atendimentoBaseDTO);
}

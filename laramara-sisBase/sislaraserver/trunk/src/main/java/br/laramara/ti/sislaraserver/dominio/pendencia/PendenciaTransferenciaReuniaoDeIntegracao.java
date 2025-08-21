package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@DiscriminatorValue(value = TipoPendencia.DescricaoTipo.TRANSFERENCIA_DE_REUNIAO_DE_INTEGRACAO)
public class PendenciaTransferenciaReuniaoDeIntegracao extends PendenciaReuniaoDeIntegracao{

	public PendenciaTransferenciaReuniaoDeIntegracao() {
	}

	public PendenciaTransferenciaReuniaoDeIntegracao(Long prontuario, ModuloPeriodo moduloPeriodo,
			List<Profissional> profissionaisAfetados) {
		super(prontuario, moduloPeriodo, profissionaisAfetados);
	}

	@Override
	public String obterDescricaoPendencia() {
		return "Reunião de integração do Prontuário " + prontuario + " será transferida para outro grupo disponível.";
	}

	@Override
	public String toString() {
		return "PendenciaTransferenciaReuniaoDeIntegracao [" + super.toString() + "]";
	}
}

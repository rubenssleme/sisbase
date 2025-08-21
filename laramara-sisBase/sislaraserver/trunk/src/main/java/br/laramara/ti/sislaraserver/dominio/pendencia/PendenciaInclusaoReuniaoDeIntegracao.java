package br.laramara.ti.sislaraserver.dominio.pendencia;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;

@Entity
@DiscriminatorValue(value = TipoPendencia.DescricaoTipo.INCLUSAO_DE_REUNIAO_DE_INTEGRACAO)
public class PendenciaInclusaoReuniaoDeIntegracao extends PendenciaReuniaoDeIntegracao {

	public PendenciaInclusaoReuniaoDeIntegracao(){
	}
	
	public PendenciaInclusaoReuniaoDeIntegracao(Long prontuario, ModuloPeriodo moduloPeriodo,
			List<Profissional> profissionaisAfetados) {
		super(prontuario, moduloPeriodo, profissionaisAfetados);
	}
	
	@Override
	public String obterDescricaoPendencia() {
		return "Inclus�o autom�tica do Prontu�rio " + prontuario + " em reuni�o de integra��o ser� efetuada.";
	}

	@Override
	public String toString() {
		return "PendenciaInclusaoReuniaoDeIntegracao [" + super.toString() + "]";
	}
}

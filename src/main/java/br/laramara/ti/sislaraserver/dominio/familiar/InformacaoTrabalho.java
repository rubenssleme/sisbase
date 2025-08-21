package br.laramara.ti.sislaraserver.dominio.familiar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.laramara.ti.sislaracommons.utilitarios.TextoUtils;
import br.laramara.ti.sislaraserver.dominio.endereco.Validavel;

@Entity
@Table(name = "informacao_trabalho")
public class InformacaoTrabalho extends Validavel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(name = "empresa", length = TamanhoMaximoFamiliar.EMPRESA)
	private String empresa;
	
	@Column(name = "funcao", length = TamanhoMaximoFamiliar.FUNCAO)
	private String funcao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa =  TextoUtils.anularVazio(empresa);
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao =  TextoUtils.anularVazio(funcao);
	}

	public void validarTamanhoMaximoDeDados() {
		if (tamanhoMaximoViolado(empresa, TamanhoMaximoFamiliar.EMPRESA)) {
			adicionarErro("Insira o Nome da Empresa de trabalho contendo "
					+ TamanhoMaximoFamiliar.EMPRESA + " dígitos.");
		}
		if (tamanhoMaximoViolado(funcao, TamanhoMaximoFamiliar.FUNCAO)) {
			adicionarErro("Insira a Função de trabalho contendo "
					+ TamanhoMaximoFamiliar.FUNCAO + " dígitos.");
		}
	}
	
	public void validarObrigatoriedadeETamanhoMaximoDeDados() {
		validarObrigatoriedade();
		validarTamanhoMaximoDeDados();
	}
	
	private void validarObrigatoriedade(){
		if (empresa == null || TextoUtils.estaVazio(empresa)) {
			adicionarErro("Insira o Nome da Empresa de trabalho.");
		}
		if (funcao == null || TextoUtils.estaVazio(funcao)) {
			adicionarErro("Insira a Função de trabalho.");
		}
	}
		
	@Override
	public String toString() {
		return "InformaçãoTrabalho [id=" + id + ", empresa=" + empresa
				+ ", funcao=" + funcao + "]";
	}
}

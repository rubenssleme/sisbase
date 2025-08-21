package br.laramara.ti.sislaracommons.dtos.doacao;

import br.laramara.ti.sislaracommons.Identificavel;
import br.laramara.ti.sislaracommons.dtos.ModeloDTO;

public class ReciboDTO extends ModeloDTO implements Identificavel{

	private static final long serialVersionUID = 6526881775584192408L;

	private Long id;

	private String nome;
	
	private String cpfCnpj;
	
	private FilialDTO filialDto;

	private String dataRegistro;
	
	private String data;
	
	private String descricao;
	
	private String motivoDoCancelamento;
		
	private String valorTotalRecibo;
	
	private String historicoOperacoes;
	
	private boolean cancelado;

	public ReciboDTO() {
		super();
	}

	public ReciboDTO(Long id){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public FilialDTO getFilial() {
		return filialDto;
	}

	public void setFilialDTO(FilialDTO filialDto) {
		this.filialDto = filialDto;
	}

	public String getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(String dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMotivoDoCancelamento() {
		return motivoDoCancelamento;
	}

	public void setMotivoDoCancelamento(String motivoDoCancelamento) {
		this.motivoDoCancelamento = motivoDoCancelamento;
	}

	public String getValorTotalRecibo() {
		return valorTotalRecibo;
	}

	public void setValorTotalRecibo(String valorTotalRecibo) {
		this.valorTotalRecibo = valorTotalRecibo;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public String getHistoricoOperacoes() {
		return historicoOperacoes;
	}

	public void setHistoricoOperacoes(String historicoOperacoes) {
		this.historicoOperacoes = historicoOperacoes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReciboDTO other = (ReciboDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

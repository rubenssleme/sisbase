package br.laramara.sistelemarketingcommons.dtos.doacao;

import java.util.ArrayList;
import java.util.List;

import br.laramara.sistelemarketingcommons.dtos.ModeloDTO;
import br.laramara.sistelemarketingcommons.dtos.contato.DistribuicaoContatoDTO;

public class DoacaoDTO extends ModeloDTO {

	private static final long serialVersionUID = 2543298485841529989L;

	private Long id;

	private List<ValorDetalhadoDTO> valoresDetalhadosDto;

	private DistribuicaoContatoDTO distribuicaoContatoDto;

	public DoacaoDTO() {
		valoresDetalhadosDto = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ValorDetalhadoDTO> getValoresDetalhadosDto() {
		return valoresDetalhadosDto;
	}

	public void setValoresDetalhadosDto(List<ValorDetalhadoDTO> valoresDetalhadosDto) {
		this.valoresDetalhadosDto = valoresDetalhadosDto;
	}

	public DistribuicaoContatoDTO getDistribuicaoContatoDto() {
		return distribuicaoContatoDto;
	}

	public void setDistribuicaoContatoDto(DistribuicaoContatoDTO distribuicaoContatoDto) {
		this.distribuicaoContatoDto = distribuicaoContatoDto;
	}

	public void adicionar(ValorDetalhadoDTO valorDetalhadoAAdicionar) {
		if (!valoresDetalhadosDto.contains(valorDetalhadoAAdicionar)) {
			valoresDetalhadosDto.add(valorDetalhadoAAdicionar);
		} else {
			valoresDetalhadosDto.set(valoresDetalhadosDto.indexOf(valorDetalhadoAAdicionar), valorDetalhadoAAdicionar);
		}
	}

	public void removerValorDetalhadoDto(ValorDetalhadoDTO valorDetalhadoSelecionadoDto) {
		valoresDetalhadosDto.remove(valorDetalhadoSelecionadoDto);
	}
}

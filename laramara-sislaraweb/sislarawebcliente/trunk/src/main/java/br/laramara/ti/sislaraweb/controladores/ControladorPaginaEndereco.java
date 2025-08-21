package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoCEPDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.EnderecoDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.MunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.endereco.ResultadoConsultaCEP;
import br.laramara.ti.sislaracommons.dtos.endereco.UfDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemMunicipioDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.ResultadoListagemUfDTO;
import br.laramara.ti.sislaraweb.utilitarios.Mensagem;

@Named
@ViewScoped
public class ControladorPaginaEndereco extends Controlador implements Serializable {

	private static final long serialVersionUID = -2729530028054844265L;

	private String ufSelecionada;
	private String municipioSelecionado;
	private List<String> ufs;
	private List<MunicipioDTO> municipios;

	@PostConstruct
	public void inicializarCombosAuxiliares() {
		carregarEndereco();
		carregarUfs();
		carregarMunicipiosAoMudarUf();
	}

	private void carregarEndereco() {
		if (getEnderecoDto() == null) {
			setEnderecoDto(new EnderecoDTO());
		}
		carregarUfEMunicipioSelecionado();
	}

	private void carregarUfEMunicipioSelecionado() {
		setUfSelecionada(getEnderecoDto().getUfDto() != null ? getEnderecoDto().getUfDto().getUf() : "");
		setMunicipioSelecionado(
				getEnderecoDto().getMunicipioDto() != null ? getEnderecoDto().getMunicipioDto().getNome() : "");
	}

	public void carregarUfs() {
		if (ufs == null) {
			ResultadoListagemUfDTO resultadoListagemUfDTO = servicoSisLaraClient.obterListagemUf();

			if (resultadoListagemUfDTO.sucesso()) {
				ufs = converterDTOParaTexto(resultadoListagemUfDTO.getObjetosDto());
			} else {
				Mensagem.exibirMensagemErro(resultadoListagemUfDTO.getMensagem());
			}
		} else {
			ufs = new ArrayList<>();
		}
	}

	public void carregarMunicipiosAoMudarUf() {
		if (estaComItemSelecionado(getUfSelecionada())) {
			ResultadoListagemMunicipioDTO resultadoListagemMunicipioPorUf = servicoSisLaraClient
					.obterListagemMunicipioPorUf(getUfSelecionada());

			if (resultadoListagemMunicipioPorUf.sucesso()) {
				municipios = resultadoListagemMunicipioPorUf.getObjetosDto();
			} else {
				Mensagem.exibirMensagemErro(resultadoListagemMunicipioPorUf.getMensagem());
			}
		}
	}

	public void consultarEnderecoPorCep() {
		if (!getEnderecoDto().getCep().isEmpty() && getEnderecoDto().getCep().length() == 8) {
			ResultadoConsultaCEP resultadoConsultaCep = servicoSisLaraClient
					.consultarEnderecoPorCep(getEnderecoDto().getCep());

			if (resultadoConsultaCep.sucesso()) {
				EnderecoCEPDTO enderecoCepDto = resultadoConsultaCep.getEnderecoCEPDto();

				getEnderecoDto().setBairro(enderecoCepDto.getBairro());
				getEnderecoDto().setEndereco(enderecoCepDto.getEndereco());
				getEnderecoDto().setUfDto(enderecoCepDto.getUfDto());
				getEnderecoDto().setMunicipioDto(enderecoCepDto.getMunicipioDto());

				setUfSelecionada(getEnderecoDto().getUfDto().getUf());
				setMunicipioSelecionado(getEnderecoDto().getMunicipioDto().getNome());

				carregarMunicipiosAoMudarUf();
			} else {
				Mensagem.exibirMensagemErro(resultadoConsultaCep.getMensagem());
			}
		}
	}

	public String getUfSelecionada() {
		return ufSelecionada;
	}

	public void setUfSelecionada(String ufSelecionada) {
		this.ufSelecionada = ufSelecionada;
	}

	public String getMunicipioSelecionado() {
		return municipioSelecionado;
	}

	public void setMunicipioSelecionado(String municipioSelecionado) {
		this.municipioSelecionado = municipioSelecionado;
	}

	public List<String> getUfs() {
		return ufs;
	}

	public void setUfs(List<String> ufs) {
		this.ufs = ufs;
	}

	public List<MunicipioDTO> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<MunicipioDTO> municipios) {
		this.municipios = municipios;
	}

	private List<String> converterDTOParaTexto(List<UfDTO> ufsDto) {
		List<String> ufs = new ArrayList<>();

		for (UfDTO ufDto : ufsDto) {
			ufs.add(ufDto.getUf());
		}

		return ufs;
	}

}
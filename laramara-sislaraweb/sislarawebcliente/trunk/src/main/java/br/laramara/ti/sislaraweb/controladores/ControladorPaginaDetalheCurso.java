package br.laramara.ti.sislaraweb.controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.laramara.ti.sislaracommons.dtos.evento.DescricaoEventoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.DetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoConsultaDetalheCursoDTO;
import br.laramara.ti.sislaracommons.dtos.evento.ResultadoListagemDetalhesCursoDTO;
import br.laramara.ti.sislaraweb.utilitarios.Mensagem;

@Named
@ViewScoped
public class ControladorPaginaDetalheCurso extends Controlador implements Serializable {

	private static final long serialVersionUID = 8428430205384494586L;

	private List<DetalheCursoDTO> detalhesCursoDtos;
	
	@PostConstruct
	public void inicializar() {
		carregarDetalhesCurso();
	}
	
	private void carregarDetalhesCurso() {
		ResultadoListagemDetalhesCursoDTO resultadoListagemDetalhesCursoDTO = servicoSisLaraClient.obterDetalhesCurso();

		if (resultadoListagemDetalhesCursoDTO.sucesso()) {
			setDetalhesCursoDtos(resultadoListagemDetalhesCursoDTO.getObjetosDto());
		}
	}

	public void obterDetalheCursoPorIdGrupo() {
		if (possuiCursoSelecionado()) {
			ResultadoConsultaDetalheCursoDTO resultadoConsultaDetalheCursoDTO = servicoSisLaraClient
					.obterDetalheCursoPorIdGrupo(getIdGrupoSelecionado());

			if (resultadoConsultaDetalheCursoDTO.sucesso()) {
				setDetalheCursoDto((DetalheCursoDTO) resultadoConsultaDetalheCursoDTO.getDetalheCursoDto());
			}
		}
	}

	public void setDetalhesCursoDtos(List<DetalheCursoDTO> detalhesCursoDtos) {
		this.detalhesCursoDtos = detalhesCursoDtos;
	}

	public List<DetalheCursoDTO> getDetalhesCursoDtos() {
		return detalhesCursoDtos;
	}

	public DescricaoEventoDTO obterDescricaoEmenta(DetalheCursoDTO detalheCursoDto) {
		return detalheCursoDto.getDescricoesEventoDto()
				.stream().filter(descricaoEventoDto -> descricaoEventoDto.getTipoDescricaoEvento()
						.getTipoDescricaoEvento().equals("Descrição / Ementa"))
				.findFirst().orElse(new DescricaoEventoDTO());
	}
	
	public void inscrever(ActionEvent evt) {
		if (possuiCursoSelecionado()) {
			redirecionarParaPaginaInscricoes();
		} else {
			Mensagem.exibirMensagem(MENSAGEM_ERRO_SELECIONE_UM_CURSO);
		}
	}

}
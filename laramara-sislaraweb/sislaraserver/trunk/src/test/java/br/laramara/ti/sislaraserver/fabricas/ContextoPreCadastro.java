package br.laramara.ti.sislaraserver.fabricas;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.precadastro.PreCadastroDTO;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

public class ContextoPreCadastro {
	
	public static PreCadastroDTO construirPreCadastroDTOsemIdentificacao(){
		PreCadastroDTO preCadastroDto = construirPreCadastroDTO();
		preCadastroDto.setId(null);
		return preCadastroDto;
	}
	
	public static PreCadastroDTO construirPreCadastroDTOAlternativo() {
		PreCadastroDTO preCadastroDTO = ContextoPreCadastro.construirPreCadastroDTO();
		preCadastroDTO.setId(new Long(13333));
		return preCadastroDTO;
	}
	public static PreCadastroDTO construirPreCadastroDTO() {
		InformacaoEssencialDTO informacaoEssencialDto = new InformacaoEssencialDTO();
		informacaoEssencialDto.setNome("Josep Meaza");
		informacaoEssencialDto.setRg("198387X");
		informacaoEssencialDto.setCpf("89359859370");
		informacaoEssencialDto.setContatoDto(ContextoContato.construirContatoDTO());
		
		PreCadastroDTO preCadastroDto = new PreCadastroDTO();
		preCadastroDto.setId(new Long(12222));
		preCadastroDto.setInformacaoEssencialDto(informacaoEssencialDto);
		preCadastroDto.setObs("Grande texto de obs.");
		return preCadastroDto;
	}

	public static PreCadastro fabricarPreCadastroComTodosOsDados() {
		InformacaoEssencial informacaoEssencial = new InformacaoEssencial();
		informacaoEssencial.setNome("Josep Paulo Meaza");
		informacaoEssencial.adicionarRg("283273XX");
		informacaoEssencial.setContato(ContextoContato
				.fabricarContatoComTodosOsDados());
		
		PreCadastro preCadastro = new PreCadastro();
		preCadastro.setId(new Long(133333));
		preCadastro.setInformacaoEssencial(informacaoEssencial);
		preCadastro.setObs("Grande texto de observação.");
		return preCadastro;
	}
}

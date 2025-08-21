package br.laramara.ti.sislaraserver.fabricas;

import java.util.ArrayList;
import java.util.List;

import br.laramara.ti.sislaracommons.dtos.InformacaoEssencialDTO;
import br.laramara.ti.sislaracommons.dtos.escola.InformacaoEducacionalDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.FamiliarDTO;
import br.laramara.ti.sislaracommons.dtos.familiar.ParentescoDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.EstadoCivilDTO;
import br.laramara.ti.sislaracommons.dtos.usuario.StatusDTO;
import br.laramara.ti.sislaraserver.dominio.Contato;
import br.laramara.ti.sislaraserver.dominio.InformacaoEssencial;
import br.laramara.ti.sislaraserver.dominio.escola.InformacaoEducacional;
import br.laramara.ti.sislaraserver.dominio.familiar.Familiar;
import br.laramara.ti.sislaraserver.dominio.familiar.InformacaoTrabalho;
import br.laramara.ti.sislaraserver.dominio.familiar.Parentesco;
import br.laramara.ti.sislaraserver.dominio.usuario.EstadoCivil;
import br.laramara.ti.sislaraserver.dominio.usuario.Status;

public class ContextoFamiliar {

	public static Familiar fabricarFamiliarComTodosOsDados() {
		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.setEmpresa("CTIS");
		informacaoTrabalho.setFuncao("Analista de Sistemas");

		Contato contato = ContextoContato.fabricarContatoComTodosOsDados();

		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial
				.fabricarInformacaoEssencialComTodosOsDados();
		informacaoEssencial.setNome("Paulo Augusto Bandeira");
		informacaoEssencial.adicionarRg("9823749X");
		informacaoEssencial.setContato(contato);

		List<InformacaoEducacional> informacoesEscolares = new ArrayList<>();
		informacoesEscolares.add(ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados());

		Familiar familiar = new Familiar();
		familiar.setInformacaoEssencial(informacaoEssencial);
		familiar.setParentesco(new Parentesco(new Long(1), "Pai"));
		familiar.setCpf("11111111111");
		familiar.setDataNascimento("27/07/1982");
		familiar.setEstadoCivil(new EstadoCivil(new Long(1), "CASADO"));
		familiar.setInformacaoTrabalho(informacaoTrabalho);
		familiar.adicionarInformacoesEducacionais(informacoesEscolares);
		familiar.setRenda("1,00");
		familiar.setPrincipalResponsavel(true);
		familiar.setStatus(Status.FALECIDO);
		return familiar;
	}

	public static Familiar fabricarFamiliarResponsavelApenasComDadosObrigatorios() {
		InformacaoTrabalho informacaoTrabalho = new InformacaoTrabalho();
		informacaoTrabalho.setEmpresa("CTIS");
		informacaoTrabalho.setFuncao("Analista de Sistemas");

		List<InformacaoEducacional> informacoesEscolares = new ArrayList<>();
		informacoesEscolares.add(ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados());
		
		Contato contato = ContextoContato.fabricarContatoComTodosOsDados();

		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial.fabricarInformacaoEssencialComTodosOsDados();
		informacaoEssencial.setNome("Paulo Augusto Bandeira");
		informacaoEssencial.adicionarRg("9823749X");
		informacaoEssencial.setContato(contato);
		
		Familiar familiar = new Familiar();
		familiar.setInformacaoEssencial(informacaoEssencial);
		familiar.setParentesco(new Parentesco(new Long(1), "Pai"));
		familiar.setDataNascimento("27/07/1982");
		familiar.setEstadoCivil(new EstadoCivil(new Long(1), "CASADO"));
		familiar.setInformacaoTrabalho(informacaoTrabalho);
		familiar.adicionarInformacoesEducacionais(informacoesEscolares);
		familiar.setRenda("32,00");
		familiar.setPrincipalResponsavel(true);
		return familiar;
	}

	public static Familiar fabricarFamiliarNaoResponsavelApenasComDadosObrigatorios() {
		
		List<InformacaoEducacional> informacoesEscolares = new ArrayList<>();
		informacoesEscolares.add(ContextoInformacaoEducacional
				.fabricarInformacaoEscolarComTodosOsDados());

		InformacaoEssencial informacaoEssencial = ContextoInformacaoEssencial.fabricarInformacaoEssencialComTodosOsDados();
		informacaoEssencial.setNome("Paulo Augusto Bandeira");
		
		Familiar familiar = new Familiar();
		familiar.setInformacaoEssencial(informacaoEssencial);
		familiar.setParentesco(new Parentesco(new Long(1), "Pai"));
		familiar.setEstadoCivil(new EstadoCivil(new Long(1), "CASADO"));
		familiar.setNaoAlfabetizado(true);
		familiar.adicionarInformacoesEducacionais(informacoesEscolares);
		familiar.setRenda("12,50");
		familiar.setPrincipalResponsavel(false);
		return familiar;
	}
	
	public static FamiliarDTO construirFamiliarDTO() {
		InformacaoEssencialDTO informacaoEssencialDTO = new InformacaoEssencialDTO();
		informacaoEssencialDTO.setNome("Josep Meaza Siqueira");
		informacaoEssencialDTO.setRg("423423SS");
		informacaoEssencialDTO.setContatoDto(ContextoContato.construirContatoDTO());
		
		List<InformacaoEducacionalDTO> informacoesEducacionalDto = new ArrayList<>();
		informacoesEducacionalDto.add(ContextoInformacaoEducacional
				.construirInformacaoEducacionalDTO());
		
		FamiliarDTO familiarDto = new FamiliarDTO();
		familiarDto.setId(new Long(12222));
		familiarDto.setInformacaoEssencialDto(informacaoEssencialDTO);
		familiarDto.setParentescoDto(new ParentescoDTO(new Long(1), "PAI"));
		familiarDto.setCpf("11111111111");
		familiarDto.setDataNascimento("27/07/1982");
		familiarDto.setEstadoCivilDto(new EstadoCivilDTO(new Long(1), "SOLTEIRO"));
		familiarDto.setInformacaoTrabalhoDto(ContextoInformacaoTrabalho
				.construirInformacaoTrabalhoDTO());
		familiarDto.setNaoAlfabetizado(true);
		familiarDto.setInformacoesEducacionaisDto(informacoesEducacionalDto);
		familiarDto.setRenda("12,34");
		familiarDto.setPrincipalResponsavel(true);
		familiarDto.setStatusDto(new StatusDTO("FALECIDO"));

		return familiarDto;
	}
	
	public static FamiliarDTO construirFamiliarDTOSemIdentificacao() {
		FamiliarDTO retorno = construirFamiliarDTO();
		retorno.setId(null);
		return retorno;
	}
}

package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.precadastro.PreCadastro;

public interface RepositorioPreCadastro {
	public void salvar(PreCadastro preCadastroASalvar) throws RgDuplicadoException;

	public List<PreCadastro> pesquisarPorNome(String nome);
	
	public List<PreCadastro> pesquisarPorRG(String rg);
	
	public List<PreCadastro> pesquisarPorCpf(String cpf);
	
	public PreCadastro obterPorId(Long id);
}

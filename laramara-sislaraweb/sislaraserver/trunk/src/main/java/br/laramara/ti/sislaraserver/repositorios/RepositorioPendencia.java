package br.laramara.ti.sislaraserver.repositorios;

import java.util.List;

import br.laramara.ti.sislaraserver.dominio.grupo.ModuloPeriodo;
import br.laramara.ti.sislaraserver.dominio.grupo.Profissional;
import br.laramara.ti.sislaraserver.dominio.pendencia.Pendencia;
import br.laramara.ti.sislaraserver.dominio.pendencia.TipoPendencia;
import br.laramara.ti.sislaraserver.dominio.usuario.Usuario;

public interface RepositorioPendencia {
	public void salvar(Pendencia pendencia);
	public Pendencia obterPorId(Long id);
	public List<Pendencia> obterPendenciasComDataPassadaOuNulaPor(Profissional profissional);
	public List<Pendencia> obterTodasPendenciasDeExcessoDeFaltasNaoResolvidasPor(Usuario usuario); 
	public List<Pendencia> obterTodasNaoResolvidas(List<TipoPendencia> tipoPendencia);
	public List<Pendencia> obterTodasNaoResolvidas(List<TipoPendencia> tipoPendencia, Profissional profissional);
	public List<Pendencia> obterTodasNaoResolvidasComDataPassada(List<TipoPendencia> tipoPendencia, Profissional profissional);
	public List<Pendencia> obterTodasNaoResolvidasDeGrupoComDataPassada(List<TipoPendencia> tipoPendencia,
			ModuloPeriodo moduloPeriodo, Profissional profissional);
	public boolean possuiPendenciaDeReuniaoDeIntegracaoNaoResolvidaPorUsuario(Usuario usuario);
	public boolean jaExistiuPendenciaDeReunicaoDeIntegracao(ModuloPeriodo moduloPeriodoSolicitante, Usuario usuario);
}

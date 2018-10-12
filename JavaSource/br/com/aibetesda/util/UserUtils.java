package br.com.aibetesda.util;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.aibetesda.enums.Permissoes;
import br.com.aibetesda.modelos.Usuario;

public class UserUtils {

	public static Usuario getUsuarioLogado()
	{
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public static boolean isAdmin()
	{
		Usuario logado = getUsuarioLogado();
		return logado.getPapel().equalsIgnoreCase(Permissoes.ADMINISTRADOR.name());
	}
	
	public static boolean isAcessor()
	{
		return getUsuarioLogado().getPapel().equalsIgnoreCase(Permissoes.ASSESSOR.name());
	}
}

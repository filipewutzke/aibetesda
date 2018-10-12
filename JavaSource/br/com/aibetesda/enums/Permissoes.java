package br.com.aibetesda.enums;

public enum Permissoes {

	ASSESSOR("ROLE_ASSESSOR"),
	ADMINISTRADOR("ROLE_ADMINISTRADOR");
	
	private String papel;

	private Permissoes(String papel) {
		this.papel = papel;
	}
	
	public String getPapel() {
		return papel;
	}
}

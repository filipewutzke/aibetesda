package br.com.aibetesda.enums;

public enum StatusAluno {

	INATIVO("Inativo", 0),
	ATIVO("Ativo", 1),
	FORMADO("Formado", 2),
	DESISTENTE("Desistente", 3);
	
	private String status;
	private int codigo;

	private StatusAluno(String str, int cod){
		this.status = str;
		this.codigo = cod;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	@Override
	public String toString() {	
		return String.valueOf(codigo);
	}
	
	public static StatusAluno getByCodigo(int codigo){
		switch (codigo) {
		case 0:
			return INATIVO;
		case 1:
			return ATIVO;
		case 2:
			return FORMADO;
		case 3:
			return DESISTENTE;
		default:
			return null;
		}
	}
	
	public static StatusAluno getByString(String status){
		for(StatusAluno stats : values())
			if(stats.getStatus().equals(status))
				return stats;
		return null;
	}
}

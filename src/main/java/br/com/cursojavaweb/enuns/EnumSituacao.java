package br.com.cursojavaweb.enuns;

public enum EnumSituacao {
	
	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String descricao;
	
	EnumSituacao(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}

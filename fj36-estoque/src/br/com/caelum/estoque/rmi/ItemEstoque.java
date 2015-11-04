package br.com.caelum.estoque.rmi;

import java.io.Serializable;

public class ItemEstoque implements Serializable {

	private static final long serialVersionUID = -8648799748985969989L;
	private String codigo;
	private Integer quantidade;

	public ItemEstoque(String string, int i) {
		codigo = string;
		quantidade = i;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}

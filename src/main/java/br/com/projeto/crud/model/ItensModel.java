package br.com.projeto.crud.model;

import java.io.Serializable;

import br.com.projeto.crud.utils.Utils;
import lombok.Getter;
import lombok.Setter;

public class ItensModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Getter @Setter int id;
	private @Getter @Setter String name;

	@Override
	public String toString() {
		return Utils.toJson(this);
	}

	public ItensModel id(int id) {
		this.id = id;
		return this;
	}

	public ItensModel name(String name) {
		this.name = name;
		return this;
	}
}
package br.com.projeto.crud.model.DTO;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ItensRequestDTO implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Atributo {name} não pode ser nulo")
	private @Getter @Setter String name;

	@Override
	public ItensRequestDTO clone() {
		try {
			return (ItensRequestDTO) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError("Clonagem não suportada", e);
		}
	}
}
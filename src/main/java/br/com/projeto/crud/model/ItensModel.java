package br.com.projeto.crud.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ItensModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private ObjectMapper OBJ_MAPPER = new ObjectMapper();

	private @Getter @Setter int id;
	private @Getter @Setter String name;

	@Override
	public String toString() {
		try {
			return OBJ_MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "## JSON-ERROR ##";
		}
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
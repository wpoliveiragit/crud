package br.com.projeto.crud.model.DTO;

import java.io.Serializable;

import br.com.projeto.crud.utils.Utils;
import lombok.Getter;
import lombok.Setter;

public class UserRequestUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Getter @Setter String passwaord;

	public UserRequestUpdateDTO passwaord(String passwaord) {
		this.passwaord = passwaord;
		return this;
	}

	@Override
	public String toString() {
		return Utils.toJson(this);
	}

}

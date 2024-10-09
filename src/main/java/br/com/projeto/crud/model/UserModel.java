package br.com.projeto.crud.model;

import java.io.Serializable;

import br.com.projeto.crud.utils.Utils;
import lombok.Getter;
import lombok.Setter;

public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Getter @Setter String login;
	private @Getter @Setter String passwaord;

	public UserModel login(String login) {
		this.login = login;
		return this;
	}

	public UserModel passwaord(String passwaord) {
		this.passwaord = passwaord;
		return this;
	}

	@Override
	public String toString() {
		return Utils.toJson(this);
	}

}

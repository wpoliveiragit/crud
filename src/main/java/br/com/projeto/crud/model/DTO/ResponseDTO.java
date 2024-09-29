package br.com.projeto.crud.model.DTO;

import java.io.Serializable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

public class ResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int STATIS_FAILURE = -1;
	public static final int STATIS_SUCCESS = 0;

	private @Getter @Setter String timestamp;
	private @Getter @Setter int status;
	private @Getter @Setter String message;
	private @Getter @Setter Object data;

	public ResponseDTO(int status, String message, Object data) {
		this.timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(System.currentTimeMillis()));
		this.status = status;
		this.message = message;
		this.data = data;
	}
}

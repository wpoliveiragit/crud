package br.com.projeto.crud.model.DTO;

public class ResponseGenerator {

	private static final String RESPONSE_GET = "Linha encontrado com sucesso!";
	private static final String RESPONSE_GET_LIST = "Linhas encontrados com sucesso!";
	private static final String RESPONSE_UPDATE = "Linha atualizado com sucesso!";
	private static final String RESPONSE_CREATE = "Linha criado com sucesso!";

	// -- PUBLIC SUCCESS
	public static final ResponseDTO responseGet(Object data) {
		return responseSuccess(data, RESPONSE_GET);
	}

	public static final ResponseDTO responseGetList(Object data) {
		return responseSuccess(data, RESPONSE_GET_LIST);
	}

	public static final ResponseDTO responseUpdate(Object data) {
		return responseSuccess(data, RESPONSE_UPDATE);
	}

	public static final ResponseDTO responseCreate(Object data) {
		return responseSuccess(data, RESPONSE_CREATE);
	}

	// -- PUBLIC FAILURE
	public static final ResponseDTO responseFail(Object errors, String message) {
		return new ResponseDTO(ResponseDTO.STATIS_FAILURE, message, errors);
	}

	// -- PRIVATE
	private static final ResponseDTO responseSuccess(Object data, String message) {
		return new ResponseDTO(ResponseDTO.STATIS_SUCCESS, message, data);
	}

}

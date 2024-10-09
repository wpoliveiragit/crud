package br.com.projeto.crud.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {

	private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

	public static final String format25(String label) {
		return String.format("%-25s", label);
	}

	public static final Logger createLoggerSize30(Class<?> c) {
		return LoggerFactory.getLogger(String.format("%-30s", c.getSimpleName()));
	}

	public static String toJson(Object obj) {
		try {
			return OBJ_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return "## JSON-ERROR ##";
		}
	}

	/**
	 * Converte String em um Map
	 * 
	 * <pre>
	 * <b>### EXEMPLO 1 ###</b>
	 * {@code
	 * String input = "a=1,b=2,c=3";
	 * Map<String, Integer> map = stringToMap(//
	 * 		input, ",", "=", Function.identity(), Integer::valueOf);
	 * }
	 * <b>RESULTADO:</b> {a=1, b=2, c=3}
	 * 
	 * 
	 * <b>### EXEMPLO 2 ###</b>
	 * {@code
	 * String input = "nome=John,id=123";
	 * Map<String, Object> result = stringToMap(//
	 * 		input, ",", "=", Function.identity(), value -> //
	 * 		"id".equals(value) ? Integer.valueOf(value) : value);
	 * }
	 * <b>RESULTADO:</b> {nome=John, id=123}
	 * </pre>
	 *
	 * @param <K>         Tipo da chave do Map.
	 * @param <V>         Tipo do valor do Map.
	 * @param txtMap      String do A ser convertida no Map.
	 * @param splitArray  Separador ente um chave/valor e chave/valor.
	 * @param splitMap    Separado ente a chave e o valor.
	 * @param keyMapper   Função que converte a chave(String) no tipo K.
	 * @param valueMapper Função que converte o valor(String) no tipo V.
	 * @return Um map<K,V> contendo o map da string.
	 */
	public static <K, V> Map<K, V> stringToMap(String txtMap, String splitArray, String splitMap,
			Function<String, K> keyMapper, Function<String, V> valueMapper) {

		return Arrays.stream(txtMap.split(splitArray)).map(s -> s.split(splitMap))//
				.collect(Collectors.toMap(//
						s -> keyMapper.apply(s[0]), //
						s -> valueMapper.apply(s[1])//
				));
	}

}

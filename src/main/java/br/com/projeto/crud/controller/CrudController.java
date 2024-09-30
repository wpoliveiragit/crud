package br.com.projeto.crud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crud")
public class CrudController {

	private final Map<String, String> itemsMap = new HashMap<>(1);

	@GetMapping("/items")
	public Map<String, String> getAllItems() {
		return itemsMap;
	}

	@PostMapping("/items")
	public void addItem(@RequestBody String item) {
		itemsMap.put(item, item);
	}

	@DeleteMapping("/items/{item}")
	public void deleteItem(@PathVariable String item) {
		itemsMap.remove(item);
	}
}
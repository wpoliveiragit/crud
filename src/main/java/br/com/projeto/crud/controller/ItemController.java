package br.com.projeto.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.model.ItemModel;
import br.com.projeto.crud.model.dto.ItemCreateDTO;
import br.com.projeto.crud.model.dto.ItemUpdateDTO;
import br.com.projeto.crud.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Operation(summary = "Listar todos os itens", description = "Retorna uma lista de todos os itens.")
	@GetMapping
	public List<ItemModel> getAll() {
		return itemService.findAll();
	}

	@Operation(summary = "Obter item por ID", description = "Retorna um item com o ID especificado.")
	@GetMapping("/{id}")
	public ResponseEntity<ItemModel> getById(@PathVariable Integer id) {
		Optional<ItemModel> item = itemService.findById(id);
		return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Adicionar um novo item", description = "Adiciona um novo item à lista.")
	@PostMapping
	public ItemModel create(@RequestBody ItemCreateDTO item) {
		return itemService.create(item);
	}

	@Operation(summary = "Atualizar item existente", description = "Atualiza um item existente pelo ID.")
	@PutMapping("/{id}")
	public ResponseEntity<ItemModel> update(@PathVariable Integer id, @RequestBody ItemUpdateDTO updatedItem) {
		return itemService.update(id, updatedItem).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Remover item", description = "Remove um item da lista pelo ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
package br.com.projeto.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.model.DTO.ItemsRequestDTO;
import br.com.projeto.crud.model.DTO.ResponseDTO;
import br.com.projeto.crud.service.ItemsService;
import br.com.projeto.crud.swagger.ItensDocSwagger;
import br.com.projeto.crud.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItensController extends ItensAbstractController implements ItensDocSwagger {

	public ItensController(ItemsService itemService) {
		super(itemService);
		Utils.createLoggerSize30(ItensController.class).info("-- LOAD BEAN -- >> {}",
				ItensController.class.getSimpleName());
	}

	@GetMapping
	@Operation(summary = Get.sum, description = Get.des)
	public ResponseEntity<ResponseDTO> findAll() throws Exception {
		return super.findAll();
	}

	@GetMapping("/{id}")
	@Operation(summary = GetId.sum, description = GetId.des)
	public ResponseEntity<ResponseDTO> findById(@PathVariable Integer id) throws Exception {
		return super.findById(id);
	}

	@PostMapping
	@Operation(summary = Post.sum, description = Post.des)
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody ItemsRequestDTO body) throws Exception {
		return super.create(body);
	}

	@PutMapping("/{id}")
	@Operation(summary = Put.sum, description = Put.des)
	public ResponseEntity<ResponseDTO> update(@PathVariable Integer id, @RequestBody ItemsRequestDTO body)
			throws Exception {
		return super.update(id, body);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = Delete.sum, description = Delete.des)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
		return super.delete(id);
	}
}

package br.com.projeto.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.crud.model.ItensModel;
import br.com.projeto.crud.model.DTO.ItensRequestDTO;
import br.com.projeto.crud.model.DTO.ResponseDTO;
import br.com.projeto.crud.model.DTO.ResponseGeneratorDTO;
import br.com.projeto.crud.service.ItemService;
import br.com.projeto.crud.utils.LoggerUtils;
import br.com.projeto.crud.utils.constants.SwaggerConstants.ItensDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Rotas de cadastros de itens")
@RestController
@RequestMapping("${app.controller.item.path}")
public class ItensController implements ItensDoc {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(ItensController.class);
	private @Autowired ItemService itemService;

	public ItensController() {
		LOG.infoCreateBean();
	}

	@GetMapping
	@Operation(summary = Get.SUM, description = Get.DES)
	public ResponseEntity<ResponseDTO> findAll() throws Exception {
		List<ItensModel> ret = itemService.findAll();
		return (ret.isEmpty()) //
				? ResponseEntity.noContent().build()//
				: ResponseEntity.ok(ResponseGeneratorDTO.responseGetList(ret));
	}

	@GetMapping("/{id}")
	@Operation(summary = GetId.SUM, description = GetId.DES)
	public ResponseEntity<ResponseDTO> findById(@PathVariable Integer id) throws Exception {
		Optional<ItensModel> opt = itemService.findById(id);
		return opt//
				.map(itemModel -> ResponseEntity.ok(ResponseGeneratorDTO.responseGet(opt.get())))//
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@Operation(summary = Post.SUM, description = Post.DES)
	public ResponseEntity<ResponseDTO> create(@Valid @RequestBody ItensRequestDTO body) throws Exception {
		ItensModel model = itemService.create(body.clone());
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGeneratorDTO.responseCreate(model));
	}

	@PutMapping("/{id}")
	@Operation(summary = Put.SUM, description = Put.DES)
	public ResponseEntity<ResponseDTO> update(@PathVariable Integer id, @RequestBody ItensRequestDTO body)
			throws Exception {
		Optional<ItensModel> opt = itemService.update(id, body.clone());
		return opt.map(model -> ResponseEntity.ok(ResponseGeneratorDTO.responseUpdate(opt.get())))//
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = Delete.SUM, description = Delete.DES)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

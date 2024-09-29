package br.com.projeto.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.projeto.crud.model.ItensModel;
import br.com.projeto.crud.model.DTO.ItemsRequestDTO;
import br.com.projeto.crud.model.DTO.ResponseDTO;
import br.com.projeto.crud.model.DTO.ResponseGenerator;
import br.com.projeto.crud.service.ItemsService;
import br.com.projeto.crud.utils.Utils;

public abstract class ItensAbstractController {

	private ItemsService itemService;

	public ItensAbstractController(ItemsService itemService) {
		Utils.createLoggerSize30(ItensController.class).info("-- LOAD BEAN -- >> {}",
				ItensController.class.getSimpleName());
		this.itemService = itemService;
	}

	public ResponseEntity<ResponseDTO> findAll() throws Exception {
		List<ItensModel> ret = itemService.findAll();
		return (ret.isEmpty()) //
				? ResponseEntity.noContent().build()//
				: ResponseEntity.ok(ResponseGenerator.responseGetList(ret));
	}

	public ResponseEntity<ResponseDTO> findById(Integer id) throws Exception {
		Optional<ItensModel> opt = itemService.findById(id);
		return opt//
				.map(itemModel -> ResponseEntity.ok(ResponseGenerator.responseGet(opt.get())))//
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	public ResponseEntity<ResponseDTO> create(ItemsRequestDTO body) throws Exception {
		ItensModel model = itemService.create(body.clone());
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseGenerator.responseCreate(model));
	}

	public ResponseEntity<ResponseDTO> update(Integer id, ItemsRequestDTO body) throws Exception {
		Optional<ItensModel> opt = itemService.update(id, body.clone());
		return opt.map(model -> ResponseEntity.ok(ResponseGenerator.responseUpdate(opt.get())))//
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	public ResponseEntity<Void> delete(Integer id) throws Exception {
		itemService.delete(id);
		return ResponseEntity.noContent().build();
	}

}

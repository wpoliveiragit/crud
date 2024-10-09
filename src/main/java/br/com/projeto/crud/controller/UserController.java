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

import br.com.projeto.crud.model.UserModel;
import br.com.projeto.crud.model.DTO.ResponseDTO;
import br.com.projeto.crud.model.DTO.ResponseGeneratorDTO;
import br.com.projeto.crud.model.DTO.UserRequestDTO;
import br.com.projeto.crud.model.DTO.UserRequestUpdateDTO;
import br.com.projeto.crud.service.UserService;
import br.com.projeto.crud.utils.LoggerUtils;
import br.com.projeto.crud.utils.constants.SwaggerConstants.UserDoc.Delete;
import br.com.projeto.crud.utils.constants.SwaggerConstants.UserDoc.Get;
import br.com.projeto.crud.utils.constants.SwaggerConstants.UserDoc.GetId;
import br.com.projeto.crud.utils.constants.SwaggerConstants.UserDoc.Post;
import br.com.projeto.crud.utils.constants.SwaggerConstants.UserDoc.Put;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rotas de cadastro de login")
@RestController
@RequestMapping("${app.controller.user.path}")
public class UserController {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(UserController.class);

	private @Autowired UserService userService;

	public UserController() {
		LOG.infoCreateBean();
	}

	@GetMapping
	@Operation(summary = Get.SUM, description = Get.DES)
	public ResponseEntity<ResponseDTO> findAll() throws Exception {
		List<UserModel> ret = userService.findAll();

		return (ret.isEmpty()) //
				? ResponseEntity.noContent().build()//
				: ResponseEntity.ok(ResponseGeneratorDTO.responseGetList(ret));
	}

	@GetMapping("/{user}")
	@Operation(summary = GetId.SUM, description = GetId.DES)
	public ResponseEntity<ResponseDTO> findById(@PathVariable String user) throws Exception {
		Optional<UserModel> opt = userService.findById(user);
		return opt//
				.map(itemModel -> ResponseEntity.ok(ResponseGeneratorDTO.responseGet(opt.get())))//
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	@Operation(summary = Post.SUM, description = Post.DES)
	public void create(@RequestBody UserRequestDTO dto) throws Exception {
		userService.create(dto);
	}

	@DeleteMapping("/{user}")
	@Operation(summary = Delete.SUM, description = Delete.DES)
	public void delete(@PathVariable String user) throws Exception {
		userService.delete(user);
	}

	@PutMapping("/{user}")
	@Operation(summary = Put.SUM, description = Put.DES)
	public ResponseEntity<ResponseDTO> update(@PathVariable String user, @RequestBody UserRequestUpdateDTO dto)
			throws Exception {

		Optional<UserModel> opt = userService.update(user, dto);

		return opt.map(model -> ResponseEntity.ok(ResponseGeneratorDTO.responseUpdate(opt.get())))//
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}

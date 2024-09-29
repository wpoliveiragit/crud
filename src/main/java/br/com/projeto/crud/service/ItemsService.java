package br.com.projeto.crud.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.dao.ItensDao;
import br.com.projeto.crud.model.ItensModel;
import br.com.projeto.crud.model.DTO.ItemsRequestDTO;
import br.com.projeto.crud.utils.Utils;

public @Service class ItemsService {
	private static final Logger LOG = Utils.createLoggerSize30(ItemsService.class);

	private @Autowired ItensDao sqlite;

	public ItemsService() {
		LOG.info("-- LOAD BEAN -- >> {}", ItemsService.class.getSimpleName());
	}

	public List<ItensModel> findAll() throws Exception {
		return printLog(sqlite.findAll());
	}

	public Optional<ItensModel> findById(int id) throws Exception {
		return printLog(sqlite.findById(id), "findById", id);
	}

	public ItensModel create(ItemsRequestDTO item) throws Exception {
		return printLog("create", sqlite.create(new ItensModel().name(item.getName())).get());
	}

	public Optional<ItensModel> update(int id, ItemsRequestDTO dto) throws Exception {
		return printLog(sqlite.update(new ItensModel().id(id).name(dto.getName())), "update", id);
	}

	public Optional<ItensModel> delete(int id) throws Exception {
		return printLog(sqlite.delete(id), "delete", id);
	}

	// PRIVATE
	private Optional<ItensModel> printLog(Optional<ItensModel> opt, String label, int id) {
		opt.ifPresentOrElse(//
				item -> LOG.info("[{}] json:{}", label, item), //
				() -> LOG.info("[{}] id: '{}' não encontrado!", label, id));
		return opt;
	}

	private List<ItensModel> printLog(List<ItensModel> list) {
		LOG.info("[{}] {} objetos recuperados", "findAll", list.size());
		list.forEach(line -> System.out.printf("\tjson:%s\n", line));
		return list;
	}

	private ItensModel printLog(String label, ItensModel model) {
		LOG.info("[{}] json:{}", label, model);
		return model;
	}

}
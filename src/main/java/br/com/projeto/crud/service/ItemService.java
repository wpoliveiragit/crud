package br.com.projeto.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.dao.ItemDao;
import br.com.projeto.crud.model.ItensModel;
import br.com.projeto.crud.model.DTO.ItensRequestDTO;
import br.com.projeto.crud.utils.LoggerUtils;

public @Service class ItemService {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(ItemService.class);

	private @Autowired ItemDao itemDao;

	public ItemService() {
		LOG.infoCreateBean();
	}

	public List<ItensModel> findAll() throws Exception {
		return printLog(itemDao.findAll());
	}

	public Optional<ItensModel> findById(int id) throws Exception {
		return printLog("findById", id, itemDao.findById(id));
	}

	public ItensModel create(ItensRequestDTO item) throws Exception {
		return printLog("create", itemDao.create(new ItensModel().name(item.getName())).get());
	}

	public Optional<ItensModel> update(int id, ItensRequestDTO dto) throws Exception {
		return printLog("update", id, itemDao.update(new ItensModel().id(id).name(dto.getName())));
	}

	public Optional<ItensModel> delete(int id) throws Exception {
		return printLog("delete", id, itemDao.delete(id));
	}

	// PRIVATE
	private Optional<ItensModel> printLog(String label, int id, Optional<ItensModel> opt) {
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
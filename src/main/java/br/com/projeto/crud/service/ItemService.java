package br.com.projeto.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.projeto.crud.model.ItemModel;
import br.com.projeto.crud.model.dto.ItemCreateDTO;
import br.com.projeto.crud.model.dto.ItemUpdateDTO;
import br.com.projeto.crud.repository.dao.ItensDao;

@Service
public class ItemService {

	private ItensDao sqlite;

	public ItemService(ItensDao sqlite) {
		this.sqlite = sqlite;
	}

	public List<ItemModel> findAll() {
		return sqlite.findAll();
	}

	public Optional<ItemModel> findById(int id) {
		return sqlite.findById(id);
	}

	public ItemModel create(ItemCreateDTO item) {
		return sqlite.insert(new ItemModel(item)).get();
	}

	public Optional<ItemModel> delete(int id) {
		return sqlite.delete(id);
	}

	public Optional<ItemModel> update(int id, ItemUpdateDTO dto) {
		return sqlite.update(new ItemModel(id, dto));
	}
}
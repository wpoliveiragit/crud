package br.com.projeto.crud.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.projeto.crud.model.ItemModel;
import br.com.projeto.crud.model.dto.ItemCreateDTO;

@Repository
public class ItemRepository {

	private List<ItemModel> itemList;
	private int nextId;

	public ItemRepository() {
		itemList = Collections.synchronizedList(new ArrayList<>());
		nextId = 1;
	}

	public List<ItemModel> findAll() {
		return itemList;
	}

	public Optional<ItemModel> findById(int id) {
		return getItem(id);
	}

	public ItemModel create(ItemCreateDTO dto) {
		ItemModel model = new ItemModel();
		model.setName(dto.getName());
		model.setId(nextId++);
		itemList.add(model);
		return model;
	}

	public Optional<ItemModel> delete(int id) {
		Optional<ItemModel> model = getItem(id);
		model.ifPresent(itemList::remove);
		return model;
	}

	public Optional<ItemModel> update(ItemModel model) {
		Optional<ItemModel> opt = getItem(model.getId());
		opt.ifPresent(item -> item.setName(model.getName()));
		return opt;
	}

	private synchronized Optional<ItemModel> getItem(int id) {
		return itemList.stream().filter(item -> item.getId() == id).findFirst();
	}

}

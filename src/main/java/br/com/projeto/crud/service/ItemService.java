package br.com.projeto.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.model.ItemModel;
import br.com.projeto.crud.model.dto.ItemCreateDTO;
import br.com.projeto.crud.model.dto.ItemUpdateDTO;
import br.com.projeto.crud.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<ItemModel> findAll() {
		return itemRepository.findAll();
	}

	public Optional<ItemModel> findById(int id) {
		return itemRepository.findById(id);
	}

	public ItemModel create(ItemCreateDTO item) {
		return itemRepository.create(item);
	}

	public Optional<ItemModel> delete(int id) {
		return itemRepository.delete(id);
	}

	public Optional<ItemModel> update(int id, ItemUpdateDTO dto) {
		ItemModel model = new ItemModel();
		model.setId(id);
		model.setName(dto.getName());
		return itemRepository.update(model);
	}
}
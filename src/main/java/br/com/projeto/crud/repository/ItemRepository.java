package br.com.projeto.crud.repository;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.model.ItemModel;
import br.com.projeto.crud.model.dto.ItemCreateDTO;

public interface ItemRepository {

	List<ItemModel> findAll();

	Optional<ItemModel> findById(int id);

	ItemModel create(ItemCreateDTO dto);

	Optional<ItemModel> delete(int id);

	Optional<ItemModel> update(ItemModel model);

}

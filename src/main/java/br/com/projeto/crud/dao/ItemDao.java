package br.com.projeto.crud.dao;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.model.ItensModel;

public interface ItemDao {

	List<ItensModel> findAll() throws Exception;

	Optional<ItensModel> findById(int id) throws Exception;

	Optional<ItensModel> update(ItensModel model) throws Exception;

	Optional<ItensModel> delete(int id) throws Exception;

	Optional<ItensModel> create(ItensModel model) throws Exception;
}

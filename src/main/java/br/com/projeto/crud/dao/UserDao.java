package br.com.projeto.crud.dao;

import java.util.List;
import java.util.Optional;

import br.com.projeto.crud.model.UserModel;

public interface UserDao {

	List<UserModel> findAll() throws Exception;

	Optional<UserModel> findById(String id) throws Exception;

	Optional<UserModel> create(UserModel model) throws Exception;

	Optional<UserModel> update(UserModel model) throws Exception;

	Optional<UserModel> delete(String id) throws Exception;

}

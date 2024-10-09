package br.com.projeto.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.crud.dao.UserDao;
import br.com.projeto.crud.model.UserModel;
import br.com.projeto.crud.model.DTO.UserRequestDTO;
import br.com.projeto.crud.model.DTO.UserRequestUpdateDTO;
import br.com.projeto.crud.utils.LoggerUtils;

public @Service class UserService {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(UserService.class);

	private @Autowired UserDao userDao;

	public UserService() {
		LOG.infoCreateBean();
	}

	public List<UserModel> findAll() throws Exception {
		List<UserModel> list = userDao.findAll();
		return list;
	}

	public Optional<UserModel> findById(String login) throws Exception {
		return userDao.findById(login);
	}

	public UserModel create(UserRequestDTO dto) throws Exception {
		Optional<UserModel> opt = userDao.create(new UserModel().login(dto.getLogin()).passwaord(dto.getPasswaord()));
		return opt.get();
	}

	public Optional<UserModel> delete(String login) throws Exception {
		return userDao.delete(login);
	}

	public Optional<UserModel> update(String user, UserRequestUpdateDTO dto) throws Exception {
		return userDao.update(new UserModel().login(user).passwaord(dto.getPasswaord()));
	}

}

package br.com.projeto.crud.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.projeto.crud.component.SQLiteConnectComponent;
import br.com.projeto.crud.component.SqlCommandsComponent;
import br.com.projeto.crud.dao.UserDao;
import br.com.projeto.crud.model.UserModel;
import br.com.projeto.crud.utils.LoggerUtils;
import br.com.projeto.crud.utils.constants.DaoConstants.TBUserReplaces;

public @Component class UserDaoImpl implements UserDao, TBUserReplaces {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(UserDaoImpl.class);

	private SQLiteConnectComponent sqLiteConnect;

	private String SELECT_ALL = "#SELECT_ALL";
	private String SELECT_BY_ID = "#SELECT_BY_ID";
	private String INSERT = "#INSERT";
	private String UPDATE = "#UPDATE";
	private String DELETE_BY_ID = "#DELETE_BY_ID";

	public UserDaoImpl(SQLiteConnectComponent sqlConnect, SqlCommandsComponent sqlCommands) throws Exception {
		LOG.infoLoadingBean();
		this.sqLiteConnect = sqlConnect;

		sqLiteConnect.createTableLog(TABLE, TABLE_COLUMNS);

		// OBTEM E AJUSTA O COMANDO
		SELECT_ALL = sqlCommands.getAdjustedSqlCommandLog(SELECT_ALL, TABLE);
		SELECT_BY_ID = sqlCommands.getAdjustedSqlCommandLog(SELECT_BY_ID, TABLE, WHERE);
		INSERT = sqlCommands.getAdjustedSqlCommandLog(INSERT, TABLE, COLUMNS, VALUES);
		UPDATE = sqlCommands.getAdjustedSqlCommandLog(UPDATE, TABLE, SET, WHERE);
		DELETE_BY_ID = sqlCommands.getAdjustedSqlCommandLog(DELETE_BY_ID, TABLE, WHERE);
		LOG.infoCreateBean();
	}

	@Override
	public List<UserModel> findAll() throws Exception {
		List<UserModel> list = new ArrayList<>();
		ResultSet rs = sqLiteConnect.getConn().prepareStatement(SELECT_ALL).executeQuery();
		while (rs.next()) {
			list.add(new UserModel().login(rs.getString("login")).passwaord(rs.getString("password")));
		}
		return list;
	}

	@Override
	public Optional<UserModel> findById(String login) throws Exception {
		Optional<UserModel> opt = getById(login);
		return opt;
	}

	@Override
	public Optional<UserModel> update(UserModel model) throws Exception {
		Optional<UserModel> opt = getById(model.getLogin());
		if (opt.isEmpty()) {
			return opt;
		}
		PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(UPDATE);
		pstmt.setString(1, model.getPasswaord());
		pstmt.setString(2, model.getLogin());
		pstmt.executeUpdate();
		return Optional.of(model);
	}

	@Override
	public Optional<UserModel> delete(String login) throws Exception {
		Optional<UserModel> opt = getById(login);
		if (opt.isEmpty()) {
			return opt;
		}
		PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(DELETE_BY_ID);
		pstmt.setString(1, login);
		pstmt.executeUpdate();
		return opt;
	}

	@Override
	public Optional<UserModel> create(UserModel model) throws Exception {
		try {
			sqLiteConnect.getConn().setAutoCommit(false); // Iniciar transação
			PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(INSERT);
			pstmt.setString(1, model.getLogin());
			pstmt.setString(2, model.getPasswaord());
			pstmt.executeUpdate();
			sqLiteConnect.getConn().commit(); // Commit na transação
			return Optional.of(model);
		} catch (Exception e) {
			sqLiteConnect.getConn().rollback(); // Reverter transação em caso de erro
			throw e;
		} finally {
			sqLiteConnect.getConn().setAutoCommit(true);
		}
	}

	// PRIVATE
	private Optional<UserModel> getById(String login) throws Exception {
		PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(SELECT_BY_ID);
		pstmt.setString(1, login);
		ResultSet rs = pstmt.executeQuery();

		return rs.next() //
				? Optional.of(new UserModel().login(rs.getString("login")).passwaord(rs.getString("password")))//
				: Optional.empty();
	}

}

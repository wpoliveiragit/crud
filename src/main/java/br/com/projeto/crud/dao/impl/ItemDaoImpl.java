package br.com.projeto.crud.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.projeto.crud.component.SQLiteConnectComponent;
import br.com.projeto.crud.component.SqlCommandsComponent;
import br.com.projeto.crud.dao.ItemDao;
import br.com.projeto.crud.model.ItensModel;
import br.com.projeto.crud.utils.LoggerUtils;
import br.com.projeto.crud.utils.constants.DaoConstants;

public @Component class ItemDaoImpl implements ItemDao, DaoConstants.TBItemReplaces {
	private static final LoggerUtils LOG = LoggerUtils.createLoggerSize30(ItemDaoImpl.class);

	private SQLiteConnectComponent sqLiteConnect;

	private String SELECT_ALL = "#SELECT_ALL";
	private String SELECT_BY_ID = "#SELECT_BY_ID";
	private String INSERT = "#INSERT";
	private String UPDATE = "#UPDATE";
	private String DELETE_BY_ID = "#DELETE_BY_ID";

	public ItemDaoImpl(SQLiteConnectComponent sqlConnect, SqlCommandsComponent sqlCommands) throws Exception {
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

	public List<ItensModel> findAll() throws Exception {
		List<ItensModel> list = new ArrayList<>();
		ResultSet rs = sqLiteConnect.getConn().prepareStatement(SELECT_ALL).executeQuery();
		while (rs.next()) {
			list.add(new ItensModel().id(rs.getInt("id")).name(rs.getString("name")));
		}
		return list;
	}

	public Optional<ItensModel> findById(int id) throws Exception {
		Optional<ItensModel> opt = getById(id);
		return opt;
	}

	public Optional<ItensModel> update(ItensModel model) throws Exception {
		Optional<ItensModel> opt = getById(model.getId());
		if (opt.isEmpty()) {
			return opt;
		}
		PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(UPDATE);
		pstmt.setString(1, model.getName());
		pstmt.setInt(2, model.getId());
		pstmt.executeUpdate();
		return Optional.of(model);
	}

	public Optional<ItensModel> delete(int id) throws Exception {
		Optional<ItensModel> opt = getById(id);
		if (opt.isEmpty()) {
			return opt;
		}
		PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(DELETE_BY_ID);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		return opt;
	}

	public Optional<ItensModel> create(ItensModel model) throws Exception {
		try {
			sqLiteConnect.getConn().setAutoCommit(false); // Iniciar transação
			PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(INSERT);
			pstmt.setString(2, model.getName());
			pstmt.executeUpdate();
			sqLiteConnect.getConn().commit(); // Commit na transação
			model.setId(pstmt.getGeneratedKeys().getInt(1));
			return Optional.of(model);
		} catch (Exception e) {
			sqLiteConnect.getConn().rollback(); // Reverter transação em caso de erro
			throw e;
		} finally {
			sqLiteConnect.getConn().setAutoCommit(true);
		}
	}

	private Optional<ItensModel> getById(int id) throws Exception {
		PreparedStatement pstmt = sqLiteConnect.getConn().prepareStatement(SELECT_BY_ID);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();

		return rs.next() //
				? Optional.of(new ItensModel().id(rs.getInt("id")).name(rs.getString("name")))//
				: Optional.empty();
	}

}

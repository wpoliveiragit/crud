package br.com.projeto.crud.repository.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.projeto.crud.config.SQLiteConnectConfig;
import br.com.projeto.crud.model.ItemModel;

@Component
public class ItensDao {
	private static final Logger LOG = LoggerFactory.getLogger(ItensDao.class.getSimpleName());

	private SQLiteConnectConfig sqliteConnect;
	private String nameTable;
	private String sqlInsert;
	private String sqlSelectMaxID;
	private String sqlSelectAll;
	private String sqlSelectByID;
	private String sqlDeleteByID;
	private String sqlUpdate;

	public ItensDao(SQLiteConnectConfig sqlConn) {
		this.sqliteConnect = sqlConn;

		// coleta as
		nameTable = "TB_ITENS";
		sqlInsert = sqlConn.getSqlMap().get("#TB_ITENS_INSERT");
		sqlSelectMaxID = sqlConn.getSqlMap().get("#TB_ITENS_SELECT_MAX_ID");
		sqlSelectAll = sqlConn.getSqlMap().get("#TB_ITENS_SELECT_ALL");
		sqlSelectByID = sqlConn.getSqlMap().get("#TB_ITENS_SELECT_BY_ID");
		sqlDeleteByID = sqlConn.getSqlMap().get("#TB_ITENS_DELETE_BY_ID");
		sqlUpdate = sqlConn.getSqlMap().get("#TB_ITENS_UPDATE");

		try {
			sqlConn.getStmt().execute(sqlConn.getSqlMap().get("#TB_ITENS_CREATE_TABLE"));
			LOG.info("[CONSTRUTOR] EXISTÊNCIA DA TABELA {} VERIFICADA", nameTable);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<ItemModel> findAll() {
		try {
			PreparedStatement selectPstmt = sqliteConnect.getConn().prepareStatement(sqlSelectAll);
			ResultSet rs = selectPstmt.executeQuery();

			List<ItemModel> list = new ArrayList<>();
			while (rs.next()) {
				list.add(getModel(rs));
			}

			LOG.info("[findAll] {} linhas recuperadas", list.size());
			return list;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Optional<ItemModel> findById(int id) {
		try {
			PreparedStatement pstmt = sqliteConnect.getConn().prepareStatement(sqlSelectByID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				LOG.info("[findById] item encontrado");
				return Optional.of(getModel(rs));
			}
			LOG.info("[findById] item não encontrado");
			return Optional.empty();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Optional<ItemModel> update(ItemModel model) {
		try {

			Optional<ItemModel> opt = findById(model.getId());
			if (opt.isEmpty()) {
				LOG.info("[update] Item '{}' não encontrado", model);
				return opt;
			}
			PreparedStatement pstmt = sqliteConnect.getConn().prepareStatement(sqlUpdate);
			pstmt.setString(1, model.getName());
			pstmt.setInt(2, model.getId());
			pstmt.executeUpdate();
			LOG.info("[update] Item '{}' encontrado", model);
			return findById(model.getId());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Optional<ItemModel> delete(int id) {
		try {
			Optional<ItemModel> opt = findById(id);
			if (opt.isEmpty()) {
				LOG.info("[delete] id '{}' não encontrado", id);
				return opt;
			}
			PreparedStatement pstmt = sqliteConnect.getConn().prepareStatement(sqlDeleteByID);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			LOG.info("[delete] id '{}' deletado", id);
			return opt;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public Optional<ItemModel> insert(ItemModel model) {
		model.setId(getNewId());
		try {
			PreparedStatement pstmt = sqliteConnect.getConn().prepareStatement(sqlInsert);
			pstmt.setInt(1, model.getId());
			pstmt.setString(2, model.getName());
			pstmt.executeUpdate();
			LOG.info("insert {}", model);
			return Optional.of(model);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private ItemModel getModel(ResultSet rs) {
		try {
			ItemModel model = new ItemModel();
			model.setId(rs.getInt("id"));
			model.setName(rs.getString("name"));
			return model;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private int getNewId() {
		try {
			ResultSet rs = sqliteConnect.getConn().prepareStatement(sqlSelectMaxID).executeQuery();
			if (rs.next()) {
				return rs.getInt("max_id") + 1;
			}
			return 1;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}

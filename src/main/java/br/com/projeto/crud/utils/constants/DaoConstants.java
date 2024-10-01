package br.com.projeto.crud.utils.constants;

public interface DaoConstants {

	interface TBItensReplaces {
		String[] TABLE = new String[] { "{table}", "TB_ITENS" };
		String[] TABLE_COLUMNS = new String[] { "{columns}",
				"id INTEGER PRIMARY KEY AUTOINCREMENT, name text NOT NULL" };
		String[] WHERE = new String[] { "{where}", "id = ?" };
		String[] COLUMNS = new String[] { "{columns}", "id, name" };
		String[] VALUES = new String[] { "{values}", "?, ?" };
		String[] SET = new String[] { "{set}", "name = ?" };
	}
	
}

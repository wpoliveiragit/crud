package br.com.projeto.crud.utils.constants;

public interface DaoConstants {

	class Method {
		public static final String format(String format, String... objects) {
			for (String obj : objects) {
				format = format.replaceFirst("\\{}", obj);
			}
			return format;
		}
	}

	interface TBItemReplaces {
		String[] TABLE = new String[] { "{table}", "TB_ITEM" };
		String[] TABLE_COLUMNS = new String[] { "{columns}",
				"id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL" };
		String[] WHERE = new String[] { "{where}", "id = ?" };
		String[] COLUMNS = new String[] { "{columns}", "id, name" };
		String[] VALUES = new String[] { "{values}", "?, ?" };
		String[] SET = new String[] { "{set}", "name = ?" };
	}

	interface ReplaceALL {
		String LBL_TABLE = "{table}";
		String LBL_COLUMNS = "{columns}";
		String LBL_WHERE = "{where}";
		String LBL_VALUES = "{values}";
		String LBL_SET = "{set}";

		String LBL_VALUES_2 = "?, ?";
		String LBL_SET_VALUE = "{} = ?";
		String LBL_COLUMNS_2 = "{}, {}";
	}

	interface TBUserReplaces extends ReplaceALL {
		interface Info {
			String TABLE = "TB_USER";
			String COLUMN_LOGIN = "login";
			String COLUMN_PASSWORD = "password";

			String TABLE_COLUMNS = Method.format("{}  TEXT PRIMARY KEY, {}  TEXT NOT NULL", //
					COLUMN_LOGIN, COLUMN_PASSWORD//
			);
		}

		String[] TABLE = new String[] { LBL_TABLE, Info.TABLE };
		String[] TABLE_COLUMNS = new String[] { LBL_COLUMNS, Info.TABLE_COLUMNS };
		String[] VALUES = new String[] { LBL_VALUES, LBL_VALUES_2 };
		String[] WHERE = new String[] { LBL_WHERE, //
				Method.format(LBL_SET_VALUE, Info.COLUMN_LOGIN) //
		};
		String[] SET = new String[] { LBL_SET, //
				Method.format(LBL_SET_VALUE, Info.COLUMN_PASSWORD) //
		};
		String[] COLUMNS = new String[] { LBL_COLUMNS, //
				Method.format(LBL_COLUMNS_2, Info.COLUMN_LOGIN, Info.COLUMN_PASSWORD) //
		};
	}

}

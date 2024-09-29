#DROP_TABLE
DROP TABLE IF EXISTS {table};

#CREATE_TABLE
CREATE TABLE IF NOT EXISTS {table}({columns});

-- CRUD
#SELECT_ALL
SELECT * FROM {table};

#SELECT_BY_ID
SELECT * FROM {table} WHERE {where};

#INSERT
INSERT INTO {table} ({columns}) VALUES ({values});

#UPDATE
UPDATE {table} SET {set} WHERE {where};

#DELETE_BY_ID
DELETE FROM {table} WHERE {where};
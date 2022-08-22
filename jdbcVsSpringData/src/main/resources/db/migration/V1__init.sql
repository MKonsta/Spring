
CREATE TABLE persons_spring_data
(
	id            SERIAL PRIMARY KEY,
	name          TEXT,
	inserted_by   TEXT,
	age           INTEGER,
	creation_date TIMESTAMP
);
ALTER SEQUENCE persons_spring_data_id_seq INCREMENT 100000;

CREATE TABLE persons_jdbc
(
	id            SERIAL PRIMARY KEY,
	name          TEXT,
	inserted_by   TEXT,
	age           INTEGER,
	creation_date TIMESTAMP
);




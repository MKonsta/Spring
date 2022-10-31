
CREATE TABLE persons
(
	id SERIAL NOT NULL PRIMARY KEY,
	username TEXT NOT NULL,
	year_of_birth INTEGER NOT NULL,
	password TEXT NOT NULL,
	role TEXT
);
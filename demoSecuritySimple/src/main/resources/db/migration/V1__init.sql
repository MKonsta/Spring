
CREATE TABLE persons
(
	id SERIAL NOT NULL PRIMARY KEY,
	username TEXT NOT NULL,
	year_of_birth INTEGER NOT NULL,
	password TEXT NOT NULL
);

INSERT INTO public.persons (username, year_of_birth, password) VALUES ('test_user1', 1960, 'password1');
INSERT INTO public.persons (username, year_of_birth, password) VALUES ('test_user2', 1960, 'password2');
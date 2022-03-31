/* --------------------------------------------- */
/* Tabla Facultades                              */
/* --------------------------------------------- */
CREATE TABLE faculties (
	faculty_id SERIAL NOT NULL,
	faculty_name VARCHAR(200) NOT NULL,
	CONSTRAINT PK_FACULTIES PRIMARY KEY (faculty_id)
);

ALTER TABLE faculties OWNER TO user_spring;



/* --------------------------------------------- */
/* Tabla Profesores                              */
/* --------------------------------------------- */
CREATE TABLE professors (
	professor_id SERIAL NOT NULL,
	faculty_id INT4 NOT NULL,
	professor_document VARCHAR(200) NOT NULL,
	professor_surname VARCHAR(200) NOT NULL,
	professor_name VARCHAR(200) NOT NULL,
	CONSTRAINT PK_PROFESSORS PRIMARY KEY (professor_id),
	CONSTRAINT FK_PROFESSORS_FACULTIES FOREIGN KEY(faculty_id) 
		REFERENCES faculties (faculty_id)
		ON DELETE restrict ON UPDATE cascade
);

ALTER TABLE professors OWNER TO user_spring;

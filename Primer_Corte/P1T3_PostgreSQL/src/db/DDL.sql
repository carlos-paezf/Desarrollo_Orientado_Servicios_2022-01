CREATE TABLE faculty(
    faculty_id SERIAL NOT NULL,
    faculty_name VARCHAR(100) NOT NULL,
    CONSTRAINT PK_FACULTIES PRIMARY KEY(faculty_id)
);

ALTER TABLE faculty OWNER TO user_node;


CREATE TABLE professor(
    professor_id SERIAL NOT NULL,
    faculty_id int4 NOT NULL,
    professor_doc VARCHAR(100) NOT NULL,
    professor_name VARCHAR(100) NOT NULL,
    professor_surname VARCHAR(100) NOT NULL,
    professor_type int2 NOT NULL 
        CONSTRAINT CK_TYPEPROFESSOR CHECK(professor_type IN(1, 2, 3, 4, 5, 6)),
    CONSTRAINT PK_PROFESSOR PRIMARY KEY(professor_id)
);

ALTER TABLE professor OWNER TO user_node;
ALTER TABLE professor ADD CONSTRAINT 
    FK_FACULTY_PROFESSOR FOREIGN KEY(faculty_id)
    REFERENCES faculty (faculty_id)
    ON DELETE RESTRICT ON UPDATE CASCADE;
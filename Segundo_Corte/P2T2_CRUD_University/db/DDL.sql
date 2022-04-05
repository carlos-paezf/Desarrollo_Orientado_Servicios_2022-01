/* ----------------------------------------------- */
/* Tabla Universidad                               */
/* ----------------------------------------------- */
CREATE TABLE university (
    university_id SERIAL NOT NULL,
    university_name VARCHAR(200) NOT NULL,
    university_nit VARCHAR(200) NOT NULL,
    CONSTRAINT PK_UNIVERSITY PRIMARY KEY (university_id)
);

ALTER TABLE university OWNER TO user_spring;



/* ----------------------------------------------- */
/* Tabla Seccional                                 */
/* ----------------------------------------------- */
CREATE TABLE sectional (
    sectional_id SERIAL NOT NULL,
    sectional_address VARCHAR(200) NOT NULL,
    sectional_name VARCHAR(200) NOT NULL,
    university_id INT4 NOT NULL,
    CONSTRAINT PK_SECTIONAL PRIMARY KEY (sectional_id),
    CONSTRAINT FK_UNIVERSITY_SECTIONAL FOREIGN KEY (university_id)
        REFERENCES university (university_id)
        ON DELETE restrict ON UPDATE cascade
);

ALTER TABLE sectional OWNER TO user_spring;



/* ----------------------------------------------- */
/* Tabla Facultad                                  */
/* ----------------------------------------------- */
CREATE TABLE faculty (
    faculty_id SERIAL NOT NULL,
    faculty_name VARCHAR(200) NOT NULL,
    sectional_id INT4 NOT NULL,
    CONSTRAINT PK_FACULTY PRIMARY KEY (faculty_id),
    CONSTRAINT FK_SECTIONAL_FACULTY FOREIGN KEY (sectional_id)
        REFERENCES sectional (sectional_id)
        ON DELETE restrict ON UPDATE cascade
);

ALTER TABLE faculty OWNER TO user_spring;



/* ----------------------------------------------- */
/* Tabla Docente                                   */
/* ----------------------------------------------- */
CREATE TABLE professor (
    professor_id SERIAL NOT NULL,
    professor_document VARCHAR(200) NOT NULL,
    professor_name VARCHAR(200) NOT NULL,
    professor_lastname VARCHAR(200) NOT NULL,
    professor_type INT NOT NULL,
    faculty_id INT4 NOT NULL,
    CONSTRAINT PK_PROFESSOR PRIMARY KEY (professor_id),
    CONSTRAINT FK_FACULTY_PROFESSOR FOREIGN KEY (faculty_id)
        REFERENCES faculty (faculty_id)
        ON DELETE restrict ON UPDATE cascade
);

ALTER TABLE professor OWNER TO user_spring;



/* ----------------------------------------------- */
/* Tabla Asignaturas                               */
/* ----------------------------------------------- */
CREATE TABLE subject (
    subject_id SERIAL NOT NULL,
    subject_semester INT NOT NULL,
    subject_name VARCHAR(200) NOT NULL,
    subject_cod INT NOT NULL,
    subject_type VARCHAR(200) NOT NULL,
    professor_id INT4 NOT NULL,
    CONSTRAINT PK_SUBJECT PRIMARY KEY (subject_id),
    CONSTRAINT FK_PROFESSOR_SUBJECT FOREIGN KEY (professor_id)
        REFERENCES professor (professor_id)
        ON DELETE restrict ON UPDATE cascade
);

ALTER TABLE subject OWNER TO user_spring;
CREATE DATABASE db_p1t5
    WITH 
    OWNER = user_node
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;


/* =============================================================== */
/* Table: Program                                                  */
/* =============================================================== */

CREATE TABLE program (
    program_id SERIAL NOT NULL,
    program_name VARCHAR(200) NOT NULL,
    CONSTRAINT PK_PROGRAM PRIMARY KEY(program_id)
);


/* =============================================================== */
/* Table: Pensum                                                   */
/* =============================================================== */

CREATE TABLE pensum (
    pensum_id SERIAL NOT NULL,
    program_id INT4 NOT NULL,
    pensum_name VARCHAR(200) NOT NULL,
    CONSTRAINT PK_PENSUM PRIMARY KEY(pensum_id)
);


/* =============================================================== */
/* Table: Subjects                                                  */
/* =============================================================== */

CREATE TABLE subjects (
    subject_id SERIAL NOT NULL,
    subject_name VARCHAR(200) NOT NULL,
    subject_reference VARCHAR(200) NOT NULL,
    CONSTRAINT PK_SUBJECT PRIMARY KEY(subject_id)
);


/* =============================================================== */
/* Table: Semester                                                  */
/* =============================================================== */

CREATE TABLE semester (
    semester_id SERIAL NOT NULL,
    semester_name VARCHAR(200) NOT NULL,
    CONSTRAINT PK_SEMESTER PRIMARY KEY(semester_id)
);


/* =============================================================== */
/* Table: Pensum-Subject                                           */
/* =============================================================== */

CREATE TABLE pensum_subject (
    pensum_id INT4 NOT NULL,
    subject_id INT4 NOT NULL,
    semester_id INT4 NOT NULL,
    CONSTRAINT PK_PENSUM_SUBJECT PRIMARY KEY(pensum_id, subject_id)
);


/* =============================================================== */
/* Table: Access                                                   */
/* =============================================================== */

CREATE TABLE access (
    access_id SERIAL NOT NULL,
    access_email VARCHAR(200) NOT NULL,
    access_key VARCHAR(200) NOT NULL,
    CONSTRAINT PK_ACCESS PRIMARY KEY(access_id)
);


/* =============================================================== */
/* Table: Constraints                                              */
/* =============================================================== */

ALTER TABLE program OWNER TO user_node;
CREATE UNIQUE INDEX index_program_name ON program (program_name);


ALTER TABLE pensum OWNER TO user_node;
ALTER TABLE pensum ADD CONSTRAINT
    FK_PROGRAM_PENSUM FOREIGN KEY(program_id)
    REFERENCES program (program_id)
    ON DELETE restrict ON UPDATE cascade;


ALTER TABLE subjects OWNER TO user_node;


ALTER TABLE semester OWNER TO user_node;


ALTER TABLE pensum_subject OWNER TO user_node;
ALTER TABLE pensum_subject 
    ADD CONSTRAINT FK_PENSUMSUBJECT_PENSUM 
    FOREIGN KEY(pensum_id) 
    REFERENCES pensum (pensum_id) 
    ON DELETE restrict ON UPDATE cascade;
ALTER TABLE pensum_subject 
    ADD CONSTRAINT FK_PENSUMSUBJECT_SUBJECT 
    FOREIGN KEY(subject_id) 
    REFERENCES subjects (subject_id) 
    ON DELETE restrict ON UPDATE cascade;
ALTER TABLE pensum_subject
    ADD CONSTRAINT FK_PENSUMSUBJECT_SEMESTER 
    FOREIGN KEY(semester_id) 
    REFERENCES semester (semester_id) 
    ON DELETE restrict ON UPDATE cascade;


ALTER TABLE access OWNER TO user_node;
CREATE UNIQUE INDEX index_access_email on access (access_email);
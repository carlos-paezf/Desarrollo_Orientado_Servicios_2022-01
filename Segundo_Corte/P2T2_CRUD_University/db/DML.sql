/* ----------------------------------------------- */
/* Tabla Universidad                               */
/* ----------------------------------------------- */
INSERT INTO university (university_name, university_nit) VALUES ('Universidad Santo Tomás', '1234567890');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de los Andes', '1234567890');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad Nacional de Colombia', '1234567890');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Antioquia', '0987654321');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Medellin', '1111111111');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Cartagena', '2222222222');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Pasto', '3333333333');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Cucuta', '4444444444');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Pereira', '5555555555');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Manizales', '6666666666');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Bucaramanga', '7777777777');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Pasto', '8888888888');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Cucuta', '9999999999');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Pereira', '0000000000');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Manizales', '1111111111');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Bucaramanga', '2222222222');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Pasto', '3333333333');
INSERT INTO university (university_name, university_nit) VALUES ('Universidad de Cucuta', '4444444444');



/* ----------------------------------------------- */
/* Tabla Seccional                                 */
/* ----------------------------------------------- */
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 1', 'Seccional 1', 1);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 2', 'Seccional 2', 1);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 3', 'Seccional 3', 2);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 4', 'Seccional 4', 2);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 5', 'Seccional 5', 3);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 6', 'Seccional 6', 4);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 7', 'Seccional 7', 4);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 8', 'Seccional 8', 4);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 9', 'Seccional 9', 5);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 10', 'Seccional 10', 6);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 11', 'Seccional 11', 7);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 12', 'Seccional 12', 8);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 13', 'Seccional 13', 9);
INSERT INTO sectional (sectional_address, sectional_name, university_id) VALUES ('Calle 14', 'Seccional 14', 9);



/* ----------------------------------------------- */
/* Tabla Facultad                                  */
/* ----------------------------------------------- */
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ingeniería', 1);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias', 2);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias Sociales', 3);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias Exactas', 4);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias Agrarias', 5);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias Ambientales', 6);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias Jurídicas', 7);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias Veterinarias', 8);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias de la Salud', 9);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias de la Educación', 10);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias de la Comunicación', 11);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias de la Educación', 12);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias de la Educación', 13);
INSERT INTO faculty (faculty_name, sectional_id) VALUES ('Facultad de Ciencias de la Educación', 14);


/* ----------------------------------------------- */
/* Tabla Docente                                   */
/* ----------------------------------------------- */
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('1111111111', 'Juan', 'Perez', 1, 1);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('2222222222', 'Ana', 'Garcia', 2, 2);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('3333333333', 'Pedro', 'Gomez', 3, 3);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('4444444444', 'Juan', 'Lopez', 4, 4);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('5555555555', 'Maria', 'Gomez', 5, 5);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('6666666666', 'Juan', 'Perez', 6, 6);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('7777777777', 'Ana', 'Garcia', 7, 7);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('8888888888', 'Pedro', 'Gomez', 8, 8);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('9999999999', 'Juan', 'Lopez', 9, 9);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('0000000000', 'Maria', 'Gomez', 10, 10);
INSERT INTO professor (professor_document, professor_name, professor_lastname, professor_type, faculty_id) VALUES ('1111111111', 'Juan', 'Perez', 11, 11);



/* ----------------------------------------------- */
/* Tabla Asignaturas                               */
/* ----------------------------------------------- */
INSERT INTO subject (subject_semester, subject_name, subject_cod, subject_type, professor_id) VALUES (1, 'Matemáticas', 111, 1, 1);
INSERT INTO subject (subject_semester, subject_name, subject_cod, subject_type, professor_id) VALUES (1, 'Lengua', 112, 1, 2);
INSERT INTO subject (subject_semester, subject_name, subject_cod, subject_type, professor_id) VALUES (1, 'Ciencias', 113, 1, 3);
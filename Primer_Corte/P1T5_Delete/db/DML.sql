/* =============================================================== */
/* Table: Program                                                  */
/* =============================================================== */

INSERT INTO program (program_name) VALUES ('Ingeniería de Sistemas');
INSERT INTO program (program_name) VALUES ('Ingeniería Civil');
INSERT INTO program (program_name) VALUES ('Arquitectura');
INSERT INTO program (program_name) VALUES ('Ingeniería Ambiental');


/* =============================================================== */
/* Table: Semester                                                 */
/* =============================================================== */

INSERT INTO semester (semester_name) VALUES ('Semestre 1');
INSERT INTO semester (semester_name) VALUES ('Semestre 2');
INSERT INTO semester (semester_name) VALUES ('Semestre 3');
INSERT INTO semester (semester_name) VALUES ('Semestre 4');
INSERT INTO semester (semester_name) VALUES ('Semestre 5');
INSERT INTO semester (semester_name) VALUES ('Semestre 6');
INSERT INTO semester (semester_name) VALUES ('Semestre 7');
INSERT INTO semester (semester_name) VALUES ('Semestre 8');
INSERT INTO semester (semester_name) VALUES ('Semestre 9');
INSERT INTO semester (semester_name) VALUES ('Semestre 10');


/* =============================================================== */
/* Table: Subjects                                                 */
/* =============================================================== */

INSERT INTO subjects (subject_name, subject_reference) VALUES ('Desarrollo Orientado a Servicios', 'ISDOS');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('POO', 'ISPOO');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('UI/UX', 'ISUIUX');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('Práctica Empresarial', 'ISPE');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('Trabajo de Grado I', 'ISTGI');


/* =============================================================== */
/* Table: Pensum                                                   */
/* =============================================================== */

INSERT INTO pensum (program_id, pensum_name) VALUES (1, 'Pensum 2022-01');
INSERT INTO pensum (program_id, pensum_name) VALUES (2, 'Pensum 2000');
INSERT INTO pensum (program_id, pensum_name) VALUES (3, 'Pensum 3');
INSERT INTO pensum (program_id, pensum_name) VALUES (4, 'Pensum 40');


/* =============================================================== */
/* Table: Pensum-Subject                                           */
/* =============================================================== */

INSERT INTO pensum_subject (pensum_id, subject_id, semester_id) VALUES (1, 1, 1);
INSERT INTO pensum_subject (pensum_id, subject_id, semester_id) VALUES (1, 2, 2);
INSERT INTO pensum_subject (pensum_id, subject_id, semester_id) VALUES (2, 2, 3);
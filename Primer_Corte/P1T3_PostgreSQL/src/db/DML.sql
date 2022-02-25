INSERT INTO faculty(faculty_name) VALUES ('Sistemas');
INSERT INTO faculty(faculty_name) VALUES ('Civil');
INSERT INTO faculty(faculty_name) VALUES ('Arquitectura');
INSERT INTO faculty(faculty_name) VALUES ('Recreación');


INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (1, '333555666', 'Luz Elena', 'Gutierrez López', 1);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (1, '888777444', 'Carlos Andres', 'Guerrero Alarcón', 2);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (1, '999888777', 'Jennifer Eliana', 'Correa Ussa', 3);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (2, '111222333', 'Ismael Ramón', 'Medida Abello', 1);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (2, '741258963', 'Aida', 'Merlano', 4);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (3, '41257845', 'Doña Ramona', 'Almeida Estragón', 3);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type)
VALUES (2, '98732145', 'Isto Manuel', 'TRansilvania Castro', 1);


INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type) 
VALUES (2, '111111', 'David', 'Ferrer', 1);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type) 
VALUES (1, '111111', 'Carlos', 'Paez', 2);

INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type) 
VALUES (4, '111111', 'CD', 'Ferreira', 3);
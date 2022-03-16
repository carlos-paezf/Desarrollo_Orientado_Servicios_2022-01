/* --------------------------------------------- */
/* Tabla: Roles                                  */
/* --------------------------------------------- */
INSERT INTO role (role_name, role_status) VALUES ('Admin', 1), ('Invitado', 1);



/* --------------------------------------------- */
/* Tabla: Usuarios                               */
/* --------------------------------------------- */
INSERT INTO users (role_id, user_doc, user_type_doc, user_name, user_surname, user_phone, user_status) VALUES (1, '1234567890', 1, 'Test 1', 'Test 1', '019283', 1);
INSERT INTO users (role_id, user_doc, user_type_doc, user_name, user_surname, user_phone, user_status) VALUES (2, '0987654321', 1, 'Test 2', 'Test 2', '019283', 2);
INSERT INTO users (role_id, user_doc, user_type_doc, user_name, user_surname, user_phone, user_status) VALUES (2, '1029384756', 1, 'Test 3', 'Test 3', '019283', 1);



/* --------------------------------------------- */
/* Tabla: Accesos                                */
/* --------------------------------------------- */
INSERT INTO access (user_id, access_email, access_password) VALUES (1, 'test1@mail.com', 'password');
INSERT INTO access (user_id, access_email, access_password) VALUES (2, 'test2@hotmail.com', 'clave');
INSERT INTO access (user_id, access_email, access_password) VALUES (3, 'test3@yahoo.com', 'constrasenia');



/* --------------------------------------------- */
/* Tabla: Ingresos                               */
/* --------------------------------------------- */
INSERT INTO login (user_id, login_date, login_time) VALUES (1, '2022-03-16', '07:00:00');
INSERT INTO login (user_id, login_date, login_time) VALUES (1, '2022-03-16', '08:00:00');
INSERT INTO login (user_id, login_date, login_time) VALUES (1, '2022-03-16', '09:00:00');
INSERT INTO login (user_id, login_date, login_time) VALUES (2, '2022-03-16', '07:00:00');
INSERT INTO login (user_id, login_date, login_time) VALUES (3, '2022-03-16', '07:00:00');
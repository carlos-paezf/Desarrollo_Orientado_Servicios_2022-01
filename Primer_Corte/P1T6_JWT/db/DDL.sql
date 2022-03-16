/* --------------------------------------------- */
/* Tabla: Roles                                  */
/* --------------------------------------------- */
CREATE TABLE role (
    role_id SERIAL NOT NULL,
    role_name VARCHAR(200) NOT NULL,
    role_status INT2 NOT NULL,
    CONSTRAINT PK_ROLE PRIMARY KEY(role_id)
);

ALTER TABLE role OWNER TO user_node;



/* --------------------------------------------- */
/* Tabla: Usuarios                               */
/* --------------------------------------------- */
CREATE TABLE users (
    user_id SERIAL NOT NULL,
    role_id INT4 NOT NULL,
    user_doc VARCHAR(50) NOT NULL,
    user_type_doc INT2 NOT NULL,
    user_name VARCHAR(60) NOT NULL,
    user_surname VARCHAR(80) NOT NULL,
    user_phone VARCHAR(50) NOT NULL,
    user_status INT2 NOT NULL,
    CONSTRAINT PK_USER PRIMARY KEY(user_id)
);

ALTER TABLE users OWNER TO user_node;

ALTER TABLE users
    ADD CONSTRAINT FK_ROLE_USER 
    FOREIGN KEY(role_id)
    REFERENCES role (role_id)
    ON DELETE restrict ON UPDATE cascade;



/* --------------------------------------------- */
/* Tabla: Alertas                                */
/* --------------------------------------------- */
CREATE TABLE alert (
    alert_id SERIAL NOT NULL,
    user_id INT4 NOT NULL,
    alert_days INT2 NOT NULL,
    alert_months INT2 NOT NULL,
    alert_title VARCHAR(500) NOT NULL,
    alert_detail TEXT NOT NULL,
    alert_status INT2 NOT NULL,
    alert_date DATE NOT NULL,
    CONSTRAINT PK_ALERT PRIMARY KEY(alert_id)
);

ALTER TABLE alert OWNER TO user_node;

ALTER TABLE alert
    ADD CONSTRAINT FK_USER_ALERT 
    FOREIGN KEY(user_id)
    REFERENCES users (user_id)
    ON DELETE restrict ON UPDATE cascade;



/* --------------------------------------------- */
/* Tabla: Imágenes                               */
/* --------------------------------------------- */
CREATE TABLE image (
    image_id SERIAL NOT NULL,
    user_id INT4 NOT NULL,
    image_public_name VARCHAR(200) NOT NULL,
    image_private_name VARCHAR(200) NOT NULL,
    image_type VARCHAR(50) NOT NULL,
    image_size VARCHAR(50) NOT NULL,
    image_favorite INT2 NOT NULL,
    CONSTRAINT PK_IMAGE PRIMARY KEY(image_id)
);

ALTER TABLE image OWNER TO user_node;

ALTER TABLE image
    ADD CONSTRAINT FK_USER_IMAGE
    FOREIGN KEY(user_id)
    REFERENCES users (user_id)
    ON DELETE restrict ON UPDATE cascade;



/* --------------------------------------------- */
/* Tabla: Accesos                                */
/* --------------------------------------------- */
CREATE TABLE access (
    user_id INT4 NOT NULL,
    access_email VARCHAR(100) NOT NULL,
    access_password VARCHAR(150) NOT NULL,
    CONSTRAINT PK_ACCESS PRIMARY KEY(user_id)
);

ALTER TABLE access OWNER TO user_node;

ALTER TABLE access
    ADD CONSTRAINT FK_USER_ACCESS
    FOREIGN KEY(user_id)
    REFERENCES users (user_id)
    ON DELETE restrict ON UPDATE cascade;



/* --------------------------------------------- */
/* Tabla: Ingresos                               */
/* --------------------------------------------- */
CREATE TABLE login (
    login_id SERIAL NOT NULL,
    user_id INT4 NOT NULL,
    login_date DATE NOT NULL,
    login_time TIME NOT NULL,
    CONSTRAINT PK_LOGIN PRIMARY KEY(login_id)
);

ALTER TABLE login OWNER TO user_node;

ALTER TABLE login
    ADD CONSTRAINT FK_USER_LOGIN
    FOREIGN KEY(user_id)
    REFERENCES users (user_id)
    ON DELETE restrict ON UPDATE cascade;
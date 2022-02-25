# Servicio Real

## Configuración Inicial

### Archivo gestor de paquetes

Crear el archivo gestor de paquetes dentro del proyecto:

```txt
npm init -y
```

### Instalar módulos necesarios

Instalar los paquetes necesarios del proyecto (CORS, Morgan, Express, Colors), con el comando:

```txt
npm install cors morgan express pg-promise colors
```

### Instalar los tipos de los módulos

Instalar los types de los anteriores paquetes con los siguientes comandos:

```txt
npm install @types/cors --save-dev
```

```txt
npm install @types/morgan --save-dev
```

```txt
npm install @types/express --save-dev
```

```txt
npm install @types/pg-promise --save-dev
```

### Instalar Nodemon en modo desarrollo

Instalar Nodemon con el comando:

```txt
npm i --save-dev nodemon
```

### Inicializar y configurar TS

Inicializar TS en el proyecto:

```txt
tsc --init
```

Configurar el archivo para el directorio de transpilación:

```json
{
    "compilerOptions": {
        ...,
        "outDir": "./dist",
        ...,
    }
}
```

### Configurar los scripts del proyecto

Crear los scripts del proyecto en el archivo `package.json`:

```json
{
    ...,
    "scripts": {
        ...,
        "build": "tsc -w",
        "dev": "nodemon dist/app.js"
    },
    ...
}
```

## PostgreSQL

### PGAdmin

Vamos a hacer una conexión a una base de datos dentro de ***PostgreSQL***, para ello debemos tener instalado dicha aplicación, y luego podemos acceder a ***PGAdmin***. También vamos a crear un directorio llamado `db` dónde guardamos las sentencias que vamos a usar en la base de datos.

Dentro de PGAdmin, en PostgreSQL 14 > Login/Group Roles, creamos un nuevo rol, con su respectiva contraseña y con los privilegios necesarios (Can Login, SuperUser, Create Roles, Create databases). Por ejemplo tenemos el usuario `user_node` con la contraseña `1234567890`.

### DDL (Data Definition Language)

El primer archivo se llamara `DDL.sql` en donde tenemos las sentencias de creación. La primera tabla será la de las facultades, la cual definimos como propiedad de un usuario especifico dentro de PostgreSQL. Dentro de dicha tabla tenemos una llave primaria que vamos a usar como llave foránea dentro de otras tablas:

```sql
CREATE TABLE faculty(
    faculty_id SERIAL NOT NULL,
    faculty_name VARCHAR(100) NOT NULL,
    CONSTRAINT PK_FACULTIES PRIMARY KEY(faculty_id)
);

ALTER TABLE faculty OWNER TO user_node;
```

Definimos la tabla de docentes, en donde tenemos múltiples campos, entre ellos una llave primaria y una llave foranea que viene de la tabla de facultades. El campo `type_professor` tiene una Constraint que se asegura de que el valor almacenado dentro de la misma, esté dentro de algunos de las valores establecidos, en esta caso, números del 1 al 6:

```sql
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
```

También debemos configurar que la tabla sea propiedad de `user_node`, y que tenga la configuración de constraint de la foreign key. En caso de que se intente eliminar la tabla de `faculty`, se lanza una restricción, pero la actualización si se puede en cascada.

```sql
ALTER TABLE professor OWNER TO user_node;
ALTER TABLE professor ADD CONSTRAINT 
    FK_FACULTY_PROFESSOR FOREIGN KEY(faculty_id)
    REFERENCES faculty (faculty_id)
    ON DELETE RESTRICT ON UPDATE CASCADE;
```

Creamos una nueva base de datos dentro de la sección PostgreSQL 14 > Databases, le ponemos por ejemplo el nombre `db_p1t3`, teniendo como dueño el usuario que acabamos de crear. Luego añadimos un Query Tool con las sentencias DDL de nuestro archivo. Si todo sale bien, podemos ingresar a la sección db_p1t3 > Schemas > Tables, y allí podemos encontrar las 2 tablas de nuestras sentencias.

### DML (Data Manipulation Language)

También creamos sentencias DML para inserción de algunos datos:

```sql
INSERT INTO faculty(faculty_name) VALUES ('Sistemas');
INSERT INTO faculty(faculty_name) VALUES ('Civil');
INSERT INTO faculty(faculty_name) VALUES ('Arquitectura');
INSERT INTO faculty(faculty_name) VALUES ('Recreación');


INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type) VALUES (2, '111111', 'David', 'Ferrer', 1);
INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type) VALUES (1, '111111', 'Carlos', 'Páez', 2);
INSERT INTO professor(faculty_id, professor_doc, professor_name, professor_surname, professor_type) VALUES (4, '111111', 'CD', 'Ferreira', 3);
```

## Configurar nuestro proyecto para conectar con la base de datos

### Objeto con variables de entorno

Creamos un archivo llamado `config/domains/var_db.ts` en donde tendremos las variables de configuración de conexión a nuestra base de datos.

```ts
export default {
    user: 'user_node',
    host: 'localhost',
    database: 'db_p1t3',
    password: '1234567890',
    port: 5433
}
```

### Camelizar los datos

Luego creamos una función para poder camelizar los datos, dentro del archivo `connection/connection_functions.ts`:

```ts
'use strict'

import pgPromise from "pg-promise"


export interface IClient { }


class ConnectionFunctions {
    static camelizeColumns = (data: any) => {
        const tmp = data[0]

        for (const prop in tmp) {
            const camel = pgPromise.utils.camelize(prop)

            if (!(camel in tmp)) {
                for (let i = 0; i < data.length; i++) {
                    const d = data[i]
                    d[camel] = d[prop]
                    delete d[prop]
                }
            }
        }
    }
}


export default ConnectionFunctions
```

### Opciones de PostgreSQL

Creamos otro archivo llamado `connection/connection_options.ts` donde usamos la función anterior:

```ts
'use strict'

import pgPromise from "pg-promise";
import { IClient } from "pg-promise/typescript/pg-subset";
import ConnectionFunctions from './connection_functions';


class ConnectionOptions {
    static pgOptions: pgPromise.IInitOptions<IClient> = {
        receive(data, result, error) {
            ConnectionFunctions.camelizeColumns(data)
        }
    }
}


export default ConnectionOptions
```

### Conexión a la DB

Tenemos un nuevo archivo llamado `connection/connection_DB.ts`, en donde establecemos la conexión a nuestra base de datos usando la librería de pg-promise estableciendo los parámetros de las opciones de PostgreSQL.

```ts
'use strict'

import { red, blue } from 'colors'
import pgPromise from 'pg-promise'

import ConnectionOptions from './connection_options'
import var_db from '../domains/var_db.config'


class ConnectionDB {
    private _pgp = pgPromise(ConnectionOptions.pgOptions)
    public pool: any

    constructor() {
        this.pool = this._pgp(var_db)
        this.connectPool()
    }

    connectPool = async () => {
        try {
            const connection = await this.pool.connect()
            connection.done()
            console.log(blue.italic(`> Conexión establecida con la base de datos ${var_db.database}\n`))
        } catch (error) {
            console.log(red('Error in ConnectionDB: '), error)
        }
    }
}


const connectionDB = new ConnectionDB()
export default connectionDB
```

## Facultades

### Faculty Repository

Creamos un directorio en el que ponemos las consultas sql a usar a manera de propiedades de un objeto. El primer objeto será las sentencias para la tabla de facultad:

```ts
export const SQL_FACULTY = {
    ALL: 'SELECT f.faculty_id, f.faculty_name FROM faculty f'
}
```

### Faculty DAO (Data Access Object)

Vamos a crear un DAO para la tabla de Facultad, con el que vamos a hacer la consulta a la base de datos:

```ts
'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class FacultyDAO {
    protected static getFaculty = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            res.status(400).json({ ok: false })
        }
    }
}


export default FacultyDAO
```

### Faculty Controller

Seguimos con el controlador para la tabla de facultades, el cual va a extender del DAO creado anteriormente, y por lo tanto usaremos su método protegido:

```ts
import { Request, Response } from 'express'
import FacultyDAO from '../daos/faculty_dao'

import { SQL_FACULTY } from '../repositories/faculty_sql'


class FacultyController extends FacultyDAO {
    public getFaculties = (req: Request, res: Response): void => {
        FacultyDAO.getFaculties(SQL_FACULTY.ALL, [], res)
    }
}


const facultyController = new FacultyController()
export default facultyController
```

### Faculty Endpoint

Creamos un endpoint para las facultades:

```ts
import { Router } from 'express'
import facultyController from '../controllers/faculty.controller';


class FacultyRoutes {
    public facultyRoutes: Router

    constructor() {
        this.facultyRoutes = Router()
        this.config()
    }

    /**
     * This is a function that is called when the class is instantiated. 
     */
    public config = (): void => {
        this.facultyRoutes.get('/', facultyController.getFaculties)
    }
}


const facultyRoutes = new FacultyRoutes()
export default facultyRoutes.facultyRoutes
```

## Professors

### Professor Repository

Creamos un nuevo objeto con las sentencias para hacer consultas sobre la tabla de professors:

```ts
export const SQL_PROFESSOR = {
    ALL: 'SELECT \ 
        p.professor_id, p.faculty_id, p.professor_doc, p.professor_name, p.professor_surname, p.professor_type \ 
        FROM professor p'
}
```

### Professor DAO (Data Access Object)

Creamos una clase DAO para poder hacer uso de la consulta:

```ts
import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB'


class ProfessorDAO {
    protected static getProfessors = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red('Error in ProfessorDAO: '), error)
            res.status(400).json({ ok: false })
        }
    }
}


export default ProfessorDAO
```

### Professor Controller

El controlador para el endpoint de profesores extiende del DAO y usa el método para obtener la información.

```ts
import { Request, Response } from "express";
import ProfessorDAO from "../daos/professor.dao";
import { SQL_PROFESSOR } from "../repositories/professor.sql";


class ProfessorController extends ProfessorDAO {
    /* This is a function that will be called when the user requests the `/professors` endpoint. */
    public getProfessors = (req: Request, res: Response): void => {
        ProfessorDAO.getProfessors(SQL_PROFESSOR.ALL, [], res)
    }
}


const professorController = new ProfessorController()
export default professorController
```

### Professor Endpoint

Creamos un nuevo endpoint dentro de las rutas:

```ts
import { Router } from 'express'
import professorController from '../controllers/professor.controller';


class ProfessorRoutes {
    public professorRoutes: Router

    constructor() {
        this.professorRoutes = Router()
        this.config()
    }

    /**
     * This is a function that is called when the class is instantiated. 
     */
    public config = (): void => {
        this.professorRoutes.get('/', professorController.getProfessors)
    }
}


const professorRoutes = new ProfessorRoutes()
export default professorRoutes.professorRoutes
```

## Server

Vamos a crear una clase llamada `Server` en el que tenemos la configuración de la aplicación, las rutas, y el método para la escucha del server.

Usaré variables de entorno, por lo que instalamos el módulo `dotenv` con el comando:

```txt
npm i dotenv
```

```ts
'use strict'

import express, { Application, json, urlencoded } from 'express'
import cors from 'cors'
import morgan from 'morgan'
import { green, italic } from 'colors'
import facultyRoutes from '../../routes/faculty.routes'
import professorRoutes from '../../routes/professor.routes';


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        faculties: '/api/faculties',
        professors: '/api/professors',
    }

    constructor() {
        this._app = express()
        this._port = process.env.PORT || '8081'
        this.config()
        this.routes()
    }

    public config = (): void => {
        this._app.set('PORT', this._port)
        this._app.use(cors())
        this._app.use(morgan('dev'))
        this._app.use(json({ limit: '100mb' }))
        this._app.use(urlencoded({ extended: true }))
    }

    public routes = (): void => {
        this._app.use(this._paths.faculties, facultyRoutes)
        this._app.use(this._paths.professors, professorRoutes)
    }

    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}`))
            console.log(`     - faculties ${italic.underline(`http://localhost:${this._port}${this._paths.faculties}`)}`)
            console.log(`     - professors ${italic.underline(`http://localhost:${this._port}${this._paths.professors}`)}`)
        })
    }
}


export default Server
```

## Servicio que muestra los tipos y el nombre de la facultad

### Consulta SQL

Creamos una query de SQL que nos permita obtener la información de los usuarios, pero retornando los tipos de docentes a manera de string, y además, mostrando el nombre de la facultad a la que pertenece, teniendo en cuenta el id de la misma. Dentro del repositorio de las sentencias de los docentes, añadimos la consulta:

```ts
export const SQL_PROFESSOR = {
    ...,
    TYPE_AND_FACULTY: "SELECT \
        p.professor_id, p.professor_name, p.professor_surname, p.professor_doc, f.faculty_name, \
        CASE p.professor_type \
            WHEN 1 THEN 'Decano' \
            WHEN 2 THEN 'Docente Titular' \
            WHEN 3 THEN 'Docente Asociado' \
            WHEN 4 THEN 'Docente Asistente' \
            WHEN 5 THEN 'Docente Auxiliar' \
            WHEN 6 THEN 'Other' \
        END AS professor_type\
        FROM professor p, faculty f \
        WHERE p.faculty_id = f.faculty_id"
}
```

Por el momento usaremos la misma función del DAO de los docentes, pero en su implementación en el contralor hacemos la implementación de la sentencia SQL:

```ts
class ProfessorController extends ProfessorDAO {
    ...
    /* This is a function that will be called when the user requests the `/professors/type-and-faculty` endpoint. */
    public getTypeProfessorsAndFaculty = (req: Request, res: Response): void => {
        ProfessorDAO.getProfessors(SQL_PROFESSOR.TYPE_AND_FACULTY, [], res)
    }
}
```

Procedemos a crear una ruta con la que accedemos al servicio:

```ts
class ProfessorRoutes {
    ...
    /**
     * This is a function that is called when the class is instantiated. 
     */
    public config = (): void => {
        ...
        this.professorRoutes.get('/type-and-faculty', professorController.getTypeProfessorsAndFaculty)
    }
}
```

Como en la clase anterior retornamos una propiedad de que contiene toda la configuración de las rutas, entonces no debemos hacer nada dentro de la clase `Server`.

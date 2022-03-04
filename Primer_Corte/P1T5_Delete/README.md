# Delete

## Inicio del proyecto

### Creación del archivo de configuración de paquetes

```txt
npm init -y
```

### Módulos necesarios

```txt
npm install cors morgan express pg-promise colors dotenv
```

### Tipado de los módulos

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

```txt
npm i --save-dev nodemon
```

### Inicializar y configurar TS

```txt
tsc --init
```

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

### DDL (Data Definition Language)

```sql
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
/* Table: Constraints                                              */
/* =============================================================== */

ALTER TABLE program OWNER TO user_node;
CREATE UNIQUE INDEX index_program_name ON program (program_name);


ALTER TABLE pensum OWNER TO user_node;
ALTER TABLE pensum ADD CONSTRAINT
    FK_PROGRAM_PENSUM FOREIGN KEY(pensum_id)
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
```

### DML (Data Manipulation Language)

```sql
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

INSERT INTO subjects (subject_name, subject_reference) VALUES ('Desarrollo Orientado a Servicios', 'IS');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('POO', 'IS');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('UI/UX', 'IS');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('Práctica Empresarial', 'IS');
INSERT INTO subjects (subject_name, subject_reference) VALUES ('Trabajo de Grado I', 'IS');


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
```

## Configurar proyecto para conectar con la base de datos

### Objeto con variables de entorno

```ts
export const vars = {
    user: 'user_node',
    host: 'localhost',
    database: 'db_p1t5',
    password: '1234567890',
    port: 5433
}
```

### Camelizar las columnas

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

```ts
'use strict'

import { red, blue } from 'colors'
import pgPromise from 'pg-promise'

import ConnectionOptions from './connection_options'
import { vars } from '../domains/var_db.config'


class ConnectionDB {
    private _pgp = pgPromise(ConnectionOptions.pgOptions)
    public pool

    constructor() {
        this.pool = this._pgp(vars)
        this.connectPool()
    }

    connectPool = async () => {
        try {
            const connection = await this.pool.connect()
            connection.done()
            console.log(blue.italic(`> Conexión establecida con la base de datos ${vars.database}\n`))
        } catch (error) {
            console.log(red('Error in ConnectionDB: '), error)
        }
    }
}


const connectionDB = new ConnectionDB()
export default connectionDB
```

## Program

### Program Repository

```ts
export const SQL_PROGRAMS = {
    ALL: 'SELECT p.program_id, p.program_name FROM program p',
    CONFIRM: 'SELECT COUNT(p.program_name) AS amount FROM program p WHERE LOWER(p.program_name) = LOWER($1)',
    CREATE: 'INSERT INTO program (program_name) VALUES ($1) RETURNING program_id'
}
```

### Program DAO (Data Access Object)

#### Obtener todos los programas

```ts
'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class ProgramDAO {
    protected static getPrograms = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            return res.status(400).json({ ok: false })
        }
    }
}


export default ProgramDAO
```

#### Crear un programa

```ts
'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class ProgramDAO {
    ...
    protected static postProgram = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        await connectionDB.pool.task(async query=> {
            const { amount } = await query.one(sqlConfirm, parameters)
            if (parseInt(amount) === 0) {
                return await query.one(sqlCreate, parameters)
            }
            else {
                return { programId: 0, amount }
            }
        })
            .then(({ programId, amount }) => {
                if (programId !== 0) {
                    return res.status(201).json({ ok: true, msg: 'Programa creado', newId: programId })
                }
                else return res.status(400).json({ ok: false, msg: 'Programa ya existente', amount })
            })
            .catch((error: any) => {
                console.log(red('Error'), error)
                return res.status(400).json({ ok: false })
            })
    }
}

export default ProgramDAO
```

### Program Controller

```ts
import { Request, Response } from 'express'
import ProgramDAO from '../daos/program.dao'
import { SQL_PROGRAMS } from '../repositories/program.sql';


class ProgramController extends ProgramDAO {
    public getPrograms = (req: Request, res: Response): void => {
        ProgramDAO.getPrograms(SQL_PROGRAMS.ALL, [], res)
    }

    public postProgram = (req: Request, res: Response): void => {
        const { programName } = req.body
        ProgramDAO.postProgram(SQL_PROGRAMS.CONFIRM, SQL_PROGRAMS.CREATE, [programName], res)
    }
}


const programController = new ProgramController()
export default programController
```

### Program Endpoints

```ts
'use strict'

import { Router } from 'express'
import programController from '../controllers/program.controller'


class ProgramRoutes {
    public programRoutes: Router

    constructor() {
        this.programRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.programRoutes.get('/', programController.getPrograms)
        this.programRoutes.post('/create-program', programController.postProgram)
    }
}


const programRoutes = new ProgramRoutes()
export default programRoutes.programRoutes
```

## Server

```ts
'use strict'

import express, { Application, json, urlencoded } from 'express'
import cors from 'cors'
import morgan from 'morgan'
import { green, italic } from 'colors'
import programRoutes from '../../routes/program.routes'


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        programs: '/api/programs',
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
        this._app.use(this._paths.programs, programRoutes)
    }

    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}`))
            console.log(`     - programs ${italic.underline(`http://localhost:${this._port}${this._paths.programs}`)}`)
        })
    }
}


export default Server
```

## Index

```ts
import Server from "./config/api/server"

const server = new Server()

console.clear()
server.init()
```

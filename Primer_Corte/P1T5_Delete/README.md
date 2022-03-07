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
    ALL: 'SELECT p.program_id, p.program_name FROM program p ORDER BY p.program_name',
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
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
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
                return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
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

## Semester

### Semester Repository

```ts
export const SQL_SEMESTER = {
    ALL: 'SELECT s.semester_id, s.semester_name FROM semester s ORDER BY s.semester_name',
    CONFIRM: 'SELECT COUNT(s.semester_name) AS amount FROM semester s WHERE LOWER(s.semester_name) = LOWER($1)',
    CREATE: 'INSERT INTO semester (semester_name) VALUES ($1) RETURNING semester_id'
}
```

### Semester DAO (Data Access Object)

#### Obtener todos los semestres

```ts
'use strict'

import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB';


class SemesterDao {
    protected static getSemesters = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default SemesterDao
```

#### Crear un semestre

```ts
'use strict'

import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB';


class SemesterDao {
    ...
    protected static postSemester = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response) => {
        try {
            const { semesterId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirm, parameters)
                if (parseInt(amount) === 0) {
                    return await query.one(sqlCreate, parameters)
                } else {
                    return { semesterId: 0, amount }
                }
            })
            if(semesterId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Semestre creado', newId: semesterId })
            } 
            else return res.status(400).json({ ok: false, msg: 'Semestre ya existente', amount })
        } catch (error: any) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default SemesterDao
```

### Semester Controller

```ts
'use strict'

import { Request, Response } from "express";

import SemesterDao from "../daos/semester.dao";
import { SQL_SEMESTER } from '../repositories/semester.sql';


class SemesterController extends SemesterDao {
    public getSemesters = (req: Request, res: Response): void => {
        SemesterDao.getSemesters(SQL_SEMESTER.ALL, [], res)
    }

    public postSemester = (req: Request, res: Response): void => {
        const { semesterName } = req.body
        SemesterDao.postSemester(SQL_SEMESTER.CONFIRM, SQL_SEMESTER.CREATE, [semesterName], res)
    }
}


const semesterController = new SemesterController()
export default semesterController
```

### Semester Endpoints

```ts
'use strict'

import { Router } from "express"
import semesterController from '../controllers/semester.controller';


class SemesterRoutes {
    public semesterRoutes: Router

    constructor() {
        this.semesterRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.semesterRoutes.get('/', semesterController.getSemesters)
        this.semesterRoutes.post('/create-semester', semesterController.postSemester)
    }
}


const semesterRoutes = new SemesterRoutes()
export default semesterRoutes.semesterRoutes
```

## Subjects

### Subjects Repository

```ts
export const SQL_SUBJECT = {
    ALL: 'SELECT s.subject_id, s.subject_name, s.subject_reference FROM subjects s ORDER BY s.subject_name',
    CONFIRM: 'SELECT COUNT(s.subject_reference) AS amount FROM subjects s WHERE LOWER(s.subject_reference) = LOWER($2)',
    CREATE: 'INSERT INTO subjects (subject_name, subject_reference) VALUES ($1, $2) RETURNING subject_id'
}
```

### Subjects DAO (Data Access Object)

#### Obtener todos las materias

```ts
'use strict'

import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB'


class SubjectDAO {
    protected static getSubjects = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}


export default SubjectDAO
```

#### Crear una materia

```ts
'use strict'

import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB'


class SubjectDAO {
    ...
    protected static postSubject = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { subjectId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirm, parameters)
                if (amount === '0') {
                    return query.one(sqlCreate, parameters)
                } 
                else return { subjectId: 0, amount }
            })
            if (subjectId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Materia creada', subjectId })
            }
            else return res.status(400).json({ ok: false, msg: 'Materia ya existente', amount })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}


export default SubjectDAO
```

### Subject Controller

```ts
import { Request, Response } from 'express'
import SubjectDAO from '../daos/subject.dao'
import { SQL_SUBJECT } from '../repositories/subject.sql';


class SubjectController extends SubjectDAO {
    public getSubjects = (req: Request, res: Response): void => {
        SubjectDAO.getSubjects(SQL_SUBJECT.ALL, [], res)
    }

    public postSubject = (req: Request, res: Response): void => {
        const { subjectName, subjectReference } = req.body
        const params = [...subjectName, ...subjectReference]
        SubjectDAO.postSubject(SQL_SUBJECT.CONFIRM, SQL_SUBJECT.CREATE, params, res)
    }
}


const subjectController = new SubjectController()
export default subjectController
```

### Subject Endpoints

```ts
'use strict'

import { Router } from "express";
import subjectController from "../controllers/subject.controller";


class SubjectRoutes {
    public subjectsRoutes: Router

    constructor() {
        this.subjectsRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.subjectsRoutes.get('/', subjectController.getSubjects)
        this.subjectsRoutes.post('/create-subject', subjectController.postSubject)
    }
}


const subjectsRoutes = new SubjectRoutes()
export default subjectsRoutes.subjectsRoutes
```

## Pensum

### Pensum Repository

```ts
export const SQL_PENSUM = {
    ALL: 'SELECT p.pensum_id, p.program_id, pr.program_name, p.pensum_name \
        FROM pensum p, program pr \
        WHERE p.program_id = pr.program_id \
        ORDER BY pr.program_name',
    CONFIRM_PROGRAM: 'SELECT COUNT(pr.program_id) AS program_amount \
        FROM program pr \
        WHERE pr.program_id = $1',
    CONFIRM_PENSUM: 'SELECT COUNT(p.pensum_name) AS amount \
        FROM pensum p \
        WHERE LOWER(p.pensum_name) = LOWER($2)',
    CREATE: 'INSERT INTO pensum (program_id, pensum_name) \
        VALUES ($1, $2) \
        RETURNING pensum_id'
}
```

### Pensum DAO (Data Access Object)

#### Obtener todos los pensum

```ts
import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB';


class PensumDAO {
    protected static getPensums = async (sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, params)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default PensumDAO
```

#### Crear un Pensum

```ts
import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB';


class PensumDAO {
    ...
    protected static postPensum = async (sqlConfirmProgram: string, sqlConfirmPensum: string, sqlCreate: string, params: any, res: Response): Promise<any> => {
        try {
            const { programAmount } = await connectionDB.pool.one(sqlConfirmProgram, params)
            if (parseInt(programAmount) === 0) {
                return res.status(400).json({ ok: false, msg: `No existe ningún programa con el id ${params[0]}` })
            }
            const { pensumId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirmPensum, params)
                if (amount === '0') return query.one(sqlCreate, params)
                return { pensumId: 0, amount }
            })
            if (pensumId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Pensum creado', newId: pensumId })
            }
            return res.status(400).json({ ok: false, msg: 'Pensum ya existente', amount })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default PensumDAO
```

### Pensum Controller

```ts
import { Request, Response } from 'express'
import PensumDAO from '../daos/pensum.dao';
import { SQL_PENSUM } from '../repositories/pensum.repository';


class PensumController extends PensumDAO {
    public getPensums = (req: Request, res: Response): void => {
        PensumDAO.getPensums(SQL_PENSUM.ALL, [], res)
    }

    public postPensum = (req: Request, res: Response): void => {
        const { programId, pensumName } = req.body
        const params = [programId, pensumName]
        PensumDAO.postPensum(SQL_PENSUM.CONFIRM_PROGRAM, SQL_PENSUM.CONFIRM_PENSUM, SQL_PENSUM.CREATE, params, res)
    }
}


const pensumController = new PensumController()
export default pensumController
```

### Pensum Endpoints

```ts
import { Router } from "express";
import pensumController from "../controllers/pensum.controller";


class PensumRoutes {
    public pensumRoutes: Router

    constructor() {
        this.pensumRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.pensumRoutes.get('/', pensumController.getPensums)
        this.pensumRoutes.post('/create-pensum', pensumController.postPensum)
    }
}


const pensumRoutes = new PensumRoutes()
export default pensumRoutes.pensumRoutes
```

## Pensum-Subject

### Pensum-Subject Repository

```ts
export const SQL_PENSUM_SUBJECT = {
    ALL: 'SELECT ps.pensum_id, p.pensum_name, ps.semester_id, s.semester_name, ps.subject_id, su.subject_name \
        FROM pensum_subject ps, pensum p, semester s, subjects su \
        WHERE ps.pensum_id = p.pensum_id AND ps.semester_id = s.semester_id AND ps.subject_id = su.subject_id \
        ORDER BY ps.pensum_id',
    CONFIRM_PENSUM: 'SELECT COUNT(p.pensum_id) AS pensum_amount\
        FROM pensum p \
        WHERE p.pensum_id = $1',
    CONFIRM_SUBJECT: 'SELECT COUNT(su.subject_id) AS subject_amount \
        FROM subjects su \
        WHERE su.subject_id = $1',
    CONFIRM_SEMESTER: 'SELECT COUNT(s.semester_id) AS semester_amount \
        FROM semester s \
        WHERE s.semester_id = $1',
    CONFIRM_PENSUM_SUBJECT: 'SELECT COUNT(ps.pensum_id) AS p_amount, COUNT(ps.subject_id) AS s_amount \
        FROM pensum_subject ps \
        WHERE ps.pensum_id = $1 AND ps.subject_id = $2',
    CREATE: 'INSERT INTO pensum_subject (pensum_id, subject_id, semester_id) \
        VALUES ($1, $2, $3) \
        RETURNING pensum_id, subject_id, semester_id'
}
```

### Pensum-Subject DAO (Data Access Object)

#### Obtener todos los pensum-subject

```ts
import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB';


class PensumSubjectDAO {
    protected static getPensumSubject = async (sqlQuery: string, params: any, res: Response) => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, params)
            return res.status(200).json({ ok: true, resultQuery: rows }) 
        } catch (error) {
            console.log(red('Error'), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}


export default PensumSubjectDAO
```

#### Postear un nuevo pensum-subject

```ts
import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB';


class PensumSubjectDAO {
    ...
    protected static postPensumSubject = async (sqlConfirmPensum: string, sqlConfirmSubject: string, sqlConfirmSemester: string, sqlConfirmPensumSubject: string, sqlCreate: string, params: any, res: Response) => {
        try {
            const { pensumAmount } = await connectionDB.pool.one(sqlConfirmPensum, params[0])
            const { subjectAmount } = await connectionDB.pool.one(sqlConfirmSubject, params[1])
            const { semesterAmount } = await connectionDB.pool.one(sqlConfirmSemester, params[2])
            if (pensumAmount === '0') return res.status(400).json({ ok: false, msg: `No hay pensum con el id ${params[0]}` })
            if (subjectAmount === '0') return res.status(400).json({ ok: false, msg: `No hay materias con el id ${params[1]}` })
            if (semesterAmount === '0') return res.status(400).json({ ok: false, msg: `No hay semestres con el id ${params[2]}` })

            const { pensumId, subjectId, semesterId } = await connectionDB.pool.task(async query => {
                const { pAmount, sAmount } = await query.one(sqlConfirmPensumSubject, params)
                if (pAmount !== '0' && sAmount !== '0') {
                    return { pensumId: 0, subjectId: 0, semesterId: 0 }
                }
                else return query.one(sqlCreate, params)
            })
            if (pensumId === 0 && subjectId === 0 && semesterId === 0) {
                return res.status(400).json({ ok: false, msg: 'Ya existe una relación del pensum con la materia' })
            }
            return res.status(201).json({ ok: true, msg: 'Relación pensum-materia ha sido creada', pensumId, subjectId, semesterId })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default PensumSubjectDAO
```

### Pensum-Subject Controller

```ts
import { Request, Response } from 'express'
import PensumSubjectDAO from '../daos/pensum-subject.dao';
import { SQL_PENSUM_SUBJECT } from '../repositories/pensum-subject.repository';


class PensumSubjectController extends PensumSubjectDAO {
    public getPensumSubject = (req: Request, res: Response) => {
        PensumSubjectDAO.getPensumSubject(SQL_PENSUM_SUBJECT.ALL, [], res)
    }

    public postPensumSubject = (req: Request, res: Response) => {
        const { pensumId, subjectId, semesterId } = req.body
        const params = [pensumId, subjectId, semesterId]
        PensumSubjectDAO.postPensumSubject(
            SQL_PENSUM_SUBJECT.CONFIRM_PENSUM,
            SQL_PENSUM_SUBJECT.CONFIRM_SUBJECT,
            SQL_PENSUM_SUBJECT.CONFIRM_SEMESTER,
            SQL_PENSUM_SUBJECT.CONFIRM_PENSUM_SUBJECT,
            SQL_PENSUM_SUBJECT.CREATE,
            params,
            res
        )
    }
}


const pensumSubjectController = new PensumSubjectController()
export default pensumSubjectController
```

### Pensum-Subject Endpoints

```ts
import { Router } from 'express'
import pensumSubjectController from '../controllers/pensum-subject.controller';


class PensumSubjectRoutes {
    public pensumSubjectRoutes: Router

    constructor() {
        this.pensumSubjectRoutes = Router()
        this.config()
    }

    public config = () => {
        this.pensumSubjectRoutes.get('/', pensumSubjectController.getPensumSubject)
        this.pensumSubjectRoutes.post('/create-pensum-subject', pensumSubjectController.postPensumSubject)
    }
}


const pensumSubjectRoutes = new PensumSubjectRoutes()
export default pensumSubjectRoutes.pensumSubjectRoutes
```

## Server

```ts
'use strict'

import express, { Application, json, urlencoded } from 'express'
import cors from 'cors'
import morgan from 'morgan'
import { green, italic } from 'colors'

import programRoutes from '../../routes/program.routes'
import semesterRoutes from '../../routes/semester.routes'
import subjectRoutes from '../../routes/subject.routes'
import pensumRoutes from '../../routes/pensum.routes'
import pensumSubjectRoutes from '../../routes/pensum-subject.routes'


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        programs: '/api/programs',
        semesters: '/api/semesters',
        subjects: '/api/subjects',
        pensums: '/api/pensums',
        pensumSubject: '/api/pensum-subject'
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
        this._app.use(this._paths.semesters, semesterRoutes)
        this._app.use(this._paths.subjects, subjectRoutes)
        this._app.use(this._paths.pensums, pensumRoutes)
        this._app.use(this._paths.pensumSubject, pensumSubjectRoutes)
    }

    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}\n\n`))
            console.log(`     - programs ${italic.underline(`http://localhost:${this._port}${this._paths.programs}`)}`)
            console.log(`     - semesters ${italic.underline(`http://localhost:${this._port}${this._paths.semesters}`)}`)
            console.log(`     - subjects ${italic.underline(`http://localhost:${this._port}${this._paths.subjects}`)}`)
            console.log(`     - pensums ${italic.underline(`http://localhost:${this._port}${this._paths.pensums}`)}`)
            console.log(`     - pensum-subject ${italic.underline(`http://localhost:${this._port}${this._paths.pensumSubject}`)}`)
            console.log('\n')
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

## Documentación de los Endpoints en Postman

Podemos generar la documentación de la colección que administra todas las rutas de este proyecto, y hacerlas publicas en la web. Por ejemplo, para este caso, esta sería la documentación para este backend REST: [Endpoint Backend - Falso SAC](https://documenter.getpostman.com/view/8438809/UVktqtTL)

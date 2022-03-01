# Candidatos Políticos y uso de un API Client

## Configuración inicial

### Inicio del proyecto

Empezamos con el comando para crear el archivo gestor de paquetes:

```txt
npm init -y
```

### Instalar módulos necesarios

```txt
npm install cors morgan express pg-promise colors dotenv
```

### Instalar el tipado de los módulos

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
CREATE DATABASE db_p1t4
    WITH 
    OWNER = user_node
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```

```sql
CREATE TABLE political_party (
    party_id SERIAL NOT NULL,
    party_name VARCHAR(200) NOT NULL,
    CONSTRAINT PK_PARTY PRIMARY KEY(party_id)
);

ALTER TABLE political_party OWNER TO user_node;


CREATE TABLE candidate (
    candidate_id SERIAL NOT NULL,
    party_id int4 NOT NULL,
    candidate_name VARCHAR(200) NOT NULL,
    candidate_date_birth DATE NOT NULL,
    candidate_evaluation NUMERIC(12,2) NOT NULL,
    CONSTRAINT PK_CANDIDATE PRIMARY KEY(candidate_id)
);

ALTER TABLE candidate OWNER TO user_node;
ALTER TABLE candidate ADD CONSTRAINT
    FK_PARTY_CANDIDATE FOREIGN KEY(party_id)
    REFERENCES political_party (party_id)
    ON DELETE RESTRICT ON UPDATE CASCADE;
```

### DML (Data Manipulation Language)

```sql
INSERT INTO political_party(party_name) VALUES ('Partido 0');
INSERT INTO political_party(party_name) VALUES ('Partido 1');
INSERT INTO political_party(party_name) VALUES ('Partido 2');
INSERT INTO political_party(party_name) VALUES ('Partido 3');
INSERT INTO political_party(party_name) VALUES ('Partido 4');
INSERT INTO political_party(party_name) VALUES ('Partido 5');
INSERT INTO political_party(party_name) VALUES ('Partido 6');


INSERT INTO candidate(party_id, candidate_name, candidate_date_birth, candidate_evaluation) 
    VALUES (1, 'Ferrer', '2000-01-31', 1.3);
INSERT INTO candidate(party_id, candidate_name, candidate_date_birth, candidate_evaluation) 
    VALUES (2, 'David', '2000-12-31', 1.3);
INSERT INTO candidate(party_id, candidate_name, candidate_date_birth, candidate_evaluation) 
    VALUES (3, 'Carlos', '2001-02-15', 5.0);
INSERT INTO candidate(party_id, candidate_name, candidate_date_birth, candidate_evaluation) 
    VALUES (4, 'Páez', '2001-06-30', 3.9);
INSERT INTO candidate(party_id, candidate_name, candidate_date_birth, candidate_evaluation) 
    VALUES (5, 'Charles', '1956-07-31', 4.7);
```

## Configurar proyecto para conectar con la base de datos

### Objeto con variables de entorno

```ts
export const vars = {
    user: 'user_node',
    host: 'localhost',
    database: 'db_p1t4',
    password: '1234567890',
    port: 5433
}
```

### Camelizar los datos

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

### Conexión a la base de datos

```ts
'use strict'

import { red, blue } from 'colors'
import pgPromise from 'pg-promise'

import ConnectionOptions from './connection_options'
import { vars } from '../domains/var_db.config'


class ConnectionDB {
    private _pgp = pgPromise(ConnectionOptions.pgOptions)
    public pool: any

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

## Political Party

### Political Party Repository

```ts
export const SQL_POLITICAL_PARTY = {
    ALL: 'SELECT p.party_id, p.party_name FROM political_party p'
}
```

### Political Party DAO (Data Access Object)

```ts
'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class PoliticalPartyDAO {
    protected static getPoliticalParties = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            return res.status(400).json({ ok: false })
        }
    }
}


export default PoliticalPartyDAO
```

### Political Faculty Controller

```ts
import { Request, Response } from 'express'

import PoliticalPartyDAO from '../daos/political_party.dao';
import { SQL_POLITICAL_PARTY } from '../repositories/political_party.sql'


class PoliticalPartyController extends PoliticalPartyDAO {
    public getPoliticalParties = (req: Request, res: Response): void => {
        PoliticalPartyDAO.getPoliticalParties(SQL_POLITICAL_PARTY.ALL, [], res)
    }
}


const politicalPartyController = new PoliticalPartyController()
export default politicalPartyController
```

### Political Party Endpoint

```ts
import { Router } from 'express'

import politicalPartyController from '../controllers/political_party.controller';


class PoliticalPartyRoutes {
    public politicalPartyRoutes: Router

    constructor() {
        this.politicalPartyRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.politicalPartyRoutes.get('/', politicalPartyController.getPoliticalParties)
    }
}


const politicalPartyRoutes = new PoliticalPartyRoutes()
export default politicalPartyRoutes.politicalPartyRoutes
```

## Candidate

### Candidate Party Repository

```ts
export const SQL_CANDIDATE = {
    ALL: 'SELECT c.candidate_id, c.candidate_name, c.candidate_date_birth, c.candidate_evaluation, p.party_name \
        FROM candidate c, political_party p \
        WHERE c.party_id = p.party_id'
}
```

### Candidate DAO (Data Access Object)

```ts
'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class CandidateDAO {
    protected static getCandidates = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            return res.status(400).json({ ok: false })
        }
    }
}


export default CandidateDAO
```

### Candidate Controller

```ts
import { Request, Response } from 'express'

import CandidateDAO from '../daos/candidate.dao'
import { SQL_CANDIDATE } from '../repositories/candidate.sql'



class CandidateController extends CandidateDAO {
    public getCandidates = (req: Request, res: Response): void => {
        CandidateDAO.getCandidates(SQL_CANDIDATE.ALL, [], res)
    }
}


const candidateController = new CandidateController()
export default candidateController
```

### Candidate Endpoint

```ts
import { Router } from 'express'

import candidateController from '../controllers/candidate.controller'


class CandidateRoutes {
    public candidateRoutes: Router

    constructor() {
        this.candidateRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.candidateRoutes.get('/', candidateController.getCandidates)
    }
}


const candidateRoutes = new CandidateRoutes()
export default candidateRoutes.candidateRoutes
```

## Server

```ts
'use strict'

import express, { Application, json, urlencoded } from 'express'
import cors from 'cors'
import morgan from 'morgan'
import { green, italic } from 'colors'

import political_partyRoutes from '../../routes/political_party.routes'
import candidateRoutes from '../../routes/candidate.routes'


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        political_parties: '/api/political-parties',
        candidates: '/api/candidates',
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
        this._app.use(this._paths.political_parties, political_partyRoutes)
        this._app.use(this._paths.candidates, candidateRoutes)
    }

    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}`))
            console.log(`     - faculties ${italic.underline(`http://localhost:${this._port}${this._paths.political_parties}`)}`)
            console.log(`     - professors ${italic.underline(`http://localhost:${this._port}${this._paths.candidates}`)}`)
        })
    }
}


export default Server
```

## Index

```ts
import Server from "./config/api/server";


const server = new Server()

console.clear()
server.init()
```

## API Client (Postman, Thunder, Insomnia)

Vamos a crear un entorno de desarrollo dentro de las API Clients, con el fin de manejar las colecciones con las request que se realicen a nuestros servicios. Para este caso, tenemos una colección con el mismo nombre del proyecto, y dentro hacemos las primeras 2 peticiones que tenemos para nuestro server. Con Postman, podemos publicar la documentación de los endpoints de cada colección, por ejemplo la documentación de las rutas para los servicios de este backend es el siguiente: [P1T4_Candidatos](https://documenter.getpostman.com/view/8438809/UVksLZFF)

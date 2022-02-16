# Primer Backend con Node.JS

## Configuración inicial

Para crear el archivo `package.json` usamos el comando `npm init`. En caso de que se quiera crear un archivo con información por defecto, usamos el comando `npm init -y`, pero modificamos el contenido del mismo.

## Estructura inicial del proyecto

El proyecto tendrá el siguiente árbol de archivos:

```txt
P1T1_DOS
    |___ src
        |___ config
        |___ controllers
        |___ daos
        |___ middlewares
        |___ models
        |___ repositories
        |___ routes
    |___ index.ts
```

## Primeros paquetes

Vamos a instalar las librerías ***CORS*** (Cross-origin resource sharing), ***Morgan*** para formatear la salida en consola, ***Express*** para el montaje del backend, ***JSONWebToken*** para los token de validación, ***PG-Promise*** para conectarnos a PostgreSQL, con el comando `npm i cors morgan express jsonwebtoken pg-promise`.

En caso de querer remover alguna librería usamos el comando `npm uninstall <librería>`.

Par poder observar las dependencias instaladas, debemos observar el archivo `package.json` en la sección con el mismo nombre.

## Types

Necesitamos sacar los tipos para la librería de CORS, morgan, express y jsonwebtoken, para ello usamos los comandos:

```txt
npm i @types/cors --save-dev
```

```txt
npm i @types/morgan --save-dev
```

```txt
npm i @types/express --save-dev
```

```txt
npm i @types/jsonwebtoken --save-dev
```

Las dependencias que se guardan en desarrollo, las podemos observar en la sección `devDependencies` del archivo `package.json`

## Nodemon

Necesitamos instalar la librería Nodemon con el comando `npm i nodemon`, la cual nos permite recargar el servidor cada que detecte un nuevo cambio en los archivos.

## Scripts

Dentro del archivo `package.json`, en la sección de scripts vamos a configurar nuestros scripts personalizados:

```json
{
    ...
    "scripts": {
        "test": "",
        "dev": "nodemon build/index.js",
        "build": "tsc -w"
    },
    ...
}
```

## Inicialización de TS

Para poder definir que en nuestro proyecto vamos a usar TypeScript, usamos el comando `npm i -g typescript` para instalar TS y luego lo llamamos con `tsc --init`. Este último comando nos genera un archivo llamado `tsconfig.json`, dentro del cual hacemos la siguiente configuración:

```json
{
    "compilerOptions": {
        ...,
        "target": "es2016",
        ...,
        "outDir": "./build",    
        ...,
    }
}
```

## Primer DAO

Vamos a crear un archivo para DAO (Data Access Object). Como es un archivo de ejemplo, no tendremos data ni peticiones reales, pero si vamos a crear la estructura de como serán nuestro próximos DAOs:

```ts
import { Request, Response } from 'express'


class DAO {
    protected static async getInfo(req: Request, res: Response): Promise<any> {
        const json = {
            professors: ['Carlos Andrés', 'Harvey'],
            course: 'DOS'
        }

        return Promise.resolve(json)
            .then((response: any) => {
                console.log('Respuesta: ', response)
                res.status(200).json({
                    ok: true,
                    response
                })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(`Error en getInfo(): ${error}`,)
            })
    }
}
```

El método anterior nos permite que solo las clases que practiquen la herencia, puedan acceder al método de esta clase mediante la palabra reservada `protected`. Mediante la palabra reservada `static`, podemos acceder a la función sin tener que crear una instancia de la clase usando la notación punto: `<Clase>.<miembro_estático>` ([TypeScript Static - TutorialsTeacher](https://www.tutorialsteacher.com/typescript/typescript-static)).

Otra característica del método es que es asíncrono, por lo que espera una respuesta. `async` transforma las funciones en las que se usa, en Promesas. El método `getInfo()` recibe como parámetros una petición y una respuesta y luego retorna una Promesa de tipo `any`. La promesa resuelve el json, y cuando todo sea correcto envía un código de status 200 y una respuesta de tipo json. En caso de error se retornar un código de error 400 y un Error en consola.

Ahora tenemos que hacer la exportación de la clase DAO, para poderlo usar en cualquier parte de nuestro proyecto:

```ts
export default DAO
```

## Primer Controller

Vamos a crear un nuevo archivo dentro de la carpeta de los controllers. Dentro del mismo creamos una clase que vamos a inicializar y exportar, pero con el fin de solo enviar una instancia a diferencia del DAO que ya envía el método que contiene mediante la palabra `static`:

```ts
class Controller { ... }

const controller = new Controller()
export default controller
```

Nuestro controlador va a heredar de DAO y luego dentro del método que va a contener, va a usar el método heredado de la clase padre:

```ts
import { Request, Response } from 'express'
import DAO from '../daos/dao'


class Controller extends DAO {
    public attackMe(req: Request, res: Response): void {
        Controller.getInfo(req, res)
    }
}


const controller = new Controller()
export default controller
```

## Primera Ruta

Vamos a crear un archivo para gestionar las rutas de la aplicación. Importamos el módulo `Router` de express y la instancia del controller. Dentro de la clase vamos a crear una variable a le que le asignamos valor dentro del constructor como el receptor de la configuración Router, y luego creamos un método para configurar los métodos que afectan a la ruta. Nuestra clase exporta el atributo de las rutas.

```ts
import { Router } from 'express'
import controller from '../controllers/controller'


class Routes {
    public testRouteAPI

    constructor() {
        this.testRouteAPI = Router()
        this.config()
    }

    public config() {
        this.testRouteAPI.get('/get-info', controller.attackMe)
    }
}


const routes = new Routes()
export default routes.testRouteAPI
```

## Primer Servidor

Vamos a crear un archivo dentro del directorio `config`. En dicho archivo creamos el primer servidor. La clase va a  contener una propiedad de tipo `app.Application`, también algunos método para poder configurar el server, activar las rutas y levantar el servidor.

El método para configurar el servidor va a hacer un set del puerto, establece el uso de los CORS (Cross-origin resource sharing), usamos a morgan en modo desarrollo, limitamos el tamaño de las respuestas json a 100 MB, y por último configurar la codificación de las rutas.

En el método para configurar las rutas, establecemos la ruta principal y las rutas establecidas por nosotros. En el método de inicializar el server, añadimos el puerto por donde debe escuchar el servidor:

```ts
import cors from 'cors'
import morgan from 'morgan'
import express from 'express'

import routes from '../routes/routes'


class Server {
    private _app: express.Application

    constructor() {
        this._app = express()
        this.config()
        this.routes()
    }

    public config(): void {
        this._app.set('PORT', 8088)
        this._app.use(cors())
        this._app.use(morgan('dev'))
        this._app.use(express.json({ limit: '100mb' }))
        this._app.use(express.urlencoded({ extended : true }))
    }

    public routes(): void {
        this._app.use('/api/test', routes)
    }

    public init(): void {
        this._app.listen(this._app.get('PORT'), () => {
            console.log(`Servidor corriendo en modo local en http://localhost:${this._app.get('PORT')}`)
        })
    }
}


export default Server
```

## index.ts

Dentro del archivo `index.ts` vamos a crear una instancia de la clase server, mediante el cual usamos el método para levantar el servidor.

```ts
import Server from "./config/server";


const server = new Server()


server.init()
```

Ahora vamos a usar la consola de comandos para poder levantar el servidor mediante el comando `npm run build`, esto nos levanta el servidor en modo observación con nodemon. En otra consola ejecutamos el comando `npm run dev` para levantar el servidor en modo desarrollo.

## Acceder al servicio

Dentro de nuestro navegador vamos a acceder a la ruta `http://localhost:8088/api/test/get-info` y podemos observar que se hace la petición y se muestra la data. Recomendable instalar la extensión ***JSON Viewer*** en Chrome.

> En caso se no funcionar revisar: En el constructor de la clase `Server` no se debe llamar el método para escuchar el servidor en x puerto. En el constructor de la clase `Routes` se debe asignar el valor a la variable que almacena el valor de `Router()` y hacer un llamado al método que configura las rutas.

## Subir archivos a GIT

Necesitamos crear un repositorio al que podemos subir la información. Al crear el repositorio se muestran los pasos para la configuración inicial. Es recomendado crear un archivo llamado `.gitignore` en el que añadimos los directorios o archivos que queremos ignorar y no subir al repositorio, como por ejemplo el directorio de `node_modules`.

```txt
node_modules
```

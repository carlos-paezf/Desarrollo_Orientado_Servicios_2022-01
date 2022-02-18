# Taller P1T2

## Inicio del proyecto

Crear el archivo `package.json` con el comando `npm init -y` y modificar su contenido.

## Creación de scripts

Crear los scripts para el proyecto:

```json
{
    ...,
    "scripts": {
        "test": "echo \"Error: no test specified\" && exit 1",
        "dev": "nodemon dist/index.js",
        "build": "tsc -w"
    },
}
```

## Librerías necesarias para el proyecto

Instalar las librerías necesarias con el siguiente comando:

```txt
npm install cors morgan express colors
```

## Tipado de las librerías

Instalar los tipos de las librerías Cors, Morgan y Express para hacerlas compatible con TS:

```txt
npm install @types/cors --save-dev
```

```txt
npm install @types/morgan --save-dev
```

```txt
npm install @types/express --save-dev
```

## Instalar Nodemon

Vamos a instalar Nodemon con el fin de que se pueda recargar el servidor con cada cambio que hacemos, para ello usamos el comando:

```txt
npm i --save-dev nodemon
```

## Inicializar TS en el proyecto

Para inicializar TS dentro del proyecto usamos el comando:

```txt
tsc --init
```

En caso de algún error, asegurarse de tener instalado TS de manera global, lo cual se hace con el comando

```txt
npm i -g typescript
```

## Configurar directorio de transpilación

Configurar el archivo `tsconfig.js` con las siguientes líneas:

```json
{
    "compilerOptions": {
        ...,
        "outDir": "./dist",
        ...,
    }
}
```

## DAO (Data Access Object)

Crear un DAO para poder tener 3 métodos estáticos que retornen la información en json dentro de una promesa. La data que se resuelve, al ser falsa por momento, la voy a crear dentro de archivos ubicados en el directorio `data`, y luego haré la importación de cada objeto:

```ts
import { Request, Response } from 'express'
import colors from 'colors'

import { products, services, users } from '../data'


class DAO {
    protected static getUsersDAO = async (req: Request, res: Response): Promise<any> => {
        return Promise.resolve(users)
            .then((response: any) => {
                console.log(colors.italic('Respuesta: '), response)
                res.status(200).json({ ok: true, response })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(error);
            })
    }

    protected static getProductsDAO = async (req: Request, res: Response): Promise<any> => {
        return Promise.resolve(products)
            .then((response: any) => {
                console.log(colors.italic('Respuesta: '), response)
                res.status(200).json({ ok: true, response })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(error)
            })
    }

    protected static getServicesDAO = async (req: Request, res: Response): Promise<any> => {
        return Promise.resolve(services)
            .then((response: any) => {
                console.log(colors.italic('Respuesta: '), response)
                res.status(200).json({ ok: true, response })
            })
            .catch((error: any) => {
                res.status(400).json({ ok: false })
                throw new Error(error)
            })
    }
}


export default DAO
```

## Controller

Crear un archivo para el controlador que va a ser llamado en las rutas. En este controlador, el cual hereda de DAO, se hace un llamado a los métodos estáticos de la clase padre, y luego se exporta una instancia de la clase:

```ts
import { Request, Response } from 'express'
import DAO from '../dao/DAO';


class Controller extends DAO {
    public getUsers = (req: Request, res: Response): Promise<any> => Controller.getUsersDAO(req, res)

    public getProducts = (req: Request, res: Response): Promise<any> => Controller.getProductsDAO(req, res)
        
    public getServices = (req: Request, res: Response): Promise<any> => Controller.getServicesDAO(req, res)
}


const controller = new Controller()
export default controller
```

## Rutas

Ahora creamos las rutas de nuestra aplicación:

```ts
import { Router } from 'express'
import controller from '../controller/Controller'


class Routes {
    public routes

    constructor() {
        this.routes = Router()
        this.config()
    }

    public config = () => {
        this.routes.get('/get-users', controller.getUsers)
        this.routes.get('/get-products', controller.getProducts)
        this.routes.get('/get-services', controller.getServices)
    }
}


const routes = new Routes()
export default routes.routes
```

## Servidor

Creamos una clase para el servidor. Dentro de dicha clase llamamos las rutas que acabamos de configurar, hacemos configuraciones generales de la app, y añadimos el puesto de escucha:

```ts
import express from 'express'
import cors from 'cors'
import morgan from 'morgan'
import colors from 'colors'
import routes from '../routes/Routes'


class Server {
    private _app: express.Application

    constructor() {
        this._app = express()
        this.config()
        this.routes()
    }

    public config = (): void => {
        this._app.set('PORT', 8888)
        this._app.use(cors())
        this._app.use(morgan('dev'))
        this._app.use(express.json({ limit: '100mb' }))
        this._app.use(express.urlencoded({ extended: true }))
    }

    public routes = (): void => {
        this._app.use('/api/activity', routes)
    }

    public listen = (): void => {
        this._app.listen(this._app.get('PORT'), () => {
            console.log('Servidor corriendo en local: ', colors.underline.green(`http://localhost:${this._app.get('PORT')} \n`))
            console.log(`   ${colors.italic.blue('- getUsers: ')} ${colors.italic(`http://localhost:${this._app.get('PORT')}/api/activity/get-users`)}`)
            console.log(`   ${colors.italic.blue('- getProducts: ')} ${colors.italic(`http://localhost:${this._app.get('PORT')}/api/activity/get-products`)}`)
            console.log(`   ${colors.italic.blue('- getServices: ')} ${colors.italic(`http://localhost:${this._app.get('PORT')}/api/activity/get-services`)}`)
        })
    }
}


export default Server
```

## Archivo principal `index.ts`

Creamos el punto de partida de la aplicación, en el que creamos una instancia del server y llamamos el método de escucha del server:

```ts
import Server from "./config/Server";


const server = new Server()

console.clear()
server.listen()
```

## Levantar el proyecto

Dentro del proyecto ejecutamos el primer comando para hacer la transpilación de TS a JS:

```txt
npm run build
```

Levantamos el proyecto con el comando:

```txt
npm run dev
```

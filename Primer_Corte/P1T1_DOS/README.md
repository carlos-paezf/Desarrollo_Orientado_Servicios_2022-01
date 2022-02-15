# Primer Backend con Node.JS

## Configuración inicial

Para crear el archivo `package.json` usamos el comando `npm init`. En caso de que se quiera crear un archivo por defecto, usamos el comando `npm init -y`, pero modificamos el contenido del mismo.

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
    |___ app.ts
```

## Primeros paquetes

Vamos a instalar las librerías ***CORS*** (Cross-origin resource sharing), ***Morgan*** para formatear la salida en consola, ***Express*** para el montaje del backend, ***JSONWebToken*** para los token de validación, ***PGPromise*** para conectarnos a Postgres, con el comando `npm i cors morgan express jsonwebtoken pg-promise`.

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

        Promise.resolve(json)
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

## Subir archivos a GIT

Necesitamos crear un repositorio al que podemos subir la información. Al crear el repositorio se muestran los pasos para la configuración inicial. Es recomendado crear un archivo llamado `.gitignore` en el que añadimos los directorios o archivos que queremos ignorar y no subir al repositorio, como por ejemplo el directorio de `node_modules`.

```txt
node_modules
```
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

    /**
     * This is where we configure the express server. 
     */
    public config = (): void => {
        this._app.set('PORT', this._port)
        this._app.use(cors())
        this._app.use(morgan('dev'))
        this._app.use(json({ limit: '100mb' }))
        this._app.use(urlencoded({ extended: true }))
    }

    /**
     * This is where we define the routes that the server will respond to. 
     */
    public routes = (): void => {
        this._app.use(this._paths.faculties, facultyRoutes)
        this._app.use(this._paths.professors, professorRoutes)
    }

    /**
     * This is where we start the server.
     */
    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}`))
            console.log(`     - faculties ${italic.underline(`http://localhost:${this._port}${this._paths.faculties}`)}`)
            console.log(`     - professors ${italic.underline(`http://localhost:${this._port}${this._paths.professors}`)}`)
        })
    }
}


export default Server
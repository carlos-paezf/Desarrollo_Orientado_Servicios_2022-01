'use strict'

import express, { Application, json, urlencoded } from 'express'
import cors from 'cors'
import morgan from 'morgan'
import { green, italic } from 'colors'

import programRoutes from '../../routes/program.routes'
import semesterRoutes from '../../routes/semester.routes'


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        programs: '/api/programs',
        semesters: '/api/semesters'
    }

    constructor() {
        this._app = express()
        this._port = process.env.PORT || '8081'
        this.config()
        this.routes()
    }

    /**
     * This is where we configure the express server 
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
        this._app.use(this._paths.programs, programRoutes)
        this._app.use(this._paths.semesters, semesterRoutes)
    }

    /**
     * This is where we start the server
     */
    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}`))
            console.log(`     - programs ${italic.underline(`http://localhost:${this._port}${this._paths.programs}`)}`)
            console.log(`     - semesters ${italic.underline(`http://localhost:${this._port}${this._paths.semesters}`)}`)
        })
    }
}


export default Server
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
import accessRoutes from '../../routes/access.routes'
import { validate } from '../../middlewares/validate.middleware';


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        programs: '/api/programs',
        semesters: '/api/semesters',
        subjects: '/api/subjects',
        pensums: '/api/pensums',
        pensumSubject: '/api/pensum-subject',
        access: '/api/private/access'
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

    public middlewares = () => {

    }

    /**
     * This is where we define the routes that the server will respond to.
     */
    public routes = (): void => {
        this._app.use(this._paths.programs, programRoutes)
        this._app.use(this._paths.semesters, semesterRoutes)
        this._app.use(this._paths.subjects, subjectRoutes)
        this._app.use(this._paths.pensums, pensumRoutes)
        this._app.use(this._paths.pensumSubject, pensumSubjectRoutes)
        this._app.use(this._paths.access, [validate.validateToken], accessRoutes)
    }

    /**
     * This is where we start the server
     */
    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}\n\n`))
            console.log(`     - programs ${italic.underline(`http://localhost:${this._port}${this._paths.programs}`)}`)
            console.log(`     - semesters ${italic.underline(`http://localhost:${this._port}${this._paths.semesters}`)}`)
            console.log(`     - subjects ${italic.underline(`http://localhost:${this._port}${this._paths.subjects}`)}`)
            console.log(`     - pensums ${italic.underline(`http://localhost:${this._port}${this._paths.pensums}`)}`)
            console.log(`     - pensum-subject ${italic.underline(`http://localhost:${this._port}${this._paths.pensumSubject}`)}`)
            console.log(`     - access ${italic.underline(`http://localhost:${this._port}${this._paths.access}`)}`)
            console.log('\n')
        })
    }
}


export default Server
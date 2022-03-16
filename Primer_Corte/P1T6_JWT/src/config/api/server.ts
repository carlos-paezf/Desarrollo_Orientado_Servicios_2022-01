import express, { Application, json, urlencoded } from "express";
import cors from 'cors'
import morgan from 'morgan'
import { green, italic } from 'colors'
import accessRouter from '../../routes/access.routes';
import userRoutes from '../../routes/user.routes';


class Server {
    private _app: Application
    private _port: string
    private _paths = {
        access: '/api/public/access',
        users: '/api/users'
    }

    constructor() {
        this._app = express()
        this._port = '8081'
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
        this._app.use(this._paths.access, accessRouter)
        this._app.use(this._paths.users, userRoutes)
    }

    public init = (): void => {
        this._app.listen(this._port, () => {
            console.log(green(`Server running locally on ${italic(`http://localhost:${this._port}`)}\n\n`))
            console.log(`     - access ${italic.underline(`http://localhost:${this._port}${this._paths.access}`)}`)
            console.log(`     - users ${italic.underline(`http://localhost:${this._port}${this._paths.users}`)}`)
            console.log('\n')
        })
    }
}


export default Server
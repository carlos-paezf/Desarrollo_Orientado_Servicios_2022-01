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

    /**
     * It sets the port, adds cors, logs requests, and sets the body limit.
     */
    public config(): void {
        this._app.set('PORT', 8088)
        this._app.use(cors())
        this._app.use(morgan('dev'))
        this._app.use(express.json({ limit: '100mb' }))
        this._app.use(express.urlencoded({ extended : true }))
    }

    /**
     * It registers the routes for the application.
     */
    public routes(): void {
        this._app.use('/api/test', routes)
    }

    /**
     * It starts the server.
     */
    public init(): void {
        this._app.listen(this._app.get('PORT'), () => {
            console.log(`Servidor corriendo en modo local en http://localhost:${this._app.get('PORT')}`)
        })
    }
}


export default Server
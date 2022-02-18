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
            console.log('\n\n')
        })
    }
}


export default Server
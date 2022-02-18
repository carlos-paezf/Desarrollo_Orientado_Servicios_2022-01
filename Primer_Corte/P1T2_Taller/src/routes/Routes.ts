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
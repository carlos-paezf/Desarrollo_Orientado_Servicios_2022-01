import { Router } from 'express'
import controller from '../controllers/controller'


class Routes {
    public testRouteAPI

    /**
     * The constructor function is used to create the routes for the API
     */
    constructor() {
        this.testRouteAPI = Router()
        this.config()
    }

    /**
     * `config()` is a function that takes no parameters and returns nothing. 
     * used to configure the application. 
     * called automatically when the application is started. 
     * a good place to add routes and configure the application. 
     * The code above adds a route to the application. 
     * The route is a request handler. 
     * When the application receives a request to `/api/get-info`, the code calls the `attackMe()`
     * function
     */
    public config() {
        this.testRouteAPI.get('/get-info', controller.attackMe)
    }
}


const routes = new Routes()
export default routes.testRouteAPI
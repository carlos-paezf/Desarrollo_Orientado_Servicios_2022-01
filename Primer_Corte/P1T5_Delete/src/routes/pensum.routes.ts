import { Router } from "express";
import pensumController from "../controllers/pensum.controller";


class PensumRoutes {
    public pensumRoutes: Router

    constructor() {
        this.pensumRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * IS creates the routes for the pensum controller.
     */
    public config = (): void => {
        this.pensumRoutes.get('/', pensumController.getPensums)
        this.pensumRoutes.post('/create-pensum', pensumController.postPensum)
    }
}


const pensumRoutes = new PensumRoutes()
export default pensumRoutes.pensumRoutes
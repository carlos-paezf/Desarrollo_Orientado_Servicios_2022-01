import { Router } from "express";
import { pensumControllerDelete, pensumControllerGet, pensumControllerPost } from "../controllers/pensums";
import { pensumControllerUpdate } from '../controllers/pensums/pensum_update.controller';


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
        this.pensumRoutes.get('/', pensumControllerGet.getPensums)
        this.pensumRoutes.get('/:pensumId', pensumControllerGet.getOnePensumById)
        this.pensumRoutes.post('/create-pensum', pensumControllerPost.postPensum)
        this.pensumRoutes.put('/update-pensum/:pensumId', pensumControllerUpdate.updateOnePensumById)
        this.pensumRoutes.delete('/:pensumId', pensumControllerDelete.deleteOnePensumById)
    }
}


const pensumRoutes = new PensumRoutes()
export default pensumRoutes.pensumRoutes
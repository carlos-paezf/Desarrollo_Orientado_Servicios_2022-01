import { Router } from 'express'
import pensumSubjectController from '../controllers/pensum-subject.controller';


class PensumSubjectRoutes {
    public pensumSubjectRoutes: Router

    constructor() {
        this.pensumSubjectRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * IS creates the routes for the pensum controller.
     */
    public config = () => {
        this.pensumSubjectRoutes.get('/', pensumSubjectController.getPensumSubject)
        this.pensumSubjectRoutes.post('/create-pensum-subject', pensumSubjectController.postPensumSubject)
    }
}


const pensumSubjectRoutes = new PensumSubjectRoutes()
export default pensumSubjectRoutes.pensumSubjectRoutes
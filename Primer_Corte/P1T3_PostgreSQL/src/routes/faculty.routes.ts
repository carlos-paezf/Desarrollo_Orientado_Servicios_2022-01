import { Router } from 'express'
import facultyController from '../controllers/faculty.controller';


class FacultyRoutes {
    public facultyRoutes: Router

    constructor() {
        this.facultyRoutes = Router()
        this.config()
    }

    /**
     * This is a function that is called when the class is instantiated. 
     */
    public config = (): void => {
        this.facultyRoutes.get('/', facultyController.getFaculties)
    }
}


const facultyRoutes = new FacultyRoutes()
export default facultyRoutes.facultyRoutes
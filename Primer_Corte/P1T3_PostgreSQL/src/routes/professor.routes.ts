import { Router } from 'express'
import professorController from '../controllers/professor.controller';


class ProfessorRoutes {
    public professorRoutes: Router

    constructor() {
        this.professorRoutes = Router()
        this.config()
    }

    /**
     * This is a function that is called when the class is instantiated. 
     */
    public config = (): void => {
        this.professorRoutes.get('/', professorController.getProfessors)
    }
}


const professorRoutes = new ProfessorRoutes()
export default professorRoutes.professorRoutes
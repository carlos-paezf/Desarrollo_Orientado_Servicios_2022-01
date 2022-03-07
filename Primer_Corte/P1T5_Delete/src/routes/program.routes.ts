'use strict'

import { Router } from 'express'
import programController from '../controllers/program.controller'


class ProgramRoutes {
    public programRoutes: Router

    constructor() {
        this.programRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * IT creates the routes for the program controller.
     */
    public config = (): void => {
        this.programRoutes.get('/', programController.getPrograms)
        this.programRoutes.post('/create-program', programController.postProgram)
    }
}


const programRoutes = new ProgramRoutes()
export default programRoutes.programRoutes
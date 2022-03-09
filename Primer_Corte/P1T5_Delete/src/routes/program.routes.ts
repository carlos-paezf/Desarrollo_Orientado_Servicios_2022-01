'use strict'

import { Router } from 'express'

import {
    programControllerDelete,
    programControllerGet,
    programControllerPost,
    programControllerUpdate
} from '../controllers/programs'


class ProgramRoutes {
    public programRoutes: Router

    constructor() {
        this.programRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * It creates the routes for the program controller.
     */
    public config = (): void => {
        this.programRoutes.get('/', programControllerGet.getPrograms)
        this.programRoutes.get('/:programId', programControllerGet.getOneProgramById)
        this.programRoutes.post('/create-program', programControllerPost.postProgram)
        this.programRoutes.put('/update-program/:programId', programControllerUpdate.updateProgram)
        this.programRoutes.delete('/:programId', programControllerDelete.deleteOneProgramById)
    }
}


const programRoutes = new ProgramRoutes()
export default programRoutes.programRoutes
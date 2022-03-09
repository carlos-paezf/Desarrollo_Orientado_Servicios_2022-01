'use strict'

import { Router } from "express";

import { 
    subjectControllerDelete, 
    subjectControllerGet, 
    subjectControllerPost, 
    subjectControllerUpdate 
} from "../controllers/subjects";


class SubjectRoutes {
    public subjectsRoutes: Router

    constructor() {
        this.subjectsRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * It creates the routes for the program controller.
     */
    public config = (): void => {
        this.subjectsRoutes.get('/', subjectControllerGet.getSubjects)
        this.subjectsRoutes.get('/:subjectId', subjectControllerGet.getOneSubjectById)
        this.subjectsRoutes.post('/create-subject', subjectControllerPost.postSubject)
        this.subjectsRoutes.put('/update-subject/:subjectId', subjectControllerUpdate.updateOneSubjectById)
        this.subjectsRoutes.delete('/:subjectId', subjectControllerDelete.deleteOneSubjectById)
    }
}


const subjectsRoutes = new SubjectRoutes()
export default subjectsRoutes.subjectsRoutes
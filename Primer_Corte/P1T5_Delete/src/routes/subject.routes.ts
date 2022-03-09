'use strict'

import { Router } from "express";
import subjectController from "../controllers/subject.controller";


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
        this.subjectsRoutes.get('/', subjectController.getSubjects)
        this.subjectsRoutes.get('/:subjectId', subjectController.getOneSubjectById)
        this.subjectsRoutes.post('/create-subject', subjectController.postSubject)
        this.subjectsRoutes.delete('/:subjectId', subjectController.deleteOneSubjectById)
    }
}


const subjectsRoutes = new SubjectRoutes()
export default subjectsRoutes.subjectsRoutes
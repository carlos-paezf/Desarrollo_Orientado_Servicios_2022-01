'use strict'

import { Router } from "express";
import subjectController from "../controllers/subject.controller";


class SubjectRoutes {
    public subjectsRoutes: Router

    constructor() {
        this.subjectsRoutes = Router()
        this.config()
    }

    public config = (): void => {
        this.subjectsRoutes.get('/', subjectController.getSubjects)
        this.subjectsRoutes.post('/create-subject', subjectController.postSubject)
    }
}


const subjectsRoutes = new SubjectRoutes()
export default subjectsRoutes.subjectsRoutes
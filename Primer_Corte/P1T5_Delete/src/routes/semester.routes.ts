'use strict'

import { Router } from "express"
import semesterController from '../controllers/semester.controller';


class SemesterRoutes {
    public semesterRoutes: Router

    constructor() {
        this.semesterRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * It creates the routes for the semester controller. 
     */
    public config = (): void => {
        this.semesterRoutes.get('/', semesterController.getSemesters)
        this.semesterRoutes.post('/create-semester', semesterController.postSemester)
    }
}


const semesterRoutes = new SemesterRoutes()
export default semesterRoutes.semesterRoutes